package com.xdata.graph;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class BreadthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	private int s;
	
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
			while(null!=adj.getData()){
				if(!marked[adj.getData()]){
					edgeTo[adj.getData()]=v;
					marked[adj.getData()]=true;
					queue.add(adj.getData());
				}
				adj=adj.getNext();
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
