package com.apps4geeks.simplemoviereviews.di;

import android.arch.lifecycle.LiveData;

import com.apps4geeks.simplemoviereviews.model.Movie;

import java.util.List;

import dagger.Component;

@Component(modules = MovieRepoModule.class)
public interface MovieRepoComponent {
    LiveData<List<Movie>> getMovieList();
}
