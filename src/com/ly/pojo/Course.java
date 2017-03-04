package com.ly.pojo;

import com.ly.json.JsonName;

public class Course {

	@JsonName(value="name")
	private String name;
	@JsonName(value="score")
	private int score;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
