package com.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.constant.ServerConstants;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCode {
	public static void main(String[] args) throws NotFoundException,
			WriterException, IOException {
		System.out.println(generatePOMSQRCode("0011", "D:/"));
		System.out.println(readQrCode("D://0011.png"));
	}

	public static String generatePOMSQRCode(String productId, String filePath) {
		String path = "";
		try {
			path = filePath + "/" + productId + ".png";
			createQrCode(productId, path);
			return path;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}

	public static void createQrCode(String qrCodeData, String filePath)
			throws WriterException, IOException {
		String charset = "UTF-8"; // or "ISO-8859-1"
		Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		createQRCode(qrCodeData, filePath, charset, hintMap, 200, 200);

		System.out.println("QR Code image created successfully!");

		// return filePath;
	}

	public static String readQrCode(String filePath) throws WriterException,
			IOException, NotFoundException {
		String charset = "UTF-8"; // or "ISO-8859-1"
		Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		String returnString = readQRCode(filePath, charset, hintMap);
		System.out.println("successfully!:" + returnString);
		return returnString;
	}

	public static void createQRCode(String qrCodeData, String filePath,
			String charset, Map hintMap, int qrCodeheight, int qrCodewidth)
			throws WriterException, IOException {

		BitMatrix matrix = new MultiFormatWriter().encode(
				new String(qrCodeData.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
		MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
				.lastIndexOf('.') + 1), new File(filePath));
	}

	public static String readQRCode(String filePath, String charset, Map hintMap)
			throws FileNotFoundException, IOException, NotFoundException {
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource(
						ImageIO.read(new FileInputStream(filePath)))));
		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
				hintMap);
		return qrCodeResult.getText();
	}
}
