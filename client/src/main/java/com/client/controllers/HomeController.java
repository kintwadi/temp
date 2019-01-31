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

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;


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
	@SuppressWarnings("unchecked")
	@GetMapping("/get_big_card/{command}")
	@ResponseBody
	public Map<Object,Object> getBigCard(@PathVariable String command) {
		
		
		Map<Object,Object> bigcard = null;
		
		try {

			sslDisabled(); 

			restTemplate = new RestTemplate();

			headers = new HttpHeaders();
			//headers.set("APIKey","1458966666");
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			String url = serverBaseUrl+"/get_big_card/"+command;
			httpEntity = new HttpEntity<String>(headers);

			responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			
			bigcard = extractedAsMap(responseEntity);
		
			
		}catch (Exception e) {
			
			System.out.println(e.getLocalizedMessage()); 

		}


		return bigcard;
		
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
	public String publicPage() {
		return "home";
	}


	@PostMapping("/login")
	public String login() {
		return "dashboard";
	}


	@PostMapping("/signup")
	public String sigup() {

		return "dashboard";
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


}
