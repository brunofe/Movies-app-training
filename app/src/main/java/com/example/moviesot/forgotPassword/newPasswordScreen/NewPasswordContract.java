package com.example.moviesot.forgotPassword.newPasswordScreen;

public interface NewPasswordContract {
    interface View {
        void setFonts();
        void configureUpBar();
        void configForSoftInputMode();
    }
    interface Presenter{
        void setUpView();
    }
}
