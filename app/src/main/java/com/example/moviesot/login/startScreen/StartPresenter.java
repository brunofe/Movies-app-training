package com.example.moviesot.login.startScreen;

public class StartPresenter implements StartContract.Presenter {
    private StartContract.View mView;

    @Override
    public void setUpView() {
        mView.setFonts();
    }
}
