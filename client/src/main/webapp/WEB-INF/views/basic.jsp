<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">


<link rel="stylesheet" type="text/css" href="/app_css/payment_form1.css">
<link rel="stylesheet" type="text/css" href="/app_css/payment_form2.css">
<link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h5>User:</h5>
		<hr>
		<a href="#" style="float: right;">upgrade </a>

		<div class="container">

			<ul class="nav nav-tabs">

				<li class="active"><a data-toggle="tab" href="#home">Orders</a></li>
				<li><a data-toggle="tab" href="#menu1">Change password</a></li>
				<li><a data-toggle="tab" href="#card_container"> Payment method details</a></li>


			</ul>

			<div class="tab-content ">
				<div id="home" class="tab-pane fade in active">
					<h3>Orders</h3>
					<p>Your orders list is empty</p>
				</div>
				<div id="menu1" class="tab-pane fade">

					<div class="creditCardForm">
						<div class="modal-header " style="padding: 35px 50px;">

							<h4>
								<span class="glyphicon glyphicon-address"></span> Change
								password
							</h4>

						</div>
						<form action="/change" method="post"
							onsubmit="return changePassword()">
							<div class=" " style="padding: 20px 50px;">

								<div class="form-group">
									<label for="email"><span
										class="glyphicon glyphicon-envelope"></span> Email</label> <input
										type="text" class="form-control" id="email" name="email"
										placeholder="Enter email">
								</div>
								<div class="form-group">
									<label for="oldpsw"><span
										class="glyphicon glyphicon-lock"></span> Old password</label> <input
										type="password" class="form-control" id="old_psw"
										name="old_psw" placeholder="Enter old password">
								</div>

								<div class="form-group">
									<label for="psw"><span class="glyphicon glyphicon-lock"></span>
										New password</label> <input type="password" class="form-control"
										id="new_psw" name="new_psw" placeholder="Enter new password">
								</div>
								<button type="submit" class="btn btn-success btn-block">
									<span class=""></span> Save
								</button>
								<span id="change_message"></span>
							</div>
						</form>

					</div>

				</div>
				<div id="card_container" class="tab-pane fade" >
				
				</div>

			</div>
		</div>

	
	
		<script src="/app_script/jquery.payform.min.js" charset="utf-8"></script>
		<script src="/app_script/form_script.js" charset="utf-8"></script>


		<script>
		
		function validateEmail(email) {
		    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		    return re.test(String(email).toLowerCase());
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
		
		   
		function changePassword(){
			
			
			var email = document.querySelector("#email");
			var old_psw = document.querySelector("#old_psw");
			var psw = document.querySelector("#new_psw");
			//var remember_me = document.querySelector("#remember_me");
			var change_message = document.querySelector("#change_message");
			var flag = false;
		
		
			if(empty(email.value)){
		
				email.style="border-color:red";
				change_message.innerHTML ="Please fillout this field(s)";
				change_message.style="color:red";
				flag = true;
				
			}
			if(!validateEmail(email.value) ){
				
				email.style="border-color:red";
				change_message.innerHTML ="Please fillout this field(s)";
				change_message.style="color:red";
				flag = true;
				
			}
			if(empty(psw.value)){
				
				psw.style="border-color:red";
				change_message.innerHTML ="Please fillout this field(s)";
				change_message.style="color:red";
				flag = true;
				
			}
			if(empty(old_psw.value)){
				
				old_psw.style="border-color:red";
				change_message.innerHTML ="Please fillout this field(s)";
				change_message.style="color:red";
				flag = true;
				
			}
				
			if(!flag){
				return true;	
			}
		
		return false;
			
		}
		
/*
		
function charge(){
			
			
			var card_name = document.querySelector("#card_name");
			var card_number = document.querySelector("#card_number");
			var card_exp = document.querySelector("#card_exp");
			var card_cvc = document.querySelector("#card_cvc");
			var charge_message = document.querySelector("#charge_message");
			var flag = false;
		
			if(empty(card_name.value)){
				
				card_name.style="border-color:red";
				charge_message.innerHTML ="Please fillout this field(s)";
				charge_message.style="color:red";
				flag = true;
				
			}
			if(empty(card_number.value)){
				
					
				card_number.style="border-color:red";
				charge_message.innerHTML ="Please fillout this field(s)";
				charge_message.style="color:red";
				flag = true;
				
			}
			if(empty(card_exp.value)){
				
				
				card_exp.style="border-color:red";
				charge_message.innerHTML ="Please fillout this field(s)";
				charge_message.style="color:red";
				flag = true;
				
			}
			if(empty(card_cvc.value)){
				
				card_cvc.style="border-color:red";
				charge_message.innerHTML ="Please fillout this field(s)";
				charge_message.style="color:red";
				flag = true;	
			}
				
			if(!flag){
				
				return true;	
			}
		
		return false;
			
		
		}
	
 */
 var card_container = document.querySelector("#card_container");
 createCardForm("basic",card_container);
 
 

		</script>
</body>
</html>