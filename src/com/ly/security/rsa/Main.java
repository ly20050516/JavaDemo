package com.ly.security.rsa;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.HashMap;


public class Main {

	public static void main(String[] args) {
		
		HashMap<String, Object> map = RSAUtils.generateKeyPair();
		RSAPublicKey rsaPublicKey = (RSAPublicKey) map.get("public");
		RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) map.get("private");
		
		String public_modules = rsaPublicKey.getModulus().toString();
		System.out.println("RSA public key length = " + public_modules.length() + ";modules = " + public_modules);
		System.out.print("RSA public key modules bit count = " + rsaPublicKey.getModulus().bitCount() + ";");
		System.out.println("RSA public key modules bit length = " + rsaPublicKey.getModulus().bitLength());
		String private_module = rsaPrivateKey.getModulus().toString();
		System.out.println("RSA private key modules = " + private_module);
		
		String public_exponent = rsaPublicKey.getPublicExponent().toString();
		System.out.println("RSA public key exponents = " + public_exponent);
		String private_exponent = rsaPrivateKey.getPrivateExponent().toString();
		System.out.println("RSA private key exponents = " + private_exponent);
		
		System.out.println();
		System.out.println("===============================================================");
		System.out.println();
		RSAPublicKey publicKey = RSAUtils.getRsaPublicKey(public_modules, public_exponent);
		RSAPrivateKey privateKey = RSAUtils.getRsaPrivateKey(private_module, private_exponent);
		
		public_modules = publicKey.getModulus().toString();
		System.out.println("RSA public key modules = " + public_modules);
		private_module = privateKey.getModulus().toString();
		System.out.println("RSA private key modules = " + private_module);
		
		public_exponent = publicKey.getPublicExponent().toString();
		System.out.println("RSA public key exponents = " + public_exponent);
		private_exponent = privateKey.getPrivateExponent().toString();
		System.out.println("RSA private key exponents = " + private_exponent);
		
		long t1 = System.currentTimeMillis();
		byte[] encrptyData = RSAUtils.encryptByRsaPublicKey(""
				+ "�볢�Խ��м����볢�Խ��м����볢�Խ��м����볢�Խ��м����볢�Խ��м���"
				+ "�볢�Խ��м����볢�Խ��м����볢�Խ��м����볢�Խ��м����볢�Խ��м���"
				+ "�볢�Խ��м����볢�Խ��м����볢�Խ��м����볢�Խ��м����볢�Խ��м���"
				+ "�볢�Խ��м����볢�Խ��м����볢�Խ��м����볢�Խ��м����볢�Խ��м���"
				+ "�볢�Խ��м����볢�Խ��м����볢�Խ��м����볢�Խ��м����볢�Խ��м���"
				+ "�볢�Խ��м����볢�Խ��м����볢�Խ��м����볢�Խ��м����볢�Խ��м���"
				+ "�볢�Խ��м����볢�Խ��м����볢�Խ��м����볢�Խ��м����볢�Խ��м���"
				+ "�볢�Խ��м����볢�Խ��м����볢�Խ��м����볢�Խ��м����볢�Խ��м���"
				+ "�볢�Խ��м����볢�Խ��м���", publicKey);
		System.out.println("do encrpty data cost " + (System.currentTimeMillis() - t1));
		
		System.out.println("encrptyData length = " + encrptyData.length);
		String strEnDataString = Base64.getEncoder().encodeToString(encrptyData);
		System.out.println("Base64 string = " + strEnDataString);
		
		encrptyData = Base64.getDecoder().decode(strEnDataString);
		t1 = System.currentTimeMillis();
		byte[] decrptyData = RSAUtils.decryptByRsaPrivateKey(encrptyData, rsaPrivateKey);
		System.out.println("do descrpyt data cost " + (System.currentTimeMillis() - t1));
		System.out.println("descrptyData length = " + decrptyData.length);
		System.out.println(new String(decrptyData));
		
		
	}

}
