package com.carrion.edward.shakeapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ShakeDetector.OnShakeListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private ShakeDetector shakeDetector;
    private ConstraintLayout constraintLayout;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.cl);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        shakeDetector = new ShakeDetector();
        shakeDetector.setOnShakeListener(this);
    }

    @Override
    public void onShake(int count) {
        handleShakeEvent(count);
    }

    private void handleShakeEvent(int count) {
        if (flag) {
            constraintLayout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_blue_light));
        } else {
            constraintLayout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
        }

        flag = !flag;
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(shakeDetector, accelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        sensorManager.unregisterListener(shakeDetector);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        sensorManager.unregisterListener(shakeDetector);
        super.onDestroy();
    }
}
