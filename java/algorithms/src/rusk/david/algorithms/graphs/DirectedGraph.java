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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * TODO: remove duplication with {@link UndirectedGraph}.
 * 
 * @author drusk
 * 
 */
public class DirectedGraph {

	private Map<Node, List<Node>> nodeAdjacencies = new HashMap<Node, List<Node>>();

	private List<Edge> edges = new ArrayList<Edge>();

	public DirectedGraph(Node[] nodes) {
		for (Node node : nodes) {
			addNode(node);
		}
	}

	public void addNode(Node node) {
		nodeAdjacencies.put(node, new ArrayList<Node>());
	}

	public void addEdge(Node node1, Node node2) {
		nodeAdjacencies.get(node1).add(node2);
	}

	public Set<Node> getNodes() {
		return nodeAdjacencies.keySet();
	}

	public List<Node> getConnectedNodes(Node node) {
		return nodeAdjacencies.get(node);
	}

	public void reverse() {

	}

}
