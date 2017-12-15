package utils;

import java.util.ArrayList;
import java.util.List;

public class Day_07_GraphNode {
	private List<Day_07_GraphNode> childNodes;
	private Day_07_GraphNode parentNode;
	private final int id;
	private final String name;
	private int weight = 0;

	public Day_07_GraphNode(int id, String name) {
		this(id, name, null);
	}

	public Day_07_GraphNode(int id, String name, Day_07_GraphNode parent) {
		childNodes = new ArrayList<Day_07_GraphNode>();
		this.id = id;
		this.name = name;
		weight = id;
	}

	public boolean contains(Day_07_GraphNode node) {
		for (Day_07_GraphNode child : childNodes) {
			if (child.contains(node)) {
				return true;
			}
		}

		return false;
	}

	public boolean containsID(int id) {
		if (this.id == id) {
			return true;
		} else {
			for (Day_07_GraphNode child : childNodes) {
				if (child.containsID(id)) {
					return true;
				}
			}

			return false;
		}
	}

	public boolean containsName(String name) {
		if (this.name.equals(name)) {
			return true;
		} else {
			for (Day_07_GraphNode child : childNodes) {
				if (child.containsName(name)) {
					return true;
				}
			}

			return false;
		}
	}

	public List<Day_07_GraphNode> getNodeForID(int id) {
		if (containsID(id)) {
			List<Day_07_GraphNode> nodes = new ArrayList<Day_07_GraphNode>();

			if (this.id == id) {
				nodes.add(this);
			}

			for (Day_07_GraphNode child : childNodes) {
				if (child.getNodeForID(id) != null) {
					nodes.addAll(child.getNodeForID(id));
				}
			}

			return nodes;
		} else {
			return null;
		}
	}

	public Day_07_GraphNode getNodeForName(String name) {
		if (containsName(name)) {
			if (this.name.equals(name)) {
				return this;
			} else {
				for (Day_07_GraphNode child : childNodes) {
					if (child.getNodeForName(name) != null) {
						return child.getNodeForName(name);
					}
				}
			}
		}

		return null;
	}

	public List<Day_07_GraphNode> getChilds() {
		return childNodes;
	}

	public void addChild(Day_07_GraphNode node) {
		childNodes.add(node);
		updateWeight();
	}

	public int getID() {
		return id;
	}

	public Day_07_GraphNode getParent() {
		return parentNode;
	}

	public String getName() {
		return name;
	}

	public boolean hasParent() {
		return parentNode != null;
	}

	public boolean hasChilds() {
		return childNodes != null && !childNodes.isEmpty();
	}

	public void setParent(Day_07_GraphNode parentNode) {
		this.parentNode = parentNode;
	}

	@Override
	public String toString() {
		String str = "Node= " + getSimpleToString() + " Parent= "
				+ (parentNode == null ? null : parentNode.getSimpleToString()) + "\n";
		for (Day_07_GraphNode node : childNodes) {
			String s = node.toString();
			s = ">" + s;
			s.replace("\n", "\n>");
		}

		return str;
	}

	public String getSimpleToString() {
		return "(" + name + "," + id + "," + weight + ")";
	}

	public int getWeight() {
		updateWeight();
		return weight;
	}

	public void updateWeight() {
		weight = id;

		for (Day_07_GraphNode node : childNodes) {
			weight += node.getWeight();
		}
	}

	public int[] getChildsWeight() {
		int[] weights = new int[childNodes.size()];
		for (int i = 0; i < childNodes.size(); i++) {
			weights[i] = childNodes.get(i).getWeight();
		}

		return weights;
	}
}
