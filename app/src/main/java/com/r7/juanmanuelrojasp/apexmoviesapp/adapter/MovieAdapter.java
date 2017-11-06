package com.r7.juanmanuelrojasp.apexmoviesapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.r7.juanmanuelrojasp.apexmoviesapp.R;
import com.r7.juanmanuelrojasp.apexmoviesapp.callbacks.MovieAdapterCallbacks;
import com.r7.juanmanuelrojasp.apexmoviesapp.model.Movie;
import com.r7.juanmanuelrojasp.apexmoviesapp.presenter.MoviePresenter;
import com.r7.juanmanuelrojasp.apexmoviesapp.utils.ApexMoviesPreferences;
import com.r7.juanmanuelrojasp.apexmoviesapp.view.MovieActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by Juan Manuel Rojas P on 11/08/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>
        implements MovieAdapterCallbacks{

    private ArrayList<Movie> movies;
    private Context context;
    private ApexMoviesPreferences preferences;

    public MovieAdapter(Context context, ArrayList<Movie> movies){
        this.context = context;
        this.movies = movies;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.template_movie, parent, false);
        return new MovieAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        /*
        * the adapter shows an amount specific of items each time
        * that the user do scroll;
        * */
        final Movie movie = movies.get(position);
        holder.txtMovieTitle.setText(movie.getLargeTitle());
        holder.imvMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MovieActivity.class);
                intent.putExtra("movie", movie);
                view.getContext().startActivity(intent);
            }
        });
        Picasso.with(holder.context)
                .load(holder.context.getString(R.string.poster_api_url) + movie.getPosterPath())
                .placeholder(R.drawable.no_poster)
                .into(holder.imvMovie);
        preferences = new ApexMoviesPreferences(holder.context);

        /* that's why I implemented this code fragment. It validate if the
         last position is equals to the last item of ArrayList.
          */
        if(position == movies.size() - 1){

            int page = preferences.getIntPreferences(holder.context.getResources().getString(R.string.preference_name),
                    holder.context.getString(R.string.preference_page), 1);

            int totalPages = preferences.getIntPreferences(holder.context.getResources().getString(R.string.preference_name),
                    holder.context.getString(R.string.preference_total_pages), 1);

            // this validation serves to control if there are more movies to add;
            // when page is greater than total pages, the request is not performed
            if(page <= totalPages) {

                MoviePresenter presenter = MoviePresenter.getInstance();
                presenter.setListener(this);
                presenter.getListMovies(holder.context,
                        movies, 1);
            }
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public void onMoviesCompleted(ArrayList<Movie> movies) {
        // this line re creates the adapter and notify to the recycler view
        // that new movies where added; it is not necessary instantiate the MovieAdapter again
        notifyDataSetChanged();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{

        private TextView txtMovieTitle;
        private ImageView imvMovie;
        private CardView cardMovie;
        private Context context;

        private MovieViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            cardMovie = (CardView)itemView.findViewById(R.id.cardMovie);
            imvMovie = (ImageView)itemView.findViewById(R.id.viewMovieImage);
            txtMovieTitle = (TextView)itemView.findViewById(R.id.viewMovieName);
        }
    }
}
