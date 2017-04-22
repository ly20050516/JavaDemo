package com.ly.security.sha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtils {
	public static String sha(String string,String algorithm) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			byte[] result = messageDigest.digest(string.getBytes());
			System.out.println(algorithm + "£ºresult length = " + result.length);
			StringBuilder shaBuilder = new StringBuilder();
			for(byte b : result) {
				String tmpString = Integer.toHexString(b & 0xff);
				if(tmpString.length() == 1) {
					tmpString = "0" + tmpString;
				}
				shaBuilder.append(tmpString);				
			}
			return shaBuilder.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String sha(String string) {
		return sha(string,"sha-1");
	}
}
