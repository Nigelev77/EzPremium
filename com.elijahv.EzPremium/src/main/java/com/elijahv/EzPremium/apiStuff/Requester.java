package com.elijahv.EzPremium.apiStuff;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import gameSpecific.Market;


public class Requester {
	
	public static final String url = "https://www.albion-online-data.com/api/v2/stats/";
	
	
	public static Map<String, Market> getResponse() {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.albion-online-data.com/api/v2/stats/History/T4_LEATHER?time-scale=1")).build();
		HttpResponse<String> response = null;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(response.body());
		return parseReponse(response.body());
		
	}
	
	private static Map<String, Market> parseReponse(String response) {
		JSONArray parsedArray = new JSONArray(response);
		Market[] markets = new Market[parsedArray.length()];
		for(int i = 0; i <parsedArray.length();i++) {
			
			markets[i] = new Market(parsedArray.getJSONObject(i));
		}
		Map<String, Market> map = new HashMap<String, Market>(markets.length);
		for(Market market:markets) {
			map.put(market.location, market);
		}
		return map;
	}
	
	
	
}
