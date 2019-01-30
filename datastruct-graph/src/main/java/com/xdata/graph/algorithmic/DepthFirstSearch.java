package com.xdata.graph;

public class DepthFirstSearch {
	
	private boolean[] marked;
	
	private int count;
	
	public DepthFirstSearch(Graph G,int s){
		marked=new boolean[G.V()];
		dfs(G,s);
	}
	
	private void dfs(Graph G,int v){
		marked[v]=true;
		count++;
		Graph.Bag<Integer> adj=G.adj(v);
		for(Graph.Bag<Integer> data:adj){
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
