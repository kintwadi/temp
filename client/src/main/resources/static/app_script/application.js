
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
				a[i].setAttribute('href',"category/"+categories[i]["page"]);
				flag = false;
			}else{
				a = document.querySelector("category");
				a[i].setAttribute('href',categories[i]["page"]);

			}
		}

	}

	this.listCategories = (menu,lang)=>{

		var url = "list_categories/"+lang;
		
		$.get(url, function(data, status){

			var categories = data;

			var category_container = document.querySelector("#nav-collapse1");

			for(var i = 0; i< categories.length; i++ ){

				var li = document.createElement("li");
				var a = document.createElement("a");
				a.addEventListener('click',categoryListener,false);
				a.param = categories;
				a.innerHTML = categories[i]["name"].toLowerCase();
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
		
		var url = "list_manager/"+command;
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
			var image = "/images/"+data["manager"][0].imagePath;
			
			big_card_day.textContent = data["event"].day;
			big_card_month.textContent = data["event"].month;
			
			//big_card_start.textContent = data["event"].start;
			
			big_card_price.innerHTML ="Fee: "+getCurrencyCode(data["event"].currencyCode)+" "+data["event"].price;
			
			big_card.setAttribute('style',"background-image: url("+image+")");
			
			
			//console.log("data: "+data["manager"][0].id);
			//console.log("data: "+data["event"].start);
		
			
		});
	}
	
}

class SliderClip {

	constructor(el) {
		this.el = el;
		this.Slides = Array.from(this.el.querySelectorAll('li'));
		this.Nav = Array.from(this.el.querySelectorAll('nav a'));
		this.totalSlides = this.Slides.length;
		this.current = 0;
		this.autoPlay = true; //true or false
		this.timeTrans = 4000; //transition time in milliseconds
		this.IndexElements = [];

		for(let i=0;i<this.totalSlides;i++) {
			this.IndexElements.push(i);
		}

		this.setCurret();
		this.initEvents();
	}
	setCurret() {
		this.Slides[this.current].classList.add('current');
		this.Nav[this.current].classList.add('current_dot');
	}
	initEvents() {
		const self = this;

		this.Nav.forEach((dot) => {
			dot.addEventListener('click', (ele) => {
				ele.preventDefault();
				this.changeSlide(this.Nav.indexOf(dot));
			})
		})

		this.el.addEventListener('mouseenter', () => self.autoPlay = false);
		this.el.addEventListener('mouseleave', () => self.autoPlay = true);

		setInterval(function() {
			if (self.autoPlay) {
				self.current = self.current < self.Slides.length-1 ? self.current + 1 : 0;
				self.changeSlide(self.current);
			}
		}, this.timeTrans);

	}
	changeSlide(index) {

		this.Nav.forEach((allDot) => allDot.classList.remove('current_dot'));

		this.Slides.forEach((allSlides) => allSlides.classList.remove('prev', 'current'));

		const getAllPrev = value => value < index;

		const prevElements = this.IndexElements.filter(getAllPrev);

		prevElements.forEach((indexPrevEle) => this.Slides[indexPrevEle].classList.add('prev'));

		this.Slides[index].classList.add('current');
		this.Nav[index].classList.add('current_dot');
	}
}

function createApplicationLayout(lang){

		var menu = null ;
		// setup the language
		if(lang == "en"){

			menu = new MenuBar(menuData);
			menu.listCategories(menu,lang);
			menu.writeMenuContent(menu,lang);
			menu.createBigCard("big_card");
			const slider = new SliderClip(document.querySelector('.slider'));
			
		}

}

createApplicationLayout("en");
	
	

	








