package com.example.moviesot.register.pendingRegisterScreen;

public interface PendingRegisterContract {
    interface View {
        void setFonts();
        void configureUpBar();
        void configForSoftInputMode();
    }
    interface Presenter{
        void setUpView();
    }
}
