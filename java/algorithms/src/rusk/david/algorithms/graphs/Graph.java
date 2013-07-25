/******************************************************************************
 * Copyright (C) 2013 David Rusk
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 *****************************************************************************/
package rusk.david.algorithms.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {

	private Map<Node, List<Node>> nodeAdjacencies = new HashMap<Node, List<Node>>();

	public Graph(Node[] nodes) {
		for (Node node : nodes) {
			addNode(node);
		}
	}

	public void addNode(Node node) {
		nodeAdjacencies.put(node, new ArrayList<Node>());
	}

	public void addEdge(Node node1, Node node2) {
		nodeAdjacencies.get(node1).add(node2);
		nodeAdjacencies.get(node2).add(node1);
	}

	public boolean hasEdge(Node node1, Node node2) {
		return nodeAdjacencies.get(node1).contains(node2);
	}

	public int getNodeCount() {
		return nodeAdjacencies.size();
	}

	public int getEdgeCount() {
		int endCount = 0;
		for (List<Node> connectedNodes : nodeAdjacencies.values()) {
			endCount += connectedNodes.size();
		}
		return endCount / 2;
	}

	public Set<Node> getNodes() {
		return nodeAdjacencies.keySet();
	}

	public List<Node> getConnectedNodes(Node node) {
		return nodeAdjacencies.get(node);
	}

	public Node mergeNodes(Node node1, Node node2) {
		Node superNode = new Node(node1.getLabel() + "-" + node2.getLabel());
		addNode(superNode);

		// TODO refactor
		List<Node> nodesConnectedToNode1 = copyConnectedNodes(node1);
		List<Node> nodesConnectedToNode2 = copyConnectedNodes(node2);

		for (Node connectedNode : nodesConnectedToNode1) {
			nodeAdjacencies.get(connectedNode).remove(node1);
			addEdge(connectedNode, superNode);
		}

		for (Node connectedNode : nodesConnectedToNode2) {
			nodeAdjacencies.get(connectedNode).remove(node2);
			addEdge(connectedNode, superNode);
		}

		nodeAdjacencies.get(superNode).remove(node1);
		nodeAdjacencies.get(superNode).remove(node2);

		nodeAdjacencies.remove(node1);
		nodeAdjacencies.remove(node2);

		return superNode;
	}

	/*
	 * Use to avoid concurrent modification errors.
	 */
	private List<Node> copyConnectedNodes(Node node) {
		List<Node> connectedNodes = new ArrayList<Node>();
		connectedNodes.addAll(getConnectedNodes(node));
		return connectedNodes;
	}

}
