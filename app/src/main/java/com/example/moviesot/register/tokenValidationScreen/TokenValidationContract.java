package com.example.moviesot.register.tokenValidationScreen;

public interface TokenValidationContract {
    interface View {
        void setFonts();
        void configureUpBar();
        void configForSoftInputMode();
    }
    interface Presenter{
        void setUpView();
    }
}
