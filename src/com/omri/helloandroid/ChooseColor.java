package com.omri.helloandroid;

import com.omri.helloandroid.customUtil.CustomListAdapter;

import android.inputmethodservice.ExtractEditText;
import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ChooseColor extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_color);
		
		final Intent returnIntent = new Intent();
		
		Button buttonRed = (Button)findViewById(R.id.buttonRed);
		Button buttonBlue = (Button)findViewById(R.id.buttonBlue);
		Button buttonGreen = (Button)findViewById(R.id.buttonGreen);
		
			
		buttonRed.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				returnIntent.putExtra("color", "red");
				setResult(RESULT_OK, returnIntent);
				finish();
			}
		});
		
		buttonBlue.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				returnIntent.putExtra("color", "blue");
				setResult(RESULT_OK, returnIntent);
				finish();
			}
		});
		
		buttonGreen.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				returnIntent.putExtra("color", "green");
				setResult(RESULT_OK, returnIntent);
				finish();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose_color, menu);
		return true;
	}

}
