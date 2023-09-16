package com.test;
import com.java4less.pdf.signature.PDFSignatureFacade;

public class SignatureTest {

	public static void main(String[] args) {
		PDFSignatureFacade facade = new PDFSignatureFacade();
		
		if (args.length!=4) System.err.println("Usage: p12file password inputfile outputfile");
		
		try {		
			facade.sign("D:/signature.pfx","mypassword","D:/filled_signature_doc.pdf","out.pdf",true,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
