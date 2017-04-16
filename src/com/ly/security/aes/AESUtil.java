package com.ly.security.aes;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class AESUtil {
	
	/**
	 * 输出 byte[]
	 * @param byteArray
	 */
	public static void printByteArray(byte[] byteArray) {
		for (int i = 0; i < byteArray.length; i++) {
			System.out.print(byteArray[i]);
		}
		System.out.println();
	}
	
	/**
	 * AES 加密
	 * @param dataSource
	 * @param password
	 * @return
	 */
	public static byte[] encrpty(byte[] dataSource,byte[] password) {
		
		System.out.println("dataSource length = " + dataSource.length + ";dataSource = ");
		printByteArray(dataSource);
		System.out.println("password length = " + password.length + ";password = ");
		printByteArray(password);
		
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128, new SecureRandom(password));
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] encodeSecretKey = secretKey.getEncoded();
			System.out.println("encodeSecretKey length = " + encodeSecretKey.length);
			SecretKeySpec secretKeySpec = new SecretKeySpec(encodeSecretKey, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			long t1 = System.currentTimeMillis();
			byte[] result = cipher.doFinal(dataSource);
			System.out.println("AES encrpty cost time : " + (System.currentTimeMillis() - t1));
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * AES 加密
	 * @param dataSource
	 * @param password
	 * @return
	 */
	public static byte[] encrpty(String dataSource,String password) {
		
		System.out.println("dataSource length = " + dataSource.length() + ";dataSource = " + dataSource);
		System.out.println("password length = " + password.length() + ";password = " + password);
		
		
		return encrpty(dataSource.getBytes(), password.getBytes());
	}
	
	/**
	 * AES 解密 
	 * @param secretSource
	 * @param password
	 * @return
	 */
	public static byte[] decrpty(byte[] secretSource,byte[] password) {
		
		System.out.println("secretSource length = " + secretSource.length + ";secretSource = ");
		printByteArray(secretSource);
		System.out.println("password length = " + password.length + ";password = " + password);
		
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128, new SecureRandom(password));
			SecretKey secretKey = keyGenerator.generateKey();
			byte[]encodeSecretKey = secretKey.getEncoded();
			SecretKeySpec secretKeySpec = new SecretKeySpec(encodeSecretKey, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			
			long t1 = System.currentTimeMillis();
			byte[] result = cipher.doFinal(secretSource);
			System.out.println("AES decrpty cost time = " + (System.currentTimeMillis() - t1));
			
			return result;
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * AES 解密
	 * @param secretSource
	 * @param password
	 * @return
	 */
	public static byte[] decrpty(byte[] secretSource,String password) {
		
		System.out.println("secretSource length = " + secretSource.length + ";secretSource = ");
		printByteArray(secretSource);
		System.out.println("password length = " + password.length() + ";password = " + password);
		
		return decrpty(secretSource, password.getBytes());
	}
	
	public static byte[] encrpty2(byte[] dataSource,byte[] password) {
		
		
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(password,"AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			return cipher.doFinal(dataSource);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
