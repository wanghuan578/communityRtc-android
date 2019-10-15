package com.spirit.community.activity;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.spirit.community.common.RpcEventType;
import com.spirit.community.control.ChatMsgEntity;
import com.spirit.community.control.ChatMsgViewAdapter;
import com.spirit.community.protocol.thrift.common.HelloNotify;
import com.spirit.community.protocol.thrift.roomgate.ChatReq;
import com.spirit.community.srpc.core.SRpcBizApp;
import com.spirit.community.srpc.core.State;
import com.spirit.community.srpc.core.observer.Observer;
import com.spirit.tba.core.EncryptType;
import com.spirit.tba.core.TbaEvent;
import com.spirit.tba.core.TsRpcHead;
import com.spirit.tba.tools.TbaToolsKit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {



    private Button mBtnSend;
    private Button mBtnBack;
    private EditText mEditTextContent;
    //聊天内容的适配器
    private ChatMsgViewAdapter mAdapter;
    private ListView mListView;
    //聊天的内容
    private final List<ChatMsgEntity> mDataArrays;
    private final Set<Long> userTuple;

    public ChatActivity() {
        mDataArrays = new ArrayList<ChatMsgEntity>();
        userTuple = new HashSet<Long>();
        userTuple.add(100103l);
        userTuple.add(100099l);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_chat);
        initView();
        initData();
        SRpcBizApp.getInstance().register(new Observer.EventListener() {
            @Override
            public void onEvent(int type, Object msg) {
                if (type == RpcEventType.ROOMGATE_CHAT_NOTIFY) {
                    ChatReq notify = (ChatReq) msg;
                    Log.i(this.toString(), "chat notify: " + JSON.toJSONString(notify, true));

                    ChatMsgEntity entity = new ChatMsgEntity();
                    entity.setDate(getDate());
                    entity.setName(notify.from_nick_name);
                    entity.setMsgType(true);
                    entity.setText(notify.chat_text);
                    mDataArrays.add(entity);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listview);
        mBtnBack = (Button) findViewById(R.id.btn_back);
        mBtnBack.setOnClickListener(this);
        mBtnSend = (Button) findViewById(R.id.btn_send);
        mBtnSend.setOnClickListener(this);
        mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);
    }

    private void initData() {
        mAdapter = new ChatMsgViewAdapter(this, mDataArrays);
        mListView.setAdapter(mAdapter);
    }

    public void onClick(View view) {
        // TODO Auto-generated method stub
        switch(view.getId()) {
            case R.id.btn_back:
                back();
                break;
            case R.id.btn_send:
                send();
                break;
        }
    }

    private void send()
    {
        String contString = mEditTextContent.getText().toString();
        if (contString.length() > 0)
        {
            String nickName = "spirit";
            ChatMsgEntity entity = new ChatMsgEntity();
            entity.setDate(getDate());
            entity.setName(nickName);
            entity.setMsgType(false);
            entity.setText(contString);
            mDataArrays.add(entity);
            mAdapter.notifyDataSetChanged();
            mEditTextContent.setText("");
            mListView.setSelection(mListView.getCount() - 1);

            long suid = 0;
            long duid = 0;
            for(Long uid : userTuple) {
                if (uid.longValue() == SRpcBizApp.getInstance().getUserid().longValue()) {
                    suid = uid;
                }
                else {
                    duid = uid;
                }
            }

            int [] src = TbaToolsKit.long2int(suid);
            int [] dest = TbaToolsKit.long2int(duid);
//            int s0 = (int) (suid & 0x000000ffffffffL);
//            int s1 = (int) (suid >> 32);
//            int d0 = (int) (duid & 0x000000ffffffffL);
//            int d1 = (int) (duid >> 32);
            ChatReq req = new ChatReq();
            req.from_nick_name = nickName;
            req.chat_text = contString;
            req.chat_time = System.currentTimeMillis();
            TsRpcHead head = new TsRpcHead(RpcEventType.ROOMGATE_CHAT_REQ);
            head.SetAttach1(src[0]);
            head.SetAttach2(src[1]);
            head.SetAttach3(dest[0]);
            head.SetAttach4(dest[1]);
            SRpcBizApp.getInstance().putEvent(new TbaEvent(head, req, 512, EncryptType.BODY));
        }
    }

    //获取日期
    private String getDate() {
        Calendar c = Calendar.getInstance();
        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH));
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 1);
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String mins = String.valueOf(c.get(Calendar.MINUTE));
        StringBuffer sbBuffer = new StringBuffer();
        sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":" + mins);
        return sbBuffer.toString();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        back();
        return true;
    }

    private void back() {
        finish();
    }
}
