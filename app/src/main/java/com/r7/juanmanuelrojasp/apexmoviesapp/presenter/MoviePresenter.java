package com.r7.juanmanuelrojasp.apexmoviesapp.presenter;

import android.view.View;
import android.widget.RelativeLayout;

import com.r7.juanmanuelrojasp.apexmoviesapp.R;
import com.r7.juanmanuelrojasp.apexmoviesapp.adapter.MovieAdapter;
import com.r7.juanmanuelrojasp.apexmoviesapp.model.Movie;
import com.r7.juanmanuelrojasp.apexmoviesapp.service.MovieService;
import com.r7.juanmanuelrojasp.apexmoviesapp.callbacks.MovieCallback;
import com.r7.juanmanuelrojasp.apexmoviesapp.callbacks.MovieView;
import com.r7.juanmanuelrojasp.apexmoviesapp.utils.ApexMoviesPreferences;

import java.util.ArrayList;

/**
 * Created by Juan Manuel Rojas P on 03/11/2017.
 */

public class MoviePresenter implements MovieCallback {

    private MovieView view;
    private MovieService service;
    private MovieCallback listener;
    private ApexMoviesPreferences preferences;
    private RelativeLayout layout;

    public MoviePresenter(final MovieView view)
    {
        this.view = view;
        listener = this;
        preferences = new ApexMoviesPreferences(view.getContext());

        layout = view.getSearchLayout();

        view.getSearchButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if the user press the Search button, the layout that contains
                // both: edit field and submit button become visible
                layout.setVisibility(View.VISIBLE);
            }
        });

        view.getSubmitButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view_) {
                final String movie = view.getMovie();
                // the presenter calls to the service that has as main objetive
                // the consumption of the REST API related to movies
                service = new MovieService(view_.getContext(), listener);
                // this method perform the action requiered
                service.getListMovies(view_.getContext(), movie, 0);
            }
        });
    }

    @Override
    public void onMoviesReturned(ArrayList<Movie> movies) {
        MovieAdapter adapter = new MovieAdapter(view.getContext(), movies);
        view.setLoadedMovies(adapter);
    }

    @Override
    public void onMoviesCompleted(ArrayList<Movie> movies) {

    }

    @Override
    public void onErrorResponse(String message) {
        view.notifyResult(message);
    }
}
