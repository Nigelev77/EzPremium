package gameSpecific;

import org.json.JSONArray;
import org.json.JSONObject;

public class Market {
	
	public String location;
	public String itemID;
	public int quality;
	public Order[] orderData;
	public int maxVolume = 0;
	public int maxAvgPrice = 0;
	
	public Market(JSONObject market) {
		
		this.location = market.getString("location");
		this.itemID = market.getString("item_id");
		this.quality = market.getInt("quality");
		JSONArray orders = market.getJSONArray("data");
		this.orderData = new Order[orders.length()];
		int length = orders.length()-1;
		for(int i = 0;i<=length;i++) {
			orderData[i] = new Order(orders.getJSONObject(i));
			if(orderData[i].itemCount>maxVolume) {
				maxVolume = orderData[i].itemCount;
			}
			if(orderData[i].avgPrice>maxAvgPrice) {
				maxAvgPrice = orderData[i].avgPrice;
			}
		}
	}

}
