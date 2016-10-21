package com.example.huanxindemo.user;

import android.content.Intent;
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

import com.example.huanxindemo.HomeActivity;
import com.example.huanxindemo.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2016/10/20 0020.
 * 登录模块
 */

public class LoginFragment extends DialogFragment {


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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @OnClick(R.id.button_confirm)
    public void Login() {
        String username = editUsername.getText().toString().trim();
        String userpwd = editPassword.getText().toString().trim();
        handlerLogin(username, userpwd);
    }

    private void handlerLogin(String username, String userpwd) {
        //开始loading
        startLoading();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(userpwd)) {
            stopLoading();
            String info = getString(R.string.user_error_not_null);
            showLoginFail(info);
            return;
        }
        //执行连接
        EMClient.getInstance().login(username, userpwd, new EMCallBack() {
            @Override
            public void onSuccess() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        stopLoading();
                        navigateToHome();
                        dismiss();
                    }
                });
            }

            @Override
            public void onError(int i, final String s) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        stopLoading();
                        showLoginFail(s);
                    }
                });
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });


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


    public void showLoginSuccess() {
        Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();
    }

    public void showLoginFail(String msg) {
        String info = getString(R.string.user_error_login_fail, msg);
        Toast.makeText(getContext(), info, Toast.LENGTH_SHORT).show();
    }

    public void navigateToHome() {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        startActivity(intent);
        getActivity().finish();
    }


    //*******************endt视图工作*****************

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
