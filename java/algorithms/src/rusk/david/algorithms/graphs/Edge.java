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

public class Edge {

	private Node sourceNode;

	private Node targetNode;

	private int weight;

	private boolean directed;

	public Edge(Node sourceNode, Node targetNode, boolean directed) {
		this(sourceNode, targetNode, directed, 1);
	}

	public Edge(Node sourceNode, Node targetNode, boolean directed, int weight) {
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
		this.directed = directed;
		this.weight = weight;
	}

	public Node getTargetNode() {
		return targetNode;
	}

	public Node getSourceNode() {
		return sourceNode;
	}

	public int getWeight() {
		return weight;
	}

	public boolean isDirected() {
		return directed;
	}

	public Node getAdjacentNode(Node node) {
		if (node.equals(sourceNode)) {
			return targetNode;
		} else if (node.equals(targetNode)) {
			return sourceNode;
		} else {
			throw new RuntimeException("Node (" + node.toString()
					+ ") is not part of edge (" + toString() + ")");
		}
	}

	public boolean isConnectedTo(Node node) {
		return node.equals(sourceNode) || node.equals(targetNode);
	}

	@Override
	public String toString() {
		return sourceNode.toString() + " -> " + targetNode.toString();
	}

}
