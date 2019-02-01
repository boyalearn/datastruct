package com.xdata.digraph.algorithmic;

/**
 * 强连通分量
 * @author zouhuixing
 * 
 * 有向图中两个顶点v和w相互可达。则称他们为强连通的。
 * 一个图中，所有相互强连通的顶点的最大子集，我们称这些子集为强连通分量。
 *
 */
public interface SCC {
	
	boolean stronglyConnected(int v,int w);
	
	int count();
	
	int id(int v);
}
