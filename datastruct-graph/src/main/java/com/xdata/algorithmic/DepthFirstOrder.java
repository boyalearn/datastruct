package com.xdata.algorithmic;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import com.xdata.digraph.DiGraph;

/**
 * ������ȵ�����
 * @author zouhuixing
 *
 */
public class DepthFirstOrder {
	
	private boolean[] marked;
	
	private Queue<Integer> pre;              //���ж����ǰ������
	
	private Queue<Integer> post;             //���ж���ĺ�������
	
	private Stack<Integer> reversePost;      //���ж�������������
	
	public DepthFirstOrder(DiGraph G) {
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
	private void dfs(DiGraph G, int v) {
		pre.add(v);
		marked[v]=true;
		for(Integer w:G.adj(v)) {
			if(!marked[w]) {
				dfs(G,w);
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
		DiGraph G=new DiGraph();
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
