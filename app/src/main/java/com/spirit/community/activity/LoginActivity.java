package com.spirit.community.activity;

import android.Manifest;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.spirit.community.common.RpcEventType;
import com.spirit.community.protocol.thrift.ClientLoginRes;
import com.spirit.community.protocol.thrift.ClientPasswordLoginReq;
import com.spirit.community.protocol.thrift.ClientPasswordLoginReqChecksum;
import com.spirit.community.protocol.thrift.HelloNotify;
import com.spirit.community.srpc.core.SRpcClient;
import com.spirit.community.srpc.core.observer.EventObserver;
import com.spirit.tba.Exception.TbaException;
import com.spirit.tba.core.TsEvent;
import com.spirit.tba.core.TsRpcHead;
import com.spirit.tba.utils.TbaUtil;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import pub.devrel.easypermissions.EasyPermissions;

public class LoginActivity extends AppCompatActivity {

    private Long serverRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText uid = findViewById(R.id.UserNameEditText);
        final EditText pwd = findViewById(R.id.PasswordEditText);

        try {
            SRpcClient.getInstance().register(new EventObserver() {
                @Override
                public void onEvent(int type, Object msg) {
                    switch (type) {
                        case RpcEventType.NETWORK_DISCONNECT: {

                        }
                        break;

                        case RpcEventType.MT_COMMON_HELLO_NOTIFY: {
                            HelloNotify notify = (HelloNotify) msg;
                            System.out.println("HelloNotifynotify: " + JSON.toJSONString(notify, true));

                            ClientPasswordLoginReq req = new ClientPasswordLoginReq();
                            if (!TextUtils.isEmpty(uid.getText())) {
                                req.user_id = Long.valueOf(uid.getText().toString());
                            }

                            long clientRandom = new Random().nextLong();
                            req.client_random = clientRandom;

                            ClientPasswordLoginReqChecksum checksum = new ClientPasswordLoginReqChecksum();
                            checksum.user_id = req.user_id;
                            if (!TextUtils.isEmpty(pwd.getText())) {
                                checksum.password = pwd.getText().toString();
                            }
                            checksum.server_random = notify.server_random;
                            checksum.client_random = clientRandom;
                            try {
                                byte[] data = new TbaUtil<ClientPasswordLoginReqChecksum>().Serialize(checksum, 1024);
                                int srclen = data.length;
                                System.out.println("srclen: " + srclen);

                                req.check_sum = new String(data, "ISO8859-1");
                                int destlen = req.check_sum.getBytes("ISO8859-1").length;

                                ClientPasswordLoginReqChecksum check = new TbaUtil<ClientPasswordLoginReqChecksum>().Deserialize(req.check_sum.getBytes("ISO8859-1"), ClientPasswordLoginReqChecksum.class);
                                System.out.println("ClientPasswordLoginReqChecksum: " + JSON.toJSONString(check, true));

                                System.out.println("destlen: " + destlen);
                            } catch (TbaException | IllegalAccessException | InstantiationException | UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }

                            TsRpcHead head = new TsRpcHead(RpcEventType.MT_CLIENT_PASSWORD_LOGIN_REQ);
                            SRpcClient.getInstance().putEvent(new TsEvent(head, req, 1024));
                        }
                        break;

                        case RpcEventType.MT_CLIENT_LOGIN_RES: {
                            ClientLoginRes res = (ClientLoginRes) msg;
                            System.out.println(JSON.toJSONString(res, true));
                            if (res.error_code == 0) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                //startActivity(intent);
                            }
                        }
                        break;

                        default:
                            break;
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

        findViewById(R.id.LoginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SRpcClient.getInstance().connect("192.168.131.42", 9999);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.RegisterBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
//                intent.putExtra("ServerAddr", addr);
//                intent.putExtra("RoomName", roomName);
                startActivity(intent);
            }
        });

        String[] perms = {Manifest.permission.ACCESS_NETWORK_STATE};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, "Need permissions for camera & microphone", 0, perms);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }


    protected void onDestroy() {
        super.onDestroy();
    }




}
