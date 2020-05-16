package com.elijahv.EzPremium.graphs;

import java.util.Arrays;

import com.elijahv.EzPremium.apiStuff.Requester;
import com.elijahv.EzPremium.graphInfo.DataInfo;
import com.elijahv.EzPremium.marketRequester.MarketChanger;

import gameSpecific.Market;
import gameSpecific.Order;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GraphArea {
	
	public static VBox root = new VBox();
	public static String market = "none";
	public static String itemID = "none";
	public static int timeInterval = 0;
	
	public static void initialise(BorderPane pane) {
		root.setMaxWidth(1000);
		pane.setCenter(root);
	}
	
	public static ScrollPane createHistoryLineAndBarChart(Market market){
		if(market == null) {
			return new ScrollPane();
		}
		ScrollPane pane = new ScrollPane();
		VBox vbox = new VBox();
		CategoryAxis xAxis = new CategoryAxis(FXCollections.<String>observableArrayList(
				Arrays.asList("Date/Time")));
		xAxis.setLabel(market.location+":"+market.itemID);
		NumberAxis yAxis = new NumberAxis("Volume sold", 0, market.maxVolume, (market.maxVolume)/50);
		BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		barChart.getData().add(series);
		for(Order order:market.orderData) {
			XYChart.Data<String, Number> dataPoint = new Data<String, Number>(order.time, order.itemCount);
			series.getData().add(dataPoint);
			Tooltip.install(dataPoint.getNode(), new Tooltip("Volume: "+String.valueOf(order.itemCount)+"\n"+"Avg Price: "+String.valueOf(order.avgPrice)+"\n"+"Timestamp: "+order.time));
			dataPoint.getNode().setOnMouseEntered(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					DataInfo.setbarDataLabels(order.time, order.itemCount, order.avgPrice);
					
				}
			
			});
		}
		barChart.setPrefWidth(market.orderData.length*25);
		barChart.setLegendVisible(false);
		barChart.setCategoryGap(0);
		
		CategoryAxis lineXAxis = new CategoryAxis();
		lineXAxis.setLabel(market.location+":"+market.itemID);
		NumberAxis lineYAxis = new NumberAxis("Avg Price", 0,market.maxAvgPrice*1.25, (market.maxAvgPrice)/50);
		LineChart<String, Number> linechart = new LineChart<String, Number>(lineXAxis, lineYAxis);
		XYChart.Series<String, Number> lineSeries = new XYChart.Series<String, Number>();
		linechart.getData().add(lineSeries);
		for(Order order:market.orderData) {

			XYChart.Data<String, Number> dataPoint = new XYChart.Data<String, Number>(order.time, order.avgPrice);
			lineSeries.getData().add(dataPoint);
			Tooltip.install(dataPoint.getNode(), new Tooltip("Volume: "+String.valueOf(order.itemCount)+"\n"+"Avg Price: "+String.valueOf(order.avgPrice)+"\n"+"Timestamp: "+order.time));
			dataPoint.getNode().setOnMouseEntered(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					DataInfo.setlineDataLabels(order.time, order.itemCount, order.avgPrice);
					
				}
			});
		}
		linechart.setPrefWidth(market.orderData.length*25);
		linechart.setLegendVisible(false);
		
		vbox.getChildren().add(barChart);
		vbox.getChildren().add(linechart);
		pane.setContent(vbox);
		
		itemID=MarketChanger.itemId;
		GraphArea.market = MarketChanger.market;
		timeInterval = MarketChanger.timeInterval;
		
		
		return pane;
		
	}
	
	public static void setupGraphs() {
		if(Requester.availableMarket.isEmpty() || MarketChanger.market == null) {
			root.getChildren().clear();
			System.gc();
			return;
		}
		root.getChildren().clear();
		System.gc();
		root.getChildren().add(createHistoryLineAndBarChart(Requester.availableMarket.get(MarketChanger.market)));
	}
	
	
	
	
}
