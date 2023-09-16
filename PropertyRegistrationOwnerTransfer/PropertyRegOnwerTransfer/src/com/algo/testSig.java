/*
 * Copyright (c) 1995-1997 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL purposes and without
 * fee is hereby granted provided that this copyright notice
 * appears in all copies. Please refer to the file "copyright.html"
 * for further important copyright and licensing information.
 *
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */
package com.algo;

import java.io.*;
import java.security.*;

class testSig {

	public static void main(String[] args) {

		/* Test generating and verifying a DSA signature */

		try {

			/* generate a key pair */

			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
			keyGen.initialize(1024, new SecureRandom());
			KeyPair pair = keyGen.generateKeyPair();

			/* create a Signature object to use for signing and verifying */

			Signature dsa = Signature.getInstance("SHA/DSA");

			/* initialize the Signature object for signing */

			PrivateKey priv = pair.getPrivate();

			dsa.initSign(priv);

			/* Update and sign the data */
			String filepath = "D:/temp/a.pdf";
			FileInputStream fis = new FileInputStream(filepath);
			byte b;
			while (fis.available() != 0) {
				b = (byte) fis.read();
				dsa.update(b);
			}
			;

			fis.close();

			/* Now that all the data to be signed has been read in, sign it */
			byte[] sig = dsa.sign();

			/* Verify the signature */

			/* Initialize the Signature object for verification */
			PublicKey pub = pair.getPublic();
			dsa.initVerify(pub);

			/* Update and verify the data */

			fis = new FileInputStream(filepath);
			while (fis.available() != 0) {
				b = (byte) fis.read();
				dsa.update(b);
			}
			;

			fis.close();

			boolean verifies = dsa.verify(sig);

			System.out.println("signature verifies: " + verifies);

		} catch (Exception e) {
			System.err.println("Caught exception " + e.toString());
		}
	}
}
