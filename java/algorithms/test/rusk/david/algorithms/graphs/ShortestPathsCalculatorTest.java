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

import static org.junit.Assert.assertThat;
import static rusk.david.algorithms.utils.CustomMatchers.equalsMap;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

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
}
