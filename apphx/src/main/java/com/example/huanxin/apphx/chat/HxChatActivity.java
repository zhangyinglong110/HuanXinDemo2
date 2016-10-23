package com.example.huanxin.apphx.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.huanxin.apphx.R;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.controller.EaseUI;

public class HxChatActivity extends AppCompatActivity {

    //启动当前的Activity
    public static void open(Context context, String chatID) {
        Intent intent = new Intent(context, HxChatActivity.class);
        intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE); //单聊
        intent.putExtra(EaseConstant.EXTRA_USER_ID, chatID);
        context.startActivity(intent);
        //一旦进入聊天界面，就取消通知栏通知
        EaseUI.getInstance().getNotifier().reset();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hx_chat);
        addChatFragment();
    }

    private void addChatFragment() {
        int chatType = getIntent().getIntExtra(EaseConstant.EXTRA_CHAT_TYPE, 0);
        String chatID = getIntent().getStringExtra(EaseConstant.EXTRA_USER_ID);
        HxChatFragment hxChatFragment = HxChatFragment.getInstance(chatType, chatID);
        getSupportFragmentManager().beginTransaction().add(R.id.layout_container, hxChatFragment).commit();
    }
}
