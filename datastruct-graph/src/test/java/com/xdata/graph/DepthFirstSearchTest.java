package com.xdata.graph;

import org.junit.Test;

public class DepthFirstSearchTest {
	@Test
	public void testOne(){
		Graph G=new Graph();
		SearchDepthFirst search=new SearchDepthFirst(G,0);
		System.out.println(search.marked(11));
	}
}
