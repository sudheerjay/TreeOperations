package com.ts.internal.redis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.ts.internal.dto.Node;

@Configuration
public class RedisServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisServiceImpl.class);
    private RedisCacheManagerIF redisManager;

    @Autowired
    public RedisServiceImpl(RedisCacheManagerIF redisManager) {
        this.redisManager = redisManager;
    }

    public RedisServiceImpl() {
    }

    public void cacheDescendentNodes(String id, List<String> descendants) {
    	for(String descendantID: descendants) {
    		redisManager.pushDescendent("node_"+id+"_descendants", descendantID);
    	}
    }

    public Node getNodebyID(String id) {
    	return redisManager.getNodeByID("node_"+id);
    }
    
    public List<String> getDescendants(String id) {
    	return redisManager.getDescendants(id+"_descendants");
    }
    
    public void putNode(Node node) {
    	redisManager.putNode(node);
    }

}
