package com.ly.pojo;

import java.util.List;

import com.ly.json.JsonName;

public class Student extends Person{

	@JsonName(value="teachers",listParameterType=Teacher.class)
	private List<Teacher> teachers;
	@JsonName(value="courses",listParameterType=Course.class)
	private List<Course> courses;
	
	public List<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	
}
