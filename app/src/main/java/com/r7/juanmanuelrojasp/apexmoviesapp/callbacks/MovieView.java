package com.r7.juanmanuelrojasp.apexmoviesapp.callbacks;

import android.content.Context;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.r7.juanmanuelrojasp.apexmoviesapp.adapter.MovieAdapter;
import com.r7.juanmanuelrojasp.apexmoviesapp.presenter.MoviePresenter;

/**
 * Created by Juan Manuel Rojas P on 03/11/2017.
 */

public interface MovieView {

    void setLoadedMovies(MovieAdapter adapter);
    void notifyResult(String message);
    Button getSubmitButton();
    Button getSearchButton();
    String getMovie();
    RelativeLayout getSearchLayout();
    Context getContext();
}
