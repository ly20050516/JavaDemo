package com.ly.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexSample {

	private void run() {
		final String line = "This order was placed for QT3000! OK?";
		final String regex = "(\\D*)(\\d+)(.*)";
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(line);
		if(matcher.find()){
			System.out.println(matcher.group(0));
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
			System.out.println(matcher.group(3));
		}else {
			System.out.println("No Match");
		}
	}
	
	/**
	 * zero or more,symbol *
	 */
	private void zeroOrMore() {
		final String lineString = "zzoo";
		final String regex = "(zo*)(zo*)";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(lineString);
		
		if(matcher.find()){
			int count = matcher.groupCount() + 1;
			System.out.println("count:" + count);
			for(int i = 0;i < count;i ++){
				System.out.println(matcher.group(i));
			}
		}else {
			System.out.println("No matcher");
		}
		
	}
	
	/**
	 * one or more,symbol +
	 */
	private void oneOrMore(){
		final String lineString = "zzoo";
		final String regex = "zo+";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(lineString);
		
		if(matcher.find()){
			int count = matcher.groupCount() + 1;
			System.out.println("count:" + count);
			
			for (int i = 0; i < count; i++) {
				System.out.println(matcher.group(i));
			}
		}else {
			System.out.println("NO Matcher");
		}
	}
	
	/**
	 * oblique,symbol /
	 */
	private void oblique() {
	
		final String lineString = "\tThere is a oblique at start and end\t";
		final String regex = "(\t*)(\\D+)(\t)";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(lineString);
		
		if(matcher.find()){
			int count = matcher.groupCount() + 1;
			System.out.println("count:" + count);
			for(int i = 0;i < count;i++){
				System.out.println(matcher.group(i));
			}
		}else {
			System.out.println("No Matcher");
		}
		
	}
	/**
	 * symbol ^
	 */
	private void head() {
		final String lineString = "What's the head\n.and What's the tail";
		final String regex = "^What";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(lineString);
		
		if(matcher.find()){
			int count = matcher.groupCount() + 1;
			System.out.println("count:" + count);
			
			for(int i = 0;i < count;i++){
				System.out.println(matcher.group());
			}
		}
	}
	/**
	 * symbol $
	 */
	private void tail() {
		final String lineString = "What's the tail";
		final String regex = "tail$";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(lineString);
		
		if(matcher.find()){
			int count = matcher.groupCount() + 1;
			System.out.println("count:" + count);
			for(int i = 0;i < count;i ++){
				System.out.println(matcher.group(i));
			}
		}
	}
	
	/**
	 * symbol ?
	 */
	private void zeroOrOne() {
		final String lineString = "dodoes";
		final String regex = "do(es?)";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(lineString);
		
		if(matcher.find()){
			int count = matcher.groupCount() + 1;
			System.out.println("count:" + count);
			for(int i = 0;i < count;i ++){
				System.out.println(matcher.group(i));
			}
		}
	}
	
	/**
	 * symbol .
	 */
	private void anyChar(){
		final String line = "any char";
		final String regex = ".+";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(line);
		
		if(matcher.find()){
			int count = matcher.groupCount() + 1;
			System.out.println("count:" + count);
			for(int i = 0;i < count;i ++){
				System.out.println(matcher.group(i));
			}
		}
	}
	/**
	 * {n}
	 */
	private void justn() {
		final String lineString = "Boob";
		final String regex = "((o){2})";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(lineString);
		
		if(matcher.find()){
			int count = matcher.groupCount() + 1;
			System.out.println("count: " + count);
			for(int i = 0;i < count;i++){
				System.out.println(matcher.group(i));
			}
		}
	}
	
	/**
	 * {n,}
	 */
	
	private void n() {
		final String lineString = "Boob";
		final String regex = "(o{2,})";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(lineString);
		
		if(matcher.find()){
			int count = matcher.groupCount() + 1;
			System.out.println("count: " + count);
			for(int i = 0;i < count;i ++){
				System.out.println(matcher.group(i));
			}
		}
		
	}
	public static void main(String[] args) {

		RegexSample rs = new RegexSample();
		rs.run();
//		rs.zeroOrMore();
//		rs.oblique();
//		rs.oneOrMore();
//		rs.head();
//		rs.tail();
//		rs.zeroOrOne();
//		rs.anyChar();
//		rs.justn();
//		rs.n();
	}

}
