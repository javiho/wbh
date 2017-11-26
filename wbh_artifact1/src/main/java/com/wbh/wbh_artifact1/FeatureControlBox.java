package com.wbh.wbh_artifact1;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

public class FeatureControlBox extends HorizontalLayout{
	
	//final DistanceMatrix distanceMatrix;
	private final NetworkView networkView;
	
	public FeatureControlBox(NetworkView networkView){
		super();
		this.networkView = networkView;
		this.addComponent(new Button("Remove edge", (e) -> networkView.removeSelectedEdge()));
		this.addComponent(new Button("Remove node", (e) -> networkView.removeSelectedNode()));
	}
	
}
