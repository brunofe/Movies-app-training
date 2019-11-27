package com.example.moviesot.RoomDB;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.moviesot.model.Movie;

@Database(entities = {Movie.class}, version = 1)

public abstract class FavoriteMovieDataBase extends RoomDatabase {

    public abstract MovieDAO movieDAO();

    private static volatile FavoriteMovieDataBase INSTANCE;

    public static FavoriteMovieDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FavoriteMovieDataBase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FavoriteMovieDataBase.class, "Movie_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final MovieDAO mDao;

        PopulateDbAsync(FavoriteMovieDataBase db) {
            mDao =  db.movieDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            return null;
        }
    }
}
