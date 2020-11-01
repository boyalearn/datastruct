package com.xdata.graph;

import org.junit.Test;

import com.xdata.graph.algorithmic.CycleCheck;

public class CycleTest {
	@Test
	public void testOne(){
		Graph G=new Graph();
		CycleCheck cycle=new CycleCheck(G);
		System.out.println(cycle.hasCycle());
	}
}
