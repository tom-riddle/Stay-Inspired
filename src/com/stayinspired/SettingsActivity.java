package com.stayinspired;

import java.util.Calendar;

import com.stayinspired.SimpleGestureFilter.SimpleGestureListener;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.view.MotionEvent;

public class SettingsActivity extends PreferenceActivity implements
		SimpleGestureListener {
	public static int flag;
	public static String strnotif;
	private SimpleGestureFilter detector;
	boolean notifyEnabled;
	SharedPreferences pref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		detector = new SimpleGestureFilter(this, this);
		getFragmentManager().beginTransaction()
				.replace(android.R.id.content, new PrefsFragment()).commit();
	}

	public void saveFlag() {

		pref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putInt("FLAG", flag);
		editor.commit();

	}

	public class PrefsFragment extends PreferenceFragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.settings);
			flag = 3600 * 1000;
			saveFlag();
			SwitchPreference checkboxPref = (SwitchPreference) findPreference("checkboxpref");
			checkboxPref
					.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

						@Override
						public boolean onPreferenceChange(
								Preference preference, Object newValue) {
							// TODO Auto-generated method stub
							Boolean sw = (Boolean) newValue;
							if (sw) {
								setAlarm();
							} else {
								disableAlarm();
							}
							return true;
						}
					});

			ListPreference listpref = (ListPreference) findPreference("listpref");

			listpref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

				@Override
				public boolean onPreferenceChange(Preference preference,
						Object newValue) {
					// TODO Auto-generated method stub

					strnotif = (String) newValue;

					if (strnotif.equals("Two")) {
						flag = 7200 * 1000;
						saveFlag();
					} else if (strnotif.equals("Three")) {
						flag = 180 * 60 * 1000;
						saveFlag();
					} else if (strnotif.equals("Four")) {
						flag = 240 * 60 * 1000;
						saveFlag();
					} else {
						flag = 7200 * 1000;
						saveFlag();
					}
					return true;
				}
			});

		}
	}

	public void setAlarm() {
		Intent intent = new Intent(getApplicationContext(), BroadActivity.class);
		// intent.putExtra("FLAG", flag);
		AlarmManager scheduler = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		PendingIntent scheduledIntent = PendingIntent.getBroadcast(this, 0,
				intent, 0);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 04);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		scheduler.setRepeating(AlarmManager.RTC_WAKEUP,
				calendar.getTimeInMillis(), 24 * 60 * 60 * 1000,
				scheduledIntent);
	}

	public void disableAlarm() {
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		Intent intent = new Intent(this, BroadActivity.class);
		PendingIntent senderMor = PendingIntent
				.getBroadcast(this, 0, intent, 0);
		am.cancel(senderMor);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent me) {
		// Call onTouchEvent of SimpleGestureFilter class
		this.detector.onTouchEvent(me);
		return super.dispatchTouchEvent(me);
	}

	public void onSwipe(int direction) {

		switch (direction) {
		case SimpleGestureFilter.SWIPE_RIGHT:
			Intent a = new Intent();
			a.setClass(this, HomeActivity.class);
			startActivity(a);
			overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_right);
			break;
		}

	}

	@Override
	public void onBackPressed() {
		Intent s = new Intent();
		s.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		s.setClass(this, HomeActivity.class);
		startActivity(s);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	@Override
	public void onDoubleTap() {
		// TODO Auto-generated method stub

	}

}