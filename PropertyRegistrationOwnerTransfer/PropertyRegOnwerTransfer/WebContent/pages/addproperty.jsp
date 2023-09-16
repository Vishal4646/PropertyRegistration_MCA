<%@page import="com.model.DocumentModel"%>
<%@page import="java.util.List"%>
<%@page import="com.model.UserModel"%>
<%@page import="com.database.ConnectionManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=utf-8 />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="../tiles/inc.jsp"></jsp:include>

</head>
<body>
	<div class="wrapper">

		<jsp:include page="../tiles/header.jsp"></jsp:include>

		<jsp:include page="../tiles/menu.jsp"></jsp:include>
		<div class="main-panel">
			<div class="content">
				<div class="container-fluid">
					<div class="row"></div>

						<div class="col-md-12">
							<div class="card">
									<div class="card-header">
										<div class="card-title">Add Property</div>
									</div>
								<div class="card-body ">
									<div class="table-full-width">
										<div class="col-md-12">
											<form class="navbar-left navbar-form nav-search mr-md-3"
												method="post" enctype="multipart/form-data"
												action="<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=uploadFile">
												<div class="row">
													<div class="card-header col-md-6">
														Reg. Document:<BR> <input class="btn btn-default "
															accept="application/pdf" required="required" type="file"
															name="file" class="form-control"
															style="width: 70%; background-color: #ffffff; border: 1px solid #8a8a8a;"><br>
														<br>

														<!-- 														Property ID: -->
														<input type="text" name="propid" placeholder="Property ID"
															required="required" class="form-control btn-default"
															style="width: 70%; background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px">

														<!-- 														Survey No.: -->
														<input type="text" name="surveyno"
															placeholder="Survey No." required="required"
															class="form-control btn-default"
															style="width: 70%; background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px">

														<!-- 														Property Type.: -->
<!-- 														<input type="text" name="proptype" -->
<!-- 															placeholder="Property Type" required="required" -->
<!-- 															class="form-control btn-default" -->
<!-- 															style="width: 70%; background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px"> -->

														<select class="form-control btn-default" name="proptype" required="required" placeholder="Property Type"
														style="width: 70%; background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px">
															<option selected disabled>Property Type</option>
															<option>Land</option>
															<option>House</option>
															<option>Shop</option>
															<option>Apartment</option>
															<option>Farm</option>
														</select>

														<!-- 														Property Area: -->
														<input type="text" name="proparea"
															placeholder="Area(sq.ft.)" required="required"
															class="form-control btn-default"
															style="width: 70%; background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px">
														<BR>
														<!-- 													<button type="submit" class="form-control btn btn-default"> -->
														<!-- 														<i class="la la-cloud-upload" style="font-size: 20px"></i> -->
														<!-- 														Upload -->
														<!-- 													</button> -->

													</div>

													<div class="card-header col-md-6">
														<BR>
														<!-- 														Address: -->
														<textarea type="text" name="addr" placeholder="Address"
															required="required" class="form-control btn-default"
															style="width: 70%; background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px"></textarea>

														<!-- 														City: -->
														<!-- 													<input  type="text" name="propcity" placeholder="City" -->
														<!-- 														required="required" class="form-control btn-default" -->
														<!-- 														style="width: 70%; background-color: #ffffff; border:1px solid #8a8a8a; margin-bottom: 15px"></input> -->

														Previous Owner Details :<BR class="mt-1"> <input
															type="text" name="preownername" placeholder="Full Name"
															required="required" class="form-control btn-default mt-3"
															style="width: 70%; background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px">

														<!-- 														Address: -->
														<textarea type="text" name="preowneraddr"
															placeholder="Address" required="required"
															class="form-control btn-default"
															style="width: 70%; background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px"></textarea>

														<div style="width: 70%">
															<button type="submit"
																class="btn btn-default btn-submit float-right">
																<i class="la la-cloud-upload" style="font-size: 20px"></i>
																Upload
															</button>
														</div>

													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
						<%@include file="uploadFileFragment.jsp"%>


				</div>
			</div>

			<jsp:include page="../tiles/footer.jsp"></jsp:include>
		</div>
	</div>
	</div>
	<!-- Modal -->

</body>

<jsp:include page="../tiles/footerinc.jsp"></jsp:include>

</html>