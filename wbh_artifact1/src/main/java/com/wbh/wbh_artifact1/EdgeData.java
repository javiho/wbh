package com.wbh.wbh_artifact1;

/**
 * Contais data for edge in distance matrix.
 * @author jaaho
 *
 */
public class EdgeData{
	
	private double distance;
	
	public EdgeData(double distance){
		this.distance = distance;
	}
	
	public String toString(){
		return Double.toString(distance);
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
}
