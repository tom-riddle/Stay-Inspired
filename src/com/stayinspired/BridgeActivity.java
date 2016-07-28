package com.stayinspired;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.Preference;

public class BridgeActivity extends BroadcastReceiver {
	SharedPreferences pref;
	private static int time;
	@SuppressLint("NewApi")
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		PendingIntent pIntent = PendingIntent.getActivity(context, 0,
				new Intent(), 0);

		Uri alarmSound = RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		Notification noti = new Notification.Builder(context)
				.setContentTitle("The Testament")
				.setContentText("Good Mornin', see you in some time!")
				.setSmallIcon(R.drawable.notification).setContentIntent(pIntent)
				.setSound(alarmSound).build();
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		// hide the notification after its selected

		noti.flags |= Notification.FLAG_AUTO_CANCEL;

		notificationManager.notify(0, noti);
		pref = context.getSharedPreferences("myPrefs",Context.MODE_PRIVATE);
		time = pref.getInt("FLAG", 0);
		Intent i = new Intent(context, NotifyActivity.class);
		context.startService(i);

		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, i,
				0);

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		am.set(AlarmManager.RTC_WAKEUP,
				System.currentTimeMillis() + time, pendingIntent);

	}
}
