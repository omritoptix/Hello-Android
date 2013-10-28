package com.omri.helloandroid.customUtil;

import com.omri.DataHandlers.Country;

import java.util.List;

import com.omri.helloandroid.R;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class CustomListAdapter extends BaseAdapter {
	//array of string
//	private final String[] values;
	//array of countries
	public List<Country> countries;
	//the activity context
	private final Activity contextActivity;
//	private boolean isDeleteMode = false;
	private String menuItemColor;
	
	 
	
	//save us to do for every element - get view by id
	static class ViewHolder {
	    public TextView text;
	 }
	
	public CustomListAdapter(Activity ctxt, List<Country> ctries, String desiredColor) {
		this.countries = ctries;
		this.contextActivity = ctxt;
		this.menuItemColor = desiredColor;
	}

	@Override
	public int getCount() {
		return countries.size();
	}

	@Override
	public Object getItem(int position) {
		return countries.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return countries.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
	    if (rowView == null) {
	    	ViewHolder viewHolder = new ViewHolder();
	    	LayoutInflater inflater = contextActivity.getLayoutInflater();
	      /*rowView = inflater.inflate(R.layout.class_assignment_checkbox, null);
	      viewHolder.text = (CheckBox) rowView.findViewById(R.id.rowCheckbox);*/	      
	    	rowView = inflater.inflate(R.layout.class_assignment_row_layout, null);
	    	viewHolder.text = (TextView) rowView.findViewById(R.id.row_layout);
/*	      viewHolder.image = (ImageView) rowView
	          .findViewById(R.id.ImageView01);*/
	    	rowView.setTag(viewHolder);
	    }
	    
	    ViewHolder holder = (ViewHolder) rowView.getTag();
	    Country country = countries.get(position);
	    holder.text.setText(country.getName());
	    
	    //**********deactivated, since added color per country************//
	    //check if there is a color defined - set for all countries
/*	    if (this.menuItemColor != null) {
	    	if (menuItemColor.equals("red")) {
	    		holder.text.setTextColor(Color.RED);
	    	}
	    	if (menuItemColor.equals("green")) {
	    		holder.text.setTextColor(Color.GREEN);
	    	}
	    	if (menuItemColor.equals("blue")) {
	    		holder.text.setTextColor(Color.BLUE);
	    	}
	    }*/
	    
	    //*****************************************************************//
	    
	  //set colors accordingly for each country
	    if (country.getColor().equals("red")) {
	    	holder.text.setTextColor(Color.RED);
	    }
	    if (country.getColor().equals("green")) {
	    	holder.text.setTextColor(Color.GREEN);
	    }
	    if (country.getColor().equals("blue")) {
	    	holder.text.setTextColor(Color.BLUE);
	    }
	    

	    return rowView;
	}
	
	//function to set list color
	public void setColor(String desiredColor) {
		this.menuItemColor = desiredColor;
		this.notifyDataSetChanged();
	}
	
/*	public void toggleDeleteMode() {
		this.isDeleteMode = !this.isDeleteMode;
	}*/
	
	

};