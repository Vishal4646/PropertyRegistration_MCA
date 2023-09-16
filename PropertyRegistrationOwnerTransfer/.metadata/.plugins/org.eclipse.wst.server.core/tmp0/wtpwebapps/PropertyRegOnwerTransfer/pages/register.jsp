<%@page import="com.constant.ServerConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="../tiles/inc.jsp"></jsp:include>

</head>
<body
	style="background: url(../tiles/back1.jpg); background-repeat: round;">

	<%-- 		<jsp:include page="../tiles/header.jsp"></jsp:include> --%>

	<%-- 		<jsp:include page="../tiles/menu.jsp"></jsp:include> --%>



	<div class="" align="center"
		style="margin-top: 50px; margin-bottom: 50px;">
		<div style="background-color: #00000080;" class="col-8">
		<h2 style="color: white; font-family: arial;"><%=ServerConstants.PROJECT_NAME %></h2>
			</div>
		<div class="col-md-8">
			<div class="card">
				<div class="card-header">
					<div class="card-title">Registration page</div>
				</div>
				<div class="card-body">
					<form method="post" enctype="multipart/form-data"
						action="<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=registerNewUser"
						name="myform" id="myform">

						<div class="row">
							<div class="col-6">
								<div class="form-group has-success" align="left">
									<label for="successInput">Register Email</label><br>
									<input type="text" id="successInput" name="emailid" required
										placeholder="email" class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$">
								</div>
								<div class="form-group has-success" align="left">
									<label for="successInput">Register Password</label><br> 
									<input type="text" id="successInput" name="pass" required
										placeholder="password" class="form-control">
								</div>
								<div class="form-group has-success" align="left">
									<label for="successInput">Register Mobile</label><br>
									<input type="text" id="successInput" name="mobile" required
										placeholder="mobile no" class="form-control" pattern="[789][0-9]{9}">
								</div>
								<div class="form-group has-success" align="left">
									<label for="successInput">Full Name</label><br>
									<input type="text" id="successInput" name="fname" required
										placeholder="full Name" class="form-control">
								</div>
							</div>
							<div class="col-6">
								<div class="form-group has-success" align="left">
									<label for="successInput">PAN No.</label><br> <input
										type="text" id="successInput" name="panno" required
										placeholder="PAN card Number" class="form-control" pattern="[A-Z]{5}[0-9]{4}[A-Z]{1}">
								</div>
								<div class="form-group has-success" align="left">
									<label for="successInput">Aadhaar No</label><br>
									<input type="text" id="successInput" name="adharno" required
										pattern="[0-9]{12}" placeholder="12 Digit Aadhaar no"
										class="form-control">
								</div>

								<div class="form-group has-success" align="left">
									<label for="successInput">Photo</label><br> <input
										type="file" name="file" class="form-control"
										placeholder="Add Image" required>
									<!-- 						<input accept="image/*" required="required" type="image" name="image" placeholder="Add Image" class="form-group has-success"> -->
								</div>

								<div class="card-action" align="left">
									<button type="submit" class="btn btn-submit btn-success">Register
										User</button>
									<button type="reset" class="btn btn-danger">Reset</button>
									<br> <a
										href="<%=request.getContextPath()%>/pages/login.jsp">Already
										user login here..!!</a>
								</div>
							</div>
						</div>


					</form>
				</div>
			</div>

		</div>
	</div>
</body>
<script type="text/javascript">
function fnSubmit(){	
	var str = $("#myform" ).serialize();
	$.post("<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=registerNewUser",
							str, function(data) {
								data = $.trim(data);
								alert(data);
								// 							$('#myform')[0].reset();
							});

	}
</script>
<jsp:include page="../tiles/footerinc.jsp"></jsp:include>
</html>