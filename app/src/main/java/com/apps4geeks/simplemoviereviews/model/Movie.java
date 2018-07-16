package com.apps4geeks.simplemoviereviews.model;

import com.apps4geeks.simplemoviereviews.util.Constants;
import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("original_title")
    private String movieTitle;
    @SerializedName("poster_path")
    private String imagePath;
    @SerializedName("overview")
    private String summary;
    @SerializedName("vote_average")
    private double avgRating;

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getImagePath() {
        return Constants.BASE_IMAGE_URL + imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }
}
