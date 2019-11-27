package com.example.moviesot.bases;

import android.os.Bundle;

public interface BaseContract {

    interface View extends BaseView { }

    interface Presenter {
        void init(Bundle extras);

        Bundle getExtras();

        void setExtras(Bundle extras);

    }
}
