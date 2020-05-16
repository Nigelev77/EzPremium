package com.elijahv.EzPremium.apiStuff;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;

import com.elijahv.EzPremium.marketRequester.MarketChanger;

import gameSpecific.Market;


public class Requester {
	
	public static final String url = "https://www.albion-online-data.com/api/v2/stats/";
	public static Map<String, Market> availableMarket = new HashMap<String, Market>();
	
	public static void getResponse() {
		availableMarket.clear();
		System.gc();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url+"History/"+MarketChanger.itemId+"?time-scale="+String.valueOf(MarketChanger.timeInterval))).build();
		HttpResponse<String> response = null;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		parseReponse(response.body());
	}
	
	private static void parseReponse(String response) {
		JSONArray parsedArray = new JSONArray(response);
		Market[] markets = new Market[parsedArray.length()];
		for(int i = 0; i <parsedArray.length();i++) {
			
			markets[i] = new Market(parsedArray.getJSONObject(i));
		}
		for(Market market:markets) {
			availableMarket.put(market.location, market);
		}
		
	}
	
	
	
}
