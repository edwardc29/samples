package com.carrion.edward.domain.repository;

import com.carrion.edward.domain.Movie;

import java.util.List;

import io.reactivex.Observable;

public interface MovieRepository {
    Observable<List<Movie>> movies();
}
