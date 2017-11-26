package com.wbh.wbh_artifact1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;

public class WorldMap{
	
	private List<WorldLocation> worldLocations;
	private Map<WorldLocation, Integer[]> coordinates;
	
	public WorldMap(){
		super();
		this.worldLocations = new ArrayList<>();
		this.coordinates = new HashMap<>();
	}
	
	public void addLocation(String name, int x, int y){
		WorldLocation wl = new WorldLocation(name);
		worldLocations.add(wl);
		Integer[] coordinates = new Integer[]{x, y};
		this.coordinates.put(wl, coordinates);
	}
	
	public void assignLocationTrait(String locationName, Trait trait){
		WorldLocation wl = this.getWorldLocationByName(locationName);
		if(wl.canAddTrait(trait)){
			wl.getTraits().add(trait);
		}else{
			assert false;
		}
	}
	
	public int getDistanceBetween(WorldLocation from, WorldLocation to){
		int distX = Math.abs(coordinates.get(from)[0] - coordinates.get(to)[0]);
		int distY = Math.abs(coordinates.get(from)[1] - coordinates.get(to)[1]);
		return distX + distY;
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
	
	private WorldLocation getWorldLocationByName(String name){
		for(WorldLocation wl : this.worldLocations){
			if(wl.getName().equals(name)){
				return wl;
			}
		}
		//throw new Exception("World location " + name + " not found.");
		assert false;
		return null;
	}
	
}
