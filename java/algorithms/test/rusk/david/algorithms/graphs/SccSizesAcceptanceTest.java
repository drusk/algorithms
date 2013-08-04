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
import static org.junit.matchers.JUnitMatchers.hasItems;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import rusk.david.algorithms.utils.TestUtils;

public class SccSizesAcceptanceTest {

	private List<Integer> getSccSizes(String path) throws IOException {
		DirectedGraph graph = new GraphBuilder()
				.buildDirectedFromAdjacencyLists(TestUtils
						.getAbsolutePath(path));

		SccCalculation sccCalculation = new SccCalculation(graph);
		List<Scc> sccs = sccCalculation.getSccs();

		List<Integer> sizes = new ArrayList<Integer>();
		for (Scc scc : sccs) {
			sizes.add(scc.size());
		}

		return sizes;
	}

	@Test
	public void acceptance1() throws IOException {
		assertThat(getSccSizes("scc_size_acceptance_test_1.txt"),
				hasItems(3, 3, 1, 1));
	}

	@Test
	public void acceptance2() throws IOException {
		assertThat(getSccSizes("scc_size_acceptance_test_2.txt"),
				hasItems(7, 1));
	}

	@Test
	public void acceptance3() throws IOException {
		assertThat(getSccSizes("scc_size_acceptance_test_3.txt"),
				hasItems(3, 2, 2, 2, 1, 1));
	}

	@Test
	public void acceptance4() throws IOException {
		assertThat(getSccSizes("scc_size_acceptance_test_4.txt"),
				hasItems(6, 3, 2, 1));
	}

	@Test
	public void acceptance5() throws IOException {
		assertThat(getSccSizes("scc_size_acceptance_test_5.txt"), hasItems(3));
	}
}
