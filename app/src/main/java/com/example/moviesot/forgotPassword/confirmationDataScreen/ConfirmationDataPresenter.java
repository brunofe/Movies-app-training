package com.example.moviesot.forgotPassword.confirmationDataScreen;

public class ConfirmationDataPresenter implements ConfirmationDataContract.Presenter {
    ConfirmationDataContract.View mView;

    ConfirmationDataPresenter(ConfirmationDataContract.View view){
        this.mView = view;
    }

    @Override
    public void setUpView() {
        mView.setFonts();
        mView.configureUpBar();
        mView.configForSoftInputMode();
    }
}
