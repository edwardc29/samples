package com.carrion.edward.data.repository.datasource;

import com.carrion.edward.data.entity.MovieResponseEntity;

import io.reactivex.Observable;

public interface MovieDataStore {
    Observable<MovieResponseEntity> movieEntityList();
}
