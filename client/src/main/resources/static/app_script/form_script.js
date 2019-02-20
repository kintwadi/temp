
 var months = ["February","March","April","May","June","July","August","September","October","November","December"];
 
 function years (startYear) {
	 
     var currentYear = new Date().getFullYear(), years = [];
     
     startYear = startYear || 1980;
     while ( startYear <= currentYear ) {
         years.push(startYear++);
     } 
     return years;
 }

 var years = years (1999) ; 

function createCardForm(page,card_container){
	
	if(page == "basic"){

     var card_form = document.createElement("div");
     card_form.setAttribute('class',"creditCardForm");
     var card_heading = document.createElement("div");
     card_heading.setAttribute('class',"heading");
     var heading = document.createElement("h1");
     heading.appendChild(document.createTextNode("Payment method"));
     card_heading.appendChild(heading);
     card_form.appendChild(card_heading);

     var card_payment = document.createElement("div");
     card_payment.setAttribute('class',"payment");
     card_form.appendChild(card_payment);
     var form = document.createElement("form");
     card_payment.appendChild(form);

     //Owner
     
     var form_group_owner = document.createElement("div");
     form_group_owner.setAttribute('class',"form-group owner");
     var owner_label = document.createElement("label");
     owner_label.for="owner";
     owner_label.appendChild(document.createTextNode("Card Owner"));
     var owner_input = document.createElement("input");
     owner_input.type ="text";
     owner_input.setAttribute('class',"form-control");
     owner_input.id= "owner";
     form_group_owner.appendChild(owner_label);
     form_group_owner.appendChild(owner_input);
     form.appendChild(form_group_owner);
     
     //CVC

     var form_group_cvc = document.createElement("div");
     form_group_cvc.setAttribute('class',"form-group CVV");
     var cvc_label = document.createElement("label");
     cvc_label.for = "cvv";
     cvc_label.appendChild(document.createTextNode("CVC"));
     var cvc_input = document.createElement("input");
     cvc_input.type ="text";
     cvc_input.setAttribute('class',"form-control");
     cvc_input.id= "cvv";
     form_group_cvc.appendChild(cvc_label);
     form_group_cvc.appendChild(cvc_input);
     form.appendChild(form_group_cvc);
     
     //Card number

     var form_group_card_number = document.createElement("div");
     form_group_card_number.setAttribute('class',"form-group");
     form_group_card_number.id ="card-number-field";
     var card_number_label = document.createElement("label");
     card_number_label.for = "cardNumber";
     card_number_label.appendChild(document.createTextNode("Card number"));
     var card_number_input = document.createElement("input");
     card_number_input.type ="text";
     card_number_input.setAttribute('class',"form-control");
     card_number_input.id= "cardNumber";
     form_group_card_number.appendChild(card_number_label);
     form_group_card_number.appendChild(card_number_input);
     form.appendChild(form_group_card_number);

     //Expiration-date

     var form_group_expiration = document.createElement("div");
     form_group_expiration.setAttribute('class',"form-group");
     form_group_expiration.id = "expiration-date";
     var expiration_date_label = document.createElement("label");
     expiration_date_label.for = "expiration_date ";
     expiration_date_label.appendChild(document.createTextNode("Expiration date"));
     var month = document.createElement("select");
     month.id="cardExpMonth";
     for(let i = 0; i < months.length; i++){
         var option = document.createElement("option");
         var value = document.createTextNode(months[i]);
         option.appendChild(value);
         month.appendChild(option);
     }
     var year = document.createElement("select");
     year.id= "cardExYear";
     for(let i = 0; i < years.length; i++){
         var option = document.createElement("option");
         var value = document.createTextNode(years[i]);
         option.appendChild(value);
         year.appendChild(option);
     }
     form_group_expiration.appendChild(expiration_date_label);
     form_group_expiration.appendChild(month);
     form_group_expiration.appendChild(year);
     form.appendChild(form_group_expiration);

     // Card image
     
     var credit_cards = document.createElement("div");
     credit_cards.setAttribute('class',"form-group");
     credit_cards.id = "credit_cards";
     var visa = document.createElement("img");
     visa.src="/images/visa.jpg";
     visa.id="visa";
     var visa = document.createElement("img");
     visa.src="/images/visa.jpg";
     visa.id="visa";
     visa.alt="visa";
     var mastercard = document.createElement("img");
     mastercard.src="/images/mastercard.jpg";
     mastercard.id="mastercard";
     var amex = document.createElement("img");
     amex.src="/images/amex.jpg";
     amex.id="amex";
     credit_cards.appendChild(visa);
     credit_cards.appendChild(mastercard);
     credit_cards.appendChild(amex);
     form.appendChild(credit_cards);
     
     // Submit btn
     
     var pay_now = document.createElement("div");
     pay_now.setAttribute('class',"form-group");
     pay_now.id = "pay-now";
     var pay_btn = document.createElement("input");
     pay_btn.type="submit";
     pay_btn.id="confirm-purchase";
     pay_btn.value = "Confirm";
     pay_btn.setAttribute('class',"btn btn-default");
     pay_now.appendChild(pay_btn);
     form.appendChild(pay_now);
     
     card_container.appendChild(card_form);
     
     var error_msg= document.createElement("span");
     error_msg.id="error_msg";
     card_form.appendChild(error_msg);
     
	}
     

}


