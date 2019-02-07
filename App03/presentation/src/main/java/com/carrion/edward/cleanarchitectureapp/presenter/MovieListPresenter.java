package com.carrion.edward.cleanarchitectureapp.presenter;

import android.support.annotation.NonNull;

import com.carrion.edward.cleanarchitectureapp.di.PerActivity;
import com.carrion.edward.cleanarchitectureapp.exception.ErrorMessageFactory;
import com.carrion.edward.cleanarchitectureapp.mapper.MovieModelDataMapper;
import com.carrion.edward.cleanarchitectureapp.model.MovieModel;
import com.carrion.edward.cleanarchitectureapp.view.MovieListView;
import com.carrion.edward.domain.Movie;
import com.carrion.edward.domain.exception.DefaultErrorBundle;
import com.carrion.edward.domain.exception.ErrorBundle;
import com.carrion.edward.domain.interactor.DefaultObserver;
import com.carrion.edward.domain.interactor.GetMovieList;

import java.util.List;

import javax.inject.Inject;

@PerActivity
public class MovieListPresenter implements Presenter {

    private MovieListView viewListView;

    private final GetMovieList getMovieListUseCase;
    private final MovieModelDataMapper movieModelDataMapper;

    @Inject
    public MovieListPresenter(GetMovieList getMovieListUseCase, MovieModelDataMapper movieModelDataMapper) {
        this.getMovieListUseCase = getMovieListUseCase;
        this.movieModelDataMapper = movieModelDataMapper;
    }

    public void setView(@NonNull MovieListView view) {
        this.viewListView = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.getMovieListUseCase.dispose();
        this.viewListView = null;
    }

    public void initialize() {
        this.loadMovieList();
    }

    private void loadMovieList() {
        this.showViewLoading();
        this.getMovieList();
    }

    private void showViewLoading() {
        this.viewListView.showLoading();
    }

    private void hideViewLoading() {
        this.viewListView.hideLoading();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewListView.context(),
                errorBundle.getException());
        this.viewListView.showError(errorMessage);
    }

    private void showMoviesCollectionInView(List<Movie> moviesList) {
        final List<MovieModel> movieModelsCollection =
                this.movieModelDataMapper.transform(moviesList);
        this.viewListView.renderMovieList(movieModelsCollection);
    }

    private void getMovieList() {
        this.getMovieListUseCase.execute(new MovieListObserver(), null);
    }

    private final class MovieListObserver extends DefaultObserver<List<Movie>> {

        @Override
        public void onComplete() {
            MovieListPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            MovieListPresenter.this.hideViewLoading();
            MovieListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
        }

        @Override
        public void onNext(List<Movie> movies) {
            MovieListPresenter.this.showMoviesCollectionInView(movies);
        }
    }
}
