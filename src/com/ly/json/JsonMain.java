package com.ly.json;

import java.util.ArrayList;
import java.util.List;

import com.ly.pojo.Course;
import com.ly.pojo.Student;
import com.ly.pojo.Teacher;

public class JsonMain {

	
	public static void main(String[] args) {
		
		testToString();
	}
	
	private static void testToString() {
		
		Teacher chineseTeacher = new Teacher();
		chineseTeacher.setAge(50);
		chineseTeacher.setName("LY");
		chineseTeacher.setSex(0);
		Course course = new Course();
		course.setName("Chinese");
		chineseTeacher.setCourse(course);
		
		Teacher chineseTeacher1 = new Teacher();
		chineseTeacher1.setAge(50);
		chineseTeacher1.setName("LY1");
		chineseTeacher1.setSex(0);
		Course course1 = new Course();
		course1.setName("Chinese1");
		chineseTeacher1.setCourse(course1);
		
		Student student1 = new Student();
		student1.setAge(10);
		student1.setName("YL");
		student1.setSex(0);
		
		List<Teacher> teachers = new ArrayList<>();
		teachers.add(chineseTeacher);
		teachers.add(chineseTeacher1);
		
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		courses.add(course1);
		
		student1.setTeachers(teachers);
		student1.setCourses(courses);
		
		System.out.println(Json.toString(student1));
	}

}
