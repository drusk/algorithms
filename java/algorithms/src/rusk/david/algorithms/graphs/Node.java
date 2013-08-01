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

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: refactor and merge with UndirectedNode.
 * 
 * @author drusk
 * 
 */
public class Node {

	private String label;

	private List<Edge> incomingEdges = new ArrayList<Edge>();

	private List<Edge> outgoingEdges = new ArrayList<Edge>();

	public Node(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public String toString() {
		return label;
	}

	public void addIncomingEdge(Edge edge) {
		incomingEdges.add(edge);
	}

	public void addOutgoingEdge(Edge edge) {
		outgoingEdges.add(edge);
	}

	public List<Node> getAdjacentNodes() {
		List<Node> adjacentNodes = new ArrayList<Node>();
		for (Edge edge : outgoingEdges) {
			adjacentNodes.add(edge.getAdjacentNode(this));
		}
		return adjacentNodes;
	}

}
