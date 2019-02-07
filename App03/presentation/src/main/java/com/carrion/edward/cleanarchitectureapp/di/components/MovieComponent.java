package com.carrion.edward.cleanarchitectureapp.di.components;

import com.carrion.edward.cleanarchitectureapp.di.PerActivity;
import com.carrion.edward.cleanarchitectureapp.di.modules.ActivityModule;
import com.carrion.edward.cleanarchitectureapp.di.modules.MovieModule;
import com.carrion.edward.cleanarchitectureapp.view.activity.MovieLisActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, MovieModule.class})
public interface MovieComponent extends ActivityComponent {
    void inject(MovieLisActivity movieLisActivity);
}
