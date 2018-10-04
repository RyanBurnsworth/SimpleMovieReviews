package com.apps4geeks.simplemoviereviews.app;

import android.app.Application;

import com.apps4geeks.simplemoviereviews.di.ContextModule;
import com.apps4geeks.simplemoviereviews.di.DaggerMovieAdapterComponent;
import com.apps4geeks.simplemoviereviews.di.DaggerMovieRepoComponent;
import com.apps4geeks.simplemoviereviews.di.MovieAdapterComponent;
import com.apps4geeks.simplemoviereviews.di.MovieRepoComponent;

public class App extends Application {
    private static MovieRepoComponent repoComponent;
    private static MovieAdapterComponent movieAdapterComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        repoComponent = DaggerMovieRepoComponent.builder().build();

        movieAdapterComponent = DaggerMovieAdapterComponent.builder()
                .contextModule(new ContextModule(getApplicationContext()))
                .build();
    }

    public static MovieRepoComponent getComponent() {
        return repoComponent;
    }

    public static MovieAdapterComponent getMovieAdapterComponent() {
        return movieAdapterComponent;
    }
}
