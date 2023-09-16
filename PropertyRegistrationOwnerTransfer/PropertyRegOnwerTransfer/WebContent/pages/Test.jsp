<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="../tiles/inc.jsp"></jsp:include>

</head>
<body style="background-color: #0261b0">
	<div class="" align="center"
		style="margin-top: 50px; margin-bottom: 50px;">
<h1 style="color: white;font-family: arial;">cert-cloud</h1>
		<div class="col-md-4">
			<div class="card">
				<div class="card-header">
					<div class="card-title">Login page</div>
				</div>
				<div class="card-body" id="loginform">
					<form action="javascript:fnSubmit();" name="myform" id="myform">
						<div class="form-group has-success"align="left">
						
						
							<label for="successInput">Login with email or mobile</label><br><br>
							<input type="text" name="uname" id="test" oninput="countNo()" 
								class="form-control">
								
								<p class="form-control mt-3" id="inp"></p>
						</div>

					</form>
					
				</div>
			</div>
		</div>
</body>
<script type="text/javascript">

function countNo() {
	  var x = document.getElementById("test").value;
	  var len = x.length;
	  one = ["Zero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten",
              "Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"];

		ten = ["", "","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"];
		
	  
	  if(x < 20){ 

		  document.getElementById("inp").innerHTML = one[x];
		  
	  }else if(x > 19 && x < 100){
		  
		  first = x.charAt(0); second = x.charAt(1);
		  var a = ten[first]; var b = one[second];
		  if(b=="Zero"){b="";}
		  document.getElementById("inp").innerHTML = a + " " + b;
		  
	  }else if(x > 99 && x < 1000){

		  first = x.charAt(0); second = x.charAt(1); third = x.charAt(2);
		  var a = one[first]; var b = ten[second]; var c = one[third];
		  if(c=="Zero"){c="";}
		  document.getElementById("inp").innerHTML = a + " hundred " + b + " " + c;
		  
	  }else if(x > 999 && x < 10000){

		  	first = x.charAt(0);
			second = x.charAt(1);
			third = x.charAt(2);
			fourth = x.charAt(3);
		  	var a = one[first];
			var b = one[second];
			var c = ten[third];
			var d = one[fourth];
		  	if(b=="Zero"){b=""}else{b = b + " hundred ";}
		  	if(c==""){var v=third+fourth; c=one[parseInt(v)]; d=""; if(c=="Zero"){c="";}}
		  	if(d=="Zero"){d="";}
		  	document.getElementById("inp").innerHTML = a + " thousand " + b + c + " " + d;
		  	console.log("a:" + a + ", b:" + b + ", c:" + c + ", d:" + d);
	  }else if(len == 0){
		  
		  document.getElementById("inp").innerHTML = "";
		  
	  }
	  
	  
	  
	  
}


</script>
<jsp:include page="../tiles/footerinc.jsp"></jsp:include>
</html>