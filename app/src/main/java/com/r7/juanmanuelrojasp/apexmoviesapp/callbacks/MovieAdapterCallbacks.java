package com.r7.juanmanuelrojasp.apexmoviesapp.callbacks;

import com.r7.juanmanuelrojasp.apexmoviesapp.model.Movie;

import java.util.ArrayList;

/**
 * Created by Juan Manuel Rojas P on 05/11/2017.
 */

public interface MovieAdapterCallbacks {
    void onMoviesCompleted(ArrayList<Movie> movies);
}
