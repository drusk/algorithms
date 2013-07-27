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

import java.util.HashSet;
import java.util.Set;

public class Graph {

	private Set<Node> nodes = new HashSet<Node>();

	public Graph(Node[] nodes) {
		for (Node node : nodes) {
			addNode(node);
		}
	}

	public void addNode(Node node) {
		nodes.add(node);
	}

	public void addEdge(Node node1, Node node2) {
		node1.addConnectedNode(node2);
		node2.addConnectedNode(node1);
	}

	public int getNodeCount() {
		return nodes.size();
	}

	public int getEdgeCount() {
		int endCount = 0;
		for (Node node : nodes) {
			endCount += node.getEdgeCount();
		}
		return endCount / 2;
	}

	public Set<Node> getNodes() {
		return nodes;
	}

	public Node mergeNodes(Node node1, Node node2) {
		nodes.remove(node1);
		nodes.remove(node2);

		Node superNode = new Node(node1.getLabel() + "-" + node2.getLabel());

		superNode.addConnectedNodes(node1.getConnectedNodes());
		superNode.addConnectedNodes(node2.getConnectedNodes());

		superNode.removeAllConnectionsTo(node1);
		superNode.removeAllConnectionsTo(node2);

		replaceNodeInEdges(node1, superNode);
		replaceNodeInEdges(node2, superNode);

		addNode(superNode);

		return superNode;
	}

	private void replaceNodeInEdges(Node originalNode, Node newNode) {
		for (Node connectedNode : originalNode.getConnectedNodes()) {
			int connections = connectedNode
					.removeAllConnectionsTo(originalNode);
			for (int i = 0; i < connections; i++) {
				connectedNode.addConnectedNode(newNode);
			}
		}
	}
}
