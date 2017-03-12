package com.ly.generic;

import java.awt.print.Paper;



public class Main {

	public static void main(String[] args) {

		GenericBookBox<Paper, Book> genericBookBox = new GenericBookBox<Paper, Book>();
		for(int i = 0;i < 100;i ++) {
			Papper papper = new Papper();
			papper.content = "i " + "th";
			genericBookBox.input(papper);
		}
		System.out.println(genericBookBox.process());
	}

}
