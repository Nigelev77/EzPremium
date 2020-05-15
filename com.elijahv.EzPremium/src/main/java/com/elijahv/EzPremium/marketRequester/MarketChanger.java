package com.elijahv.EzPremium.marketRequester;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.elijahv.EzPremium.apiStuff.Requester;
import com.elijahv.EzPremium.graphs.GraphArea;

import gameSpecific.ItemIDMap;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
	
	public static VBox createChangerMenu(Group root) {

		VBox menu = new VBox();
		TextField field = new TextField();
		field.setTooltip(new Tooltip("Enter item name"));
		VBox fieldBox = new VBox();
		fieldBox.setMaxHeight(250);
		VBox searchList = new VBox();
		ScrollPane scroll = new ScrollPane(searchList);
		fieldBox.getChildren().add(field);
		field.setPromptText("Enter item name");
		FilteredList<String> filter = new FilteredList<String>(FXCollections.observableArrayList(ItemIDMap.itemIDs.keySet()));
		ChoiceBox<String> markets = new ChoiceBox<String>();
		markets.setTooltip(new Tooltip("Select market"));
		markets.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				market = markets.getValue();
				
			}
		});
		menu.getChildren().addAll(fieldBox, markets);
		field.textProperty().addListener((list, oldVal, newVal) -> {
			filter.setPredicate(string -> {
				if(newVal == null || newVal.isEmpty()) {
					return true;
				}
				
				String newValLower = newVal.toLowerCase();
				if(string.toLowerCase().contains(newValLower)) {
					return true;
				}
				return false;
			});
		});
		
		Background onHover = new Background(new BackgroundFill(Color.MEDIUMBLUE, new CornerRadii(0), null));
		Background onExit = new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0), null));
		
		
		SortedList<String> sorted = new SortedList<String>(filter);
		
		sorted.addListener(new ListChangeListener<String>() {


			@Override
			public void onChanged(Change<? extends String> c) {
				searchList.getChildren().clear();
				List<Label> items = new ArrayList<Label>();
				List<String> list = new ArrayList<String>(sorted);
				Collections.sort(list);
				for(String item:list) {
					Label label = new Label(item);
					EventHandler<MouseEvent> itemClickHandler = new EventHandler<MouseEvent>() {
						
						@Override
						public void handle(MouseEvent event) {
							itemId = ItemIDMap.itemIDs.get(item);
							Requester.getResponse();
							markets.setItems(FXCollections.observableArrayList(Requester.availableMarket.keySet()));
							field.setText(item);
							field.deselect();
							markets.setValue(markets.getItems().get(0));
						}
					};
					EventHandler<MouseEvent> itemEnterHandler = new EventHandler<MouseEvent>() {
						
						@Override
						public void handle(MouseEvent event) {
							label.setBackground(onHover);
							
						}
					};
					EventHandler<MouseEvent> itemExitHandler = new EventHandler<MouseEvent>() {
						
						@Override
						public void handle(MouseEvent event) {
							label.setBackground(onExit);
							
						}
					};
					label.setOnMouseClicked(itemClickHandler);
					label.setOnMouseEntered(itemEnterHandler);
					label.setOnMouseExited(itemExitHandler);
					items.add(label);
				}
				searchList.getChildren().addAll(items);
				
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
				Requester.getResponse();
				GraphArea.setupGraphs();
				
			}
		});
		
		
		return menu;
		
	}
	
	
	
	
	
}
