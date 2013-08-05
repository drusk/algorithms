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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.hasItems;
import static rusk.david.algorithms.graphs.GraphMatchers.edge;

import java.io.IOException;

import org.junit.Test;

import rusk.david.algorithms.utils.TestUtils;

public class GraphBuilderTest {

	@Test
	public void buildUndirectedGraphFromAdjacencyList() throws IOException {
		Graph graph = new GraphBuilder()
				.buildUndirectedFromAdjacencyLists(TestUtils
						.getAbsolutePath("undirected_graph_adjacency_list.txt"));

		assertEquals(4, graph.getNodeCount());
		assertEquals(6, graph.getEdgeCount());
	}

	@Test
	public void buildDirectedGraphFromAdjacencyList() throws IOException {
		Graph graph = new GraphBuilder()
				.buildUndirectedFromAdjacencyLists(TestUtils
						.getAbsolutePath("directed_graph_adjacency_list.txt"));

		assertEquals(4, graph.getNodeCount());
		assertEquals(5, graph.getEdgeCount());

		assertTrue(graph.hasEdge("1", "2"));
		assertTrue(graph.hasEdge("1", "3"));
		assertTrue(graph.hasEdge("2", "3"));
		assertTrue(graph.hasEdge("2", "4"));
		assertTrue(graph.hasEdge("3", "4"));
	}

	@Test
	public void buildDirectedGraphFromAdjacencyListOneEdgePerLine()
			throws IOException {
		Graph graph = new GraphBuilder()
				.buildUndirectedFromAdjacencyLists(TestUtils
						.getAbsolutePath("directed_graph_adjacency_list2.txt"));

		assertEquals(4, graph.getNodeCount());
		assertEquals(5, graph.getEdgeCount());

		assertTrue(graph.hasEdge("1", "2"));
		assertTrue(graph.hasEdge("1", "3"));
		assertTrue(graph.hasEdge("2", "3"));
		assertTrue(graph.hasEdge("2", "4"));
		assertTrue(graph.hasEdge("3", "4"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void buildWeightedUndirectedGraphFromAdjacencyList()
			throws IOException {
		Graph graph = new GraphBuilder()
				.buildWeightedUndirectedFromAdjacencyLists(TestUtils
						.getAbsolutePath("weighted_undirected_adj_list.txt"));

		assertEquals(4, graph.getNodeCount());
		assertEquals(3, graph.getEdgeCount());

		assertThat(
				graph.getEdges(),
				hasItems(edge("0", "1", false, 3), edge("0", "2", false, 1),
						edge("2", "3", false, 5)));
	}
}
