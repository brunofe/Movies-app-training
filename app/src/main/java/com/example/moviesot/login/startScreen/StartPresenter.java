package com.example.moviesot.login.startScreen;

import com.example.moviesot.repository.UsersRepository;

public class StartPresenter implements StartContract.Presenter {
    private StartContract.View mView;

    StartPresenter(StartContract.View view) {
        this.mView = view;
    }

    public StartPresenter(){

    }
    @Override
    public void setUpView() {
        mView.setFonts();
        mView.configureUpBar();
        mView.configForSoftInputMode();
        mView.configureButton();
    }

    @Override
    public void goToNextScreen(String status, String email) {
        String flow;

        if(status.equals("REGISTERED")){
            flow="LoginFlow";
        } else if(status.equals("PENDING")){
            flow="PendingFlow";
        } else if(status.equals("INEXISTENT")) {
            flow="RegisterFlow";
        } else {
            flow="RegisterFlow";
        }

       mView.goToFirstScreenFlow(flow, email);
    }

    @Override
    public void getUserInfo(String userInput) {
        UsersRepository repository = new UsersRepository();

        repository.getUser(userInput);
    }
}
