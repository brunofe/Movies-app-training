package com.example.moviesot.register.tokenValidationScreen;

public class TokenValidationPresenter implements TokenValidationContract.Presenter{
    TokenValidationContract.View mView;

    TokenValidationPresenter(TokenValidationContract.View view){
        this.mView = view;
    }


    @Override
    public void setUpView() {
        mView.configForSoftInputMode();
        mView.configureUpBar();
        mView.setFonts();
    }
}
