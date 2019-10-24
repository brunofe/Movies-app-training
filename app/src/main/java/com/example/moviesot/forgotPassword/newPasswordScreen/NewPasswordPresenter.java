package com.example.moviesot.forgotPassword.newPasswordScreen;

public class NewPasswordPresenter implements NewPasswordContract.Presenter {

    NewPasswordContract.View mView;
    NewPasswordPresenter(NewPasswordContract.View view){
        mView = view;
    }

    @Override
    public void setUpView() {
        mView.setFonts();
        mView.configureUpBar();
        mView.configForSoftInputMode();
    }
}
