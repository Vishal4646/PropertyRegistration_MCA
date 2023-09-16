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
<!-- <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script> -->
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<!-- <script src="jquery.masknumber.js"></script> -->
<!-- <script rel="stylesheet" href="assets/js/jquery.masknumber.js"></script> -->
	<script src="<%=request.getContextPath()%>/theme/assets/js/jquery.masknumber.js"></script>
	<script src="<%=request.getContextPath()%>/theme/assets/js/jquery.masknumber2.js"></script>
	<script src="<%=request.getContextPath()%>/theme/assets/js/jquery.masknumber3.js"></script>
	<script src="<%=request.getContextPath()%>/theme/assets/js/jquery.masknumber.min.js"></script>

	<div class="wrapper">

		<jsp:include page="../tiles/header.jsp"></jsp:include>

		<jsp:include page="../tiles/menu.jsp"></jsp:include>
		<div class="main-panel">
			<div class="content">
				<div class="container-fluid">
				<h4 class="page-title">Transfer of Ownership</h4>
<%
 	UserModel um = (UserModel) session.getAttribute("USER_MODEL");
 %>
 				<form id="mainform">
 					<div class="row">
 					
						<div class="col-md-6">
							<div class="card p-4">
								<div class="card-header">
										<div class="card-title btn btn-primary  text-white">Property Details</div>
									</div>
								<div class="card-body ">
														<!-- 														Property ID: -->
														
														<p class="font-weight-bold m-0 p-0">Select Property</p><select class="form-control btn-default" name="proptype" id="proptype" required="required"
														placeholder="Property Type" style="background-color: #ffffff;
														border: 1px solid #8a8a8a; margin-bottom: 15px" onchange="onPropSelect()">
														<option selected disabled hidden>Select Your Property</option>
															<%
													List list = ConnectionManager.getUserProperties(um.getUid());
													for (int i = 0; i < list.size(); i++) {
														DocumentModel dm = (DocumentModel) list.get(i);
													%>
															<option><%= dm.getPropid() %>:  <%= dm.getProptype() %>  @  <%= dm.getAddr() %></option>
															
															<% } %>
														</select>
														
														Property ID<input readonly type="text" name="propid" id="propid" placeholder="Property ID"
															required="required" class="form-control btn-default"
															style="background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px">
															
															
														<!-- 														Survey No.: -->
														 Survey Number<input readonly type="text" name="surveyno" id="surveyno"
															placeholder="Survey No." required="required"
															class="form-control btn-default"
															style="background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px">
														
														<!-- 														Date of Registration: -->
														
														Date of Registration<input readonly type=text name="dateofreg" id="dateofreg"
															placeholder="Date of Registraion" required="required"
															class="form-control btn-default"
															style="background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px">
														
														<!-- 														Address: -->
														Property Location<input readonly type="text" name="propaddrs" id="propaddrs"
															placeholder="Address" required="required"
															class="form-control btn-default"
															style="background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px">
														
														<!-- 														Name of Registrar: -->
														 Registrar<input readonly type="text" name="registrarname" id="registrarname"
															placeholder="Name of Registrar" required="required"
															class="form-control btn-default"
															style="background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px">
															
														<!-- 														Name of Registrar: -->
														 <input hidden type="text" name="proptype2" id="proptype2"
															placeholder="proptype" required="required"
															class="form-control btn-default"
															style="background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px">
														

														<BR>
														<!-- 													<button type="submit" class="form-control btn btn-default"> -->
														<!-- 														<i class="la la-cloud-upload" style="font-size: 20px"></i> -->
														<!-- 														Upload -->
														<!-- 													</button> -->

													

														<!-- 														City: -->
														<!-- 													<input  type="text" name="propcity" placeholder="City" -->
														<!-- 														required="required" class="form-control btn-default" -->
														<!-- 														style="background-color: #ffffff; border:1px solid #8a8a8a; margin-bottom: 15px"></input> -->

													</div>
													</div>
													</div>
														<div class="col-md-6">
															<div class="card p-4">
																<div class="card-header">
																	<div class="card-title btn btn-primary  text-white">New Owner Details</div>
																</div>
															<div class="card-body">
															<div class="align-content-center mb-3">
															<img id="profile" style="width: 150px;height: 150px;" />
															</div>
														<!-- 														Adhar: -->
														<p class="font-weight-bold m-0 p-0">Adhaar Number</p><input type="text" name="newowneradhar" id="newowneradhar"
															placeholder="Adhaar No." required="required" oninput="countAdhar()"
															class="form-control btn-default" min="0" max="12"
															style="background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px"></input>
														<p type="text" id="count"></p>
														
														<!-- 														NewOwner Name: -->
														 Name<input readonly type="text" name="newownername" id="newownername"
															placeholder="Full Name" required="required"
															class="form-control btn-default"
															style="background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px">
															

														<!-- 														Mobile: -->
														 Mobile<input readonly type="text" name="newownermob" id="newownermob"
															placeholder="Mobile No." required="required"
															class="form-control btn-default"
															style="background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px"></input>
															

														<!-- 														Email: -->
														 Email<input readonly type="email" name="newowneremail" id="newowneremail"
															placeholder="Email" required="required"
															class="form-control btn-default"
															style="background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px"></input>

														<!-- 														Email: -->
