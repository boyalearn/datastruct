package com.xdata.graph.algorithmic;

import com.xdata.graph.struct.Graph;

/**
 * ´ıÊµÏÖ
 * @author zouhuixing
 *
 */
@SuppressWarnings("unused")
public class Search {
	
	private Graph graph;
	

	private int resource;
	
	public Search(Graph G,int s){
		this.graph=G;
		this.resource=s;
	}
	
	public boolean marked(int v){
		return false;
	}
	
	public int count(){
		int i=0;
		return i;
	}
}
