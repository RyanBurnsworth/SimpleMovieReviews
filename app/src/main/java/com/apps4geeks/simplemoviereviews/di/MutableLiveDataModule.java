package com.apps4geeks.simplemoviereviews.di;

import android.arch.lifecycle.MutableLiveData;

import com.apps4geeks.simplemoviereviews.model.Movie;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class MutableLiveDataModule {
    @Provides
    public MutableLiveData<List<Movie>> getLiveMovieList() {
        return new MutableLiveData<>();
    }
}
