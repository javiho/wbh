package com.wbh.wbh_artifact1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;

public class WorldMap extends AbsoluteLayout{
	
	private List<WorldLocation> worldLocations;
	private Map<WorldLocation, Integer[]> coordinates;
	
	public WorldMap(){
		super();
		this.worldLocations = new ArrayList<>();
		this.coordinates = new HashMap<>();
		this.setWidth("600px");
		this.setHeight("600px");
	}
	
	public void addLocation(String name, int x, int y){
		WorldLocation wl = new WorldLocation(name);
		worldLocations.add(wl);
		Integer[] coordinates = new Integer[]{x, y};
		this.coordinates.put(wl, coordinates);
	}
	
	public List<WorldLocation> getWorldLocations(){
		return this.worldLocations;
	}

	public Map<WorldLocation, Integer[]> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Map<WorldLocation, Integer[]> coordinates) {
		this.coordinates = coordinates;
	}

	public void setWorldLocations(List<WorldLocation> worldLocations) {
		this.worldLocations = worldLocations;
	}
	
}
