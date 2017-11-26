package com.wbh.wbh_artifact1;

import java.util.ArrayList;
import java.util.List;

/*
 * Class invariant: there is 0 or 1 traits in traits List which belong to the same Category.
 */
public class WorldLocation {

	private String name;
	private List<Trait> traits;
	//private final World world;
	
	public WorldLocation(String name){
		this.name = name;
		this.traits = new ArrayList<>();
		//this.world = world;
	}
	
	public boolean canAddTrait(Trait trait){
		Category traitCategory = trait.getCategory();
		for(Trait t : this.traits){
			if(t.getCategory() == traitCategory){
				return false;
			}
		}
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Trait> getTraits() {
		return traits;
	}

	public void setTraits(List<Trait> traits) {
		this.traits = traits;
	}
	
}
