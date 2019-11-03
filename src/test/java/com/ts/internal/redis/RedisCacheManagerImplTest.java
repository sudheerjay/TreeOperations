package com.ts.internal.redis;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class RedisCacheManagerImplTest {

	private RedisUtil<String> redisUtils = Mockito.mock(RedisUtil.class);

	@InjectMocks
	private RedisCacheManagerImpl redisManager = new RedisCacheManagerImpl(redisUtils);

	@Test
	public void testGetDataBykey() {
		String id = "node_1";
		String response = "{\"id\":1,\"parent\":null,\"root\":null,\"height\":0}";
		when(redisUtils.getValue(id)).thenReturn(response);
		ObjectMapper mapper = new ObjectMapper();
		String outPut = "";
		try {
			outPut = mapper.writeValueAsString(redisManager.getNodeByID(id));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(outPut,response);
	}

	@Test
	public void pushDescendent() {
		String key = "node_2";
		String nodeData = "node_8";
		doNothing().when(redisUtils).pushValue(key, nodeData);
		redisManager.pushDescendent(key, nodeData);
	}

	@Test
	public void shouldPopProjectId() {
		String nodeId = "node_6_descendants";
		String descendantId = "node_8";
		doNothing().when(redisUtils).popValue(nodeId, descendantId);
		redisManager.popDescendant(nodeId, descendantId);
	}

}
