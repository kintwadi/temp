
var event_type = ["Sport","Comedy show","Music concert","Tour","Annual General Meetings ","Board Meetings ","Breakout Session ","Business Dinners and Banquets","Conference","Hollow Square","Boardroom","Banquet","Colloquium","Conclave","Congress","Consumer Shows","Conventions","Exhibitions","Expo or Expositions","Fairs","Functions","Fundraisers","Galas","Green Meetings","Meetups","Networking","Party","Plenary or General Sessions","Press Conferences","Product Launch Event","Receptions","Team Building","Seminars","Social Event","Symposiums","Trade Shows","Workshops","other"];


function eventTypeSelect(identifier){
	

	var select = document.querySelector(identifier);
	select.addEventListener("change",eventTypeSelectListener);

	for(let i = 0 ; i < event_type.length; i++){
		
		var option = document.createElement("option");
		
		var text = document.createTextNode(event_type[i]);
		option.appendChild(text);
		select.appendChild(option);
		
	}

}

function eventTypeSelectListener(evt){
	
	console.log("selected event  type: "+evt.currentTarget.value);
	var input = document.querySelector("#otherEvent");
	if(evt.currentTarget.value == "other"){
		input.style.display="block";
	}else{
		input.style.display="none";
	}
	
	
}

