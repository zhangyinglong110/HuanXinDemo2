package com.example.huanxin.apphx.contact;

import android.os.Bundle;
import android.util.Log;

import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMContactManager;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.exceptions.HyphenateException;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/10/21 0021.
 */

public class HxContactListFragment extends EaseContactListFragment implements EMContactListener {
    private EMContactManager mEMContactManager;
    private List<String> contacts;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        customUI();
        mEMContactManager = EMClient.getInstance().contactManager();
        mEMContactManager.setContactListener(this);
        asyncGetContactsFromServer();
    }

    private void customUI() {
        // 隐藏EaseUI的标题栏
        super.hideTitleBar();
    }

    private void asyncGetContactsFromServer() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    contacts = mEMContactManager.getAllContactsFromServer();
                    refreshContacts();//刷新联系人列表
                } catch (HyphenateException e) {
                    Log.d("apphx", "asyncGetContactsFromServer! Exception");
                }
            }


        };
        new Thread(runnable).start();
    }


    private void refreshContacts() {
        HashMap<String, EaseUser> hashMap = new HashMap<String, EaseUser>();
        for (String hxId : contacts) {
            EaseUser easeUser = new EaseUser(hxId);
            hashMap.put(hxId, easeUser);
        }
        //设置当前视图上的联系人
        setContactsMap(hashMap);
        //刷新当前的视图列表
        refresh();
    }


    //联系人的监听 start----------------------------------------
    //添加联系人
    @Override
    public void onContactAdded(String s) {
        contacts.add(s);
        refreshContacts();
    }

    //删除联系人
    @Override
    public void onContactDeleted(String s) {
        contacts.remove(s);
        refreshContacts();
    }

    //收到好友的邀请
    @Override
    public void onContactInvited(String s, String s1) {
        // TODO: 2016/10/11 0011 显示好友邀请信息(同意，拒绝的交互按钮)
    }

    // 好友请求被同意
    @Override
    public void onContactAgreed(String s) {
        // TODO: 2016/10/11 0011 当对方同意你的好友申请, 显示对方已接受
    }

    // 好友请求被拒绝
    @Override
    public void onContactRefused(String s) {
        // TODO: 2016/10/11 0011 当对方拒绝你的好友申请，显示对方已拒绝(一般不做处理)
    }
    //联系人的监听 end----------------------------------------

}
