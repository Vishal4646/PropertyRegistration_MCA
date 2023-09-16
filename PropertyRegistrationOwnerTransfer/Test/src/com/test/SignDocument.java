package com.test;

import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

//import com.itextpdf.awt.geom.Rectangle2D;
//import com.itextpdf.text.pdf.PdfException;
import com.qoppa.pdf.PDFException;
import com.qoppa.pdf.SigningInformation;
import com.qoppa.pdf.form.SignatureField;
import com.qoppa.pdfSecure.PDFSecure;


 
public class SignDocument 
{
 
    public static void main (String [] args)
    {
    	
    	try{
    	PDFSecure pdfDoc = new PDFSecure ("D:/Test.pdf", null);
    	 
    	// Load the keystore that contains the digital id to use in signing
    	FileInputStream pkcs12Stream = new FileInputStream ("D:/signature.pfx");
    	KeyStore store = KeyStore.getInstance("PKCS12");
    	store.load(pkcs12Stream, "mypassword".toCharArray());
    	pkcs12Stream.close();
    	 
    	// Create signing information
    	SigningInformation signInfo = new SigningInformation (store, "myalias", "mypassword");
    	 
    	// Create signature field on the first page
    	Rectangle2D signBounds = new Rectangle2D.Double (36, 36, 144, 48);
    	SignatureField signField = pdfDoc.addSignatureField(0, "signature", signBounds);
    	signField.setRequired(true);
    	signField.setPrintable(true);
    	// Apply digital signature
    	pdfDoc.signDocument(signField, signInfo);
    	 
    	// Save the document
    	pdfDoc.saveDocument ("D:/output1.pdf");
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    	
    }
}