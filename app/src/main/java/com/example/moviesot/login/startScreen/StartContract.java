package com.example.moviesot.login.startScreen;

public interface StartContract {
    interface View {
        void setFonts();
        void configureUpBar();
        void configureButton();
        void configForSoftInputMode();
        void goToFirstScreenFlow(String flow, String email);
    }
    interface Presenter{
        void setUpView();
        void goToNextScreen(String status, String email);
        void getUserInfo(String userInput);
    }
}
