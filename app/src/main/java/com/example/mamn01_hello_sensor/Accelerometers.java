package com.example.mamn01_hello_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Accelerometers extends AppCompatActivity implements SensorEventListener {
    private TextView x, y, z;
    private SensorManager mSensorManager;
    private Sensor mAccelerator;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometers);

        x = findViewById(R.id.accelerometer_x);
        y = findViewById(R.id.accelerometer_y);
        z = findViewById(R.id.accelerometer_z);
        view = getWindow().getDecorView().getRootView();
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        start();
    }

    private void start() {
        mAccelerator = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerator, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this, mAccelerator);
    }

    @Override
    protected void onResume() {
        super.onResume();
        start();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x.setText(getString(R.string.accelerometer_value_x, event.values[0]));
            y.setText(getString(R.string.accelerometer_value_y, event.values[1]));
            z.setText(getString(R.string.accelerometer_value_z, event.values[2]));
            view.setBackgroundColor(
                    Color.rgb(
                            getRGBEquivalent(event.values[0]),
                            getRGBEquivalent(event.values[1]),
                            getRGBEquivalent(event.values[2])
                    )
            );
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private int getRGBEquivalent(float value) {
        return (int) Math.floor(255 * Math.abs(value) / 10);
    }
}
