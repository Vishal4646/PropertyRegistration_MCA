package com.pdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.security.CodeSource;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.ProviderException;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.NoSuchElementException;

import sun.security.pkcs11.SunPKCS11;

import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfSignatureAppearance;
import com.lowagie.text.pdf.PdfStamper;

/**
 * 
 * @author Jan Peter Stotz
 * 
 */
public class DigitalSignature {

	private static PrivateKey privateKey;

	private static Certificate[] certificateChain;

	// private static ResourceBundle bundle =
	// ResourceBundle.getBundle("strings");

	private static String PRODUCTNAME = "jPdfSign";

	private static String VERSION = "0.3.1";

	private static String JAR_FILENAME = "jPdfSign.jar";

	public static void main(String[] args) {
		String version = System.getProperty("java.version");
		System.out.println(version);
		String pkcs12FileName = "D:/work/tmp/signature.p12";
		String pdfInputFileName = "D:/work/tmp/a.pdf";
		String pdfOutputFileName = "D:/work/tmp/output1.pdf";
		DigitalSignature.process(pkcs12FileName, pdfInputFileName,
				pdfOutputFileName);

	}

	public static void process(String pkcs12FileName, String pdfInputFileName,
			String pdfOutputFileName) {
		// for (int i = 0; i < args.length; i++) {
		// System.out.println("arg[" + i + "] :" + args[i]);
		// }
		// if (args.length < 2)
		// showUsage();

		try {

			boolean usePKCS12 = !(pkcs12FileName.equals("-PKCS11"));

			System.out.println("");
			System.out.println("pdfInputFileName : " + pdfInputFileName);
			System.out.println("pdfOutputFileName: " + pdfOutputFileName);

			if (usePKCS12)
				readPrivateKeyFromPKCS12(pkcs12FileName, "mypassword");
			else
				readPrivateKeyFromPKCS11();

			PdfReader reader = null;
			try {
				reader = new PdfReader(pdfInputFileName);
			} catch (IOException e) {
				System.err
						.println("An unknown error accoured while opening the input PDF file: \""
								+ pdfInputFileName + "\"");
				e.printStackTrace();
				System.exit(-1);
			}
			FileOutputStream fout = null;
			try {
				fout = new FileOutputStream(pdfOutputFileName);
			} catch (FileNotFoundException e) {
				System.err
						.println("An unknown error accoured while opening the output PDF file: \""
								+ pdfOutputFileName + "\"");
				e.printStackTrace();
				System.exit(-1);
			}
			PdfStamper stp = null;
			try {
				stp = PdfStamper.createSignature(reader, fout, '\0');
				PdfSignatureAppearance sap = stp.getSignatureAppearance();
				sap.setCrypto(privateKey, certificateChain, null,
						PdfSignatureAppearance.SELF_SIGNED);
				sap.setReason("I'm the author");
				sap.setLocation("Lisbon");
				sap.setVisibleSignature(new Rectangle(100, 100, 200, 200), 1,
						null);
				stp.close();
			} catch (Exception e) {
				System.err
						.println("An unknown error accoured while signing the PDF file:");
				e.printStackTrace();
				System.exit(-1);
			}
		} catch (KeyStoreException kse) {
			System.err
					.println("An unknown error accoured while initializing the KeyStore instance:");
			kse.printStackTrace();
			System.exit(-1);
		}
	}

