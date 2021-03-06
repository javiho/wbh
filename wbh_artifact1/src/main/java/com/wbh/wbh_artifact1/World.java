package com.wbh.wbh_artifact1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class World {
	
	private DistanceMatrix<Trait> traitDistances;
	private List<Category> categories;
	private List<Trait> traits;
	private WorldMap worldMap;

	public World(){
		this.traitDistances = new DistanceMatrix<Trait>();
		this.categories = getCategories();
		this.traits = getTraits();
		populateDistanceMatrix();
		traitDistances.printGraph();
		this.worldMap = new WorldMap();
		populateWorldMap();
		assignLocationTraits();
	}
	
	public DistanceMatrix<Trait> getDistanceMatrix(){
		return this.traitDistances;
	}
	
	public WorldMap getWorldMap(){
		return worldMap;
	}
	
	private List<Category> getCategories(){
		Category[] categories = new Category[]{
				new Category("government"),
				new Category("military"),
				new Category("wealth")
		};
		return Arrays.asList(categories);
	}
	
	private List<Trait> getTraits(){
		Trait[] traits = new Trait[]{
				new Trait("autocracy", categories.get(0)),
				new Trait("democracy", categories.get(0)),
				new Trait("constitutional_monarchy", categories.get(0)),
				new Trait("professional_army", categories.get(1)),
				new Trait("guard_only", categories.get(1)),
				new Trait("universal_conscription", categories.get(1)),
				new Trait("poor", categories.get(2)),
				new Trait("medium", categories.get(2)),
				new Trait("rich", categories.get(2))
		};
		return Arrays.asList(traits);
	}
	
	private void populateDistanceMatrix(){
		// TODO: pitää sallia myös ksisuutaiset kaaret
		String[][] rawEdgeData = {
				{"autocracy", "professional_army", "0.1"},
				{"autocracy", "guard_only", "1.0"},
				{"autocracy", "universal_conscription", "0.1"},
				{"guard_only", "universal_conscription", "1.0"},
				{"universal_conscription", "professional_army", "0.5"},
				
				{"constitutional_monarchy", "autocracy", "0.7"},
				{"democracy", "constitutional_monarchy", "0.3"},
				
				{"poor", "medium", "0.5"},
				{"medium", "rich", "0.1"}
		};
		List<Object[]> edges = new ArrayList<>();
		for(int i = 0; i < rawEdgeData.length; i++){
			Object[] edge = {
					getTraitByName(rawEdgeData[i][0]),
					getTraitByName(rawEdgeData[i][1]),
					Double.parseDouble(rawEdgeData[i][2])
			};
			edges.add(edge);
		}
		for(Object[] edge : edges){
			// Edge values need to be unique. This is why the type can't be primitive type double.
			// double 1.0 == double 1.0, but Double(1.0) != Double(1.0).
			this.traitDistances.addPair((Trait)edge[0], (Trait)edge[1], (Double)edge[2]);
		}
	}
	
	private Trait getTraitByName(String name){
		Trait foundTrait = null;
		int traitsFound = 0;
		for(Trait trait : this.traits){
			if(trait.getOwnName().equals(name)){
				traitsFound++;
				foundTrait = trait;
			}
		}
		if(traitsFound != 1){
			//throw new Exception("Ambiguous trait name.");
			System.out.println("traitsFound: " + traitsFound + " of name " + name + ".");
			assert false;
		}
		return foundTrait;
	}
	
	private void populateWorldMap(){
		Object[][] rawLocationData = new Object[][]{
			{"Helsinki", "5", "9"},
			{"Turku", "3", "8"},
			{"Tampere", "4", "6"},
			{"Oulu", "3", "3"},
			{"Raja-Jooseppi", "8", "1"},
		};
		for(Object[] rawLocationRow : rawLocationData){
			this.worldMap.addLocation((String)rawLocationRow[0],
					Integer.parseInt((String)rawLocationRow[1]),
					Integer.parseInt((String)rawLocationRow[2]));
		}
	}
	
	private void assignLocationTraits(){
		Object[][] rawLocationTraitData = new Object[][]{
			{"Helsinki", "government", "autocracy"},
			{"Helsinki", "wealth", "medium"},
			{"Helsinki", "military", "universal_conscription"},
			{"Turku", "government", "democracy"},
			{"Turku", "wealth", "rich"},
			{"Turku", "military", "professional_army"},
			{"Tampere", "government", "constitutional_monarchy"},
			{"Tampere", "wealth", "poor"},
			{"Tampere", "military", "universal_conscription"},
			{"Oulu", "government", "democracy"},
			{"Oulu", "wealth", "medium"},
			{"Oulu", "military", "guard_only"},
			{"Raja-Jooseppi", "government", "autocracy"},
			{"Raja-Jooseppi", "wealth", "poor"},
			{"Raja-Jooseppi", "military", "guard_only"},
		};
		for(Object[] dataRow : rawLocationTraitData){
			this.worldMap.assignLocationTrait(
					(String)dataRow[0], this.getTraitByName((String)dataRow[2]));
		}
	}
	
}
