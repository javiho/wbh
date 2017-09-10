package com.wbh.wbh_artifact1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.graph.MutableNetwork;
import com.google.common.graph.NetworkBuilder;

public class DistanceMatrix <T>{

	MutableNetwork<T, EdgeData> distances;
	
	public DistanceMatrix(){
		this.distances = NetworkBuilder.directed().build();
		this.distances.allowsParallelEdges();
		this.distances.allowsSelfLoops();
	}
	
	/**
	 * Adds edge from node1 to node2, and edge from node2 to node1 with same value.
	 * @param node1
	 * @param node2
	 * @param distance
	 */
	public void addPair(T node1, T node2, EdgeData distance){
		// Edgejen pitää olla uniikkeja - entä jos on kaksi samaa booleania?
		distances.addEdge(node1, node2, distance);
		distances.addEdge(node2, node1, distance);
	}
	
	/**
	 * Adds edges between node1 and node2 with two possibly different distances.
	 * @param node1
	 * @param node2
	 * @param distance
	 */
	public void addPair(T node1, T node2, Double distance1to2, Double distance2to1){
		distances.addEdge(node1, node2, distance1to2);
		distances.addEdge(node2, node1, distance2to1);
	}
	
	public void printGraph(){
		Set<Double> edgeValues = this.distances.edges();
		for(Double edgeValue : edgeValues){
			System.out.println(edgeValue.toString());
		}
	}
	
	/*private Map<T, Map<T, Double>> distances;
	
	public DistanceMatrix(){
		distances = new HashMap<>();
	}
	
	public Map<T, Double> getDistancesFrom(T element){
		return distances.get(element);
	}
	
	public double getDistance(T from, T to){
		return getDistancesFrom(from).get(to);
	}
	
	public boolean isSymmetricDistance(T element1, T element2){
		return getDistance(element1, element2) == getDistance(element2, element1);
	}
	
	public void addPair(T element1, T element2, double distance){
		//
		// jos e1 on jo entuudestaan ja jos e2 on sen etäisyyslistassa:
		// 		virhe
		// jos e2 on jo entuudestaan ja jos e1 on sen etäisyyslistassa:
		// 		virhe
		// jos e1 == e2: virhe
		 // 	jos e1 on jo
		 //		lisää e2 tällä etäisyydellä
		 //
	}*/
	
	/*
	private List<List<T>> content;
	private List<Trait> rowObjects;
	
	public DistanceMatrix(){
		content = new ArrayList<>();
		rowObjects = new ArrayList<>();
	}
	
	public T get(int i, int j){
		return content.get(i).get(j);
	}
	
	public T getRow(int index){
		return content.get(i).get(j);
	}
	
	public T getRow(Trait trait){
		
	}
	*/
	
}