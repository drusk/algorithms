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
import java.util.Arrays;
import java.util.List;

import rusk.david.algorithms.graphs.DirectedGraph;
import rusk.david.algorithms.graphs.GraphBuilder;
import rusk.david.algorithms.graphs.Scc;
import rusk.david.algorithms.graphs.SccCalculation;
import rusk.david.algorithms.utils.ArrayUtils;

public class Assignment4 {

	private static final String INPUT_FILE = "/home/drusk/algorithms/SCC.txt";

	public static void main(String[] args) throws IOException {
		DirectedGraph graph = new GraphBuilder()
				.buildDirectedFromAdjacencyLists(INPUT_FILE);

		System.out.println("Built graph from adjacency list.");
		System.out.println("Graph has " + graph.getNodeCount() + " nodes and "
				+ graph.getEdgeCount() + " edges.");

		SccCalculation sccCalculation = new SccCalculation(graph);

		System.out.println("Finished SCC calculation");

		List<Scc> sccs = sccCalculation.getSccs();
		System.out.println("Total SCCS: " + sccs.size());

		int[] sizes = new int[sccs.size()];
		for (int i = 0; i < sccs.size(); i++) {
			sizes[i] = sccs.get(i).size();
		}

		/* Sorted from smallest to largest. */
		Arrays.sort(sizes);

		int[] descendingSizes = ArrayUtils.reverse(sizes);

		printLargestSccs(descendingSizes, 5);
	}

	private static void printLargestSccs(int[] descendingSizes, int n) {
		for (int i = 0; i < n; i++) {
			if (i >= descendingSizes.length) {
				System.out.print(0);
			} else {
				System.out.print(descendingSizes[i]);
			}

			if (i < n - 1) {
				System.out.print(",");
			}
		}
	}
}