<!-- 														 Test<input type="number" name="test2" id="test2" -->
<!-- 															class="form-control btn-default" -->
<!-- 															style="background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px"></input> -->
															
														
												</div>
												</div>
												</div>
												
												
													<div class="col-md-6">
															<div class="card p-4">
																<div class="card-header">
																	<div class="card-title btn btn-primary text-white">Selling Details</div>
																</div>
															<div class="card-body ">
														<!-- 														Adhar: -->
														Property Area (Sq.m)<input readonly type="text" name="proparea" id="proparea"
															placeholder="Property Area" required="required"
															class="form-control btn-default" min="0" max="16"
															style="background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px"></input>
														
														<!-- 														Adhar: -->
														Market Value<input readonly type="number" name="marketval" id="marketval"
															placeholder="Market Value" required="required"
															class="form-control btn-default" min="0" max="16"
															style="background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px"></input>
														
														
														<!-- 														NewOwner Name: -->
														 <p class="font-weight-bold m-0 p-0">Sell Value (Rs.)</p><input type="number" name="sellval" id="sellval"
															placeholder="Sell Value" required="required"
															class="form-control btn-default" oninput="onSellValChange()"
															style="background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px">
															<p class="text-danger" id="notice1">Sell Value can't be less than Market Value</p>
															

														<!-- 														Mobile: -->
														 Stamp Duty (6% of Sell Value) (Rs.)<input readonly type="number" name="stampduty" id="stampduty"
															placeholder="Stamp Duty" required="required"
															class="form-control btn-default"
															style="background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px"></input>
															

														<!-- 														Email: -->
														 Registration Fee (Rs.)<input readonly type="number" name="regfee" id="regfee"
															placeholder="Registration Fee" required="required"
															class="form-control btn-default"
															style="background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px"></input>
														
														<!-- 														Email: -->
														 Total Cost (Rs.)<input readonly type="number" name="totalcost" id="totalcost"
															placeholder="Total Cost" required="required"
															class="form-control btn-default"
															style="background-color: #ffffff; border: 1px solid #8a8a8a; margin-bottom: 15px"></input>
															
															
																<span class="form-control border border-warning mb-3">
																		<label class="form-check-label ml-3">
																			<input class="form-check-input" type="checkbox" id="payment"
																			name="payment" value="done" onchange='checkChange(this);'>
																			<span class="form-check-sign">Payment Received</span>
																		</label>
																</span>
															
															

														<div style="margin: auto">
															<button style="text-align: center" onclick="SendReq()" id="btnsbt"
																class="form-control btn btn-default btn-submit float-right">
																Add Request</button>
														</div>

												</div>
												</div>
												</div>
												
									</div>
											</form>	
									</div>
								</div>
							</div>
						</div>
						<%@include file="uploadFileFragment.jsp"%>


				</div>
			</div>

			<jsp:include page="../tiles/footer.jsp"></jsp:include>
		</div>
	</div>
	</div>
	<!-- Modal -->

</body>

<jsp:include page="../tiles/footerinc.jsp"></jsp:include>

<script>
// $(':input[readonly]').css({'background-color':'#8f2121 !important'});
// $('test').maskNumber();
document.getElementById("profile").style.display = "none";
$('test2').maskNumber({thousands: '*'});

var check1 = 0;
var check2 = 0;
var check3 = 0;
document.getElementById("btnsbt").disabled = true;
console.log("BTN Disabled");

function btnEn(){
	if(check1 == 1 && check2 == 1){
		  document.getElementById("btnsbt").disabled = false;
			console.log("BTN Enabled");
	}else{
		document.getElementById("btnsbt").disabled = true;
		console.log("IN ELSE");
	}
}
btnEn();

