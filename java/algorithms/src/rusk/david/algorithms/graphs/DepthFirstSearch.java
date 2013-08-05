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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class DepthFirstSearch {

	private List<Node> traversalOrder = new ArrayList<Node>();

	private List<Node> finishOrder = new ArrayList<Node>();

	/**
	 * Performs depth first search on a graph.
	 * 
	 * @param graph
	 *            The graph to be searched.
	 * @param startNode
	 *            The node to start the search from.
	 */
	public DepthFirstSearch(DirectedGraph graph, Node startNode) {
		doRecursiveDepthFirstSearch(graph, startNode);
	}

	private void markExplored(Node node) {
		traversalOrder.add(node);
		node.setExplored(true);
	}

	private void markFinished(Node node) {
		if (!node.isFinished()) {
			node.setFinished(true);
			finishOrder.add(node);
		}
	}

	private void doRecursiveDepthFirstSearch(DirectedGraph graph, Node startNode) {
		assert graph.getNodes().contains(startNode) : "Graph must contain start node.";

		markExplored(startNode);

		for (Node connectedNode : graph.getAdjacentNodes(startNode)) {
			if (!connectedNode.isExplored()) {
				doRecursiveDepthFirstSearch(graph, connectedNode);
			}
		}

		markFinished(startNode);
	}

	private void doIterativeDepthFirstSearch(DirectedGraph graph, Node startNode) {
		assert graph.getNodes().contains(startNode) : "Graph must contain start node.";

		Deque<Node> nodesToVisit = new ArrayDeque<Node>(graph.getNodeCount());
		nodesToVisit.push(startNode);

		while (!nodesToVisit.isEmpty()) {
			Node currentNode = nodesToVisit.peek();
			if (!currentNode.isExplored()) {
				markExplored(currentNode);

				for (Node adjacentNode : graph.getAdjacentNodes(currentNode)) {
					if (!adjacentNode.isExplored()) {
						nodesToVisit.push(adjacentNode);
					}
				}
			} else {
				/* We have seen this node before, so it is 'finished'. */
				Node finishedNode = nodesToVisit.pop();
				markFinished(finishedNode);
			}
		}

	}

	/**
	 * 
	 * @return A list of nodes in the order they were encountered by the depth
	 *         first search.
	 */
	public List<Node> getTraversalOrder() {
		return traversalOrder;
	}

	/**
	 * 
	 * @return A list of nodes in the order they were finished being explored
	 *         (including all of their descendants).
	 */
	public List<Node> getFinishOrder() {
		return finishOrder;
	}

}
