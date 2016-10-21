package com.example.huanxin.apphx;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;

/**
 * 环信相关基础配置
 * 作者：yuanchao on 2016/10/11 0011 11:22
 * 邮箱：yuanchao@feicuiedu.com
 */
public class HxBaseApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();
        // 初始化环信sdk和easeui库
        initEaseUI();
    }

    private void initEaseUI() {
        EMOptions options = new EMOptions();
        options.setAutoLogin(false); // 关闭自动登录
        options.setAcceptInvitationAlways(true); // 自动同意
        EaseUI.getInstance().init(this, options);
        // 关闭环信日志
        EMClient.getInstance().setDebugMode(false);
    }
}
