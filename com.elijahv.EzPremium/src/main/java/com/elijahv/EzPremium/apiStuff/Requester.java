package com.elijahv.EzPremium.apiStuff;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;

import gameSpecific.Order;


public class Requester {
	
	public static final String url = "https://www.albion-online-data.com/api/v2/stats/";
	
	
	public static String getResponse() {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.albion-online-data.com/api/v2/stats/History/UNIQUE_HIDEOUT?time-scale=6")).build();
		HttpResponse<String> response = null;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		parseReponse(response.body());
		return response.body();
		
		
	}
	
	private static List<HashMap<String, List<Order>>> parseReponse(String response) {
		response = response.substring(1, response.length());
		String[] marketsList = response.split("\"location\"");
		for(String market:marketsList) {
			System.out.println(market);
		}
		
		
		
		
		
		return null;
	}
	
	
	
}
