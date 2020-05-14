package com.elijahv.EzPremium.graphInfo;

import javafx.scene.Group;
import javafx.scene.control.Label;

public class DataInfo {
	
	public static Group root;
	public static Label dataPointValue = new Label();
	public static Label dataPointName = new Label();
	public static Label dataPointPrice = new Label();
	
	
	public static Group createDataInfo() {
		root = new Group();
		root.getChildren().addAll(dataPointName, dataPointValue, dataPointPrice);
		return root;
	}
	
	public static void setDataLabels(String name, int value, int price) {
		dataPointName.setText(name);
		dataPointValue.setText(String.valueOf(value));
		dataPointPrice.setText(String.valueOf(price));
	}
	
	
	
	
	
}
