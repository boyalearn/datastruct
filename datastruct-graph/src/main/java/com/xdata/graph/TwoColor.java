package com.xdata.graph;

public class TwoColor {
	private boolean[] marked;
	private boolean[] color;
	private boolean isTwoColorable=true;
	
	public TwoColor(Graph G){
		marked=new boolean[G.V()];
		color=new boolean[G.V()];
		for(int s=0;s<G.V();s++){
			if(!marked[s]){
				dfs(G,s);
			}
		}
	}
	private void dfs(Graph G,int v){
		marked[v]=true;
		Graph.Bag<Integer> adj=G.adj(v);
		while(null!=adj.getData()){
			if(!marked[adj.getData()]){
				color[adj.getData()]=!color[v];
				dfs(G,adj.getData());
			}else if(color[adj.getData()]==color[v]){
				isTwoColorable=false;
			}
			adj=adj.getNext();
		}
	}
	public boolean isBipartite(){
		return isTwoColorable;
	}
}
