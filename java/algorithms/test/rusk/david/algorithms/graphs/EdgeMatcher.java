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

import org.hamcrest.Description;
import org.junit.internal.matchers.TypeSafeMatcher;

public class EdgeMatcher extends TypeSafeMatcher<Edge> {

	private String sourceNodeId;

	private String targetNodeId;

	private boolean directed;

	private int weight;

	public EdgeMatcher(String sourceNodeId, String targetNodeId,
			boolean directed, int weight) {
		this.sourceNodeId = sourceNodeId;
		this.targetNodeId = targetNodeId;
		this.directed = directed;
		this.weight = weight;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("edge from ").appendValue(sourceNodeId)
				.appendText(" to ").appendValue(targetNodeId)
				.appendText(" (directed=").appendValue(directed)
				.appendText(") ").appendText(" with weight ")
				.appendValue(weight);
	}

	@Override
	public boolean matchesSafely(Edge actualEdge) {
		return actualEdge.getSourceNode().getId().equals(sourceNodeId)
				&& actualEdge.getTargetNode().getId().equals(targetNodeId)
				&& actualEdge.isDirected() == directed
				&& actualEdge.getWeight() == weight;
	}

}
