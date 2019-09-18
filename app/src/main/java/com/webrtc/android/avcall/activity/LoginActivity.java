package com.webrtc.android.avcall.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


import com.webrtc.android.avcall.R;

import pub.devrel.easypermissions.EasyPermissions;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.LoginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                intent.putExtra("ServerAddr", addr);
//                intent.putExtra("RoomName", roomName);
                startActivity(intent);
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

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }


    protected void onDestroy() {
        super.onDestroy();
    }
}
