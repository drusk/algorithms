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
import java.util.Stack;

public class DepthFirstSearch {

	private List<Node> traversalOrder = new ArrayList<Node>();

	private List<Node> finishOrder = new ArrayList<Node>();

//	private List<Node> previouslyExplored;
	
	/**
	 * Performs depth first search on a graph.
	 * 
	 * @param graph
	 *            The graph to be searched.
	 * @param startNode
	 *            The node to start the search from.
	 */
	public DepthFirstSearch(DirectedGraph graph, Node startNode) {
//		this(graph, startNode, new ArrayList<Node>());
		doIterativeDepthFirstSearch(graph, startNode);
	}

	/**
	 * Performs depth first search on a graph, given some nodes that should
	 * already be considered explored.
	 * 
	 * @param graph
	 *            The graph to be searched.
	 * @param startNode
	 *            The node to start the search from.
	 * @param previouslyExplored
	 *            Nodes which should be considered explored right from the start
	 *            of the search.
	 */
//	public DepthFirstSearch(DirectedGraph graph, Node startNode,
//			List<Node> previouslyExplored) {
//		assert !previouslyExplored.contains(startNode) : "Start node was previously explored.";
//		
//		this.previouslyExplored = previouslyExplored;
//		doIterativeDepthFirstSearch(graph, startNode);
//	}

	private void markExplored(Node node) {
		assert !traversalOrder.contains(node): "Cannot traverse node multiple times.";
		traversalOrder.add(node);
		node.setExplored(true);
	}
	
	private void markFinished(Node node) {
		if (!node.isExplored()) {
			throw new RuntimeException("Node " + node.toString() + " must be explored before it can be finished.");
		}
		if (finishOrder.contains(node)) {
			System.out.println("Traversal order: " + traversalOrder);
//			System.out.println("Previously explored: " + previouslyExplored);
//			System.out.println("Node is unexplored: " + isUnexplored(node));
			throw new RuntimeException("Cannot finish node " + node.toString() + " multiple times: " + finishOrder);
		}
//		assert !finishOrder.contains(node): "Cannot finish node multiple times.";
		node.setFinished(true);
		finishOrder.add(node);
	}

//	private boolean isUnexplored(Node node) {
//		return !previouslyExplored.contains(node) && !traversalOrder.contains(node);
//	}

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
				if (!finishedNode.isFinished()) {
					markFinished(finishedNode);
				}
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
