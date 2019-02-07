package com.carrion.edward.data.repository;

import com.carrion.edward.data.entity.mapper.MovieEntityDataMapper;
import com.carrion.edward.data.repository.datasource.MovieDataStore;
import com.carrion.edward.data.repository.datasource.MovieDataStoreFactory;
import com.carrion.edward.domain.Movie;
import com.carrion.edward.domain.repository.MovieRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * {@link MovieRepository} for retrieving movie data.
 */
@Singleton
public class MovieDataRepository implements MovieRepository {

    private final MovieDataStoreFactory movieDataStoreFactory;
    private final MovieEntityDataMapper movieEntityDataMapper;

    /**
     * Constructs a {@link MovieRepository}.
     *
     * @param dataStoreFactory      A factory to construct different data source implementations.
     * @param movieEntityDataMapper {@link MovieEntityDataMapper}.
     */
    @Inject
    MovieDataRepository(MovieDataStoreFactory dataStoreFactory,
                        MovieEntityDataMapper movieEntityDataMapper) {
        this.movieDataStoreFactory = dataStoreFactory;
        this.movieEntityDataMapper = movieEntityDataMapper;
    }

    @Override
    public Observable<List<Movie>> movies() {
        final MovieDataStore movieDataStore = this.movieDataStoreFactory.createCloudDataStore();
        return movieDataStore.movieEntityList().map(this.movieEntityDataMapper::transform);
    }
}