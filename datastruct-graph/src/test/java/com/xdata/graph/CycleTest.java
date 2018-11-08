package com.xdata.graph;

import org.junit.Test;

public class CycleTest {
	@Test
	public void testOne(){
		Graph G=new Graph();
		Cycle cycle=new Cycle(G);
		System.out.println(cycle.hasCycle());
	}
}
