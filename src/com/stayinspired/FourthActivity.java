package com.stayinspired;

import java.util.Calendar;

import com.stayinspired.SimpleGestureFilter.SimpleGestureListener;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class FourthActivity extends ActionBarActivity implements
		SimpleGestureListener {

	TextView tv1;
	private SimpleGestureFilter detector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setLogo(R.drawable.laouncher);
		getSupportActionBar().setTitle(" " + "Stay Inspired!");
		getSupportActionBar().setDisplayUseLogoEnabled(true);
		setContentView(R.layout.activity_fourth);
		tv1 = (TextView) findViewById(R.id.date);
		detector = new SimpleGestureFilter(this, this);
		final Calendar c = Calendar.getInstance();
		int yy = c.get(Calendar.DAY_OF_MONTH);
		int mm = c.get(Calendar.MONTH);
		String name[] = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };
		tv1.setText(new StringBuilder().append(name[mm]).append(" ").append(yy));

		ImageButton myanim = (ImageButton) findViewById(R.id.gif);
		final AnimationDrawable myanimdraw = (AnimationDrawable) myanim
				.getDrawable();

		myanim.post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				myanimdraw.start();
			}
		});
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
			a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			a.setClass(this, HomeActivity.class);
			startActivity(a);
			overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_right);
			break;
		case SimpleGestureFilter.SWIPE_LEFT:
			Intent a1 = new Intent();
			a1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			a1.setClass(this, InspireActivity.class);
			startActivity(a1);
			overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_right);
			break;

		}

	}

	public void gif1(View v) {
		Intent kintent = new Intent();
		kintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		kintent.setClass(this, InspireActivity.class);
		startActivity(kintent);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	@Override
	public void onBackPressed() {
		Intent lintent = new Intent();
		lintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		lintent.setClass(this, HomeActivity.class);
		startActivity(lintent);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fourth, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.home23) {
			Intent aintent = new Intent();
			aintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			aintent.setClass(this, HomeActivity.class);
			startActivity(aintent);
			overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_right);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onDoubleTap() {
		// TODO Auto-generated method stub

	}
}
