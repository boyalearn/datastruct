package com.xdata.graph;

public class Cycle {
	private boolean[] marked;
	private boolean hasCycle;
	
	public Cycle(Graph G){
		marked=new boolean[G.V()];
		for(int s=0;s<G.V();s++){
			if(!marked[s]){
				dfs(G,s,s);
			}
		}
	}
	
	private void dfs(Graph G, int v,int u){
		marked[v]=true;
		Graph.Bag<Integer> adj=G.adj(v);
		while(null!=adj.getData()){
			if(!marked[adj.getData()]){
				dfs(G,adj.getData(),u);
			}else if(adj.getData()!=u){
				hasCycle=true;
			}
			adj=adj.getNext();
		}
	}
	
	public boolean hasCycle(){
		return hasCycle;
	}
}
