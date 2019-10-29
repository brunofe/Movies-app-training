package com.example.moviesot.register.pendingRegisterScreen;

public class PendingRegisterPresenter implements PendingRegisterContract.Presenter {

    PendingRegisterContract.View mView;

    PendingRegisterPresenter(PendingRegisterContract.View view){
        this.mView = view;
    }

    @Override
    public void setUpView() {
        mView.configForSoftInputMode();
        mView.configureUpBar();
        mView.setFonts();
    }
}
