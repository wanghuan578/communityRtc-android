package com.spirit.community.activity;

import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.spirit.community.control.ContactAdapter;
import com.spirit.community.control.DividerItemDecoration;
import com.spirit.community.control.LetterView;
import com.spirit.community.control.UserInfo;
import com.spirit.tba.tools.TbaToolsKit;

import java.util.LinkedList;
import java.util.List;

public class FriendsListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<UserInfo> userInfoList;
    private LinearLayoutManager layoutManager;
    private LetterView letterView;
    private ContactAdapter userListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MultiDex.install(this);
        setContentView(R.layout.activity_friends_list);

        userInfoList = new LinkedList<UserInfo>();

        String [] userName = new String[] {"王欢", "安然","奥兹","德玛","张三丰", "郭靖", "黄蓉", "黄老邪", "赵敏", "123", "天山童姥", "任我行", "于万亭", "陈家洛", "韦小宝", "$6", "穆人清", "陈圆圆", "郭芙", "郭襄", "穆念慈", "东方不败", "梅超风", "林平之", "林远图", "灭绝师太", "段誉", "鸠摩智"};

        for (int i = 0; i < userName.length; i++) {
            UserInfo user = new UserInfo();
            user.setName(userName[i]);
            user.setUid(TbaToolsKit.int2long(new int[]{i , 0}));
            userInfoList.add(user);
        }

        recyclerView = (RecyclerView) findViewById(R.id.contact_list);
        letterView = (LetterView) findViewById(R.id.letter_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        userListAdapter = new ContactAdapter(this, userInfoList);
        recyclerView.setAdapter(userListAdapter);

        letterView.setCharacterListener(new LetterView.CharacterClickListener() {
            @Override
            public void clickCharacter(String character) {
                layoutManager.scrollToPositionWithOffset(userListAdapter.getScrollPosition(character),0);
            }

            @Override
            public void clickArrow() {
                layoutManager.scrollToPositionWithOffset(0,0);
            }
        });

        userListAdapter.setOnItemClickListener(new ContactAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position, UserInfo userInfo) {
                Toast.makeText(FriendsListActivity.this, "click " + position, Toast.LENGTH_SHORT).show();
            }
        });

        userListAdapter.setOnItemLongClickListener(new ContactAdapter.OnItemLongClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(FriendsListActivity.this, "long click " + position, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
