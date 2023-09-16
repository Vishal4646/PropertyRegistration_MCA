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
					<h4 class="page-title">Shared With Others</h4>
				

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
													<th>Request Sign</th>
													<th>Sign Status</th>
													<th></th>
												</tr>
											</thead>
											<tbody>
												<%
													UserModel um = (UserModel) session.getAttribute("USER_MODEL");
													List list = ConnectionManager.getSendList(um.getUid());
													if(list.size()>0){
													for (int i = 0; i < list.size(); i++) {
														ShareRequestDocModel dm = (ShareRequestDocModel) list.get(i);
												%>
												<tr>

													<td onclick="javascript:fnViewDoc('modalUpdateDoc<%=dm.getDid() %>')"><i class="la la-file-pdf-o"
														style="font-size: 15px;"></i> <%=dm.getFilename()%>
														<p class="card-category"
															style="color: green; font-size: 10px;">Done</p></td>
															
															
															
															<div class="modal fade" id="modalUpdateDoc<%=dm.getDid() %>" tabindex="-1" role="dialog" aria-labelledby="modalUpdatePro" aria-hidden="true">
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
										<embed id="pdfset" style="display:block" target="new" src="<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=viewFile&fid=<%=dm.getDid()%>"type="application/pdf" height="400px" width=" 480px">
									
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>
															
													<td><i class="la la-calendar"></i><%=dm.getCurrenttimedate()%></td>
													<td align="center">
														
													
															<p class="badge badge-secondary"
															
															> Document Send</p>
														

													</td>
													
													<td class="td-actions text-right">

														<button class="btn btn-primary dropdown-toggle"
															type="button" id="dropdownMenu1" data-toggle="dropdown">


															<i class="la  la-arrow-circle-down"
																style="font-size: 20px;"></i>
														</button>

														<ul class="dropdown-menu" role="menu"
															aria-labelledby="dropdownMenu">
															<!-- 		
																												download preview Email a copy -->
																												
															<a class="dropdown-item" target="_blank"
															href="<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=viewFile&fid=<%=dm.getDid()%>" type="application/pdf" download><i
																class="la la-cloud-download" style="font-size: 15px;"></i>
																Download</a>
															<a class="dropdown-item" href="javascript:fnViewDoc('modalUpdateDoc<%=dm.getDid() %>')"><i
																class="la la-folder-open-o" style="font-size: 15px;"></i>
																Preview</a>
															
															<a class="dropdown-item" href="#"  onclick="fnDeleteDocument('<%=dm.getDid()%>');"><i
																class="la la-close" style="font-size: 15px;"></i> Delete</a>
														</ul>
													</td>



												</tr>
												<%
													}}
													
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
function fnViewDoc(a){	
// 	alert(b);
// 	document.getElementById('docid').value=a;
	
// 	document.getElementById('docname').innerHTML='Document Name : '+b;
	$("#"+a).modal("show");
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