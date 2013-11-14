package com.chrisseto.quicksilver.util;

import com.chrisseto.quicksilver.object.Player;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Calibrate implements SensorEventListener {

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		synchronized (this) {
			switch (event.sensor.getType()) {
			case Sensor.TYPE_ACCELEROMETER:
				Assets.getInstance().sensorManager.unregisterListener(this);
				System.arraycopy(event.values, 0, Player.accelCal, 0, 3);
				break;
			}
		}
	}

	public static void calibrate() {
		Assets.getInstance().sensorManager.registerListener(new Calibrate(),
				Assets.getInstance().sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
	}
}
