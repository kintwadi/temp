var root = document.querySelector("#root").innerHTML;

var currency_symbols = {
    'USD': '$', // US Dollar
    'EUR': '€', // Euro
    'CRC': '₡', // Costa Rican Colón
    'GBP': '£', // British Pound Sterling
    'ILS': '₪', // Israeli New Sheqel
    'INR': '₹', // Indian Rupee
    'JPY': '¥', // Japanese Yen
    'KRW': '₩', // South Korean Won
    'NGN': '₦', // Nigerian Naira
    'PHP': '₱', // Philippine Peso
    'PLN': 'zł', // Polish Zloty
    'PYG': '₲', // Paraguayan Guarani
    'THB': '฿', // Thai Baht
    'UAH': '₴', // Ukrainian Hryvnia
    'VND': '₫', // Vietnamese Dong
    'kwz': 'KZ' // Angola kwanza
};
var menuData = {

		logo:"logo",
		brand:"Bodasbue",
		home: "home",
		contact:"Contact:",
		phone:" (49)124589660",
		categories:" Categories",
		signup:" Sign Up",
		login:" Login",


};
var cardProps ={


        containerId: "#card_up_container",
        containerClass: "",
        rowId: "r1",
        rowClass: "card_up_r",
        collId: "c1",
        collClass: "card_up_c",
        imgSrc: "",
        imgWidth :"width:100%",
        title:"Blue night",
        description:"",
        date: "Febrary .20. 2019 - 20H30",
        buy:"Buy",
        link:"",
        price: " "
        	
       
    }

var flag = true;

