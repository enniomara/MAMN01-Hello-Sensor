package com.example.mamn01_hello_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Sensors extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mRotationV;
    float[] rMat = new float[9];
    float[] orientation = new float[3];
    int mAzimuth;
    ImageView compass_img;
    TextView degreesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        compass_img = (ImageView) findViewById(R.id.img_compass);
        degreesText = (TextView) findViewById(R.id.txt_azimuth);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        start();
    }

    private void start() {
        mRotationV = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this, mRotationV);
    }

    @Override
    protected void onResume() {
        super.onResume();
        start();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            SensorManager.getRotationMatrixFromVector(rMat, event.values);
            mAzimuth = (int) (Math.toDegrees(SensorManager.getOrientation(rMat, orientation)[0]) + 360) % 360;
        }

        mAzimuth = Math.round(mAzimuth);

        if (mAzimuth <= 15 || mAzimuth >= 345) {
            degreesText.setTextColor(Color.RED);
        } else {
            degreesText.setTextColor(Color.BLACK);
        }

        compass_img.setRotation(-mAzimuth);
        degreesText.setText(mAzimuth + "° ");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
