package com.ts.internal.services;

import java.util.ArrayList;
import java.util.List;

public class NodeOperations {

	static final int N = 1000001;

	static int[] start = new int[100001];
	static int[] end = new int[100001];
	static int[] visited = new int[100001];
	static ArrayList<Integer> adj[] = new ArrayList[N];
	static ArrayList<Integer> dfsOrder = new ArrayList<>();

	static void prepareRequiredTreeStructure(int node1, int node2) {
		// keep track of node visited
		visited[node1] = 1;
		node2++;
		start[node1] = node2;
		dfsOrder.add(node1);

		for (Integer i : adj[node1]) {
			if (visited[i] != 1) {
				prepareRequiredTreeStructure(i, node2);
			}
		}
		end[node1] = node2;
	}

	List<String> getAllDescendantsAndPersist(int nodeId) {
		
		List<String> descendants = new ArrayList<>();
        if(start[nodeId]!=end[nodeId]) 
        { 
            for(int j=start[nodeId]+1;j<=end[nodeId];j++) 
            { 
            	descendants.add(dfsOrder.get(j-1).toString());
            }  
        }
        
        return descendants;
	}

	public void writeTree() {

		/*
		 * the below prepared tree will have all the data we need to test our APIs. 1 /
		 * | \ / | \ 2 10 5 / \ / \ 3 4 6 7 / \ 8 9
		 */

		for (int i = 0; i < N; i++)
			adj[i] = new ArrayList<>();

		// insertion of nodes in graph
		addEdge(1, 2);
		addEdge(1, 5);
		addEdge(1, 10);
		addEdge(2, 3);
		addEdge(2, 4);
		addEdge(5, 6);
		addEdge(5, 7);
		addEdge(6, 8);
		addEdge(6, 9);

		prepareRequiredTreeStructure(1, 0);

	}

	// function to add edges in graph
	static void addEdge(int a, int b) {
		adj[a].add(b);
		adj[b].add(a);
	}

}
