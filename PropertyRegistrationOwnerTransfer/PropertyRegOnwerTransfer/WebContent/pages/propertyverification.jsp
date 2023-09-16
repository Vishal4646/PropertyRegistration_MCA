<%@page import="com.model.VerifyRequestModel"%>
<%@page import="org.apache.catalina.mbeans.UserMBean"%>
<%@page import="com.model.ShareRequestDocModel"%>
<%@page import="com.model.DocumentModel"%>
<%@page import="java.util.List"%>
<%@page import="com.model.UserModel"%>
<%@page import="com.database.ConnectionManager"%>
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

		<jsp:include page="../tiles/header.jsp"></jsp:include>

		<jsp:include page="../tiles/menu.jsp"></jsp:include>
		<div class="main-panel">
			<div class="content">
				<div class="container-fluid">
					<h4 class="page-title">Registrar Property Verification Panel</h4>
					<div class="row">
						
						<div class="col-md-12">
							<div class="card">
							<div class="card-header">
										<div class="card-title">Pending For Verification</div>
										<input id="myInput" class="form-control w-50" type="text" placeholder="Search.."
										 style="margin-bottom: 1px; margin-top: 3px">
										
									</div> 
								<div class="card-body ">
									<div class="table-full-width">
										<table class="table">
											<thead>
												<tr style="text-align: center">
													<th>Sr.No.</th>
													<th>Property</th>
													<th>Owner</th>
													<th>Property ID</th>
													<th>Survey No.</th>
													<th>Area(Sq.Ft.)</th>
													<th>Reg.Date</th>
													<th>Status</th>
													<th>Address</th>
													<th>Sign/Reject</th>
													<th>Action</th>

												</tr>
											</thead>
											<tbody id="myTable">
												<%
													List list = ConnectionManager.getPendingDocList();
												int i = 0;
													for (i = 0; i < list.size(); i++) {
														VerifyRequestModel dm = (VerifyRequestModel) list.get(i);
														UserModel um = ConnectionManager.getUserNameFromUid(dm.getUserid());
												%>
												<tr>
												<td><%=i+1 %>.</td>

													<td style="cursor: pointer" onclick="javascript:fnViewDoc('<%=dm.getDid()%>')"><%=dm.getProptype()%></td>
															
															
													<td><%=um.getFname()%></td>
													<td><%=dm.getPropid()%></td>
													<td><%=dm.getSurveyno()%></td>
													<td><%=dm.getProparea()%></td>
													<% String da = dm.getCurrenttimedate();
													  da = da.substring(0, da.indexOf(' '));
													%>
													<td><%=da%></td>
													<td><%=dm.getStatus()%></td>
													<td><%=dm.getAddr()%></td>
															
						<div class="modal fade" id="modalUpdateDoc<%=dm.getDid()%>" tabindex="-1" role="dialog" aria-labelledby="modalUpdatePro" aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered" role="document">
								<div class="modal-content" >
									<div class="modal-header bg-primary">
										<h6 class="modal-title">
											<i class="la la-frown-o"></i> PDFViewer
										</h6>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body text-center"  heigth="500px"width="500px">
										<embed id="pdfset" style="display:block" target="new" src="<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=viewShareFile2&sid=<%=dm.getDid()%>"type="application/pdf" height="400px" width=" 480px">
									
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>
													
<!-- 													Sign/Reject Here -->
													<td class="td-actions text-left">

														<button class="btn btn-success dropdown-toggle"
															type="button" id="dropdownMenu1" data-toggle="dropdown">
															Sign/Reject
														</button>

														<ul class="dropdown-menu" role="menu"
															aria-labelledby="dropdownMenu">
															<!-- 		
																												download preview Email a copy -->
																												
															<button class="btn dropdown-item" onclick="javascript:fnSignFile('<%=dm.getDid()%>','<%=ConnectionManager.getFilenameFromdid(dm.getDid())%>');">
															<i class="la la-check text-success" style="font-size: 15px;"></i>
																Sign</button>
															<button class="btn dropdown-item" onclick="javascript:fnRejectFile('<%=dm.getDid()%>');"><i
																class="la la-close text-danger" style="font-size: 15px;"></i>
																Reject</button>
														</ul>
													</td>
													
													<td class="td-actions text-left">

														<button class="btn btn-primary dropdown-toggle"
															type="button" id="dropdownMenu1" data-toggle="dropdown">


															<i class="la  la-arrow-circle-down"
																style="font-size: 20px;"></i>
														</button>

														<ul class="dropdown-menu" role="menu"
															aria-labelledby="dropdownMenu">
															<!-- 		
																												download preview Email a copy -->
																												
																						
<!-- 															<a class="dropdown-item" target="_blank" -->
<%-- 															href="<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=viewShareFile&sid=<%=dm.getDid()%>" type="application/pdf" download><i --%>
<!-- 																class="la la-cloud-download" style="font-size: 15px;"></i> -->
<!-- 																Download</a> -->
															<a class="dropdown-item" href="javascript:fnViewDoc('<%=dm.getDid() %>')"><i
																class="la la-folder-open-o" style="font-size: 15px;"></i>
																Preview</a>
															
														</ul>
													</td>



												</tr>
												<%
													}
												%>

											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
						</div>
	
						
						<div class="modal fade" id="modalUpdate1" tabindex="-1"
							role="dialog" aria-labelledby="modalUpdatePro" aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered" role="document">
								<div class="modal-content">
									<div class="modal-header bg-primary">
										<h6 class="modal-title">
											<i class="la la-frown-o"></i> Request to Sign
										</h6>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">

										<form class="navbar-left navbar-form nav-search mr-md-3"
											id="requestsign" action="javascript:fnRequestSign();">
											<br>
											<div class="card-header " id="docname">Document Name :

											</div>
											<div class="card-header ">
												<br> Need to Sign before requesting?
												<p align="right">
													<br> <i class="la la-frown-o"></i>All sign by<input
														type="date" name="cdate">
												</p>
											</div>
											<input type="hidden" id="docid" name="docid">
											<div class="card-header ">
												<input type="text" id="successInput" name="uname"
													placeholder="Email ID or Mobile Number"> <br>
											</div>
											<div class="card-header ">
												<br>
												<textarea id="successInput" name="msg"
													placeholder="Add a message" style="width: 100%"></textarea>

											</div>
											<div class="card-header " align="center">
												<br> <input type="submit" class="btn btn-success"
													value="Submit" style="width: 50%">
											</div>
										</form>
										<!-- 					<p>Currently the pro version of the <b>Ready Dashboard</b> Bootstrap is in progress development</p> -->
										<!-- 					<p> -->
										<!-- 						<b>We'll let you know when it's done</b></p> -->
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>
				<div class="modal fade" id="modalUpdatesignin" tabindex="-1"
							role="dialog" aria-labelledby="modalUpdatePro" aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered" role="document">
								<div class="modal-content">
									<div class="modal-header bg-primary">
										<h6 class="modal-title">
											<i class="la la-frown-o"></i> Request to Sign
										</h6>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">

										<form class="navbar-left navbar-form nav-search mr-md-3"
											 method="post" enctype="multipart/form-data"
											action="<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=signFile">
											<br>
											<div class="card-header " id="signdocname">Document Name :

											</div>
											<div class="card-header ">
												<br> Need to Sign before digital sign?
												<p align="right">
													<br> <input type="text" id="successInput" name="password"
													placeholder="Email Certificate Password">
												</p>
											</div>
											<input type="hidden" id="signdocid" name="signdocid">
											<input class="btn btn-default" accept="application/p12"
													type="file" name="file" class="form-control"><br><br>
												
											
											<div class="card-header " align="center">
												<br> <input type="submit" class="btn btn-success"
													value="Sign Document" style="width: 50%">
											</div>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>

				</div>
			</div>

			<jsp:include page="../tiles/footer.jsp"></jsp:include>
		</div>
	</div>
	</div>
	<!-- Modal -->

