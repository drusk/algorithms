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
package rusk.david.algorithms.assignments;

import java.io.IOException;
import java.util.Map;

import rusk.david.algorithms.graphs.Graph;
import rusk.david.algorithms.graphs.GraphBuilder;
import rusk.david.algorithms.graphs.Node;
import rusk.david.algorithms.graphs.ShortestPathsCalculation;
import rusk.david.algorithms.graphs.UndirectedGraph;

public class Assignment5 {

	private static final String INPUT_FILE = "/home/drusk/Documents/Courses/online/algorithms1/assignments/a5/dijkstraData.txt";

	public static void main(String[] args) throws IOException {
		UndirectedGraph graph = new GraphBuilder()
				.buildWeightedUndirectedFromAdjacencyLists(INPUT_FILE);

		System.out.println("Created graph with " + graph.getNodeCount()
				+ " nodes and " + graph.getEdgeCount() + " edges.");

		Map<Node, Integer> shortestPathLengths = new ShortestPathsCalculation(
				graph, graph.getNode("1")).getPathLengths();

		String[] nodeIds = new String[] { "7", "37", "59", "82", "99", "115",
				"133", "165", "188", "197" };

		Node[] nodes = getNodes(nodeIds, graph);
		for (int i = 0; i < nodes.length; i++) {
			System.out.print(shortestPathLengths.get(nodes[i]));
			if (i < nodes.length - 1) {
				System.out.print(",");
			}
		}
	}

	private static Node[] getNodes(String[] nodeIds, Graph graph) {
		Node[] nodes = new Node[nodeIds.length];
		for (int i = 0; i < nodeIds.length; i++) {
			nodes[i] = graph.getNode(nodeIds[i]);
		}
		return nodes;
	}

}
