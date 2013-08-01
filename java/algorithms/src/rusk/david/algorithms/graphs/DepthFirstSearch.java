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

public class DepthFirstSearch {

	private Map<Node, Boolean> explored = new HashMap<Node, Boolean>();

	private List<Node> traversalOrder = new ArrayList<Node>();

	/**
	 * Performs depth first search on a graph.
	 * 
	 * @param graph
	 *            The graph to be searched.
	 */
	public DepthFirstSearch(DirectedGraph graph, Node startNode) {
		assert graph.getNodes().contains(startNode) : "Graph must contain start node.";

		for (Node node : graph.getNodes()) {
			explored.put(node, false);
		}

		doDepthFirstSearch(graph, startNode);
	}

	private void markExplored(Node node) {
		explored.put(node, true);
		traversalOrder.add(node);
	}

	private boolean isUnexplored(Node node) {
		return !explored.get(node);
	}

	private void doDepthFirstSearch(DirectedGraph graph, Node startNode) {
		markExplored(startNode);

		for (Node connectedNode : graph.getAdjacentNodes(startNode)) {
			if (isUnexplored(connectedNode)) {
				doDepthFirstSearch(graph, connectedNode);
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

}