function charge(){
	
	var cardOwner = $('#owner');
	var cardNumber = $('#cardNumber');
	var cardNumberField = $('#card-number-field');
	var cardExpMonth = $("#cardExpMonth");
	var cardExYear = $("#cardExYear");
	var cvv = $("#cvv");
	var cardType ="visa";
	var error_msg= document.createElement("span");
	
	if ($.payform.parseCardType(cardNumber.val()) == 'amex') {
	    
		cardType = "amex";
		
	 } else if ($.payform.parseCardType(cardNumber.val()) == 'mastercard') {
	    
		 cardType = "mastercard";
	 }

	var data = {
			
			cardOwner:cardOwner.val(),
			cardNumber:cardNumber.val(),
			cardExpMonth:cardExpMonth.val(),
			cardExYear:cardExYear.val(),
			cardCode:cvv.val(),
			cardType:cardType
			
	}
	
	$.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/charge",
        data: JSON.stringify(data),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            console.log("SUCCESS : ", data);
           

        },
        error: function (e) {

            console.log("status : ", e.status);
        }
    });

}
 $(function() {

	var owner = $('#owner');
	var cardNumber = $('#cardNumber');
	var cardNumberField = $('#card-number-field');
	var CVV = $("#cvv");
	var mastercard = $("#mastercard");
	var confirmButton = $('#confirm-purchase');
	var visa = $("#visa");
	var amex = $("#amex");

//Use the payform library to format and validate
//the payment fields.

	//cardNumber.payform('formatCardNumber');
	//CVV.payform('formatCardCVC');


	cardNumber.keyup(function() {

 	amex.removeClass('transparent');
 	visa.removeClass('transparent');
 	mastercard.removeClass('transparent');

 	if ($.payform.validateCardNumber(cardNumber.val()) == false) {
    	
 		cardNumberField.addClass('has-error');
 	} else {
    	 
 		cardNumberField.removeClass('has-error');
     	cardNumberField.addClass('has-success');
 	}

 if ($.payform.parseCardType(cardNumber.val()) == 'visa') {
     mastercard.addClass('transparent');
     amex.addClass('transparent');
 } else if ($.payform.parseCardType(cardNumber.val()) == 'amex') {
     mastercard.addClass('transparent');
     visa.addClass('transparent');
 } else if ($.payform.parseCardType(cardNumber.val()) == 'mastercard') {
     amex.addClass('transparent');
     visa.addClass('transparent');
 }
});

confirmButton.click(function(e) {
  
 e.preventDefault();

 var isCardValid = $.payform.validateCardNumber(cardNumber.val());
 var isCvvValid = $.payform.validateCardCVC(CVV.val());
 var error_msg = document.querySelector("#error_msg");

 if(owner.val().length < 5){
     
	 error_msg.innerHTML = "Wrong owner name";
	 
	 
 } else if (!isCardValid) {
	 
	 error_msg.innerHTML = "WInvalid Card";
	 
 } else if (!isCvvValid) {
   
	 error_msg.innerHTML = "Wrong CVV or CVC";
	 
 } else {
     // Everything is correct. Add your form submission code here.
	 
	 charge();
 }
});
});
 
 
