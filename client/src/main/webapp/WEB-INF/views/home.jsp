<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>home</title>


<%@ include file="header.jsp"%>


</head>
<body>
	<br>
	<div class="container" id="cardsContainer">
		
	<!-- 	<h4 style="float: right;" class="btn btn-default ">View all</h4>
		<h2 class="btn btn-default ">RECENTLY ADDED</h2>
		 -->
	<!-- 	<div class="panel panel-default">

			<div class="panel-body">

				<div class="container">
				
					<div id="pageContent"></div>
					
				</div>
				
				<div class="row">
				
					<div class="col-sm-12">

						<ul id="pagination-added-recently" class="pagination sm pagination-circle pg-blue">

						</ul>
						
					</div>
				</div>
			</div>
			
			<div class="panel-footer clearfix">
				<div class="pull-right">
					<a href="#"></a> <a href="#" class="btn btn-default">top</a>
				</div>
			</div>
		</div> -->

	</div>

	<script>
		function createDiv(page) {

			var pageContent = document.querySelector('#pageContent');
			var div = document.createElement("span");
			div.setAttribute('id', page);
			div.setAttribute('class', "well");
			div.setAttribute('style', "background-color: lightblue;");
			var node = document.createElement("span");
			var text = document.createTextNode("content: " + page);
			//node.appendChild(text);
			//div.appendChild(node);

			//pageContent.appendChild(div);

		}
		function added(page) {

			createDiv(page);

		}

		$('#pagination-added-recently').twbsPagination({
			totalPages : 16,
			visiblePages : 6,
			cssClass : 'page-item',
			next : '<span aria-hidden="true">&laquo;</span>',
			prev : '<span aria-hidden="true">&raquo;</span>',
			first : 'First',
			last : 'Last',
			onPageClick : function(event, page) {
				//fetch content and render here
				//added(page);

			}
		});
		
		
		
		 function createPanel(data,containerId, labelText, viewAllId,viewAllText ){

			 
		      var outerContainer = document.querySelector(containerId);
		      
		      var label = document.createElement("h1");
		      
		      label.setAttribute('class',"btn btn-default ");
		      label.setAttribute('style',"float: right;");
		      label.setAttribute('id',viewAllId);
		      var text = document.createTextNode(viewAllText);
		      
		      label.appendChild(text);
		      
		      outerContainer.appendChild(label);
		      
		      
		      label = document.createElement("h1");
		      label.setAttribute('class',"btn btn-default ");
		      text = document.createTextNode(labelText);
		      
		      label.appendChild(text);
		      
		      outerContainer.appendChild(label);
		      
		      
		      
		      var panel = document.createElement("div");
		      panel.setAttribute('class','panel panel-default');
		      var panelBody = document.createElement("div");
		      panelBody.setAttribute('class',"panel-body");
		      var innerContainer = document.createElement("div");
		      innerContainer.setAttribute('class',"container");
		      
		      var content = document.createElement("div");
		
		      innerContainer.appendChild(content);
		      panelBody.appendChild(innerContainer);
		      panel.appendChild(panelBody);
		      outerContainer.appendChild(panel);
		      
		      var index = 1;
		      
			for (let i = 0; i < data.length; i++) {
				
				
				var card = document.createElement("div");
				card.setAttribute('class', "card well");
				card.setAttribute('style', "width:270px; display:inline-block;padding:5px;float:left");
				
				var cardImage = document.createElement("img");
				cardImage.setAttribute('class', "card-img-top img-rounded");
				cardImage.setAttribute('style',"width:260px;height:340px;margin: 0 auto;");
				
				
				cardImage.src ="/images/m"+index+".jpg";
				index++;
				cardImage.setAttribute('alt', "image");
				card.appendChild(cardImage);
				
				var cardBody = document.createElement("div");
				cardBody.setAttribute('class', "card-body");

				cardBody.innerHTML += "day: " + data[i].day + "<br>";
				cardBody.innerHTML += "month: " + data[i].month + "<br>";
				cardBody.innerHTML += "year: " + data[i].year;
				card.appendChild(cardBody);
				content.appendChild(card);

			}
		      
		      
		    }

		function createCard(data) {
			var index = 1;
			var pageContent = document.querySelector('#pageContent');
			console.log(data.length);
			
			for (let i = 0; i < data.length; i++) {
				
				
				var card = document.createElement("div");
				card.setAttribute('class', "card well");
				card.setAttribute('style', "width:270px; display:inline-block;padding:5px;float:left");
				
				var cardImage = document.createElement("img");
				cardImage.setAttribute('class', "card-img-top img-rounded");
				cardImage.setAttribute('style',"width:260px;height:340px;margin: 0 auto;");
				
				
				cardImage.src ="/images/m"+index+".jpg";
				index++;
				cardImage.setAttribute('alt', "image");
				card.appendChild(cardImage);
				
				var cardBody = document.createElement("div");
				cardBody.setAttribute('class', "card-body");

				cardBody.innerHTML += "day: " + data[i].day + "<br>";
				cardBody.innerHTML += "month: " + data[i].month + "<br>";
				cardBody.innerHTML += "year: " + data[i].year;
				card.appendChild(cardBody);
				pageContent.appendChild(card);

			}

		}

		function listAddedRecently() {

			var url = "recently_added";

			$.get(url, function(data, status) {


				createPanel(data,"#cardsContainer","RECENTLY ADDED","recently_added_id","VIEW ALL");
			
			

			});

		}
		
		
		function featured(command ) {

			
			var url = "featured/"+command;

			$.get(url, function(data, status) {

		
				createPanel(data,"#cardsContainer","FEATURED","featured_id","VIEW ALL");
			

			});

		}
		
	function mainEvents(command ) {

			
			var url = "mainEvents/"+command;

			$.get(url, function(data, status) {

		
				createPanel(data,"#cardsContainer","MAIN EVENTS","main_events_id","VIEW ALL");
			

			});

		}
	
		
		listAddedRecently();
		featured("featured");
		mainEvents("mainEvents");
	</script>
</body>
</html>