	public static String signDocument(String pkcs12FileName,
			String pdfInputFileName, String pdfOutputFileName,
			String certpassword) {
		// for (int i = 0; i < args.length; i++) {
		// System.out.println("arg[" + i + "] :" + args[i]);
		// }
		// if (args.length < 2)
		// showUsage();
		String stattus = "";
		try {

			// String pkcs12FileName = "D:/signature.p12";
			// String pdfInputFileName = "D:/aaa.pdf";
			// String pdfOutputFileName = "D:/output 1.pdf";
			boolean usePKCS12 = !(pkcs12FileName.equals("-PKCS11"));

			System.out.println("");
			System.out.println("pdfInputFileName : " + pdfInputFileName);
			System.out.println("pdfOutputFileName: " + pdfOutputFileName);

			if (usePKCS12)
				readPrivateKeyFromPKCS12(pkcs12FileName, certpassword);
			else
				readPrivateKeyFromPKCS11();

			PdfReader reader = null;
			try {
				reader = new PdfReader(pdfInputFileName);
			} catch (IOException e) {
				System.err
						.println("An unknown error accoured while opening the input PDF file: \""
								+ pdfInputFileName + "\"");
				e.printStackTrace();
				// System.exit(-1);
				stattus = "Error Opening Input PDF";
			}
			FileOutputStream fout = null;
			try {
				fout = new FileOutputStream(pdfOutputFileName);
			} catch (FileNotFoundException e) {
				System.err
						.println("An unknown error accoured while opening the output PDF file: \""
								+ pdfOutputFileName + "\"");
				e.printStackTrace();
				// System.exit(-1);
				stattus = "Error Opening Output PDF";
			}
			PdfStamper stp = null;
			try {
				stp = PdfStamper.createSignature(reader, fout, '\0');
				PdfSignatureAppearance sap = stp.getSignatureAppearance();
				sap.setCrypto(privateKey, certificateChain, null,
						PdfSignatureAppearance.SELF_SIGNED);
				sap.setReason("I'm the author");
				sap.setLocation("Pune India");
				sap.setVisibleSignature(new Rectangle(100, 100, 200, 200), 1,
						null);
				stp.close();
			} catch (Exception e) {
				System.err
						.println("An unknown error accoured while signing the PDF file:");
				e.printStackTrace();
				// System.exit(-1);
				stattus = "Signing Error";
			}
		} catch (KeyStoreException kse) {
			System.err
					.println("An unknown error accoured while initializing the KeyStore instance:");
			kse.printStackTrace();
			// System.exit(-1);
			stattus = "Invalid Keystore";
		}
		return stattus;
	}

	private static void readPrivateKeyFromPKCS11() throws KeyStoreException {
		// Initialize PKCS#11 provider from config file
		String configFileName = getConfigFilePath("pkcs11.cfg");

		Provider p = null;
		try {
			p = new SunPKCS11(configFileName);
			Security.addProvider(p);
		} catch (ProviderException e) {
			System.err
					.println("Unable to load PKCS#11 provider with config file: "
							+ configFileName);
			e.printStackTrace();
			System.exit(-1);
		}
		String pkcs11PIN = "000000";
		System.out.print("Please enter the smartcard pin: ");
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			pkcs11PIN = in.readLine();
			// System.out.println(pkcs11PIN);
			// System.out.println(pkcs11PIN.length());
		} catch (Exception e) {
			System.err
					.println("An unknown error accoured while reading the PIN:");
			e.printStackTrace();
			System.exit(-1);
		}
		KeyStore ks = null;
		try {
			ks = KeyStore.getInstance("pkcs11", p);
			ks.load(null, pkcs11PIN.toCharArray());
		} catch (NoSuchAlgorithmException e) {
			System.err
					.println("An unknown error accoured while reading the PKCS#11 smartcard:");
			e.printStackTrace();
			System.exit(-1);
		} catch (CertificateException e) {
			System.err
					.println("An unknown error accoured while reading the PKCS#11 smartcard:");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			System.err
					.println("An unknown error accoured while reading the PKCS#11 smartcard:");
			e.printStackTrace();
			System.exit(-1);
		}

