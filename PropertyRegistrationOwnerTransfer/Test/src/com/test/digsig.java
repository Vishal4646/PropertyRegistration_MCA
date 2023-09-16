package com.test;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.*;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import com.itextpdf.text.pdf.*;
import java.io.FileInputStream;
import java.security.cert.Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class digsig {

//	public static void main(String[] args) throws IOException,
//			DocumentException {
//		Document document = new Document();
//		KeyStore ks = null;
//		try {
//			ks = KeyStore.getInstance(KeyStore.getDefaultType());
//		} catch (KeyStoreException ex) {
//			Logger.getLogger(SEED.class.getName()).log(Level.SEVERE, null, ex);
//		}
//		try {
//			ks.load(new FileInputStream(
//					"C:/Program Files/Java/jdk1.6.0/bin/.keystore"), "a"
//					.toCharArray());
//		} catch (NoSuchAlgorithmException ex) {
//			Logger.getLogger(SEED.class.getName()).log(Level.SEVERE, null, ex);
//		} catch (CertificateException ex) {
//			Logger.getLogger(SEED.class.getName()).log(Level.SEVERE, null, ex);
//		}
//		PrivateKey key = null;
//		try {
//			key = (PrivateKey) ks.getKey("foobar", "a".toCharArray());
//		} catch (KeyStoreException ex) {
//			Logger.getLogger(SEED.class.getName()).log(Level.SEVERE, null, ex);
//		} catch (NoSuchAlgorithmException ex) {
//			Logger.getLogger(SEED.class.getName()).log(Level.SEVERE, null, ex);
//		} catch (UnrecoverableKeyException ex) {
//			Logger.getLogger(SEED.class.getName()).log(Level.SEVERE, null, ex);
//		}
//		Certificate[] chain = null;
//		try {
//			chain = ks.getCertificateChain("myalias");
//		} catch (KeyStoreException ex) {
//			Logger.getLogger(SEED.class.getName()).log(Level.SEVERE, null, ex);
//		}
//		PdfReader reader = new PdfReader("C:/../a.pdf");
//		FileOutputStream os = new FileOutputStream("Signed.pdf");
//		PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0');
//		PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
//		appearance.setCrypto(key, chain, null,
//				PdfSignatureAppearance.CERTIFIED_NO_CHANGES_ALLOWED);
//		appearance.setReason("It's personal.");
//		appearance.setLocation("Foobar");
//		appearance.setVisibleSignature(new Rectangle(160, 732, 232, 780), 1,
//				null);
//		stamper.close();
//		document.close();
//	}

	
	public static void main(String[] args) throws SignatureException, IOException, DocumentException {
		boolean b=signPdf("pkcs12" ,new File( "D:/signature.dfx"), "mypassword".toCharArray(), "mypassword".toCharArray(), new File("D:/filled_signature_doc.pdf"), "D:/output.pdf", null, "pune", "useraaaaa@123", "owneraaaaa@123");
		System.out.println(b);
//		System.out.println(new File("D:/original.pdf").getAbsolutePath());
//		PdfReader pdfReader = new PdfReader(new File("D:/filled_signature_doc.pdf").getAbsolutePath(),"".getBytes());
//		System.out.println("Is the PDF Encrypted "+pdfReader.isEncrypted());
//		
//			                        System.out.println("File is opened with full permissions : "+pdfReader.isOpenedWithFullPermissions());
//		
//			                        System.out.println("File length is : "+pdfReader.getFileLength());
//		
//			System.out.println("File is tampered? "+pdfReader.isTampered());
//		
//			                        pdfReader.close();
	}
	public static final boolean signPdf(String keystoreType, // Keystore type.
																// Can be
																// KeyStore.getDefaultType()
			File keystore, // The keystore file
			char[] keystorePassword, // the password to the keystore
			char[] privateKeyPassword, // the passwort of the private key
			File pdfFile, // the PDF input File
			String pdfOutfile, // The whole filename of the PDF output File
			String reason, // the reason for this signing
			String location, // and the location where it was signed
			String userPassword, // Username for encryption
			String ownerPassword // Password used for encryption
	) throws SignatureException, IOException, DocumentException {
		try {
			PrivateKey key = null;
			Certificate[] chain = null;
			if (!keystore.exists())
				return false;
			if (reason != null) {
				KeyStore ks = KeyStore.getInstance(keystoreType);
				FileInputStream fis = new FileInputStream(keystore);
				try {
					ks.load(fis, keystorePassword);
				} finally {
					fis.close();
				}
				String alias = (String) ks.aliases().nextElement();
				key = (PrivateKey) ks.getKey(alias, privateKeyPassword);
				chain = ks.getCertificateChain(alias);
			}
			System.out.println(pdfFile.getAbsolutePath());
			PdfReader pdfReader = new PdfReader(pdfFile.getAbsolutePath(),"".getBytes());
			PdfReader.unethicalreading = true;
			File outputFile = new File(pdfOutfile);
			PdfStamper pdfStamper;
			if (reason != null) {
				
				pdfStamper = PdfStamper.createSignature(pdfReader, null, '\0',
						outputFile);
				PdfSignatureAppearance sap = pdfStamper
						.getSignatureAppearance();
//				sap.setCrypto(key, chain, null,
//						PdfSignatureAppearance.CERTIFIED_FORM_FILLING);
//				sap.setCryptoDictionary(PdfDictionary.);
				sap.setReason(reason);
				sap.setLocation(location);
//				sap.close();
			} else {
				FileOutputStream fos = new FileOutputStream(outputFile);
				pdfStamper = new PdfStamper(pdfReader, fos);
			}
			if (userPassword != null){
				pdfStamper.setEncryption(true, userPassword, ownerPassword,
						PdfWriter.AllowPrinting | PdfWriter.AllowCopy
								| PdfWriter.AllowScreenReaders
								| PdfWriter.AllowDegradedPrinting);
//				pdfStamper.setEncryption("Vibhu".getBytes(), "Vibhu@123456789".getBytes(),PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);

			}
			
			pdfStamper.setFormFlattening(true);
			pdfStamper.close();
			return true;
		} catch (KeyStoreException key) {
//			throw new SignatureException(key);
			key.printStackTrace();
		} catch (NoSuchAlgorithmException nsa) {
//			throw new SignatureException(nsa);
			nsa.printStackTrace();
		} catch (CertificateException ce) {
//			throw new SignatureException(ce);
			ce.printStackTrace();
		} catch (UnrecoverableKeyException ur) {
//			throw new SignatureException(ur);
			ur.printStackTrace();
		}
		return false;
	}

}