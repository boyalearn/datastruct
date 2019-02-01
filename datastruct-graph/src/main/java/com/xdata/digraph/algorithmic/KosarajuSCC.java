package com.xdata.digraph.algorithmic;

import com.xdata.basestruct.Bag;
import com.xdata.digraph.struct.Digraph;

/**
 * 
 * @author zouhuixing
 *
 * 强连通分量算法。
 * 
 * 算法待分析  TODO
 */
public class KosarajuSCC implements SCC{

	private boolean[] marked;         //已经访问过的顶点
	
	private int[] id;                 //强连通分量标识符
	
	private int count;                //强连通分量的数量
	
	public KosarajuSCC(Digraph G) {
		marked=new boolean[G.V()];
		id=new int[G.V()];
		DepthFirstOrder order=new DepthFirstOrder(G.reverse());
		for(int s:order.reversePost()) {
			if(!marked[s]) {
				dfs(G,s); count++;
			}
		}
	}
	
	private void dfs(Digraph G,int v) {
		marked[v]=true;
		id[v]=count;
		for(Bag<Integer> w:G.adj(v)) {
			if(!marked[w.getData()]) {
				dfs(G,w.getData());
			}
		}
	}
	
	
	@Override
	public boolean stronglyConnected(int v, int w) {
		return id[v]==id[w];
	}

	@Override
	public int count() {
		return count;
	}

	@Override
	public int id(int v) {
		return id[v];
	}
	
	public static void main(String[] args) {
		Digraph G=new Digraph();
		KosarajuSCC kosarajuSCC=new KosarajuSCC(G);
		int c=kosarajuSCC.count();
		System.out.println(c);
	}

}
