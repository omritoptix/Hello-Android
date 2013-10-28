package com.omri.helloandroid.customUtil;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

public class MyDbOpenHelper extends SQLiteOpenHelper {

	//************tables and columns************************//
	//************country tables****************************//
	public static final String TABLE_COUNTRIES = "countries";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_COUNTRY_NAME = "name";
	public static final String COLUMN_COUNTRY_COLOR = "color";
	private static final String DATABASE_NAME = "countries.db";
	private static final int DATABASE_VERSION = 1;
/*	//all columns
	private String[] allColumns = { MyDbOpenHelper.COLUMN_ID,
			 MyDbOpenHelper.COLUMN_COUNTRY_NAME, MyDbOpenHelper.COLUMN_COUNTRY_COLOR };*/
	
	
	public MyDbOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	


	
	
	//create tables statements	 
	 private static final String DATABASE_CREATE = "create table "
		      + TABLE_COUNTRIES + "(" + COLUMN_ID
		      + " integer primary key autoincrement, " + COLUMN_COUNTRY_NAME
		      + " text not null, " + COLUMN_COUNTRY_COLOR + " text not null" + ");";
	 
	 
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public MyDbOpenHelper(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		//insert default data
		db.execSQL("insert into countries (name,color) values "
				+ "('Afghanistan','red'),"
				+ "('Albania','red'),"
				+ "('Algeria','red'),"
				+ "('Andorra','red'),"
				+ "('Angola','red'),"
				+ "('Antigua','red'),"
				+ "('Barbuda','red'),"
				+ "('Argentina','green'),"
				+ "('Armenia','red'),"
				+ "('Bahamas','red'),"
				+ "('Bahrain','red'),"
				+ "('Bangladesh','red'),"
				+ "('Barbados','red'),"
				+ "('Belarus','blue'),"
				+ "('Belgium','red'),"
				+ "('Brazil','red'),"
				+ "('Brunei','red'),"
				+ "('Darussalam','red');"
				);
		
		//print out column names
/*		Cursor ti = db.rawQuery("PRAGMA table_info(countries)", null);
		if ( ti.moveToFirst() ) {
		    do {
		        System.out.println("col: " + ti.getString(1));
		    } while (ti.moveToNext());
		}
		
		//good practice to insert one value
		ContentValues values = new ContentValues();
	    values.put(MyDbOpenHelper.COLUMN_COUNTRY_NAME, "test");
	    values.put(MyDbOpenHelper.COLUMN_COUNTRY_COLOR, "red");
		db.insert(TABLE_COUNTRIES, null, values);*/
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MyDbOpenHelper.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTRIES);
		    onCreate(db);
	}

}
