package com.ly.pojo;

import com.ly.json.JsonName;

public class Person {

	@JsonName(value="name")
	private String name;
	@JsonName(value="sex")
	private int sex;
	@JsonName(value="age")
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
		
}
