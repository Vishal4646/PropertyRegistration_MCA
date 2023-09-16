<%@page import="com.model.DocumentModel"%>
<%@page import="com.model.SignRequestModel"%>
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
<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<%System.err.println("on transferdetails page"); %>

	<div class="wrapper">

		<jsp:include page="../tiles/header.jsp"></jsp:include>

		<jsp:include page="../tiles/menu.jsp"></jsp:include>
		<div class="main-panel">
			<div class="content">
				<div class="container-fluid">
				<div class="row">
				<h4 class="page-title col-md-11">Details</h4>
				<div class=" col-md-1"><a style="text-align: center" href="<%=request.getContextPath()%>/pages/propertytransferverif.jsp" id="btnsbt"
																class="btn btn-back float-right la la-arrow-left " onmouseover="this.style.color='black'">
																Back</a>
																</div>
																</div>
<%
 	UserModel um = (UserModel) session.getAttribute("USER_MODEL");
 %>
 				<form id="mainform">
 					<div class="row">
 					
						<div class="col-md-6">
							<div class="card p-4">
								<div class="card-header">
										<div class="card-title btn btn-primary  text-white">Property Details</div>
									</div>
									 
								<div class="card-body ">
								
													<% List docs = ConnectionManager.getDocumentFromPropId(request.getParameter("propid"));
													DocumentModel dc = (DocumentModel) docs.get(0);
													List sh = ConnectionManager.getSignRequestDocsFromDid(dc.getDid());
													SignRequestModel sm = (SignRequestModel) sh.get(0);
													List preowner = ConnectionManager.getUserDetailsFromAdhar2(request.getParameter("preowneradhar"));
													UserModel preo = (UserModel) preowner.get(0);
													List newowner = ConnectionManager.getUserDetailsFromAdhar2(request.getParameter("newowneradhar"));
													UserModel newo = (UserModel) newowner.get(0);
													
													%>
														Property Type<p class="font-weight-bold"><%= dc.getProptype()%></p>
														<!-- 														Property ID: -->
														
														Property ID<p class="font-weight-bold"><%= dc.getPropid()%></p>
															
															
														<!-- 														Survey No.: -->
														 Survey Number<p class="font-weight-bold"><%= dc.getSurveyno()%></p>
														
														<!-- 														Date of Registration: -->
														
														Date of Registration<p class="font-weight-bold"><%= dc.getCurrenttimedate()%></p>
														
														<!-- 														Address: -->
														Property Location<p class="font-weight-bold"><%= dc.getAddr()%></p>
														
														<!-- 														Name of Registrar: -->
														 Registrar<p class="font-weight-bold"><%= dc.getRegistrar()%></p>
															
														
													</div>
													</div>
													</div>
														<div class="col-md-6">
															<div class="card p-4">
																<div class="card-header">
																	<div class="card-title btn btn-primary  text-white">Previous Owner Details</div>
																</div>
															<div class="card-body ">
															<div class="align-content-center mb-3">
															<img id="profile" style="width: 150px;height: 150px;" src="<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=showPicture&adhar=<%= preo.getAdharno()%>" />
															</div>
														<!-- 														Adhar: -->
														Adhaar Number<p class="font-weight-bold"><%= preo.getAdharno()%></p>
														<p type="text" id="count"></p>
														
														<!-- 														NewOwner Name: -->
														 Name<p class="font-weight-bold"><%= preo.getFname()%></p>
															

														<!-- 														Mobile: -->
														 Mobile<p class="font-weight-bold"><%= preo.getMobile()%></p>
															

														<!-- 														Email: -->
														 Email<p class="font-weight-bold"><%= preo.getEmailid()%></p>
														
												</div>
												</div>
												</div>
												
														<div class="col-md-6">
															<div class="card p-4">
																<div class="card-header">
																	<div class="card-title btn btn-primary  text-white">New Owner Details</div>
																</div>
															<div class="card-body ">
																<div class="align-content-center mb-3">
															<img id="profile2" style="width: 150px;height: 150px;" src="<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=showPicture&adhar=<%= newo.getAdharno()%>"/>
															</div>
														<!-- 														Adhar: -->
														Adhaar Number<p class="font-weight-bold"><%= newo.getAdharno()%></p>
														<p type="text" id="count"></p>
														
														<!-- 														NewOwner Name: -->
														 Name<p class="font-weight-bold"><%= newo.getFname()%></p>
															

														<!-- 														Mobile: -->
														 Mobile<p class="font-weight-bold"><%= newo.getMobile()%></p>
															

														<!-- 														Email: -->
														 Email<p class="font-weight-bold"><%= newo.getEmailid()%></p>
														
												</div>
												</div>
												</div>
												
												
													<div class="col-md-6">
															<div class="card p-4">
																<div class="card-header">
																	<div class="card-title btn btn-primary text-white">Selling Details</div>
																</div>
															<div class="card-body ">
														<!-- 														Adhar: -->
														Property Area (Sq.m)<p class="font-weight-bold"><%= dc.getProparea()%></p>
														
														<!-- 														Adhar: -->
														Market Value<p class="font-weight-bold"><%= sm.getMarketval()%></p>
														
														
														<!-- 														NewOwner Name: -->
														 Sell Value (Rs.)<p class="font-weight-bold"><%= sm.getSellval()%></p>
															

														<!-- 														Mobile: -->
														 Stamp Duty (6% of Sell Value) (Rs.)<p class="font-weight-bold"><%= sm.getStampduty()%></p>
															

														<!-- 														Email: -->
														 Registration Fee (Rs.)<p class="font-weight-bold"><%= sm.getRegfee()%></p>
														
														<!-- 														Email: -->
														 Total Cost (Rs.)<p class="font-weight-bold"><%= sm.getTotalcost()%></p>
															
															 Payment Received<p class="font-weight-bold"><%= sm.getPayment()%></p>
															
															
															
															

														<div style="margin: auto">
															
														</div>

												</div>
												</div>
												</div>
												
									</div>
											</form>	
											
									<div class="row">
										<div class="col-6 float-right text-right">
											<button class="btn btn-success" onclick="fnTransferProp(<%=sm.getRid()%>)">Permit & Transfer Property</button>
										</div>
										<div class="col-6 float-left text-left">
											<button class="btn btn-danger" onclick="fnRejectTransferProp(<%=sm.getRid()%>)">Reject Trasfer of Property</button>
										</div>
									
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

<script>
		function fnTransferProp(rid){
			console.log("Transfering this property: " + rid);
			if(confirm("Are you sure to Transfer the Property to new Owner?")){
				$.post("<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=transferOfProperty&rid="+rid,
						"", function(data) {
							data = $.trim(data);

							console.log("data: " +data);
						
							if(data==1){
								console.log("Transfer of Property is Successfull!");
								alert("Transfer of Property is Successfull!");
								window.location.href = "<%=request.getContextPath()%>/pages/propertytransferverif.jsp";
							}else{
								alert("Some error occured while processing!: " + data);
							}
							
						});
			}else{
				alert("Transfer of Property operation Cancelled!");
			}
		}

</script>

</html>