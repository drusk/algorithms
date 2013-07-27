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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.hasItems;

import org.junit.Test;

public class GraphTest extends AbstractGraphTest {

	@Test
	public void basicGraphProperties() {
		Node[] nodes = createNodes(3);

		Graph graph = new Graph(nodes);
		graph.addEdge(nodes[0], nodes[1]);
		graph.addEdge(nodes[1], nodes[2]);

		assertEquals(3, graph.getNodeCount());
		assertEquals(2, graph.getEdgeCount());

		assertTrue(nodes[0].isConnectedTo(nodes[1]));
		assertTrue(nodes[1].isConnectedTo(nodes[0]));
		assertTrue(nodes[1].isConnectedTo(nodes[2]));
		assertTrue(nodes[2].isConnectedTo(nodes[1]));
		assertFalse(nodes[0].isConnectedTo(nodes[2]));
		assertFalse(nodes[2].isConnectedTo(nodes[0]));
	}

	@Test
	public void mergeNodesWithOneEdge() {
		Node[] nodes = createNodes(3);

		Graph graph = new Graph(nodes);
		graph.addEdge(nodes[0], nodes[1]);
		graph.addEdge(nodes[1], nodes[2]);

		Node mergedNode = graph.mergeNodes(nodes[1], nodes[2]);

		assertEquals(2, graph.getNodeCount());
		assertEquals(1, graph.getEdgeCount());

		assertThat(graph.getNodes(), hasItems(mergedNode, nodes[0]));
		assertTrue(mergedNode.isConnectedTo(nodes[0]));
	}

	@Test
	public void mergeNodesMultipleEdgesBetweenRemaining() {
		Node[] nodes = createNodes(3);

		Graph graph = new Graph(nodes);
		graph.addEdge(nodes[0], nodes[1]);
		graph.addEdge(nodes[1], nodes[2]);
		graph.addEdge(nodes[2], nodes[0]);

		Node mergedNode = graph.mergeNodes(nodes[1], nodes[2]);

		assertEquals(2, graph.getNodeCount());
		assertEquals(2, graph.getEdgeCount());

		assertThat(graph.getNodes(), hasItems(mergedNode, nodes[0]));
		assertTrue(mergedNode.isConnectedTo(nodes[0]));
	}

	@Test
	public void mergeNodesHighlyCrossConnected() {
		Node[] nodes = createNodes(4);

		Graph graph = new Graph(nodes);
		graph.addEdge(nodes[0], nodes[1]);
		graph.addEdge(nodes[1], nodes[2]);
		graph.addEdge(nodes[2], nodes[3]);
		graph.addEdge(nodes[3], nodes[0]);
		graph.addEdge(nodes[0], nodes[2]);
		graph.addEdge(nodes[1], nodes[3]);

		Node mergedNode = graph.mergeNodes(nodes[1], nodes[3]);

		assertEquals(3, graph.getNodeCount());
		assertEquals(5, graph.getEdgeCount());

		assertThat(graph.getNodes(), hasItems(mergedNode, nodes[0], nodes[2]));
		assertTrue(mergedNode.isConnectedTo(nodes[0]));
		assertTrue(mergedNode.isConnectedTo(nodes[2]));
		assertTrue(nodes[2].isConnectedTo(nodes[0]));
	}

	@Test
	public void mergeNodesWithMultipleEdgesToTheSameNode() {
		Node[] nodes = createNodes(3);

		Graph graph = new Graph(nodes);
		graph.addEdge(nodes[0], nodes[1]);
		graph.addEdge(nodes[0], nodes[1]);
		graph.addEdge(nodes[1], nodes[2]);
		graph.addEdge(nodes[1], nodes[2]);

		Node mergedNode = graph.mergeNodes(nodes[0], nodes[1]);

		assertEquals(2, graph.getNodeCount());
		assertEquals(2, graph.getEdgeCount());

		assertThat(graph.getNodes(), hasItems(mergedNode, nodes[2]));
		assertTrue(mergedNode.isConnectedTo(nodes[2]));
		assertTrue(nodes[2].isConnectedTo(mergedNode));
	}

}
