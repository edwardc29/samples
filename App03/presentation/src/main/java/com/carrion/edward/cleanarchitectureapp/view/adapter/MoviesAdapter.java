package com.carrion.edward.cleanarchitectureapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carrion.edward.cleanarchitectureapp.R;
import com.carrion.edward.cleanarchitectureapp.model.MovieModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private List<MovieModel> movieList;

    @Inject
    MoviesAdapter(Context context) {
        this.movieList = new ArrayList<>();
    }

    @Override public int getItemCount() {
        return this.movieList != null ? this.movieList.size() : 0;
    }

    @Override public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override public void onBindViewHolder(MovieViewHolder holder, final int position) {
        final MovieModel movieModel = this.movieList.get(position);
        holder.textViewTitle.setText(movieModel.getTitle());
    }

    @Override public long getItemId(int position) {
        return position;
    }

    public void setMoviesList(List<MovieModel> movieList) {
        this.movieList = movieList;
        this.notifyDataSetChanged();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView textViewTitle;

        MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
