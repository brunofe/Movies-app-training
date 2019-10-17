package com.example.moviesot.login.startScreen;

public class StartPresenter implements StartContract.Presenter {
    private StartContract.View mView;

    StartPresenter(StartContract.View view) {
        this.mView = view;
    }
    @Override
    public void setUpView() {
        mView.setFonts();
        mView.configureUpBar();
        mView.configForSoftInputMode();
    }
}
