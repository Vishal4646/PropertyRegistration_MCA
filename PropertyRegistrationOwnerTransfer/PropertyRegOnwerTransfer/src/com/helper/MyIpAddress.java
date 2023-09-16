package com.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyIpAddress {

	public static void main1(String a[]) {

		try {
			InetAddress ipAddr = InetAddress.getLocalHost();
			System.out.println(ipAddr.getHostAddress());
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		}
	}
public static void main(String[] args) throws IOException {
	checkAvailableAddress("192.168.0.119");
}
	public static String checkAvailableAddress(String ipaddress) throws IOException {
		String subnet = ipaddress;
		Process p = Runtime.getRuntime().exec("ping "+subnet);
		BufferedReader inputStream = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		String ans = "Yes";
		String s = "";
		while ((s = inputStream.readLine()) != null) {
			if (s.contains("unreachable") || s.contains("try again")) {
				ans = "No";
				break;
			}
		}
		System.out.println(ans);
		return ans;
	}

	public static String getIpAddress() {
		String ipaddress = "";

		try {
			InetAddress ipAddr = InetAddress.getLocalHost();
			ipaddress = ipAddr.getHostAddress();
			System.out.println(ipAddr.getHostAddress());
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		}
		return ipaddress;
	}

}