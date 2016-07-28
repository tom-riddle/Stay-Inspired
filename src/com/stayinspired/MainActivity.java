package com.stayinspired;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TableLayout;

public class MainActivity extends ActionBarActivity {
	CountDownTimer Timer;
	TableLayout rl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Animation fadeOut = new AlphaAnimation(1, 0);
		fadeOut.setInterpolator(new AccelerateInterpolator());
		fadeOut.setStartOffset(1000); // Start fading out after 500 milli seconds
		fadeOut.setDuration(2500);
		rl=(TableLayout) findViewById(R.id.nope);
		rl.setAnimation(fadeOut);
		
		
		Timer=new CountDownTimer(3000, 1000){
			public void onFinish(){
				closeScreen();
			}
		@Override
		public void onTick(long millisUntilFinished){
			
		}
		}.start();
	}
	
	public void closeScreen()
	{
		Intent aintent=new Intent();
		aintent.setClass(this, HomeActivity.class);
		startActivity(aintent);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
}
