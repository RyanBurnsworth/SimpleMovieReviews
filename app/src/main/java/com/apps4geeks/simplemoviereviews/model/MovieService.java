package com.apps4geeks.simplemoviereviews.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieService {

    @GET("3/discover/movie")
    Call<MovieList> getMovies();
}
