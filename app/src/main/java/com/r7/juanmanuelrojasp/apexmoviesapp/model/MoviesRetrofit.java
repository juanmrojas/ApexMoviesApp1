package com.r7.juanmanuelrojasp.apexmoviesapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Juan Manuel Rojas P on 03/11/2017.
 */

public class MoviesRetrofit implements Parcelable {

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @SerializedName("total_pages")
    @Expose
    private int pages;
    @SerializedName("results")
    @Expose
    private ArrayList<Movie> movies;

    protected MoviesRetrofit(Parcel in) {
        pages = in.readInt();
        movies = in.createTypedArrayList(Movie.CREATOR);
    }

    public static final Creator<MoviesRetrofit> CREATOR = new Creator<MoviesRetrofit>() {
        @Override
        public MoviesRetrofit createFromParcel(Parcel in) {
            return new MoviesRetrofit(in);
        }

        @Override
        public MoviesRetrofit[] newArray(int size) {
            return new MoviesRetrofit[size];
        }
    };

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(pages);
        parcel.writeTypedList(movies);
    }
}

