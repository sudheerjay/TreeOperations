package com.ts.internal.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ts.internal.dto.Node;

@Configuration
public class RedisCacheManagerImpl implements RedisCacheManagerIF {
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheManagerImpl.class);
	private RedisUtil<String> redisUtils;

	@Autowired
	public RedisCacheManagerImpl(RedisUtil<String> redisUtilRequestResource) {
		this.redisUtils = redisUtilRequestResource;
	}

	@Override
	public void pushDescendent(String key, String projectId) {
		LOGGER.debug("Entering method pushProjectId key:{},projectId:{}", key, projectId);
		redisUtils.pushValue(key, projectId);
	}

	@Override
	public Node getNodeByID(String id) {
		ObjectMapper mapper = new ObjectMapper();
		String nodeInfo = redisUtils.getValue(id);
		Node node = null;
		try {
			node = mapper.readValue(nodeInfo, Node.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return node;
	}
}
