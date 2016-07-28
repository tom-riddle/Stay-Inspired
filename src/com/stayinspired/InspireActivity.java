package com.stayinspired;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.stayinspired.SimpleGestureFilter.SimpleGestureListener;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class InspireActivity extends ActionBarActivity implements
		SimpleGestureListener {

	private SimpleGestureFilter detector;
	DataBaseHelper db = new DataBaseHelper(this);
	Cursor localCursor = null;
	Cursor localCursor2 = null;
	Cursor localCursor10 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setLogo(R.drawable.laouncher);
		getSupportActionBar().setTitle(" " + "Stay Inspired!");
		getSupportActionBar().setDisplayUseLogoEnabled(true);
		setContentView(R.layout.activity_inspire);
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
		TextView tv = (TextView) findViewById(R.id.textview5);
		Cursor localCursor = this.db.gettitle();
		String str2 = localCursor.getString(
				localCursor.getColumnIndex("stayinspired_title")).toString();
		tv.setText(str2);
		Log.d("YO", "successful");

		ImageView iv = (ImageView) findViewById(R.id.imageview2);
		Cursor localCursor10 = this.db.getImage();
		byte[] bb = localCursor10.getBlob(localCursor10
				.getColumnIndex("stayinspired_image"));
		ByteArrayInputStream imageStream = new ByteArrayInputStream(bb);
		Bitmap theImage = BitmapFactory.decodeStream(imageStream);
		iv.setImageBitmap(theImage);
		
		TextView tv21=(TextView) findViewById(R.id.quotetext);
		Cursor localCursor2=this.db.gettitle();
		String str3=localCursor2.getString(localCursor2.getColumnIndex("stayinspired_title")).toString();
		tv21.setText(str3);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent me) {
		// Call onTouchEvent of SimpleGestureFilter class
		this.detector.onTouchEvent(me);
		return super.dispatchTouchEvent(me);
	}
	
	public void next(View v)
	{
		Intent a1 = new Intent();
		a1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		a1.setClass(this, FifthActivity.class);
		startActivity(a1);
		overridePendingTransition(R.anim.slide_in_left,
				R.anim.slide_out_right);
	}

	public void onSwipe(int direction) {

		switch (direction) {

		case SimpleGestureFilter.SWIPE_RIGHT:
			Intent a = new Intent();
			a.setClass(this, FourthActivity.class);
			startActivity(a);
			overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_right);
			break;
		case SimpleGestureFilter.SWIPE_LEFT:
			Intent a1 = new Intent();
			a1.setClass(this, FifthActivity.class);
			startActivity(a1);
			overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_right);
			break;

		}

	}

	@Override
	public void onBackPressed() {
		Intent lintent = new Intent();
		lintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		lintent.setClass(this, FourthActivity.class);
		startActivity(lintent);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inspire, menu);
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
