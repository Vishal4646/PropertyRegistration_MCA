package com.database;

import io.ipfs.api.MerkleNode;
import io.ipfs.cid.Cid;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Key;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.fileupload.FileItem;
import org.openqa.selenium.internal.seleniumemulation.SetTimeout;

import com.algo.AES;
import com.algo.RSA;
import com.constant.ServerConstants;
import com.helper.FileHelper;
import com.helper.MailUtility;
import com.helper.SMSSender;
import com.helper.SimpleCryptoAndroidJava;
import com.helper.StringHelper;
import com.helper.TestFileDemo;
import com.ipfsblockchain.BlockChainIPFS;
import com.ipfsblockchain.FileObjectHelper;
import com.model.DocumentModel;
import com.model.PropertyViewRequestModel;
import com.model.ShareRequestDocModel;
import com.model.ShareRequestOneMailDocModel;
import com.model.SignRequestModel;
import com.model.TransferRecords;
import com.model.UserModel;
import com.model.VerifyRequestModel;
import com.pdf.DigitalSignature;
import com.pdf.Randomkey;

/**
 * 
 * @author Admin
 */
public class ConnectionManager extends DBUtils {
	
	public static BlockChainIPFS br = new BlockChainIPFS();
	
	public static void main(String[] args) {
//		long mill = Long.valueOf("1349333576093");
		long mill = System.currentTimeMillis();
	      
	      System.out.println(mill);
	      
	      Date date = new Date(mill);
	    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
	    formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	    String dateFormatted = formatter.format(date);

	      System.out.println("Sum of x+y = " + dateFormatted);
	      
	      
	      String data = "OldOwnerId=5,NewOwnerId=6,PropertyId=1";
		BlockChainIPFS bl = new BlockChainIPFS();
		//bl.addNewBlock(data, "1");
		//bl.getBlockByHash("QmSr2UxDvQVbAsmxaUaXviXXLKrJJreKH4LLPx6tXkLmCt");
		bl.getBlockHistoryById("1");
		
		//Object o = FileObjectHelper.readObject(ServerConstants.getLastMerkelPath("2"));
		//System.out.println("ob: " + o.toString());
		

//		UserModel loggedInUser = ConnectionManager.getUserNameFromUid("15");
//		HashMap parameters = new HashMap();
//		parameters.put("fid", "39");
//		parameters.put("inline", "false");
//		downloadFile(parameters, loggedInUser, null);

	}
	
	public static String millToDatetime(String mill){
		long milll = Long.valueOf(mill);
		Date date = new Date(milll);
	    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
	    formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	    String dateFormatted = formatter.format(date);
	    return dateFormatted;
	}

	public static void downloadFile(HashMap parameters, UserModel um,
			HttpServletResponse response) {
		downloadFile(parameters, um, response, false);
	}

