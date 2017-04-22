package com.ly.security.md5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	
	public static String md5(String string) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] result = messageDigest.digest(string.getBytes());
			System.out.println("result length = " + result.length);
			StringBuilder md5Builder = new StringBuilder();
			for(byte b : result) {
				String tmpString = Integer.toHexString(b & 0xff);
				if(tmpString.length() == 1) {
					tmpString = "0" + tmpString;
				}
				md5Builder.append(tmpString);				
			}
			return md5Builder.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String md5(File file) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			FileInputStream fis = new FileInputStream(file);
			
			byte[] buffer = new byte[8192];
			int len = 0;
			while((len = fis.read(buffer)) > 0) {
				messageDigest.update(buffer,0,len);
			}
			
			if(fis != null) {
				fis.close();
			}
			
			byte[] result = messageDigest.digest();
			StringBuilder md5Budiler = new StringBuilder();
			for(byte b : result) {
				String tmp = Integer.toHexString(b & 0xff);
				if(tmp.length() == 1) {
					md5Budiler.append("0").append(tmp);
				}else{
					md5Budiler.append(tmp);
				}
			}
			
			return md5Budiler.toString();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String md5NIO(File file) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			FileInputStream fis = new FileInputStream(file);
			MappedByteBuffer byteBuffer = fis.getChannel().map(MapMode.READ_ONLY, 0, file.length());
			messageDigest.update(byteBuffer);
			byte[] result = messageDigest.digest();
			if(fis != null) {
				fis.close();
			}
			StringBuilder md5Budiler = new StringBuilder();
			for(byte b : result) {
				String tmp = Integer.toHexString(b & 0xff);
				if(tmp.length() == 1) {
					md5Budiler.append("0").append(tmp);
				}else{
					md5Budiler.append(tmp);
				}
			}
			
			return md5Budiler.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
