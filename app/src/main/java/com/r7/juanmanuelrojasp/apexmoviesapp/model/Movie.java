package com.r7.juanmanuelrojasp.apexmoviesapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Juan Manuel Rojas P on 03/11/2017.
 */

public class Movie implements Parcelable {

    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("vote_count")
    @Expose
    int votes;
    @SerializedName("popularity")
    @Expose
    double popularity;
    @SerializedName("vote_average")
    @Expose
    double voteAverage;
    @SerializedName("adult")
    @Expose
    boolean adult;
    @SerializedName("original_title")
    @Expose
    String largeTitle;
    @SerializedName("original_language")
    @Expose
    String language;
    @SerializedName("poster_path")
    @Expose
    String posterPath;
    @SerializedName("overview")
    @Expose
    String overview;
    @SerializedName("release_date")
    @Expose
    String dateRelease;
    @SerializedName("title")
    @Expose
    String shortTitle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getLargeTitle() {
        return largeTitle;
    }

    public void setLargeTitle(String largeTitle) {
        this.largeTitle = largeTitle;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(String dateRelease) {
        this.dateRelease = dateRelease;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public static Creator<Movie> getCREATOR() {
        return CREATOR;
    }

    protected Movie(Parcel in) {
        id = in.readInt();
        votes = in.readInt();
        popularity = in.readDouble();
        voteAverage = in.readDouble();
        //adult = in.readByte() != 0;
        largeTitle = in.readString();
        language = in.readString();
        posterPath = in.readString();
        overview = in.readString();
        dateRelease = in.readString();
        shortTitle = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(id);
        parcel.writeInt(votes);
        parcel.writeDouble(popularity);
        parcel.writeDouble(voteAverage);
        //parcel.writeValue(adult);
        parcel.writeString(largeTitle);
        parcel.writeString(language);
        parcel.writeString(posterPath);
        parcel.writeString(overview);
        parcel.writeString(dateRelease);
        parcel.writeString(shortTitle);
    }
}
