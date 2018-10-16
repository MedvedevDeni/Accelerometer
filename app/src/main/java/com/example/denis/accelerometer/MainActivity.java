package com.example.denis.accelerometer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

//  Класс позволяющий работать с датчиками устройства
    private SensorManager sensorManager;
//  Объект для наблюдения за определенным датчиком
    private Sensor accelerometer;
//  Текстовые поля для вывода и просмотра возвращаемых значений
    private TextView originalValues;
    private TextView roundedValues;

//  Вызывается при создании Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        originalValues = findViewById(R.id.originalValues);
        roundedValues = findViewById(R.id.roundedValues);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

//  Вызывается при изменении показаний датчика
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float coordinateX = event.values[0];
            float coordinateY = event.values[1];
            float coordinateZ = event.values[2];
            int coordX = (int) coordinateX;
            int coordY = (int) coordinateY;
            int coordZ = (int) coordinateZ;
            String tmp = coordinateX + "\n" + coordinateY + "\n" + coordinateZ;
            String temp = coordX + "\n" + coordY + "\n" + coordZ;
            originalValues.setText(tmp);
            roundedValues.setText(temp);
        }
    }

//  Вызывается при восстановлении из неактивного состояния
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

//  Вызывается перед выходом из активного состояния
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

//  Вызывается при изменении точности показаний датчика
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}