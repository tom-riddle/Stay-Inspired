package com.stayinspired;

import java.io.IOException;

import com.stayinspired.SimpleGestureFilter.SimpleGestureListener;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class ListViewActivity extends ActionBarActivity implements
		SimpleGestureListener {
	ListView list;
	Context context;
	DataBaseHelper db = new DataBaseHelper(this);
	SQLiteDatabase mydb;
	public static Cursor localCursor;
	private SimpleGestureFilter detector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

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
		detector = new SimpleGestureFilter(this, this);
		mydb = this.db.getWritableDatabase();
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setLogo(R.drawable.laouncher);
		getSupportActionBar().setTitle(" " + "Stay Inspired");
		getSupportActionBar().setDisplayUseLogoEnabled(true);
		setContentView(R.layout.activity_list_view);
		context = this;
		String[] memetitles = new String[] { "favourites_title",
				"favourites_date" };

		int[] arrayView = new int[] { R.id.textView87, R.id.datelist };

		list = (ListView) findViewById(R.id.listView1);
		Cursor cursor = null;
		cursor = this.db.gettitle2();

		if (cursor.getCount() == 0)
			Toast.makeText(this, "There are no favourites yet!", Toast.LENGTH_SHORT)
					.show();

		// create an Adapter with arguments layoutID, Cursor, Array Of Columns,
		// and Array of ViewIds which is to be Populated

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.single_row, cursor, memetitles, arrayView, 0);
		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long arg3) {
				long id = list.getItemIdAtPosition(position);

				localCursor = mydb.rawQuery(
						"SELECT favourites_event,favourites_image FROM favourites WHERE _id LIKE '%"
								+ id + "'", null);
				Intent favintent = new Intent();
				favintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				favintent.setClass(ListViewActivity.this,
						FavouritesActivity.class);
				startActivity(favintent);
				overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_right);

			}
		});

	}

	@Override
	public void onBackPressed() {
		Intent s=new Intent();
		s.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		s.setClass(this,HomeActivity.class);
		startActivity(s);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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
		getMenuInflater().inflate(R.menu.list_view, menu);
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
