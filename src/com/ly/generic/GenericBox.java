package com.ly.generic;

import java.util.ArrayList;

public abstract class GenericBox<T,E> {

	ArrayList<T> mArrayList = new ArrayList<T>();
	public void input(T t) {
		if(t != null) {
			mArrayList.add(t);
		}
	}
	
	public abstract E process();
}
