package com.ly.security.sha;

public class Main {

	public static void main(String[] args) {
		String resultString = SHAUtils.sha("����ҽ��� SHA ����");
		System.out.println(resultString);

		String testString = "����ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ��������ҽ��� SHA ����";
		String[] shaStrings = { "sha", "sha-1", "sha-224", "sha-256",
				"sha-384", "sha-512" };

		for (String algorithm : shaStrings) {
			resultString = SHAUtils.sha(testString, algorithm);
			System.out.println(resultString);
		}
	}

}
