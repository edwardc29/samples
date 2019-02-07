package com.carrion.edward.cleanarchitectureapp;

import android.app.Application;

import com.carrion.edward.cleanarchitectureapp.di.components.ApplicationComponent;
import com.carrion.edward.cleanarchitectureapp.di.components.DaggerApplicationComponent;
import com.carrion.edward.cleanarchitectureapp.di.modules.ApplicationModule;

public class AndroidApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
