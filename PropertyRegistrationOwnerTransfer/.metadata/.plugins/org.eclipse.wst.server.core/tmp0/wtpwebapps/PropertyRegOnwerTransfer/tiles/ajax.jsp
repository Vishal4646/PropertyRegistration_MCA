<%@page import="com.model.VerifyRequestModel"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="com.algo.RSA"%>
<%@page import="java.io.FileOutputStream"%>
<%@ page trimDirectiveWhitespaces="true" %>

<%@page import="java.io.OutputStream"%>

<%@page import="com.model.ShareRequestOneMailDocModel"%>
<%@page import="com.model.ShareRequestDocModel"%>
<%@page import="com.constant.ServerConstants"%>
<%@page import="com.helper.TestFileDemo"%>
<%@page import="com.pdf.Randomkey"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.security.Key"%>   
<%@page import="com.algo.AES"%>
<%@page import="com.model.DocumentModel"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="com.helper.HttpHelper"%>
<%@page import="com.model.UserModel"%>
<%@page import="com.database.ConnectionManager"%>   
<%@page import="com.helper.StringHelper"%>
<%@page import="java.util.HashMap"%>

<%
	String sMethod = StringHelper.n2s(request.getParameter("methodId"));
	String returnString = "";
	System.out.println("HIIIII");
	boolean bypasswrite = false;

	HashMap parameters = StringHelper.displayRequest(request);
	System.out.println("parameters: " + parameters);

	
	
	
	
	if (sMethod.equalsIgnoreCase("registerNewUser")) {
		HashMap uploadMap=HttpHelper.parseMultipartRequest(request);
		uploadMap.putAll(parameters);
		System.out.println("uploadMapuploadMap: " + uploadMap);
		
		
		FileItem fi=(FileItem)uploadMap.get("fileITEM");
// 		fi.write(new File("E:/test/a.pdf"));
		System.out.println("uploadmap: " + uploadMap);
		System.out.println("filenaem: " + fi.getName());
		
// 		String message = ConnectionManager.uploadPropertyNew(fi,um.getUid(),um.getRole(),um.getEmailid(), key, propid, uploadMap.get("surveyno").toString(), uploadMap.get("proptype").toString(), uploadMap.get("proparea").toString(), uploadMap.get("addr").toString(), uploadMap.get("preownername").toString(), uploadMap.get("preowneraddr").toString());


		returnString = ConnectionManager.insertUser(fi, uploadMap);
		response.sendRedirect(request.getContextPath()+"/pages/login.jsp");
		
		
	}else if (sMethod.equalsIgnoreCase("deleteUser")) {
		String id= request.getParameter("uid");
		returnString = ConnectionManager.deleteuser(id);
	}  else if (sMethod.equalsIgnoreCase("requestsign")) {
		UserModel um=(UserModel)session.getAttribute("USER_MODEL");
		String id= um.getUid();
		parameters.put("uid", id);
		returnString = ConnectionManager.requestDocumentSign(parameters,um);
	}  else if (sMethod.equalsIgnoreCase("sendotponmobile")) {
		
		String otp=  ConnectionManager.sendOTPOnmobilNo(parameters);
		session.setAttribute("tempOTP",otp);
		returnString ="1";
	}  else if (sMethod.equalsIgnoreCase("rejectFile")) {
		
		returnString =  ConnectionManager.rejectProperty(parameters);
	}
	else if (sMethod.equalsIgnoreCase("verifyotponmobile")) {
		String enterotp = StringHelper.n2s(parameters.get("otpdata"));
		String sid = StringHelper.n2s(parameters.get("ssid"));
		
		
		
// 		String otp = StringHelper.n2s(session.getAttribute("tempOTP"));
		List <ShareRequestOneMailDocModel> list = ConnectionManager.getEmailSharebleFidOnSid(sid);
		ShareRequestOneMailDocModel sm=(ShareRequestOneMailDocModel)list.get(0);
		String otp=sm.getOtp();
		if (enterotp.equals(otp)) {
	
	
	byte[] encdata = TestFileDemo.readFileDataInByte(ServerConstants.FILE_UPLOAD_PATH
					+ "/" + sm.getFilename());

	AES a = new AES();

	String keystr = sm.getAeskey();
	Key key = a.generateKey(keystr);
	byte[] dec1 = a.decryptUsingKey(encdata, key);
	//	 		session.setAttribute("OTP",otp);
	
	
	ConnectionManager.writeBytes(sm.getFilename(), dec1, response,false);
	
	/*
	response.setContentType("application/pdf");
	BufferedOutputStream output = new BufferedOutputStream(
			response.getOutputStream());

	for (int i = 0; i < dec1.length; i++) {
		output.write(dec1[i]);
	}
	output.close();
	response.getOutputStream().flush();
	response.getOutputStream().close();
	*/
	returnString="1" ;
		}
		else{
	returnString="0";
		}
	}
	else if (sMethod.equalsIgnoreCase("download")) {
		
		String sid = StringHelper.n2s(parameters.get("ssid"));
		List <ShareRequestOneMailDocModel> list = ConnectionManager.getEmailSharebleFidOnSid(sid);
		ShareRequestOneMailDocModel sm=(ShareRequestOneMailDocModel)list.get(0);
		
	
	byte[] encdata = TestFileDemo.readFileDataInByte(ServerConstants.FILE_UPLOAD_PATH
					+ "/" + sm.getFilename());

	AES a = new AES();

	String keystr = sm.getAeskey();
	Key key = a.generateKey(keystr);
	byte[] dec1 = a.decryptUsingKey(encdata, key);
	//	 		session.setAttribute("OTP",otp);

		ConnectionManager.writeBytes(sm.getFilename(), dec1, response,true);
	
	return;
		}
	
