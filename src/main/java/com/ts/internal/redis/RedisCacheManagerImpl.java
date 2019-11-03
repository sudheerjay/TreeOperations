package com.ts.internal.redis;

import java.util.List;

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
	public void pushDescendent(String key, String nodeId) {
		redisUtils.pushValue(key, nodeId);
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

	@Override
	public void putNode(Node node) {
		ObjectMapper mapper = new ObjectMapper();
		String nodeData = null;
		try {
			nodeData = mapper.writeValueAsString(node);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		redisUtils.putValue("node_" + node.getId(), nodeData);
	}

	@Override
	public List<String> getDescendants(String id) {
		// TODO Auto-generated method stub
		return redisUtils.getValues(id);
	}

	@Override
	public void popDescendant(String key, String nodeId) {
		redisUtils.popValue(key, nodeId);
	}
}
