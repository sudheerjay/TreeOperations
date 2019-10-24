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
	public void getDescendants(@RequestParam("nodeId") String id) {
		List<String> descendents = service.getDescendantNodes(id);
		System.out.println(descendents);
	}

	@PutMapping("/node/changeParent")
	public void changeParent(@RequestParam("nodeId") String id, @RequestParam("parentId") String parentId) {
		service.changeParent(id, parentId);
	}
}
