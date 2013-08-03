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
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class GraphBuilderTest {

	private static final String testDir = "test/rusk/david/algorithms/graphs";

	private String getProjectBasePath() {
		String testAbsolutePath = new File("test.txt").getAbsolutePath();
		return testAbsolutePath.substring(0,
				testAbsolutePath.lastIndexOf(File.separator));
	}

	private String getAbsolutePath(String relativePath) {
		return getProjectBasePath() + File.separator + testDir + File.separator
				+ relativePath;
	}

	@Test
	public void buildUndirectedGraphFromAdjacencyList() throws IOException {
		Graph graph = new GraphBuilder()
				.buildUndirectedFromAdjacencyLists(getAbsolutePath("undirected_graph_adjacency_list.txt"));

		assertEquals(4, graph.getNodeCount());
		assertEquals(6, graph.getEdgeCount());
	}

	@Test
	public void buildDirectedGraphFromAdjacencyList() throws IOException {
		Graph graph = new GraphBuilder()
				.buildUndirectedFromAdjacencyLists(getAbsolutePath("directed_graph_adjacency_list.txt"));

		assertEquals(4, graph.getNodeCount());
		assertEquals(5, graph.getEdgeCount());

		assertTrue(graph.hasEdge("1", "2"));
		assertTrue(graph.hasEdge("1", "3"));
		assertTrue(graph.hasEdge("2", "3"));
		assertTrue(graph.hasEdge("2", "4"));
		assertTrue(graph.hasEdge("3", "4"));
	}

}
