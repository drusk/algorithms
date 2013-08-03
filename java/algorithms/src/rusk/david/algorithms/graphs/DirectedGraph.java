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
import java.util.List;
import java.util.Map;

public class DirectedGraph extends Graph {

	private Map<Node, List<Edge>> incomingEdgesByNode;

	private Map<Node, List<Edge>> outgoingEdgesByNode;

	public DirectedGraph() {
		super();
	}

	public DirectedGraph(Node[] nodes) {
		super(nodes);
	}

	public DirectedGraph(Collection<Node> nodes) {
		super(nodes);
	}

	@Override
	protected void initializeExtraDataStructures() {
		incomingEdgesByNode = new HashMap<Node, List<Edge>>();
		outgoingEdgesByNode = new HashMap<Node, List<Edge>>();
	}

	@Override
	public void addNode(Node node) {
		super.addNode(node);

		incomingEdgesByNode.put(node, new ArrayList<Edge>());
		outgoingEdgesByNode.put(node, new ArrayList<Edge>());
	}

	@Override
	public void addEdge(Node sourceNode, Node targetNode) {
		assert nodes.contains(sourceNode) && nodes.contains(targetNode) : "Nodes must be part of the graph.";

		Edge edge = new Edge(sourceNode, targetNode, true);
		edges.add(edge);
		outgoingEdgesByNode.get(sourceNode).add(edge);
		incomingEdgesByNode.get(targetNode).add(edge);
	}

	@Override
	public List<Node> getAdjacentNodes(Node node) {
		List<Node> adjacentNodes = new ArrayList<Node>();
		for (Edge edge : outgoingEdgesByNode.get(node)) {
			adjacentNodes.add(edge.getAdjacentNode(node));
		}
		return adjacentNodes;
	}

	public DirectedGraph reversed() {
		DirectedGraph reversedGraph = new DirectedGraph(nodes);
		for (Edge edge : edges) {
			reversedGraph.addEdge(edge.getTargetNode(), edge.getSourceNode());
		}

		return reversedGraph;
	}

}
