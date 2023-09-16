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
					<h4 class="page-title">Shared With Me</h4>
					<div class="row">
						

					</div>

					<div class="row row-card-no-pd">

						<div class="col-md-10">
							<div class="card card-tasks">
								
								<div class="card-body ">
									<div class="table-full-width">
										<table class="table">
											<thead>
												<tr>

													<th>Name</th>
													<th>Last Updated</th>
													<th>Action</th>
<!-- 													<th>Sign Status</th> -->
													<th></th>
												</tr>
											</thead>
											<tbody>
												<%
													UserModel um = (UserModel) session.getAttribute("USER_MODEL");
												
													List list = ConnectionManager.getRecivedDocumentList(um.getUid());
													for (int i = 0; i < list.size(); i++) {
														ShareRequestDocModel sm = (ShareRequestDocModel) list.get(i);
												%>
												<tr>

													<td onclick="javascript:fnViewDoc('<%=sm.getDid()%>')"><i class="la la-file-pdf-o"
								  						style="font-size: 15px;"></i> <%=sm.getFilename()%>
														<p class="card-category"
															style="color: green; font-size: 10px;">Done</p></td>
															
															
															
															<div class="modal fade" id="modalUpdateDoc<%=sm.getSid()%>" tabindex="-1" role="dialog" aria-labelledby="modalUpdatePro" aria-hidden="true">
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
										<embed id="pdfset" style="display:block" target="new" src="<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=viewShareFile&sid=<%=sm.getSid()%>"type="application/pdf" height="400px" width=" 480px">
									
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>
															
													<td><i class="la la-calendar"></i><%=sm.getCurrenttimedate()%></td>
													
													
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
																												
															<a class="dropdown-item"  target="_blank"
															href="<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=viewShareFile&sid=<%=sm.getSid()%>" type="application/pdf" ><i
																class="la la-cloud-download" style="font-size: 15px;"></i>
																Download</a>
															<a class="dropdown-item" href="javascript:fnViewDoc('<%=sm.getDid() %>')"><i
																class="la la-folder-open-o" style="font-size: 15px;"></i>
																Preview</a>
															
															<a class="dropdown-item" href="#"  onclick="fnDeleteDocument('<%=sm.getDid()%>');"><i
																class="la la-close" style="font-size: 15px;"></i> Delete</a>
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
								<div class="card-footer ">
									<div class="stats">
										<i class="now-ui-icons loader_refresh spin"></i> Updated 3
										minutes ago
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
			</div>

			<jsp:include page="../tiles/footer.jsp"></jsp:include>
		</div>
	</div>
	</div>
	<!-- Modal -->

</body>

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