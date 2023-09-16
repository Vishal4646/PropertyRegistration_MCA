package com.test;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratePDF {

	private static String USER_PASS = "Hello123";

	private static String OWNER_PASS = "Owner123";
	
	
	public static void main(String[] args) {
		try {
			
			OutputStream file = new FileOutputStream(new File("D://Test.pdf"));

			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, file);

			writer.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(),
					PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);

			document.open();
			document.add(new Paragraph("Hello World, iText"));
			document.add(new Paragraph(new Date().toString()));

			document.close();
			file.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}