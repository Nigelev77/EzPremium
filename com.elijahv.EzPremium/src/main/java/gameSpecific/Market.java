package gameSpecific;

import org.json.JSONArray;
import org.json.JSONObject;

public class Market {
	
	public String location;
	public String itemID;
	public int quality;
	public Order[] orderData;
	
	
	public Market(JSONObject market) {
		this.location = market.getString("location");
		this.itemID = market.getString("item_id");
		this.quality = market.getInt("quality");
		JSONArray orders = market.getJSONArray("data");
		this.orderData = new Order[orders.length()];
		for(int i = 0;i<orders.length();i++) {
			orderData[i] = new Order(orders.getJSONObject(i));
		}
	}
	
}
