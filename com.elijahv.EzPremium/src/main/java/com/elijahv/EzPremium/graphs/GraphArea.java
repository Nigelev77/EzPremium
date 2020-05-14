package com.elijahv.EzPremium.graphs;

import java.util.Arrays;

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
import javafx.scene.layout.VBox;

public class GraphArea {
	
	public static ScrollPane createHistoryLineAndBarChart(Market market){
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
		}
		linechart.setPrefWidth(market.orderData.length*25);
		linechart.setLegendVisible(false);
		
		vbox.getChildren().add(barChart);
		vbox.getChildren().add(linechart);
		pane.setContent(vbox);
		return pane;
		
	}
	
	
	
	
	
}
