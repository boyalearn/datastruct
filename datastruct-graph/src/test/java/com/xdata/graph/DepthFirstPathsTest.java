package com.xdata.graph;

import java.util.Stack;
import org.junit.Test;

import com.xdata.graph.algorithmic.SearchPathDepthFirst;
import com.xdata.graph.struct.Graph;

public class DepthFirstPathsTest {
	@Test
	public void testOne(){
		Graph G=new Graph();
		//深度优先算法
		SearchPathDepthFirst path=new SearchPathDepthFirst(G,0);
		Stack<Integer> stack=(Stack<Integer>)path.pathTo(6);
		int len=stack.size();
		for(int i=0;i<len;i++){
			System.out.print(stack.pop()+" ");
		}
	}
}
