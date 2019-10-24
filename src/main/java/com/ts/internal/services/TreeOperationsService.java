package com.ts.internal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<String> getDescendantNodes( String id) {
		List<String> descendants = nodeOperations.getAllDescendantsAndPersist(Integer.parseInt(id));
		redisService.cacheDescendentNodes(id, descendants);
		return descendants;
	}

	/*
	 * This method changes the parent of any given node to the given parentId and
	 * prints the entire tree
	 */
	public void changeParent(String id, String parentId) {

	}

}
