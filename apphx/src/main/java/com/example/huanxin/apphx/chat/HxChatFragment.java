package com.example.huanxin.apphx.chat;

import android.os.Bundle;

import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;

/**
 * 环信聊天页面
 * Created by Administrator on 2016/10/23 0023.
 */

public class HxChatFragment extends EaseChatFragment {

    /**
     * 如果需要用到HxChatFragment的话， 需要传递两个参数
     *
     * @param chatType 聊天的类型(单聊？群聊)
     * @param chatID   聊天的对象(聊天目标的id)
     * @return
     */
    public static HxChatFragment getInstance(int chatType, String chatID) {
        HxChatFragment chatFragment = new HxChatFragment();
        Bundle args = new Bundle();
        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, chatType);
        args.putString(EaseConstant.EXTRA_USER_ID, chatID);
        chatFragment.setArguments(args);
        return chatFragment;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        customUI();
    }

    private void customUI() {
        hideTitleBar();
    }
}
