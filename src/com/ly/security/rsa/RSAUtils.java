package com.ly.security.rsa;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAUtils {
	
	public static KeyPair generateKeyPair(int length) {
		
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(length);
			return keyPairGenerator.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static HashMap<String, Object> generateKeyPair() {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(1024);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("public", rsaPublicKey);
			map.put("private", rsaPrivateKey);
			return map;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static RSAPublicKey getRsaPublicKey(String modules,String exponent) {
		
		try{
			BigInteger b1 = new BigInteger(modules);
			BigInteger b2 = new BigInteger(exponent);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPublicKeySpec keySpec = new RSAPublicKeySpec(b1, b2);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static RSAPrivateKey getRsaPrivateKey(String modules,String exponent) {
		
		try{
			BigInteger b1 = new BigInteger(modules);
			BigInteger b2 = new BigInteger(exponent);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(b1, b2);
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static byte[] encryptByRsaPublicKey(String dataSource,RSAPublicKey rsaPublicKey){
		
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
			int module_len = rsaPublicKey.getModulus().bitLength() / 8 - 11;
			System.out.println("encryptByRsaPublicKey module_len = " + module_len);
			byte[] data = dataSource.getBytes();
			System.out.println("encryptByRsaPublicKey data.length = " + data.length);
			if(data.length <= module_len) {
				return cipher.doFinal(data);
			}
			int group = data.length % module_len == 0 ? data.length / module_len : data.length / module_len + 1;
			System.out.println("encryptByRsaPublicKey group = " + group);
			int en_data_len = data.length * 2;
			int en_data_offset = 0;
			byte[] enData = new byte[en_data_len];			
			
			
			for(int i = 0;i < group - 1;i ++){
				int start = i * module_len;
				byte[] dest = new byte[module_len];
				System.arraycopy(data, start, dest, 0, module_len);
				dest = cipher.doFinal(dest);
				System.out.println("encryptByRsaPublicKey dest.length = " + dest.length);
				if(en_data_offset + dest.length > en_data_len) {
					
					//À©ÈÝ
					int new_en_data_len = en_data_len + data.length;
					System.out.println("À©ÈÝ: " + new_en_data_len);
					byte[] tmp = new byte[en_data_len];
					System.arraycopy(enData, 0, tmp, 0, en_data_len);
					enData = new byte[new_en_data_len];
					System.arraycopy(tmp, 0, enData, 0, en_data_len);
					en_data_len = new_en_data_len;
				}
				
				System.arraycopy(dest, 0, enData, en_data_offset, dest.length);
				en_data_offset += dest.length;
			}
			byte[] dest = new byte[data.length - (group - 1) * module_len];
			System.arraycopy(data, (group - 1) * module_len, dest, 0, data.length - (group - 1) * module_len);
			dest = cipher.doFinal(dest);
			System.out.println("encryptByRsaPublicKey dest.length = " + dest.length);
			int new_en_data_len = en_data_offset + dest.length;
			byte[] tmp = new byte[new_en_data_len];
			System.arraycopy(enData, 0, tmp, 0, en_data_offset);
			System.arraycopy(dest, 0, tmp, en_data_offset, dest.length);

			return tmp;
			
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
	
	public static byte[] decryptByRsaPrivateKey(byte[] enData,RSAPrivateKey rsaPrivateKey) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
			int module_len = rsaPrivateKey.getModulus().bitLength() / 8;
			System.out.println("decryptByRsaPrivateKey module_len = " + module_len);
			if(enData.length < module_len) {
				return cipher.doFinal(enData);
			}
			
			int group = enData.length / module_len;
			System.out.println("decryptByRsaPrivateKey group = " + group);
			byte[] deData = new byte[enData.length];
			int de_data_off = 0;
			for(int i = 0;i < group;i ++) {
				byte[] dest = new byte[module_len];
				System.arraycopy(enData, i * module_len, dest, 0, module_len);
				dest = cipher.doFinal(dest);
				System.arraycopy(dest, 0, deData, de_data_off, dest.length);
				de_data_off += dest.length;
			}
			
			byte[] tmp = new byte[de_data_off];
			System.arraycopy(deData, 0, tmp, 0, de_data_off);
			return tmp;
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
