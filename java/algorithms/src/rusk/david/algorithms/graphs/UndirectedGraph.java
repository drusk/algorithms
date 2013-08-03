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
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A graph structure where edges are undirected. Parallel edges are allowed.
 * 
 * @author drusk
 * 
 */
public class UndirectedGraph {

	private Set<Node> nodes = new HashSet<Node>();

	private Set<Edge> edges = new HashSet<Edge>();

	private Map<Node, List<Edge>> edgesByNode = new HashMap<Node, List<Edge>>();

	public UndirectedGraph() {
	}

	public UndirectedGraph(Node[] nodes) {
		for (Node node : nodes) {
			addNode(node);
		}
	}

	public UndirectedGraph(Collection<Node> nodes) {
		this(nodes.toArray(new Node[nodes.size()]));
	}

	public void addNode(Node node) {
		nodes.add(node);
		edgesByNode.put(node, new ArrayList<Edge>());
	}

	public void addEdge(Node sourceNode, Node targetNode) {
		assert nodes.contains(sourceNode) && nodes.contains(targetNode) : "Nodes must be part of the graph.";

		Edge edge = new Edge(sourceNode, targetNode, false);
		edges.add(edge);
		edgesByNode.get(sourceNode).add(edge);
		edgesByNode.get(targetNode).add(edge);
	}

	public void removeEdge(Edge edge) {
		edges.remove(edge);

		for (List<Edge> edgeList : edgesByNode.values()) {
			edgeList.remove(edge);
		}
	}

	public boolean hasEdge(Node sourceNode, Node targetNode) {
		return getAdjacentNodes(sourceNode).contains(targetNode);
	}

	public int getNodeCount() {
		return nodes.size();
	}

	public int getEdgeCount() {
		return edges.size();
	}

	public Set<Node> getNodes() {
		return nodes;
	}

	public Set<Edge> getEdges() {
		return edges;
	}

	public Node mergeNodes(Node node1, Node node2) {
		Node superNode = new Node(node1.getId() + "-" + node2.getId());
		addNode(superNode);

		List<Node> connectedNodes = new ArrayList<Node>();
		for (Node node : getAdjacentNodes(node1)) {
			if (!node.equals(node1) && !node.equals(node2)) {
				connectedNodes.add(node);
			}
		}
		for (Node node : getAdjacentNodes(node2)) {
			if (!node.equals(node1) && !node.equals(node2)) {
				connectedNodes.add(node);
			}
		}

		Set<Edge> edgesToDelete = new HashSet<Edge>();
		edgesToDelete.addAll(edgesByNode.get(node1));
		edgesToDelete.addAll(edgesByNode.get(node2));

		nodes.remove(node1);
		nodes.remove(node2);
		edgesByNode.remove(node1);
		edgesByNode.remove(node2);

		for (Edge edge : edgesToDelete) {
			removeEdge(edge);
		}

		for (Node connectedNode : connectedNodes) {
			addEdge(superNode, connectedNode);
		}

		return superNode;
	}

	public List<Node> getAdjacentNodes(Node node) {
		List<Node> adjacentNodes = new ArrayList<Node>();
		for (Edge edge : edgesByNode.get(node)) {
			adjacentNodes.add(edge.getAdjacentNode(node));
		}
		return adjacentNodes;
	}

}
