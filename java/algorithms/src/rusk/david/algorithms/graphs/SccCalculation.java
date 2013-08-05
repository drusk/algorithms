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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Calculates strongly connected components of a graph using Kosaraju's method.
 * 
 * @author drusk
 * 
 */
public class SccCalculation {

	private class DfsLoop {

		private List<Node> finishingOrder = new ArrayList<Node>();

		private Map<Node, List<Node>> nodesByLeader = new HashMap<Node, List<Node>>();

		public DfsLoop(DirectedGraph graph, List<Node> nodeProcessingOrder) {
			for (Node node : nodeProcessingOrder) {
				if (node.isExplored()) {
					// Node already explored
					continue;
				}

				List<Node> discoveredNodes = new DepthFirstSearch(graph, node)
						.getFinishOrder();
				finishingOrder.addAll(discoveredNodes);

				nodesByLeader.put(node, discoveredNodes);
			}

		}

		public List<Node> getFinishingOrder() {
			return finishingOrder;
		}

		public Map<Node, List<Node>> getNodesByLeader() {
			return nodesByLeader;
		}
	}

	private List<Scc> stronglyConnectedComponents = new ArrayList<Scc>();

	public SccCalculation(DirectedGraph graph) {
		DirectedGraph reversedGraph = graph.reversed();

		DfsLoop firstLoop = new DfsLoop(reversedGraph, new ArrayList<Node>(
				reversedGraph.getNodes()));
		List<Node> finishingOrder = firstLoop.getFinishingOrder();

		// Reset graph
		for (Node node : graph.getNodes()) {
			node.setExplored(false);
			node.setFinished(false);
		}

		Collections.reverse(finishingOrder);

		DfsLoop secondLoop = new DfsLoop(graph, finishingOrder);
		for (List<Node> sccNodes : secondLoop.getNodesByLeader().values()) {
			stronglyConnectedComponents.add(new Scc(sccNodes));
		}
	}

	public List<Scc> getSccs() {
		return stronglyConnectedComponents;
	}

}
