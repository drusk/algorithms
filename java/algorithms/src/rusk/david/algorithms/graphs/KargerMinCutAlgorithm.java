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


/**
 * A randomized algorithm to compute the minimum cut of a connected graph
 * (http://en.wikipedia.org/wiki/Karger%27s_algorithm).
 * 
 * @author drusk
 * 
 */
public class KargerMinCutAlgorithm {

	private UndirectedGraph graph;

	private RandomNodeSelector randomNodeSelector;

	/**
	 * 
	 * @param graph
	 *            the graph to perform the min cut on.
	 */
	public KargerMinCutAlgorithm(UndirectedGraph graph,
			RandomNodeSelector randomNodeSelector) {
		this.graph = graph;
		this.randomNodeSelector = randomNodeSelector;
	}

	/**
	 * 
	 * @return the minimum number of crossing edges when partitioning the graph
	 *         in two.
	 */
	public int getMinCutSize() {
		while (graph.getNodeCount() > 2) {
			contractGraph();
		}

		return graph.getEdgeCount();
	}

	/**
	 * Picks a remaining edge and merges the attached nodes.
	 */
	private void contractGraph() {
		/*
		 * Randomly choose two connected nodes to merge (i.e. an edge to
		 * contract).
		 */
		Node node1 = randomNodeSelector.selectNode(graph.getNodes());
		Node node2 = randomNodeSelector.selectNode(node1.getConnectedNodes());

		graph.mergeNodes(node1, node2);
	}

}
