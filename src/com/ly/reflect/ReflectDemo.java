package com.ly.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectDemo {

	public void printClass(Class<?> c){
		
		// class name
		System.out.println("Class Name: " + c.getName());
		// Declared fields
		System.out.println("Declared Fields, length = " + c.getDeclaredFields().length);
		Field [] declaredFields = c.getDeclaredFields();
		for(int i = 0;i < declaredFields.length;i ++) {
			System.out.println("Declared Fields " + i + " : " + declaredFields[i].getName());
		}
		
		// Fields
		System.out.println("Fields,length = " + c.getFields().length);
		Field[] fields = c.getFields();
		for(int i = 0;i < fields.length;i ++) {
			System.out.println("Fields " + i + " : " + fields[i]);
		}
		
		// Declared methods
		Method[] declaredMethods = c.getDeclaredMethods();
		System.out.println("Declared Methods, length = " + declaredMethods.length);
		for(int i = 0;i < declaredMethods.length;i ++){
			System.out.println("Declared Methods " + i + " " + declaredMethods[i]);
		}
		
		// Methods
		Method[] methods = c.getMethods();
		System.out.println("Methods,length = " + methods.length);
		for(int i = 0;i < methods.length;i ++){
			System.out.println("Methods " + i + " " + methods[i]);
		}
		
		// class loader
		ClassLoader classLoader = c.getClassLoader();
		System.out.println("class loader =" + classLoader);
		System.out.println("system class loader = " + ClassLoader.getSystemClassLoader());
		
		// constructor
		Constructor<?> []constructors = c.getConstructors();
		System.out.println("Constructors,length = " + constructors.length);
		for(int i = 0;i < constructors.length;i ++){
			System.out.println("constructor " + i + " " + constructors[i]);
		}
	}
	
	public void fieldClassOperation() {
		try {
			Class<?> filedClass = Class.forName("com.ly.reflect.FieldClass");
			System.out.println(filedClass.getName());
			Constructor<?> fieldConstructor = filedClass.getConstructor(
					new Class<?>[]{
							int.class,
							int.class,
							int.class,
							int.class}
					);
			System.out.println("Constructor is " + fieldConstructor);
			fieldConstructor.setAccessible(true);
			Object fieldClassInstance = fieldConstructor.newInstance(1,2,3,4);
			Field privateField = filedClass.getDeclaredField("privateField");
			privateField.setAccessible(true);
			privateField.setInt(fieldClassInstance, 10);
			System.out.println("privateField = " + privateField.getInt(fieldClassInstance));
			
			Method nameMethod = filedClass.getDeclaredMethod("name", String.class);
			nameMethod.setAccessible(true);
			String nameString = (String) nameMethod.invoke(fieldClassInstance, "Zhuoying");
			System.out.println("name call = " + nameString);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
