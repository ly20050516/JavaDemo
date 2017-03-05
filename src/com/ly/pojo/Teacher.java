package com.ly.pojo;

import com.ly.json.JsonName;

public class Teacher extends Person{

	@JsonName(value="course")
	private Course course;
	@JsonName(value="salary")
	private int salary;
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	
}
