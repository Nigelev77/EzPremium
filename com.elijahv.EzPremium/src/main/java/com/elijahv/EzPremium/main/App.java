package com.elijahv.EzPremium.main;

import java.util.Map;

import com.elijahv.EzPremium.apiStuff.Requester;
import com.elijahv.EzPremium.graphInfo.DataInfo;
import com.elijahv.EzPremium.graphs.GraphArea;

import gameSpecific.Market;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application{
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		
		VBox root = new VBox();
		root.setMaxWidth(1000);
		Group left = new Group();
		Group dataInfo = DataInfo.createDataInfo();
		BorderPane pane = new BorderPane(root);
		pane.setLeft(left);
		pane.setRight(dataInfo);
		
		Map<String, Market> markets = Requester.getResponse();
		ScrollPane graph = GraphArea.createHistoryLineAndBarChart(markets.get("Martlock"));
		//ScrollPane linegraph = GraphArea.createHistoryLineChart(markets.get("Martlock"));
		
		Label label = new Label("Martlock");
		label.setPrefWidth(50);
		label.setPrefHeight(100);
		label.setWrapText(true);
		left.getChildren().add(label);
		root.getChildren().add(graph);

		Scene scene = new Scene(pane, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	
}
