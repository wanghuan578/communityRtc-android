package com.spirit.community.activity;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.spirit.community.common.CommonDef;
import com.spirit.community.common.RpcEventType;
import com.spirit.community.protocol.thrift.login.UserRegisterReq;
import com.spirit.community.protocol.thrift.login.UserRegisterRes;
import com.spirit.community.srpc.core.SRpcClient;
import com.spirit.community.srpc.core.observer.Observer;
import com.spirit.tba.core.TsEvent;
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

        SRpcClient.getInstance().register(new Observer.EventListener() {
            @Override
            public void onEvent(int type, Object msg) {
                switch (type) {
                    case RpcEventType.NETWORK_DISCONNECT: {
//                        Looper.prepare();
//                        Toast.makeText(RegisterActivity.this, "网络异常", Toast.LENGTH_LONG).show();
//                        Looper.loop();
                    }
                    break;

                    case RpcEventType.MT_HELLO_NOTIFY: {

                        UserRegisterReq req = new UserRegisterReq();
                        req.user_name = usernameEdit.getText().toString();
                        req.nick_name = nickNameEdit.getText().toString();
                        req.password = passwd = passwdEdit.getText().toString();
                        req.invitation_code = invitationCodeEdit.getText().toString();
                        req.gender = 0;
                        req.cellphone = cellphoneEdit.getText().toString();
                        req.email = emailEdit.getText().toString();

                        //req.gender =
                        TsRpcHead head = new TsRpcHead(RpcEventType.MT_CLIENT_REGISTER_REQ);
                        SRpcClient.getInstance().putEvent(new TsEvent(head, req, 1024));
                    }
                        break;

                    case RpcEventType.MT_CLIENT_REGISTER_RES: {
                        UserRegisterRes res = (UserRegisterRes) msg;
                        System.out.println(JSON.toJSONString(res, true));

                        SRpcClient.getInstance().getLoginServer().close();

                        if (res.error_code == 0) {
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            intent.putExtra("uid", res.getUser_id());
                            intent.putExtra("passwd", passwd);
                            startActivity(intent);
                        }
                        else {
                            Looper.prepare();
                            Toast.makeText(RegisterActivity.this, res.error_text, Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    }
                    break;

                    default:
                        break;
                }
            }
        });

        findViewById(R.id.SubmitBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                usernameEdit.setHint(null);
//                nickNameEdit.setHint(null);

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

                try {
                    SRpcClient.getInstance().getLoginServer().connect(CommonDef.host, CommonDef.port);
                } catch (Exception e) {
                    Toast.makeText(RegisterActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
