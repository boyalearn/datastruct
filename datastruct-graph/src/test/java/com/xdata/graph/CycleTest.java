package com.xdata.graph;

import org.junit.Test;

import com.xdata.graph.algorithmic.Cycle;
import com.xdata.graph.struct.Graph;

public class CycleTest {
	@Test
	public void testOne(){
		Graph G=new Graph();
		Cycle cycle=new Cycle(G);
		System.out.println(cycle.hasCycle());
	}
}
