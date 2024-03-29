package com.example.moviesot.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.example.moviesot.R;
import com.example.moviesot.model.Movie;
import com.example.moviesot.utils.MovieManager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListMovieHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<Movie> movieList;
    private MovieManager.OnDeleteMovie deleteMovie;
    private LayoutInflater inflater;
    private Context context;
    private boolean isLoadingAdded = false;
    String url;

    private final int ITEM = 0;
    private final int LOADING = 1;

    public ListMovieHomeAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setDeleteMovie(MovieManager.OnDeleteMovie onDeleteMovie){
        this.deleteMovie = onDeleteMovie;

    }

    public void setMovieList(List<Movie> listMovie){
        this.movieList = listMovie;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView;
        RecyclerView.ViewHolder viewHolder = null;

        switch (i) {
            case ITEM:
                View viewItem = inflater.inflate(R.layout.movie_item_list, viewGroup, false);
                viewHolder = new ListMovieHomeAdapter.MovieViewHomeHolder(viewItem,this);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.item_loading, viewGroup, false);
                viewHolder = new ListMovieHomeAdapter.LoadingViewHomeHolder(viewLoading,this);
                break;

        }
        return viewHolder;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {

        switch (getItemViewType(position)) {

            case ITEM:
                final ListMovieHomeAdapter.MovieViewHomeHolder movieViewHolder = (ListMovieHomeAdapter.MovieViewHomeHolder) viewHolder;

                url = String.format("http://image.tmdb.org/t/p/w185//%s", movieList.get(position).getPoster_path());

                movieViewHolder.movieTitle.setText(movieList.get(position).getTitle());
                movieViewHolder.movieOverView.setText(movieList.get(position).getOverview());
                movieViewHolder.movieYear.setText(movieList.get(position).getRelease_date());
                movieViewHolder.movieVoteAverage.setText(movieList.get(position).getVote_average());
                Picasso.get().load(url).into(movieViewHolder.imageMovie);

                movieViewHolder.imageFavorite.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        deleteMovie.deleteMovie(movieList.get(position).getId());
                    }
                });

                break;

            case LOADING:
                ListMovieHomeAdapter.LoadingViewHomeHolder loadingVH = (ListMovieHomeAdapter.LoadingViewHomeHolder) viewHolder;
                loadingVH.mProgressBar.setVisibility(View.VISIBLE);
                break;
        }

    }

    @Override
    public int getItemCount() {
        if(movieList==null){
            return 0;
        }
        return movieList.size();
    }

    class MovieViewHomeHolder extends RecyclerView.ViewHolder {
        final ListMovieHomeAdapter mAdapter;
        private final ImageView imageMovie,imageFavorite;
        private final TextView movieTitle, movieYear,  movieVoteAverage, movieOverView;
        private final View view;


        public MovieViewHomeHolder(@NonNull View itemView, ListMovieHomeAdapter adapter) {
            super(itemView);

            this.mAdapter = adapter;
            view = itemView;
            movieTitle = itemView.findViewById(R.id.movie_title);
            movieYear = itemView.findViewById(R.id.year_movie);
            movieOverView = itemView.findViewById(R.id.description_movie);
            imageMovie = itemView.findViewById(R.id.imagemMovie);
            movieVoteAverage = itemView.findViewById(R.id.note_movie);
            imageFavorite = itemView.findViewById(R.id.favorite_movie);

        }
    }

    @Override
    public int getItemViewType(int position) {
        return (position == movieList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;

    }

    protected class LoadingViewHomeHolder extends RecyclerView.ViewHolder {
        private ProgressBar mProgressBar;

        public LoadingViewHomeHolder(View itemView,ListMovieHomeAdapter adapter) {
            super(itemView);
            mProgressBar = itemView.findViewById(R.id.progress_bar_loading);

        }
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Movie());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = movieList.size() - 1;
        Movie movie = getItem(position);

        if (movie != null) {
            movieList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Movie getItem(int position) {
        return movieList.get(position);
    }


    public void add(Movie movie) {
        movieList.add(movie);
        notifyItemInserted(movieList.size() - 1);
    }

}
