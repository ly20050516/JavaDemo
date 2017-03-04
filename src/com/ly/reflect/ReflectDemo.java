package com.ly.reflect;

import java.lang.reflect.Field;

public class ReflectDemo {

	public void printClass(Class<?> c){
		
		System.out.println("Class Name: " + c.getName());
		System.out.println("Declared Fields, length = " + c.getDeclaredFields().length);
		Field [] declaredFields = c.getDeclaredFields();
		for(int i = 0;i < declaredFields.length;i ++) {
			System.out.println("Declared Fields " + i + " : " + declaredFields[i]);
		}
		
		System.out.println("Fields,length = " + c.getFields().length);
		Field[] fields = c.getFields();
		for(int i = 0;i < fields.length;i ++) {
			System.out.println("Fields " + i + " : " + fields[i]);
		}
	}

}
