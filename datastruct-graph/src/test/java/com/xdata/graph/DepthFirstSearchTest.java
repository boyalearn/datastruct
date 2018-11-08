package com.xdata.graph;

import org.junit.Test;

public class DepthFirstSearchTest {
	@Test
	public void testOne(){
		Graph G=new Graph();
		DepthFirstSearch search=new DepthFirstSearch(G,0);
		System.out.println(search.marked(11));
	}
}
