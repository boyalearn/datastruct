package com.xdata.graph;

import java.util.Stack;
import org.junit.Test;

public class DepthFirstPathsTest {
	@Test
	public void testOne(){
		Graph G=new Graph();
		//深度优先算法
		DepthFirstPaths path=new DepthFirstPaths(G,0);
		Stack<Integer> stack=(Stack<Integer>)path.pathTo(6);
		int len=stack.size();
		for(int i=0;i<len;i++){
			System.out.print(stack.pop()+" ");
		}
	}
}
