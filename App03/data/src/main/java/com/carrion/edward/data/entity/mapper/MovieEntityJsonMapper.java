package com.carrion.edward.data.entity.mapper;

import com.carrion.edward.data.entity.MovieEntity;
import com.carrion.edward.data.entity.MovieResponseEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import javax.inject.Inject;

public class MovieEntityJsonMapper {

    private ObjectMapper objectMapper;

    @Inject
    public MovieEntityJsonMapper() {
        this.objectMapper = new ObjectMapper();
    }

    public MovieEntity transformUserEntity(String userJsonResponse) throws IOException {
        return objectMapper.readValue(userJsonResponse, MovieEntity.class);
    }

    public MovieResponseEntity transformUserEntityCollection(String userListJsonResponse) throws IOException {
        return objectMapper.readValue(userListJsonResponse, MovieResponseEntity.class);
    }
}