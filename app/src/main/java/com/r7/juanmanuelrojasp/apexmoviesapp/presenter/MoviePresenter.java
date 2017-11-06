package com.r7.juanmanuelrojasp.apexmoviesapp.presenter;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.r7.juanmanuelrojasp.apexmoviesapp.R;
import com.r7.juanmanuelrojasp.apexmoviesapp.adapter.MovieAdapter;
import com.r7.juanmanuelrojasp.apexmoviesapp.callbacks.MovieAdapterCallbacks;
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

    private MovieService service;
    private RelativeLayout layout;
    private MovieView view;
    private static MoviePresenter presenter = new MoviePresenter();
    private MovieCallback listener;
    private MovieAdapterCallbacks listener2;

    private MoviePresenter() {}

    public static MoviePresenter getInstance(){
        return presenter;
    }

    public void setView(MovieView view){
        this.view = view;
    }

    public void setListener(){
        this.listener = this;
    }

    public void setListener(MovieAdapterCallbacks listener){
        this.listener2 = listener;
    }

    public void init(){

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
                ApexMoviesPreferences preferences = new ApexMoviesPreferences(view_.getContext());
                preferences.putStringPreferences(view_.getContext().getString(R.string.preference_name),
                        view_.getContext().getString(R.string.preference_movie), movie);
                preferences.putIntPreferences(view_.getContext().getString(R.string.preference_name),
                        view_.getContext().getString(R.string.preference_page), 1);
                preferences.putIntPreferences(view_.getContext().getString(R.string.preference_name),
                        view_.getContext().getString(R.string.preference_total_pages), 1);

                // the presenter calls to the service that has as main objetive
                // the consumption of the REST API related to movies
                service = new MovieService(view_.getContext(), listener);
                // this method perform the action requiered
                service.getListMovies(view_.getContext(), 0);
            }
        });
    }


    public void getListMovies(Context context, ArrayList<Movie> movies, int code){
        // the presenter calls to the service that has as main objetive
        // the consumption of the REST API related to movies
        service = new MovieService(context, listener);
        service.setMoviesInstance(movies);
        // this method perform the action requiered
        service.getListMovies(context, code);
    }

    @Override
    public void onMoviesReturned(ArrayList<Movie> movies, int code) {
        switch (code) {
            case 0:
                MovieAdapter adapter = new MovieAdapter(view.getContext(), movies);
            view.setLoadedMovies(adapter);
            break;
            case 1:
                listener2.onMoviesCompleted(movies);
        }
    }

    @Override
    public void onErrorResponse(String message) {
        view.notifyResult(message);
    }
}
