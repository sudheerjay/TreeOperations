package com.ts.internal.dto;

public class Node {

	private int id;
	private Node parent;
	private Node root;
	private Node left, right;

	// private List<Node> children;
	private int height;

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

//	public List<Node> getChildren() {
//		return children;
//	}
//
//	public void setChildren(List<Node> children) {
//		this.children = children;
//	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
