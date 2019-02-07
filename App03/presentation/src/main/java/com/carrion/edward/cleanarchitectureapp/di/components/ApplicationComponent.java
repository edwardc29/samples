package com.carrion.edward.cleanarchitectureapp.di.components;

import android.content.Context;

import com.carrion.edward.cleanarchitectureapp.di.modules.ApplicationModule;
import com.carrion.edward.cleanarchitectureapp.view.activity.BaseActivity;
import com.carrion.edward.domain.executor.PostExecutionThread;
import com.carrion.edward.domain.executor.ThreadExecutor;
import com.carrion.edward.domain.repository.MovieRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    MovieRepository movieRepository();
}
