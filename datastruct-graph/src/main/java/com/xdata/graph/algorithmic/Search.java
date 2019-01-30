package com.xdata.graph;

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
