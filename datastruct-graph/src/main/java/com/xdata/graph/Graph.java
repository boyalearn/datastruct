package com.xdata.graph;

public class Graph {
	private Bag<Integer> bag;
	
	
	private class Bag<T>{
		private T node;
		private T next;
	}
	
	private class Node<T>{
		private T data;
		private T next;
	}

}
