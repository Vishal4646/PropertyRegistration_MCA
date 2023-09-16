<%@page import="com.constant.ServerConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=utf-8 />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="../tiles/inc.jsp"></jsp:include>

</head>
<body>
	<div class="wrapper">

		<div class="main-header">
			<div class="logo-header">
				<a href="index.html" class="logo"> <%=ServerConstants.PROJECT_NAME %> </a>
			</div>
		</div>
		<div class="main-panel">
			<div class="content">
				<div class="container-fluid">
					<h4 class="page-title">Upload File</h4>
					<div class="row">
						<div class="col-md-3">
							<div class="card card-stats card-warning">
								<div class="card-body ">
									<div class="row">
										<form class="navbar-left navbar-form nav-search mr-md-3"
											method="post" enctype="multipart/form-data"
											action="<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=uploadFile">
											<div class="card-header ">
												<input class="btn btn-default" accept="application/pdf" type="file" name="file"
													class="form-control">
												<div class="input-group-append"></div>
												<br>
												<button type="submit" class="btn btn-default">
													<i class="la la-cloud-upload" style="font-size: 20px"></i>
													Upload
												</button>

											</div>
										</form>

									</div>
								</div>
							</div>
						</div>


					</div>



				</div>
			</div>

		</div>
	</div>
	</div>
	<!-- Modal -->

</body>

<jsp:include page="../tiles/footerinc.jsp"></jsp:include>
</html>