package com.wbh.wbh_artifact1;

import com.vaadin.ui.Table;

public class LocationInfoBox extends Table{

	public LocationInfoBox(){
		this.addContainerProperty("Category", String.class, null);
		this.addContainerProperty("Value", String.class, null);
	}
	
	public void presentWorldLocation(WorldLocation worldLocation){
		//this.clear();
		// TODO: poistettava varmemmalla/lyhyemmällä tavalla
		int itemCount = this.getItemIds().size();
		//System.out.println("item cunt: " + itemCount);
		for(int itemId = 1; itemId <= itemCount; itemId++){
			this.removeItem(itemId);
		}
		assert this.getItemIds().size() == 0;
		int idCounter = 1;
		
		for(Trait t : worldLocation.getTraits()){
			this.addItem(new Object[]{t.getCategory().getName(), t.getOwnName()}, idCounter);
			idCounter++;
			//System.out.println("wl info box: added " + t.getCategory().getName() + " " + t.getOwnName());
		}
		
	}
}
