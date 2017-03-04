package com.ly.json;

import java.util.ArrayList;
import java.util.List;

import com.ly.pojo.Course;
import com.ly.pojo.Student;
import com.ly.pojo.Teacher;

public class JsonMain {

	
	public static void main(String[] args) {
		
		testToString();
		testToObject();
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
		
//		List<Student> students = new ArrayList<>();
//		students.add(student1);
//		
//		chineseTeacher.setStudents(students);
		long t1 = System.currentTimeMillis();
		String jsonString = Json.toString(student1);
		System.out.println("toString time " + (System.currentTimeMillis() - t1));
		System.out.println(jsonString);
	}
	
	private static void testToObject() {
		String jsonString = "{\"teachers\":[{\"course\":{\"name\":\"Chinese\",\"score\":0},\"name\":\"LY\",\"sex\":0,\"age\":50},{\"course\":{\"name\":\"Chinese1\",\"score\":0},\"name\":\"LY1\",\"sex\":0,\"age\":50}],\"courses\":[{\"name\":\"Chinese\",\"score\":0},{\"name\":\"Chinese1\",\"score\":0}],\"name\":\"YL\",\"sex\":0,\"age\":10}";
		long t1 = System.currentTimeMillis();
		Student student = Json.toObject(jsonString, Student.class);
		System.out.println("toObject time " + (System.currentTimeMillis() - t1));
	}

}
