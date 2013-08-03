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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DirectedGraphTest extends AbstractGraphTest {

	@Test
	public void reverseLinearPath() {
		Node nodes[] = createNodes(3);

		DirectedGraph graph = new DirectedGraph(nodes);
		graph.addEdge(nodes[0], nodes[1]);
		graph.addEdge(nodes[1], nodes[2]);

		assertTrue(graph.hasEdge(nodes[0], nodes[1]));
		assertTrue(graph.hasEdge(nodes[1], nodes[2]));

		assertFalse(graph.hasEdge(nodes[1], nodes[0]));
		assertFalse(graph.hasEdge(nodes[2], nodes[1]));

		DirectedGraph reversedGraph = graph.reversed();

		assertTrue(reversedGraph.hasEdge(nodes[1], nodes[0]));
		assertTrue(reversedGraph.hasEdge(nodes[2], nodes[1]));

		assertFalse(reversedGraph.hasEdge(nodes[0], nodes[1]));
		assertFalse(reversedGraph.hasEdge(nodes[1], nodes[2]));

		// Make sure original graph is unchanged
		assertTrue(graph.hasEdge(nodes[0], nodes[1]));
		assertTrue(graph.hasEdge(nodes[1], nodes[2]));

		assertFalse(graph.hasEdge(nodes[1], nodes[0]));
		assertFalse(graph.hasEdge(nodes[2], nodes[1]));
	}

}
