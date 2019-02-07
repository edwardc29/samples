package com.carrion.edward.data.repository.datasource;

import com.carrion.edward.data.entity.MovieResponseEntity;
import com.carrion.edward.data.net.RestApi;

import io.reactivex.Observable;

public class CloudMovieDataStore implements MovieDataStore {
    private final RestApi restApi;

    public CloudMovieDataStore(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override public Observable<MovieResponseEntity> movieEntityList() {
        return this.restApi.movieEntityList();
    }
}
