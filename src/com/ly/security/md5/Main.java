package com.ly.security.md5;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		
		String resultString = MD5Utils.md5("请对我进行md5加密，谢谢"
				+ "请对我进行md5加密，谢谢"
				+ "请对我进行md5加密，谢谢"
				+ "请对我进行md5加密，谢谢"
				+ "请对我进行md5加密，谢谢"
				+ "请对我进行md5加密，谢谢"
				+ "请对我进行md5加密，谢谢"
				+ "请对我进行md5加密，谢谢"
				+ "请对我进行md5加密，谢谢"
				+ "请对我进行md5加密，谢谢"
				+ "请对我进行md5加密，谢谢"
				+ "请对我进行md5加密，谢谢"
				+ "请对我进行md5加密，谢谢"
				+ "请对我进行md5加密，谢谢"
				+ "请对我进行md5加密，谢谢"
				+ "请对我进行md5加密，谢谢"
				+ "请对我进行md5加密，谢谢"
				+ "请对我进行md5加密，谢谢"
				+ "请对我进行md5加密，谢谢"
				+ "请对我进行md5加密，谢谢"
				+ "请对我进行md5加密，谢谢"
				+ "请对我进行md5加密，谢谢");
		System.out.println(resultString);
		
		File file = new File("md5.txt");
		resultString = MD5Utils.md5(file);
		System.out.println(resultString);
		resultString = MD5Utils.md5NIO(file);
		System.out.println(resultString);
		
	}

}
