package com.ts.internal.dto;

import org.springframework.beans.factory.annotation.Autowired;

public class Node {

	private int id;
	private Node parent;
	private Node root;
	private int height;

	@Autowired
	public Node(int id, Node root, Node parent, int height) {
		this.id=id;
		this.root=root;
		this.parent=parent;
		this.height=height;
	}
	
	public Node() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
