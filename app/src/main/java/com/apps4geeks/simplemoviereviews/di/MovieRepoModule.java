package com.apps4geeks.simplemoviereviews.di;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.apps4geeks.simplemoviereviews.model.Movie;
import com.apps4geeks.simplemoviereviews.model.MovieList;
import com.apps4geeks.simplemoviereviews.model.MovieService;
import com.apps4geeks.simplemoviereviews.util.Constants;

import java.io.IOException;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = MutableLiveDataModule.class)
public class MovieRepoModule {

    @Provides
    public MovieService getMovieService(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }

    @Provides
    public Retrofit getRetrofit(OkHttpClient.Builder httpBuilder) {
        httpBuilder = new OkHttpClient.Builder();
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

        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(httpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public OkHttpClient.Builder getHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Provides
    public LiveData<List<Movie>> getMovies(MovieService movieService, final MutableLiveData<List<Movie>> liveMovieList) {
        try {
            movieService.getMovies().enqueue(new Callback<MovieList>() {
                @Override
                public void onResponse(Call<MovieList> call, retrofit2.Response<MovieList> response) {
                    MovieList movieList1 = response.body();
                    liveMovieList.setValue(movieList1.getMovieList());
                }

                @Override
                public void onFailure(Call<MovieList> call, Throwable t) {
                    Log.e("TAG", t.getLocalizedMessage());
                }
            });

        } catch (NullPointerException e) {
            Log.e("TAG", "NullPointer Exception");
        }

        return liveMovieList;
    }
}
