package com.xdata.graph;

import org.junit.Test;
import com.xdata.Bag;
import com.xdata.graph.algorithmic.ConnectComCalculate;

public class CCTest {
	
	@SuppressWarnings("unchecked")
	@Test
	public void testOne(){
		Graph G=new Graph();
		ConnectComCalculate cc=new ConnectComCalculate(G);
		
		int M=cc.count();
		System.out.println(M+" components");
		
		Bag<Integer>[] components=(Bag<Integer>[])new Bag[M];
		for(int i=0;i<M;i++){
			components[i]=new Bag<Integer>();
		}
		for(int v=0;v<G.V();v++){
			components[cc.id(v)].add(v);
		}
		for(int i=0;i<M;i++){
			Bag<Integer> adj=components[i];
			for(Integer data:adj){
				System.out.print(data+" ");
			}
			System.out.println();
		}
	}

}
