package com.ts.internal.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ts.internal.services.TreeOperationsService;

@RestController
@RequestMapping("/tree")
public class TreeOperationsResource {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TreeOperationsResource.class);

	@Autowired
	TreeOperationsService service;

	@GetMapping("/node/getDescendants")
	public List<String> getDescendants(@RequestParam("nodeId") String id) {
		LOGGER.info("Entring getDescendants from Resource");
		return service.getDescendantNodes(id);
	}

	@PutMapping("/node/changeParent")
	public void changeParent(@RequestParam("nodeId") String id, @RequestParam("parentId") String parentId) {
		LOGGER.info("Entring changeParent from Resource");
		service.changeParent(id, parentId);
	}
}
