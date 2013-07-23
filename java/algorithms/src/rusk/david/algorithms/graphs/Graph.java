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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {

	private Map<Node, Set<Node>> nodeAdjacencies = new HashMap<Node, Set<Node>>();

	public Graph(Node[] nodes) {
		for (Node node : nodes) {
			addNode(node);
		}
	}

	public void addNode(Node node) {
		nodeAdjacencies.put(node, new HashSet<Node>());
	}

	public void addEdge(Node node1, Node node2) {
		nodeAdjacencies.get(node1).add(node2);
		nodeAdjacencies.get(node2).add(node1);
	}

	public int getNodeCount() {
		return nodeAdjacencies.size();
	}

	public int getEdgeCount() {
		int endCount = 0;
		for (Set<Node> nodeSet : nodeAdjacencies.values()) {
			endCount += nodeSet.size();
		}
		return endCount / 2;
	}

	public Set<Node> getNodes() {
		return nodeAdjacencies.keySet();
	}

	public Set<Node> getConnectedNodes(Node node) {
		return nodeAdjacencies.get(node);
	}

	public void mergeNodes(Node node1, Node node2) {
		Node superNode = new Node(node1.getLabel() + "-" + node2.getLabel());
		addNode(superNode);

		Set<Node> connectedNodes = new HashSet<Node>();
		connectedNodes.addAll(getConnectedNodes(node1));
		connectedNodes.addAll(getConnectedNodes(node2));

		connectedNodes.remove(node1);
		connectedNodes.remove(node2);

		for (Node connectedNode : connectedNodes) {
			addEdge(superNode, connectedNode);
		}

		nodeAdjacencies.remove(node1);
		nodeAdjacencies.remove(node2);
	}

}
