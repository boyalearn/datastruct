package com.xdata.consisithash;

import java.util.SortedMap;
import java.util.TreeMap;

public class RBConsistHash implements ConsistHash{

	/**
	 * ����ڵ�����Ĭ��Ϊ0
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
		// �õ����ڸ�Hashֵ������Map
		SortedMap<Integer, Res> subMap = source.tailMap((int)index);
		 // ��һ��Key����˳ʱ���ȥ��node������Ǹ����
		if(null==subMap||subMap.isEmpty()){
			subMap= source.tailMap(0);
		}
		Integer i = subMap.firstKey();
		// ���ض�Ӧ�ķ���������
		return subMap.get(i);
	}

}
