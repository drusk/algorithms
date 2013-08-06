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

/**
 * Calculates the single source, shortest paths using Dijkstra's algorithm.
 * 
 * @author drusk
 * 
 */
public class ShortestPathsCalculation {

	public static final int INFINITE_DISTANCE = 1000000;

	private Map<Node, Integer> shortestPathLengths = new HashMap<Node, Integer>();

	private Graph graph;

	private int numNodesProcessed = 0;

	private boolean noFrontierEdges = false;

	/**
	 * Calculates the shortest paths from a source node to all other nodes on a
	 * graph.
	 * 
	 * @param graph
	 *            The graph defining the nodes and edges on which the search is
	 *            to be performed.
	 * @param sourceNode
	 *            The node from which the shortest path to each other node is to
	 *            be calculated.
	 */
	public ShortestPathsCalculation(Graph graph, Node sourceNode) {
		this.graph = graph;

		for (Node node : graph.getNodes()) {
			shortestPathLengths.put(node, INFINITE_DISTANCE);
		}

		shortestPathLengths.put(sourceNode, 0);
		markProcessed(sourceNode);

		while (!allNodesProcessed()) {
			processNode();
		}
	}

	/**
	 * @return A map containing each node and its distance from the source node.
	 */
	public Map<Node, Integer> getPathLengths() {
		return shortestPathLengths;
	}

	private boolean allNodesProcessed() {
		return noFrontierEdges || numNodesProcessed == graph.getNodeCount();
	}

	private void markProcessed(Node node) {
		node.setExplored(true);
		numNodesProcessed++;
	}

	private void processNode() {
		Edge minEdge = null;
		int minDijkstraCriterion = Integer.MAX_VALUE;

		Set<Edge> frontierEdges = getFrontierEdges();
		if (frontierEdges.isEmpty()) {
			noFrontierEdges = true;
			return;
		}

		for (Edge edge : frontierEdges) {
			int dijkstraGreedyCriterion = dijkstraGreedyCriterion(edge);
			if (dijkstraGreedyCriterion < minDijkstraCriterion) {
				minDijkstraCriterion = dijkstraGreedyCriterion;
				minEdge = edge;
			}
		}

		assert minEdge != null : "No edges to remaining nodes";

		/* TODO refactor */
		Node processedNode = null;
		if (!minEdge.getTargetNode().isExplored()) {
			processedNode = minEdge.getTargetNode();
		} else {
			processedNode = minEdge.getSourceNode();
		}
		markProcessed(processedNode);
		shortestPathLengths.put(processedNode, minDijkstraCriterion);
	}

	private int dijkstraGreedyCriterion(Edge edge) {
		/* TODO refactor */
		Node processedNode = null;
		if (edge.getSourceNode().isExplored()) {
			processedNode = edge.getSourceNode();
		} else {
			processedNode = edge.getTargetNode();
		}
		return shortestPathLengths.get(processedNode) + edge.getWeight();
	}

	private Set<Edge> getFrontierEdges() {
		Set<Edge> frontierEdges = new HashSet<Edge>();
		for (Edge edge : graph.getEdges()) {
			Node sourceNode = edge.getSourceNode();
			Node targetNode = edge.getTargetNode();
			/* TODO refactor */
			if ((sourceNode.isExplored() && !targetNode.isExplored())
					|| (!sourceNode.isExplored() && targetNode.isExplored())) {
				frontierEdges.add(edge);
			}
		}
		return frontierEdges;
	}
}
