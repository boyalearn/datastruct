package com.xdata.graph;

import org.junit.Test;

import com.xdata.graph.algorithmic.TwoColor;
import com.xdata.graph.struct.Graph;

public class TwoColorTest {
	@Test
	public void testOne(){
		Graph G=new Graph();
		TwoColor two=new TwoColor(G);
		System.out.println(two.isBipartite());
	}

}
