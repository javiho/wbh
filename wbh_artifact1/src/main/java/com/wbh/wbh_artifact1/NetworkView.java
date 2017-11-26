package com.wbh.wbh_artifact1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vaadin.pontus.vizcomponent.VizComponent;
import com.vaadin.pontus.vizcomponent.VizComponent.EdgeClickEvent;
import com.vaadin.pontus.vizcomponent.VizComponent.NodeClickEvent;
import com.vaadin.pontus.vizcomponent.VizComponent.NodeClickListener;
import com.vaadin.pontus.vizcomponent.model.Graph;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class NetworkView extends VerticalLayout{

	Graph graph;
	final VizComponent vizComponent;
	private DistanceMatrix distanceMatrix;
	private Map<Graph.Node, Trait> nodeCorrespondence = new HashMap<>();
	private Map<Graph.Edge, EdgeData> edgeCorrespondence = new HashMap<>();
	private Graph.Node selectedNode;
	private Graph.Edge selectedEdge;
	
	public NetworkView(World world){
		this.vizComponent = new VizComponent();
		//Graph.Node node1 = new Graph.Node("n1");
        //Graph.Node node2 = new Graph.Node("n2");
		
		this.distanceMatrix = world.getDistanceMatrix();

        Graph graph = new Graph("G", Graph.DIGRAPH);
        this.graph = graph;
        
        /*graph.addEdge(node1, node2);
        graph.addEdge(node2, node1);
        Graph.Edge edge1 = graph.getEdge(node1, node2);
        edge1.setParam("color", "red");
        node1.setParam("shape", "box");
        node1.setParam("label", "\"First!\"");
        edge1.setParam("label", "e1");
        edge1.setParam("splines", "none");*/

        vizComponent.setWidth("900px");
        vizComponent.setHeight("600px");
        vizComponent.drawGraph(graph);

        /*Label label = new Label(
                "In this example there are two nodes. "
                        + "The color of the nodes and edges is changed when clicking on them. "
                        + "Note also the tooltip");*/

        setSizeFull();
        //addComponent(label);
        addComponent(vizComponent);
        setExpandRatio(vizComponent, 1);
        setComponentAlignment(vizComponent, Alignment.MIDDLE_CENTER);
        
        vizComponent.addClickListener(new NodeClickListener() {

            @Override
            public void nodeClicked(NodeClickEvent e) {
                selectedNode = e.getNode();
                vizComponent.addCss(e.getNode(), "stroke", "blue");
                vizComponent.addTextCss(e.getNode(), "fill", "blue");
            }

        });

        vizComponent.addClickListener(new VizComponent.EdgeClickListener() {

            @Override
            public void edgeClicked(EdgeClickEvent e) {
            	selectedEdge = e.getEdge();
            	vizComponent.addCss(e.getEdge(), "stroke", "blue");
            	vizComponent.addTextCss(e.getEdge(), "fill", "blue");

            }

        });
	}
	
	public void visualizeDistanceMatrix(DistanceMatrix<Trait> dm){
		/*
		 * lisää nodet
		 * lisää edget niiden välille. lisää arvo edgen labeliksi
		 * 
		 */
		clearGraph(this.graph);
		this.nodeCorrespondence.clear();
		this.nodeCorrespondence.clear();
		
		Map<String, Graph.Node> nodeNames = new HashMap<>();
		List<Trait> nodes = dm.getNodes();
		for(Trait trait : nodes){
			String traitName = trait.getFullName();
			Graph.Node newNode = new Graph.Node(traitName);
			nodeNames.put(traitName, newNode);
			this.nodeCorrespondence.put(newNode, trait);
			this.graph.addNode(newNode);
		}
		List<Object[]> edges = dm.getEdgesAndNodes();
		for(Object[] edge : edges){
			Trait trait1 = (Trait)edge[1];
			Trait trait2 = (Trait)edge[2];
			String node1name = trait1.getFullName();
			String node2name = trait2.getFullName();
			Graph.Node node1 = nodeNames.get(node1name);
			Graph.Node node2 = nodeNames.get(node2name);
			EdgeData edgeData = (EdgeData)edge[0];
			double edgeValue = edgeData.getDistance();
			//this.graph.addNode(new Graph
			Graph.Edge newEdge = this.graph.addEdge(node1, node2);
			this.edgeCorrespondence.put(newEdge, edgeData);
			newEdge.setParam("label", Double.toString(edgeValue));
		}
		
        vizComponent.drawGraph(graph);

	}
	
	public void removeSelectedEdge(){
		System.out.println("removeSelectedEdge called");
		EdgeData removed = this.edgeCorrespondence.get(this.selectedEdge);
		this.distanceMatrix.removeEdge(removed);
		this.visualizeDistanceMatrix(this.distanceMatrix);
		System.out.println("edge removed");
	}
	
	public void removeSelectedNode(){
		System.out.println("removeSelectedNode called");
		Trait removed = this.nodeCorrespondence.get(this.selectedNode);
		this.distanceMatrix.removeNode(removed);
		this.visualizeDistanceMatrix(this.distanceMatrix);
		System.out.println("edge removed");
	}
	
	/*public EdgeData getSelectedEdge(){
		return this.edgeCorrespondence.get(this.selectedEdge);
	}
	
	public Trait getSelectedNode(){
		return this.nodeCorrespondence.get(this.selectedNode);
	}*/
	
	private void clearGraph(Graph graph){
		Set<Graph.Node> nodes = graph.getNodes();
		// Can't iterate directly over nodes Set while removing.
		Graph.Node[] nodeArray = nodes.toArray(new Graph.Node[0]);
		for(Graph.Node n : nodeArray){
			System.out.println("clearGraph: removing node");
			graph.remove(n);
		}
		assert(nodes.size() == 0);
		Set<Graph.Edge> edges = graph.getEdges();
		Graph.Node[] edgeArray = nodes.toArray(new Graph.Node[0]);
		for(Graph.Node e : edgeArray){
			System.out.println("clearGraph: removing edge");
			graph.remove(e);
		}
		assert(edges.size() == 0);
	}
	
}
