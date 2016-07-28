package com.stayinspired;

import com.stayinspired.SimpleGestureFilter.SimpleGestureListener;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class AboutUsActivity extends ActionBarActivity implements
		SimpleGestureListener {
	private SimpleGestureFilter detector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setLogo(R.drawable.laouncher);
		getSupportActionBar().setTitle(" " + "Stay Inspired!");
		getSupportActionBar().setDisplayUseLogoEnabled(true);
		setContentView(R.layout.activity_about_us);
		detector = new SimpleGestureFilter(this, this);
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

	public void website(View v) {
		Intent zintent = new Intent();
		zintent.setData(Uri.parse("http://www.thetestament.in"));
		startActivity(zintent);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();

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
		getMenuInflater().inflate(R.menu.about_us, menu);
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
			aintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
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
