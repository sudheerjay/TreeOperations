package com.ts.internal.redis;

import com.ts.internal.dto.Node;

public interface RedisCacheManagerIF {

	Node getNodeByID(String id);

	void pushDescendent(String key, String projectId);

}
