package com.omri.DataHandlers;

public class Country {
	
	private long id;
	private String name;
	private String color;
	

	public Country(long id, String name, String color) {
		this.id = id;
		this.name = name;
		this.color = color;		
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

}
