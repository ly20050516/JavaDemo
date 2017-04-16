package com.ly.security.aes;


public class Main {

	public static void main(String[] args) {

		String dataSource = "对这段数据进行加密对这段数据进行加密对这段数据进行加密对这段数据进行加密对这段数据进行加密对这段数据进行加密对这段数据进行加密对这段数据进行加密";
		String password = "1234567812345678123456781234567812345678123456781234567812345678123456781234567812345678123456781234567812345678123456781234567812345678123456781234567812345678123456781234567812345678123456781234567812345678123456781234567812345678123456781234567812345678";
		
		byte[] result = AESUtil.encrpty(dataSource, password);
		System.out.println("AES encrpty result length = " + result.length + ";");
		AESUtil.printByteArray(result);
		
		System.out.println(new String(result));
		
		result = AESUtil.decrpty(result, password);
		
		System.out.println(new String(result));
		
		byte []contentString = new byte[]{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','b'};
		String password2 = "1234567812345678";
		result = AESUtil.encrpty2(contentString, password2.getBytes());
		System.out.println("result = " + new String(result));
	}

}
