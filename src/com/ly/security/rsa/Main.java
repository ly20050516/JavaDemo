package com.ly.security.rsa;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;


public class Main {

	public static void main(String[] args) {
		
		HashMap<String, Object> map = RSAUtils.generateKeyPair();
		RSAPublicKey rsaPublicKey = (RSAPublicKey) map.get("public");
		RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) map.get("private");
		
		String public_modules = rsaPublicKey.getModulus().toString();
		System.out.println("RSA public key modules = " + public_modules);
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
		
	}

}
