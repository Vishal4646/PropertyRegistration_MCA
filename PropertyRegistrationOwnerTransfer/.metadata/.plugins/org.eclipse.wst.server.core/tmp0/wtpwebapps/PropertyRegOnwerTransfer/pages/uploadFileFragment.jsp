
<div class="modal fade" id="modalUpdate" tabindex="-1" role="dialog"
	aria-labelledby="modalUpdatePro" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<h6 class="modal-title">
					<i class="la la-frown-o"></i> Upload File
				</h6>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body text-center">
				<form class="navbar-left navbar-form nav-search mr-md-3"
					method="post" enctype="multipart/form-data"
					action="<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=uploadFile">
					<div class="card-header">
					
					<input type="text" name="propid" placeholder="Property ID" required="required" class="form-control"
					 style="text-align:center; background-color:#6F8996; color:#ffffff; margin-bottom:15px">
					 
						<input class="btn btn-default" accept="application/pdf"
							required="required" type="file" name="file" class="form-control"><br>
						<br>
						
						<button type="submit" class="btn btn-default">
							<i class="la la-cloud-upload" style="font-size: 20px"></i> Upload
						</button>
						
					</div>
				</form>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<!-- Modal Property ID -->

<div class="modal fade" id="modalProperty" tabindex="-1" role="dialog"
	aria-labelledby="modalUpdatePro" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<h6 class="modal-title">
					<i class="la la-frown-o"></i> User Name
				</h6>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body text-center">
				<form class="navbar-left navbar-form nav-search mr-md-3"
					method="post"
					action="<%=request.getContextPath()%>/pages/propertyverification.jsp"">
					<div class="card-header">
					<input type="text" name="propid" placeholder="User Name" required="required" class="form-control"
					 style="text-align:center; background-color:#6F8996; color:#ffffff; margin-bottom:15px">
						<br>
						<button type="submit" class="btn btn-default">
							<i class="la la-search" style="font-size: 20px"></i> Search
						</button>
					</div>
				</form>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>


<div class="modal fade" id="modalUpdate1" tabindex="-1" role="dialog"
	aria-labelledby="modalUpdatePro" aria-hidden="true">
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
					<div class="card-header " id="docname">Document Name :</div>
					<div class="card-header ">
						<br> Need to Sign before requesting?
						<p align="right">
							<br> <i class="la la-frown-o"></i>All sign by<input
								type="date" name="cdate" required="required">
						</p>
					</div>
					<input type="hidden" id="docid" name="docid">
					<div class="card-header ">
						<input type="text" id="successInput" name="uname"
							required="required" placeholder="Email ID or Mobile Number">
						<br>
					</div>
					<div class="card-header ">
						<br>
						<textarea id="successInput" name="msg" required="required"
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
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="modalShareDoc" tabindex="-1" role="dialog"
	aria-labelledby="modalUpdatePro" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<h6 class="modal-title">
					<i class="la la-frown-o"></i> Share With Others
				</h6>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">

				<form class="navbar-left navbar-form nav-search mr-md-3"
					id="requestShare" action="javascript:fnShareDocument()">
					<br>
					<div class="card-header " id="sharedocname">Document Name :</div>
					<div class="card-header ">
						<br> Need to Choose Share User Mail?
						<p align="right">
							<br>
						</p>
					</div>
					<input type="hidden" id="sharedocid" name="sharedocid">
					<%-- 											<% List lsit=ConnectionManager.getUserList(um.getUid()); %> --%>
					<br> <br>
					<div class="card-header ">
						<input type="text" id="successInput" name="username"
							required="required" placeholder="Email ID or Mobile Number">
						<br>
					</div>

					<div class="card-header " align="center">
						<br> <input type="submit" class="btn btn-success"
							value="Share Document" style="width: 50%">
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="modalShareOnEmailDoc" tabindex="-1"
	role="dialog" aria-labelledby="modalUpdatePro" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<h6 class="modal-title">
					<i class="la la-frown-o"></i> Email A Copy
				</h6>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">

				<form class="navbar-left navbar-form nav-search mr-md-3"
					id="requestShareOnEmail"
					action="javascript:fnShareDocumentOnMail()">
					<br>
					<div class="card-header " id="shareonemaildocname">Document
						Name :</div>
					<div class="card-header ">
						<br> Need to Enter User Mail?
						<p align="right">
							<br>
						</p>
					</div>
					<input type="hidden" id="shareonemaildocid"
						name="shareonemaildocid">
					<%-- 											<% List lsit=ConnectionManager.getUserList(um.getUid()); %> --%>
					<br> <br>
					<div class="card-header ">
						<input type="text" id="successInput" name="emailid"
							required="required" placeholder="Email ID "> <br>
					</div>

					<div class="card-header " align="center">
						<br> <input type="submit" class="btn btn-success"
							value="Share Document With Other User" style="width: 50%">
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
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
					<div class="card-header " id="signdocname">Document Name :</div>
					<div class="card-header ">
						<br> Need to Sign before digital sign?
						<p align="right">
							<br> <input type="text" id="successInput" name="password"
								required="required" placeholder="Email Certificate Password">
						</p>
					</div>
					<input type="hidden" id="signdocid" name="signdocid"> <input
						class="btn btn-default" accept="application/p12" type="file"
						required="required" name="file" class="form-control"><br>
					<br>


					<div class="card-header " align="center">
						<br> <input type="submit" class="btn btn-success"
							value="Sign Document" style="width: 50%">
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<script>
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



function fnShareDocument(){	
	var str = $("#requestShare" ).serialize();

	$.post("<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=requestShare",
			str, function(data) {
				data = $.trim(data);
				alert(data);
				
					window.location.href="<%=request.getContextPath()%>/pages/dashboard.jsp";

							$('#myform')[0].reset();
						});

	}

function fnDeleteDocument(did){	

if(confirm('Are you sure want to Delete?')){
	window.location.href='<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=deleteFile&did='+did;
		}
	}
	

	
function fnShareFileWith(a,b){	
//	alert(b);
	document.getElementById('sharedocid').value=a;
	
	document.getElementById('sharedocname').innerHTML='Document Name : '+b;
	
	$("#modalShareDoc").modal("show");
}
</script>