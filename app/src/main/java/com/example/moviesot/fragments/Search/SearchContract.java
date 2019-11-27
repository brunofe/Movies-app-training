package com.example.moviesot.fragments.Search;

import com.example.moviesot.bases.BaseContract;
import com.example.moviesot.model.ListMovie;

public class SearchContract {

    interface View extends BaseContract.View {

       void teste();
       void setFirstPage(ListMovie listMovie);
        void setOthersPage(ListMovie listMovie);

    }

    interface Presenter extends BaseContract.Presenter {

        void testePresenter();

        void getListMovie(String name, String page);




    }
}
