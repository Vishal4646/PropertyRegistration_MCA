package com.constant;

import java.io.File;
import java.util.HashMap;

public class ServerConstants {
	public static final String SYSTEM_IP = "192.168.0.103";
	
	public static final String PROJECT_NAME = "Property Registration & Owner Transfer";
	public static final String db_driver = "com.mysql.jdbc.Driver";
	public static final String db_user = "root";
	public static final String db_pwd = "";
	public static final String db_url = "jdbc:mysql://localhost/PropertySchema";
	//public static final String ADMIN_EMAIL_ID = "dgfgfdgf@gmail.com";
	//public static final String ADMIN_EMAIL_PASS = "gfsgsfa";
	public static final String SMS_URL = "";
	public static boolean SEND_SMS= false;
    
	public static String getLastMerkelPath(){
		return project_path+"\\lastMerkelNode";
	}
	
	public static String getLastMerkelPath(String pid){
		return project_path+"\\lastMerkelNode_"+pid;
	}
	

	public static String blockChainFileName="PropertyBlockChain.bin";
	public static int difficulty = 5;
	
	public static final String send_verification_link = "localhost:8080";
	public static final String send_file_on_email_link = "localhost:8080/SecurePrivateCloudNew/pages/otp.jsp";
	public static final String project_path = "D:\\work\\project\\PropertyRegistrationOwnerTransfer";
	public static final String keys_path = project_path+"\\"+"keys-users\\";

	public static String ADMIN_MOBILE_NUMBER = "1111111111";
	public static String ADMIN_email = "1111111111";

	public static final HashMap hm = new HashMap();
	public static String TEMP_HOME = "D:/work/tmp";

	public static final String LOCAL_UPLOAD = TEMP_HOME;   
	public static final String FILE_UPLOAD_PATH = TEMP_HOME;

	
}
