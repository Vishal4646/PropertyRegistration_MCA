<%@page import="com.model.TransferRecords"%>
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
					<h4 class="page-title">Transfered Properties Record</h4>
					<div class="row"></div>

					<div class="row row-card-no-pd">

						<div class="col-md-12">
							<div class="card card-tasks">
								
								<div class="card-body ">
								<%
								UserModel um = (UserModel) session.getAttribute("USER_MODEL");
												List list = ConnectionManager.getTransferedPropRecords();
													
													if(list.size()>0){
													%>
									<div class="table-full-width">
										<table class="table">
											<thead>
												<tr style="text-align: center">

													<th>Sr.No.</th>
													<th>Property</th>
													<th>Property ID</th>
													<th>Pre.OwnerId</th>
													<th>Pre.OwnerName</th>
													<th>New.OwnerId</th>
													<th>New.OwnerName</th>
													<th>Transfer Date</th>
													<th>Address</th>
													<th>Registrar</th>
												</tr>
											</thead>
											<tbody>
												<%
												int i = 0;
													for ( i = 0; i < list.size(); i++) {
														TransferRecords dm = (TransferRecords) list.get(i);
												%>
												<tr>
												<td><%= i+1 %>.</td>

													<td> <%=dm.getProp()%></td>
													<td><%=dm.getPid()%></td>
													<td><%=dm.getPreoid()%></td>
													<td><%=dm.getPreoname()%></td>
													<td><%=dm.getNewoid()%></td>
													<td><%=dm.getNewoname()%></td>
													<td><%=dm.getTradate()%></td>
													<td><%=dm.getAddrs()%></td>
													<td><%=dm.getReg()%></td>
													
												</tr>
												<%
													}
												%>

											</tbody>
										</table>
									</div>
									<%}else{ %>
									<h2>No Records Found</h2>
									<%} %>
								</div>
							</div>
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

function fnChangeSaleStat(stat,did,verif){
	if(verif=="verified"){
	$.post("<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=changeSaleStat&stat="+stat+"&did="+did,
			 function(data) {
				if(data=="0"){
					alert("Some Problem Occurred!");
					
				} else {
					alert("Status Changed Successfully!");
					//window.location.href="<%=request.getContextPath()%>/pages/dashboard.jsp";
					location.reload(); 
				}
			});
	}else{
		alert("Property is not Verifies yet. Please Verify Property first to Sale.");
	}
}


</script>
</html>