<%@page import="com.constant.ServerConstants"%>
<%@page import="com.model.SignRequestModel"%>
<%@page import="java.util.List"%>

<%@page import="com.database.ConnectionManager"%>
<%@page import="com.model.UserModel"%>
<div class="main-header">
	<div class="logo-header">
		<a href="index.html" class="logo"> <%=ServerConstants.PROJECT_NAME %> </a>
		<button class="navbar-toggler sidenav-toggler ml-auto" type="button"
			data-toggle="collapse" data-target="collapse" aria-controls="sidebar"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<button class="topbar-toggler more">
			<i class="la la-ellipsis-v"></i>
		</button>
	</div>
	<nav class="navbar navbar-expand-sm bg-primary navbar-project1">
		<div class="container-fluid">
			<%
				UserModel um = (UserModel) session.getAttribute("USER_MODEL");
			%>

			<ul class="navbar-nav topbar-nav ml-md-auto align-items-center">

				<%
					List<SignRequestModel> list = ConnectionManager
							.getPendingSignInRequests(um.getUid());
				%>
				
				<% if(um.getRole().toString().equalsIgnoreCase("1")){ %>
				<li class="nav-item dropdown hidden-caret"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> <i class="la la-bell"></i> <span
						class="notification"><%=list.size()%></span>
				</a>

					<ul class="dropdown-menu notif-box"
						aria-labelledby="navbarDropdown">
						<li>
							<div class="dropdown-title">
								You have
								<%=list.size()%>
								Signature Request's
							</div>
						</li>
						<li>
							<div class="notif-center">
								<%
									for (int i = 0; i < list.size(); i++) {
										SignRequestModel sm = (SignRequestModel) list.get(i);
								%>
								<a href="#">
									<div class="notif-icon notif-primary">
										<i class="la la-user-plus"></i>
									</div>
									<div class="notif-content">
										<span class="block"
											onclick="javascript:fnSignFile('<%=sm.getRid()%>','<%=ConnectionManager.getFilenameFromdid(sm.getDid())%>');">
											<%
												UserModel user = ConnectionManager.getUserNameFromUid(sm
															.getUid());
											%> Signature Request <%=user.getEmailid()%><br><%=user.getMobile()%>
										</span> <span class="time"><%=sm.getCurrenttimedate()%></span>
									</div>
								</a>
								<%
									}
								%>
							
							</div>
						</li>
						<li><a class="see-all" href="javascript:void(0);"> <strong>See
									all notifications</strong> <i class="la la-angle-right"></i>
						</a></li>
					</ul></li>
					
					<%}%>

				<li class="nav-item dropdown"><a
					class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"
					aria-expanded="false"> <img
						src="<%=request.getContextPath()%>/theme/assets/img/profile.png"
						alt="user-img" width="36" class="img-circle"><span><%=um.getEmailid()%></span>
				</a>
					<ul class="dropdown-menu dropdown-user">
						<li>
							<div class="user-box">
								<div class="u-img">
									<img
										src="<%=request.getContextPath()%>/theme/assets/img/profile.png"
										alt="user">
								</div>
								<div class="u-text">


									<h4><%=um.getEmailid()%></h4>
												</div>
							</div>
						</li>
			
						<a class="dropdown-item" href="<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=logout"><i class="fa fa-power-off"></i> Logout</a>
					</ul> </li>
			</ul>
		</div>
	</nav>
</div>