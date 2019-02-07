package com.carrion.edward.domain.interactor;

import com.carrion.edward.domain.Movie;
import com.carrion.edward.domain.executor.PostExecutionThread;
import com.carrion.edward.domain.executor.ThreadExecutor;
import com.carrion.edward.domain.repository.MovieRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetMovieList extends UseCase<List<Movie>, Void> {
    private final MovieRepository movieRepository;

    @Inject
    GetMovieList(MovieRepository movieRepository, ThreadExecutor threadExecutor,
                 PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }

    @Override
    Observable<List<Movie>> buildUseCaseObservable(Void aVoid) {
        return this.movieRepository.movies();
    }
}
