package com.stayinspired;

import java.io.IOException;

import com.stayinspired.SimpleGestureFilter.SimpleGestureListener;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class FifthActivity extends ActionBarActivity implements
		SimpleGestureListener {
	DataBaseHelper db = new DataBaseHelper(this);
	private SimpleGestureFilter detector;
	Cursor localCursor = null;
	Cursor localCursor1 = null;
	Cursor localCursor2 = null;
	Cursor localCursor3 = null;
	Cursor localCursor4 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setLogo(R.drawable.laouncher);
		getSupportActionBar().setTitle(" " + "Stay Inspired!");
		getSupportActionBar().setDisplayUseLogoEnabled(true);
		setContentView(R.layout.activity_fifth);
		detector = new SimpleGestureFilter(this, this);
		try {

			db.createDataBase();

		} catch (IOException ioe) {

			throw new Error("Unable to create database");

		}

		try {

			db.openDataBase();

		} catch (SQLException sqle) {

			throw sqle;

		}
		TextView tv1 = (TextView) findViewById(R.id.title);
		Cursor localCursor1 = this.db.getHighlights();
		String str2 = localCursor1.getString(
				localCursor1.getColumnIndex("stayinspired_highlights"))
				.toString();
		tv1.setText(str2);
		Log.d("YO", "successful");

		TextView tv2 = (TextView) findViewById(R.id.why1);
		Cursor localCursor2 = this.db.getwhy();
		String str3 = localCursor2.getString(
				localCursor2.getColumnIndex("stayinspired_why")).toString();
		tv2.setText(str3);
		Log.d("YO", "successful");

		TextView tv4 = (TextView) findViewById(R.id.how1);
		Cursor localCursor4 = this.db.gethow();
		String str5 = localCursor4.getString(
				localCursor4.getColumnIndex("stayinspired_how")).toString();
		tv4.setText(str5);
		Log.d("YO", "successful");

		TextView tv3 = (TextView) findViewById(R.id.what1);
		Cursor localCursor3 = this.db.getwhat();
		String str4 = localCursor3.getString(
				localCursor3.getColumnIndex("stayinspired_what")).toString();
		tv3.setText(str4);
		Log.d("YO", "successful");

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
			a.setClass(this, InspireActivity.class);
			startActivity(a);
			overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_right);
			break;
		case SimpleGestureFilter.SWIPE_LEFT:
			Intent a1 = new Intent();
			a1.setClass(this, LastActivity.class);
			startActivity(a1);
			overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_right);
			break;

		}

	}

	public void lastpage(View v) {
		Intent lintent = new Intent();
		lintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		lintent.setClass(this, LastActivity.class);
		startActivity(lintent);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	@Override
	public void onBackPressed() {
		Intent lintent = new Intent();
		lintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		lintent.setClass(this, InspireActivity.class);
		startActivity(lintent);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fifth, menu);
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
