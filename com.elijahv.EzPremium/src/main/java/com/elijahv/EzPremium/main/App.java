package com.elijahv.EzPremium.main;

import com.elijahv.EzPremium.graphInfo.DataInfo;
import com.elijahv.EzPremium.graphs.GraphArea;
import com.elijahv.EzPremium.marketRequester.MarketChanger;

import gameSpecific.ItemIDMap;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application{
	
	
	
	public static void main(String[] args) {
		ItemIDMap.generateList();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		

		Group left = new Group();
		Group dataInfo = DataInfo.createDataInfo();
		BorderPane pane = new BorderPane();
		pane.setLeft(left);
		pane.setRight(dataInfo);
		GraphArea.initialise(pane);
		left.getChildren().add(MarketChanger.createChangerMenu(left));
		Scene scene = new Scene(pane, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	
}
