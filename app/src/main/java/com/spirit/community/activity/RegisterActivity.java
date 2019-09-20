package com.spirit.community.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.spirit.community.common.RpcEventType;
import com.spirit.community.protocol.thrift.ClientLoginRes;
import com.spirit.community.protocol.thrift.ClientPasswordLoginReq;
import com.spirit.community.protocol.thrift.ClientPasswordLoginReqChecksum;
import com.spirit.community.protocol.thrift.HelloNotify;
import com.spirit.community.protocol.thrift.UserRegisterReq;
import com.spirit.community.protocol.thrift.UserRegisterRes;
import com.spirit.community.srpc.core.SRpcClient;
import com.spirit.community.srpc.core.observer.EventListener;
import com.spirit.tba.Exception.TbaException;
import com.spirit.tba.core.TsEvent;
import com.spirit.tba.core.TsRpcHead;
import com.spirit.tba.utils.TbaUtil;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity {

    private String passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText username = findViewById(R.id.UserNameEditText);
        final EditText nickName = findViewById(R.id.NickNameEditText);
        final EditText pwd = findViewById(R.id.PasswdEditText);
        final EditText email = findViewById(R.id.EmailEditText);
        final EditText cellphone = findViewById(R.id.CellphoneEditText);
        final EditText identityCard = findViewById(R.id.IdentityEditText);

        SRpcClient.getInstance().register(new EventListener() {
            @Override
            public void onEvent(int type, Object msg) {
                switch (type) {
                    case RpcEventType.NETWORK_DISCONNECT: {

                    }
                    break;

                    case RpcEventType.MT_HELLO_NOTIFY: {
                        UserRegisterReq req = new UserRegisterReq();
                        req.user_name = username.getText().toString();
                        req.nick_name = nickName.getText().toString();
                        req.password = passwd = pwd.getText().toString();
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

                if (!TextUtils.isEmpty(username.getText())
                        && !TextUtils.isEmpty(nickName.getText())
                        && !TextUtils.isEmpty(pwd.getText())) {
                    try {
                        SRpcClient.getInstance().getLoginServer().connect("192.168.131.42", 9999);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
