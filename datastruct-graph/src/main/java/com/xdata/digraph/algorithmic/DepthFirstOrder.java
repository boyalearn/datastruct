package com.xdata.digraph.algorithmic;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import com.xdata.basestruct.Bag;
import com.xdata.digraph.struct.Digraph;

/**
 * 深度优先的排序
 * @author zouhuixing
 *
 */
public class DepthFirstOrder {
	
	private boolean[] marked;
	
	private Queue<Integer> pre;              //所有顶点的前序排列
	
	private Queue<Integer> post;             //所有顶点的后续排列
	
	private Stack<Integer> reversePost;      //所有顶点的逆后序排列
	
	public DepthFirstOrder(Digraph G) {
		pre  = new LinkedBlockingQueue<Integer>();
		post = new LinkedBlockingQueue<Integer>();
		reversePost=new Stack<Integer>();
		marked=new boolean[G.V()];
		
		for(int v=0;v<G.V();v++) {
			if(!marked[v]) {
				dfs(G,v);
			}
		}
		
	}
	private void dfs(Digraph G,int v) {
		pre.add(v);
		marked[v]=true;
		for(Bag<Integer> w:G.adj(v)) {
			if(!marked[w.getData()]) {
				dfs(G,w.getData());
			}
		}
		post.add(v);
		reversePost.push(v);
	}
	
	public Iterable<Integer> pre(){
		return pre;
	}
	
	public Iterable<Integer> post(){
		return post;
	}
	public Iterable<Integer> reversePost(){
		return reversePost;
	}
	
	public static void main(String[] args) {
		Digraph G=new Digraph();
		DepthFirstOrder depthFirstOrder=new DepthFirstOrder(G);
		
		Queue<Integer> pre=(Queue<Integer>)depthFirstOrder.pre();
		while(!pre.isEmpty()) {
			System.out.print(" "+pre.poll());
		}
		System.out.println();
		Queue<Integer> post=(Queue<Integer>)depthFirstOrder.post();
		while(!post.isEmpty()) {
			System.out.print(" "+post.poll());
		}
		System.out.println();
		Stack<Integer>  reversePost=(Stack<Integer>)depthFirstOrder.reversePost();
		while(!reversePost.isEmpty()) {
			System.out.print(" "+reversePost.pop());
		}
	}
}
