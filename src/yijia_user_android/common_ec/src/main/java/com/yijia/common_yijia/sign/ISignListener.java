package com.yijia.common_yijia.sign;


public interface ISignListener {

    void onSignInSuccess();

    void onSignInFailure(String msg);

    void onSignUpSuccess();

    void onSignUpFailure(String msg);
}
