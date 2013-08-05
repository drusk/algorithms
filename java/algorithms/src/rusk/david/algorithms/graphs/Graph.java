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

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Graph {

	protected Set<Node> nodes = new HashSet<Node>();

	protected Set<Edge> edges = new HashSet<Edge>();

	public Graph() {
		this(new Node[] {});
	}

	public Graph(Node[] nodes) {
		initializeExtraDataStructures();

		for (Node node : nodes) {
			addNode(node);
		}
	}

	public Graph(Collection<Node> nodes) {
		this(nodes.toArray(new Node[nodes.size()]));
	}

	protected abstract void initializeExtraDataStructures();

	public void addNode(Node node) {
		nodes.add(node);
	}

	/* TODO: method for adding edges just using node ids */
	public abstract void addEdge(Node sourceNode, Node targetNode);

	public abstract void addEdge(Node sourceNode, Node targetNode, int weight);

	public boolean hasEdge(Node sourceNode, Node targetNode) {
		return getAdjacentNodes(sourceNode).contains(targetNode);
	}

	public boolean hasEdge(String sourceNodeId, String targetNodeId) {
		Node sourceNode = null;
		Node targetNode = null;

		for (Node node : getNodes()) {
			if (node.getId().equals(sourceNodeId)) {
				sourceNode = node;
			}
			if (node.getId().equals(targetNodeId)) {
				targetNode = node;
			}
		}

		if (sourceNode == null || targetNode == null) {
			return false;
		}

		return hasEdge(sourceNode, targetNode);
	}

	public Set<Node> getNodes() {
		return nodes;
	}

	public Set<Edge> getEdges() {
		return edges;
	}

	public int getNodeCount() {
		return nodes.size();
	}

	public int getEdgeCount() {
		return edges.size();
	}

	public abstract List<Node> getAdjacentNodes(Node node);

}
