package com.xdata.graph.algorithmic;

import com.xdata.basestruct.Bag;
import com.xdata.graph.struct.Graph;

/**
 * ͼ�л��ļ��
 * @author zouhuixing
 * @Desc �㷨����
 * ѭ��ÿһ�����㡣������������㷨��ÿ���ҵ�һ������ǵĶ����ж��Ƿ��������ȱ�ʾ�л�
 */

public class CycleCheck {
	private boolean[] marked;
	private boolean hasCycle;
	
	public CycleCheck(Graph G){
		marked=new boolean[G.V()];
		for(int s=0;s<G.V();s++){
			if(!marked[s]){
				dfs(G,s,s);
			}
		}
	}
	
	private void dfs(Graph G, int v,int u){
		marked[v]=true;
		Bag<Integer> adj=G.adj(v);
		for(Bag<Integer> data:adj){
			if(!marked[data.getData()]){
				dfs(G,data.getData(),u);
			}else if(data.getData()==u){
				hasCycle=true;
			}
		}
	}
	
	public boolean hasCycle(){
		return hasCycle;
	}
}
