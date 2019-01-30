package com.xdata.graph.algorithmic;

import com.xdata.basestruct.Bag;
import com.xdata.graph.struct.Graph;

/**
 * 
 * @author zouhuixing
 * 
 * 连通分量
 * 
 * @desc 连通分量算法方式。
 * 循环遍历顶点。每一个顶点采用深度优先的方式进行遍历。
 * 每开始一次深度遍历表示有一个连通子图
 *
 */
public class ConnectComCalculate {
	
	private boolean[] marked;         //记录一个顶点是否被访问的数组
	private int[] id;                 //记录一个顶点是属于哪个连通分量
	private int count;                //记录连通分量数量
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
	 * 深度优先的遍历方式
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
	 * 判断两个顶点是否连通
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
