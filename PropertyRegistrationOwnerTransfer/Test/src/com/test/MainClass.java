package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;

import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfSignatureAppearance;
import com.lowagie.text.pdf.PdfStamper;

public class MainClass {
  public static void main(String[] args) throws Exception {
	  System.out.println("hello");
    PdfReader reader;
    KeyStore ks = KeyStore.getInstance("pkcs12");
    ks.load(new FileInputStream("D:/signature.pfx"), "mypassword".toCharArray());
    FileInputStream fis = new FileInputStream(new File("D:/signature.pfx"));
    PrivateKey key = (PrivateKey) ks.getKey("myalias", "mypassword".toCharArray());
    Certificate[] chain = ks.getCertificateChain("myalias");
    reader = new PdfReader("D:/filled_signature_doc.pdf","".getBytes());
    FileOutputStream os = new FileOutputStream("D:/1.pdf");
    PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0');
    PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
    appearance.setCrypto(key, chain, null, PdfSignatureAppearance.SELF_SIGNED);
//    appearance.setReason("personal");
//    appearance.setLocation("Foobar");
//    appearance.setVisibleSignature("yoursig");
    stamper.close();
  }

}