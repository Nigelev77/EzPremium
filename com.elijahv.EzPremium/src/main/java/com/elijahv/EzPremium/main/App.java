package com.elijahv.EzPremium.main;

import java.util.Arrays;

import com.elijahv.EzPremium.apiStuff.Requester;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		
		Group root = new Group();
		Group left = new Group();
		BorderPane pane = new BorderPane(root);
		pane.setLeft(left);
		CategoryAxis xAxis = new CategoryAxis(FXCollections.<String>observableArrayList(Arrays.asList("Speed", "User rating", "Milage", "Safety")));
		xAxis.setLabel("collections");
		NumberAxis yAxis = new NumberAxis("number", 0, 100, 10);
		
		
		BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
		XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
		series1.setName("Fiat");
		series1.getData().add(new XYChart.Data<String, Number>("Speed", 1.0)); 
		series1.getData().add(new XYChart.Data<String, Number>("User rating", 3.0)); 
		series1.getData().add(new XYChart.Data<String, Number>("Milage", 5.0)); 
		series1.getData().add(new XYChart.Data<String, Number>("Safety", 5.0));   
		
		XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>(); 
		series2.setName("Audi"); 
		series2.getData().add(new XYChart.Data<String, Number>("Speed", 5.0)); 
		series2.getData().add(new XYChart.Data<String, Number>("User rating", 6.0)); 
		
		
		barChart.getData().addAll(series1, series2);
		
		Label label = new Label(Requester.getResponse());
		label.setPrefWidth(50);
		label.setPrefHeight(100);
		label.setWrapText(true);
		left.getChildren().add(label);
		root.getChildren().add(barChart);
		
		Scene scene = new Scene(pane, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	
}
