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
 * TODO: refactor this for use with either directed or undirected graphs.
 * 
 * @author drusk
 * 
 */
public class UndirectedNode {

	private String label;

	private List<UndirectedNode> connectedNodes = new ArrayList<UndirectedNode>();

	public UndirectedNode(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	/**
	 * Adds a connection from this node to another. Note: self edges are not
	 * allowed, so adding a connection to this node will be skipped. However,
	 * the same nodes can have multiple edges between each other.
	 * 
	 * @param otherNode
	 *            The node this node is connected to.
	 */
	public void addConnectedNode(UndirectedNode otherNode) {
		if (otherNode.equals(this)) {
			return;
		}

		connectedNodes.add(otherNode);
	}

	public void addConnectedNodes(List<UndirectedNode> otherNodes) {
		for (UndirectedNode node : otherNodes) {
			addConnectedNode(node);
		}
	}

	/**
	 * Removes this node's connections to another node.
	 * 
	 * @param otherNode
	 *            The node to disconnect from.
	 * @return the number of connections removed
	 */
	public int removeAllConnectionsTo(UndirectedNode otherNode) {
		int removed = 0;
		while (connectedNodes.contains(otherNode)) {
			connectedNodes.remove(otherNode);
			removed++;
		}
		return removed;
	}

	public List<UndirectedNode> getConnectedNodes() {
		return connectedNodes;
	}

	public int getEdgeCount() {
		return connectedNodes.size();
	}

	public boolean isConnectedTo(UndirectedNode otherNode) {
		return connectedNodes.contains(otherNode);
	}

	public String toString() {
		return label;
	}

}
