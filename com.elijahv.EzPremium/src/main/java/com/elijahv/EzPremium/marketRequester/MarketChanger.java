package com.elijahv.EzPremium.marketRequester;

import com.elijahv.EzPremium.apiStuff.Requester;
import com.elijahv.EzPremium.graphs.GraphArea;

import gameSpecific.ItemIDMap;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MarketChanger {
	
	
	public static String market = "Martlock";
	public static int timeInterval = 1;
	public static String itemId = "T4_LEATHER";
	public static ChoiceBox<String> markets = new ChoiceBox<String>();
	public static TextField field = new TextField();
	
	public static VBox createChangerMenu(Group root) {

		VBox menu = new VBox();
		field.setTooltip(new Tooltip("Enter item name"));
		VBox fieldBox = new VBox();
		fieldBox.setMaxHeight(250);
		VBox searchList = new VBox();
		ScrollPane scroll = new ScrollPane(searchList);
		fieldBox.getChildren().add(field);
		field.setPromptText("Enter item name");
		markets.setTooltip(new Tooltip("Select market"));
		markets.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				market = markets.getValue();
				
			}
		});
		menu.getChildren().addAll(fieldBox, markets);
		
		

		
		Background onHover = new Background(new BackgroundFill(Color.MEDIUMBLUE, new CornerRadii(0), null));
		Background onExit = new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0), null));
		
		ObservableList<Label> itemList = FXCollections.observableArrayList();
		
		for(String item:ItemIDMap.itemIDs.keySet()) {
			Label itemLabel = new Label(item);
			EventHandler<MouseEvent> itemClickHandler = new EventHandler<MouseEvent>() {
				
				@Override
				public void handle(MouseEvent event) {
					MarketChanger.itemId = ItemIDMap.itemIDs.get(item);
					Requester.getResponse();
					MarketChanger.markets.setItems(FXCollections.observableArrayList(Requester.availableMarket.keySet()));
					MarketChanger.field.setText(item);
					MarketChanger.field.deselect();
					MarketChanger.markets.setValue(MarketChanger.markets.getItems().get(0));
				}
			};
			
			EventHandler<MouseEvent> itemEnterHandler = new EventHandler<MouseEvent>() {
				
				@Override
				public void handle(MouseEvent event) {
					itemLabel.setBackground(onHover);
					
				}
			};
			EventHandler<MouseEvent> itemExitHandler = new EventHandler<MouseEvent>() {
				
				@Override
				public void handle(MouseEvent event) {
					itemLabel.setBackground(onExit);
					
				}
			};
			itemLabel.setOnMouseClicked(itemClickHandler);
			itemLabel.setOnMouseEntered(itemEnterHandler);
			itemLabel.setOnMouseExited(itemExitHandler);
			itemList.add(itemLabel);
		}
		
		field.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				searchList.getChildren().clear();
				System.gc();
				if(newValue==null || newValue.isEmpty()) {
					searchList.getChildren().addAll(itemList);
				}else {
					for(Label item:itemList) {
						if(item.getText().toLowerCase().contains(newValue.toLowerCase())) {
							searchList.getChildren().add(item);
						}
					}
				}
				
			}
		});
		
		
		
		field.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					fieldBox.getChildren().add(scroll);
				}else {
					fieldBox.getChildren().remove(scroll);
				}
				
			}
			
		});
		
		ChoiceBox<Integer> times = new ChoiceBox<Integer>(FXCollections.observableArrayList(1, 6, 24));
		times.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				MarketChanger.timeInterval = times.getValue();
			}
		});
		times.setTooltip(new Tooltip("Time interval"));
		times.setValue(times.getItems().get(0));
		menu.getChildren().add(times);
		
		Button confirm = new Button("Show Graph");
		confirm.setTooltip(new Tooltip("Click to show graph"));
		menu.getChildren().add(confirm);
		confirm.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(market.equals(GraphArea.market) && timeInterval == GraphArea.timeInterval && itemId.equals(GraphArea.itemID)) {
					return;
				}
				Requester.getResponse();
				GraphArea.setupGraphs();
				
			}
		});
		
		
		return menu;
		
	}
	
	
	
	
	
}
