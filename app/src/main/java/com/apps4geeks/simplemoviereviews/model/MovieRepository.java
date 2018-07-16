package com.apps4geeks.simplemoviereviews.model;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.apps4geeks.simplemoviereviews.util.Constants;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {
    private final static String TAG = "MovieRepo";

    private static MovieRepository movieRepo;
    private MovieService movieService;

    private MovieRepository() {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        httpBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalUrl = original.url();

                HttpUrl url = originalUrl.newBuilder()
                        .addQueryParameter("api_key", Constants.API_KEY)
                        .addQueryParameter("with_genre", String.valueOf(27))
                        .build();

                Request request = original
                        .newBuilder()
                        .url(url)
                        .build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(httpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieService = retrofit.create(MovieService.class);
    }

    public synchronized static MovieRepository getInstance() {
        if (movieRepo == null)
            movieRepo = new MovieRepository();

        return movieRepo;
    }

    public MutableLiveData<List<Movie>> getMovies() {
        final MutableLiveData<List<Movie>> liveMovieList = new MutableLiveData<>();

        try {
            movieService.getMovies().enqueue(new Callback<MovieList>() {
                @Override
                public void onResponse(Call<MovieList> call, retrofit2.Response<MovieList> response) {
                    MovieList movieList1 = response.body();
                    liveMovieList.setValue(movieList1.getMovieList());
                }

                @Override
                public void onFailure(Call<MovieList> call, Throwable t) {
                    Log.e(TAG, t.getLocalizedMessage());
                }
            });

        } catch (NullPointerException e) {
            Log.e(TAG, "NullPointer Exception");
        }

        return liveMovieList;
    }
}
