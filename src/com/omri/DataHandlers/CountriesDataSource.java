package com.omri.DataHandlers;

import java.util.ArrayList;
import java.util.List;

import com.omri.helloandroid.customUtil.MyDbOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CountriesDataSource {

	//Database fields
	 private SQLiteDatabase database;
	 private MyDbOpenHelper dbHelper;
	 private String[] allColumns = { MyDbOpenHelper.COLUMN_ID,
			 MyDbOpenHelper.COLUMN_COUNTRY_NAME, MyDbOpenHelper.COLUMN_COUNTRY_COLOR };
	
	
	 public CountriesDataSource(Context context) {
		dbHelper = new MyDbOpenHelper(context);
	 }
	
	 public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	 }
	
	 public void close() {
	    dbHelper.close();
	 }
	 
	 public Country createCountry(String name, String color) {
		    ContentValues values = new ContentValues();
		    values.put(MyDbOpenHelper.COLUMN_COUNTRY_NAME, name);
		    values.put(MyDbOpenHelper.COLUMN_COUNTRY_COLOR, color);
		    long insertId = database.insert(MyDbOpenHelper.TABLE_COUNTRIES, null,
		        values);
		    Cursor cursor = database.query(MyDbOpenHelper.TABLE_COUNTRIES,
		        allColumns, MyDbOpenHelper.COLUMN_ID + " = " + insertId, null,
		        null, null, null);
		    cursor.moveToFirst();
		    Country newCountry = cursorToCountry(cursor);
		    cursor.close();
		    return newCountry;
		  }
	 
	 public void deleteCountry(Country country) {
		    long id = country.getId();
		    System.out.println("Country deleted with id: " + id);
		    database.delete(MyDbOpenHelper.TABLE_COUNTRIES, MyDbOpenHelper.COLUMN_ID
		        + "=" + id, null);
		  }
	 
	 
	 public List<Country> getAllCountries() {
		    List<Country> countries = new ArrayList<Country>();

		    Cursor cursor = database.query(MyDbOpenHelper.TABLE_COUNTRIES,
		        allColumns, null, null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		      Country country = cursorToCountry(cursor);
		      countries.add(country);
		      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		    return countries;
		  }
	 
	 
	 public int updateCountry(Country country) {		
		 	String strFilter = MyDbOpenHelper.COLUMN_ID + "=" + country.getId();
		    ContentValues values = new ContentValues();
		    values.put(MyDbOpenHelper.COLUMN_COUNTRY_COLOR, country.getColor());
		 
		    // updating row
		    return database.update(MyDbOpenHelper.TABLE_COUNTRIES, values, strFilter,null);
		}
	 
	 private Country cursorToCountry(Cursor cursor) {
		    Country country = new Country(cursor.getLong(0),cursor.getString(1),cursor.getString(2));
		    return country;
		  }
	  

}
