package com.wbh.wbh_artifact1;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;

public class WorldMapGrid extends GridLayout{

	//private List<WorldLocation> worldLocations;
	WorldMap worldMap;
	List<Button> worldLocationTiles;
	
	public WorldMapGrid(WorldMap worldMap){
		super();
		setStyleName("worldMap");
		this.worldMap = worldMap;
		this.setWidth("600px");
		this.setHeight("600px");
		this.setColumns(10);
		this.setRows(10);
		worldLocationTiles = new ArrayList<>();
		this.fillEmptySlots();
	}
	
	public void visualizeWorldMap(){
		this.clearSlots();
		for(WorldLocation wl : this.worldMap.getWorldLocations()){
			//coordinates could be transformed if needed in the future
			Integer[] coordinates = this.worldMap.getCoordinates().get(wl);
			int x = coordinates[0];
			int y = coordinates[1];
			addLocation(wl, x, y);
		}
		this.fillEmptySlots();
	}
	
	private void addLocation(WorldLocation worldLocation, int x, int y){
		Button locationComponent = new Button(worldLocation.getName());
		locationComponent.setSizeFull();
		worldLocationTiles.add(locationComponent);
		this.addComponent(locationComponent, x, y);
	}
	
	private void fillEmptySlots(){
		for(int i = 0; i < this.getColumns(); i++){
			for(int j = 0; j < this.getRows(); j++){
				if(this.getComponent(j, i) == null){
					Button defaultTile = new Button();
					defaultTile.setSizeFull();
					this.addComponent(defaultTile); // Without text buttons are too small.
				}
			}
		}
	}
	
	private void clearSlots(){
		this.removeAllComponents();
	}
	
	private String coordinatesToCssString(double x, double y){
		return "left: " + x + "px; top: " + y + "px;";
	}
	
}