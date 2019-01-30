package com.xdata.graph.algorithmic;

import com.xdata.basestruct.Bag;
import com.xdata.graph.struct.Graph;

public class SearchDepthFirst {
	
	private boolean[] marked;
	
	private int count;
	
	public SearchDepthFirst(Graph G,int s){
		marked=new boolean[G.V()];
		dfs(G,s);
	}
	
	private void dfs(Graph G,int v){
		marked[v]=true;
		count++;
		Bag<Integer> adj=G.adj(v);
		for(Bag<Integer> data:adj){
			if(!marked[data.getData()]){
				dfs(G,data.getData());
			}
		}
	}
	
	public boolean marked(int w){
		return marked[w];
	}
	public int count(){
		return count;
	}
}
