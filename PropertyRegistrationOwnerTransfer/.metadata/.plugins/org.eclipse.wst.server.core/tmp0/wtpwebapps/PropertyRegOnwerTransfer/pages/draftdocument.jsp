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
					<h4 class="page-title">Recently Uploaded</h4>
					<div class="row"></div>

					<div class="row row-card-no-pd">

						<div class="col-md-12">
							<div class="card card-tasks">
							
								<div class="card-body ">
									<div class="table-full-width">
										<table class="table">
											<thead>
												<tr style="text-align: center">

													
													<th>Sr.No.</th>
													<th>Property</th>
													<th>Property ID</th>
													<th>Survey No.</th>
													<th>Area(Sq.Ft.)</th>
													<th>Reg.Date</th>
													<th>Address</th>
													<th>Status</th>
													<th>Action</th>
													<th>History</th>
												</tr>
											</thead>
											<tbody>
												<%
													UserModel um = (UserModel) session.getAttribute("USER_MODEL");
													List list = ConnectionManager
															.getWitoutSignDocumentList(um.getUid());
													for (int i = 0; i < list.size(); i++) {
														DocumentModel dm = (DocumentModel) list.get(i);
												%>
												<tr>
												<td><%= i+1 %>.</td>

													<td style="cursor: pointer" onclick="javascript:fnViewDoc('<%=dm.getDid()%>')"><i
														style="font-size: 15px;"></i> <%=dm.getProptype()%></td>





													<td><%=dm.getPropid()%></td>
													<td><%=dm.getSurveyno()%></td>
													<td><%=dm.getProparea()%></td>
													<% String da = dm.getCurrenttimedate();
													  da = da.substring(0, da.indexOf(' '));
													%>
													<td><%=da%></td>
													<td></i><%=dm.getAddr()%></td>
													
													<%if(dm.getStatus().equalsIgnoreCase("in-draft")){ %>
													
													<td><p class="card-category"
															style=" color: blue; cursor: pointer;"
															onclick="javascript:fnShowValue('<%=dm.getDid()%>','<%=dm.getDocName()%>');">
															Request to Verify</p></td>
															
														<%}else{ %>
													
                                                        <td align="center" style="color: #59d05d"><span href="#" class="badge" style="font-size: 12px"
														data-toggle="modal" data-target="#modalUpdate2"><a class="text-secondary"><%=dm.getStatus().equalsIgnoreCase("in-draft")? "In-Draft": ""%></a>
														<a class="text-success"><%=dm.getStatus().equalsIgnoreCase("verified")? "Verified": ""%></a>
														<a class="text-danger"><%=dm.getStatus().equalsIgnoreCase("rejected")? "Rejected": ""%></a></span></td>
													
													<%} %>
													
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

															<a class="dropdown-item" target="_blank"
																href="<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=viewFile&fid=<%=dm.getDid()%>"
																type="application/pdf"> <i
																class="la la-cloud-download" style="font-size: 15px;"></i>Download
															</a>
															<a class="dropdown-item"
																href="javascript:fnViewDoc('<%=dm.getDid()%>')"> <i
																class="la la-folder-open-o" style="font-size: 15px;"></i>Preview
															</a>
															<a class="dropdown-item"
																href="javascript:fnEmailDoc('<%=dm.getDid()%>','<%=dm.getDocName()%>')">
																<i class="la la-send" style="font-size: 15px;"></i>
																Email a copy
															</a>
															<a class="dropdown-item"
																href="#"  onclick="fnDeleteDocument('<%=dm.getDid()%>');"><i
																class="la la-close" style="font-size: 15px;"></i> Delete</a>
																
														</ul>
													</td>


													
													<td><a target="_blank" href="<%=request.getContextPath()%>/pages/blckhnview.jsp?pid=<%=dm.getDid()%>">Show History</a></td>


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
						
						<div id="loadModalsHere">
						
						</div>
						<%@include file="uploadFileFragment.jsp"%>

					
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
function fnShareDocumentOnMail(){	
	var str = $("#requestShareOnEmail" ).serialize();

	$.post("<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=requestShareOnEmail",
			str, function(data) {
				data = $.trim(data);
				alert(data);
				
					window.location.href="<%=request.getContextPath()%>/pages/dashboard.jsp";

							$('#myform')[0].reset();
						});

	}
function fnEmailDoc(a,b){	
	alert(b);
	document.getElementById('shareonemaildocid').value=a;
	
	document.getElementById('shareonemaildocname').innerHTML='Document Name : '+b;
	
	$("#modalShareOnEmailDoc").modal("show");
}


function fnViewDoc(did){	
// 	alert(b);
// 	document.getElementById('docid').value=a;
	
// 	document.getElementById('docname').innerHTML='Document Name : '+b;
	//$("#"+a).modal("show");
		s='';
params = 'scrollbars=no,resizable=no,status=no,location=no,toolbar=no,menubar=no,width=1000,height=700';
s2="<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=viewFile2&fid="+did+"&inline=1";
	window.open(s2, 'My PDF', params);
	
}

function fnShowValue(a,b){	
// 	alert(b);
	
// 	$("#modalUpdate1").modal("show");
	
	$("#loadModalsHere").load('requestToSign.jsp', function() {
		document.getElementById('docid').value=a;
		document.getElementById('docname').innerHTML='Document Name : '+b;
		
		$("#signReqModal").modal("show");
	});
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