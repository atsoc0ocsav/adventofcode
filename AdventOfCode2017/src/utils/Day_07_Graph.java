package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class Day_07_Graph {
	private HashMap<Integer, List<Day_07_GraphNode>> nodesPerID;
	private HashMap<String, Day_07_GraphNode> nodes;

	public Day_07_Graph() {
		nodesPerID = new HashMap<Integer, List<Day_07_GraphNode>>();
		nodes = new HashMap<String, Day_07_GraphNode>();
	}

	public Day_07_GraphNode addElementToGraph(int id, String name) {
		Day_07_GraphNode node = new Day_07_GraphNode(id, name);
		nodes.put(name, node);

		if (nodesPerID.containsKey(id)) {
			List<Day_07_GraphNode> programsForID = nodesPerID.get(id);
			programsForID.add(node);
			return node;
		} else {
			List<Day_07_GraphNode> list = new ArrayList<Day_07_GraphNode>();
			list.add(node);
			nodesPerID.put(id, list);
		}

		return node;
	}

	public void addChild(int parentID, String parentName, String childName) {
		if (!nodesPerID.containsKey(parentID)) {
			addElementToGraph(parentID, parentName);
		}

		List<Day_07_GraphNode> nodesList = nodesPerID.get(parentID);
		boolean found = false;
		for (Day_07_GraphNode pNode : nodesList) {
			if (pNode.getName().equals(parentName)) {
				found = true;

				Day_07_GraphNode node = nodes.get(childName);
				node.setParent(pNode);
				pNode.addChild(node);
			}
		}

		if (!found) {
			throw new NoSuchElementException("Parent node not found for Name=" + parentName);
		}
	}

	public Day_07_GraphNode getRoot() {
		Integer[] keysAsArray = nodesPerID.keySet().stream().toArray(Integer[]::new);
		Day_07_GraphNode node = nodesPerID.get((int) keysAsArray[0]).get(0);

		while (node.hasParent()) {
			node = node.getParent();
		}

		return node;
	}

	public void printGraph() {
		for (int id : nodesPerID.keySet()) {
			List<Day_07_GraphNode> node = nodesPerID.get(id);

			System.out.println("ID: " + id);
			for (Day_07_GraphNode l : node) {
				System.out.print(">>>>> " + l.toString());
			}

			System.out.println();
		}
	}

	public void calculateWeight() {
		for (String name : nodes.keySet()) {
			nodes.get(name).updateWeight();
		}
	}
}
