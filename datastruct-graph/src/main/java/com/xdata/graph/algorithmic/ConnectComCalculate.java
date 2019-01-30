package com.xdata.graph.algorithmic;

import com.xdata.basestruct.Bag;
import com.xdata.graph.struct.Graph;

/**
 * 
 * @author zouhuixing
 * 
 * ��ͨ����
 * 
 * @desc ��ͨ�����㷨��ʽ��
 * ѭ���������㡣ÿһ���������������ȵķ�ʽ���б�����
 * ÿ��ʼһ����ȱ�����ʾ��һ����ͨ��ͼ
 *
 */
public class ConnectComCalculate {
	
	private boolean[] marked;         //��¼һ�������Ƿ񱻷��ʵ�����
	private int[] id;                 //��¼һ�������������ĸ���ͨ����
	private int count;                //��¼��ͨ��������
	public ConnectComCalculate(Graph G){
		marked=new boolean[G.V()];
		id=new int[G.V()];
		for(int s=0;s<G.V();s++){
			if(!marked[s]){
				dfs(G,s);
				count++;
			}
		}
	}
	/**
	 * ������ȵı�����ʽ
	 * @param G
	 * @param v
	 */
	private void dfs(Graph G,int v){
		marked[v]=true;
		id[v]=count;
		Bag<Integer> adj=G.adj(v);
		for(Bag<Integer> data:adj){
			if(!marked[data.getData()]){
				dfs(G,data.getData());
			}
		}
	}
	/**
	 * �ж����������Ƿ���ͨ
	 * @param v
	 * @param w
	 * @return
	 */
	public boolean connected(int v,int w){
		return id(v)==id(w);
	}
	
	public int id(int v){
		return id[v];
	}
	public int count(){
		return count;
	}

}
