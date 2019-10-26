package com.ts.internal.services;

import java.util.ArrayList;
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
	 * to find the required descendants and stores them in the RedisDB.
	 */
	public List<String> getDescendantNodes(String id) {
		List<String> descendants = nodeOperations.getAllDescendantsAndPersist(Integer.parseInt(id));
		redisService.cacheDescendentNodes(id, descendants);
		return descendants;
	}

	/*
	 * This method changes the parent of any given node to the given parentId and
	 * prints the entire tree and update the RedisDB with the changes
	 */
	public void changeParent(String id, String parentId) {
		Node node = redisService.getNodebyID("node_"+id);
		Node parent = redisService.getNodebyID("node_"+parentId);	

		Node pastParent = node.getParent();
		node.setParent(parent);
		node.setHeight(parent.getHeight() + 1);

		redisService.putNode(node);

		handleChildrenNodes(node, pastParent);
		
		//remove the descendant rule from the old parent
		redisService.popDescendant("node_"+pastParent.getId()+"_descendants", "node_"+id);
		
		//add the selected node as a descendant for the newParent
		List<String> descendantNodeList = new ArrayList<>();
		descendantNodeList.add("node_"+id);
		redisService.cacheDescendentNodes(parentId, descendantNodeList);
		
	}

	void handleChildrenNodes(Node node,Node pastParent) {
		
		List<String> descendants = redisService.getDescendants("node_"+node.getId());
		
		for (String id : descendants) {
			//remove this node as a descendant from moved node
			redisService.popDescendant("node_"+node.getId()+"_descendants", id);
			
			Node node1 = redisService.getNodebyID(id);
			node1.setParent(pastParent);
			node1.setHeight(pastParent.getHeight() + 1);
			redisService.putNode(node1);
		}
		
	}
}
