package com.elijahv.EzPremium.graphInfo;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;

public class DataInfo {
	
	public static Group root;
	public static VBox barChartBox;
	public static VBox lineChartBox;
	public static Label bardataPointVolume = new Label(), bardataPointTime = new Label(),  bardataPointPrice = new Label();
	public static Label linedataPointVolume = new Label(), linedataPointTime = new Label(),  linedataPointPrice = new Label();

	
	public static Group createDataInfo() {
		root = new Group();
		lineChartBox = new VBox();
		barChartBox = new VBox();
		lineChartBox.setMaxWidth(250);
		barChartBox.setMaxWidth(250);
		root.getChildren().addAll(barChartBox, lineChartBox);
		formatLabels();
		barChartBox.getChildren().addAll(bardataPointVolume, bardataPointPrice, bardataPointTime);
		lineChartBox.getChildren().addAll(linedataPointVolume, linedataPointPrice, linedataPointTime);
		lineChartBox.getTransforms().add(new Translate(0, 100*3+100));
		return root;
	}
	
	public static void setbarDataLabels(String name, int value, int price) {
		bardataPointTime.setText("Date: "+ name);
		bardataPointVolume.setText("Volume: "+String.valueOf(value));
		bardataPointPrice.setText("Avg Price: "+String.valueOf(price));
	}
	
	public static void setlineDataLabels(String name, int value, int price) {
		linedataPointTime.setText("Date: "+name);
		linedataPointVolume.setText("Volume: "+String.valueOf(value));
		linedataPointPrice.setText("Avg Price: "+String.valueOf(price));
	}
	

	
	private static void formatLabels() {
		bardataPointTime.setBackground(new Background(new BackgroundFill(Color.CORAL, new CornerRadii(10), null)));
		bardataPointTime.setMinWidth(250);
		bardataPointTime.setMinHeight(100);
		bardataPointTime.setAlignment(Pos.CENTER);
		bardataPointVolume.setBackground(new Background(new BackgroundFill(Color.CORAL, new CornerRadii(10), null)));
		bardataPointVolume.setMinWidth(250);
		bardataPointVolume.setMinHeight(100);
		bardataPointVolume.setAlignment(Pos.CENTER);
		bardataPointPrice.setBackground(new Background(new BackgroundFill(Color.CORAL, new CornerRadii(10), null)));
		bardataPointPrice.setMinWidth(250);
		bardataPointPrice.setMinHeight(100);
		bardataPointPrice.setAlignment(Pos.CENTER);
		
		linedataPointTime.setBackground(new Background(new BackgroundFill(Color.CORAL, new CornerRadii(10), null)));
		linedataPointTime.setMinWidth(250);
		linedataPointTime.setMinHeight(100);
		linedataPointTime.setAlignment(Pos.CENTER);
		linedataPointVolume.setBackground(new Background(new BackgroundFill(Color.CORAL, new CornerRadii(10), null)));
		linedataPointVolume.setMinWidth(250);
		linedataPointVolume.setMinHeight(100);
		linedataPointVolume.setAlignment(Pos.CENTER);
		linedataPointPrice.setBackground(new Background(new BackgroundFill(Color.CORAL, new CornerRadii(10), null)));
		linedataPointPrice.setMinWidth(250);
		linedataPointPrice.setMinHeight(100);
		linedataPointPrice.setAlignment(Pos.CENTER);
		
	}
	
	
	
}
