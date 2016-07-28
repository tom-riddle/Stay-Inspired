package com.stayinspired;

import java.io.ByteArrayInputStream;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.stayinspired.DataBaseHelper;
import com.stayinspired.SimpleGestureFilter.SimpleGestureListener;

public class FavouritesActivity extends ActionBarActivity implements
		OnTouchListener, SimpleGestureListener {
	DataBaseHelper db = new DataBaseHelper(this);
	Cursor localCursor101 = null;
	String str99;
	final static float STEP = 200;
	TextView tv27;
	float mRatio = 1.0f;
	int mBaseDist;
	float mBaseRatio;
	float fontsize = 13;
	ImageView img;
	private SimpleGestureFilter detector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setLogo(R.drawable.laouncher);
		getSupportActionBar().setTitle(" " + "Stay Inspired!");
		getSupportActionBar().setDisplayUseLogoEnabled(true);

		setContentView(R.layout.activity_favourites);
		tv27 = (TextView) findViewById(R.id.favorite);
		img = (ImageView) findViewById(R.id.finalimg1);
		detector = new SimpleGestureFilter(this, this);
		/*
		 * try {
		 * 
		 * db.createDataBase();
		 * 
		 * } catch (IOException ioe) {
		 * 
		 * throw new Error("Unable to create database");
		 * 
		 * }
		 */

		try {

			db.openDataBase();

		} catch (SQLException sqle) {

			throw sqle;

		}

		ListViewActivity.localCursor.moveToFirst();
		String str37 = ListViewActivity.localCursor
				.getString(
						ListViewActivity.localCursor
								.getColumnIndex("favourites_event")).toString();
		str99 = str37;
		tv27.setText(str37);
		Log.d("YO", "successful");
		
		
		byte[] bb = ListViewActivity.localCursor.getBlob(ListViewActivity.localCursor
				.getColumnIndex("favourites_image"));
		ByteArrayInputStream imageStream = new ByteArrayInputStream(bb);
		Bitmap theImage = BitmapFactory.decodeStream(imageStream);
		img.setImageBitmap(theImage);

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
			a.setClass(this, ListViewActivity.class);
			startActivity(a);
			overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_right);
			break;

		}

	}

	public void share231(View v) {
		Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);

		// set the type
		shareIntent.setType("text/plain");

		// build the body of the message to be shared
		String shareMessage = str99;

		// add the message
		shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareMessage);

		// start the chooser for sharing
		startActivity(Intent.createChooser(shareIntent, "Share Via"));
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	public boolean onTouchEvent(MotionEvent event) {
		if (event.getPointerCount() == 2) {
			int action = event.getAction();
			int pureaction = action & MotionEvent.ACTION_MASK;
			if (pureaction == MotionEvent.ACTION_POINTER_DOWN) {
				mBaseDist = getDistance(event);
				mBaseRatio = mRatio;
			} else {
				float delta = (getDistance(event) - mBaseDist) / STEP;
				float multi = (float) Math.pow(2, delta);
				mRatio = Math.min(1024.0f, Math.max(0.1f, mBaseRatio * multi));
				tv27.setTextSize(mRatio + 13);

			}
		}
		return true;
	}

	int getDistance(MotionEvent event) {
		int dx = (int) (event.getX(0) - event.getX(1));
		int dy = (int) (event.getY(0) - event.getY(1));
		return (int) (Math.sqrt(dx * dx + dy * dy));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.favourites, menu);
		// MenuItem item=menu.findItem(R.id.action_share);
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
		tv27.setTextSize(18);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}
