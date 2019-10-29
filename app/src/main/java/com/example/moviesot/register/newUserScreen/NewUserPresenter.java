package com.example.moviesot.register.newUserScreen;

public class NewUserPresenter implements NewUserContract.Presenter{

    private NewUserContract.View mView;

    NewUserPresenter(NewUserContract.View view){
        this.mView = view;
    }

    @Override
    public void setUpView() {
        mView.setFonts();
        mView.configureUpBar();
        mView.configForSoftInputMode();
    }
}
