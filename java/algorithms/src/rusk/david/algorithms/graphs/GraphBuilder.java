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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import rusk.david.algorithms.utils.IOUtils;

public class GraphBuilder {

	private Map<String, Node> nodesByLabel;

	public UndirectedGraph buildFromAdjacencyLists(String path) throws IOException {
		nodesByLabel = new HashMap<String, Node>();

		for (String line : IOUtils.readLines(path)) {
			parseNodeAndConnections(line);
		}

		return new UndirectedGraph(nodesByLabel.values());
	}

	private void parseNodeAndConnections(String line) {
		String[] nodeLabels = line.split("\\s+");

		assert nodeLabels.length >= 1;

		Node currentNode = getOrMakeNode(nodeLabels[0]);

		for (int i = 1; i < nodeLabels.length; i++) {
			Node connectedNode = getOrMakeNode(nodeLabels[i]);
			currentNode.addConnectedNode(connectedNode);
		}
	}

	private Node getOrMakeNode(String label) {
		if (nodesByLabel.containsKey(label)) {
			return nodesByLabel.get(label);
		}

		Node newNode = new Node(label);
		nodesByLabel.put(label, newNode);

		return newNode;
	}

}
