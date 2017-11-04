package com.r7.juanmanuelrojasp.apexmoviesapp.callbacks;

import com.r7.juanmanuelrojasp.apexmoviesapp.model.Movie;

import java.util.ArrayList;

/**
 * Created by Juan Manuel Rojas P on 03/11/2017.
 */

public interface MovieCallback {
    void onMoviesReturned(ArrayList<Movie> movies);
    void onMoviesCompleted(ArrayList<Movie> movies);
    void onErrorResponse(String message);
}
