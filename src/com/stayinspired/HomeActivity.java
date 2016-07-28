package com.stayinspired;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;

public class HomeActivity extends ActionBarActivity {
	LinearLayout ll;
	SharedPreferences msharedpreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setLogo(R.drawable.laouncher);
		getSupportActionBar().setTitle(" " + "Stay Inspired!");
		getSupportActionBar().setDisplayUseLogoEnabled(true);

		setContentView(R.layout.activity_home);
		Animation fadein = new AlphaAnimation(0, 1);
		fadein.setInterpolator(new AccelerateInterpolator());
		fadein.setStartOffset(200); // Start fading out after 500 milli seconds
		fadein.setDuration(1000);
		ll = (LinearLayout) findViewById(R.id.yup);
		ll.setAnimation(fadein);
	}

	public void aboutus(View v) {
		Intent aintent = new Intent();
		aintent.setClass(this, AboutUsActivity.class);
		startActivity(aintent);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	public void settings(View v) {
		Intent cintent = new Intent();
		cintent.setClass(this, SettingsActivity.class);
		startActivity(cintent);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	public void favorites(View v) {
		Intent fintent = new Intent();
		fintent.setClass(this, ListViewActivity.class);
		startActivity(fintent);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	public void setclock(View v) {
		Intent dintent = new Intent();
		dintent.setClass(this, ClockActivity.class);
		startActivity(dintent);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	public void inspireme(View v) {
		Intent bintent = new Intent();
		bintent.setClass(this, FourthActivity.class);
		startActivity(bintent);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	@Override
	public void onBackPressed() {
		moveTaskToBack(true);

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
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		return super.onOptionsItemSelected(item);
	}
}
