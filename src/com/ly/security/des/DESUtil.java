package com.ly.security.des;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESUtil {

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
	 * DES 加密
	 * @param dataSource
	 * @param password
	 * @return
	 */
	public static byte[] encrypt(byte[] dataSource,byte[] password) {
		
		System.out.println("dataSource length = " + dataSource.length + ";dataSource = ");
		printByteArray(dataSource);
		System.out.println("password length = " + password.length + ";password = ");
		printByteArray(password);
		
		try {
			//1.创建密钥工厂
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
			//2.用密码创建DES密钥描述
			KeySpec desKeySpec = new DESKeySpec(password);
			//3.生成密钥
			SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
			//4.创建 Cipher 对象
			Cipher cipher = Cipher.getInstance("DES");
			//5.初始化 Cipher 对象
			SecureRandom secureRandom = new SecureRandom();
			cipher.init(Cipher.ENCRYPT_MODE, secretKey,secureRandom);
			//6.执行最终的加密操作
			long t1 = System.currentTimeMillis();
			byte[] result = cipher.doFinal(dataSource);
			System.out.println("DES encrpty cost time : " + (System.currentTimeMillis() - t1));
			return result;
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * DES 加密
	 * @param dataSource
	 * @param password
	 * @return
	 */
	public static byte[] encrpty(String dataSource,String password) {
		
		System.out.println("dataSource length = " + dataSource.length() + ";dataSource = " + dataSource);
		System.out.println("password length = " + password.length() + ";password = " + password);
		
		return encrypt(dataSource.getBytes(), password.getBytes());

	}
	
	/**
	 * DES 解密
	 * @param secretSource
	 * @param password
	 * @return
	 */
	public static byte[] decrpty(byte[] secretSource,byte[] password) {
		
		System.out.println("secretSource length = " + secretSource.length + ";secretSource = " + secretSource);
		System.out.println("password length = " + password.length + ";password = " + password);
	
		
		try {
			//1.创建密钥工厂
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
			//2.创建DES密钥描述
			KeySpec desKeySpec = new DESKeySpec(password);
			//3.用密钥工厂将DES密钥描述生成密钥
			SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
			//4.创建 Cipher
			Cipher cipher = Cipher.getInstance("DES");
			//5.初始化Cipher
			SecureRandom secureRandom = new SecureRandom();
			cipher.init(Cipher.DECRYPT_MODE, secretKey,secureRandom);
			//6.解密
			long t1 = System.currentTimeMillis();
			byte[] result = cipher.doFinal(secretSource);
			System.out.println("DES decrpty cost time : " + (System.currentTimeMillis() - t1));
			return result;
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * DES 解密
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
}
