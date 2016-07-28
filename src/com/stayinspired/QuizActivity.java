package com.stayinspired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends ActionBarActivity {
	DataBaseHelper db = new DataBaseHelper(this);
	// declare the variables
	public static List<Question> questions; // the list with the questions
	private List<String> answers = new ArrayList<String>(); // this will contain
															// the answer for
															// each question
	private String correctAnswer, optionD, optionB, optionC; //
	private Question currentQ;
	public static int score;
	static TextView question;
	Button button1, button2, button3, button4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setLogo(R.drawable.laouncher);
		getSupportActionBar().setTitle(" " + "Stay Inspired!");
		getSupportActionBar().setDisplayUseLogoEnabled(true);
		setContentView(R.layout.activity_quiz);

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

		// get the fields from the layout
		// questionNumber = (TextView) findViewById(R.id.textView333);
		question = (TextView) findViewById(R.id.textView333);
		button1 = (Button) findViewById(R.id.button111);
		button2 = (Button) findViewById(R.id.button112);
		button3 = (Button) findViewById(R.id.button113);
		button4 = (Button) findViewById(R.id.button114);

		questions = this.db.getAllQuestions();

		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				Button answerButton = ((Button) v);
				String answer = answerButton.getText().toString();

				updateScore(answer);

				loadNextQuestion();

			}
		});
		button2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				Button answerButton = ((Button) v);
				String answer = answerButton.getText().toString();

				updateScore(answer);

				// and get the next question
				loadNextQuestion();

			}
		});
		button3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				Button answerButton = ((Button) v);
				String answer = answerButton.getText().toString();

				updateScore(answer);

				loadNextQuestion();
				overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_right);

			}
		});
		button4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				Button answerButton = ((Button) v);
				String answer = answerButton.getText().toString();

				updateScore(answer);

				loadNextQuestion();
				overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_right);

			}
		});
		resetQuiz();
	}

	public void resetQuiz() {

		// reset the score
		score = 0;

		// shuffle the question list
		Collections.shuffle(questions);

		// load the 1st question
		loadNextQuestion();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

	}

	private void loadNextQuestion() {

		if (questions.size() > 0)
			currentQ = questions.remove(0);
		else {
			results();
			return;
		}

		question.setText(currentQ.getQUESTION());

		// assign the results to values. These values will be used after for
		// result check
		correctAnswer = currentQ.getCorrectAnswer();

		optionB = currentQ.getOPTB();
		optionC = currentQ.getOPTC();
		optionD = currentQ.getOPTD();
		// make sure the list is empty
		answers.clear();

		// add the answers to the list
		answers.add(correctAnswer);

		answers.add(optionB);
		answers.add(optionC);
		answers.add(optionD);

		// shuffle the answers
		Collections.shuffle(answers);

		// assign the to buttons
		button1.setText(answers.remove(0));
		button2.setText(answers.remove(0));
		button3.setText(answers.remove(0));
		button4.setText(answers.remove(0));

	}

	// this method redirects to another activity with the results. for my
	// example
	// I passed some variables to the new activity and finished the current
	private void results() {

		Intent results = new Intent(getBaseContext(), ResultActivity.class);
		results.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		results.putExtra("SCORE", score);

		startActivity(results);

		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

	}

	@SuppressWarnings("static-access")
	private void updateScore(String answer) {
		Context context = getApplicationContext();

		// Set layout to toast

		if (answer == correctAnswer) {
			score++;
			
			
			final Toast toast = new Toast(context);
			toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					toast.cancel();
				}
			}, 10);

		} else {
			final Toast toast = new Toast(context);
			toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show();
			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					toast.cancel();
				}
			}, 10);
		}

	}

	@Override
	public void onBackPressed() {
		Intent lintent = new Intent();
		lintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		lintent.setClass(this, LastActivity.class);
		startActivity(lintent);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		finish();
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.home23) {
			Intent newIntent = new Intent();
			newIntent.setClass(this, HomeActivity.class);
			startActivity(newIntent);
			overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_right);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
