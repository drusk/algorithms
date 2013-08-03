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

import rusk.david.algorithms.graphs.GraphBuilder;
import rusk.david.algorithms.graphs.KargerMinCutAlgorithm;
import rusk.david.algorithms.graphs.RandomEdgeSelector;
import rusk.david.algorithms.graphs.UndirectedGraph;

public class Assignment3 {

	private static final String INPUT_FILE = "/home/drusk/Documents/Courses/online/algorithms1/assignments/a3/kargerMinCut.txt";

	private static final int NUMBER_OF_ITERATIONS = 100;

	public static void main(String[] args) throws IOException {
		int minCut = Integer.MAX_VALUE;
		int currentCut;

		for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
			currentCut = getCutSize(INPUT_FILE);
			if (currentCut < minCut) {
				minCut = currentCut;
			}
		}

		System.out.println("Minimum cut size found in " + NUMBER_OF_ITERATIONS
				+ " iterations: " + minCut);
	}

	private static int getCutSize(String inputFile) throws IOException {
		UndirectedGraph graph = new GraphBuilder()
				.buildFromAdjacencyLists(inputFile);

		KargerMinCutAlgorithm kargerAlgorithm = new KargerMinCutAlgorithm(
				graph, new RandomEdgeSelector());

		return kargerAlgorithm.getMinCutSize();
	}

}
