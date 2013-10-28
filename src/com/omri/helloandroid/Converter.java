package com.omri.helloandroid;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.util.Log;

public class Converter extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.class_assignment_converter);
		
		//add listener to convert button
		Button convertButton = (Button)findViewById(R.id.convert_button);
		convertButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText editText = (EditText) findViewById(R.id.editConvert);
				//also need to Make sure it's an int and not other type
				int tempratureToConvert = Integer.parseInt(editText.getText().toString());
				//get radio button, and accordingly convert to the right temperature
				RadioGroup radioButtonGroup = (RadioGroup) findViewById(R.id.radioDegree);
				//get checked button id
				int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();
				//get id's of current radio buttons
		        int celsiusId = findViewById(R.id.radioC).getId();
		        int farenhiteId = findViewById(R.id.radioF).getId();
		        
		        //check with radio button was checked , and convert the temperature
		        
		        if (radioButtonID == celsiusId) {
		        	//set edit text to display in farenhite
		        	String convertedTemp = Integer.toString(tempratureToConvert + 273);
		        	editText.setText(convertedTemp);
		        	Toast.makeText(Converter.this,"CELSIUS", Toast.LENGTH_SHORT).show();
		        }
		        else {
		        	String convertedTemp = Integer.toString(tempratureToConvert - 273);
		        	editText.setText(convertedTemp);
		        	Toast.makeText(Converter.this,"FARENHITE", Toast.LENGTH_SHORT).show();
		        }
		        //switch can't work with dynamically evaluated variables - just constants
		        
//				switch (radioButtonID) {
//				case celsiusId : Toast.makeText(Converter.this,"CELSIUS", Toast.LENGTH_SHORT).show();   
//				}

			}
			
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.converter, menu);
		return true;
	}

}
