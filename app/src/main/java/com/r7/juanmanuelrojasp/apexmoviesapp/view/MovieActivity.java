package com.r7.juanmanuelrojasp.apexmoviesapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.r7.juanmanuelrojasp.apexmoviesapp.R;
import com.r7.juanmanuelrojasp.apexmoviesapp.model.Movie;

public class MovieActivity extends AppCompatActivity {

    TextView txvMovieTitle, txvMovieOverview,
            txvMovieRelease, txvMovieAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        txvMovieTitle = (TextView)findViewById(R.id.txvMovieTitle);
        txvMovieOverview = (TextView)findViewById(R.id.txvMovieOverview);
        txvMovieRelease = (TextView)findViewById(R.id.txvMovieRelease);
        txvMovieAverage = (TextView)findViewById(R.id.txvMovieAverage);

        Movie movie = getIntent().getParcelableExtra("movie");
        if(movie != null){
            txvMovieTitle.setText(movie.getLargeTitle());
            txvMovieOverview.setText(movie.getOverview());
            txvMovieRelease.setText(movie.getDateRelease());
            txvMovieAverage.setText("" + movie.getVoteAverage());
        }
    }

}
