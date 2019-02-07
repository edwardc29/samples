package com.carrion.edward.data.net;

import com.carrion.edward.data.entity.MovieResponseEntity;

import io.reactivex.Observable;

public interface RestApi {
    Observable<MovieResponseEntity> movieEntityList();
}
