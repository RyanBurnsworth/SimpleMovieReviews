package com.apps4geeks.simplemoviereviews.di;

import com.apps4geeks.simplemoviereviews.view.MovieAdapter;

import dagger.Component;

@Component(modules = MovieAdapterModule.class)
public interface MovieAdapterComponent {
    MovieAdapter getMovieAdapter();
}
