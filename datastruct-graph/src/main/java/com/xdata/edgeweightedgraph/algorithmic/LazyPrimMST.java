package com.xdata.edgeweightedgraph.algorithmic;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import com.xdata.Bag;
import com.xdata.edgeweightedgraph.MST;
import com.xdata.edgeweightedgraph.struct.Edge;
import com.xdata.edgeweightedgraph.struct.EdgeWeightedGraph;
/**
 * 
 * @author zouhuixing
 * ��С������
 * TODO �������㷨
 * 
 */
public class LazyPrimMST implements MST{
	
	private boolean[] marked;   //��С�������Ķ���
	
	private Queue<Edge> mst;    //��С�������ı�
	
	private MinPQ<Edge> pq;     //���бߣ���Ч�ıߣ�
	
	public LazyPrimMST(EdgeWeightedGraph G) {
		pq = new MinPQ<Edge>();
		marked = new boolean[G.V()];
		mst = new LinkedBlockingQueue<Edge>();
		visit(G,0);
		while(!pq.isEmpty()) {
			Edge e=pq.delMin();
			int v=e.either(),w=e.other(v);
			if(marked[v]&&marked[w]) continue;
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
		for(Edge e:G.adj(v)) {
			if(!marked[e.other(v)]) {
				pq.insert(e);
			}
		}
	}

	@Override
	public Queue<Edge> edges() {
		return mst;
	}
	
	public Iterable<Edge> minPQ(){
		return pq;
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
		Queue<Edge> edges=lazyPrimMST.edges();
		System.out.println("Path  :");
		for(Edge edge:edges) {
			System.out.println("  "+edge.toString());
		}
		System.out.println("Weight:");
		System.out.println(lazyPrimMST.weight());
	}

}
