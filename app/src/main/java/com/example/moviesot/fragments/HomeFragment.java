package com.example.moviesot.fragments;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.moviesot.R;
import com.example.moviesot.adapter.ListMovieHomeAdapter;
import com.example.moviesot.model.Movie;
import com.example.moviesot.utils.MovieManager;
import com.example.moviesot.viewModel.RoomViewModel;

import java.util.List;

public class HomeFragment extends Fragment implements MovieManager.OnDeleteMovie {
    TextView myAwesomeTextView;
    String t;
    ImageButton navigationButton;

    TextView titleActionBar;
    String text = "<font color=#FFFFFF>MOVIES</font><font color=#FFFFFF><b>OT</b></font>";

    private LinearLayoutCompat logoutButton;
    private TextView emailView;
    private TextView userView;
    private ListMovieHomeAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView mRecyclerView;
    private RoomViewModel roomViewModel;
    private PagerAdapter adapter;
    private ViewPager viewPager;
    private ImageButton favoriteButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        roomViewModel = ViewModelProviders.of(this).get(RoomViewModel.class);

        Toolbar myToolbar = v.findViewById(R.id.my_toolbar);
        titleActionBar = (TextView) v.findViewById(R.id.titleActionBar);


        titleActionBar.setText(Html.fromHtml(text));


        mAdapter = new ListMovieHomeAdapter(v.getContext());
        mRecyclerView = v.findViewById(R.id.recyclerview_home);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        roomViewModel.getAllMovie().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable final List<Movie> movieList) {
                // Update the cached copy of the words in the adapter.
                if(movieList==null){

                }
                mAdapter.setMovieList(movieList);

            }
        });
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setDeleteMovie(this);
        mRecyclerView.setAdapter(mAdapter);

        return v;

    }

    @Override
    public void deleteMovie(String id) {
        roomViewModel.delete(id);
    }
}
