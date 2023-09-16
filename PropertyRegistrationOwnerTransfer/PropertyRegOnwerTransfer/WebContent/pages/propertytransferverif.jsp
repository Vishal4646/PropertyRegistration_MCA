<%@page import="com.model.ShareRequestDocModel"%>
<%@page import="com.model.SignRequestModel"%>
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
					<h4 class="page-title">Registrar Property Transfer Verification</h4>
					<div class="row">
						
						<div class="col-md-12">
							<div class="card">
							<div class="card-header">
										<div class="card-title">Pending For Verification</div>
									</div>
								<div class="card-body ">
								
									<div class="table-full-width">
									
										<table class="table">
											<thead>
												<tr style="text-align: center">

													<th>Sr.No.</th>
													<th>Property</th>
													<th>Prev.Owner</th>
													<th>New.Owner</th>
													<th>Property ID</th>
													<th>Survey No.</th>
													<th>Reg.Date</th>
													<th>Address</th>
													<th>Sign/Reject</th>
													<th>Action</th>
													<th></th>
												</tr>
											</thead>
											<tbody>
												<%
													List list = ConnectionManager.getTransfReqList();
												int i = 0;
													for (i = 0; i < list.size(); i++) {
														SignRequestModel sm = (SignRequestModel) list.get(i);
														DocumentModel dm = ConnectionManager.getOriginalFileName(sm.getDid());
												%>
												<tr>
												<td><%= i+1 %>.</td>

<%-- 	<td style="cursor: pointer" onclick="javascript:fnViewDoc('<%=sm.getDid()%>')"> <%=dm.getProptype()%></td> --%>
						<td><a style="cursor: pointer" href="<%=request.getContextPath()%>/pages/transferdetails.jsp?propid=<%= sm.getPropid()%>&preowneradhar=<%=sm.getPreowneradhar()%>&newowneradhar=<%=sm.getNewowneradhar()%>"><%=dm.getProptype()%></a> </td>
															
													<td style="text-align: center"><%=sm.getPreownername()%>
													<%=sm.getPreowneradhar() %></td>
													<td style="text-align: center"><%=sm.getNewownername()%>
													<%=sm.getNewowneradhar() %></td>
													
													<td><%=sm.getPropid()%></td>
													<td><%=sm.getSurveyno()%></td>
													<% String da = sm.getCurrenttimedate();
													  da = da.substring(0, da.indexOf(' '));
													%>
													<td><%=da%></td>
													<td style="text-align: center"><%=sm.getPropaddrs()%></td>
															
															
					
												
													<td class="td-actions text-left">

														<button class="btn btn-primary dropdown-toggle"
															type="button" id="dropdownMenu1" data-toggle="dropdown">


															<i class="la  la-arrow-circle-down"
																style="font-size: 20px;"></i>
														</button>

														<ul class="dropdown-menu" role="menu"
															aria-labelledby="dropdownMenu">
														
															<a class="dropdown-item" href="javascript:fnViewDoc('<%=sm.getDid() %>')"><i
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
s2="<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=viewTransferFile&fid="+did+"&inline=1";
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
</script>
</html>