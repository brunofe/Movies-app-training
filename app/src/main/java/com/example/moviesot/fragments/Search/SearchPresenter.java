package com.example.moviesot.fragments.Search;

import com.example.moviesot.bases.BasePresenter;

import com.example.moviesot.model.ListMovie;
import com.example.moviesot.repository.homeRepository.HomeRepository;
import com.example.moviesot.repository.homeRepository.HomeRepositoryImpl;

public class SearchPresenter extends BasePresenter implements SearchContract.Presenter {

    private HomeRepositoryImpl homeRepositoryImpl;
    private SearchContract.View mView;
    private String page;

    SearchPresenter(SearchContract.View view){
        super(view);
        this.mView =(view);
        this.homeRepositoryImpl = new HomeRepositoryImpl();
    }

    @Override
    public void init() {

    }

    @Override
    public void getListMovie(String name, final String page){


        homeRepositoryImpl.getMovieList(name, page, new HomeRepository.MovieListaCallBack() {
            @Override
            public void onSuccess(ListMovie response) {
                if(page.equals("1")){
                    mView.setFirstPage(response);
                } else {
                    mView.setOthersPage(response);
                }
            }
        });
    }



    @Override
    public void testePresenter() {

    }
}
