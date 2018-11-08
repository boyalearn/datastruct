package com.xdata.graph;

import org.junit.Test;

public class SymbolGraphTest {
	//@Test
	public void testOne(){
		SymbolGraph sg=new SymbolGraph();
		Graph G=sg.G();
		Graph.Bag<Integer> adj=G.adj(sg.index("LAS"));
		while(null!=adj.getData()){
			System.out.println("  "+sg.name(adj.getData()));
			adj=adj.getNext();
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
		BreadthFirstPaths bfs=new BreadthFirstPaths(G,s);
		int t=sg.index("MCO");
		if(bfs.hasPathTo(t)){
			for(int v:bfs.pathTo(t)){
				System.out.println("  "+sg.name(v));
			}
		}else{
			System.out.println("Not connected");
		}
	}
}