package com.ts.internal.redis;

import java.util.List;

import com.ts.internal.dto.Node;

public interface RedisCacheManagerIF {

	Node getNodeByID(String id);

	void pushDescendent(String key, String projectId);

	void putNode(Node node);

	List<String> getDescendants(String id);

}
