package com.ly.security.sha;

public class Main {

	public static void main(String[] args) {
		String resultString = SHAUtils.sha("请对我进行 SHA 加密");
		System.out.println(resultString);

		String testString = "请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密请对我进行 SHA 加密";
		String[] shaStrings = { "sha", "sha-1", "sha-224", "sha-256",
				"sha-384", "sha-512" };

		for (String algorithm : shaStrings) {
			resultString = SHAUtils.sha(testString, algorithm);
			System.out.println(resultString);
		}
	}

}
