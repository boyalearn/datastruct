package com.xdata.graph;

import java.util.Stack;

import org.junit.Test;

public class BreadthFirstPathsTest {
	@Test
	public void testOne(){
		Graph G=new Graph();
		BreadthFirstPaths path=new BreadthFirstPaths(G,0);
		Stack<Integer> stack=(Stack<Integer>)path.pathTo(2);
		int len=stack.size();
		for(int i=0;i<len;i++){
			System.out.print(stack.pop()+" ");
		}
	}

}