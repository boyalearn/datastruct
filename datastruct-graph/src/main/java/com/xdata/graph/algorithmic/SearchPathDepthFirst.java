package com.xdata.graph.algorithmic;

import java.util.Stack;

import com.xdata.basestruct.Bag;
import com.xdata.graph.struct.Graph;

public class SearchPathDepthFirst {
	private boolean[] marked;
	private int[] edgeTo;
	private final int resource;
	
	public SearchPathDepthFirst(Graph G,int s){
		this.resource=s;
		this.marked=new boolean[G.V()];
		this.edgeTo=new int[G.V()];
		dfs(G,s);
	}
	
	public void dfs(Graph G,int v){
		marked[v]=true;
		Bag<Integer> adj=G.adj(v);
		for(Bag<Integer> data:adj){
			if(!marked[data.getData()]){
				edgeTo[data.getData()]=v;
				dfs(G,data.getData());
			}
		}
	}
	public boolean hasPathTo(int v){
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v)){
			return null;
		}
		Stack<Integer> path=new Stack<Integer>();
		for(int x=v;x!=resource;x=edgeTo[x]){
			path.push(x);
		}
		path.push(resource);
		return path;
	}
}
