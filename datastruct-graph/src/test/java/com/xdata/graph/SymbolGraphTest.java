package com.xdata.graph;

import java.util.Stack;

import org.junit.Test;

import com.xdata.Bag;
import com.xdata.graph.algorithmic.SearchPathBreadthFirst;
import com.xdata.symbolgraph.struct.SymbolGraph;

public class SymbolGraphTest {
	@Test
	public void testOne(){
		SymbolGraph sg=new SymbolGraph();
		Graph G=sg.G();
		Bag<Integer> adj=G.adj(sg.index("LAS"));
		for(Bag<Integer> data:adj){
			System.out.println("  "+sg.name(data.getData()));
		}
	}
	@Test
	public void testTwo(){
		SymbolGraph sg=new SymbolGraph();
		Graph G=sg.G();
		String source="MCO";
		if(!sg.contains(source)){
			System.out.println(source+" not in database.");
			return ;
		}
		int s=sg.index(source);
		SearchPathBreadthFirst bfs=new SearchPathBreadthFirst(G,s);
		int t=sg.index("LAS");
		if(bfs.hasPathTo(t)){
			Stack<Integer> stack=(Stack<Integer>)bfs.pathTo(t); 
			int length=stack.size();
			for(int i=0;i<length;i++){
				System.out.print("->"+sg.name(stack.pop()));
			}
		}else{
			System.out.println("Not connected");
		}
	}
}