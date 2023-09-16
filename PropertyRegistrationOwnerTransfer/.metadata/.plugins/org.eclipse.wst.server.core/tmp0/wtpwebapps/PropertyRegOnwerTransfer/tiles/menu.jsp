
<%@page import="com.database.ConnectionManager"%>
<%@page import="com.model.UserModel"%>
<link rel="stylesheet"
	href="http://www.jacklmoore.com/colorbox/example1/colorbox.css" />
<div class="sidebar">
	<div class="scrollbar-inner sidebar-wrapper">
		<div class="user">
			<div class="photo">
				<img
					src="<%=request.getContextPath()%>/theme/assets/img/profile.png">
			</div>
			<div class="info">
				<a class=""> <span> <%
 	UserModel um = (UserModel) session.getAttribute("USER_MODEL");
 %>
						<span class="user-level"><%=um.getFname()%></span> <%=um.getEmailid()%>

				</span>
				</a>
				<div class="clearfix"></div>


			</div>
		</div>
		
		<%if(um.getRole().equalsIgnoreCase("1")){ %>
		
		<ul class="nav">
			
			<li class="nav-item"><a
				href="<%=request.getContextPath()%>/pages/dashboard.jsp"> <i
					class="la la-newspaper-o"></i>
					<p>Verified Properties</p> <span class="badge badge-count"><%=ConnectionManager.getUploadedDocumentCount(um.getUid())%></span>
			</a></li>
			</li>
			
			<li class="nav-item"><a
				href="<%=request.getContextPath()%>/pages/propertyverification.jsp"> <i class="la la-check-square"></i>
					<p>Property Verification Requests</p>
					 <span class="badge badge-count"><%=ConnectionManager.getPendingDocList().size()%></span>
			</a></li>
			
			<li class="nav-item"><a
				href="<%=request.getContextPath()%>/pages/propertytransferverif.jsp"> <i class="la la-clone"></i>
					<p>Property Transfer Requests</p>
					<span class="badge badge-count"><%=ConnectionManager.getTransfReqList().size()%></span>
			</a></li>
			
			<li class="nav-item"><a
				href="<%=request.getContextPath()%>/pages/tranferRecords.jsp"> <i class="la la-clone"></i>
					<p>Transfer Records</p>
			</a></li>

		</ul>
		
		<%}else{ %>
		
		<ul class="nav">


			<li class="nav-item update-pro">

				<a type="button" style="background-color: #1d62f0; color:#ffff" href="<%=request.getContextPath()%>/pages/addproperty.jsp">
					<i class="la la-cloud-upload"></i>
					<p>Add Property</p>
				</a>

			</li>


			<li class="nav-item"><a
				href="<%=request.getContextPath()%>/pages/draftdocument.jsp"> <i
					class="la la-keyboard-o"></i>
					<p>Recently Added</p> <span class="badge badge-count"><%=ConnectionManager.getWitoutSignDocumentListCount(um
					.getUid())%></span>
			</a></li>
			
			<li class="nav-item"><a
				href="<%=request.getContextPath()%>/pages/transferofowner.jsp"> <i class="la la-newspaper-o"></i>
					<p>Transfer of Ownership</p>
			</a></li>
			</li>
			
			
			<li class="nav-item"><a
				href="<%=request.getContextPath()%>/pages/dashboard.jsp"> <i
					class="la la-newspaper-o"></i>
					<p>Verified Properties</p> <span class="badge badge-count"><%=ConnectionManager.getUploadedDocumentCount(um.getUid())%></span>
			</a></li>
			</li>
			
			<li class="nav-item"><a
				href="<%=request.getContextPath()%>/pages/pendingTransfer.jsp"> <i
					class="la la-newspaper-o"></i>
					<p>Pending Status</p> <span class="badge badge-count"><%=ConnectionManager.getUploadedDocumentCount(um.getUid())%></span>
			</a></li>
			
			<li class="nav-item"><a
				href="<%=request.getContextPath()%>/pages/buyProperty.jsp"> <i
					class="la la-newspaper-o"></i>
					<p>Buy Properties</p> <span class="badge badge-count"><%=ConnectionManager.getCountPropertiesAvailableForBuy(um.getUid())%></span>
			</a></li>
			
			<li class="nav-item"><a
				href="<%=request.getContextPath()%>/pages/propertyRequests.jsp"> <i
					class="la la-newspaper-o"></i>
					<p>Property Requests</p> 
					<span class="badge badge-count"><%=ConnectionManager.getCountIncomingPropRequests(um.getUid())%></span>
					<span class="badge badge-count"><%=ConnectionManager.getCountAcceptedRequest(um.getUid())%></span>
			</a></li>
			
			
			
			</li>
			
			<!-- 						</a> -->
<!-- 			<li class="nav-item"><a -->
<%-- 				href="<%=request.getContextPath()%>/pages/receiveddocument.jsp"> --%>
<!-- 					<i class="la la-dashboard"></i> -->
<%-- 					<p>Shared With Me</p> <span class="badge badge-count"><%=ConnectionManager.getRecivedDocumentListCount(um.getUid())%></span> --%>
<!-- 			</a></li> -->
<!-- 			<li class="nav-item"><a -->
<%-- 				href="<%=request.getContextPath()%>/pages/senddocument.jsp"> <i --%>
<!-- 					class="la la-table"></i> -->
<%-- 					<p>Shared With Others</p> <span class="badge badge-count"><%=ConnectionManager.getSendDocumentListCount(um.getUid())%></span> --%>
<!-- 			</a></li> -->


		</ul>
		
		<%} %>
	</div>
</div>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="http://www.jacklmoore.com/colorbox/jquery.colorbox.js"></script>
<script>
	/* function openColorBox(){
	   $.colorbox({iframe:true, width:"50%", height:"100%", href: "http://localhost:7080/SecurePrivateCloudNew/pages/dashboard.jsp"});
	 }
	 
	 setTimeout(openColorBox, 1000);
	 */
</script>
