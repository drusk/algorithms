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

	private abstract class LineParser {

		protected Map<String, Node> nodesByLabel = new HashMap<String, Node>();

		protected Node getOrMakeNode(String label, Graph graph) {
			if (nodesByLabel.containsKey(label)) {
				return nodesByLabel.get(label);
			}

			Node newNode = new Node(label);
			nodesByLabel.put(label, newNode);
			graph.addNode(newNode);

			return newNode;
		}

		public abstract void parseLine(String line, Graph graph);
	}

	private class UndirectedGraphLineParser extends LineParser {
		@Override
		public void parseLine(String line, Graph graph) {
			String[] nodeLabels = line.split("\\s+");

			assert nodeLabels.length >= 1;

			Node currentNode = getOrMakeNode(nodeLabels[0], graph);
			for (int i = 1; i < nodeLabels.length; i++) {
				Node connectedNode = getOrMakeNode(nodeLabels[i], graph);
				if (!graph.hasEdge(currentNode, connectedNode)) {
					/* Don't add undirected edges twice! */
					graph.addEdge(currentNode, connectedNode);
				}
			}
		}
	}

	private class DirectedGraphLineParser extends LineParser {
		@Override
		public void parseLine(String line, Graph graph) {
			String[] nodeLabels = line.split("\\s+");

			assert nodeLabels.length == 2;
			Node sourceNode = getOrMakeNode(nodeLabels[0], graph);
			Node targetNode = getOrMakeNode(nodeLabels[1], graph);

			graph.addEdge(sourceNode, targetNode);
		}
	}

	public UndirectedGraph buildUndirectedFromAdjacencyLists(String path)
			throws IOException {
		UndirectedGraph graph = new UndirectedGraph();
		buildFromAdjacencyLists(path, graph, new UndirectedGraphLineParser());
		return graph;
	}

	public DirectedGraph buildDirectedFromAdjacencyLists(String path)
			throws IOException {
		DirectedGraph graph = new DirectedGraph();
		buildFromAdjacencyLists(path, graph, new DirectedGraphLineParser());
		return graph;
	}

	private void buildFromAdjacencyLists(String path, Graph graph,
			LineParser lineParser) throws IOException {
		for (String line : IOUtils.readLines(path)) {
			lineParser.parseLine(line, graph);
		}
	}

}
