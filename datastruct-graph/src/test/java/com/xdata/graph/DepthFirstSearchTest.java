package com.xdata.graph;

import org.junit.Test;

import com.xdata.graph.algorithmic.SearchDepthFirst;
import com.xdata.graph.struct.Graph;

public class DepthFirstSearchTest {
	@Test
	public void testOne(){
		Graph G=new Graph();
		SearchDepthFirst search=new SearchDepthFirst(G,0);
		System.out.println(search.marked(11));
	}
}
