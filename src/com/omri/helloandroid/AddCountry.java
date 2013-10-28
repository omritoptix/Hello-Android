package com.omri.helloandroid;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddCountry extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_country);
		//get add button
		Button addButton = (Button)findViewById(R.id.createCountryButton);
		//get country name
		final EditText countryNameEditText = (EditText) findViewById(R.id.countryNameEdit);
		//populate the spinner with values
		final Spinner spinner = (Spinner) findViewById(R.id.colors_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.colors_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		
		addButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String colorChosen = spinner.getSelectedItem().toString();
				String countryName = countryNameEditText.getText().toString();
				Intent returnIntent = new Intent();
				returnIntent.putExtra("color", colorChosen);
				returnIntent.putExtra("countryName", countryName);
				setResult(RESULT_OK, returnIntent);
				finish();				
			}
		});
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_country, menu);
		return true;
	}

}
