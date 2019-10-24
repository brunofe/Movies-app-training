package com.example.moviesot.forgotPassword.confirmationDataScreen;

public interface ConfirmationDataContract {
    interface View {
        void setFonts();
        void configureUpBar();
        void configForSoftInputMode();
    }
    interface Presenter{
        void setUpView();
    }
}
