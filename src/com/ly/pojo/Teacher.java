package com.ly.pojo;

import java.util.List;

import com.ly.json.JsonName;

public class Teacher extends Person{

	@JsonName(value="students",listParameterType=Student.class)
	private List<Student> students;
	@JsonName(value="course",listParameterType=Course.class)
	private Course course;
	
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	
}
