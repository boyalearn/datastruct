package com.xdata.graph;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class BreadthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	public int s;
	
	public BreadthFirstPaths(Graph G,int s){
		this.marked=new boolean[G.V()];
		this.edgeTo=new int[G.V()];
		this.s=s;
		bfs(G,s);
	}
	private void bfs(Graph G,int s){
		Queue<Integer> queue=new LinkedBlockingQueue<Integer>();
		marked[s]=true;
		queue.add(s);
		while(!queue.isEmpty()){
			int v=queue.poll();
			Graph.Bag<Integer> adj=G.adj(v);
			for(Graph.Bag<Integer> data:adj){
				if(!marked[data.getData()]){
					edgeTo[data.getData()]=v;
					marked[data.getData()]=true;
					queue.add(data.getData());
				}
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
		for(int x=v;x!=s;x=edgeTo[x]){
			path.push(x);
		}
		path.push(s);
		return path;
	}

}
