package com.carrion.edward.cleanarchitectureapp.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.carrion.edward.cleanarchitectureapp.R;
import com.carrion.edward.cleanarchitectureapp.di.HasComponent;
import com.carrion.edward.cleanarchitectureapp.di.components.DaggerMovieComponent;
import com.carrion.edward.cleanarchitectureapp.di.components.MovieComponent;
import com.carrion.edward.cleanarchitectureapp.model.MovieModel;
import com.carrion.edward.cleanarchitectureapp.presenter.MovieListPresenter;
import com.carrion.edward.cleanarchitectureapp.view.MovieListView;
import com.carrion.edward.cleanarchitectureapp.view.adapter.MoviesAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MovieLisActivity extends BaseActivity implements HasComponent<MovieComponent>, MovieListView {
    private MovieComponent movieComponent;

    @Inject
    MovieListPresenter movieListPresenter;
    @Inject
    MoviesAdapter moviesAdapter;

    @BindView(R.id.pb)
    ProgressBar progressBar;

    @BindView(R.id.rv_movies)
    RecyclerView moviesRecyclerView;

    Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        initializeInjector();
        movieComponent.inject(this);
        movieListPresenter.setView(this);
        movieListPresenter.initialize();

        moviesRecyclerView.setAdapter(moviesAdapter);
    }

    private void initializeInjector() {
        this.movieComponent = DaggerMovieComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public MovieComponent getComponent() {
        return movieComponent;
    }

    @Override
    public void renderMovieList(List<MovieModel> movieList) {
        moviesRecyclerView.setVisibility(View.VISIBLE);
        moviesAdapter.setMoviesList(movieList);
        moviesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        showAlert(message);
    }

    @Override
    public Context context() {
        return getApplicationContext();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }
}
