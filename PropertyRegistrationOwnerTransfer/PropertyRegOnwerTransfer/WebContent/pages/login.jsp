<%@page import="com.constant.ServerConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="../tiles/inc.jsp"></jsp:include>

</head>
<body style="background: url(../tiles/back1.jpg); background-repeat: round;">
	<div class="" align="center"
		style="margin-top: 50px; margin-bottom: 50px;">
<div style="background-color: #00000080;" class="col-8">
		<h2 style="color: white; font-family: arial;"><%=ServerConstants.PROJECT_NAME %></h2>
			</div>
		<div class="col-md-4">
			<div class="card">
				<div class="card-header">
					<div class="card-title">Login page</div>
				</div>
				<div class="card-body" id="loginform">
					<form action="javascript:fnSubmit();" name="myform" id="myform">
						<div class="form-group has-success"align="left">
							<label for="successInput">Enter Emailid</label><br><br>
							<input type="text" id="emailinp" name="emailid" placeholder="email"
								class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$">
						</div>
						<div class="form-group has-success"align="left">
							<label for="successInput">Enter Password</label><br><br>
							<input type="password" id="passinp" name="pass" placeholder="password"
								class="form-control">
						</div>

						<div class="card-action" align="left">
							<button type="submit"  class="btn btn-success">Login</button>
							<a href="<%=request.getContextPath()%>/pages/register.jsp">Register New User</a>
							<a href="javascript:clearForm()" class="text-right float-right">Clear</a>
						</div>
					</form>
					
				</div>
					<form action="javascript:fnCheck();" name="myform2" id="myform2">
				<div class="form-group has-success" id="otp" align="left" style="display: none;">
				
							<label for="successInput">Enter OTP for verification</label><br><br>
							<input type="text" id="otpdata" name="otpdata" placeholder="Enter otp"
								class="form-control"><br>
								<input type="submit" class="btn btn-success"value="Verify OTP" >
						</div>
						</form>
			</div>
		</div>
		</div>
</body>
<script type="text/javascript">
function clearForm(){
	$("#myform")[0].reset();
}

$(document).ready(function(){
		setTimeout(clearForm, 1000);
	});


function fnSubmit(){	
var str = $("#myform" ).serialize();
$.post("<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=checkLogin",
						str, function(data) {
							data = $.trim(data);
// 							alert(data);
							if(data=="0"){
								alert("Please Check Your Username And Password.??");
								window.location.href="<%=request.getContextPath()%>/pages/login.jsp";
							} else {
								//document.getElementById('loginform').style.display = 'none';
								//document.getElementById('otp').style.display = 'block';
								
							window.location.href="<%=request.getContextPath()%>/pages/dashboard.jsp";
							}
							$('#myform')[0].reset();
						});

	}
function fnCheck(){
	var a=document.getElementById("otpdata");
	var str = $("#myform" ).serialize();
	
	$.post("<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=checkotp&otp="+a.value,
							str, function(data) {
								data = $.trim(data);
//	 							alert(data);
								if(data=="0"){
									alert("Please Enter Correct OTP.??");
									
								} else {
									alert("Successfully Login.!!");
									window.location.href="<%=request.getContextPath()%>/pages/dashboard.jsp";
								}
								$('#myform')[0].reset();
							});

		}
</script>
<jsp:include page="../tiles/footerinc.jsp"></jsp:include>
</html>