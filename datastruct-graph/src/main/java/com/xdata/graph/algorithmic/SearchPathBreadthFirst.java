package com.xdata.graph.algorithmic;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import com.xdata.basestruct.Bag;
import com.xdata.graph.struct.Graph;

public class SearchPathBreadthFirst {
	private boolean[] marked;
	private int[] edgeTo;
	public int s;
	
	public SearchPathBreadthFirst(Graph G,int s){
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
			Bag<Integer> adj=G.adj(v);
			for(Bag<Integer> data:adj){
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
