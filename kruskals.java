// Java program for Kruskal's algorithm 

import java.util.ArrayList; 
import java.util.Comparator; 
import java.util.List; 

public class KruskalsMST { 

	// Defines edge structure 
	static class Edge { 
		int src, dest, weight; 

		public Edge(int src, int dest, int weight) 
		{ 
			this.src = src; 
			this.dest = dest; 
			this.weight = weight; 
		} 
	} 

	// Defines subset element structure 
	static class Subset { 
		int parent, rank; 

		public Subset(int parent, int rank) 
		{ 
			this.parent = parent; 
			this.rank = rank; 
		} 
	} 

	// Starting point of program execution 
	public static void main(String[] args) 
	{ 
		int V = 38; 
		List<Edge> graphEdges = new ArrayList<Edge>( 
			List.of(new Edge(1, 2, 78), new Edge(1, 9, 100), 
					new Edge(2, 4, 74), new Edge(2, 3, 65), 
					new Edge(3, 5, 104), new Edge(4, 7, 36),
					new Edge(4, 37, 29), new Edge(5, 6, 54),
					new Edge(5, 12, 31), new Edge(6, 37, 9),
					new Edge(6, 10, 30), new Edge(7, 8, 33),
					new Edge(7, 37, 36), new Edge(8, 37, 45),
					new Edge(8, 9, 45), new Edge(9, 23, 149),
					new Edge(10, 11, 8), new Edge(11, 14, 35),
					new Edge(12, 13, 45), new Edge(12, 16, 72),
					new Edge(13, 14, 25), new Edge(14, 15, 25),
					new Edge(14, 17, 35), new Edge(15, 16, 46),
					new Edge(16, 17, 42), new Edge(16, 19, 36),
					new Edge(17, 18, 8), new Edge(18, 20, 35),
					new Edge(19, 20, 54), new Edge(19, 26, 81),
					new Edge(20, 21, 10), new Edge(21, 22, 24),
					new Edge(21, 25, 60), new Edge(22, 23, 61),
					new Edge(22, 24, 61), new Edge(23, 36, 140),
					new Edge(24, 25, 22), new Edge(24, 27, 38),
					new Edge(25, 26, 75), new Edge(26, 30, 60),
					new Edge(27, 28, 25), new Edge(28, 29, 12),
					new Edge(29, 31, 33), new Edge(29, 32, 34),
					new Edge(30, 31, 61), new Edge(31, 34, 47),
					new Edge(32, 33, 31), new Edge(32, 34, 30),
					new Edge(33, 35, 26), new Edge(33, 36, 68),
					new Edge(34, 35, 15), new Edge(28,25, 36)
			)
		); 

		// Sort the edges in non-decreasing order 
		// (increasing with repetition allowed) 
		graphEdges.sort(new Comparator<Edge>() { 
			@Override public int compare(Edge o1, Edge o2) 
			{ 
				return o1.weight - o2.weight; 
			} 
		}); 


		kruskals(V, graphEdges); 
	} 

	// Function to find the MST 
	private static void kruskals(int V, List<Edge> edges) 
	{ 
		int j = 0; 
		int noOfEdges = 0; 

		// Allocate memory for creating V subsets 
		Subset subsets[] = new Subset[V]; 

		// Allocate memory for results 
		Edge results[] = new Edge[V]; 

		// Create V subsets with single elements 
		for (int i = 0; i < V; i++) { 
			subsets[i] = new Subset(i, 0); 
		} 

		// Number of edges to be taken is equal to V-2 (since first node starts from 1) 
		while (noOfEdges < V - 2) { 

			// Pick the smallest edge. And increment 
			// the index for next iteration 
			Edge nextEdge = edges.get(j);  //last index is 50 but goes to 51
			int x = findRoot(subsets, nextEdge.src); 
			int y = findRoot(subsets, nextEdge.dest); 

			// If including this edge doesn't cause cycle, 
			// include it in result and increment the index 
			// of result for next edge 
			if (x != y) { 
				results[noOfEdges] = nextEdge; 
				union(subsets, x, y); 
				noOfEdges++; 
			} 

			j++; 
		} 

		// Print the contents of result[] to display the 
		// built MST 
		System.out.println( 
			"Following are the edges of the constructed MST:"); 
		int minCost = 0; 
		for (int i = 0; i < noOfEdges; i++) { 
			System.out.println(results[i].src + " -- "
							+ results[i].dest + " == "
							+ results[i].weight); 
			minCost += results[i].weight; 
		} 
		System.out.println("Total cost of MST: " + minCost); 
	} 

	// Function to unite two disjoint sets 
	private static void union(Subset[] subsets, int x, 
							int y) 
	{ 
		int rootX = findRoot(subsets, x); 
		int rootY = findRoot(subsets, y); 

		if (subsets[rootY].rank < subsets[rootX].rank) { 
			subsets[rootY].parent = rootX; 
		} 
		else if (subsets[rootX].rank 
				< subsets[rootY].rank) { 
			subsets[rootX].parent = rootY; 
		} 
		else { 
			subsets[rootY].parent = rootX; 
			subsets[rootX].rank++; 
		} 
	} 

	// Function to find parent of a set 
	private static int findRoot(Subset[] subsets, int i) 
	{ 
		if (subsets[i].parent == i)   
			return subsets[i].parent; 

		subsets[i].parent 
			= findRoot(subsets, subsets[i].parent); 
		return subsets[i].parent; 
	} 
} 