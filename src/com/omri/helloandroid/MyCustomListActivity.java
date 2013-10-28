package com.omri.helloandroid;

import java.security.PublicKey;
import java.sql.Date;
import java.util.List;

import com.omri.DataHandlers.CountriesDataSource;
import com.omri.DataHandlers.Country;
import com.omri.helloandroid.customUtil.CustomListAdapter;
import com.omri.helloandroid.customUtil.MyDbOpenHelper;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnFocusChangeListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MyCustomListActivity extends Activity {

	private CustomListAdapter myAdapter;
	private CountriesDataSource countriesDbSource;
	private ListView myListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.class_assignment_list_view);
		final Activity thisActivity = this;
/*		 final String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
			        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
			        "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
			        "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2"};*/
		
		//delete DB for checking create
//		this.deleteDatabase("countries.db");
		
		//open connnection to DB
		CountriesDataSource datasource = new CountriesDataSource(this);
		this.countriesDbSource = datasource;
	    datasource.open();
	    
	    //get all countries
	    List<Country> countries = datasource.getAllCountries();

		 //set the adapter
		CustomListAdapter adapter = new CustomListAdapter(this, countries,null);
		this.myAdapter = adapter;
		ListView myListView = (ListView)findViewById(R.id.countries_list);
		this.myListView = myListView;
		myListView.setAdapter(adapter);
		
		/*//set last color chosen to list if exists
		SharedPreferences shPr = getSharedPreferences("com.omri.shPr", MODE_PRIVATE);
		String lastColorChosen = (shPr.getString("color_chosen", null));
		if (lastColorChosen != null) {
//			Toast.makeText(this, "last color chosen :" + lastColorChosen, Toast.LENGTH_SHORT).show();
			this.myAdapter.setColor(lastColorChosen);
		}*/
		
		
		 // Register the ListView  for Context menu
        registerForContextMenu(myListView);
        
		//set onItemClick 
		myListView.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				//set our current adapter
				CustomListAdapter myAdapter =(CustomListAdapter) parent.getAdapter();
				//write to logcat
				Country country = (Country) myAdapter.getItem(position);
				Log.d("name",""+ country.getName());
				Log.d("count",""+myAdapter.getCount());
				//write to screen
				Toast.makeText(thisActivity,
					      "Clicked " + country.getName(), Toast.LENGTH_SHORT)
					      .show();
			}
			
		});	
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.countries_list_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {		
		//respond to clicked item events
		switch (item.getItemId()) {
		case R.id.add: 
			Intent addCountry = new Intent(this,AddCountry.class);
			startActivityForResult(addCountry, 2);
			break;
		case R.id.delete:
/*			myAdapter.toggleDeleteMode();
			myAdapter.notifyDataSetChanged();*/
			break;

		default:
			break;
		}
		return true;
	}
	
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	      super.onCreateContextMenu(menu, v, menuInfo);
	      MenuInflater inflater = getMenuInflater();
	      inflater.inflate(R.menu.list_context_menu, menu);
	}
	
	public boolean onContextItemSelected(MenuItem item) {
		//info holds the selected item info , not the menu item info
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
/*		Intent contextMenuIntent = new Intent(this,ChooseColor.class);
		switch(item.getItemId()) {
		case R.id.choose_color:
			//pass item selected position as parameter
			long itemId = myAdapter.getItemId(info.position);
			contextMenuIntent.putExtra("itemId", itemId);
			contextMenuIntent.putExtra("isItemIdNull", false);
			contextMenuIntent.putExtra("position", info.position);
			startActivityForResult(contextMenuIntent, 2);
			break;
		case R.id.delete:
			Country countryToDelete = (Country) myAdapter.getItem(info.position);
			String countryName = countryToDelete.getName();
			//update DB
			countriesDbSource.deleteCountry(countryToDelete);
			//update list view using hidden on text view
			myAdapter.countries.remove(countryToDelete);
			myAdapter.notifyDataSetChanged();
			Toast.makeText(this, countryName + "has been deleted!", Toast.LENGTH_SHORT).show();
		default:
			break;
		}*/
		String colorString;
		switch(item.getItemId()) {
		case R.id.red:
			colorString = "red";
			break;
		case R.id.green:
			colorString = "green";
			break;
		case R.id.blue:
			colorString = "blue";
			break;
		default:
			colorString = "red";
			break;
			
		}
		Country countryToUpdate = new Country(myAdapter.getItemId(info.position), null, colorString);
		//update db
		countriesDbSource.updateCountry(countryToUpdate);
		//update list view
		Country countryFromList = (Country) myAdapter.getItem(info.position);
		countryFromList.setColor(colorString);
		myAdapter.notifyDataSetChanged();
		
		return true;		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//remember the last color a user has chosen using shared preferences 
		SharedPreferences shPr = getSharedPreferences("com.omri.shPr", MODE_PRIVATE);
		Editor shPrEditor = shPr.edit();
		//check which application retrieve the result by checking requestCode
		if (requestCode == 1) {
			String color = data.getStringExtra("color");
			shPrEditor.putString("color_chosen", color);
			shPrEditor.commit();
			myAdapter.setColor(color);
		}
		if (requestCode == 2) {
			String color = data.getStringExtra("color");
			String countryName = data.getStringExtra("countryName");
			Country countryToAdd = countriesDbSource.createCountry(countryName, color);
			myAdapter.countries.add(countryToAdd);
			myAdapter.notifyDataSetChanged();
			
		}
		
	}

}
