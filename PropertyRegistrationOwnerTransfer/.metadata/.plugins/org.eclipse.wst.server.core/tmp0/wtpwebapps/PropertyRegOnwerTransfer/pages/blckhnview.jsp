<%@page import="java.util.HashMap"%>
<%@page import="com.ipfsblockchain.BlockChainIPFS"%>
<%@page import="com.model.DocumentModel"%>
<%@page import="java.util.List"%>
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
<style>
	.hash{
	border: 1px solid #4fe14f;
	background-color: #F9F9FB;
	border-radius: 4px;
	padding: 1px 5px;
}

.hoverr: hover {
	box-shadow: 0 14px 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22);
	background-color: red;
}
</style>
<body class="container py-5" style="background-color: #F9F9FB">


<%
String pid = (String)request.getParameter("pid");
	System.out.println("pid: " + pid);
%>

<h3 class="text-center m-auto">Blockchain View of Property</h3>
<%if(pid == null || pid.equalsIgnoreCase("")){ %>
	<h4 class="text-center text-danger m-auto">Please select a valid property to view details</h4>
<%}else{
	List list = ConnectionManager.getSingleProperty(pid);
	if(list.size() == 0){
%>
	<h4 class="text-center text-danger m-auto">No valid property selected</h4>
	<%}else{
		DocumentModel dm = (DocumentModel) list.get(0);
	%>

<p class="text-secondary text-center m-auto">Default ID: <%=pid %> | Property ID: <%=dm.getPropid() %> |  Survey No.: <%=dm.getSurveyno() %> |  Property type: <%=dm.getProptype() %> |  Address : <%=dm.getAddr() %> |  Verification Status: <%=dm.getStatus() %> </p>
<br>
<div class="row d-flex justify-content-center">
<% BlockChainIPFS br = new BlockChainIPFS();
	List blocks = br.getBlockHistoryById(pid);
	
	if(blocks.size() > 0){
		for(int i=0; i<blocks.size(); i++){
			String data = (String) blocks.get(i);
			//System.out.println("got data: " + data);
			HashMap<String, String> map = new HashMap<String, String>();
			String arr[] = data.split(",");
			//System.out.println("length: " + arr.length);
			//for(int k=0; k<arr.length; k++){
				//System.out.println("splitted: " + k + " : " + arr[k]);
			//}
			//System.out.println("");
			//System.out.println("");
			
			for(int j=0; j<arr.length; j++){
				map.put(arr[j].split("=")[0], arr[j].split("=")[1]);
			}
			//System.out.println(map);
%>
	<div class="card hoverr" style="width: 65%;">
		<div class="card-body p-4">
			<%if(i==0){ %>
				<h5 class="card-title" style="font-size: 24px!important;">GENESIS BLOCK</h5>
			<%}else{ %>
				<h5 class="card-title" style="font-size: 24px!important;">BLOCK #<%=i%></h5>
			<%} %>
			<br>
			<!-- <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6> -->
			<div style = "text-transform:uppercase;">Previous Hash : &nbsp;&nbsp;<span class="text-success"><%=map.get("prevHash") %></span></div>
			<div style = "text-transform:uppercase;">Hash : &nbsp;&nbsp;<span class="text-success hash"><%=map.get("Hash") %></span></div>
			<br>
			<div style = "text-transform:uppercase;">Previous Owner : &nbsp;&nbsp;<%=ConnectionManager.getOnwerDetForBlock((String)map.get("OldOwnerId")) %></div>
			<div style = "text-transform:uppercase;">Current Owner : &nbsp;&nbsp;<%=ConnectionManager.getOnwerDetForBlock((String)map.get("NewOwnerId")) %></div>
			
			<br>
			
			<small>Created on : &nbsp;&nbsp;<%=ConnectionManager.millToDatetime((String)map.get("time")) %></small>
			
		</div>
	</div>
	
	
	<%}
		}else{ %>
	<h4 class="text-center text-info m-auto">No history records available for this property yet</h4>
	<%}}
	} %>
	</div>
	
</body>
</html>