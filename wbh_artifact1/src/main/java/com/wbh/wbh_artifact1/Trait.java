package com.wbh.wbh_artifact1;

public class Trait {

	private String ownName;
	private Category category;
	
	public Trait(String ownName, Category category){
		this.ownName = ownName;
		this.category = category;
	}
	
	public String toString(){
		return getFullName();
	}
	
	public String getFullName(){
		return this.category.getName() + " - " + this.ownName;
	}

	public String getOwnName() {
		return ownName;
	}

	public void setOwnName(String ownName) {
		this.ownName = ownName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
