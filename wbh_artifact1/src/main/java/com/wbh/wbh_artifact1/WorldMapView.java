//package com.wbh.wbh_artifact1;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.vaadin.ui.AbsoluteLayout;
//import com.vaadin.ui.Button;
//
//public class WorldMapView extends AbsoluteLayout{
//
//	//private List<WorldLocation> worldLocations;
//	WorldMap worldMap;
//	
//	public WorldMapView(WorldMap worldMap){
//		super();
//		setStyleName("worldMap");
//		this.worldMap = worldMap;
//		this.setWidth("600px");
//		this.setHeight("600px");
//	}
//	
//	public void visualizeWorldMap(){
//		for(WorldLocation wl : this.worldMap.getWorldLocations()){
//			//coordinates could be transformed if needed in the future
//			Double[] coordinates = this.worldMap.getCoordinates().get(wl);
//			double x = coordinates[0];
//			double y = coordinates[1];
//			addLocation(wl, x, y);
//		}
//	}
//	
//	private void addLocation(WorldLocation worldLocation, double x, double y){
//		Button locationComponent = new Button(worldLocation.getName());
//		this.addComponent(locationComponent, coordinatesToCssString(x, y));
//	}
//	
//	private String coordinatesToCssString(double x, double y){
//		return "left: " + x + "px; top: " + y + "px;";
//	}
//	
//}
