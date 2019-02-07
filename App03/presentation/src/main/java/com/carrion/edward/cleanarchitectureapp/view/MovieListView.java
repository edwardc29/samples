package com.carrion.edward.cleanarchitectureapp.view;

import com.carrion.edward.cleanarchitectureapp.model.MovieModel;

import java.util.List;

public interface MovieListView extends LoadDataView {
    void renderMovieList(List<MovieModel> movieModelCollection);
}