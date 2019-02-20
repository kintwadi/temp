<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<c:set var="context" value="${pageContext.request.contextPath}" />

<link href="/app_css/application.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script src="/app_script/jquery.twbsPagination.js"
	type="text/javascript"></script>
<!-- Material Design Bootstrap -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.0/css/mdb.min.css"
	rel="stylesheet">

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	
	

<style>
 h4{
	
	
	text-align: center;
	font-size: 20px;
}
img {
	border-radius:5px;
}

.modal-footer {
	background-color: #f9f9f9;
}

.center {
  margin: auto;
  width: 100%;
  border: 1px solid silver;
  padding: 5px;
  background-image: url("/images/m8.png");
}
.big_card_image{
	width: 100%;
	
	
}
/* Three image containers (use 25% for four, and 50% for two, etc) */
.card_up_c {
  float: left;
  width: 33.33%;
  padding: 5px;
}

/* Clear floats after image containers */
.card_up_r::after {
  content: "";
  clear: both;
  
}

</style>
</head>
<body >
<span id ="root" style="display: none">${context}</span>
	<div class="container">
	
		<nav class="navbar navbar-default navbar-fixed-top "> <span
			id="brand" class=""
			style="float: left; border-radius: 5px; margin-top: 5px;"></span>

		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<!-- <a class="navbar-brand" href="#" id="brand" style="float: left">Brand</a>  -->
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="navbar-collapse-1">

				<ul class="nav navbar-nav navbar-right">
					<li><a
						class="btn  btn-outline btn-circle collapsed"
						data-toggle="collapse" href="#nav-collapse1" aria-expanded="false"
						aria-controls="nav-collapse1" id="categories">Categories</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-earphone"
							id="contact"></span></a></li>
					<li><a href="#" id="facebook"><span
							class="fa fa-facebook-square"></span></a></li>
					<li><a href="#" id="tweeter"><span
							class="fa fa-twitter-square"></span></a></li>
					<li><a href="#" id="instagram"><span
							class="fa fa-instagram"></span></a></li>
					<li><a href="#" id="youtube"><span class="fa fa-youtube"></span></a></li>
					<li><a href="#"><span id="signup"
							class="glyphicon glyphicon-user "></span> </a></li>
					<li><a href="#"><span id="login"
							class="glyphicon glyphicon-log-in"></span> </a></li>

				</ul>
				<ul class="collapse nav navbar-nav nav-collapse navbar-right"
					id="nav-collapse1">

				</ul>

			</div>
			<!-- /.navbar-collapse -->

		</div>
		<!-- /.container --> </nav>
		<!-- /.navbar -->

		<br>
		<br>
		<br>
		<div class="search">

			<div class="col-lg-10  ">
				<div class="input-group ">
					<input type="text" class="form-control" aria-label=" search..."
						placeholder="search..."> <span class="input-group-addon">
						<span class="fa fa-search  "></span>
					</span>
				</div>
				
			</div>
		
		</div>
		

	</div>

	<div class="container">
	
		<div id="card_up_container">

		
		</div>

		<!--  login modal-->

		<!-- Modal -->
		
		<div class="modal fade" id="loginModal" role="dialog">
			<form action="/login" method="post" onsubmit="return login()">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header" style="padding: 35px 50px;">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4>
							<span class="glyphicon glyphicon-lock"></span> Login
						</h4>
					</div>
					<div class="modal-body" style="padding: 40px 50px;">
					
							<div class="form-group">
								<label for="lemail"><span
									class="glyphicon glyphicon-envelope"></span> Email</label> <input
									type="text" class="form-control" id="lemail" name="lemail"
									placeholder="Enter email">
							</div>

							<div class="form-group">
								<label for="lpsw"><span
									class="glyphicon glyphicon-lock"></span> Password</label> <input
									type="password" class="form-control" id="lpsw" name="lpws"
									placeholder="Enter password">
							</div>

							<div class="checkbox">
								<label><input type="checkbox" value="" checked>Remember
									me</label>
							</div>
							<button type="submit" class="btn btn-success btn-block" id ="btn_login">
								<span class="glyphicon glyphicon-off" ></span> Login
							</button>
							<h6 id = "login_message" ></h6>

							<div class="modal-footer">
								
								<p>
									Not a member? <a href="#sigupModal" >Sign Up</a>
								</p>
								<p>
									Forgot <a href="#">Password?</a>
								</p>
							</div>
						
					</div>
			
				</div>

			</div>
			</form>
		</div>
	
	<!--  sign up modal-->

	<!-- Modal -->
	
	<div class="modal modal-adminpro-general default-popup-PrimaryModal fade  animated" id="signupModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header " style="padding: 35px 50px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					
					<h4>
						<span class="glyphicon glyphicon-lock"></span> Sign Up
					</h4>
					
				</div>
				<form action="/signup" method="post" onsubmit="return signup()">
				<div class="modal-body " style="padding: 40px 50px;">
					
						<div class="form-group ">
							<label for="usrname"><span
								class="glyphicon glyphicon-user"></span> Username</label> <input
								type="text" class="form-control" id="usrname" name="rname"
								placeholder="Enter user name">
						</div>
						<div class="form-group">
							<label for="email"><span
								class="glyphicon glyphicon-envelope"></span> Email</label> <input
								type="text" class="form-control" id="email" name="remail"
								placeholder="Enter email">
						</div>
						<!-- <div class="form-group">
							<label for="cellphone"><span
								class="glyphicon glyphicon-phone"></span> Cell phone</label> <input
								type="text" class="form-control" id="cellphone" name="cell_phone"
								placeholder="Cellphone">
						</div> -->
						<div class="form-group">
							<label for="psw"><span
								class="glyphicon glyphicon-lock"></span> Password</label> <input
								type="password" class="form-control" id="psw" name="rpsw"
								placeholder="Enter password">
						</div>
						<!-- <div class="form-group">
							<label for="psw"><span
								class="glyphicon glyphicon-lock"></span>Repeat Password</label> <input
								type="password" class="form-control" id="rpsw"
								placeholder="Enter password">
						</div> -->
						<div class="checkbox">
							<label><input type="checkbox" value=""  id="remember_me"checked>Remember
								me</label>
						</div>
						<button type="submit" class="btn btn-success btn-block" id="btn_signup">
							<span class="glyphicon glyphicon-off"></span> Sign Up
						</button>
						<h6 id = "signup_message" ></h6>
				</div>
				</form>
			</div>
	
		</div>
	</div>
	
	</div>


	<!-- AFTER LOAD SCRIPT -->
	<script type="text/javascript" src="/app_script/application.js"></script>
	<script>
	$(document).ready(function(){
		  $("#signup").click(function(){
		    $("#signupModal").modal();
		  });
		});	
	
	$(document).ready(function(){
		  $("#login").click(function(){
		    $("#loginModal").modal();
		  });
		});
	
	
	function signup(){
		
		var usrname = document.querySelector("#usrname");
		var email = document.querySelector("#email");
		var psw = document.querySelector("#psw");
		var rpsw = document.querySelector("#rpsw");
		var remember_me = document.querySelector("#remember_me");
		var signup_message = document.querySelector("#signup_message");
		var flag = false;
	
		 if(empty(usrname.value)){
			
			usrname.style="border-color:red";
			flag = true;
			signup_message.innerHTML ="Please fillout this field(s)";
			signup_message.style="color:red";
		} 
		if(empty(email.value)){
	
			email.style="border-color:red";
			signup_message.innerHTML ="Please fillout this field(s)";
			signup_message.style="color:red";
			flag = true;
			
		}
		if(!validateEmail(email.value) ){
			
			email.style="border-color:red";
			signup_message.innerHTML ="Please fillout this field(s)";
			signup_message.style="color:red";
			flag = true;
			
		}
		if(empty(psw.value)){
			
			
			psw.style="border-color:red";
			signup_message.innerHTML ="Please fillout this field(s)";
			signup_message.style="color:red";
			flag = true;
			
		}
			
		if(!flag){
			
		
			return true;
			
		}
	
	return false;
		
	}
	
	function login(){
		
		
		//var usrname = document.querySelector("#usrname");
		var email = document.querySelector("#lemail");
		var psw = document.querySelector("#lpsw");
		var rpsw = document.querySelector("#rpsw");
		var remember_me = document.querySelector("#remember_me");
		var signup_message = document.querySelector("#signup_message");
		var flag = false;

		if(empty(email.value)){
			
			
			email.style="border-color:red";
			signup_message.innerHTML ="Please fillout this field(s)";
			signup_message.style="color:red";
			flag = true;
			
		}
		if(!validateEmail(email.value) ){
			
			email.style="border-color:red";
			signup_message.innerHTML ="Please fillout this field(s)";
			signup_message.style="color:red";
			flag = true;
			
		}
		if(empty(psw.value)){
			
			
			psw.style="border-color:red";
			signup_message.innerHTML ="Please fillout this field(s)";
			signup_message.style="color:red";
			flag = true;
			
		}
		
		
		if(!flag){
			
			//alert("aqui");
			return true;
			
		}
		//alert("false");
	return false;
	}
	

	function empty(str)
	{
	    if (typeof str == 'undefined' || !str || str.length === 0 || str === "" || !/[^\s]/.test(str) || /^\s*$/.test(str) || str.replace(/\s/g,"") === "")
	    {
	        return true;
	    }
	    else
	    {
	        return false;
	    }
	}
	
	function validateEmail(email) {
	    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    return re.test(String(email).toLowerCase());
	}
	
	
	 
	
	</script>


</body>
</html>