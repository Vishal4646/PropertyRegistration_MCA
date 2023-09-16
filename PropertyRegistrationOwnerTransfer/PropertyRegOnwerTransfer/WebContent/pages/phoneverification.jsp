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
String emailid = StringHelper.n2s(request.getParameter("emailid"));
%>

	<div class="" align="center"
		style="margin-top: 50px; margin-bottom: 50px;">
<h1 style="color: white;font-family: arial;">cert-cloud</h1>
		<div class="col-md-4">
			<div class="card">
				<div class="card-header">
					<div class="card-title">Verify Your Mobile no</div>
				</div>
				<div class="card-body" id="loginform">
					<form action="javascript:fnSubmit();" name="myform" id="myform">
						<div class="form-group has-success"align="left">
							<label for="successInput">Enter mobile no verification</label><br><br>
							<input type="text" id="successInput" name="mobileno" placeholder="mobile no"
								class="form-control">
								<input type="hidden" value="<%=sid%>"id="successInput" name="sid">
								<input type="hidden" value="<%=emailid%>"id="successInput" name="emailid">
						</div>

						<div class="card-action" align="left">
							<button type="submit"  class="btn btn-success">Send Otp</button>
						
						</div>
					</form>
					
				</div>
				
			</div>
		</div>
</body>
<script type="text/javascript">
function fnSubmit(){	
var str = $("#myform" ).serialize();
$.post("<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=sendotponmobile",
						str, function(data) {
							data = $.trim(data);
// 							alert(data);
							if(data=="0"){
								alert("Please Enter Correct Mobile no.??");
								
							} else {
								window.location.href="<%=request.getContextPath()%>/pages/otp.jsp?sid=<%=sid%>";
							}
							$('#myform')[0].reset();
						});

	}

</script>
<jsp:include page="../tiles/footerinc.jsp"></jsp:include>
</html>