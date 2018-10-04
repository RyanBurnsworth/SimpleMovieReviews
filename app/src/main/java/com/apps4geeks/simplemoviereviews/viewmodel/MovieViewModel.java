package com.apps4geeks.simplemoviereviews.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.apps4geeks.simplemoviereviews.app.App;
import com.apps4geeks.simplemoviereviews.model.Movie;

import java.util.List;

import javax.inject.Inject;

public class MovieViewModel extends ViewModel {
    private LiveData<List<Movie>> liveMovieList;

    @Inject
    public MovieViewModel() {
        this.liveMovieList = App.getComponent().getMovieList();
    }

    public LiveData<List<Movie>> getLiveMovieData() {
        return liveMovieList;
    }
}