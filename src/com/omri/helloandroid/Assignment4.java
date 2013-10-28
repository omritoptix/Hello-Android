package com.omri.helloandroid;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Assignment4 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.class_assignment_4);
		
		//"call" intent
		Button callButton = (Button)findViewById(R.id.call);
		callButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent callIntent = new Intent();
				callIntent.setAction("android.intent.action.CALL");
				callIntent.setData(Uri.parse("tel:0548045492"));
				startActivity(callIntent);
			}
		});
		
		//"browser" intent
		Button browserButton = (Button)findViewById(R.id.browser);
		browserButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent();
				browserIntent.setAction("android.intent.action.VIEW");
				browserIntent.setData(Uri.parse("http://www.mako.co.il"));
//				browserIntent.putExtra(SearchManager.QUERY,"http://www.mako.co.il");
				startActivity(browserIntent);
				
			}
		});
		
		//"open map" intent
		Button mapButton = (Button)findViewById(R.id.map);
		mapButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent mapIntent = new Intent();
				mapIntent.setAction(Intent.ACTION_VIEW);
				mapIntent.setData(Uri.parse("geo:0,0?q=someplace"));
				startActivity(mapIntent);
				
			}
		});
		
		//"capture image" intent
		Button captureImageButton = (Button)findViewById(R.id.capture_image);
		captureImageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent captureImageIntent = new Intent();
				captureImageIntent.setAction("android.media.action.IMAGE_CAPTURE");
				startActivity(captureImageIntent);
				
			}
		});
		
		//"view contact" intent
		Button viewContactsButton = (Button)findViewById(R.id.view_contacts);
		viewContactsButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent viewContactsIntent = new Intent();
				viewContactsIntent.setAction(Intent.ACTION_VIEW);
				viewContactsIntent.setData(Uri.parse("content://contacts/people/"));
				startActivity(viewContactsIntent);
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.assignment4, menu);
		return true;
	}
	
	

}
