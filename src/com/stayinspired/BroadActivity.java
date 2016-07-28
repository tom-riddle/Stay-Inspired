package com.stayinspired;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BroadActivity extends BroadcastReceiver {

	public static final String CUSTOM_INTENT = "com.stayinspired.intent.action.TEST";

	@SuppressLint("NewApi")
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent intent1 = new Intent(context, Sensored.class);
		context.startService(intent1);
	}
}
