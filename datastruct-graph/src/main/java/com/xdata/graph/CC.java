package com.xdata.graph;

public class CC {
	private boolean[] marked;
	private int[] id;
	private int count;
	public CC(Graph G){
		marked=new boolean[G.V()];
		id=new int[G.V()];
		for(int s=0;s<G.V();s++){
			if(!marked[s]){
				dfs(G,s);
				count++;
			}
		}
	}
	
	private void dfs(Graph G,int v){
		marked[v]=true;
		id[v]=count;
		Graph.Bag<Integer> adj=G.adj(v);
		while(null!=adj.getData()){
			if(!marked[adj.getData()]){
				dfs(G,adj.getData());
			}
			adj=adj.getNext();
		}
	}
	public boolean connected(int v,int w){
		return id[v]==id[w];
	}
	
	public int id(int v){
		return id[v];
	}
	public int count(){
		return count;
	}

}
