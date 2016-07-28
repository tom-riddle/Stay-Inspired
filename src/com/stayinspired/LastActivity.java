package com.stayinspired;

import java.io.IOException;
import java.util.Date;

import com.stayinspired.SimpleGestureFilter.SimpleGestureListener;

import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

public class LastActivity extends ActionBarActivity implements
		SimpleGestureListener {

	TextView scaleText, tv9;
	TextView scaleGesture;
	ImageView image;
	TextView finalh;
	private SimpleGestureFilter detector;
	ZoomControls zoom;
	ScaleGestureDetector scaleGestureDetector;
	float mScaleFactor = 1.f;
	DataBaseHelper db = new DataBaseHelper(this);
	SQLiteDatabase mydb;
	Cursor localCursor5 = null;
	String str99;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setLogo(R.drawable.laouncher);
		getSupportActionBar().setTitle(" " + "Stay Inspired!");
		getSupportActionBar().setDisplayUseLogoEnabled(true);

		setContentView(R.layout.activity_last);
		scaleText = (TextView) findViewById(R.id.article);
		detector = new SimpleGestureFilter(this, this);
		scaleGestureDetector = new ScaleGestureDetector(this,
				new simpleOnScaleGestureListener());
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
		tv9 = (TextView) findViewById(R.id.article);
		Cursor localCursor5 = this.db.getOneArticle();
		String str8 = localCursor5.getString(
				localCursor5.getColumnIndex("stayinspired_event")).toString();
		str99 = str8;
		tv9.setText(str8);
		Log.d("YO", "successful");

		finalh = (TextView) findViewById(R.id.finalhead);
		Cursor localCursor3 = this.db.gettitle();
		String st = localCursor3.getString(
				localCursor3.getColumnIndex("stayinspired_title")).toString();
		finalh.setText(st);

	}

	public void share23(View v) {
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
	public boolean dispatchTouchEvent(MotionEvent me) {
		// Call onTouchEvent of SimpleGestureFilter class
		this.detector.onTouchEvent(me);
		return super.dispatchTouchEvent(me);
	}

	public void onSwipe(int direction) {

		switch (direction) {

		case SimpleGestureFilter.SWIPE_RIGHT:
			Intent a = new Intent();
			a.setClass(this, FifthActivity.class);
			startActivity(a);
			overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_right);
			break;

		}

	}

	public  void quiz7(View v) {
		Intent quizintent = new Intent();
		quizintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		quizintent.setClass(this, QuizActivity.class);
		startActivity(quizintent);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	@Override
	public void onBackPressed() {
		Intent lintent = new Intent();
		lintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		lintent.setClass(this, FifthActivity.class);
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
		getMenuInflater().inflate(R.menu.last, menu);
		// MenuItem item=menu.findItem(R.id.action_share);
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		scaleGestureDetector.onTouchEvent(event);
		return true;
	}

	public class simpleOnScaleGestureListener extends
			SimpleOnScaleGestureListener {

		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			// TODO Auto-generated method stub
			float size = scaleGesture.getTextSize();

			float factor = detector.getScaleFactor();

			float product = size * factor;

			scaleGesture.setTextSize(TypedValue.COMPLEX_UNIT_PX, product);

			size = scaleGesture.getTextSize();

			return true;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		if (id == R.id.action_favorite) {
			mydb = this.db.getWritableDatabase();
			String str76 = (String) DateFormat.format("dd-MM", new Date());
			String str77 = "INSERT INTO favourites(favourites_event,favourites_title,favourites_highlights,favourites_image,favourites_date) SELECT stayinspired_event,stayinspired_title,stayinspired_highlights,stayinspired_image,stayinspired_date FROM stayinspired WHERE stayinspired_date LIKE '%"
					+ str76 + "'";
			this.mydb.execSQL(str77);
			
			Toast.makeText(this, "Marked as Favourite", Toast.LENGTH_SHORT).show();

		}
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
		tv9.setTextSize(20);

	}
}
