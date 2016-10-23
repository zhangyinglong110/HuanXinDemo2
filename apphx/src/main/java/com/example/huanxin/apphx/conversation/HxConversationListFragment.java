package com.example.huanxin.apphx.conversation;

import android.os.Bundle;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMChatManager;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.ui.EaseConversationListFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/10/23 0023.
 */

public class HxConversationListFragment extends EaseConversationListFragment implements EMMessageListener {
    private EMChatManager mEMChatManager;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        customUI();
        //消息的监听。有新的消息就会立刻刷新
        mEMChatManager = EMClient.getInstance().chatManager();
        mEMChatManager.addMessageListener(this);
    }

    private void customUI() {
        hideTitleBar();
    }


    // start-interface: EMMessageListener
    // 接受消息接口,在接受到文本、图片、...,将来调用
    @Override
    public void onMessageReceived(List<EMMessage> list) {
        refresh();
    }

    // 命令消息体,透传消息
    @Override
    public void onCmdMessageReceived(List<EMMessage> list) {

    }

    // 接收到消息已读的回执(对方已经读了些条消息)
    @Override
    public void onMessageReadAckReceived(List<EMMessage> list) {

    }

    // 收到消息体的发送回执（消息已成功发送到对方设备）
    @Override
    public void onMessageDeliveryAckReceived(List<EMMessage> list) {

    }

    @Override
    public void onMessageChanged(EMMessage emMessage, Object o) {

    }

    // end-interface: EMMessageListener
}
