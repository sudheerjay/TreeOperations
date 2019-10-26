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
    		List<String> preDescendants = redisManager.getDescendants("node_"+id+"_descendants");
    		if(!preDescendants.contains(descendantID)) // to make sure there are no duplicates in the list
    			redisManager.pushDescendent("node_"+id+"_descendants", descendantID);
    	}
    }

    /*
     * fetched a node from DB for given ID
     */
    public Node getNodebyID(String id) {
    	return redisManager.getNodeByID(id);
    }
    /*
     * Fetches all the descendant nodes for a given node.
     */
    public List<String> getDescendants(String id) {
    	return redisManager.getDescendants(id+"_descendants");
    }
    

    public void putNode(Node node) {
    	redisManager.putNode(node);
    }
    /*
     * This method deletes the given node from the descendant list of newly moved node 
     */
    public void popDescendant(String key, String nodeId) {
    	redisManager.popDescendant(key, nodeId);
    }
}
