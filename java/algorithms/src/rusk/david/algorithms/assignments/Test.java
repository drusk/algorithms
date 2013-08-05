package rusk.david.algorithms.assignments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import rusk.david.algorithms.graphs.DirectedGraph;
import rusk.david.algorithms.graphs.GraphBuilder;
import rusk.david.algorithms.graphs.Node;
import rusk.david.algorithms.graphs.Scc;
import rusk.david.algorithms.graphs.SccCalculation;

public class Test {

	private static final String INPUT_FILE = "/home/drusk/algorithms/java/algorithms/test/rusk/david/algorithms/graphs/scc_size_acceptance_test_4.txt";

	public static void main(String[] args) throws IOException {
		Set<String> goodStartingNodes = new HashSet<String>();
		Set<String> badStartingNodes = new HashSet<String>();
		Set<String> badFinishingOrders = new HashSet<String>();
		
		for (int i = 0; i < 10000; i++) {
			doTest(goodStartingNodes, badStartingNodes, badFinishingOrders);
		}
		
		System.out.println("Bad starting nodes:");
		System.out.println(badStartingNodes);
		
		System.out.println("Good starting nodes:");
		System.out.println(goodStartingNodes);
		
		System.out.println("Bad finishing orders: (" + badFinishingOrders.size() + ")");
		for (String finishingOrder : badFinishingOrders) {
			System.out.println(finishingOrder);
		}
		System.out.println("("  + badFinishingOrders.size() + ")");
	}

	private static void doTest(Set<String> goodStartingNodes, Set<String> badStartingNodes, Set<String> badFinishingOrders) throws IOException {
		DirectedGraph graph = new GraphBuilder()
				.buildDirectedFromAdjacencyLists(INPUT_FILE);

		SccCalculation sccCalculation = new SccCalculation(graph);
		List<Scc> sccs = sccCalculation.getSccs();

		List<Integer> sizes = new ArrayList<Integer>();
		for (Scc scc : sccs) {
//			System.out.println(scc.toString());
			sizes.add(scc.size());
		}

//		if (sizes.size() != 4
//				|| !(sizes.contains(6) && sizes.contains(3)
//						&& sizes.contains(2) && sizes.contains(1))) {
//			badStartingNodes.add(sccCalculation.getFirstNode().getId());
//			badFinishingOrders.add(sccCalculation.getFinishingOrder());
//		} else {
//			goodStartingNodes.add(sccCalculation.getFirstNode().getId());
//		}
		
	}

}
