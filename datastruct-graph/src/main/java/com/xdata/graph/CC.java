package com.xdata.graph;
/**
 * 
 * @author zouhuixing
 * 
 * 连通分量
 *
 */
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
		for(Graph.Bag<Integer> data:adj){
			if(!marked[data.getData()]){
				dfs(G,data.getData());
			}
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
