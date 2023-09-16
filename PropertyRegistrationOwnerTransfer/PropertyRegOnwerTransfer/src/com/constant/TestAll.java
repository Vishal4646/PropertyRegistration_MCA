package com.constant;

import java.security.Key;
import java.security.Provider;
import java.security.Security;

import com.algo.AES;
import com.algo.RSA;
import com.helper.TestFileDemo;
import com.pdf.DigitalSignature;

public class TestAll {
	public static void main(String[] args) {
		int testCase = 1;
		// Print all Security Algorithms START
		for (Provider provider : Security.getProviders()) {
			System.out.println(provider.getName());
			for (String key : provider.stringPropertyNames())
				System.out.println("\t" + key + "\t"
						+ provider.getProperty(key));
		}
		// Print all Security Algorithms END
		
		
		// Digital Signature Start
		//keytool -genkeypair -keystore D:/signature.p12 -storepass mypassword -storetype pkcs12 -alias myalia44s -dname "cn=John Smith, ou=Google, o=Google, c=US"
		// you can generate .p12 certificate using the above command 
		 String pkcs12FileName = "D:/temp/signature.p12";
		 String pdfInputFileName = "D:/temp/a.pdf";
		 String pdfOutputFileName = "D:/temp/output1.pdf";
		 DigitalSignature.process(pkcs12FileName, pdfInputFileName,
		 pdfOutputFileName);
		// Digital Signature END

		// File Encryption and Decryption Test Start
		String AESKey = "ABCD1234ABCD1234";
		String inputPDF = pdfInputFileName;
		String outputPDF = ServerConstants.LOCAL_UPLOAD + "/temp.pdf";

		AES aes = new AES();
		Key key;
		try {
			key = aes.generateKey(AESKey);
			// System.out.println(key.toString());
			byte[] fileContent = TestFileDemo.readFileDataInByte(inputPDF);
			byte[] enc = aes.encryptUsingKey(fileContent, key);
			byte[] decdata = aes.decryptUsingKey(enc, key);
			TestFileDemo.writByteDataToFile(outputPDF, decdata);
			System.out.println(decdata.length);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// File Encryption and Decryption Test End

		
		// RSA Start
		
		RSA rsa = new RSA();
		try {
			String [] a = rsa.getNewKeyPair();
//			String [] b = obj.getNewKeyPair();
			System.out.println("Public:"+a[0]);
			System.out.println("Private:"+a[1]);
			String EncResult1 = rsa.encryptUsingPublic("any message", a[0]);
//			AES.encrypt("Hello", EncResult1);
			String DecResult2 = rsa.decryptUsingPrivate(EncResult1, a[1]);
			System.out.println(DecResult2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// RSA END
		
	}
}
