<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>

 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
 
  <link rel="stylesheet" href="/app_css/payment_form1.css">
   <link rel="stylesheet" href="/app_css/payment_form2.css">
   
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  
  
<link rel="stylesheet" href="/iziModal/css/iziModal.min.css">
<%@ include file="header.jsp"%>

<style>

#card_placement{

	border-style: solid;
  	border-width: 1px;
  	border-color: #85144b;
  	border-radius:5px;
  	background-color: #001f3f;	
}
#card_placement input {

	font-size: 12px;
	width: 80%;
	border-radius:5px;
}
#card_placement select {

	font-size: 15px;
	width: 40%;
	border-radius:5px;
	background-color: white;
	
}
#card_placement label,th ,table{
	font-size: 15px;
	padding: 5px;
	
	
	
		
}

#card_placement button {
	width:20%; 
	color: black;
	border-radius: 5px;	
	
}


</style>

</head>
<body>
<input type="hidden" id="page" value="${page}">
	<%-- <input type="text" id="evt" style="display: none" onclick="getEvent('${event}')"> --%>


	<br>
	<div class="container ">

		<table class="table" >
			<thead>
				<tr>

					<th scope="col" id="header_event_title">Blue jammes</th>
					<th scope="col" id="header_description">Description</th>
				</tr>
			</thead>
			<tbody id="eventContainer1">



			</tbody>
		</table>

		<div id="tickets">
		<%-- <form action="/composer" method="get"> --%>

			<table class="table table-responsive" id="card_info">
				<thead>
					<tr>

						<th scope="col">EVENT</th>
						<th scope="col">EVENT DATE & TIME</th>
						<th scope="col">LOCATION</th>
						<th scope="col">PRICE</th>
						<th scope="col">TICKET</th>
					</tr>
				</thead>
				<tbody id="eventContainer2">



				</tbody>
			</table>
			<input type="hidden" id ="ticketId" name="ticketId">
			<input type="hidden" id ="eventId" name="eventId">
			<div id="charge" class="container collapse " >
		
				
			     
			</div>
			<%-- </form> --%>
		</div>

	</div>


	<script>
	
	var page = document.querySelector("#page");
	createApplicationLayout("en", page);
	
	var rowProps = {
		
		rowId: "",
		date_time:"12.01.2019 - 12H00",
		location:"Hanuer land strasse 72,60314 Frankfurt am Main",
		price: "32 euro",
		eventId:"",
		userEmail:"user1@gmail.com",
		image: "m7.jpg",
		title:"Blue Night",
		description: "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."
	}
	function createRow(rowId,rowClass,content, tr, container){
		
		var row =  document.createElement("td");
    	var div = document.createElement("div");
    
    	div.appendChild(content);
    	row.appendChild(div);
    	tr.appendChild(row);
    	container.appendChild(tr);
    	
	}
    function addRow(tableNumber,tableBody,rowProps){
    	
    	var data = ${event};
    	
    	var tr = document.createElement("tr");
    	var container = document.querySelector(tableBody);
    	var content = null;
		if(tableNumber == 1){
			
			document.querySelector("#header_event_title").innerHTML = data["title"].toUpperCase();
			document.querySelector("#header_description").innerHTML = "description".toUpperCase();
			
			
			// event image
	    	content = document.createElement("img");
	    	content.src = "/images/"+data["image"];
	    	content.setAttribute('style',"width:20%;");
	    	createRow("","",content,tr, container);
	    	
	    	// description
	    	content = document.createElement("p");
	    	content.setAttribute('style',"font-size: 15px;");
	    	content.appendChild(document.createTextNode(data["description"]));
	    	
	    	createRow("","",content,tr, container);
	    
	    	
		}
		if(tableNumber == 2){
			
			var tickets = data["tickets"];
			
			for(var i = 0 ; i < tickets.length; i++){
				tr = document.createElement("tr");
				content = document.createElement("p");
		    	content.setAttribute('style',"font-size: 15px;");
		    	content.appendChild(document.createTextNode(tickets[i]["eventInfo"]));
		    	createRow("","",content,tr, container);
		    	
		    	content = document.createElement("p");
		    	content.setAttribute('style',"font-size: 15px;");
		    	content.appendChild(document.createTextNode(tickets[i]["dateAndTime"]));
		    	
		    	createRow("","",content,tr, container);
		    	
		    	content = document.createElement("p");
		    	content.setAttribute('style',"font-size: 15px;");
		    	content.appendChild(document.createTextNode(tickets[i]["location"]));
		    	createRow("","",content,tr, container);
		    	
		    	content = document.createElement("p");
		    	content.setAttribute('style',"font-size: 15px;");
		    	console.log("price: "+tickets[i]["price"]);
		    	content.appendChild(document.createTextNode(tickets[i]["price"]));
		    	createRow("","",content,tr, container);
		    	
		    	content = document.createElement("a");
		    	content.href="/composer?tick_ref="+tickets[i]['id']+"&evt_ref="+tickets[i]["eventId"];
		    	//content.setAttribute('type',"submit");
		    	//var ref = 
		    	//content.setAtribute("id",)
		    	//content.setAttribute('src',"#charge");
		    	//content.setAttribute('onclick',"toggleLink('tickets[i]['id'],tickets[i]['eventId']')");
		    	
		    	//content.setAttribute('data-toggle',"collapse");
		    	//content.setAttribute('data-target',"#charge");
		    	content.setAttribute('aria-expanded',"false");
		    	content.setAttribute('class',"btn btn-info btn-xs btn-circle");
		    	//content.setAttribute('value',"Ticket");
		    	content.appendChild(document.createTextNode("Buy ticket"));
		    	
		    	createRow("","",content,tr, container);
				
			}
			
			
		}
		
    }
    
    
    function getEvent(){
    	
    	var event = ${event};
    	
    	//var tickets = ${tickets};
    	
    	
    	console.log("event: "+JSON.stringify(event));
    	
    	//console.log("tickets: "+JSON.stringify(tickets));
    		
    }
    getEvent();
    
    
	addRow(1,"#eventContainer1",rowProps);
	addRow(2,"#eventContainer2",rowProps);
	
	function myForm(){
		
		var month = document.querySelector("#cardExpMonth");
		 for(let i = 0; i < months.length; i++){
	         var option = document.createElement("option");
	         var value = document.createTextNode(months[i]);
	         option.appendChild(value);
	         month.appendChild(option);
	     }
		 
	     var year = document.querySelector("#cardExYear");
	   
	     for(let i = 0; i < years.length; i++){
	         var option = document.createElement("option");
	         var value = document.createTextNode(years[i]);
	         option.appendChild(value);
	         year.appendChild(option);
	     }
	}
	
	myForm();
	var card_container = document.querySelector("#card_container");
	createCardForm("payment",card_container);
	
	function toggleLink(ticketId,eventId){
		
		alert("ticketId: "+ ticketId +"eventId "+ eventId);
		
		
		//$("#charge").slideToggle();
	}
	
	
	

</script>
</body>
</html>