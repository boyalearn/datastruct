package com.xdata.graph;

import java.util.Stack;

public class DepthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	private final int resource;
	
	public DepthFirstPaths(Graph G,int s){
		this.resource=s;
		this.marked=new boolean[G.V()];
		this.edgeTo=new int[G.V()];
		dfs(G,s);
	}
	
	public void dfs(Graph G,int v){
		marked[v]=true;
		Graph.Bag<Integer> adj=G.adj(v);
		for(Graph.Bag<Integer> data:adj){
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