	public static void downloadFile(HashMap parameters, UserModel um,
			HttpServletResponse response, boolean isSHared) {
		String did = StringHelper.n2s(parameters.get("fid"));
		System.out.println("parameters: " + parameters + ", um: " + um
				+ ", fid: " + did);

//		String inline = StringHelper.n2s(parameters.get("inline"));
		try {
			String filename = "";
			String keystr = "";
			String userId = "";
			if (!isSHared) {

//				List<DocumentModel> list = ConnectionManager
//						.getDocumentDataFromDB(did);
				VerifyRequestModel dm = ConnectionManager.getVerifFileDataFromdid(did);
				filename = dm.getDocName();
				System.out.println("filename1: " + filename);
				keystr = dm.getAeskey();
				userId = dm.getUserid();
				System.out.println("DocData1: " + filename + ", " + userId + ", " + keystr);
			} else {
//				List<DocumentModel> list = ConnectionManager
//						.getRecivedDocument(did);

				VerifyRequestModel dm = ConnectionManager.getVerifFileDataFromdid(did); 
				filename = dm.getDocName();
				System.out.println("filename2: " + filename);
				keystr = dm.getAeskey();
				userId = dm.getUserid();
				System.out.println("DocData2: " + filename + ", " + userId + ", " + keystr);
			}

			// byte[] encdata=dm.getDocData();
			// String filename = dm.getDocName();
//			UserModel signedBy_um = ConnectionManager.getUserNameFromUid(userId);
//			System.out.println("signed by: " + signedBy_um.getFname());
			// HadoopReaderWriter.readFile(filename);
			byte[] encdata = TestFileDemo
					.readFileDataInByte(ServerConstants.FILE_UPLOAD_PATH + "/"
							+ filename);

			AES a = new AES();
			System.out.println("private: " + ServerConstants.keys_path + um.getEmailid() + "private.bin");
			byte[] privateKeyBytes = TestFileDemo.readFileDataInByte(ServerConstants.keys_path + um.getEmailid() + "private.bin");
			String privateKey = new String(privateKeyBytes);
//			System.out.println("privateKey: " + privateKey);
			RSA rsa = new RSA();

			String rsaDecNewAESKey = rsa.decryptUsingPrivate(keystr, privateKey);
			System.out.println("rsaDecNewAESKey: " + rsaDecNewAESKey);

			System.out.println(keystr + "   <- keystr ->  " + rsaDecNewAESKey);
			Key key;

			key = a.generateKey(rsaDecNewAESKey);

			byte[] dec1 = a.decryptUsingKey(encdata, key);
			// session.setAttribute("OTP",otp);
			/*
			 * File f=new File("a123.pdf"); FileOutputStream fos=new
			 * FileOutputStream(f); fos.write(dec1); fos.close();
			 */
			// response.setContentType("application/pdf");
			//
			// if (inline.length() > 0) {
			// response.setHeader("Content-Disposition", "inline;filename="
			// + fid + ".pdf");
			// } else {
			// response.setHeader("Content-Disposition",
			// "attachment;filename=" + fid + ".pdf");
			// }
			// OutputStream output = response.getOutputStream();
			//
			// // for (int i=0; i<dec1.length; i++) {
			// // output.write(dec1[i]);
			// // }
			//
			// output.write(dec1);
			// output.close();

			writeBytes(did, dec1, response, true);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getOnwerDetForBlock(String uid){
		String query = "select * from useraccount where uid = ?";
		List list = DBUtils.getBeanList(UserModel.class, query, uid);
		if(list.size() > 0){
			UserModel um = (UserModel) list.get(0);
			String ret = um.getFname() + "  |  " + um.getEmailid() + "  |  " + um.getMobile() + "  |  " + um.getAdharno();
			return ret;
		}else{
			return "-  |  -  |  -  |  -";
		}
	}

	public static void downloadFile2(HashMap parameters, UserModel um,
			HttpServletResponse response, boolean isSHared) {
		String did = StringHelper.n2s(parameters.get("fid"));
		System.out.println("parameters: " + parameters + ", um: " + um
				+ ", fid: " + did);

//		String inline = StringHelper.n2s(parameters.get("inline"));
		try {
			String filename = "";
			String keystr = "";
			String userId = "";
			if (!isSHared) {

				List<DocumentModel> list = ConnectionManager
						.getDocumentDataFromDB(did);
				DocumentModel dm = (DocumentModel) list.get(0);
				filename = dm.getDocName();
				System.out.println("filename1: " + filename);
				keystr = dm.getAeskey();
				userId = dm.getUserid();
				System.out.println("DocData1: " + filename + ", " + userId + ", " + keystr);
			} else {
				List<DocumentModel> list = ConnectionManager
						.getRecivedDocument(did);

				DocumentModel dm = (DocumentModel) list.get(0);
				filename = dm.getDocName();
				System.out.println("filename2: " + filename);
				keystr = dm.getAeskey();
				userId = dm.getUserid();
				System.out.println("DocData2: " + filename + ", " + userId + ", " + keystr);
			}

			// byte[] encdata=dm.getDocData();
			// String filename = dm.getDocName();
//			UserModel signedBy_um = ConnectionManager.getUserNameFromUid(userId);
//			System.out.println("signed by: " + signedBy_um.getFname());
			// HadoopReaderWriter.readFile(filename);
			byte[] encdata = TestFileDemo
					.readFileDataInByte(ServerConstants.FILE_UPLOAD_PATH + "/"
							+ filename);

			AES a = new AES();
			System.out.println("private: " + ServerConstants.keys_path + um.getEmailid() + "private.bin");
			byte[] privateKeyBytes = TestFileDemo.readFileDataInByte(ServerConstants.keys_path + um.getEmailid() + "private.bin");
			String privateKey = new String(privateKeyBytes);
//			System.out.println("privateKey: " + privateKey);
			RSA rsa = new RSA();

			String rsaDecNewAESKey = rsa.decryptUsingPrivate(keystr, privateKey);
			System.out.println("rsaDecNewAESKey: " + rsaDecNewAESKey);

			System.out.println(keystr + "   <- keystr ->  " + rsaDecNewAESKey);
			Key key;

			key = a.generateKey(rsaDecNewAESKey);

			byte[] dec1 = a.decryptUsingKey(encdata, key);

			writeBytes(did, dec1, response, true);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void viewTransferFile(HashMap parameters, UserModel um,
			HttpServletResponse response, boolean isSHared) {
		String did = StringHelper.n2s(parameters.get("fid"));
		System.out.println("parameters: " + parameters + ", um: " + um
				+ ", fid: " + did);

//		String inline = StringHelper.n2s(parameters.get("inline"));
		try {
			String filename = "";
			String keystr = "";
			String userId = "";
			if (!isSHared) {

				List<DocumentModel> list = ConnectionManager
						.getDocumentDataFromDB(did);
				DocumentModel dm = (DocumentModel) list.get(0);
				filename = dm.getDocName();
				System.out.println("filename1: " + filename);
				keystr = dm.getAeskey();
				userId = dm.getUserid();
				System.out.println("DocData1: " + filename + ", " + userId + ", " + keystr);
			} else {
				List<DocumentModel> list = ConnectionManager
						.getRecivedDocument(did);

				DocumentModel dm = (DocumentModel) list.get(0);
				filename = dm.getDocName();
				System.out.println("filename2: " + filename);
				keystr = dm.getAeskey();
				userId = dm.getUserid();
				System.out.println("DocData2: " + filename + ", " + userId + ", " + keystr);
			}
			UserModel user = getUserNameFromUid(userId);

			// byte[] encdata=dm.getDocData();
			// String filename = dm.getDocName();
//			UserModel signedBy_um = ConnectionManager.getUserNameFromUid(userId);
//			System.out.println("signed by: " + signedBy_um.getFname());
			// HadoopReaderWriter.readFile(filename);
			byte[] encdata = TestFileDemo
					.readFileDataInByte(ServerConstants.FILE_UPLOAD_PATH + "/"
							+ filename);

			AES a = new AES();
			System.out.println("private: " + ServerConstants.keys_path + um.getEmailid() + "private.bin");
			byte[] privateKeyBytes = TestFileDemo.readFileDataInByte(ServerConstants.keys_path + user.getEmailid() + "private.bin");
			String privateKey = new String(privateKeyBytes);
//			System.out.println("privateKey: " + privateKey);
			RSA rsa = new RSA();

			String rsaDecNewAESKey = rsa.decryptUsingPrivate(keystr, privateKey);
			System.out.println("rsaDecNewAESKey: " + rsaDecNewAESKey);

			System.out.println(keystr + "   <- keystr ->  " + rsaDecNewAESKey);
			Key key;

			key = a.generateKey(rsaDecNewAESKey);

			byte[] dec1 = a.decryptUsingKey(encdata, key);

			writeBytes(did, dec1, response, true);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void viewSharedDataFile(ShareRequestDocModel sm,
			HttpServletResponse response) throws Exception {
		System.out.println("fielename: " + sm);
		String filename = sm.getFilename();
		String uid = sm.getSenduserid();
		String aeskeyenc = sm.getAeskey();

		UserModel um = getUserNameFromUid(uid);

		byte[] encData = TestFileDemo
				.readFileDataInByte(ServerConstants.FILE_UPLOAD_PATH + "/"
						+ filename);
		byte[] privatebyte = TestFileDemo.readFileDataInByte(um.getEmailid()
				+ "private.bin");
		String privateKey = new String(privatebyte);
		RSA rsa = new RSA();
		String keystr = rsa.decryptUsingPrivate(aeskeyenc, privateKey);
		AES a = new AES();
		Key key = a.generateKey(keystr);

		byte[] decryptedBytes = a.decryptUsingKey(encData, key);
		try (FileOutputStream fos = new FileOutputStream("temp.pdf")) {
			fos.write(decryptedBytes);
			// fos.close(); There is no more need for this line since you had
			// created the instance of "fos" inside the try. And this will
			// automatically close the OutputStream
		}
		writeBytes(filename, decryptedBytes, response, false);
		return;

	}

	public static void viewSharedDataFile2(VerifyRequestModel dm,
			HttpServletResponse response) throws Exception {
		System.out.println("fielename: " + dm);
		String filename = dm.getDocName();
		String uid = dm.getRuid();
		String aeskeyenc = dm.getAeskey();

		UserModel um = getUserNameFromUid(uid);

		byte[] encData = TestFileDemo
				.readFileDataInByte(ServerConstants.FILE_UPLOAD_PATH + "/"
						+ filename);
		byte[] privatebyte = TestFileDemo.readFileDataInByte(ServerConstants.keys_path + um.getEmailid()
				+ "private.bin");
		String privateKey = new String(privatebyte);
		RSA rsa = new RSA();
		String keystr = rsa.decryptUsingPrivate(aeskeyenc, privateKey);
		AES a = new AES();
		Key key = a.generateKey(keystr);
		
		System.out.println("decrypting with this private: " + um.getEmailid() + "private.bin");
		System.out.println("this is encrypted key: " + aeskeyenc);
		System.out.println("this is decrypted key: " + keystr);

		byte[] decryptedBytes = a.decryptUsingKey(encData, key);
		try (FileOutputStream fos = new FileOutputStream("temp.pdf")) {
			fos.write(decryptedBytes);
			// fos.close(); There is no more need for this line since you had
			// created the instance of "fos" inside the try. And this will
			// automatically close the OutputStream
		}
		writeBytes(filename, decryptedBytes, response, false);
		

	}
	
	public static void setTimeout(Runnable runnable, int delay){
	    new Thread(() -> {
	        try {
	            Thread.sleep(delay);
	            runnable.run();
	        }
	        catch (Exception e){
	            System.err.println(e);
	        }
	    }).start();
	}

	public static void writeBytes(String fileName, byte[] data,
			HttpServletResponse response, boolean isInline) {
		OutputStream output;
		try {
			System.out.println("here 1");
			output = response.getOutputStream();
			System.out.println("Output:" + output);
			System.out.println("Downloading File " + fileName);
			response.setContentType("application/pdf");
			if (fileName != null && fileName.indexOf(".") == -1) {
				System.out.println("here 2");
				fileName = fileName + ".pdf";
				System.out.println("here 3");
			}
			if (isInline) {
				System.out.println("here 4");
				response.setHeader("Content-Disposition", "inline;filename="
						+ fileName);
				System.out.println("here 5");
			} else {
				System.out.println("here 6");
				response.setHeader("Content-Disposition",
						"attachment;filename=" + fileName);
				System.out.println("here 7");
			}

			output.write(data);
			output.close();
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: " + e.getMessage().toString());
			e.printStackTrace();
		}
	}

	public static String sendOTPOnmobilNo(HashMap parameters) {
		String mobileno = StringHelper.n2s(parameters.get("mobileno"));
		int random = (int) (Math.random() * 999 + 100);
		// int nextrandom = (int) (Math.random() * 999 + 100);

		ConnectionManager.sendOTPToMobile(mobileno, random + "");
		// String OTP = random + "-" + nextrandom;
		System.out.println("OTP :" + random);

		return random + "";
	}

	public static List<ShareRequestDocModel> getRecivedDocumentList(String uid) {

		String query = "select * from sharerequestdoc where senduserid like ?";

		List<ShareRequestDocModel> list = DBUtils.getBeanList(
				ShareRequestDocModel.class, query, uid);

		return list;
	}

	public static List<DocumentModel> getRecivedDocument(String fid) {

		String query = "select * from documents where did like ?";

		List<DocumentModel> list = DBUtils.getBeanList(DocumentModel.class,
				query, fid);

		return list;
	}

	public static String getUserDetailsFromAdhar(HashMap param) {
		String query = "select * from useraccount where adharno like ?";

		System.out.println("adhar:  " + param.get("adhar"));
		UserModel um = null;
		List<UserModel> list = DBUtils.getBeanList(UserModel.class, query,
				param.get("adhar"));
		String reutnstring = "-1";
		if (list.size() > 0) {

			String name = list.get(0).getFname();
			String email = list.get(0).getEmailid();
			String mob = list.get(0).getMobile();
			reutnstring = name + "," + mob + "," + email;
		}
		System.out.println("list: " + list);
		System.out.println("reutnstring: " + reutnstring);
		return reutnstring;
	}

	public static List getUserDetailsFromAdhar2(String adhar) {
		String query = "select * from useraccount where adharno like ?";

		System.out.println("adhar:  " + adhar);
		UserModel um = null;
		List<UserModel> list = DBUtils.getBeanList(UserModel.class, query,
				adhar);
		
		System.out.println("list: " + list);
		return list;
	}

	public static String getPropDetailsFromPropId(HashMap param) {
		String query = "select * from documents where propid like ? and status like 'verified'";

		System.out.println("propid:  " + param.get("propid"));
		UserModel um = null;
		List<DocumentModel> list = DBUtils.getBeanList(DocumentModel.class,
				query, param.get("propid"));
		String reutnstring = "-1";
		if (list.size() > 0) {

			String propid = list.get(0).getPropid();
			String surveyno = list.get(0).getSurveyno();
			String dor = list.get(0).getCurrenttimedate();
			String nor = list.get(0).getRegistrar();
			String addrs = list.get(0).getAddr();
			String area = list.get(0).getProparea();
			String type = list.get(0).getProptype();
			reutnstring = propid + "|" + surveyno + "|" + dor + "|" + nor + "|" + addrs + "|" + area + "|" + type;
		}
		System.out.println("list: " + list);
		System.out.println("reutnstring: " + reutnstring);
		return reutnstring;
	}
	
	public static List getSingleProperty(String pid){
		String query = "select * from documents where did = ?";
		List list = DBUtils.getBeanList(DocumentModel.class, query, pid);
		return list;
	}

	public static String updateRequestShareOnEmail(HashMap parameters)
			throws Exception {
		System.out.println(parameters);
		String success = "";
		// userid, fname, lname, emailid, mobile, address, role, access,
		// qualification, dob, username, emailverify, mobileverify, password
		String uid = StringHelper.n2s(parameters.get("uid"));
		String doshareonemaildocidcid = StringHelper.n2s(parameters
				.get("shareonemaildocid"));
		String emailid = StringHelper.n2s(parameters.get("emailid"));
		String mobileno = StringHelper.n2s(parameters.get("mobileno"));
		DocumentModel dm = getFileDataFromdid(doshareonemaildocidcid);

		String newFileName = emailid + "_ShareOnEmail_" + dm.getDocName();
		// HadoopReaderWriter.readFile(dm.getDocName());
		byte[] encData = TestFileDemo
				.readFileDataInByte(ServerConstants.FILE_UPLOAD_PATH + "/"
						+ dm.getDocName());
		AES a = new AES();

		UserModel um = getUserNameFromUid(uid);

		byte[] privateKeyBytes = TestFileDemo.readFileDataInByte(um
				.getEmailid() + "private.bin");
		String privateKey = new String(privateKeyBytes);
		RSA rsa = new RSA();
		String ownerRSAEncAESKey = dm.getAeskey();
		String rsaDecNewAESKey = rsa.decryptUsingPrivate(ownerRSAEncAESKey,
				privateKey);

		Key key = a.generateKey(rsaDecNewAESKey);
		// got original file data
		byte[] decData = a.decryptUsingKey(encData, key);
		String keystr = Randomkey.randomString(16);
		encryptSimpleFileToEncrypt(keystr, newFileName, decData);

		String sql = "insert into sharerequestonemaildoc (did, uid, senduseremail, aeskey, filename,otp) values(?,?,?,?,?,?)";
		int random = (int) (Math.random() * 999 + 100);
		int list = DBUtils.executeUpdate(sql, doshareonemaildocidcid, uid,
				emailid, keystr, newFileName, random);
		String lastrecorddatasql = "SELECT * FROM sharerequestonemaildoc ORDER BY sid DESC LIMIT 1;";
		List<ShareRequestOneMailDocModel> lastRecordList = getBeanList(
				ShareRequestOneMailDocModel.class, lastrecorddatasql);
		ShareRequestOneMailDocModel sqm = (ShareRequestOneMailDocModel) lastRecordList
				.get(0);
//		ConnectionManager.sendMailToEmailAddress(
//						emailid,
//						"Please download file click on "
//								+ ServerConstants.send_file_on_email_link
//								+ "?sid="
//								+ sqm.getSid()
//								+ "&emailid="
//								+ emailid
//								+ " this link..!! <br> <br><B></a></B><br><br> Thank You.!!");

		//sendMSGToMobile(mobileno, "Enter Otp " + random + " to download file..!!");
		if (list > 0) {

			success = "Email Requset Send Successfully..!!! ";

		} else {
			success = "Error adding user to database";
		}
		return success;

	}

	public static String updateRequestShare(HashMap parameters)
			throws Exception {

		// share for registered users..

		System.out.println(parameters);
		String success = "";
		// userid, fname, lname, emailid, mobile, address, role, access,
		// qualification, dob, username, emailverify, mobileverify, password
		String uid = StringHelper.n2s(parameters.get("uid"));
		String docid = StringHelper.n2s(parameters.get("sharedocid"));
		String uname = StringHelper.n2s(parameters.get("username"));
		String user_email = StringHelper.n2s(parameters.get("user_email"));

		String requestUid = getUidFromUserName(uname);
		DocumentModel dm = getFileDataFromdid(docid);

		String newFileName = uid + "_Share_" + dm.getDocName();
		// HadoopReaderWriter.readFile(dm.getDocName());
		byte[] encData = TestFileDemo
				.readFileDataInByte(ServerConstants.FILE_UPLOAD_PATH + "/"
						+ dm.getDocName());
		AES a = new AES();

		byte[] privateKeyBytes = TestFileDemo.readFileDataInByte(user_email
				+ "private.bin");
		String privateKey = new String(privateKeyBytes);
		RSA rsa = new RSA();
		String ownerRSAEncAESKey = dm.getAeskey();
		String rsaDecNewAESKey = rsa.decryptUsingPrivate(ownerRSAEncAESKey,
				privateKey);

		Key key = a.generateKey(rsaDecNewAESKey);

		// Key key = a.generateKey(dm.getAeskey().toString());

		byte[] decData = a.decryptUsingKey(encData, key);
		String keystr = Randomkey.randomString(16);
		encryptSimpleFileToEncrypt(keystr, newFileName, decData);
		UserModel um = getUserNameFromUid(requestUid);
		byte[] publicbyte = TestFileDemo.readFileDataInByte(um.getEmailid()
				+ "public.bin");
		String publickey = new String(publicbyte);

		String enckey = rsa.encryptUsingPublic(keystr, publickey);
		String sql = "insert into sharerequestdoc (did, uid, senduserid, aeskey, filename) values(?,?,?,?,?)";

		int list = DBUtils.executeUpdate(sql, docid, uid, requestUid, enckey,
				newFileName);
		if (list > 0) {

			success = "Requset Send Successfully..!!! ";

		} else {
			success = "Error adding user to database";
		}
		return success;

	}

	public static int getSendDocumentListCount(String uid) {

		String query = "select * from sharerequestdoc where uid like ?";

		List<ShareRequestDocModel> list = DBUtils.getBeanList(
				ShareRequestDocModel.class, query, uid);
		return list.size();
	}

	public static List getPropertyDetailsFromId(String uname) {
		String uid = getUidFromUserName(uname);
		String query = "select * from documents where userid like ?";

		List<DocumentModel> list = DBUtils.getBeanList(DocumentModel.class,
				query, uid);
		System.out.println("Got the List");
		for (int i = 0; i < list.size(); i++) {
			System.out.println("got the list: " + list.get(i).getDocName());
		}

		return list;
	}

	public static int getRecivedDocumentListCount(String uid) {

		String query = "select * from sharerequestdoc where senduserid like ?";

		List<ShareRequestDocModel> list = DBUtils.getBeanList(
				ShareRequestDocModel.class, query, uid);

		return list.size();
	}
	
	public static String requestPropDetails(String did, String uid){
		
		UserModel um = getUserNameFromUid(uid);
		DocumentModel dm = getDocumentFromDid(did);
		
		String query = "insert into proprequests (did,proptype,reqto,reqfrom,uname,adhar,email,mobile) values (?,?,?,?,?,?,?,?)";
		int a = DBUtils.executeUpdate(query, did,dm.getProptype(),dm.getUserid(),uid,um.getFname(),um.getAdharno(),um.getEmailid(),um.getMobile());
		
		if(a>0){
			return "1";
		}else{
			return "0";
		}
		
	}

	public static List getPropertiesAvailableForBuy(String uid){
		String query = "select * from documents where sale='Y' and status='verified' and userid <>'" + uid + "'";
		List list  = DBUtils.getBeanList(DocumentModel.class, query);
		
		return list;
	}
	public static int getCountPropertiesAvailableForBuy(String uid){
		String query = "select * from documents where sale='Y' and status='verified' and userid <>'" + uid + "'";
		List list  = DBUtils.getBeanList(DocumentModel.class, query);
		
		return list.size();
	}

	public static List getIncomingPropRequests(String uid){
		String query = "select * from proprequests where reqto='" + uid + "'";
		List list = DBUtils.getBeanList(PropertyViewRequestModel.class, query);
		return list;
	}
	public static int getCountIncomingPropRequests(String uid){
		String query = "select * from proprequests where reqto='" + uid + "'";
		List list = DBUtils.getBeanList(PropertyViewRequestModel.class, query);
		return list.size();
	}
	
	public static DocumentModel getDocumentFromDid(String did){
		String query = "select * from documents where did='" + did + "'";
		List list = DBUtils.getBeanList(DocumentModel.class, query);
		DocumentModel dm = null;
		if(list.size()>0){
			 dm = (DocumentModel)list.get(0);
		}
		return dm;
	}


	public static String transferOfProperty(String rid) {

		String query = "SELECT * FROM signrequestdoc where rid like ?";
		SignRequestModel srm = null;
		List list = DBUtils.getBeanList(SignRequestModel.class, query, rid);
		if(list.size()>0){
			srm = (SignRequestModel) list.get(0);
		}else{
			System.out.println("Error geting transfer requests!");
			return "Some error Occurred!";
		}
		UserModel oldOwner = getUserNameFromUid(srm.getUid());
		UserModel newOwner = getUserFromAdhar(srm.getNewowneradhar());
		DocumentModel document = (DocumentModel) getDocumentDataFromDB(srm.getDid()).get(0);
		System.out.println("TRANSFERING THIS PROPERTY: " + document.getDid());
		String data = "OldOwnerId=" + oldOwner.getUid() + ",NewOwnerId=" + newOwner.getUid() + ",PropertyId=" + document.getDid();

//		try {
//			data = SimpleCryptoAndroidJava.encryptString(data);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		System.out.println("dataToBlockchain = " + data);
		//MerkleNode node = br.addBlock(data, document.getDid());
		BlockChainIPFS bl = new BlockChainIPFS();
		bl.addNewBlock(data, document.getDid());
		
		String oldAesEnc =  document.getAeskey();
		
		//Decryption of Key
		byte[] privateKeyBytes = TestFileDemo.readFileDataInByte(ServerConstants.keys_path + oldOwner.getEmailid()
				+ "private.bin");
		String privateKey = new String(privateKeyBytes);
		RSA rsa = new RSA();
		String decryptedKey = rsa.decryptUsingPrivate(oldAesEnc,privateKey);
		System.out.println("decryptedKey: " + decryptedKey);
		
		//Encryption of Key
		byte[] publicbyte = TestFileDemo.readFileDataInByte(ServerConstants.keys_path + newOwner.getEmailid()
				+ "public.bin");
		String publickey = new String(publicbyte);
		String newEncryptedKey = rsa.encryptUsingPublic(decryptedKey, publickey);
		System.out.println("newEncryptedKey: " + newEncryptedKey);

//		if (node != null) {
			if (true) {
			
			String sql = "update documents set aeskey='" + newEncryptedKey +"', userid='" + newOwner.getUid() + "', "
							+ "status='in-draft', preownername='" + oldOwner.getFname() + "', preowneraddr='" + document.getAddr() + "', sale='N' "
							+ " where did='" + document.getDid() + "'";
			
			int a = DBUtils.executeUpdate(sql);

			System.out.println("Property updated.");
			
			String updt="delete FROM `signrequestdoc` where rid='" + rid + "'";
			DBUtils.executeUpdate(updt);

			System.out.println("status updated.");
			if(a>0){
				return "1";
			}else{
				return "0";
			}
		}else{
			return "Something went wrong!";
		}


	}

	public static String uploadDocument(FileItem fi, String userId,
			String role, String name, String key) {
		// docId, docName, docSize, docData, udate
		// documents
		System.out.println(key + ":" + role + ":" + name + ":" + fi.getName());
		try {
			File f = new File(ServerConstants.LOCAL_UPLOAD);
			if (!f.exists()) {
				f.mkdirs();
			}
			String fileName = fi.getName();
			if (role.contentEquals("2"))
				fileName = fileName + "-" + name;

			fi.write(new File(ServerConstants.LOCAL_UPLOAD + "/" + fileName));

			StringBuffer fileContent = FileHelper
					.getFileContent(ServerConstants.LOCAL_UPLOAD + "/"
							+ fileName);
			SimpleCryptoAndroidJava sc = new SimpleCryptoAndroidJava();
			byte[] encrypted = sc.encryptNew(fileContent.toString(), "0000");
			try (FileOutputStream fos = new FileOutputStream(
					ServerConstants.FILE_UPLOAD_PATH + "/" + fileName)) {
				fos.write(encrypted);
				// fos.close(); There is no more need for this line since you
				// had created the instance of "fos" inside the try. And this
				// will automatically close the OutputStream
			}
			String query = "insert into documents (docName, docSize, docData,userid) values (?,?,?,?)";
			executeUpdate(query, fileName, fi.getSize(), encrypted, userId);

			return "File Uploaded Successfully! File Indexing Completed!";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String uploadPropertyNew(FileItem fi, String userId,
			String role, String name, String keystr, String propid,
			String surveyno, String proptype, String proparea, String addr,
			String preownername, String preowneraddr) {
		// docId, docName, docSize, docData, udate
		// documents
		System.out.println(keystr + ":" + role + ":" + name + ":"
				+ fi.getName());
		try {
			File f = new File(ServerConstants.LOCAL_UPLOAD);
			if (!f.exists()) {
				f.mkdirs();
			}
			String fileName = fi.getName();

			if (role.contentEquals("2"))
				fileName = fileName;

			String filename = userId + "_" + new File(fileName).getName()
					+ ".pdf";
			byte[] data = fi.get();
			// simple to enc
			encryptSimpleFileToEncrypt(keystr, filename, data);

			UserModel um = getUserNameFromUid(userId);
			File file = new File(ServerConstants.keys_path + um.getEmailid() + "public.bin");
			if (!file.exists()) {

				System.err.println("PUBLIC KEY NOT FOUND FOR USER "
						+ um.getEmailid());

			}

			byte[] publicbyte = TestFileDemo.readFileDataInByte(ServerConstants.keys_path + um.getEmailid()
					+ "public.bin");
			String publickey = new String(publicbyte);
			RSA rsa = new RSA();
			String rsaPubEncAESKey = rsa.encryptUsingPublic(keystr, publickey);
			String query = "insert into documents (docName,userid,aeskey, propid, surveyno, proptype, proparea, addr, preownername, preowneraddr, status, registrar) values (?,?,?,?,?,?,?,?,?,?,?,?)";
			executeUpdate(query, filename, userId, rsaPubEncAESKey, propid,
					surveyno, proptype, proparea, addr, preownername,
					preowneraddr, "in-draft", "in-draft");

			return "Property Uploaded Successfully! File Indexing Completed!";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String checkPropertyAvailable(String uid, String propid) {

		System.out.println("uid: " + uid + ", propid: " + propid);
		String query = "select * from documents where userid like ? and propid like ? and status like 'verified'";
		DocumentModel dm = null;
		String did = "-1";
		System.out
				.println("checking property availability.........................");
		List<DocumentModel> list = DBUtils.getBeanList(DocumentModel.class,
				query, uid, propid);
		if (list.size() > 0) {
			did = list.get(0).getDid();
			System.out.println("property available.........................");
		}
		System.out.println("did: " + did);
		return did;

	}

	public static List getUserProperties(String uid) {
		// TODO Auto-generated method stub
		String query = "select * from documents where userid like '" + uid
				+ "' and status = 'verified'";
		DocumentModel dm = null;
		List<DocumentModel> list = DBUtils.getBeanList(DocumentModel.class,
				query);

		return list;

	}

	public static String addTransferRequest(String userId, String role,
			String name, String did, String propid, HashMap parameters) {

		try {

			UserModel um = getUserNameFromUid(userId);
//			File file = new File(ServerConstants.keys_path+um.getEmailid() + "public.bin");
//			if (!file.exists()) {
//
//				System.err.println("PUBLIC KEY NOT FOUND FOR USER "
//						+ um.getEmailid());
//
//			}
			String preownername = um.getFname();
			String preowneradhar = um.getAdharno();

//			byte[] publicbyte = TestFileDemo.readFileDataInByte(ServerConstants.keys_path+um.getEmailid()
//					+ "public.bin");
//			String publickey = new String(publicbyte);
			String query = "insert into signrequestdoc (uid, did, propid, dateofreg, surveyno, preownername, preowneradhar, newownername,"
					+ "newownermob, newowneradhar, newowneremail, status, propaddrs,"
					+ "marketval, sellval, stampduty, regfee, totalcost, payment) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			executeUpdate(query, userId, did, propid, parameters.get("dateofreg").toString(), parameters.get("surveyno").toString(),
					preownername, preowneradhar, parameters.get("newownername").toString(), parameters.get("newownermob").toString(),
					parameters.get("newowneradhar").toString(), parameters.get("newowneremail").toString(), "in-draft", parameters.get("propaddrs").toString(),
					parameters.get("marketval").toString(), parameters.get("sellval").toString(),
					parameters.get("stampduty").toString(), parameters.get("regfee").toString(), parameters.get("totalcost").toString(), parameters.get("payment").toString());
			

			return "Property Request for Transfer Added Successfully!!";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void encryptSimpleFileToEncrypt(String keystr,
			String filename, byte[] data) throws Exception {
		AES a = new AES();
		Key key = a.generateKey(keystr);

		byte[] encrypted = a.encryptUsingKey(data, key);
		try (FileOutputStream fos = new FileOutputStream(
				ServerConstants.FILE_UPLOAD_PATH + "/" + filename)) {
			fos.write(encrypted);
			System.out.println("Save Data into Encrypted File path:"
					+ new File(ServerConstants.FILE_UPLOAD_PATH + "/"
							+ filename).getAbsolutePath());
			// fos.close(); There is no more need for this line since you had
			// created the instance of "fos" inside the try. And this will
			// automatically close the OutputStream
		}
		// HadoopReaderWriter.writeFile(ServerConstants.FILE_UPLOAD_PATH + "/"+
		// filename);
	}

	public static String signDocument(FileItem fi, String signer_id,
			String sign_request_id, String certPass, String registrar) {
		// docId, docName, docSize, docData, udate
		// documents
		System.out.println("info: " + signer_id + " : " + sign_request_id);	// 30 : 62
		try {
			File f = new File(ServerConstants.LOCAL_UPLOAD);
			if (!f.exists()) {
				f.mkdirs();
			}

			String fileName = fi.getName();
			System.out.println("filename: " + fileName);

			// String keystr = ConnectionManager.getSecretKey(did);
			fi.write(new File(ServerConstants.LOCAL_UPLOAD + "/" + fileName));
			System.out.println("Ccertificate Upload done :"
					+ new File(ServerConstants.LOCAL_UPLOAD + "/" + fileName).getCanonicalPath());
			String enckeystr = ConnectionManager.getKeyFromDid(sign_request_id);
			System.out.println("enckeystr :" + enckeystr);
			// String privateKey = ConnectionManager.getPrivateKeyFromUid(uid);
			// System.out.println("privateKey :"+privateKey);

//			UserModel signer_usermodel = getUserNameFromUid(signer_id);		//v
			String docUid = getDocumentDataFromDB(sign_request_id).get(0).getUserid().toString();
			UserModel signer_usermodel = getUserNameFromUid(docUid); 
			System.out.println("signer_usermodel: " + signer_usermodel.getFname());
			System.out.println("this private: " + ServerConstants.keys_path + signer_usermodel.getEmailid()
					+ "private.bin");
			File private_key = new File(ServerConstants.keys_path + signer_usermodel.getEmailid()
					+ "private.bin");
			if (!private_key.exists()) {
				return "Private key do not exist. Please re-register the user";
			}

			byte[] privatebyte = TestFileDemo
					.readFileDataInByte(ServerConstants.keys_path + signer_usermodel.getEmailid()
							+ "private.bin");

			String privateKey = new String(privatebyte);
			System.out
					.println("----------------------------------------------------");
			System.out.println("privateKey :" + privateKey + " => length:"
					+ privateKey.length());
			RSA rsa = new RSA();
			String keystr = rsa.decryptUsingPrivate(enckeystr, privateKey);
			System.out.println("Final Keys:" + keystr);
			System.out
					.println("----------------------------------------------------");
			String pdffilename = ConnectionManager
					.getFileNameFromRid(sign_request_id); 
			System.out.println("pdffilename: " + pdffilename);
			byte[] fileContent = TestFileDemo
					.readFileDataInByte(ServerConstants.LOCAL_UPLOAD + "/"
							+ pdffilename);
			// System.out.println("fileContent :"+fi.get());
			AES a = new AES();
			Key key = a.generateKey(keystr);

			byte[] decrypted = a.decryptUsingKey(fileContent, key);
			try (FileOutputStream fos = new FileOutputStream("temp.pdf")) {
				fos.write(decrypted);
				// fos.close(); There is no more need for this line since you
				// had
				// created the instance of "fos" inside the try. And this will
				// automatically close the OutputStream
			}

			String pkcs12FileName = new File(ServerConstants.LOCAL_UPLOAD + "/"
					+ fileName).getCanonicalPath();
			String pdfInputFileName = "temp.pdf";
			String pdfOutputFileName = "signedDocumnet.pdf";

			// Document Sign Done
			String errorStatus = DigitalSignature.signDocument(pkcs12FileName,
					pdfInputFileName, pdfOutputFileName, certPass);
			System.out.println("errorStatus: " + errorStatus);
			if (errorStatus != null && errorStatus.length() > 0) {
				return errorStatus;
			}
			System.out.println("Document Sign Done..!!");

			// updateStatusToVerified(sign_request_id);

			System.out.println("pdfOutputFileName: " + pdfOutputFileName);
			byte[] pdfOutputFileContaint = TestFileDemo
					.readFileDataInByte(pdfOutputFileName);
			String newkeystr = Randomkey.randomString(16);

			DocumentModel original_document = ConnectionManager
					.getOriginalFileName(sign_request_id);
			UserModel requestor_usermodel = getUserNameFromUid(original_document
					.getUserid());
			encryptSimpleFileToEncrypt(newkeystr,
					original_document.getDocName(), pdfOutputFileContaint);
			byte[] publickey = TestFileDemo
					.readFileDataInByte(ServerConstants.keys_path+requestor_usermodel.getEmailid()
							+ "public.bin");
			String encryptedNewKey = rsa.encryptUsingPublic(newkeystr,
					new String(publickey));

			DocumentModel dmold = getFileDataFromdid(original_document.getDid()
					.toString());
			UserModel um2 = getUserNameFromUid(dmold.getUserid());

			// sending mail and sms

			//sendMailToEmailAddress(um2.getEmailid().toString(), "Your Document has  " + original_document.getDocName() + " been signed.!!");
			//sendMSGToMobile(um2.getMobile(), "Your Document has  " + original_document.getDocName() + " been signed.!!");

			String sql = "update documents set aeskey=?,status='verified', registrar='"
					+ registrar
					+ "'  where did like '"
					+ original_document.getDid() + "'";
			System.err.println(sql);
			int list = DBUtils.executeUpdate(sql, encryptedNewKey);
			
			System.out.println("updating this from verifreq: " + original_document.getDid());
			String updt = "UPDATE verifreq SET signstatus='YES', status='verified', registrar='" + registrar +"' where did='" + original_document.getDid() + "'";
			int val = DBUtils.executeUpdate(updt);

			System.out.println("File Uploaded Successfully! File Indexing Completed!");
			return "File Uploaded Successfully! File Indexing Completed!";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void updateStatusToVerified(String did) {
		String query = "UPDATE documents SET status='verified' WHERE did like ?";

		int list = DBUtils.executeUpdate(query, did);
		if (list > 0) {
			System.out.println("Status updated to verified!");
		} else {
			System.out.println("Error updating status!");
		}

	}
	
	public static String changeSaleStaus(String stat,String did){
		System.out.println("conn: " + stat + ", " + did);
		String query = "UPDATE documents SET sale='" + stat + "' WHERE did='" + did + "'";
		System.out.println(query);
		int list = DBUtils.executeUpdate(query);
		System.out.println(list);
		if(list>0){
			return "1";
		}else{
			return "0";
		}
	}
	
	public static String changeRequestStaus(String stat,String reqid){
		System.out.println("conn: " + stat + ", " + reqid);
		String query = "";
		String returnstat;
		if(stat.equalsIgnoreCase("1")){
			 returnstat = "1";
			 query = "UPDATE proprequests SET stat='Y' WHERE did='" + reqid + "'";
		}else{
			returnstat = "2";
			query = "delete from proprequests where reqid='" + reqid + "'";
		}
		System.out.println(query);
		int list = DBUtils.executeUpdate(query);
		System.out.println(list);

		System.out.println("returning: " + returnstat);
			return returnstat;
	}

	public static List acceptedRequest(String uid){
		String query = "select * from proprequests where reqfrom='" + uid + "' and stat='Y'";
		List list  = DBUtils.getBeanList(PropertyViewRequestModel.class, query);
		List list2 = new ArrayList();
		if(list.size()>0){
			
			for(int i=0; i<list.size(); i++){
				PropertyViewRequestModel pm = (PropertyViewRequestModel) list.get(i);
				DocumentModel dm =  getDocumentFromDid(pm.getDid());
				list2.add(dm);
			}
			return list2;
			
		}else{
			return list2;
		}
	}
	
	public static String isPropReqAccepted(String did, String reqfrom){
		String query = "select * from proprequests where did=? and reqfrom=? and stat='Y'";
		List list = DBUtils.getBeanList(PropertyViewRequestModel.class, query, did, reqfrom);
		if(list.size()>0){
			return "Y";
		}else{
			return "N";
		}
	}
	
	public static int getCountAcceptedRequest(String uid){
		String query = "select * from proprequests where reqfrom='" + uid + "' and stat='Y'";
		List list  = DBUtils.getBeanList(PropertyViewRequestModel.class, query);
		
			return list.size();
	}
	
	public static List getTransferedPropRecords() {

		String query = "select * from transferrecord";
		TransferRecords um = null;
		List<TransferRecords> list = DBUtils.getBeanList(TransferRecords.class, query);
		return list;
	}

	public static String rejectProperty(HashMap param){
		
		String query = "update documents set status='rejected' where did like ?";
			String ret = "-1";
			int list = DBUtils.executeUpdate(query, param.get("did"));
				if (list > 0) {
					System.out.println("Property Rejected!");
					ret = "1";
				} else {
					System.out.println("Error updating status!");
				}
				
		return ret;
	}

//	public static String sendMSGToMobile(String mobile, String message) {
//
//		String sms[] = { mobile };
//		System.out.println(":::::" + sms[0]);
//		for (int i = 0; i < sms.length; i++) {
//
//			SMSSender sender = new SMSSender(sms[i], message);
//			try {
//				sender.send();
//				Thread.sleep(4000);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		return "Y";
//	}

	public static String fPasswd(HashMap parameters) {
		System.err.println(parameters);
		String userName = StringHelper.n2s(parameters.get("username1"));
		String rpass = StringHelper.n2s(parameters.get("rpassword"));
		try {
			rpass = SimpleCryptoAndroidJava.encryptString(rpass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println("f**** :" + rpass);
		String sql = "update useraccount set password= ? where username like '"
				+ userName + "'";
		System.err.println(sql);
		int list = DBUtils.executeUpdate(sql, rpass);
		if (list > 0) {
			return "Password Successfully Changed..!!";
		} else {
			return "error";
		}
	}

	public static String updateIpAddress(String userid, String ipaddress) {
		String str = "N";
		// String ipaddress = MyIpAddress.getIpAddress();

		String sql = "update useraccount set ipaddress=?,wrongpassword=? where userid ="
				+ userid;
		System.out.println(sql);
		int list = DBUtils.executeUpdate(sql, ipaddress, "0");
		if (list > 0) {
			str = "Y";
		} else {
			str = "N";
		}
		return str;
	}

	public static String activeEmail(String email) {

		String sql = "update useraccount set emailactivestatus=? where emailid like '"
				+ email + "'";
		System.out.println(sql);
		int list = DBUtils.executeUpdate(sql, "Y");
		System.out.println(list + " llll");
		String str = "";
		if (list > 0) {
			str = "Email Successfully Varified..!!!";
		} else {
			str = "Email Not Varified..???";
		}
		return str;
	}

	public static String activeMobile(String mobile) {

		String sql = "update useraccount set mobileactivestatus=? where mobile like "
				+ mobile;
		int list = DBUtils.executeUpdate(sql, "Y");
		String str = "";
		if (list > 0) {
			str = "Mobile No Successfully Varified..!!!";
		} else {
			str = "Mobile No Not Varified..???";
		}
		return str;
	}

	public static UserModel checkLogin(HashMap parameters) {
		String emailid = StringHelper.n2s(parameters.get("emailid"));
		String pass = StringHelper.n2s(parameters.get("pass"));

		String query = "select * from useraccount where emailid = ? and pass = ?";
		UserModel um = null;
		List list = DBUtils.getBeanList(UserModel.class, query, emailid, pass);
		if (list.size() > 0) {
			um = (UserModel) list.get(0);
		}
		return um;
	}

	public static List<DocumentModel> getUploadedDocumentList(String uid) {

		String query = "select * from documents where userid like ? and status like 'verified' order by did ASC";

		List<DocumentModel> list = DBUtils.getBeanList(DocumentModel.class,
				query, uid);

		return list;
	}

	public static int getVerifiesDocumentCount() {

		String query = "select * from documents where status like 'verified' order by did ASC";

		List<DocumentModel> list = DBUtils.getBeanList(DocumentModel.class,
				query);

		return list.size();
	}

	public static List<DocumentModel> getWitoutSignDocumentList(String uid) {

		String query = "select * from documents where userid like ? and status like 'in-draft' or status like 'rejected'";

		List<DocumentModel> list = DBUtils.getBeanList(DocumentModel.class,
				query, uid);

		return list;
	}

	public static int getWitoutSignDocumentListCount(String uid) {

		String query = "select * from documents where userid like ? and status like 'in-draft' or status like 'rejected'";

		List<DocumentModel> list = DBUtils.getBeanList(DocumentModel.class,
				query, uid);

		// System.out.println("getWitoutSignDocumentListCount: " +
		// list.get(0).getDocName());

		return list.size();
	}

	public static List<ShareRequestDocModel> getSendDocumentList(String uid) {

		String query = "select * from sharerequestdoc where senduserid like ?";

		List<ShareRequestDocModel> list = DBUtils.getBeanList(
				ShareRequestDocModel.class, query, uid);

		return list;
	}

	public static List<ShareRequestOneMailDocModel> getEmailSharebleFidOnSid(
			String sid) {

		String query = "select * from sharerequestonemaildoc where sid like ?";

		List<ShareRequestOneMailDocModel> list = DBUtils.getBeanList(
				ShareRequestOneMailDocModel.class, query, sid);

		return list;
	}

	public static ShareRequestOneMailDocModel getEmailSharebleFidModelOnSid(
			String sid) {

		String query = "select * from sharerequestonemaildoc where sid like ?";

		List<ShareRequestOneMailDocModel> list = DBUtils.getBeanList(
				ShareRequestOneMailDocModel.class, query, sid);
		ShareRequestOneMailDocModel sm = (ShareRequestOneMailDocModel) list
				.get(0);
		return sm;
	}

	public static List<ShareRequestDocModel> getSendList(String uid) {

		String query = "select * from sharerequestdoc where uid like ?";

		List<ShareRequestDocModel> list = DBUtils.getBeanList(
				ShareRequestDocModel.class, query, uid);

		return list;
	}

	public static List<DocumentModel> getDocumentDataFromDB(String did) {

		String query = "select * from documents where did like ?";

		List list = DBUtils.getBeanList(DocumentModel.class,
				query, did);
		if(list.size()>0)
			System.out.println("Got document");

		return list;
	}

	public static List<DocumentModel> getDocumentFromPropId(String propid) {

		String query = "select * from documents where propid like ?";

		List<DocumentModel> list = DBUtils.getBeanList(DocumentModel.class,
				query, propid);
		System.out.println("firing: select * from documents where propid like " + propid);
		if(list.size()>0){
			System.out.println("Got List!");
		}else{
			System.out.println("Didn't get list");
		}

		return list;
	}


	public static List<SignRequestModel> getPendingSignInRequests(String uid) {

		String query = "SELECT * FROM `signrequestdoc` where status like 'NO' and ruid like '"
				+ uid + "'";

		List<SignRequestModel> list = DBUtils.getBeanList(
				SignRequestModel.class, query);

		return list;
	}

	public static String checkPropAvail(String propid) {

		String query = "SELECT * FROM documents where propid like '" + propid
				+ "' and status like 'verified'";
		String val = "-1";
		List<DocumentModel> list = DBUtils.getBeanList(DocumentModel.class,
				query);
		if (list.size() > 0) {
			val = "1";
		}
		return val;
	}

	public static List<SignRequestModel> getTransfReqList() {

		String query = "select * from signrequestdoc where status like 'in-draft'";

		List<SignRequestModel> list = DBUtils.getBeanList(
				SignRequestModel.class, query);

		return list;
	}

	public static String getUidFromUserName(String uname) {

		// String query = "select * from documents where userid like ?";

		String query = "select * from useraccount where emailid like ? or mobile like ? or fname like ?";
		UserModel um = null;
		List<UserModel> list = DBUtils.getBeanList(UserModel.class, query,
				uname, uname, uname);
		if (list.size() > 0) {
			um = (UserModel) list.get(0);
		}

		return um.getUid();
	}


	public static UserModel getAdminDetails() {

		String query = "select * from useraccount where role='1'";
		UserModel um = null;
		List<UserModel> list = DBUtils.getBeanList(UserModel.class, query);
		if (list.size() > 0) {
			um = (UserModel) list.get(0);
		}

		return um;
	}

	public static String getFileNameFromRid(String rid) {

		// String query = "select * from documents where userid like ?";

		String query = "select * from documents where did like ?";
		DocumentModel um = null;
		List<DocumentModel> list = DBUtils.getBeanList(DocumentModel.class,
				query, rid);
		if (list.size() > 0) {
			um = (DocumentModel) list.get(0);
		}

		return um.getDocName();
	}

	public static DocumentModel getOriginalFileName(String rid) {

		// String query = "select * from documents where userid like ?";

		String query = "select * from documents where did like ?";
		SignRequestModel um = null;
		List<SignRequestModel> list = DBUtils.getBeanList(
				SignRequestModel.class, query, rid);
		if (list.size() > 0) {
			um = (SignRequestModel) list.get(0);
		}

		String did = um.getDid();
		String query1 = "select * from documents where did like ?";
		DocumentModel dm = null;
		List<DocumentModel> list1 = DBUtils.getBeanList(DocumentModel.class,
				query1, did);
		if (list1.size() > 0) {
			dm = (DocumentModel) list1.get(0);
		}
		return dm;
	}

	public static String getRuidFromUserName(String uname) {

		// String query = "select * from documents where userid like ?";

		String query = "select * from useraccount where emailid like ? or mobile like ?";
		UserModel um = null;
		List<UserModel> list = DBUtils.getBeanList(UserModel.class, query,
				uname, uname);
		if (list.size() > 0) {
			um = (UserModel) list.get(0);
		}

		return um.getUid();
	}

	public static String getKeyFromDid(String rid) {

		// String query = "select * from documents where userid like ?";

		String query = "select * from documents where did like ?";
		SignRequestModel um = null;
		List<SignRequestModel> list = DBUtils.getBeanList(
				SignRequestModel.class, query, rid);
		if (list.size() > 0) {
			um = (SignRequestModel) list.get(0);
		}

		return um.getAeskey();
	}

	public static String getFilenameFromdid(String did) {

		// String query = "select * from documents where userid like ?";

		String query = "select * from documents where did like ?";
		DocumentModel um = null;
		List<DocumentModel> list = DBUtils.getBeanList(DocumentModel.class,
				query, did);
		if (list.size() > 0) {
			um = (DocumentModel) list.get(0);
		}
		if (um != null)
			return um.getDocName();
		else
			return "";
	}

	public static DocumentModel getFileDataFromdid(String did) {

		// String query = "select * from documents where userid like ?";

		String query = "select * from documents where did like ?";
		DocumentModel um = null;
		List<DocumentModel> list = DBUtils.getBeanList(DocumentModel.class,
				query, did);
		if (list.size() > 0) {
			um = (DocumentModel) list.get(0);
		}

		return um;
	}

	public static VerifyRequestModel getVerifFileDataFromdid(String did) {

		// String query = "select * from documents where userid like ?";

		String query = "select * from verifreq where did like ?";
		VerifyRequestModel dm = null;
		List<VerifyRequestModel> list = DBUtils.getBeanList(VerifyRequestModel.class,
				query, did);
		if (list.size() > 0) {
			dm = (VerifyRequestModel) list.get(0);
		}

		return dm;
	}

	public static ShareRequestDocModel getRecivedFileDataFromSid(String sid) {

		// String query = "select * from documents where userid like ?";

		String query = "select * from sharerequestdoc where sid like ?";
		ShareRequestDocModel um = null;
		List<ShareRequestDocModel> list = DBUtils.getBeanList(
				ShareRequestDocModel.class, query, sid);
		if (list.size() > 0) {
			um = (ShareRequestDocModel) list.get(0);
		}

		return um;
	}

	public static VerifyRequestModel getRecivedFileDataFromSid2(String sid) {

		// String query = "select * from documents where userid like ?";

		String query = "select * from verifreq where did like ?";
		VerifyRequestModel um = null;
		List<VerifyRequestModel> list = DBUtils.getBeanList(VerifyRequestModel.class,
				query, sid);
		if (list.size() > 0) {
			um = (VerifyRequestModel) list.get(0);
		}

		return um;
	}

	public static String getSecretKey(String did) {

		// String query = "select * from documents where userid like ?";

		String query = "select * from documents where did like ?";
		DocumentModel um = null;
		List<DocumentModel> list = DBUtils.getBeanList(DocumentModel.class,
				query, did);
		if (list.size() > 0) {
			um = (DocumentModel) list.get(0);
		}

		return um.getAeskey();
	}

	// public static String getEncryptedKey(UserModel umRequest, String
	// ownerKey) {
	//
	// String encryption = RSAUtils.encryptUsingPublic(ownerKey,
	// umRequest.getPublickey());
	// System.out.println(encryption);
	//
	// return encryption;
	// }
	//
	// public static String getDecryptedKey(UserModel umRequest,
	// String encryptedKey) {
	//
	// String decryption = RSAUtils.decryptUsingPrivate(encryptedKey,
	// umRequest.getPrivatekey());
	// System.out.println(decryption);
	//
	// return decryption;
	// }
	public static UserModel getUserNameFromUid(String uid) {

		// String query = "select * from documents where userid like ?";

		String query = "select * from useraccount where uid like ?";
		UserModel um = null;
		List list = DBUtils.getBeanList(UserModel.class, query, uid);
		if (list.size() > 0) {
			System.out.println("Got user model");
			um = (UserModel) list.get(0);
		}

		return um;
	}

	public static int getUploadedDocumentCount(String uid) {

		String query = "select * from documents where userid like ? and status like 'verified'";

		List<DocumentModel> list = DBUtils.getBeanList(DocumentModel.class,
				query, uid);

		return list.size();
	}

	public static int getReceivedDocumentCount(String uid) {

		String query = "select * from documents where userid like ?";

		List<DocumentModel> list = DBUtils.getBeanList(DocumentModel.class,
				query, uid);

		return list.size();
	}

	public static int getVerificationRequestCount(String uid) {

		String query = "select * from verifreq where userid like ?";

		List<VerifyRequestModel> list = DBUtils.getBeanList(VerifyRequestModel.class,
				query, uid);

		return list.size();
	}

	public static List getPendingDocList() {

		String query = "select * from verifreq where status = 'in-draft'";

		List<VerifyRequestModel> list = DBUtils.getBeanList(VerifyRequestModel.class,
				query);

		return list;
	}

	public static UserModel checkLoginOnUserid(String userId) {
		// String userNameId = StringHelper.n2s(parameters.get("username"));
		// String pass = StringHelper.n2s(parameters.get("password"));

		String query = "SELECT * FROM useraccount where userid like ?";
		UserModel um = null;
		List<UserModel> list = DBUtils.getBeanList(UserModel.class, query,
				userId);
		if (list.size() > 0) {
			um = (UserModel) list.get(0);
		}
		return um;
	}

	public static int getPendingListData(String docid) {
		// String userNameId = StringHelper.n2s(parameters.get("username"));
		// String pass = StringHelper.n2s(parameters.get("password"));

		String query = "SELECT * FROM signrequestdoc where did like ? and status like 'NO'";
		UserModel um = null;
		List<UserModel> list = DBUtils.getBeanList(UserModel.class, query,
				docid);
		return list.size();
	}

	public static boolean verifySignedOrNot(String docid) {
		// String userNameId = StringHelper.n2s(parameters.get("username"));
		// String pass = StringHelper.n2s(parameters.get("password"));

		String query = "SELECT * FROM signrequestdoc where did like ? and status like 'YES'";
		UserModel um = null;
		List<UserModel> list = DBUtils.getBeanList(UserModel.class, query,
				docid);
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}

	}

	public static List getSignRequestDocsFromDid(String docid) {

		String query = "SELECT * FROM signrequestdoc where did like ?";
		UserModel um = null;
		List<SignRequestModel> list = DBUtils.getBeanList(SignRequestModel.class, query,
				docid);

		return list;

	}
	
	public static UserModel getUserFromAdhar(String adhar){
		String sql = "select * from useraccount where adharno='" + adhar + "'";
		UserModel um = null;
		List list = DBUtils.getBeanList(UserModel.class, sql);
		
		if(list.size()>0){
			System.out.println("Got user model from adhar");
			um = (UserModel) list.get(0);
		}
		return um;
	}

	public static boolean checkUserNameExists(HashMap parameters) {
		String uid = StringHelper.n2s(parameters.get("name"));
		String queryDuplicate = "select 1 from enquirymaster where username='"
				+ uid + "'";
		System.out.println("query " + queryDuplicate);

		return dataExists(queryDuplicate);
	}

	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static boolean dataExists(String query) {

		boolean success = false;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getDBConnection();
			rs = conn.createStatement().executeQuery(query);
			System.out.println("Executing " + query);
			if (rs.next()) {
				success = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return success;
	}

	public static String deleteuser(String uid) {
		String query = "delete from useraccount where userid=?";
		int i = DBUtils.executeUpdate(query, new Object[] { uid });
		return i + "";
	}

	public static String deleteFile(String did) {

		// HadoopReaderWriter.deleteFile(getFilenameFromdid(did));
		String query = "delete from documents where did=?";
		int j = DBUtils.executeUpdate(query, new Object[] { did });
		query = "delete from signrequestdoc where did=?";
		int i = DBUtils.executeUpdate(query, new Object[] { did });
		return j + "";
	}

	public static String requestDocumentSign(HashMap parameters,
			UserModel requestor_um) throws Exception {
		System.out.println(parameters);
		String success = "";
		// userid, fname, lname, emailid, mobile, address, role, access,
		// qualification, dob, username, emailverify, mobileverify, password
		String uid = StringHelper.n2s(parameters.get("uid"));
		String expirydate = StringHelper.n2s(parameters.get("cdate"));
		String docid = StringHelper.n2s(parameters.get("docid"));
		String msg = StringHelper.n2s(parameters.get("msg"));
		String signer_name = StringHelper.n2s(parameters.get("uname"));
		String signer_uid = getUidFromUserName(signer_name);
		DocumentModel dm = getFileDataFromdid(docid);
		
		UserModel signer_userModel = getUserNameFromUid(signer_uid);
		String newFileName = uid + "_" + dm.getDocName();
		byte[] encData = TestFileDemo
				.readFileDataInByte(ServerConstants.FILE_UPLOAD_PATH + "/"
						+ dm.getDocName());

		System.out.println("signer_userModel: " + signer_userModel.getEmailid());
		byte[] privatebyte = TestFileDemo.readFileDataInByte(ServerConstants.keys_path+requestor_um
				.getEmailid() + "private.bin");
		String privateKey = new String(privatebyte);
		RSA rsa = new RSA();
		String keystr_file = rsa.decryptUsingPrivate(dm.getAeskey().toString(),
				privateKey);
		AES a = new AES();
		Key key = a.generateKey(keystr_file);

		// AES a = new AES();
		// Key key = a.generateKey(dm.getAeskey().toString());
		byte[] decData = a.decryptUsingKey(encData, key);

		String keystr = Randomkey.randomString(16);

		encryptSimpleFileToEncrypt(keystr, newFileName, decData);

		System.out.println("getting public key: " + signer_userModel.getEmailid() + "public.bin");
		System.out.println("encrypting this key: " + keystr);
		
		byte[] publicbyte = TestFileDemo.readFileDataInByte(ServerConstants.keys_path+signer_userModel
				.getEmailid() + "public.bin");
		String publickey = new String(publicbyte);

		System.out
				.println("-----------------------------------------------------");
		System.out.println("Public Key:" + publickey);
		System.out.println("keystr:" + keystr);
		System.out
				.println("-----------------------------------------------------");
		String enckey = rsa.encryptUsingPublic(keystr, publickey);
		System.out.println("encrypted key: " + enckey);
		// TestFileDemo.writByteDataToFile(ServerConstants.FILE_UPLOAD_PATH +
		// "/"+ newFileName, decData);
		
		DocumentModel dmo = getFileDataFromdid(docid);

		String sql = "insert into verifreq (did, userid,filename, docname, expirydate, msg, ruid, aeskey,"
				+ "propid, surveyno, proptype, proparea, addr, preownername, preowneraddr, registrar ) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		int list = DBUtils.executeUpdate(sql, docid, uid, newFileName,newFileName,
				expirydate, msg, signer_uid, enckey,
				dmo.getPropid().toString(), dmo.getSurveyno().toString(), dmo.getProptype().toString(), dmo.getProparea().toString(),
				dmo.getAddr().toString(), 
				dmo.getPreownername().toString(), dmo.getPreowneraddr().toString(), dmo.getRegistrar().toString());
		if (list > 0) {

			success = "Requset Send Successfully..!!! ";

		} else {
			success = "Error adding user to database";
		}
		return success;
	}

	private static String getPublicKeyFromUid(String uid) {
		// TODO Auto-generated method stub
		String query = "select * from useraccount where uid like ?";
		UserModel um = null;
		List<UserModel> list = DBUtils.getBeanList(UserModel.class, query, uid);
		if (list.size() > 0) {
			um = (UserModel) list.get(0);
		}

		return um.getPublickey();
		// return null;
	}

	private static String getPrivateKeyFromUid(String uid) {
		// TODO Auto-generated method stub
		String query = "select * from useraccount where uid like ?";
		UserModel um = null;
		List<UserModel> list = DBUtils.getBeanList(UserModel.class, query, uid);
		if (list.size() > 0) {
			um = (UserModel) list.get(0);
		}

		return um.getPrivatekey();
		// return null;
	}

	public static StringBuffer uploadDocument(FileItem fi) {
		String answer = "acd";
		File f = new File(ServerConstants.LOCAL_UPLOAD);
		if (!f.exists()) {
			f.mkdirs();
		}
		String fileName = fi.getName();
		try {
			fi.write(new File(ServerConstants.LOCAL_UPLOAD + "/" + fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		StringBuffer fileContent = FileHelper
				.getFileContent(ServerConstants.LOCAL_UPLOAD + "/" + fileName);
		System.out.println(fileContent.toString());

		return fileContent;
	}

	public static Connection getDBConnection() {
		Connection conn = null;
		try {
			Class.forName(ServerConstants.db_driver);
			conn = DriverManager.getConnection(ServerConstants.db_url,
					ServerConstants.db_user, ServerConstants.db_pwd);
			System.out.println("Got Connection");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return conn;
	}

//	public static String sendOTPToMailAndMobile(String emailormobile) {
//		// userid, fname, lname, emailid, mobile, address, role, access,
//		// qualification, dob
//		String sql = "select * from useraccount where emailid like '"
//				+ emailormobile + "' or mobile like '" + emailormobile + "'";
//		List list = getBeanList(UserModel.class, sql);
//		System.out.println("size :" + list.size());
//		if (list.size() < 0) {
//			return "error";
//		} else {
//			UserModel um = (UserModel) list.get(0);
//			int random = (int) (Math.random() * 999 + 100);
//			// int nextrandom = (int) (Math.random() * 999 + 100);
//			System.err.println(um.getMobile() + " : " + um.getEmailid());
//			ConnectionManager.sendOTPToMobile(um.getMobile(), random + "");
//			ConnectionManager.sendOTPToEmail(um.getEmailid(), random + "");
//			// String OTP = random + "-" + nextrandom;
//			System.out.println("OTP :" + random);
//			return random + "";
//		}
//
//	}

	public static String sendOTPToMobile(String mobile, String OTP) {
		String str = "";
		String sms[] = { mobile };
		System.out.println(":::::" + sms[0]);
		for (int i = 0; i < sms.length; i++) {

			SMSSender sender = new SMSSender(
					sms[i],
					"Hi "
							+ mobile
							+ ", You Are Login On New Machine, so please refer this OTP :- "
							+ OTP);
			try {
				sender.send();
				Thread.sleep(4000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;
	}

//	public static String sendOTPToEmail(String email, String OTP) {
//		String str = "";
//		HashMap param = new HashMap();
//		param.put("to", email);
//		param.put("subject", "Activating your Account");
//		param.put(
//				"msg",
//				"Hi "
//						+ email
//						+ ", You Are Login On New Machine, so please refer this OTP :- "
//						+ OTP);
//
//		new MailUtility().sendEmail(param, ServerConstants.ADMIN_EMAIL_ID,
//				ServerConstants.ADMIN_EMAIL_PASS);
//		return str;
//	}

//	public static String sendMailToEmailAddress(String email, String message) {
//		System.out.println(email);
//		System.out.println(message);
//		String str = "";
//		HashMap param = new HashMap();
//		param.put("to", email);
//		param.put("subject", "Activating your Account");
//		param.put("msg", message);
//
//		new MailUtility().sendEmail(param, ServerConstants.ADMIN_EMAIL_ID,
//				ServerConstants.ADMIN_EMAIL_PASS);
//		return str;
//	}

//	public static String sendVarificationLinks(String email, String mobile) {
//
//		HashMap param = new HashMap();
//		param.put("to", email);
//		param.put("subject", "Activating your Account");
//		param.put(
//				"msg",
//				"<div>Successfully Registered...!!! Hi,Activate your account please visit http://"
//						+ ServerConstants.send_verification_link
//						+ "/SecurePrivateCloudNew/tiles/ajax.jsp?methodId=activeemail&email="
//						+ email + "</div>");
//
//		// MailUtility mail=new MailUtility();
//		// new MailUtility().sendEmailMultipart(param);
//		new MailUtility().sendEmail(param, ServerConstants.ADMIN_EMAIL_ID,
//				ServerConstants.ADMIN_EMAIL_PASS);
//
//		// sms----------------------
//		String sms[] = { mobile };
//		System.out.println(":::::" + sms[0]);
//		for (int i = 0; i < sms.length; i++) {
//
//			SMSSender sender = new SMSSender(
//					sms[i],
//					"Successfully Registered...!!! http://"
//							+ ServerConstants.send_verification_link
//							+ "/SecurePrivateCloudNew/tiles/ajax.jsp?methodId=activemobile&mobile="
//							+ mobile);
//			try {
//				sender.send();
//				Thread.sleep(4000);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		return "Y";
//	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static String insertUser(FileItem fi, HashMap parameters) throws Exception {
		
		String success = "";
		
		System.out.println(parameters);
		// userid, fname, lname, emailid, mobile, address, role, access,
		// qualification, dob, username, emailverify, mobileverify, password
		String emailid = StringHelper.n2s(parameters.get("emailid"));
		String pass = StringHelper.n2s(parameters.get("pass"));
		String mobile = StringHelper.n2s(parameters.get("mobile"));
		String fname = StringHelper.n2s(parameters.get("fname"));
		String panno = StringHelper.n2s(parameters.get("panno"));
		String adharno = StringHelper.n2s(parameters.get("adharno"));
		// String mobile = StringHelper.n2s(parameters.get("mobile"));
//		 String aeskey= StringHelper.n2s(parameters.get("skey"));
		
		try {
			File f = new File(ServerConstants.LOCAL_UPLOAD);
			if (!f.exists()) {
				f.mkdirs();
			}
			
			File abc = new File(ServerConstants.keys_path);
			if(!abc.exists()){
				abc.mkdirs();
			}
//			String fileName = fi.getName();

			String filename = parameters.get("adharno") + ".png";
			System.out.println("filename:::: " + filename);
//			byte[] data = fi.get();
			fi.write(new File(ServerConstants.LOCAL_UPLOAD + "/" + filename));
			// simple to enc
//			encryptSimpleFileToEncrypt(keystr, filename, data);

//			UserModel um = getUserNameFromUid(userId);
//			File file = new File(parameters.get("adharno") + "public.bin");
//			if (!file.exists()) {
//
//				System.err.println("PUBLIC KEY NOT FOUND FOR USER " + parameters.get("adharno"));
//
//			}

//			byte[] publicbyte = TestFileDemo.readFileDataInByte(parameters.get("adharno") + "public.bin");
//			String publickey = new String(publicbyte);
//			RSA rsa = new RSA();
//			String rsaPubEncAESKey = rsa.encryptUsingPublic(keystr, publickey);
			
			RSA obj = new RSA();

			String[] a = obj.getNewKeyPair();

			TestFileDemo
					.writByteDataToFile(ServerConstants.keys_path+emailid + "public.bin", a[0].getBytes());
			TestFileDemo.writByteDataToFile(ServerConstants.keys_path+emailid + "private.bin",
					a[1].getBytes());

		
		String sql = "insert into useraccount (image, emailid, mobile,fname, panno, adharno, pass) values(?,?,?,?,?,?,?)";

		int list = DBUtils.executeUpdate(sql, filename, emailid, mobile, fname, panno, adharno, pass);
		if (list > 0) {

//			String str = sendVarificationLinks(emailid, mobile);
//			if (str.equalsIgnoreCase("Y")) {
//				success = "User registered Successfully Email and Mobile Varification Link Send On Your Email Id and Mobile No...!!! ";
//			} else {
//				success = "Error adding user to database";
//			}
			success = "Error adding user to database";
		} else {
			success = "Error adding user to database";
		}
		

		return "Image Uploaded Successfully! File Indexing Completed!";

	} catch (Exception e) {
		e.printStackTrace();
	}
		
		return success;
	}

	public static String getRoleName(String role) {
		String str = "";
		if (role.equalsIgnoreCase("1")) {
			str = "MD";
		} else if (role.equalsIgnoreCase("2")) {
			str = "Doctor";
		} else if (role.equalsIgnoreCase("3")) {
			str = "Receptionist";
		} else if (role.equalsIgnoreCase("4")) {
			str = "Cashier";
		} else if (role.equalsIgnoreCase("5")) {
			str = "Medical";
		} else if (role.equalsIgnoreCase("6")) {
			str = "Nourse";
		} else if (role.equalsIgnoreCase("7")) {
			str = "Patient";
		}

		return str;
	}

	public static String insertHospitalCharges(HashMap parameters) {
		System.out.println(parameters);
		String success = "";
		// userid, fname, lname, emailid, mobile, address, role, access,
		// qualification, dob, username, emailverify, mobileverify, password
		String patientid = StringHelper.n2s(parameters.get("patientid"));
		String doctorid = StringHelper.n2s(parameters.get("doctorid"));
		String amt = StringHelper.n2s(parameters.get("amt"));

		String sql = "insert into hospital_charges (patientid, doctorid, amt) values(?,?,?)";

		int list = DBUtils.executeUpdate(sql, patientid, doctorid, amt);
		if (list > 0) {

			success = "Successfully Add Hosital Charges..!!";

		} else {
			success = "Error adding user to database";
		}
		return success;
	}

	public static String updatepaycharges(HashMap parameters) {
		System.out.println(parameters);
		String success = "";
		// userid, fname, lname, emailid, mobile, address, role, access,
		// qualification, dob, username, emailverify, mobileverify, password
		String patientid = StringHelper.n2s(parameters.get("patientid"));
		String amt = StringHelper.n2s(parameters.get("amt"));

		String sql = "insert into pay_charges (patientid, amt) values(?,?)";

		int list = DBUtils.executeUpdate(sql, patientid, amt);
		if (list > 0) {

			success = "Successfully Pay Hosital Charges..!!";

		} else {
			success = "Error adding user to database";
		}
		return success;
	}

	public static String insertMedicalCharges(HashMap parameters) {
		System.out.println(parameters);
		String success = "";
		// userid, fname, lname, emailid, mobile, address, role, access,
		// qualification, dob, username, emailverify, mobileverify, password
		String patientid = StringHelper.n2s(parameters.get("patientid"));
		String doctorid = StringHelper.n2s(parameters.get("doctorid"));
		String medicine = StringHelper.n2s(parameters.get("medicine"));
		String amt = StringHelper.n2s(parameters.get("amt"));

		String sql = "insert into medical_charges (patientid, doctorid,medicine, amt) values(?,?,?,?)";

		int list = DBUtils.executeUpdate(sql, patientid, doctorid, medicine,
				amt);
		if (list > 0) {

			success = "Successfully Add Medical Charges..!!";

		} else {
			success = "Error adding user to database";
		}
		return success;
	}

	public static String updateUser(HashMap parameters) {
		System.out.println(parameters);
		String success = "";
		// userid, fname, lname, emailid, mobile, address, role, access,
		// qualification, dob, username, emailverify, mobileverify, password
		String fname = StringHelper.n2s(parameters.get("fname"));
		String lname = StringHelper.n2s(parameters.get("lname"));
		String fullname = fname + " " + lname;
		String emailid = StringHelper.n2s(parameters.get("email"));
		String phoneno = StringHelper.n2s(parameters.get("mobile"));
		String address = StringHelper.n2s(parameters.get("address"));
		String qualification = StringHelper
				.n2s(parameters.get("qualification"));
		String dob = StringHelper.n2s(parameters.get("dob"));
		String username = StringHelper.n2s(parameters.get("username"));
		String password = StringHelper.n2s(parameters.get("password"));
		String userid = StringHelper.n2s(parameters.get("userid"));
		String role = ConnectionManager.getRoleId(parameters.get("userprof")
				.toString());
		String sql = "update useraccount set fname=?, lname=?, emailid=?, mobile=?, address=?, role=?, access=?, qualification=?, dob=?, username=?, emailverify=?, mobileverify=?,ipaddress=?, password=? where userid like ?";

		int list = DBUtils.executeUpdate(sql, fname, lname, emailid, phoneno,
				address, role, "AMD", qualification, dob, username, "N", "N",
				"N", password, userid);
		if (list > 0) {
			//String str = sendVarificationLinks(emailid, phoneno);
			if (true) {
				success = "User registered Successfully Email and Mobile Varification Link Send On Your Email Id and Mobile No...!!! ";
			} else {
				success = "Error adding user to database";
			}
		} else {
			success = "Error adding user to database";
		}
		return success;
	}

	public static String getRoleId(String str) {
		// TODO Auto-generated method stub
		// Doctor, Nurse ,Patient,Cashier,Medical,Receptionist,Patient
		HashMap hm = ServerConstants.hm;

		return hm.get(str).toString();
	}

	public static String insertStudentFeedback(HashMap parameters) {
		System.out.println(parameters);
		String success = "";

		String Abuffer = StringHelper.n2s(parameters.get("Abuffer"));
		String Bbuffer = StringHelper.n2s(parameters.get("Bbuffer"));
		String Cbuffer = StringHelper.n2s(parameters.get("Cbuffer"));
		String Dbuffer = StringHelper.n2s(parameters.get("Dbuffer"));
		String uid = StringHelper.n2s(parameters.get("uid"));
		String rollno = StringHelper.n2s(parameters.get("rollno"));
		String tid = StringHelper.n2s(parameters.get("tid"));
		String fid = StringHelper.n2s(parameters.get("fid"));
		String totalscore = StringHelper.n2s(parameters.get("totalscore"));
		int A[] = new int[7];
		int B[] = new int[4];
		int C[] = new int[3];
		int D[] = new int[10];
		String Asub[] = Abuffer.split("_");
		String Bsub[] = Bbuffer.split("_");
		String Csub[] = Cbuffer.split("_");
		String Dsub[] = Dbuffer.split("_");
		for (int i = 0; i < Asub.length; i++) {
			A[i] = StringHelper.n2i(Asub[i]);
		}
		for (int i = 0; i < Bsub.length; i++) {
			B[i] = StringHelper.n2i(Bsub[i]);
		}
		for (int i = 0; i < Csub.length; i++) {
			C[i] = StringHelper.n2i(Csub[i]);
		}
		for (int i = 0; i < Dsub.length; i++) {
			D[i] = StringHelper.n2i(Dsub[i]);
		}
		String sql = "update studentavg set feedback=?,score=" + totalscore
				+ " where rollno=" + rollno;
		int list = DBUtils.executeUpdate(sql, "Y");
		// srno, fid, tid, uid
		String sql11 = "insert into feedbackstudent (fid,tid,uid) values(?,?,?)";
		int list11 = DBUtils.executeUpdate(sql11, fid, tid, uid);
		if (list > 0) {
			String sql1 = "insert into feedback (A1, A2, A3, A4, A5, A6, A7, B1, B2, B3, B4, C1, C2, C3, D1, D2, D3, D4, D5, D6, D7, D8, D9, D10, uid, fileid, tid,rollno,score) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			int list1 = DBUtils.executeUpdate(sql1, Asub[0], Asub[1], Asub[2],
					Asub[3], Asub[4], Asub[5], Asub[6], Bsub[0], Bsub[1],
					Bsub[2], Bsub[3], Csub[0], Csub[1], Csub[2], Dsub[0],
					Dsub[1], Dsub[2], Dsub[3], Dsub[4], Dsub[5], Dsub[6],
					Dsub[7], Dsub[8], Dsub[9], uid, fid, tid, rollno,
					totalscore);

			if (list1 > 0) {
				success = "User registered Successfully";

			} else {
				success = "Error adding user to database";
			}
		} else {
			success = "Error adding user to database";
		}
		return success;
	}

	public static String uploadMarks(HashMap parameters) {
		System.out.println(parameters);
		String success = "";
		String uid = StringHelper.n2s(parameters.get("uid"));
		String spliteUid[] = uid.split("_");
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String sql = "insert into selfevaluationresult (marks,uid,year) values(?,?,?)";

		int list = DBUtils.executeUpdate(sql, spliteUid[1], spliteUid[0], year);
		if (list > 0) {
			success = "Your Result Is :" + spliteUid[1];

		} else {
			success = "Error adding user to database";
		}

		return success;
	}

	public static String registerNewUser(HashMap parameters) {
		
		System.out.println(parameters);
		String success = "";
		String name = StringHelper.n2s(parameters.get("name"));
		String email = StringHelper.n2s(parameters.get("email"));
		String phone = StringHelper.n2s(parameters.get("phone"));
		String loginid = StringHelper.n2s(parameters.get("loginid"));
		String pass = StringHelper.n2s(parameters.get("pass"));
		String sql = "insert into useraccounts ( name, email, phone, loginid, pass) values(?,?,?,?,?)";
		int list = DBUtils
				.executeUpdate(sql, name, email, phone, loginid, pass);
		if (list > 0) {
			success = "User registered Successfully";
		} else {
			success = "Error adding user to database";
		}
		return success;
	}

	public static int reject(HashMap request) {
		String app = "N";
		String uid = StringHelper.n2s(request.get("uid"));
		System.out
				.println(app
						+ "==============================================================");
		String query = "update useraccounts set approved=? where uid=?";
		int i = DBUtils.executeUpdate(query, new Object[] { app, uid });
		return i;
	}

	public static double round(double value, int precision) {
		int scale = (int) Math.pow(10, precision);
		return (double) Math.round(value * scale) / scale;
	}

	public static ArrayList<Double> shuffle(ArrayList<Double> value) {
		ArrayList<Double> returnValues = new ArrayList<Double>();
		System.out
				.println("/////////////////////////////OUTPUT" + value.size());
		for (int i = 0; i < value.size(); i++) {
			double data = value.get(i);
			data = data + (Math.random() * 20);
			System.out.println(value.get(i) + "::" + data);
			returnValues.add(data);
		}
		System.out.println("/////////////////////////////OUTPUT END");
		return returnValues;
	}

	public static int approve(HashMap request) {
		String app = "Y";
		String uid = StringHelper.n2s(request.get("uid"));
		System.out
				.println(app
						+ "==============================================================");
		String query = "update useraccounts set approved=? where uid=?";
		int i = DBUtils.executeUpdate(query, new Object[] { app, uid });
		return i;
	}

	public static int approveFile(HashMap request) {
		String app = "Y";
		String fid = StringHelper.n2s(request.get("fid"));
		System.out
				.println(app
						+ "==============================================================");
		String query = "update teacherfiles set approve=? where fid=?";
		int i = DBUtils.executeUpdate(query, new Object[] { app, fid });
		return i;
	}
	// public static int analysis(HashMap request) {
	// String app = "";
	// String rollno = StringHelper.n2s(request.get("rollno"));
	// String query = "SELECT * FROM studentavg where rollno like "+rollno;
	// StudentAvgModel sm = null;
	// List<StudentAvgModel> studentrollno = DBUtils.getBeanList(
	// StudentAvgModel.class, query);
	// for (int i = 0; i < studentrollno.size(); i++) {
	// sm = (StudentAvgModel) studentrollno.get(i);
	// app=sm.
	// }
	// System.out.println(app
	// + "==============================================================");
	// String query = "update useraccounts set approved=? where uid=?";
	// int i = DBUtils.executeUpdate(query, new Object[] { app, uid });
	// return i;
	// }

}
