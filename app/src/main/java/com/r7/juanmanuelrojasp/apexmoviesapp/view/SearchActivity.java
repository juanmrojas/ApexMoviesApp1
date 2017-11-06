package com.r7.juanmanuelrojasp.apexmoviesapp.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.r7.juanmanuelrojasp.apexmoviesapp.R;
import com.r7.juanmanuelrojasp.apexmoviesapp.adapter.MovieAdapter;
import com.r7.juanmanuelrojasp.apexmoviesapp.callbacks.MovieView;
import com.r7.juanmanuelrojasp.apexmoviesapp.presenter.MoviePresenter;
import com.r7.juanmanuelrojasp.apexmoviesapp.utils.ApexMoviesPreferences;

public class SearchActivity extends AppCompatActivity implements MovieView {

    private RecyclerView recycler;
    private Button btnSearch, btnSubmit;
    private EditText edtMovie;
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btnSearch = (Button)findViewById(R.id.btnSearchMovie);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        edtMovie = (EditText)findViewById(R.id.edtSearchMovie);
        recycler = (RecyclerView)findViewById(R.id.recyclerMovies);
        layout = (RelativeLayout)findViewById(R.id.search_container);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    protected void onStart() {
        super.onStart();
        // the layout that contains both views is not visible.
        layout.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // here the SearchPresenter is initialized
        MoviePresenter presenter = MoviePresenter.getInstance();
        presenter.setView(this);
        presenter.setListener();
        presenter.init();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // case no valid because the system kept up the movies in memory
        ApexMoviesPreferences preferences = new ApexMoviesPreferences(this);
        preferences.putStringPreferences(this.getString(R.string.preference_name),
                this.getString(R.string.preference_movie), "");
        preferences.putIntPreferences(this.getString(R.string.preference_name),
                this.getString(R.string.preference_page), 1);
        preferences.putIntPreferences(this.getString(R.string.preference_name),
                this.getString(R.string.preference_total_pages), 0);
    }

    @Override
    public void setLoadedMovies(MovieAdapter adapter) {
        recycler.setAdapter(adapter);
    }

    @Override
    public void notifyResult(String message) {

    }

    @Override
    public Button getSubmitButton() {
        return btnSubmit;
    }

    @Override
    public Button getSearchButton() {
        return btnSearch;
    }

    @Override
    public String getMovie() {
        return edtMovie.getText().toString();
    }

    @Override
    public RelativeLayout getSearchLayout() {
        return layout;
    }

    @Override
    public Context getContext() {
        return null;
    }
}
