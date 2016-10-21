package com.example.huanxindemo.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.huanxindemo.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2016/10/20 0020.
 * 注册模块
 */

public class RegisterFragment extends DialogFragment {


    @BindView(R.id.edit_username)
    TextInputEditText editUsername;
    @BindView(R.id.edit_password)
    TextInputEditText editPassword;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.button_confirm)
    Button buttonConfirm;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @OnClick(R.id.button_confirm)
    public void register() {
        String username = editUsername.getText().toString().trim();
        String userpwd = editPassword.getText().toString().trim();
        handlerRegister(username, userpwd);
    }


    private void handlerRegister(final String username, final String userpwd) {
        //开始loading
        startLoading();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(userpwd)) {
            stopLoading();
            String info = getString(R.string.user_error_not_null);
            showRegisterFail(info);
            return;
        }
        //执行连接
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(username, userpwd);
                    //注册成功将会执行下面操作
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            stopLoading();
                            showRegisterSuccess();
                            dismiss();
                        }
                    });
                } catch (final HyphenateException e) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            stopLoading();
                            showRegisterFail(e.getDescription());
                        }
                    });
                }
            }
        }).start();
    }


    //*******************start视图工作*****************
    public void startLoading() {
        setCancelable(false);
        buttonConfirm.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void stopLoading() {
        setCancelable(true);
        buttonConfirm.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }


    public void showRegisterSuccess() {
        Toast.makeText(getContext(), "注册成功", Toast.LENGTH_SHORT).show();
    }

    public void showRegisterFail(String msg) {
        String info = getString(R.string.user_error_register_fail, msg);
        Toast.makeText(getContext(), info, Toast.LENGTH_SHORT).show();
    }


    //*******************endt视图工作*****************

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
