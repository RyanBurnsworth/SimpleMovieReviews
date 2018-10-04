package com.apps4geeks.simplemoviereviews.di;

import android.content.Context;

import com.apps4geeks.simplemoviereviews.app.App;
import com.apps4geeks.simplemoviereviews.model.Movie;
import com.apps4geeks.simplemoviereviews.view.MovieAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class MovieAdapterModule {

    @Provides
    public MovieAdapter getMovieAdapter(ArrayList<Movie> movieArrayList, Context context) {
        return new MovieAdapter(movieArrayList, context);
    }

    @Provides
    public ArrayList<Movie> getMovieArrayList() {
        return new ArrayList<Movie>();
    }
}
