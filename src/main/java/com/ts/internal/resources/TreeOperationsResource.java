package com.ts.internal.resources;

import java.util.List;

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

	@Autowired
	TreeOperationsService service;

	@GetMapping("/node/getDescendants")
	public List<String> getDescendants(@RequestParam("nodeId") String id) {
		return service.getDescendantNodes(id);
	}

	@PutMapping("/node/changeParent")
	public void changeParent(@RequestParam("nodeId") String id, @RequestParam("parentId") String parentId) {
		service.changeParent(id, parentId);
	}
}
