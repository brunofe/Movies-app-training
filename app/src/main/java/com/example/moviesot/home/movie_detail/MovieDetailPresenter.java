package com.example.moviesot.home.movie_detail;


import com.example.moviesot.bases.BasePresenter;
import com.example.moviesot.model.ListMovie;
import com.example.moviesot.model.MovieDetail;
import com.example.moviesot.repository.homeRepository.HomeRepository;
import com.example.moviesot.repository.homeRepository.HomeRepositoryImpl;

public class MovieDetailPresenter extends BasePresenter implements MovieDetailContract.Presenter {

    private MovieDetailContract.View mView;
    private HomeRepositoryImpl homeRepositoryImpl;

    MovieDetailPresenter(MovieDetailContract.View view){
        super(view);
        this.mView =(view);
        this.homeRepositoryImpl = new HomeRepositoryImpl();
    }

    @Override
    public void init() {

    }

    @Override
    public void getDetailMovie(String id) {
        homeRepositoryImpl.getMovieDetail(id, new HomeRepository.MovieDetailCallBack() {
            @Override
            public void onSuccess(MovieDetail response) {
                mView.getDetailMovie(response);
            }
        });
    }

    @Override
    public void getListMovie(String id, final String page){


        homeRepositoryImpl.getMovieListDetail(id, new HomeRepository.MovieListaCallBack() {
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
}
