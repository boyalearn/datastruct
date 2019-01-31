package com.xdata.digraph.algorithmic;

import java.util.Stack;

import com.xdata.basestruct.Bag;
import com.xdata.digraph.struct.Digraph;

public class DirectedCycle {
	
	private boolean[] marked;
	
	private int[] edgeTo;
	
	private Stack<Integer> cycle;
	
	private boolean[] onStack;
	
	public DirectedCycle(Digraph G) {
		onStack=new boolean[G.V()];
		edgeTo=new int[G.V()];
		marked=new boolean[G.V()];
		for(int v=0;v<G.V();v++) {
			if(!marked[v]) {
				dfs(G,v);
			}
		}
	}
	
	private void dfs(Digraph G,int v) {
		onStack[v]=true;
		marked[v]=true;
		for(Bag<Integer> w:G.adj(v)) {
			if(this.hasCycle()) {
				return ;
			}else if(!marked[w.getData()]){
				edgeTo[w.getData()]=v;
				dfs(G,w.getData());
			}else if(onStack[w.getData()]) {
				cycle=new Stack<Integer>();
				for(int x=v;x!=w.getData();x=edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w.getData());
				cycle.push(v);
			}
		}
		onStack[v]=false;
	}
	
	public Iterable<Integer> cycle() {
		return cycle;
	}
	
	public boolean hasCycle() {
		return null!=cycle;
	}
	
	
	public static void main(String[] args) {
		Digraph G=new Digraph();
		DirectedCycle directedCycle=new DirectedCycle(G);
		if(directedCycle.hasCycle()) {
			Stack<Integer> stack=directedCycle.cycle;
			int length=stack.size();
			for(int i=0;i<length;i++) {
				int k=stack.pop();
				if(i==0) {
					System.out.print(k);
				}else {
					System.out.print("->"+k);
				}
				
			}
		}else {
			System.out.println("no cycle");
		}
	}

}
