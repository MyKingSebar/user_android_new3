package com.bokang.yijia;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.latte.activities.ProxyActivity;
import com.example.latte.app.AccountManager;
import com.example.latte.app.IUserChecker;
import com.example.latte.app.Latte;
import com.example.latte.delegates.LatteDelegate;
import com.example.latte.ec.launcher.LauncherDelegate;
import com.example.latte.ec.main.EcBottomDelegate;
import com.example.latte.ec.sign.ISignListener;
import com.example.latte.ec.sign.SignInDelegate;
import com.example.latte.ui.launcher.ILauncherListener;
import com.example.latte.ui.launcher.OnLauncherFinishTag;

import qiu.niorgai.StatusBarCompat;

public class MainActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    public LatteDelegate setRootDelegate() {
//        return new ExampleDelegate();
//        return new LauncherDelegate();
//        return new SignUpDelegate();
//        return new LauncherScrollDelegate();
        return new com.yijia.common_yijia.sign.SignInDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        Toast.makeText(this, "onLauncherFinish" + tag, Toast.LENGTH_LONG).show();
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动成功，用户登录了", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动成功，用户没登录", Toast.LENGTH_LONG).show();
//                startWithPop(new SignInDelegate());
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            default:
                break;
        }
    }

    private LatteDelegate getSignDelegate(){
        //检查用户是否登陆了APP
        AccountManager.checkAccont(new IUserChecker() {
            @Override
            public void onSignIn() {
//                return new EcBottomDelegate;
                Toast.makeText(getApplicationContext(), "启动成功，用户登录了", Toast.LENGTH_LONG).show();
//                getSupportDelegate().startWithPop(new EcBottomDelegate());
            }

            @Override
            public void onNoSignIn() {
                Toast.makeText(getApplicationContext(), "启动成功，用户没登录", Toast.LENGTH_LONG).show();
//                getSupportDelegate().startWithPop(new EcBottomDelegate());
//                return new SignInDelegate();
            }

        });
        return null;
    }

}
