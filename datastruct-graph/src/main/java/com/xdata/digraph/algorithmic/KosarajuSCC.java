package com.xdata.digraph.algorithmic;

import com.xdata.basestruct.Bag;
import com.xdata.digraph.struct.Digraph;

/**
 * 
 * @author zouhuixing
 *
 * ǿ��ͨ�����㷨��
 * 
 * �㷨������  TODO
 */
public class KosarajuSCC implements SCC{

	private boolean[] marked;         //�Ѿ����ʹ��Ķ���
	
	private int[] id;                 //ǿ��ͨ������ʶ��
	
	private int count;                //ǿ��ͨ����������
	
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
