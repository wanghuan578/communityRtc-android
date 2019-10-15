package com.spirit.community.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.spirit.community.common.CommonDef;
import com.spirit.community.common.RpcEventType;
import com.spirit.community.protocol.thrift.common.CommonRes;
import com.spirit.community.protocol.thrift.common.HelloNotify;
import com.spirit.community.protocol.thrift.common.SessionTicket;
import com.spirit.community.protocol.thrift.login.ClientLoginRes;
import com.spirit.community.protocol.thrift.login.ClientPasswordLoginReq;
import com.spirit.community.protocol.thrift.login.ClientPasswordLoginReqChecksum;
import com.spirit.community.protocol.thrift.roomgate.ConnectChecksum;
import com.spirit.community.protocol.thrift.roomgate.ConnectReq;
import com.spirit.community.rtc.avcall.signal.SignalClient;
import com.spirit.community.srpc.core.SRpcBizApp;
import com.spirit.community.srpc.core.State;
import com.spirit.community.srpc.core.observer.Observer;
import com.spirit.tba.Exception.TbaException;
import com.spirit.tba.core.EncryptType;
import com.spirit.tba.core.TbaEvent;
import com.spirit.tba.core.TsRpcHead;
import com.spirit.tba.tools.TbaToolsKit;
import java.io.UnsupportedEncodingException;
import java.util.Random;


public class LoginActivity extends AppCompatActivity {

    private SharedPreferences config;
    private CheckBox rememberPassCheckBox;
    private String uid;
    private String passwd;
    private Button loginBtn;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        config = getSharedPreferences("configuration", MODE_PRIVATE);

        final EditText uidEdit = findViewById(R.id.UserNameEditText);
        final EditText pwdEdit = findViewById(R.id.PasswordEditText);
        rememberPassCheckBox = (CheckBox) findViewById(R.id.remember_pass);

        uidEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
        pwdEdit.setInputType(EditorInfo.TYPE_TEXT_VARIATION_URI);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            uid = bundle.getString("uid");
            passwd = bundle.getString("passwd");
            uidEdit.setText(String.valueOf(uid));
            pwdEdit.setText(passwd);
        }
        else {
            if (config.getBoolean("is_remember", false)) {
                rememberPassCheckBox.setChecked(true);
                uid = config.getString("uid", "");
                passwd = config.getString("passwd", "");
                uidEdit.setText(String.valueOf(uid));
                pwdEdit.setText(passwd);
            }
        }

        String sdcardPath = System.getenv("EXTERNAL_STORAGE");
        Log.i(this.toString(), sdcardPath);
        try {
            SRpcBizApp.getInstance().init();
            SRpcBizApp.getInstance().register(new Observer.EventListener() {
                @Override
                public void onEvent(int type, Object msg) {

                    if (type == RpcEventType.MT_HELLO_NOTIFY) {
                        HelloNotify notify = (HelloNotify) msg;
                        Log.i(this.toString(), "HelloNotifynotify: " + JSON.toJSONString(notify, true));

                        if (SRpcBizApp.getInstance().getState() == State.ROOMGATE_CONNECTING) {

                            ConnectReq req = new ConnectReq();
                            ConnectChecksum checksum = new ConnectChecksum();
                            checksum.client_random = new Random().nextLong();
                            checksum.server_random = notify.server_random;
                            checksum.user_id = Long.valueOf(uid);

                            try {
                                byte[] data = new TbaToolsKit<ConnectChecksum>().serialize(checksum, 1024);
                                req.checksum = new String(data, "ISO8859-1");
                            } catch (TbaException | UnsupportedEncodingException e) {
                                Log.e(this.toString(), e.getMessage());
                            }

                            TsRpcHead head = new TsRpcHead(RpcEventType.ROOMGATE_CONNECT_REQ);
                            SRpcBizApp.getInstance().putEvent(new TbaEvent(head, req, 512, EncryptType.WHOLE));
                        }
                        else {
                            ClientPasswordLoginReq req = new ClientPasswordLoginReq();
                            if (!TextUtils.isEmpty(uidEdit.getText())) {
                                req.user_id = Long.valueOf(uidEdit.getText().toString());
                            }

                            long clientRandom = new Random().nextLong();
                            req.client_random = clientRandom;

                            ClientPasswordLoginReqChecksum checksum = new ClientPasswordLoginReqChecksum();
                            checksum.user_id = req.user_id;
                            if (!TextUtils.isEmpty(pwdEdit.getText())) {
                                checksum.password = pwdEdit.getText().toString();
                            }
                            checksum.server_random = notify.server_random;
                            checksum.client_random = clientRandom;
                            try {
                                byte[] data = new TbaToolsKit<ClientPasswordLoginReqChecksum>().serialize(checksum, 1024);
                                //int srclen = data.length;
                                //Log.i(this.toString(),"srclen: " + srclen);
                                req.check_sum = new String(data, "ISO8859-1");
                            }
                            catch (TbaException | UnsupportedEncodingException e) {
                                Log.e(this.toString(), e.getMessage());
                                Looper.prepare();
                                Toast.makeText(LoginActivity.this, "程序异常", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }

                            TsRpcHead head = new TsRpcHead(RpcEventType.MT_CLIENT_PASSWORD_LOGIN_REQ);
                            SRpcBizApp.getInstance().setState(State.LOGIN_SERVER_LOGIN);
                            SRpcBizApp.getInstance().putEvent(new TbaEvent(head, req, 1024, EncryptType.WHOLE));
                        }
                    }
                    else if (type ==  RpcEventType.MT_CLIENT_LOGIN_RES) {
                        ClientLoginRes res = (ClientLoginRes) msg;
                        Log.i(this.toString(),"ClientLoginRes: " + JSON.toJSONString(res, true));

                        SRpcBizApp.getInstance().getLoginServer().close();

                        if (res.error_code == 0) {
                            SRpcBizApp.getInstance().setUserid(Long.valueOf(uid));
                            try {
                                SessionTicket sessionTicket = new TbaToolsKit<SessionTicket>().deserialize(res.session_ticket.getBytes("ISO8859-1"), SessionTicket.class);
                                SignalClient.getInstance().setIceServer(sessionTicket.ice_server);
                                SignalClient.getInstance().setSignalServer(sessionTicket.signal_server);
                            } catch (IllegalAccessException | TbaException | InstantiationException | UnsupportedEncodingException e) {
                                Log.e(this.toString(), e.getMessage());
                            }

                            boolean checked = rememberPassCheckBox.isChecked();
                            if (checked) {
                                SharedPreferences.Editor edit = config.edit();
                                edit.putString("uid", uid);
                                edit.putString("passwd", passwd);
                                edit.putBoolean("is_remember", true);
                                edit.commit();
                            }

                            try {
                                SRpcBizApp.getInstance().getRoomGate().open(CommonDef.ROOMGATE_HOST, CommonDef.ROOMGATE_PORT);
                            } catch (Exception e) {
                                Log.e(this.toString(), e.getMessage());
                            }
                        }
                        else {
                            loginBtn.post(new Runnable(){
                                @Override
                                public void run() {
                                    loginBtn.setTextColor(0xFFFFFFFF);
                                    loginBtn.setEnabled(true);
                                }
                            });
                            Looper.prepare();
                            Toast.makeText(LoginActivity.this, res.getError_text(), Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    }
                    else if (type == RpcEventType.ROOMGATE_CONNECT_RES) {
                        CommonRes res = (CommonRes) msg;
                        if (res.error_code == 0) {
                            SRpcBizApp.getInstance().setState(State.ROOMGATE_CONNECTED);
                            Intent intent = new Intent(LoginActivity.this, ChatActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            });


        } catch (Exception e) {
            Log.e(this.toString(), e.getMessage());
        }

        loginBtn = findViewById(R.id.LoginBtn);
        loginBtn.setTextColor(0xFFFFFFFF);
        loginBtn.setEnabled(true);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    loginBtn.setTextColor(0xFFD0EFC6);
                    loginBtn.setEnabled(false);

                    if (TextUtils.isEmpty(uidEdit.getText())) {
                        Toast.makeText(LoginActivity.this, "账号为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(pwdEdit.getText())) {
                        Toast.makeText(LoginActivity.this, "密码为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    uid = uidEdit.getText().toString();
                    passwd = pwdEdit.getText().toString();

                    SRpcBizApp.getInstance().getLoginServer().connect(CommonDef.LOGIN_SERVER_HOST, CommonDef.LOGIN_SERVER_PORT);
                } catch (Exception e) {
                    Log.i(this.toString(), e.getMessage());
                }
            }
        });

        registerBtn = findViewById(R.id.RegisterBtn);
        registerBtn.setTextColor(0xFFFFFFFF);
        registerBtn.setEnabled(true);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerBtn.setTextColor(0xFFD0EFC6);
                registerBtn.setEnabled(false);
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loginBtn.setTextColor(0xFFFFFFFF);
        loginBtn.setEnabled(true);
        registerBtn.setTextColor(0xFFFFFFFF);
        registerBtn.setEnabled(true);
    }

    protected void onDestroy() {
        super.onDestroy();
    }




}
