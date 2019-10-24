package com.example.moviesot.register.pendingRegister;

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
