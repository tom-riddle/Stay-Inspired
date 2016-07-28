package com.stayinspired;

import java.util.Calendar;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;

public class Sensored extends Service implements SensorEventListener {

	SensorManager ssrmgr = null;
	Sensor ssr = null;
	float mAccel = 0;
	float mAccelCurrent = 0;
	float mAccelLast = 0;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		ssrmgr = (SensorManager) getSystemService(SENSOR_SERVICE);
		ssr = ssrmgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		ssrmgr.registerListener(this, ssr, SensorManager.SENSOR_DELAY_NORMAL);
		return START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];
		mAccelLast = mAccelCurrent;
		mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		mAccel = mAccelCurrent - 10;
		if (mAccel > 1.4) {
			if(hour>=4 && hour<=12)
			{
			Intent i = new Intent();
			i.setClass(this, BridgeActivity.class);
			this.sendBroadcast(i);
			ssrmgr.unregisterListener(this);
			stopSelf();
			}
		}

	}

	protected void onPause() {
		ssrmgr.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

}