		String alias = "";
		try {
			alias = (String) ks.aliases().nextElement();
			privateKey = (PrivateKey) ks.getKey(alias, pkcs11PIN.toCharArray());
		} catch (NoSuchElementException e) {
			System.err
					.println("An unknown error accoured while retrieving the private key:");
			System.err
					.println("The selected PKCS#12 file does not contain any private keys.");
			e.printStackTrace();
			System.exit(-1);
		} catch (NoSuchAlgorithmException e) {
			System.err
					.println("An unknown error accoured while retrieving the private key:");
			e.printStackTrace();
			System.exit(-1);
		} catch (UnrecoverableKeyException e) {
			System.err
					.println("An unknown error accoured while retrieving the private key:");
			e.printStackTrace();
			System.exit(-1);
		}
		certificateChain = ks.getCertificateChain(alias);
	}

	protected static void readPrivateKeyFromPKCS12(String pkcs12FileName,
			String pkcs12Password) throws KeyStoreException {
		// String pkcs12Password = "mypassword";
		KeyStore ks = null;
		System.out.print("Please enter the password for \"" + pkcs12FileName
				+ "\": ");
		try {
			// BufferedReader in = new BufferedReader(new InputStreamReader(
			// System.in));
			// pkcs12Password = in.readLine();
			// pkcs12Password=""
		} catch (Exception e) {
			System.err
					.println("An unknown error accoured while reading the password:");
			e.printStackTrace();
			System.exit(-1);
		}

		try {
			ks = KeyStore.getInstance("pkcs12");
			ks.load(new FileInputStream(pkcs12FileName),
					pkcs12Password.toCharArray());
		} catch (NoSuchAlgorithmException e) {
			System.err
					.println("An unknown error accoured while reading the PKCS#12 file:");
			e.printStackTrace();
			System.exit(-1);
		} catch (CertificateException e) {
			System.err
					.println("An unknown error accoured while reading the PKCS#12 file:");
			e.printStackTrace();
			System.exit(-1);
		} catch (FileNotFoundException e) {
			System.err.println("Unable to open the PKCS#12 keystore file \""
					+ pkcs12FileName + "\":");
			System.err
					.println("The file does not exists or missing read permission.");
			// System.exit(-1);
		} catch (IOException e) {
			System.err
					.println("An unknown error accoured while reading the PKCS#12 file:");
			e.printStackTrace();
			// System.exit(-1);
		}
		String alias = "";
		try {
			alias = (String) ks.aliases().nextElement();
			privateKey = (PrivateKey) ks.getKey(alias,
					pkcs12Password.toCharArray());
		} catch (NoSuchElementException e) {
			System.err
					.println("An unknown error accoured while retrieving the private key:");
			System.err
					.println("The selected PKCS#12 file does not contain any private keys.");
			e.printStackTrace();
			// System.exit(-1);
		} catch (NoSuchAlgorithmException e) {
			System.err
					.println("An unknown error accoured while retrieving the private key:");
			e.printStackTrace();
			// System.exit(-1);
		} catch (UnrecoverableKeyException e) {
			System.err
					.println("An unknown error accoured while retrieving the private key:");
			e.printStackTrace();
			// System.exit(-1);
		}
		certificateChain = ks.getCertificateChain(alias);
	}

	protected static String getConfigFilePath(String configFilename) {
		CodeSource source = DigitalSignature.class.getProtectionDomain()
				.getCodeSource();
		URL url = source.getLocation();

		String jarPath = URLDecoder.decode(url.getFile());
		File f = new File(jarPath);
		try {
			jarPath = f.getCanonicalPath();
		} catch (IOException e) {
		}
		if (!f.isDirectory()) {
			f = new File(jarPath);
			jarPath = f.getParent();
		}
		System.out.println(jarPath);
		if (jarPath.length() > 0) {
			return jarPath + File.separator + configFilename;
		} else
			return configFilename;
	}

	public static void showUsage() {
		System.out.println("jPdfSign v" + VERSION
				+ " by Jan Peter Stotz - jpstotz@gmx.de\n");
		System.out.println(PRODUCTNAME + " usage:");
		System.out
				.println("\nFor using a PKCS#12 (.p12) file as signature certificate and private key source:");
		System.out.print("\tjava -jar " + JAR_FILENAME);
		System.out
				.println(" pkcs12FileName pdfInputFileName pdfOutputFileName");
		System.out
				.println("\nFor using a PKCS#11 smartcard as signature certificate and private key source:");
		System.out.print("\tjava -jar" + JAR_FILENAME);
		System.out.println(" -PKCS11 pdfInputFileName pdfOutputFileName");
		System.exit(0);
	}
}
