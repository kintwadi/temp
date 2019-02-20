package com.client.controllers;

import org.springframework.http.MediaType;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


@CrossOrigin(origins ="*")
@Controller

public class HomeController {

	private RestTemplate restTemplate = null;
	private HttpHeaders headers = null;
	private String serverBaseUrl ="https://192.168.178.55:8181";
	private HttpEntity< String> httpEntity = null;
	private ResponseEntity<String> responseEntity = null;



	@GetMapping("/list_categories/{lang}")
	@ResponseBody
	public List<Object>getCategoriesFromCategoryPage(@PathVariable String lang){


		ArrayList<Object> categories = null;

		try {

			sslDisabled(); 

			restTemplate = new RestTemplate();

			headers = new HttpHeaders();
			//headers.set("APIKey","1458966666");
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			String url = serverBaseUrl+"/list_categories/en";
			httpEntity = new HttpEntity<String>(headers);

			responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);


			categories = extractedAsArrayList(responseEntity);


		}catch (Exception e) {

			System.out.println(e.getLocalizedMessage()); 

		}


		return categories;

	}


	
	@GetMapping("/get_big_card/{command}")
	@ResponseBody
	public List<Object> getBigCards(@PathVariable String command) {


		List<Object> bigcards = null;

		try {

			sslDisabled(); 

			restTemplate = new RestTemplate();

			headers = new HttpHeaders();
			//headers.set("APIKey","1458966666");
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			String url = serverBaseUrl+"/get_big_cards/"+command;
			httpEntity = new HttpEntity<String>(headers);

			responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

			bigcards = extractedAsArrayList(responseEntity);
			


		}catch (Exception e) {

			System.out.println(e.getLocalizedMessage()); 

		}


		return bigcards;

	}

	@GetMapping("/recently_added")
	@ResponseBody
	public List<Object> recentlyAdded() {

		ArrayList<Object> events = null;

		try {

			sslDisabled(); 

			restTemplate = new RestTemplate();

			headers = new HttpHeaders();
			//headers.set("APIKey","1458966666");
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			String url = serverBaseUrl+"/recently_added";
			httpEntity = new HttpEntity<String>(headers);

			responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);


			events = extractedAsArrayList(responseEntity);


		}catch (Exception e) {

			System.out.println(e.getLocalizedMessage()); 

		}


		return events;

	}

	@GetMapping("/featured/{command}")
	@ResponseBody
	public List<Object> featured(@PathVariable String command) {

		ArrayList<Object> events = null;

		try {

			sslDisabled(); 

			restTemplate = new RestTemplate();

			headers = new HttpHeaders();
			//headers.set("APIKey","1458966666");
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			String url = serverBaseUrl+"/featured/"+command;
			httpEntity = new HttpEntity<String>(headers);

			responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);


			events = extractedAsArrayList(responseEntity);


		}catch (Exception e) {

			System.out.println(e.getLocalizedMessage()); 

		}


		return events;


	}

	@GetMapping("/mainEvents/{command}")
	@ResponseBody
	public List<Object> main(@PathVariable String command) {

		ArrayList<Object> events = null;

		try {

			sslDisabled(); 

			restTemplate = new RestTemplate();

			headers = new HttpHeaders();
			//headers.set("APIKey","1458966666");
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			String url = serverBaseUrl+"/mainEvents/"+command;
			httpEntity = new HttpEntity<String>(headers);

			responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);


			events = extractedAsArrayList(responseEntity);


		}catch (Exception e) {

			System.out.println(e.getLocalizedMessage()); 

		}


		return events;

	}

	@SuppressWarnings("unchecked")
	private ArrayList<Object> extractedAsArrayList(ResponseEntity<String> response) {
		Gson gson = new Gson();
		return  gson.fromJson(response.getBody(), ArrayList.class);
	}

	@SuppressWarnings("unchecked")
	private Map<Object,Object>extractedAsMap(ResponseEntity<String> response) {
		Gson gson = new Gson();

		return gson.fromJson(response.getBody(), Map.class); 
	}

	@RequestMapping("/")
	public String publicPage(Model model) {
		
		model.addAttribute("page", "home");
		return "home";
	}


	@GetMapping("/login_validation/{email}/{psw}")
	public String loginValidation(@PathVariable String email, @PathVariable  String psw,Model model, HttpSession session) {

		System.out.println("email: "+email);
		System.out.println("psw: "+psw);
		session.setAttribute("email",email );


		try {

			sslDisabled(); 

			restTemplate = new RestTemplate();

			headers = new HttpHeaders();
			//headers.set("APIKey","1458966666");
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			String url = serverBaseUrl+"/get_user/"+email;
			httpEntity = new HttpEntity<String>(headers);
			responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			System.out.println("user: "+responseEntity.getBody());

			if(responseEntity.getStatusCodeValue() == 200) {

			}

			//ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			//String json = ow.writeValueAsString(responseEntity.getBody());

			//Gson gson = new Gson();
			//String fromJson = gson.fromJson(json, String.class);



		}catch (Exception e) {

			System.out.println(e.getLocalizedMessage()); 

		}
		return "dashboard";
	}

	private JSONObject stringToJson(String str) {

		JSONParser parser = new JSONParser();

		JSONObject jsonObject = null;;
		try {
			jsonObject = (JSONObject) parser.parse(str);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		return jsonObject;

	}

	@PostMapping("/login")
	public String login(HttpServletRequest request, Model model, HttpSession session) {

		String email = request.getParameter("lemail");
		String psw = request.getParameter("lpws");
		System.out.println("password: "+psw);

		try {

			sslDisabled(); 

			restTemplate = new RestTemplate();

			headers = new HttpHeaders();
			//headers.set("APIKey","1458966666");
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			String url = serverBaseUrl+"/get_user/"+email;
			httpEntity = new HttpEntity<String>(headers);
			responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

			if(responseEntity.getStatusCodeValue() == 200) {

				JSONObject map = stringToJson(responseEntity.getBody()) ;
				JSONObject user = (JSONObject)map.get("user") ;
				JSONObject profile = (JSONObject)map.get("profile");
				System.out.println("map: "+map.toJSONString());
				String userPsw = (String) user.get("password");
				String profileType = (String) profile.get("type");

				if(userPsw.equals(psw)) {

					if(profileType.equals("basic")) {

						model.addAttribute("map", map);
						session.setAttribute("email",email );
						return "basic";

					}else {

						model.addAttribute("map", map);
						session.setAttribute("email",email );
						return "dashboard";
					}

				}

			}

		}catch (Exception e) {

			System.out.println(e.getLocalizedMessage()); 

		}
		model.addAttribute("page", "home");
		return "home";
	}


	@PostMapping("/charge")
	public String payment(@RequestBody Charge charge) {

		System.out.println("charge: "+charge.toString());

		return "payment ok";
	}

	public String charge(HttpServletRequest request,HttpSession session ,Model model) {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();

		String cardName = request.getParameter("card_name");
		String cardNumber = request.getParameter("card_number");
		String cardExp= request.getParameter("card_exp");
		String email = (String) session.getAttribute("email");

		objectNode.put("email", email);
		objectNode.put("cardName", cardName);
		objectNode.put("cardNumber", cardNumber);
		objectNode.put("cardExp", cardExp);

		try {

			sslDisabled(); 

			restTemplate = new RestTemplate();

			headers = new HttpHeaders();
			//headers.set("APIKey","1458966666");
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			String url = serverBaseUrl+"/charge";
			httpEntity = new HttpEntity<String>(headers);

			//responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			ResponseEntity<String> response = restTemplate.postForEntity( url, objectNode, String.class );
			System.out.println("add user: "+response.getStatusCodeValue() );

			if(response.getStatusCodeValue() == 200) {

				model.addAttribute("email", email);
				session.setAttribute("email",email );
				model.addAttribute("page", "basic");
				return "basic";
			}

		}catch (Exception e) {

			System.out.println(e.getLocalizedMessage()); 

		}
		model.addAttribute("page", "home");
		return "home";

	}

	@PostMapping("/change")
	public String changePassword(HttpServletRequest request,HttpSession session ,Model model) {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		String email = request.getParameter("email");
		String psw = request.getParameter("new_psw");

		objectNode.put("email", email);
		objectNode.put("psw", psw);

		try {

			sslDisabled(); 

			restTemplate = new RestTemplate();

			headers = new HttpHeaders();
			//headers.set("APIKey","1458966666");
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			String url = serverBaseUrl+"/update_user/"+psw+"/"+email;
			httpEntity = new HttpEntity<String>(headers);

			//ResponseEntity<String> response = restTemplate.postForEntity( url, objectNode, String.class );

			ResponseEntity<String>  response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);

			System.out.println("add user: "+response.getStatusCodeValue() );

			if(response.getStatusCodeValue() == 200) {

				model.addAttribute("email", email);
				session.setAttribute("email",email );
				model.addAttribute("page", "basic");
				
				return "basic";
			}

		}catch (Exception e) {

			System.out.println(e.getLocalizedMessage()); 

		}
		model.addAttribute("page", "home");
		return "home";

	}

	@PostMapping("/signup")
	public String sigupValid(HttpServletRequest request,HttpSession session ,Model model) {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		String name = request.getParameter("rname");
		String email = request.getParameter("remail");
		String psw = request.getParameter("rpsw");

		System.out.println("name: "+name);
		System.out.println("email: "+email);
		System.out.println("psw: "+psw);

		objectNode.put("name", name);
		objectNode.put("email", email);
		objectNode.put("psw", psw);

		try {

			sslDisabled(); 

			restTemplate = new RestTemplate();

			headers = new HttpHeaders();
			//headers.set("APIKey","1458966666");
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			String url = serverBaseUrl+"/register_user";
			httpEntity = new HttpEntity<String>(headers);

			//responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			ResponseEntity<String> response = restTemplate.postForEntity( url, objectNode, String.class );
			System.out.println("add user: "+response.getStatusCodeValue() );

			if(response.getStatusCodeValue() == 200) {


				model.addAttribute("username", name);
				model.addAttribute("email", email);
				model.addAttribute("page", "basic");
				return "basic";
			}

		}catch (Exception e) {

			System.out.println(e.getLocalizedMessage()); 

		}
		model.addAttribute("page", "home");
		return "home";

	}



	@RequestMapping("/admin")
	public String goHome() {


		try { 
			sslDisabled(); 

		} catch (Exception e) {

			System.out.println(e.getLocalizedMessage()); 
		}

		return "dashboard";
	}

	private List<String> getTokens(String str,String split) {
		List<String> tokens = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(str, split);
		while (tokenizer.hasMoreElements()) {
			tokens.add(tokenizer.nextToken());
		}
		return tokens;
	}

	@GetMapping("/profile")
	public String profile(HttpServletRequest request, Model model) {



		return "dashboard";
	}
	@PostMapping("/profile/reg_event")
	public String postEvent(HttpServletRequest request, Model model) {

		String email = request.getParameter("userEmail");
		String s_event_type = request.getParameter("s_event_type");
		String other_event = request.getParameter("other_event");
		String date = request.getParameter("event_date");


		String type = s_event_type;

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();

		if(s_event_type.equals("")) {
			type = other_event; 	
		}
		objectNode.put("type", type);
		objectNode.put("description", request.getParameter("description"));
		objectNode.put("country",request.getParameter("s_country_list"));
		objectNode.put("city", request.getParameter("city"));
		objectNode.put("street", request.getParameter("street"));
		objectNode.put("reference", "");
		objectNode.put("numberOfSeats", request.getParameter("number_of_seats"));
		objectNode.put("seatStyle", request.getParameter("s_event_seat_type"));
		objectNode.put("date", date);
		objectNode.put("start", request.getParameter("start_time"));
		objectNode.put("end", request.getParameter("end_time"));	
		objectNode.put("price", request.getParameter("price"));
		objectNode.put("currencyCode", request.getParameter("s_currency"));
		objectNode.put("postalCode", request.getParameter("postal_code"));
		objectNode.put("startTimeZone", request.getParameter("s_time_type"));
		objectNode.put("endTimeZone", request.getParameter("s_end_type"));
		objectNode.put("day", getTokens(date,"/").get(0));
		objectNode.put("month", getTokens(date,"/").get(1));
		objectNode.put("year", getTokens(date, "/").get(2));

		System.out.println("day: "+getTokens(date,"/").get(0));
		System.out.println("month: "+getTokens(date,"/").get(1));
		System.out.println("year: "+getTokens(date,"/").get(2));

		try {

			sslDisabled(); 

			restTemplate = new RestTemplate();

			headers = new HttpHeaders();
			//headers.set("APIKey","1458966666");
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			String url = serverBaseUrl+"/register_event/"+email;
			httpEntity = new HttpEntity<String>(headers);

			ResponseEntity<String> response = restTemplate.postForEntity( url, objectNode, String.class );
			System.out.println(response.toString());

		}catch (Exception e) {

			System.out.println(e.getLocalizedMessage()); 

		}


		return "dashboard";
	}
	
	@GetMapping("/composer")
	public String composer(HttpServletRequest request,Model model) {
		
		
		long eventId = Long.parseLong(request.getParameter("evt_ref"));
		long ticketId = Long.parseLong(request.getParameter("tick_ref"));
		
		System.out.println("ticketId:"+ticketId);
		System.out.println("teventId:"+eventId);
		
		try {

			sslDisabled(); 

			restTemplate = new RestTemplate();

			headers = new HttpHeaders();
			//headers.set("APIKey","1458966666");
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			String url = serverBaseUrl+"/get_ticket/"+ticketId +"/"+eventId;
			httpEntity = new HttpEntity<String>(headers);

			responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

			
			JSONObject event = stringToJson(responseEntity.getBody()) ;
			
			model.addAttribute("event", event);
			
			System.out.println("event: "+event.toJSONString());
			


		}catch (Exception e) {

			System.out.println(e.getLocalizedMessage()); 

		}
		
		model.addAttribute("page", "composer");
		
		return "composer";
	}




	public void sslDisabled() throws NoSuchAlgorithmException, KeyManagementException, IOException {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}
			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}
			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		}
		};

		// Install the all-trusting trust manager
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		URL url = new URL("https://192.168.178.55:8181");
		URLConnection con = url.openConnection();
		Reader reader = new InputStreamReader(con.getInputStream());
		while (true) {
			int ch = reader.read();
			if (ch==-1) {
				break;
			}
			//System.out.print((char)ch);
		}

	}
	@GetMapping("card/{id}")
	private Object paymeentPage(@PathVariable long id, Model model) {

		System.out.println("event id:"+ id);
	

		try {

			sslDisabled(); 

			restTemplate = new RestTemplate();

			headers = new HttpHeaders();
			//headers.set("APIKey","1458966666");
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			String url = serverBaseUrl+"/get_event_id/"+id;
			httpEntity = new HttpEntity<String>(headers);

			responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

			
			JSONObject event = stringToJson(responseEntity.getBody()) ;
			
			model.addAttribute("event", event);


		}catch (Exception e) {

			System.out.println(e.getLocalizedMessage()); 

		}
		
		
		return "payment";
	}


}
