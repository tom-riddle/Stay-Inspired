package com.stayinspired;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

	private static String DB_PATH = "/data/data/com.stayinspired/databases/";
	private static String DB_NAME = "si";
	private SQLiteDatabase myDataBase;
	private final Context myContext;

	public DataBaseHelper(Context context) {

		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}

	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();
		SQLiteDatabase db = null;

		if (dbExist) {

		} else {
			db = this.getReadableDatabase();
			db.close();
			try {
				copyDataBase();

			} catch (IOException e) {

				throw new Error("Error copying database");

			}
		}

	}

	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {

		}

		if (checkDB != null) {

			checkDB.close();

		}

		return checkDB != null ? true : false;
	}

	private void copyDataBase() throws IOException {

		InputStream myInput = myContext.getAssets().open(DB_NAME);
		String outFileName = DB_PATH + DB_NAME;
		OutputStream myOutput = new FileOutputStream(outFileName);

		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException {

		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);

	}

	@Override
	public synchronized void close() {

		if (myDataBase != null)
			myDataBase.close();

		super.close();

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public Cursor getOneArticle() {
		String str1 = (String) DateFormat.format("dd-MM", new Date());
		String str = "SELECT stayinspired_event FROM stayinspired WHERE stayinspired_date LIKE '%"
				+ str1 + "'";
		Cursor localCursor = this.myDataBase.rawQuery(str, null);
		localCursor.moveToFirst();
		return localCursor;
	}

	public Cursor getHighlights() {
		String str7 = (String) DateFormat.format("dd-MM", new Date());
		String str1 = "SELECT stayinspired_highlights FROM stayinspired WHERE stayinspired_date LIKE '%"
				+ str7 + "'";
		Cursor localCursor = this.myDataBase.rawQuery(str1, null);
		localCursor.moveToFirst();
		return localCursor;

	}

	public Cursor getQuotes() {
		String str71 = (String) DateFormat.format("dd-MM", new Date());
		String str12 = "SELECT stayinspired_quotes FROM stayinspired WHERE stayinspired_date LIKE '%"
				+ str71 + "'";
		Cursor localCursor = this.myDataBase.rawQuery(str12, null);
		localCursor.moveToFirst();
		return localCursor;

	}

	public Cursor getImage() {
		String str10 = (String) DateFormat.format("dd-MM", new Date());
		String str11 = "SELECT stayinspired_image FROM stayinspired WHERE stayinspired_date LIKE '%"
				+ str10 + "'";
		Cursor localCursor = this.myDataBase.rawQuery(str11, null);
		localCursor.moveToFirst();
		return localCursor;
	}

	public Cursor getwhy() {
		String str13 = (String) DateFormat.format("dd-MM", new Date());
		String str14 = "SELECT stayinspired_why FROM stayinspired WHERE stayinspired_date LIKE '%"
				+ str13 + "'";
		Cursor localCursor = this.myDataBase.rawQuery(str14, null);
		localCursor.moveToFirst();
		return localCursor;
	}

	public Cursor getwhat() {
		String str15 = (String) DateFormat.format("dd-MM", new Date());
		String str16 = "SELECT stayinspired_what FROM stayinspired WHERE stayinspired_date LIKE '%"
				+ str15 + "'";
		Cursor localCursor = this.myDataBase.rawQuery(str16, null);
		localCursor.moveToFirst();
		return localCursor;
	}

	public Cursor gethow() {
		String str17 = (String) DateFormat.format("dd-MM", new Date());
		String str18 = "SELECT stayinspired_how FROM stayinspired WHERE stayinspired_date LIKE '%"
				+ str17 + "'";
		Cursor localCursor = this.myDataBase.rawQuery(str18, null);
		localCursor.moveToFirst();
		return localCursor;
	}

	public Cursor gettitle() {
		String str19 = (String) DateFormat.format("dd-MM", new Date());
		String str20 = "SELECT stayinspired_title FROM stayinspired WHERE stayinspired_date LIKE '%"
				+ str19 + "'";
		Cursor localCursor = this.myDataBase.rawQuery(str20, null);
		localCursor.moveToFirst();
		return localCursor;
	}

	public Cursor getfavourite() {
		Cursor localCursor = this.myDataBase.rawQuery(
				"SELECT favourites_event FROM favourites", null);
		localCursor.moveToFirst();
		return localCursor;

	}

	public Cursor getFavimage() {
		Cursor localCursor = this.myDataBase.rawQuery(
				"SELECT favourites_image FROM favourites", null);
		localCursor.moveToFirst();
		return localCursor;
	}

	public Cursor gettitle2() {

		String str20 = "SELECT _id,favourites_title,favourites_date FROM favourites";
		Cursor localCursor = this.myDataBase.rawQuery(str20, null);
		localCursor.moveToFirst();
		return localCursor;
	}

	public List<Question> getAllQuestions() throws SQLException {

		List<Question> questionList = new ArrayList<Question>();
		String str30 = (String) DateFormat.format("dd-MM", new Date());

		String selectQuery = "SELECT _id,quiz_question,quiz_answer,quiz_two,quiz_three,quiz_four FROM quiz WHERE quiz_date LIKE '%"
				+ str30 + "'";

		Cursor cursor = myDataBase.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {

			do {

				Question quest = new Question();
				quest.setID(cursor.getInt(cursor.getColumnIndex("_id")));

				quest.setQUESTION(cursor.getString(cursor
						.getColumnIndex("quiz_question")));
				quest.setCorrectAnswer(cursor.getString(cursor
						.getColumnIndex("quiz_answer")));
				quest.setOPTB(cursor.getString(cursor
						.getColumnIndex("quiz_two")));
				quest.setOPTC(cursor.getString(cursor
						.getColumnIndex("quiz_three")));
				quest.setOPTD(cursor.getString(cursor
						.getColumnIndex("quiz_four")));
				questionList.add(quest);
			} while (cursor.moveToNext());
		}

		cursor.close();
		myDataBase.close();
		return questionList;

	}

}