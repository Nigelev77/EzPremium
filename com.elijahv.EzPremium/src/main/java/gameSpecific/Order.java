package gameSpecific;

import org.json.JSONObject;

public class Order {
	
	public int itemCount;
	public int avgPrice;
	public String time;
	
	public Order(JSONObject orderHistory) {
		itemCount = orderHistory.getInt("item_count");
		avgPrice = orderHistory.getInt("avg_price");
		time = orderHistory.getString("timestamp");
	}
	
	
}
