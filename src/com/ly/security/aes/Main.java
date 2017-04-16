package com.ly.security.aes;


public class Main {

	public static void main(String[] args) {

		String dataSource = "对这段数据进行加密";
		String password = "1234567812345678123456781234567812345678123456781234567812345678123456781234567812345678123456781234567812345678123456781234567812345678123456781234567812345678123456781234567812345678123456781234567812345678123456781234567812345678123456781234567812345678";
		
		byte[] result = AESUtil.encrpty(dataSource, password);
		System.out.println("AES encrpty result length = " + result.length + ";");
		AESUtil.printByteArray(result);
		
		System.out.println(new String(result));
		
		result = AESUtil.decrpty(result, password);
		
		System.out.println(new String(result));
	}

}
