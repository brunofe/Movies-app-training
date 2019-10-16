package com.example.moviesot.login.startScreen;

public class startPresenter implements startContract.Presenter {
    startContract.View mView;

    @Override
    public void setUpView() {
        mView.setFonts();
    }
}
