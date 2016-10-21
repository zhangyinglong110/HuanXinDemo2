package com.example.huanxindemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.example.huanxindemo.user.LoginFragment;
import com.example.huanxindemo.user.RegisterFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2016/10/20 0020.
 * 项目开始的Splash页面
 */

public class SlpashActivity extends AppCompatActivity {

    @BindView(R.id.image_logo)
    ImageView imageLogo;
    @BindView(R.id.button_register)
    Button buttonRegister;
    @BindView(R.id.button_login)
    Button buttonLogin;


    private Unbinder mUnbinder;
    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }


    @Override public void onContentChanged() {
        super.onContentChanged();
        mUnbinder = ButterKnife.bind(this);
    }
    @OnClick(R.id.button_login)
    public void showLoginDialog() {
        if (loginFragment == null) {
            loginFragment = new LoginFragment();
        }
        loginFragment.show(getSupportFragmentManager(), null);
    }

    @OnClick(R.id.button_register)
    public void showRegisterDialog() {
        if (registerFragment == null) {
            registerFragment = new RegisterFragment();
        }
        registerFragment.show(getSupportFragmentManager(), null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
