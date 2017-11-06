package com.r7.juanmanuelrojasp.apexmoviesapp.service;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.r7.juanmanuelrojasp.apexmoviesapp.R;
import com.r7.juanmanuelrojasp.apexmoviesapp.model.Movie;
import com.r7.juanmanuelrojasp.apexmoviesapp.model.MoviesRetrofit;
import com.r7.juanmanuelrojasp.apexmoviesapp.callbacks.MovieCallback;
import com.r7.juanmanuelrojasp.apexmoviesapp.callbacks.RetrofitApexAPI;
import com.r7.juanmanuelrojasp.apexmoviesapp.utils.ApexMoviesPreferences;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Juan Manuel Rojas P on 03/11/2017.
 */

public class MovieService {

    private ArrayList<Movie> movies;
    private MovieCallback listener;
    private RetrofitApexAPI service;
    private ApexMoviesPreferences preferences;
    private Context context;

    public MovieService(Context context, MovieCallback listener){
        this.listener = listener;
        movies = new ArrayList<Movie>();
        setRetrofitApiService(context);
        preferences = new ApexMoviesPreferences(context);
        this.context = context;
    }

    private void setRetrofitApiService(Context context) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getResources().getString(R.string.app_ws_movies_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(RetrofitApexAPI.class);
    }

    public void setMoviesInstance(ArrayList<Movie> movies){
        this.movies = movies;
    }

    public void getListMovies(Context context, int code){

        //api_key=503695767acad7d3a0bbd347e5293747&language=en-US&query=Avengers&page=1&include_adult=false

        // the Observable pattern was implemented with the intention of
        // perform operations in a secondary thread; it avoids that the UI thread being blocked
        // during the consumption of the end point

        int page = preferences.getIntPreferences(context.getResources().getString(R.string.preference_name),
                context.getString(R.string.preference_page), 1);

        String movie = preferences.getStringPreferences(context.getResources().getString(R.string.preference_name),
                context.getString(R.string.preference_movie), "");

        service.getMoviesList(context.getResources().getString(R.string.app_ws_api_key),"en-US",
                movie, page, false)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(handleResult(page, code));
    }

    private Observer<MoviesRetrofit> handleResult(final int page, final int code){
        return new Observer<MoviesRetrofit>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("TAG_Service", "onCompleted");
            }

            @Override
            public void onNext(MoviesRetrofit moviesRetrofit) {
                Log.d("TAG_Service", moviesRetrofit.getMovies().toString());
                // here we have two scenarios; when the app execute the end point for first time
                // and que the response to be assigned to an ArrayList. But, when is just necessary
                // add more items to the ArrayList, I used the addAll method.
                if(code == 0) {
                    movies = moviesRetrofit.getMovies();
                    int pages = moviesRetrofit.getPages();
                    preferences.putIntPreferences(
                            context.getString(R.string.preference_name),
                            context.getString(R.string.preference_total_pages),
                            pages);
                }
                else
                    movies.addAll(moviesRetrofit.getMovies());
                // stored these values because are relevant to control
                // the flow execution
                preferences.putIntPreferences(
                        context.getString(R.string.preference_name),
                        context.getString(R.string.preference_page),
                        preferences.getIntPreferences(context.getString(R.string.preference_name),
                                context.getString(R.string.preference_page), 1) + 1);

                listener.onMoviesReturned(movies, code);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("TAG_Service", "onError");
            }

            @Override
            public void onComplete() {

            }
        };

    }
}
