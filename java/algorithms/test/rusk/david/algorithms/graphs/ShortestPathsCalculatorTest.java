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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static rusk.david.algorithms.utils.CustomMatchers.equalsMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import rusk.david.algorithms.utils.TestUtils;

public class ShortestPathsCalculatorTest extends AbstractGraphTest {

	@Test
	public void getShortestPathLengthsBasicLectureExample() {
		Node[] nodes = createNodes(4);
		UndirectedGraph graph = new UndirectedGraph(nodes);
		graph.addEdge(nodes[0], nodes[1], 1);
		graph.addEdge(nodes[0], nodes[2], 4);
		graph.addEdge(nodes[1], nodes[2], 2);
		graph.addEdge(nodes[1], nodes[3], 6);
		graph.addEdge(nodes[2], nodes[3], 3);

		Map<Node, Integer> expectedLengths = new HashMap<Node, Integer>();
		expectedLengths.put(nodes[0], 0);
		expectedLengths.put(nodes[1], 1);
		expectedLengths.put(nodes[2], 3);
		expectedLengths.put(nodes[3], 6);

		assertThat(
				new ShortestPathsCalculation(graph, nodes[0]).getPathLengths(),
				equalsMap(expectedLengths));
	}

	@Test
	public void getShortestPathLengthsUnconnectedNode() {
		Node[] nodes = createNodes(5);
		UndirectedGraph graph = new UndirectedGraph(nodes);
		graph.addEdge(nodes[0], nodes[1], 1);
		graph.addEdge(nodes[0], nodes[2], 4);
		graph.addEdge(nodes[1], nodes[2], 2);
		graph.addEdge(nodes[1], nodes[3], 6);
		graph.addEdge(nodes[2], nodes[3], 3);

		Map<Node, Integer> expectedLengths = new HashMap<Node, Integer>();
		expectedLengths.put(nodes[0], 0);
		expectedLengths.put(nodes[1], 1);
		expectedLengths.put(nodes[2], 3);
		expectedLengths.put(nodes[3], 6);
		expectedLengths.put(nodes[4],
				ShortestPathsCalculation.INFINITE_DISTANCE);

		assertThat(
				new ShortestPathsCalculation(graph, nodes[0]).getPathLengths(),
				equalsMap(expectedLengths));
	}

	@Test
	public void acceptanceTest1() throws IOException {
		assertThat(getDistance("shortest_paths_1.txt", "1", "7"), equalTo(5));
	}

	@Test
	public void acceptanceTest2() throws IOException {
		assertThat(getDistance("shortest_paths_2.txt", "13", "5"), equalTo(26));
	}

	@Test
	public void acceptanceTest3() throws IOException {
		assertThat(getDistance("shortest_paths_3.txt", "28", "6"), equalTo(9));
	}

	private int getDistance(String fileName, String sourceNodeId,
			String targetNodeId) throws IOException {
		UndirectedGraph graph = new GraphBuilder()
				.buildWeightedUndirectedFromAdjacencyLists(TestUtils
						.getAbsolutePath(fileName));

		Map<Node, Integer> pathLengths = new ShortestPathsCalculation(graph,
				graph.getNode(sourceNodeId)).getPathLengths();

		return pathLengths.get(graph.getNode(targetNodeId));
	}
}
