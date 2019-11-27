package com.example.moviesot.RoomDB;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.moviesot.model.Movie;

import java.util.List;

@Dao
public interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie movie);

    @Query("SELECT * from movie_table ORDER BY vote_average ASC")
    LiveData<List<Movie>> getAllCompromissos();

    @Query("DELETE FROM movie_table WHERE id = :idMovie")
    void deleteMovie(String idMovie);
}
