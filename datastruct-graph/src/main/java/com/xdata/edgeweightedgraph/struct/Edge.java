package com.xdata.edgeweightedgraph.struct;

import javax.management.RuntimeErrorException;

public class Edge implements Comparable<Edge>{
	
	private int v;
	
	private int w;
	
	private double weight;
	
	public Edge(int v,int w,double weight ){
		this.v=v;
		this.w=w;
		this.weight=weight;
	}
	
	public double weight(){
		return weight;
	}
	
	public int either(){
		return v;
	}
	
	public int other(int vertex){
		if(w==vertex) return v;
		else if(v==vertex) return w;
		else throw new RuntimeException("不存在这样的边");
	}

	@Override
	public int compareTo(Edge o) {
		if(this.weight<o.weight)
			return -1;
		else if(this.weight==o.weight)
			return 0;
		else return 1;
	}
	
	
}
