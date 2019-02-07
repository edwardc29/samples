package com.carrion.edward.cleanarchitectureapp.view.activity;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.carrion.edward.cleanarchitectureapp.AndroidApplication;
import com.carrion.edward.cleanarchitectureapp.di.components.ApplicationComponent;
import com.carrion.edward.cleanarchitectureapp.di.modules.ActivityModule;

public class BaseActivity extends AppCompatActivity {
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);

        AlertDialog alertDialog = builder.create();

        if (!isFinishing()) {
            alertDialog.show();
        }
    }
}
