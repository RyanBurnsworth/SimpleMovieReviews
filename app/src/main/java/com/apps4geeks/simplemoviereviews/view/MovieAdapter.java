package com.apps4geeks.simplemoviereviews.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps4geeks.simplemoviereviews.R;
import com.apps4geeks.simplemoviereviews.model.Movie;
import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movieList;
    private Context mContext;

    public MovieAdapter(List<Movie> movieList, Context mContext) {
        this.movieList = movieList;
        this.mContext = mContext;
    }

    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapter.MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.movieRating.setText(String.valueOf(movie.getAvgRating()));
        holder.movieSummary.setText(movie.getSummary());
        holder.movieTitle.setText(movie.getMovieTitle());
        Glide.with(mContext).load(movie.getImagePath()).into(holder.posterImage);
    }

    public void addItems(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView posterImage;
        private TextView movieTitle;
        private TextView movieSummary;
        private TextView movieRating;

        MovieViewHolder(View view) {
            super(view);
            posterImage = view.findViewById(R.id.posterImage);
            movieTitle = view.findViewById(R.id.movieTitle);
            movieSummary = view.findViewById(R.id.movieSummary);
            movieRating = view.findViewById(R.id.movieRating);
        }
    }
}
