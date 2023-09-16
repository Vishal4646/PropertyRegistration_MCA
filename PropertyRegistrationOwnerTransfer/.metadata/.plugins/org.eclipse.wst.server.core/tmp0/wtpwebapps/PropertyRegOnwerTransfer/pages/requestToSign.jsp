<%@page import="com.model.UserModel"%>
<%@page import="com.database.ConnectionManager"%>
<div class="modal fade" id="signReqModal" tabindex="-1"
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
												<p align="left">
													<br> <i class="la la-frown-o"></i>All sign by<input
														type="date" name="cdate" required="required">
												</p>
											</div>
											<%
											UserModel admin = ConnectionManager.getAdminDetails();
											%>
											<input type="hidden" id="docid" name="docid">
											<div class="card-header ">
												<input type="hidden" id="successInput" name="uname"
													placeholder="Email ID or Mobile Number" value="<%=admin.getMobile()%>">
													
													<p>Requesting to Admin</p>
													<h6><%=admin.getEmailid()%></h6>
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