package com.spirit.community.activity;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.spirit.community.common.CommonDef;
import com.spirit.community.common.RpcEventType;
import com.spirit.community.protocol.thrift.login.UserRegisterReq;
import com.spirit.community.protocol.thrift.login.UserRegisterRes;
import com.spirit.community.srpc.core.SRpcBizApp;
import com.spirit.community.srpc.core.observer.Observer;
import com.spirit.tba.core.TbaEvent;
import com.spirit.tba.core.TsRpcHead;

public class RegisterActivity extends AppCompatActivity {

    private String passwd;

    private EditText usernameEdit = null;
    private EditText nickNameEdit = null;
    private EditText passwdEdit = null;
    private EditText emailEdit = null;
    private EditText cellphoneEdit = null;
    private EditText identityCardEdit = null;
    private EditText invitationCodeEdit = null;

    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEdit = findViewById(R.id.UserNameEditText);
        nickNameEdit = findViewById(R.id.NickNameEditText);
        passwdEdit = findViewById(R.id.PasswdEditText);
        emailEdit = findViewById(R.id.EmailEditText);
        cellphoneEdit = findViewById(R.id.CellphoneEditText);
        identityCardEdit = findViewById(R.id.IdentityEditText);
        invitationCodeEdit = findViewById(R.id.InvitationCodeEditText);

        SRpcBizApp.getInstance().register(new Observer.EventListener() {
            @Override
            public void onEvent(int type, Object msg) {

                if (type == RpcEventType.MT_HELLO_NOTIFY) {
                    UserRegisterReq req = new UserRegisterReq();
                    req.user_name = usernameEdit.getText().toString();
                    req.nick_name = nickNameEdit.getText().toString();
                    req.password = passwd = passwdEdit.getText().toString();
                    req.invitation_code = invitationCodeEdit.getText().toString();
                    req.gender = 0;
                    req.identity_card = identityCardEdit.getText().toString();
                    req.cellphone = cellphoneEdit.getText().toString();
                    req.email = emailEdit.getText().toString();

                    TsRpcHead head = new TsRpcHead(RpcEventType.MT_CLIENT_REGISTER_REQ);
                    SRpcBizApp.getInstance().putEvent(new TbaEvent(head, req, 1024, false));
                }
                else if (type ==  RpcEventType.MT_CLIENT_REGISTER_RES) {
                    UserRegisterRes res = (UserRegisterRes) msg;

                    Log.i(this.toString(), "UserRegisterRes: " + JSON.toJSONString(res, true));

                    SRpcBizApp.getInstance().getLoginServer().close();

                    if (res.error_code == 0) {
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        intent.putExtra("uid", res.getUser_id());
                        intent.putExtra("passwd", passwd);
                        startActivity(intent);
                    }
                    else {
                        registerBtn.post(new Runnable(){
                            @Override
                            public void run() {
                                registerBtn.setTextColor(0xFFFFFFFF);
                                registerBtn.setEnabled(true);
                            }
                        });

                        Looper.prepare();
                        Toast.makeText(RegisterActivity.this, res.error_text, Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }
            }
        });

        registerBtn = findViewById(R.id.SubmitBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(usernameEdit.getText())) {
                    Toast.makeText(RegisterActivity.this, "username为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(nickNameEdit.getText())) {
                    Toast.makeText(RegisterActivity.this, "nickname为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(passwdEdit.getText())) {
                    Toast.makeText(RegisterActivity.this, "password为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(invitationCodeEdit.getText())) {
                    Toast.makeText(RegisterActivity.this, "邀请码为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                registerBtn.setTextColor(0xFFD0EFC6);
                registerBtn.setEnabled(false);

                try {
                    SRpcBizApp.getInstance().getLoginServer().connect(CommonDef.LOGIN_SERVER_HOST, CommonDef.LOGIN_SERVER_PORT);
                }
                catch (Exception e) {
                    Log.e(this.toString(), e.getMessage());
                    Toast.makeText(RegisterActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerBtn.setTextColor(0xFFFFFFFF);
        registerBtn.setEnabled(true);
    }
}
