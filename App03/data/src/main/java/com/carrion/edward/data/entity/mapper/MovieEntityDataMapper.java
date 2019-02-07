package com.carrion.edward.data.entity.mapper;

import com.carrion.edward.data.entity.MovieEntity;
import com.carrion.edward.data.entity.MovieResponseEntity;
import com.carrion.edward.domain.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MovieEntityDataMapper {

    @Inject
    public MovieEntityDataMapper() {}

    public Movie transform(MovieEntity movieEntity) {
        Movie movie = null;

        if (movieEntity != null) {
            movie = new Movie(movieEntity.getId());
            movie.setTitle(movieEntity.getTitle());
            movie.setPopularity(movieEntity.getPopularity());

        }

        return movie;
    }

    public List<Movie> transform(List<MovieEntity> movieEntityList) {
        final List<Movie> movieList = new ArrayList<>();
        for (MovieEntity movieEntity : movieEntityList) {
            Movie movie = transform(movieEntity);

            if (movie != null) {
                movieList.add(movie);
            }
        }

        return movieList;
    }

    public List<Movie> transform(MovieResponseEntity movieResponseEntity) {
        return transform(movieResponseEntity.getResults());
    }

}
