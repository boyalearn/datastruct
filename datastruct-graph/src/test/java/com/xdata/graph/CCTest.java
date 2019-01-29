package com.xdata.graph;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class CCTest {
	
	@SuppressWarnings("unchecked")
	@Test
	public void testOne(){
		Graph G=new Graph();
		CC cc=new CC(G);
		
		int M=cc.count();
		System.out.println(M+" components");
		
		Graph.Bag<Integer>[] components=(Graph.Bag<Integer>[])new Graph.Bag[M];
		for(int i=0;i<M;i++){
			components[i]=new Graph.Bag<Integer>();
		}
		for(int v=0;v<G.V();v++){
			components[cc.id(v)].add(v);
		}
		for(int i=0;i<M;i++){
			Graph.Bag<Integer> adj=components[i];
			for(Graph.Bag<Integer> data:adj){
				System.out.print(data.getData()+" ");
			}
			System.out.println();
		}
		Map<String,Object> map=new HashMap<String,Object>();
	}

}
