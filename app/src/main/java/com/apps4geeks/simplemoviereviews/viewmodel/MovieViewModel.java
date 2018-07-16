package com.apps4geeks.simplemoviereviews.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.apps4geeks.simplemoviereviews.model.Movie;
import com.apps4geeks.simplemoviereviews.model.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {
    private MutableLiveData<List<Movie>> liveMovieList;

    public MovieViewModel() {
        liveMovieList = MovieRepository.getInstance().getMovies();
    }

    public LiveData<List<Movie>> getLiveMovieData() {
        return liveMovieList;
    }
}