function MenuBar(data,lang){

	this.data = data;

	this.writeMenuContent = (menu,lang)=>{

		var brand = document.querySelector("#brand");
		//var home = document.getElementById("home");
		var contact = document.querySelector("#contact");
		var login = document.querySelector("#login");
		var signup = document.querySelector("#signup");
		var categories = document.querySelector("#categories");

		brand.textContent = menu.data.brand;
		//home.textContent = menu.data.home;
		contact.textContent = menu.data.phone;
		login.textContent = menu.data.login;
		signup.textContent = menu.data.signup;
		categories.textContent = menu.data.categories;


	}

	function categoryListener (e){

		var a = document.querySelector(".category");	
		var categories = a[0].param;
		for(var i = 0; i< categories.length; i++ ){

			if(flag){
				a = document.querySelector(".category");
				a[i].setAttribute('href',root+"/category/"+categories[i]["page"]);
				flag = false;
			}else{
				a = document.querySelector("category");
				a[i].setAttribute('href',categories[i]["page"]);

			}
		}

	}

	this.listCategories = (menu,lang)=>{

		var url = root+"/list_categories/"+lang;
		
		$.get(url, function(data, status){

			var categories = data;

			var category_container = document.querySelector("#nav-collapse1");

			for(var i = 0; i< categories.length; i++ ){

				var li = document.createElement("li");
				var a = document.createElement("a");
				a.addEventListener('click',categoryListener,false);
				a.param = categories;
				a.innerHTML = categories[i]["name"].toUpperCase();
				a.setAttribute('class',"category");
				li.appendChild(a);
				category_container.appendChild(li);
			};
		});

	}
	
	function getCurrencyCode (currencyCode){
		
		if(currencyCode.toLowerCase() == "eur"){
			return "&#128;";
		}
		return "&#36;";
	}
	
	this.createBigCard = (command)=>{
		
		var url = root+"/get_big_card/"+command;
		$.get(url, function(data, status){
			
			var big_card = document.querySelector("#big_card");
			var big_card_title = document.querySelector("#big_card_title");
			var big_card_year = document.querySelector("#big_card_year");
			var big_card_start = document.querySelector("#big_card_start");
			var big_card_day = document.querySelector("#big_card_day");
			var big_card_month = document.querySelector("#big_card_month");
			var big_card_price = document.querySelector("#big_card_price");
			
			big_card_title.textContent = data["manager"][0].title;
			big_card_year.textContent = data["event"].year;
			var image = "/images/m8.png";
			
			big_card_day.textContent = data["event"].day;
			big_card_month.textContent = data["event"].month;
			
			//big_card_start.textContent = data["event"].start;
			
			big_card_price.innerHTML ="Fee: "+getCurrencyCode(data["event"].currencyCode)+" "+data["event"].price;
			
			big_card.setAttribute('style',"background-image: url("+image+")");
			
			
			//console.log("data: "+data["manager"][0].id);
			//console.log("data: "+data["event"].start);
		
			
		});
	}
	cardBuilder = (cardProps,command)=>{
		
		var card_container = document.querySelector(cardProps.containerId);
	      
        var card_row = document.createElement("div");

        //card_row.setAttribute('id',cardProps.rowId);
        card_row.setAttribute('class',cardProps.rowClass);

        var cardColl = document.createElement("div");
        //cardColl.setAttribute('id',cardProps.collId);
        cardColl.setAttribute('class',cardProps.collClass);

        var img = document.createElement('img');
        img.setAttribute('style',cardProps.imgWidth);
        img.src=cardProps.imgSrc;
        
        var details = document.createElement("div");
        details.setAttribute('class',"alert alert-success");
        var title = document.createElement('h4');
        title.appendChild(document.createTextNode(cardProps.title));
        details.appendChild(title);
        details.appendChild(document.createElement('hr'));
        
        details.appendChild(document.createElement("br"));
        var date = document.createElement("h5");
        date.setAttribute('style',"text-align: center;margin-top:-5px; color:rgb(111, 84, 153)");
        date.appendChild(document.createTextNode(cardProps.date));
        details.appendChild(date);
        var price = document.createElement('h5');
        price.setAttribute('style',"float:left; margin-top:-5px; color:rgb(111, 84, 153)");
        price.appendChild(document.createTextNode(cardProps.price));
        details.appendChild(price);
        //details.appendChild(document.createElement('hr'));
        var buyLink = document.createElement('a');
        
        buyLink.setAttribute('href',root+"/"+cardProps.link);
        buyLink.setAttribute('style',"float:right; margin-top:-20px;border-radius: 5px; color:red");
        buyLink.setAttribute('class',"btn ");
        buyLink.appendChild(document.createTextNode(cardProps.buy));
       
        details.appendChild(buyLink);
       
        cardColl.appendChild(img);
        cardColl.appendChild(details);
		
        card_row.appendChild(cardColl);

        card_container.appendChild(card_row);
		
	}
	this.loadCard =(command) =>{
		
		var url = "get_big_card/"+command;
		
		$.get(url, function(data, status){
			
			console.log("data: "+JSON.stringify(data));
			
			cardProps.imgSrc = "/images/"+data[0].image;
			cardProps.title = data[0].title;
			cardProps.date = data[0].day+"."+data[0].month+"."+data[0].year;
			cardProps.price = "Price : "+data[0].price+" "+ data[0].currencyCode;
			cardProps.link = "card/"+data[0].eventId;
			cardBuilder(cardProps,command);
	
			cardProps.imgSrc = "/images/"+data[1].image;
			cardProps.date = data[1].day+"."+data[1].month+"."+data[1].year;
			cardProps.title = data[1].title;
			cardProps.price = "Price : "+ data[1].price+" "+data[1].currencyCode;
			cardProps.link = "card/"+data[1].eventId;
			cardBuilder(cardProps,command);
			
			cardProps.imgSrc = "/images/"+data[2].image;
			cardProps.date = data[2].day+"."+data[2].month+"."+data[2].year;
			cardProps.title = data[2].title;
			cardProps.price = "Price : "+data[2].price+" "+ data[2].currencyCode;
			cardProps.link = "card/"+data[2].eventId;
			cardBuilder(cardProps,command);
	
		});
	}
	
}


function createApplicationLayout(lang,page){

		var menu = null ;
		// setup the language
		if(lang == "en"){

			menu = new MenuBar(menuData);
			menu.listCategories(menu,lang);
			menu.writeMenuContent(menu,lang);
			//menu.createBigCard("big_card");
			if(page.value != "composer"){
				menu.loadCard("big_card");
			}else{
				var search = document.querySelector(".search");
				search.style.display ="none";
			}
			
		
			
		}

}

	
	

	








