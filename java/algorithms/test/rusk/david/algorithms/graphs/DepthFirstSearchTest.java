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

import static org.hamcrest.CoreMatchers.anyOf;
import static org.junit.Assert.assertThat;
import static rusk.david.algorithms.utils.CollectionMatchers.containsExactly;

import org.junit.Test;

public class DepthFirstSearchTest extends AbstractGraphTest {

	@Test
	public void threeLinearNodes() {
		Node[] nodes = createNodes(3);

		DirectedGraph graph = new DirectedGraph(nodes);
		graph.addEdge(nodes[0], nodes[1]);
		graph.addEdge(nodes[1], nodes[2]);

		DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graph,
				nodes[0]);
		assertThat(depthFirstSearch.getTraversalOrder(),
				containsExactly(nodes[0], nodes[1], nodes[2]));
	}

	@Test
	public void threeNodeCycle() {
		Node[] nodes = createNodes(3);

		DirectedGraph graph = new DirectedGraph(nodes);
		graph.addEdge(nodes[0], nodes[1]);
		graph.addEdge(nodes[1], nodes[2]);
		graph.addEdge(nodes[2], nodes[0]);

		DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graph,
				nodes[0]);
		assertThat(depthFirstSearch.getTraversalOrder(),
				containsExactly(nodes[0], nodes[1], nodes[2]));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void fourNodesWithBranch() {
		Node[] nodes = createNodes(4);

		DirectedGraph graph = new DirectedGraph(nodes);
		graph.addEdge(nodes[0], nodes[1]);
		graph.addEdge(nodes[0], nodes[2]);
		graph.addEdge(nodes[1], nodes[3]);
		graph.addEdge(nodes[2], nodes[3]);

		DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graph,
				nodes[0]);
		assertThat(
				depthFirstSearch.getTraversalOrder(),
				anyOf(containsExactly(nodes[0], nodes[1], nodes[3], nodes[2]),
						containsExactly(nodes[0], nodes[2], nodes[3], nodes[1])));
	}

	@Test
	public void finishTimesThreeLinearNodes() {
		Node[] nodes = createNodes(3);

		DirectedGraph graph = new DirectedGraph(nodes);
		graph.addEdge(nodes[0], nodes[1]);
		graph.addEdge(nodes[1], nodes[2]);

		DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graph,
				nodes[0]);
		assertThat(depthFirstSearch.getFinishOrder(),
				containsExactly(nodes[2], nodes[1], nodes[0]));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void finishTimesFourNodesWithBranch() {
		Node[] nodes = createNodes(4);

		DirectedGraph graph = new DirectedGraph(nodes);
		graph.addEdge(nodes[0], nodes[1]);
		graph.addEdge(nodes[0], nodes[2]);
		graph.addEdge(nodes[1], nodes[3]);
		graph.addEdge(nodes[2], nodes[3]);

		DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graph,
				nodes[0]);
		assertThat(
				depthFirstSearch.getFinishOrder(),
				anyOf(containsExactly(nodes[3], nodes[1], nodes[2], nodes[0]),
						containsExactly(nodes[3], nodes[2], nodes[1], nodes[0])));
	}

}