// 	else if (sMethod.equalsIgnoreCase("uploadFile")) { 
// 		UserModel um=(UserModel)session.getAttribute("USER_MODEL");
// 		HashMap uploadMap=HttpHelper.parseMultipartRequest(request);
// 		uploadMap.putAll(parameters);
// 		if(session.getAttribute("USER_MODEL")==null){
// 			 request.getRequestDispatcher("../pages/login.jsp").forward(request, response);
// 		}
		
		
// 		FileItem fi=(FileItem)uploadMap.get("fileITEM");
// // 		fi.write(new File("E:/test/a.pdf"));
// 		System.out.println(uploadMap);
// 		System.out.println("usermodel values:"+fi);
// 		System.out.println(um.getUid()+":"+um.getEmailid()+":"+um.getRole()+":"+um.getSecretkey());
// 		String message=ConnectionManager.uploadDocument(fi,um.getUid(),um.getRole(),um.getEmailid(), um.getSecretkey());

// 		request.getRequestDispatcher("../pages/dashboard.jsp").forward(request, response);
// 	}
	else if (sMethod.equalsIgnoreCase("deleteFile")) {
		String did=StringHelper.n2s(parameters.get("did"));
		 ConnectionManager.deleteFile(did);
		 request.getRequestDispatcher("../pages/dashboard.jsp").forward(request, response);
	}
	else if (sMethod.equalsIgnoreCase("requestShare")) {
		UserModel um=(UserModel)session.getAttribute("USER_MODEL");
		String id= um.getUid();
		parameters.put("uid", id);
		parameters.put("user_email", um.getEmailid());
		returnString = ConnectionManager.updateRequestShare(parameters);
	}      
	else if (sMethod.equalsIgnoreCase("requestShareOnEmail")) {
		UserModel um=(UserModel)session.getAttribute("USER_MODEL");
		String id= um.getUid();
		parameters.put("uid", id);
		
		
		returnString = ConnectionManager.updateRequestShareOnEmail(parameters);
	}    
	else if (sMethod.equalsIgnoreCase("getUserDetailsFromAdhar")) {
		System.out.println("in here in ajax");
		returnString = ConnectionManager.getUserDetailsFromAdhar(parameters);
		System.out.println("out of ajax");
	}  else if (sMethod.equalsIgnoreCase("showPicture")) {
		String adhar=StringHelper.n2s(parameters.get("adhar"));
		System.out.println("adharid:: " + adhar);
		System.out.println("path:: " + ServerConstants.LOCAL_UPLOAD+"\\"+adhar+".png");
		ImageIO.write(ImageIO.read(new File(ServerConstants.LOCAL_UPLOAD+"\\"+adhar+".png")), "PNG", response.getOutputStream());
		System.out.println("out of ajax");
	}    
	
	
	
	else if (sMethod.equalsIgnoreCase("uploadFile")) {
		UserModel um=(UserModel)session.getAttribute("USER_MODEL");
		HashMap uploadMap=HttpHelper.parseMultipartRequest(request);
		uploadMap.putAll(parameters);
		System.out.println("uploadMapuploadMap: " + uploadMap);
		if(session.getAttribute("USER_MODEL")==null){
	 request.getRequestDispatcher("../pages/login.jsp").forward(request, response);
		}
		
		
		FileItem fi=(FileItem)uploadMap.get("fileITEM");
// 		fi.write(new File("E:/test/a.pdf"));
		System.out.println("uploadmap: " + uploadMap);
		
		String key=Randomkey.randomString(16);
		
		System.out.println("usermodel values:"+fi);
		System.out.println(um.getUid()+":"+um.getEmailid()+":"+um.getRole()+":"+key);
		String propid = (String) uploadMap.get("propid");
		System.out.println("propid is " + propid);
		String message = ConnectionManager.uploadPropertyNew(fi,um.getUid(),um.getRole(),um.getEmailid(), key, propid, uploadMap.get("surveyno").toString(), uploadMap.get("proptype").toString(), uploadMap.get("proparea").toString(), uploadMap.get("addr").toString(), uploadMap.get("preownername").toString(), uploadMap.get("preowneraddr").toString());
		
		response.sendRedirect(request.getContextPath()+"/pages/draftdocument.jsp");
		
	} 
	else if (sMethod.equalsIgnoreCase("addTransferReq")) {
		System.out.println("in here in addTransferReq");
		UserModel um=(UserModel)session.getAttribute("USER_MODEL");
// 		HashMap uploadMap=HttpHelper.parseMultipartRequest(request);
// 		uploadMap.putAll(parameters);
		if(session.getAttribute("USER_MODEL")==null){
	 request.getRequestDispatcher("../pages/login.jsp").forward(request, response);
		}
		
		
		System.out.println("uploadmap: " + parameters);
		
		System.out.println("details::: " + um.getUid() + ", survey: " + parameters.get("propid").toString());
		String did = ConnectionManager.checkPropertyAvailable(um.getUid(), parameters.get("propid").toString());
		if(did.equalsIgnoreCase("-1")){
		
			System.out.println("property not available");
			returnString = "Property not found for this property id";
			System.out.println("details::: " + um.getUid() + ", survey: " + parameters.get("propid").toString());
		}else{
			System.out.println("property available for did: " + did);
			System.out.println(um.getUid()+":"+um.getEmailid()+":"+um.getRole());
			String propid = (String) parameters.get("propid");
			System.out.println("propid is " + propid);
			String message = ConnectionManager.addTransferRequest(um.getUid(),um.getRole(),um.getEmailid(), did, propid, parameters);
// 			String message = ConnectionManager.addTransferRequest(um.getUid(),um.getRole(),um.getEmailid(), did, propid, parameters.get("dateofreg").toString(),
// 					parameters.get("surveyno").toString(), parameters.get("newownername").toString(), parameters.get("newownermob").toString(), parameters.get("newowneradhar").toString(),
// 					parameters.get("newowneremail").toString(), parameters.get("propaddrs").toString(), parameters.get("marketval").toString(), parameters.get("sellval").toString(),
// 					parameters.get("stampduty").toString(), parameters.get("regfee").toString(), parameters.get("totalcost").toString());
			returnString = message;
			
		}  
				
	}
	else if (sMethod.equalsIgnoreCase("getPropDetailsFromPropId")) {
		System.out.println("in here in getPropDetailsFromPropId");
		UserModel um=(UserModel)session.getAttribute("USER_MODEL");
		
		System.out.println("uploadmap: " + parameters);
		
			System.out.println(um.getUid()+":"+um.getEmailid()+":"+um.getRole());
			String propid = (String) parameters.get("propid");
			System.out.println("propid is " + propid);
			String message = ConnectionManager.getPropDetailsFromPropId(parameters);
			returnString = message;
				
	}
	else if (sMethod.equalsIgnoreCase("signFile")) { 
		UserModel um=(UserModel)session.getAttribute("USER_MODEL");
		HashMap uploadMap=HttpHelper.parseMultipartRequest(request);
		uploadMap.putAll(parameters);
		if(session.getAttribute("USER_MODEL")==null){
	 request.getRequestDispatcher("../pages/login.jsp").forward(request, response);
		}
		
		
		FileItem fi=(FileItem)uploadMap.get("fileITEM");
// 		fi.write(new File("E:/test/a.pdf"));
		System.out.println(uploadMap);
		System.out.println("usermodel values:"+fi);
		System.out.println(um.getUid()+":"+um.getEmailid()+":"+um.getRole()+":"+um.getPublickey());
		String message=ConnectionManager.signDocument(fi,um.getUid(),uploadMap.get("signdocid").toString(),uploadMap.get("password").toString(), um.getFname());
		request.getRequestDispatcher("../pages/dashboard.jsp").forward(request, response);
	}
	else if (sMethod.equalsIgnoreCase("viewShareFile")) {
		String sid=StringHelper.n2s(request.getParameter("sid"));
		System.out.println("sid: " + sid);
		ShareRequestDocModel sm=ConnectionManager.getRecivedFileDataFromSid(sid);
		System.out.println("shreREqModel: " + sm);
		
		ConnectionManager.viewSharedDataFile(sm,response);
		
		
	}
	else if (sMethod.equalsIgnoreCase("viewShareFile2")) {
		String sid=StringHelper.n2s(request.getParameter("sid"));
		System.out.println("sid: " + sid);
		VerifyRequestModel dm=ConnectionManager.getRecivedFileDataFromSid2(sid);
		System.out.println("shreREqModel: " + dm);
		
		ConnectionManager.viewSharedDataFile2(dm,response);
		
		
	}
	else if (sMethod.equalsIgnoreCase("activeemail")) {
		String email = request.getParameter("email");
		returnString = ConnectionManager.activeEmail(email); 

	}
	else if (sMethod.equalsIgnoreCase("checkPropAvail")) {
		String propid = request.getParameter("propid");
		System.out.println("propid for check: " + propid);
		returnString = ConnectionManager.checkPropAvail(propid); 
		System.out.println("propid for reutn: " + returnString);
	}
	else if (sMethod.equalsIgnoreCase("fPasswd")) {
		String password = request.getParameter("rpassword");
		String otp1= request.getParameter("otp");
		String otp=(String)session.getAttribute("OTP");
		System.out.println(otp1+" : "+otp);
		if(otp.equalsIgnoreCase(otp1)){
				returnString = ConnectionManager.fPasswd(parameters).trim();
				System.err.println("*** Okay");
		}
		else{
	returnString="error".trim();	
		}
	}
	else if (sMethod.equalsIgnoreCase("updateUser")) {
		returnString = ConnectionManager.updateUser(parameters);
	}
	else if (sMethod.equalsIgnoreCase("updatecharges")) {
		
		returnString = ConnectionManager.insertHospitalCharges(parameters);
	}
