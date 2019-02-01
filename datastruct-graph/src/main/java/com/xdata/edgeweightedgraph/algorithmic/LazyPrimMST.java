package com.xdata.edgeweightedgraph.algorithmic;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import com.xdata.basestruct.Bag;
import com.xdata.edgeweightedgraph.MST;
import com.xdata.edgeweightedgraph.struct.Edge;
import com.xdata.edgeweightedgraph.struct.EdgeWeightedGraph;
/**
 * 
 * @author zouhuixing
 * 最小生成树
 * TODO 待分析算法
 * 
 */
public class LazyPrimMST implements MST{
	
	private boolean[] marked;
	
	private Queue<Edge> mst;
	
	private MinPQ<Edge> pq;
	
	public LazyPrimMST(EdgeWeightedGraph G) {
		pq = new MinPQ<Edge>();
		marked = new boolean[G.V()];
		mst = new LinkedBlockingQueue<Edge>();
		visit(G,0);
		while(!pq.isEmpty()) {
			Edge e=pq.delMin();
			int v=e.either(),w=e.other(v);
			mst.add(e);
			if(!marked[v]) {
				visit(G,v);
			}
			if(!marked[w]) {
				visit(G,w);
			}
		}
	}
	private void visit(EdgeWeightedGraph G,int v) {
		marked[v]=true;
		for(Bag<Edge> e:G.adj(v)) {
			if(!marked[e.getData().other(v)]) {
				pq.insert(e.getData());
			}
		}
	}

	@Override
	public Queue<Edge> edges() {
		return mst;
	}

	@Override
	public double weight() {
		double weight=0;
		for(Edge e:mst) {
			weight+=e.weight();
		}
		return weight;
	}
	
	public static void main(String[] args) {
		EdgeWeightedGraph G=new EdgeWeightedGraph();
		LazyPrimMST lazyPrimMST=new LazyPrimMST(G);
		System.out.println(lazyPrimMST.weight());
	}

}
