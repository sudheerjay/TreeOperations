package com.ts.internal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts.internal.dto.Node;
import com.ts.internal.redis.RedisServiceImpl;

@Service
public class TreeOperationsService {

	@Autowired
	private RedisServiceImpl redisService;

	@Autowired
	private NodeOperations nodeOperations;

	/*
	 * This method goes through the entire tree from the given nodeId. it used DFS
	 * to print the required descendants.
	 */
	public List<String> getDescendantNodes(String id) {
		List<String> descendants = nodeOperations.getAllDescendantsAndPersist(Integer.parseInt(id));
		redisService.cacheDescendentNodes(id, descendants);
		return descendants;
	}

	/*
	 * This method changes the parent of any given node to the given parentId and
	 * prints the entire tree
	 */
	public void changeParent(String id, String parentId) {
		Node node = redisService.getNodebyID(id);
		Node parent = redisService.getNodebyID(parentId);

		node.setParent(parent);
		node.setHeight(parent.getHeight() + 1);

		redisService.putNode(node);

		handleChildrenNodes(node);
	}

	//change the parent to newlyUpdated node and change it's height
	void handleChildrenNodes(Node node) {
		List<String> descendants = redisService.getDescendants("node_"+node.getId() + "_descendants");
		for (String id : descendants) {
			Node node1 = redisService.getNodebyID(id);
			node1.setParent(node);
			node1.setHeight(node.getHeight() + 1);
			redisService.putNode(node1);
		}
	}
}
