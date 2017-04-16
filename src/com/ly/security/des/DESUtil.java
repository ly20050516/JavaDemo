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
	 * ��� byte[]
	 * @param byteArray
	 */
	public static void printByteArray(byte[] byteArray) {
		for (int i = 0; i < byteArray.length; i++) {
			System.out.print(byteArray[i]);
		}
		System.out.println();
	}
	
	/**
	 * DES ����
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
			//1.������Կ����
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
			//2.�����봴��DES��Կ����
			KeySpec desKeySpec = new DESKeySpec(password);
			//3.������Կ
			SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
			//4.���� Cipher ����
			Cipher cipher = Cipher.getInstance("DES");
			//5.��ʼ�� Cipher ����
			SecureRandom secureRandom = new SecureRandom();
			cipher.init(Cipher.ENCRYPT_MODE, secretKey,secureRandom);
			//6.ִ�����յļ��ܲ���
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
	 * DES ����
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
	 * DES ����
	 * @param secretSource
	 * @param password
	 * @return
	 */
	public static byte[] decrpty(byte[] secretSource,byte[] password) {
		
		System.out.println("secretSource length = " + secretSource.length + ";secretSource = " + secretSource);
		System.out.println("password length = " + password.length + ";password = " + password);
	
		
		try {
			//1.������Կ����
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
			//2.����DES��Կ����
			KeySpec desKeySpec = new DESKeySpec(password);
			//3.����Կ������DES��Կ����������Կ
			SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
			//4.���� Cipher
			Cipher cipher = Cipher.getInstance("DES");
			//5.��ʼ��Cipher
			SecureRandom secureRandom = new SecureRandom();
			cipher.init(Cipher.DECRYPT_MODE, secretKey,secureRandom);
			//6.����
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
	 * DES ����
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
