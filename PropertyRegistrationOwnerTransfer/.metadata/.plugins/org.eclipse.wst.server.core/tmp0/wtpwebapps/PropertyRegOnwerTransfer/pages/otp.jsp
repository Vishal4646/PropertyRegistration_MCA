<%@page import="com.helper.StringHelper"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="../tiles/inc.jsp"></jsp:include>

</head>
<body style="background-color: #0261b0">

	<%-- 		<jsp:include page="../tiles/header.jsp"></jsp:include> --%>

	<%-- 		<jsp:include page="../tiles/menu.jsp"></jsp:include> --%>

<%
String sid = StringHelper.n2s(request.getParameter("sid"));
%>


	<div class="" align="center"
		style="margin-top: 50px; margin-bottom: 50px;">
<h1 style="color: white;font-family: arial;">cert-cloud</h1>
		<div class="col-md-4">
			<div class="card" id="card">
				<div class="card-header">
					<div class="card-title">OTP Verification Page</div>
				</div>
				
				<div class="form-group has-success" id="otp" align="left" style="display: block;">
				<form  target="_new" name="myform1" id="myform1">
							<label for="successInput" align="center">Enter OTP for verification</label><br><br>
							<input type="text" id="otpdata" name="otpdata" placeholder="Enter otp"
								class="form-control"><br>
								<input type="hidden" value="<%=sid%>"id="successInput" name="ssid">
								<input type="button" class="btn btn-success"value="Verify OTP" onclick="fnCheck();">
								</form>
						</div>
						
			</div>
			<embed id="pdfset" style="display: none;" src="<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=download&ssid=<%=sid%>" type="application/pdf" height="800px" width=" 800px">
		</div>
		</div>
</body>
<script type="text/javascript">

function fnCheck(){
	
	var str = $("#myform1" ).serialize();
	$.post("<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=verifyotponmobile",
							str, function(data) {
								data = $.trim(data);
								
								<%if(sid.length()>0){%>
								
								document.getElementById("pdfset").style.display="block";
								<%}%>
								
// 	 							alert(data);
								if(data=="0"){
									alert("Please Enter Correct OTP.??");
									
								} else {
									document.getElementById("card").style.display="none"
									alert("Successfully Verify OTP.!!");
									document.getElementById("pdfset").style.display="block";
								}
								$('#myform')[0].reset();
							});

		}
</script>
<jsp:include page="../tiles/footerinc.jsp"></jsp:include>
</html>