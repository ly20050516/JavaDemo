package com.ly.generic;

public class Book implements Product{

	@Override
	public String type() {
		// TODO Auto-generated method stub
		return "book";
	}

	@Override
	public String number() {
		// TODO Auto-generated method stub
		return "123456789";
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return type() + " " + number();
	}

}