</body>
<script>
$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>
<jsp:include page="../tiles/footerinc.jsp"></jsp:include>
<script type="text/javascript">
function fnShowValue(a,b){	
// 	alert(b);
	document.getElementById('docid').value=a;
	
	document.getElementById('docname').innerHTML='Document Name : '+b;
	
	$("#modalUpdate1").modal("show");
}
function fnSignFile(a,b){	
// 	alert(b);
	document.getElementById('signdocid').value=a;
	
	document.getElementById('signdocname').innerHTML='Document Name : '+b;
	
	$("#modalUpdatesignin").modal("show");
}
//function fnViewDoc(a){	
// 	alert(b);
// 	document.getElementById('docid').value=a;
	
// 	document.getElementById('docname').innerHTML='Document Name : '+b;
//	$("#"+a).modal("show");
//}

function fnRejectFile(did){
	
	if(confirm('Are You Sure Want to Reject This Property?')){
		var str = "did=" + did;
	$.post("<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=rejectFile",
			str, function(data) {
				data = $.trim(data);
				
				if(data == "1"){
					alert("Property Rejected Successfully!");
					window.location.href="<%=request.getContextPath()%>/pages/propertyverification.jsp";
				}else{
					alert("Error Rejecting Property");
				}
				
			});

		}
}

function fnViewDoc(did){	
// 	alert(b);
// 	document.getElementById('docid').value=a;
	
// 	document.getElementById('docname').innerHTML='Document Name : '+b;
	//$("#"+a).modal("show");
		s='';
params = 'scrollbars=no,resizable=no,status=no,location=no,toolbar=no,menubar=no,width=1000,height=700';
s2="<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=viewSharedFile&fid="+did+"&inline=1";
	window.open(s2, 'My PDF', params);
	
}
function fnRequestSign(){	
var str = $("#requestsign" ).serialize();

$.post("<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=requestsign",
		str, function(data) {
			data = $.trim(data);
			alert(data);
			
				window.location.href="<%=request.getContextPath()%>/pages/dashboard.jsp";
			
			$('#myform')[0].reset();
		});

	}
	
</script>
</html>