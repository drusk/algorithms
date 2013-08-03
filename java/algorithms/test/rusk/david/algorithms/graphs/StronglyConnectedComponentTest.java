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
import static org.junit.Assert.fail;
import static org.junit.matchers.JUnitMatchers.hasItems;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StronglyConnectedComponentTest extends AbstractGraphTest {

	@Test
	public void threeTriangularSCCs() {
		// This is the example given in the lecture notes
		Node[] nodes = createNodes(9);

		DirectedGraph graph = new DirectedGraph(nodes);
		graph.addEdge(nodes[0], nodes[1]);
		graph.addEdge(nodes[1], nodes[2]);
		graph.addEdge(nodes[2], nodes[0]);
		graph.addEdge(nodes[3], nodes[0]);
		graph.addEdge(nodes[3], nodes[4]);
		graph.addEdge(nodes[4], nodes[5]);
		graph.addEdge(nodes[5], nodes[3]);
		graph.addEdge(nodes[6], nodes[5]);
		graph.addEdge(nodes[6], nodes[7]);
		graph.addEdge(nodes[7], nodes[8]);
		graph.addEdge(nodes[8], nodes[6]);

		List<Scc> sccs = new SccCalculation(graph).getSccs();

		/*
		 * Check there are the right number of components and they each have the
		 * right size.
		 */
		assertEquals(3, sccs.size());

		Scc firstComponent = sccs.get(0);
		assertEquals(3, firstComponent.size());

		Scc secondComponent = sccs.get(1);
		assertEquals(3, secondComponent.size());

		Scc thirdComponent = sccs.get(2);
		assertEquals(3, thirdComponent.size());

		/*
		 * Check that the right nodes are grouped together. This is a bit tricky
		 * because the order of the SCCs is random, and the order of nodes
		 * within the SCC is also not deterministic.
		 */
		List<List<String>> nodeIdsByComponent = new ArrayList<List<String>>();
		nodeIdsByComponent.add(getNodeIds(firstComponent));
		nodeIdsByComponent.add(getNodeIds(secondComponent));
		nodeIdsByComponent.add(getNodeIds(thirdComponent));

		boolean seenFirstComponent = false;
		boolean seenSecondComponent = false;
		boolean seenThirdComponent = false;
		for (List<String> nodeIds : nodeIdsByComponent) {
			if (nodeIds.contains("0")) {
				assertThat(nodeIds, hasItems("0", "1", "2"));
				seenFirstComponent = true;
			} else if (nodeIds.contains("3")) {
				assertThat(nodeIds, hasItems("3", "4", "5"));
				seenSecondComponent = true;
			} else if (nodeIds.contains("6")) {
				assertThat(nodeIds, hasItems("6", "7", "8"));
				seenThirdComponent = true;
			} else {
				fail("SCC has incorrect nodes");
			}
		}

		assertTrue(seenFirstComponent);
		assertTrue(seenSecondComponent);
		assertTrue(seenThirdComponent);
	}

	private List<String> getNodeIds(Scc scc) {
		List<String> nodeIds = new ArrayList<String>();
		for (Node node : scc.getNodes()) {
			nodeIds.add(node.getId());
		}
		return nodeIds;
	}
}
