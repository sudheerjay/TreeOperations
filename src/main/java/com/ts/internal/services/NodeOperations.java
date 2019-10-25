package com.ts.internal.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ts.internal.dto.Node;
import com.ts.internal.redis.RedisServiceImpl;

@Component
public class NodeOperations {
	
	@Autowired
	RedisServiceImpl redisService;

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
            	descendants.add("node"+dfsOrder.get(j-1).toString());
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

		Node root = new Node(1,null,null,0);
		
		Node node2 = new Node(2,root,root,1);
		Node node5 = new Node(5,root,root,1);
		Node node10 = new Node(10,root,root,1);
		Node node3 = new Node(3,root,node2,2);
		Node node4 = new Node(4,root,node2,2);
		Node node6 = new Node(6,root,node5,2);
		Node node7 = new Node(7,root,node5,2);
		Node node8 = new Node(8,root,node6,3);
		Node node9 = new Node(9,root,node6,3);
		// insertion of nodes in graph
		addEdgeNode(root, node2);
		addEdgeNode(root, node5);
		addEdgeNode(root, node10);
		addEdgeNode(node2, node3);
		addEdgeNode(node2, node4);
		addEdgeNode(node5, node6);
		addEdgeNode(node5, node7);
		addEdgeNode(node6, node8);
		addEdgeNode(node6, node9);

		prepareRequiredTreeStructure(1,0);

	}

	// function to add edges in graph
	void addEdgeNode(Node a, Node b) {
		
		//put root in 
		if(null == a.getRoot()) {
			redisService.putNode(a);
		}

		redisService.putNode(b);
		
		adj[a.getId()].add(b.getId());
	}

}
