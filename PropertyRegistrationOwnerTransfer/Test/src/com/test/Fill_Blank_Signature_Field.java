package com.test;


import java.io.IOException;
import java.util.List;

import com.gnostice.pdfone.PDFOne;
import com.gnostice.pdfone.PdfDocument;
import com.gnostice.pdfone.PdfException;
import com.gnostice.pdfone.PdfFormField;
import com.gnostice.pdfone.PdfFormSignatureField;
import com.gnostice.pdfone.PdfSignature;
public class Fill_Blank_Signature_Field {

  public static void main(String[] args) throws IOException, PdfException {

	  PdfSignature pdfSignature =
		        new PdfSignature("D:/signature.pfx",  // pathname of PFX 
		                         "mypassword",                      // password for PFX
		                         "I approve this document",       // description
		                         "Moonbase, Moon",                // reason
		                         "test@example.com",              // contact info
		                         1);                              // page number

		    // Load a document with blank form fields
		    PdfDocument doc = new PdfDocument();

		    // Load a PDF document with a blank signature
		    doc.load("D:/smallseotools-1522246468.pdf");

		    // Retrieve a list of all signature fields
		    List fields = doc.getAllFormFieldsOnPage(1, PdfFormField.ALIGNMENT_CENTER);

		    PdfFormSignatureField signatureField;
		    // Iterate the list and fill the signature fields
		    for (int i = 0; i < fields.size(); i++) {
		      signatureField = (PdfFormSignatureField) fields.get(i);
		      if (signatureField.isUnsigned()) {
		        signatureField.fill(pdfSignature);
		        break;
		      }
		    }
		  
		    doc.drawLine(10, 18, 200, 18);

		    // Save the document to file
		    doc.save("D:/output.pdf");
		 
		    // Close IO resources
		    doc.close();
  }
}