
var event_seat_type = ["Theatre ","Classroom ","Herringbone ","U Shape","Horse Shoe","Hollow Square","Boardroom","Banquet","Cabaret","Cocktail","Lounge Zone"];


function eventSeatType(identifier){
	

	var select = document.querySelector(identifier);
	select.addEventListener("change",eventSeatTypeSelectListener);

	for(let i = 0 ; i < event_seat_type.length; i++){
		
		var option = document.createElement("option");
		
		var text = document.createTextNode(event_seat_type[i]);
		option.appendChild(text);
		select.appendChild(option);
		
	}

}

function eventSeatTypeSelectListener(evt){
	
	console.log("selected event seat type: "+evt.currentTarget.value);
	
}

