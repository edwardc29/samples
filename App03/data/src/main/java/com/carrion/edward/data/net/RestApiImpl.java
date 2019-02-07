package com.carrion.edward.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.carrion.edward.data.BuildConfig;
import com.carrion.edward.data.entity.MovieResponseEntity;
import com.carrion.edward.data.entity.mapper.MovieEntityJsonMapper;
import com.carrion.edward.data.exception.NetworkConnectionException;

import io.reactivex.Observable;

import java.net.MalformedURLException;

public class RestApiImpl implements RestApi {

    private final Context context;
    private final MovieEntityJsonMapper movieEntityJsonMapper;

    public RestApiImpl(Context context, MovieEntityJsonMapper movieEntityJsonMapper) {
        if (context == null || movieEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.movieEntityJsonMapper = movieEntityJsonMapper;
    }

    @Override
    public Observable<MovieResponseEntity> movieEntityList() {
        return Observable.create(emitter -> {
            if (isThereInternetConnection()) {
                try {
                    String responseUserEntities = getMovieEntitiesFromApi();
                    if (responseUserEntities != null) {
                        emitter.onNext(movieEntityJsonMapper.transformUserEntityCollection(
                                responseUserEntities));
                        emitter.onComplete();
                    } else {
                        emitter.onError(new NetworkConnectionException());
                    }
                } catch (Exception e) {
                    emitter.onError(new NetworkConnectionException(e.getCause()));
                }
            } else {
                emitter.onError(new NetworkConnectionException());
            }
        });
    }

    private String getMovieEntitiesFromApi() throws MalformedURLException {
        return ApiConnection.createGET(context, BuildConfig.MOVIES + "?api_key=" + BuildConfig.API_KEY + "&language=en-US&page=1").requestSyncCall();
    }

    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}