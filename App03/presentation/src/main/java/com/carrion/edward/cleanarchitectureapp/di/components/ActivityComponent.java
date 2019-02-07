package com.carrion.edward.cleanarchitectureapp.di.components;

import android.app.Activity;

import com.carrion.edward.cleanarchitectureapp.di.PerActivity;
import com.carrion.edward.cleanarchitectureapp.di.modules.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
}
