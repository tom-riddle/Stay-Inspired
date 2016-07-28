package com.stayinspired;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

public class NotifyActivity extends BroadcastReceiver {

	@SuppressLint("NewApi")
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent intent1 = new Intent(context, FourthActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent1,
				0);

		Uri alarmSound = RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		Notification noti = new Notification.Builder(context)
				.setContentTitle("Inspire Me!")
				.setSmallIcon(R.drawable.notification).setContentIntent(pIntent)
				.setSound(alarmSound).build();
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		// hide the notification after its selected
		noti.flags |= Notification.FLAG_AUTO_CANCEL;

		notificationManager.notify(1, noti);

	}

}