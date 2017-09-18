package com.wbh.wbh_artifact1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;

public class WorldMap extends AbsoluteLayout{
	
	private List<WorldLocation> worldLocations;
	private Map<WorldLocation, Double[]> coordinates;
	
	public WorldMap(){
		super();
		this.worldLocations = new ArrayList<>();
		this.coordinates = new HashMap<>();
		this.setWidth("600px");
		this.setHeight("600px");
	}
	
	public void addLocation(String name, double x, double y){
		WorldLocation wl = new WorldLocation(name);
		worldLocations.add(wl);
		Double[] coordinates = new Double[]{x, y};
		this.coordinates.put(wl, coordinates);
	}
	
	public List<WorldLocation> getWorldLocations(){
		return this.worldLocations;
	}

	public Map<WorldLocation, Double[]> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Map<WorldLocation, Double[]> coordinates) {
		this.coordinates = coordinates;
	}

	public void setWorldLocations(List<WorldLocation> worldLocations) {
		this.worldLocations = worldLocations;
	}
	
}