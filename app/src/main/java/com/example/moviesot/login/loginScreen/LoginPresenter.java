package com.example.moviesot.login.loginScreen;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View mView;

    LoginPresenter(LoginContract.View view){
        this.mView = view;
    }

    @Override
    public void setUpView() {
        mView.setFonts();
        mView.configureUpBar();
        mView.configForSoftInputMode();
    }
}
