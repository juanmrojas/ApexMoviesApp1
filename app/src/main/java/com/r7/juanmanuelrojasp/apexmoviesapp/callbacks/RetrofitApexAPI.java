package com.r7.juanmanuelrojasp.apexmoviesapp.callbacks;

import com.google.gson.JsonObject;
import com.r7.juanmanuelrojasp.apexmoviesapp.model.MoviesRetrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Juan Manuel Rojas P on 03/11/2017.
 */

public interface RetrofitApexAPI {

    @GET("3/search/movie?")
    Observable<MoviesRetrofit> getMoviesList(@Query("api_key") String apiKey, @Query("language") String language, @Query("query") String query,
                                             @Query("page") int page, @Query("include_adult") boolean adult);

}
