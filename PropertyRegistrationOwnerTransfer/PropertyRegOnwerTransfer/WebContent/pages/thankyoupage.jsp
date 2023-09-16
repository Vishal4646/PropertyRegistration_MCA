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



	<div class="" align="center"
		style="margin-top: 50px; margin-bottom: 50px;">
<h1 style="color: white;font-family: arial;">cert-cloud</h1>
		<div class="col-md-4">
			<div class="card">
				<div class="card-header">
					<div class="card-title">Thank You..!!!</div>
				</div>
				<div class="card-body" id="loginform">
											<div class="form-group has-success"align="center">
							<label for="successInput">Thank You for visiting Website...!!!</label><br><br>
							
						</div>

						<div class="card-action" align="center">
							<button type="button"  value="OK" class="btn btn-success"align="center" onclick="fnCheck();">OK</button>
							
						</div>
				
					
				</div>
				
			</div>
		</div>
</body>
<script type="text/javascript">

function fnCheck(){
	window.location.href="<%=request.getContextPath()%>/pages/login.jsp";
	
		}
</script>
<jsp:include page="../tiles/footerinc.jsp"></jsp:include>
</html>