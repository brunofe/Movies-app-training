package com.example.moviesot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.moviesot.R;
import com.example.moviesot.model.ListMovie;
import com.example.moviesot.model.Movie;
import com.example.moviesot.model.MovieDetail;
import com.example.moviesot.utils.MovieManager;
import com.squareup.picasso.Picasso;

public class ListMovieAdapterDetail extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int ITEMDETAIL = 0;
    private final int ITEM = 1;
    private final int LOADING = 2;
    private ListMovie movieList;
    private MovieDetail movieDetail;
    private MovieManager.OnOpenDetailMovie openDetailMovie;
    private LayoutInflater inflater;
    private Context context;
    private boolean isLoadingAdded = false;

    public ListMovieAdapterDetail(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setOpenDetailMovie(MovieManager.OnOpenDetailMovie openDetailMovie){

        this.openDetailMovie = openDetailMovie;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView;
        RecyclerView.ViewHolder viewHolder = null;

        switch (i) {
            case ITEMDETAIL:
                View viewItemDetail = inflater.inflate(R.layout.item_movie_detail, viewGroup, false);
                viewHolder = new ItemDetailViewHolderDetail(viewItemDetail, this);
                break;
            case ITEM:
                View viewItem = inflater.inflate(R.layout.movie_item_list, viewGroup, false);
                viewHolder = new MovieViewHolderDetail(viewItem, this);
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        switch (getItemViewType(position)) {
            case ITEMDETAIL:
                final ItemDetailViewHolderDetail itemDetailViewHolderDetail = (ItemDetailViewHolderDetail) viewHolder;
                String urlPoster = String.format("http://image.tmdb.org/t/p/w185//%s", movieDetail.getPosterId());
                String urldetail = String.format("http://image.tmdb.org/t/p/w185//%s", movieDetail.getBannerId());
                itemDetailViewHolderDetail.descriptionDetail.setText(movieDetail.getOverview());
                Picasso.get().load(urldetail).fit().centerCrop().into(itemDetailViewHolderDetail.bannerMovieDetail);
                Picasso.get().load(urlPoster).into(itemDetailViewHolderDetail.posterMovieDetail);
                itemDetailViewHolderDetail.titleMovieDetail.setText(movieDetail.getTitle());
                itemDetailViewHolderDetail.yearMovieDetail.setText(movieDetail.getYear());
                break;

            case ITEM:
                final MovieViewHolderDetail movieViewHolder = (MovieViewHolderDetail) viewHolder;

                String url = String.format("http://image.tmdb.org/t/p/w185//%s", movieList.getMovie().get(position).getPoster_path());

                movieViewHolder.movieTitle.setText(movieList.getMovie().get(position).getTitle());
                movieViewHolder.movieYear.setText(movieList.getMovie().get(position).getRelease_date());
                Picasso.get().load(url).into(movieViewHolder.imageMovie);
                movieViewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDetailMovie.openMovieDetail(movieList.getMovie().get(position).getId());
                    }
                });

                break;
        }
    }

    public void setMovieList(ListMovie listMovie) {
        this.movieList = listMovie;
        notifyDataSetChanged();

    }

    public void setMovieDetail(MovieDetail movieDetail){

        this.movieDetail = movieDetail;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (movieList == null) {
            return 0;
        }
        return movieList.getMovie().size() ;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0) return ITEMDETAIL;
        return (position == movieList.getMovie().size() - 1 && isLoadingAdded) ? LOADING : ITEM;

    }

    class MovieViewHolderDetail extends RecyclerView.ViewHolder {

        final ListMovieAdapterDetail mAdapter;
        private final ImageView imageMovie;
        private final TextView movieTitle, movieYear;
        private final View view;

        public MovieViewHolderDetail(@NonNull View itemView, ListMovieAdapterDetail adapter) {
            super(itemView);

            this.mAdapter = adapter;
            view = itemView;
            movieTitle = itemView.findViewById(R.id.movie_title);
            movieYear = itemView.findViewById(R.id.year_movie);
            imageMovie = itemView.findViewById(R.id.imagemMovie);

        }
    }


    protected class ItemDetailViewHolderDetail extends RecyclerView.ViewHolder {
        private ImageView bannerMovieDetail, posterMovieDetail;
        private CardView cardViewDetail;
        private TextView descriptionDetail, titleMovieDetail, genresMovieDetail, avarageMovieDetail,yearMovieDetail,durationMovieDetail;

        public ItemDetailViewHolderDetail(View itemView, ListMovieAdapterDetail adapter) {
            super(itemView);

            bannerMovieDetail = itemView.findViewById(R.id.banner_movie_detail);
            posterMovieDetail = itemView.findViewById(R.id.poster_movie_detail);
            descriptionDetail = itemView.findViewById(R.id.description_detail);
            titleMovieDetail =  itemView.findViewById(R.id.title_movie_detail);
            avarageMovieDetail = itemView.findViewById(R.id.avarage_detail);
            yearMovieDetail = itemView.findViewById(R.id.year_detail);
            durationMovieDetail = itemView.findViewById(R.id.duration_detail);

        }
    }

    public Movie getItem(int position) {
        return movieList.getMovie().get(position);
    }

    public void add(Movie movie) {
        movieList.getMovie().add(movie);
        notifyItemInserted(movieList.getMovie().size() - 1);
    }

    public void addAll(ListMovie listMovie) {

        for (Movie movie : listMovie.getMovie()) {
            add(movie);
        }
    }
}