else if (sMethod.equalsIgnoreCase("updatepaycharges")) {
	
		returnString = ConnectionManager.updatepaycharges(parameters);
	}
else if (sMethod.equalsIgnoreCase("checkotp")) {
	UserModel um =	(UserModel)session.getAttribute("USER_MODEL");
	String otp=StringHelper.n2s(um.getOtp());
	String votp=StringHelper.n2s(request.getParameter("otp"));
	if(otp.equalsIgnoreCase(votp)){
		returnString="1";
	}else{
		returnString="0";
	}
}
	else if (sMethod.equalsIgnoreCase("activemobile")) {
		String mobile = request.getParameter("mobile");

		returnString = ConnectionManager.activeMobile(mobile);

	}
	


	else if (sMethod.equalsIgnoreCase("transferOfProperty")) {
		String rid=request.getParameter("rid");
		returnString = ConnectionManager.transferOfProperty(rid); 
	}
	else if (sMethod.equalsIgnoreCase("fnRequestToView")) {
		String did=request.getParameter("did");
		String uid=request.getParameter("uid");
		returnString = ConnectionManager.requestPropDetails(did,uid); 
	}

/* 	else if (sMethod.equalsIgnoreCase("sendOTP")) {
		String username1=request.getParameter("username1");
		String otp = ConnectionManager.sendOTPToMailAndMobile(username1);
		session.setAttribute("OTP",otp);
	} */
	
	else if (sMethod.equalsIgnoreCase("viewFile")) {
		UserModel um =	(UserModel)session.getAttribute("USER_MODEL");
		ConnectionManager.downloadFile(parameters, um, response,false);
 return;
	}
	
	else if (sMethod.equalsIgnoreCase("viewFile2")) {
		UserModel um =	(UserModel)session.getAttribute("USER_MODEL");
		ConnectionManager.downloadFile2(parameters, um, response,false);
 return;
	}
	else if (sMethod.equalsIgnoreCase("viewSharedFile")) {
		UserModel um =	(UserModel)session.getAttribute("USER_MODEL");
		ConnectionManager.downloadFile(parameters, um, response,false);
 return;
	}
	else if (sMethod.equalsIgnoreCase("viewTransferFile")) {
		UserModel um =	(UserModel)session.getAttribute("USER_MODEL");
		ConnectionManager.viewTransferFile(parameters, um, response,false);
 		return;
	}
	else if (sMethod.equalsIgnoreCase("changeSaleStat")) {
		String stat = request.getParameter("stat");
		String did = request.getParameter("did");
		System.out.println("ajax: " + stat + ", " + did);
		returnString = ConnectionManager.changeSaleStaus(stat,did);
	}
	else if (sMethod.equalsIgnoreCase("changeRequestStat")) {
		String stat = request.getParameter("stat");
		String reqid = request.getParameter("reqid");
		System.out.println("ajax: " + stat + ", " + reqid);
		returnString = ConnectionManager.changeRequestStaus(stat,reqid);
	}
	else if (sMethod.equalsIgnoreCase("checkLogin")) {
	UserModel um = ConnectionManager.checkLogin(parameters);
		if (um != null) {
		//UserModel um1 = ConnectionManager.checkLogin(parameters);
		//int random = (int )(Math.random() * 999 + 100);
		//ConnectionManager.sendOTPToMobile(um1.getMobile(),random+"");
// 				ConnectionManager.sendOTPToEmail(um1.getEmailid(),random+"");
// 				String OTP=random+"-"+nextrandom;
		//System.out.println("OTP :"+random);
		//um1.setOtp(random+"");
			session.setAttribute("USER_MODEL", um);
			returnString = "1";
		}else{
			returnString = "0";
		}

	}

	else if (sMethod.equalsIgnoreCase("logout")) {
		session.removeAttribute("USER_MODEL");
		bypasswrite = true;
%>
<script>
			window.location.href='<%=request.getContextPath()%>/pages/login.jsp';
</script>
<%
	}
	if (!bypasswrite) {
		out.println(returnString);
	}
%>
