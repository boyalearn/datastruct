package com.xdata.graph;

import com.xdata.basestruct.Bag;

public interface IGraph {

	public void addEdge(int v,int w);
	
	public int V();
	
	public int E();
	
	public Bag<Integer> adj(int v);

}
