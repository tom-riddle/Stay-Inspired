package com.stayinspired;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends ActionBarActivity {

	TextView finalvalue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setLogo(R.drawable.laouncher);
		getSupportActionBar().setTitle(" " + "Stay Inspired!");
		getSupportActionBar().setDisplayUseLogoEnabled(true);
		setContentView(R.layout.activity_result);
		finalvalue = (TextView) findViewById(R.id.result23);
		/*
		 * Bundle bun=getIntent().getExtras(); String
		 * sap=bun.getString("SCORE");
		 */
		Intent intent7 = getIntent();
		int sap = intent7.getIntExtra("SCORE", 0);
		finalvalue.setText(String.valueOf(sap));
		if (sap == 5)
			Toast.makeText(this, "Congratulations, you nailed it!",
					Toast.LENGTH_SHORT).show();
	}

	public void home1(View v) {
		Intent aintent = new Intent();
		aintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		aintent.setClass(this, HomeActivity.class);
		startActivity(aintent);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	public void lastread1(View v) {
		Intent quizintent = new Intent();
		quizintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		quizintent.setClass(this, LastActivity.class);
		startActivity(quizintent);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	public void fav1(View v) {
		Intent quizintent = new Intent();
		quizintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		quizintent.setClass(this, ListViewActivity.class);
		startActivity(quizintent);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	public void set1(View v) {
		Intent quizintent = new Intent();
		quizintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		quizintent.setClass(this, SettingsActivity.class);
		startActivity(quizintent);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	public void gotoquiz(View v) {
		
		
		Intent x = new Intent();
		x.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		x.setClass(this, QuizActivity.class);
		startActivity(x);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	@Override
	public void onBackPressed() {
		Intent quiz1intent = new Intent();
		quiz1intent.setClass(this, LastActivity.class);
		startActivity(quiz1intent);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
		finish();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		return super.onOptionsItemSelected(item);
	}
}
