package com.xdata.graph;

import java.util.Stack;

import org.junit.Test;

import com.xdata.graph.algorithmic.SearchPathBreadthFirst;

public class BreadthFirstPathsTest {
	@Test
	public void testOne(){
		Graph G=new Graph();
		SearchPathBreadthFirst path=new SearchPathBreadthFirst(G,0);
		for(int to=0;to<G.V();to++){
			Stack<Integer> stack=(Stack<Integer>)path.pathTo(to);
			System.out.println(path.s+"to"+to);
			if(null==stack){
				System.out.println("null");
				continue;
			}
			int len=stack.size();
			int x=0;
			for(int i=0;i<len;i++){
				x=stack.pop();
				if(x==path.s){
					System.out.print(x);
				}else{
				    System.out.print("->"+x);
				}
			}
			System.out.println();
		}
	}

}