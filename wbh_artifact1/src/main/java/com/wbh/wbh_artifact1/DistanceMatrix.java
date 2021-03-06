package com.wbh.wbh_artifact1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.google.common.graph.MutableNetwork;
import com.google.common.graph.NetworkBuilder;

/*
 * Is responsible for distances between features (not physical places).
 */
public class DistanceMatrix <T extends Trait>{

	MutableNetwork<T, EdgeData> distances;
	
	public DistanceMatrix(){
		this.distances = NetworkBuilder.directed().build();
		//this.distances.allowsParallelEdges();
		//this.distances.allowsSelfLoops();
	}
	
	/**
	 * Adds edge from node1 to node2, and edge from node2 to node1 with same value.
	 * @param node1
	 * @param node2
	 * @param distance
	 */
	public void addPair(T node1, T node2, Double distance){
		// Edgejen pitää olla uniikkeja - entä jos on kaksi samaa booleania?
		distances.addEdge(node1, node2, new EdgeData(distance));
		distances.addEdge(node2, node1, new EdgeData(distance));
	}
	
	/**
	 * Adds edges between node1 and node2 with two possibly different distances.
	 * @param node1
	 * @param node2
	 * @param distance
	 */
	public void addPair(T node1, T node2, Double distance1to2, Double distance2to1){
		distances.addEdge(node1, node2, new EdgeData(distance1to2));
		distances.addEdge(node2, node1, new EdgeData(distance2to1));
	}
	
	public void printGraph(){
		Set<EdgeData> edgeValues = this.distances.edges();
		for(EdgeData edgeValue : edgeValues){
			System.out.println(edgeValue.toString());
		}
		Set<T> nodes = this.distances.nodes();
		for(T node : nodes){
			Set<T> adjacentNodes = this.distances.adjacentNodes(node);
			System.out.println(node + " adjacent nodes:");
			for(T adjacentNode : adjacentNodes){
				// Since edgeConnecting connects two adjecent nodes, it's guaranteed to exist.
				EdgeData edgeConnecting = this.distances.edgeConnecting(node, adjacentNode).get();
				System.out.println(adjacentNode + ", edge: " + edgeConnecting);
			}
		}
		
	}
	
	public List<T> getNodes() {
		Set<T> nodes = this.distances.nodes();
		//T[] asList = (T[]) nodes.toArray();
		List<T> asList = new ArrayList<T>(nodes);
		// Sorting so that nodes will be returned in more consistent order between different calls.
		Collections.sort(asList,
				(t1, t2) -> t1.getFullName().compareTo(t2.getFullName()));
		return asList;
	}
	
	/**
	 * 
	 * @return list of arrays like: {EdgeData edge, T from-node, T to-node}.
	 */
	public List<Object[]> getEdgesAndNodes(){
		Set<T> nodes = this.distances.nodes();
		List<Object[]> result = new ArrayList<>();
		for(T node1 : nodes){
			for(T node2 : nodes){
				Set<EdgeData> from1to2 = distances.edgesConnecting(node1, node2);
				assert from1to2.size() == 1 || from1to2.size() == 0;
				if(from1to2.size() == 1){
					Object[] resultEdge = new Object[]{
							from1to2.toArray(new EdgeData[0])[0],
							node1,
							node2
					};
					result.add(resultEdge);
				}
				
			}
		}
		return result;
		/*for(T node : nodes){
			Set<EdgeData> outEdges = distances.outEdges(node);
			for(EdgeData outEdge : outEdges){
				Object[] edgeRow = new Object[]{outEdge, node, };
			}
			
		}*/
	}
	
	public void removeEdge(EdgeData edge){
		this.distances.removeEdge(edge);
		
	}
	
	public void removeNode(T node){
		this.distances.removeNode(node);
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
	
	/*public String[] getNodeNames(){
	Set<T> nodes = this.distances.nodes();
	List<String> nodeNames = new ArrayList<>();
	for(T node : nodes){
		nodeNames.add(node.getFullName());
	}
	return nodeNames.toArray(new String[0]);
	}*/
	
}
