package com.ly.reflect;

public class FieldClass extends FieldSupperClass{
	private int privateField;
	protected int protectedField;
	public int publicField;
	int packageField;
	
	public FieldClass() {
	}
	
	public FieldClass(int pri,int pro,int pub,int pack){
		privateField = pri;
		protectedField = pro;
		publicField = pub;
		packageField = pack;
	}
	
	private String name(String nickName) {
		String nameString = null;
		if(isStringEmpty(nameString)){
			nameString = "I am FieldClass";
		}
		nameString = nameString + ",and My nick name is " + nickName;
		return nameString;
	}
	
	private boolean isStringEmpty(String string){
		return string == null || string.equals("");
	}
}
