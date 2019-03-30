package com.example.mamn01_hello_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openSensors(View view) {
        Intent intent = new Intent(this, Sensors.class);
        startActivity(intent);
    }

    public void openAccelerometers(View view) {
        Intent intent = new Intent(this, Accelerometers.class);
        startActivity(intent);
    }
}