function countAdhar() {
  var x = document.getElementById("newowneradhar").value;
  var str = "adhar=" + x;
  if(x.length == 12){
		check1 = 1;
	  document.getElementById("count").style.display = "none";
	  $.post("<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=getUserDetailsFromAdhar",
				str, function(data) {
					data = $.trim(data);
					if(data=="-1"){
// 						console.log("adhar is not available1");
// 						alert("Adhaar Number is not available! Please Check your Adhaar.");
// 						console.log("adhar is not available2");
						
					} else {
						var arr = data.split(",");
// 						console.log("arr: " + arr);
// 						console.log("arr0: " + arr[0]);
// 						console.log("arr1: " + arr[1]);
// 						console.log("arr2: " + arr[2]);
						$("#newownername").val(arr[0]); 
						$("#newownermob").val(arr[1]); 
						$("#newowneremail").val(arr[2]);
						document.getElementById("profile").style.display = "block";
						$("#profile").attr("src", "<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=showPicture&adhar=" + x);
						check1 = 1;
						btnEn();
						
					}
				});
  }
  
  if(x.length > 12){
		check1 = 0;
		btnEn();
	  document.getElementById("count").style.display = "block";
	  document.getElementById("count").innerHTML = "Invalid Number";
	  document.getElementById("profile").style.display = "none";
		$("#newownername").val(""); 
		$("#newownermob").val(""); 
		$("#newowneremail").val(""); 
  }else if(x.length < 12){
		check1 = 0;
		btnEn();
	  document.getElementById("count").style.display = "block";
	  document.getElementById("count").innerHTML = "Remaining: " + (12-x.length);
	  document.getElementById("profile").style.display = "none";
		$("#newownername").val(""); 
		$("#newownermob").val(""); 
		$("#newowneremail").val(""); 
  }
}

function onPropSelect(){
	  var x = document.getElementById("proptype").value;
	  var prop = x.substr(0, x.indexOf(':'));
	  console.log("prop is : " + prop);
	  var str = "propid=" + prop;
	  var map = new Map();
		map.set("Land", 1000);
		map.set("House", 100000);
		map.set("Shop", 950000);
		map.set("Apartment", 81000);
		map.set("Farm", 120);
	  $.post("<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=getPropDetailsFromPropId",
				str, function(data) {
					data = $.trim(data);
					if(data=="-1"){
						alert("Error Getting Details!");
						
					} else {
						var arr = data.split("|");
						$("#propid").val(arr[0]); 
						$("#surveyno").val(arr[1]); 
						$("#dateofreg").val(arr[2]); 
						$("#registrarname").val(arr[3]); 
						$("#propaddrs").val(arr[4]); 
						$("#proparea").val(arr[5]); 
						$("#proptype2").val(arr[6]);

						$("#marketval").val(map.get(arr[6]));
						$("#sellval").val((arr[5]) * (map.get(arr[6])));
						onSellValChange();
						
						
					}
				});
}

function onSellValChange(){

	  var sell = document.getElementById("sellval").value;
	  var area = document.getElementById("proparea").value;
	  var market = document.getElementById("marketval").value;
	  var marVal = parseInt(area) * parseInt(market);
	  if(parseInt(sell) < marVal){
		  //alert("less value: " + sell +" :: " + marVal);
		  document.getElementById("notice1").style.display = "block";
			check2 = 0;
			btnEn();
	  }else{
		  //alert("larger value in else: " + sell + " :: " + marVal);
		  document.getElementById("notice1").style.display = "none";
			check2 = 1;
			btnEn();
	  }
	  
	  var stamp = (6/100) * sell;
	  var reg = (1/100) * sell;
	  
	  if(reg > 30000){
		  reg = 30000;
	  }

	  var total = parseInt(sell) + parseInt(stamp) + parseInt(reg);
	  
	  $("#stampduty").val(stamp);
	  $("#regfee").val(reg);
	  $("#totalcost").val(total);
}

function checkChange(checkbox) {
    if(checkbox.checked == true){
		check3 = 1;
    }else{
        check3 = 0;
   }
}

function SendReq(){
// 	$("#mainform").valid();

var str = $("#mainform" ).serialize();
if(check3 == 0){
	str = str + "&payment=null";
}
		//alert("str: " + str);
$.post("<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=addTransferReq",
		str, function(data) {
			data = $.trim(data);

		console.log("data: " +data);
			
		});
console.log("data: ");
		}
		
// $(function () {
//     $('#btnformat').on('click', function () {
//         var x = $('#num').val();
//         $('#num').val(addCommas(x));
//     });
// });
 
// function addCommas(x) {
//     var parts = x.toString().split(".");
//     parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
//     return parts.join(".");
// }

</script>

</html>