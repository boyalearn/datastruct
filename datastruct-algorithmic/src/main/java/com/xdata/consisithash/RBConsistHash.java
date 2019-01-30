package com.xdata.consisithash;

import java.util.SortedMap;
import java.util.TreeMap;

public class RBConsistHash implements ConsistHash{

	/**
	 * 虚拟节点数量默认为0
	 */
	private int virNode=0;
	
	private TreeMap<Integer,Res> source=new TreeMap<Integer,Res>();
	
	public RBConsistHash(int virNode) {
		this.virNode=virNode;
	}

	@Override
	public void addRes(Res res) {
		if(virNode+1>1){
			for(int i=0;i<virNode+1;i++){
				source.put(res.hashCode("#"+i),res);
			}
		}else{
			source.put(res.hashCode("#1"),res);
		}
		
	}

	@Override
	public void rmRes(Res res) {
		if(virNode>0){
			for(int i=0;i<virNode;i++){
				source.remove(res.hashCode("#"+i));
			}
		}else{
			source.remove(res.hashCode("#1"));
		}	
		
	}

	@Override
	public Res getRes(Object index) {
		// 得到大于该Hash值的所有Map
		SortedMap<Integer, Res> subMap = source.tailMap((int)index);
		 // 第一个Key就是顺时针过去离node最近的那个结点
		if(null==subMap||subMap.isEmpty()){
			subMap= source.tailMap(0);
		}
		Integer i = subMap.firstKey();
		// 返回对应的服务器名称
		return subMap.get(i);
	}

}
