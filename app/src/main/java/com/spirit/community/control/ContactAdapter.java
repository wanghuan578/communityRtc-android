package com.spirit.community.control;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.spirit.community.activity.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public interface OnItemClickListener {
        void onClick(int position, UserInfo userInfo);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemLongClickListener {
        void onClick(int position);
    }

    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<UserInfo> userList;
    private List<Contact> resultList; // 最终结果（包含分组的字母）
    //private String[] mContactNames; // 联系人名称字符串数组
    //private List<String> pinyinUserist; // 联系人名称List（转换成拼音）
    //private List<Contact> resultList; // 最终结果（包含分组的字母）
    private List<String> characterList; // 字母List

    public enum ITEM_TYPE {
        ITEM_TYPE_CHARACTER,
        ITEM_TYPE_CONTACT
    }

    public ContactAdapter(Context context, List<UserInfo> userList) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.userList = userList;

        before();
    }

    private void before() {

        //pinyinUserist = new ArrayList<>();
        //Map<String, String> map = new HashMap<>();

        for (UserInfo user : userList) {
            String pinyin = Utils.getPingYin(user.getName());
            user.setPinyin(pinyin);
        }

//        for (int i = 0; i < mContactNames.length; i++) {
//            String pinyin = Utils.getPingYin(mContactNames[i]);
//            map.put(pinyin, mContactNames[i]);
//            pinyinUserist.add(pinyin);
//        }
        Collections.sort(userList, new ContactComparator());

        resultList = new ArrayList<>();
        characterList = new ArrayList<>();

        for (UserInfo user : userList) {

            String name = user.getName();
            Log.i(this.toString(),"name: " + name);
            String character = (name.charAt(0) + "").toUpperCase(Locale.ENGLISH);

            if (!characterList.contains(character)) {
                if (character.hashCode() >= "A".hashCode() && character.hashCode() <= "Z".hashCode()) { // 是字母
                    characterList.add(character);
                    resultList.add(new Contact(character, ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal()));
                }
                else {
                    if (!characterList.contains("#")) {
                        characterList.add("#");
                        resultList.add(new Contact("#", ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal()));
                    }
                }
            }

            resultList.add(new Contact(name, ITEM_TYPE.ITEM_TYPE_CONTACT.ordinal()));
        }

//        for (int i = 0; i < pinyinUserist.size(); i++) {
//
//            String name = pinyinUserist.get(i);
//            String character = (name.charAt(0) + "").toUpperCase(Locale.ENGLISH);
//
//            if (!characterList.contains(character)) {
//                if (character.hashCode() >= "A".hashCode() && character.hashCode() <= "Z".hashCode()) { // 是字母
//                    characterList.add(character);
//                    resultList.add(new Contact(character, ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal()));
//                } else {
//                    if (!characterList.contains("#")) {
//                        characterList.add("#");
//                        resultList.add(new Contact("#", ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal()));
//                    }
//                }
//            }
//
//            resultList.add(new Contact(map.get(name), ITEM_TYPE.ITEM_TYPE_CONTACT.ordinal()));
//        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal()) {
            return new CharacterHolder(mLayoutInflater.inflate(R.layout.item_character, parent, false));
        } else {
            return new ContactHolder(mLayoutInflater.inflate(R.layout.item_contact, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CharacterHolder) {
            ((CharacterHolder) holder).mTextView.setText(resultList.get(position).getName());
        } else if (holder instanceof ContactHolder) {
            ((ContactHolder) holder).mTextView.setText(resultList.get(position).getName());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(position, null);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemLongClickListener != null) {
                    onItemLongClickListener.onClick(position);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return resultList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return resultList == null ? 0 : resultList.size();
    }

    public class CharacterHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        CharacterHolder(View view) {
            super(view);

            mTextView = (TextView) view.findViewById(R.id.character);
        }
    }

    public class ContactHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        ContactHolder(View view) {
            super(view);

            mTextView = (TextView) view.findViewById(R.id.contact_name);
        }
    }

    public int getScrollPosition(String character) {
        if (characterList.contains(character)) {
            for (int i = 0; i < resultList.size(); i++) {
                if (resultList.get(i).getName().equals(character)) {
                    return i;
                }
            }
        }

        return -1; // -1不会滑动
    }
}