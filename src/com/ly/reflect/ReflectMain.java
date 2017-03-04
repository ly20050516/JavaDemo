package com.ly.reflect;

public class ReflectMain {

	public static void main(String[] args) {
		
		ReflectDemo reflectDemo = new ReflectDemo();
		reflectDemo.printClass(FieldClass.class);
		reflectDemo.printClass(FieldInterface.class);
	}

}
