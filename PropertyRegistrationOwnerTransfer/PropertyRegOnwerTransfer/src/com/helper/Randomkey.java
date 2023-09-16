package com.helper;


import java.security.SecureRandom;

public class Randomkey {

	static final String AB = "0123456789";
	static SecureRandom rnd = new SecureRandom();

	public static String randomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		if (sb.toString().length()!=len) {
			randomString(len);
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		System.out.println(Randomkey.randomString(4));
	}

}
