package com.xdata.algorithmic;

import com.xdata.tree.AVLTree;

public class AVLConsistHash implements ConsistHash{
	
	/**
	 * 虚拟节点数量默认为0
	 */
	private int virNode=0;
	
	private AVLTree<Integer,Object> source=new AVLTree<Integer,Object>();
	
	
	/**
	 * 
	 * @param virNode 虚拟节点数量
	 */
	public AVLConsistHash(int virNode) {
		this.virNode=virNode;
	}
	@Override
	public void addRes(Res res) {
		if(virNode+1>1){
			for(int i=0;i<virNode+1;i++){
				source.add(res.hashCode("#"+i),res);
			}
		}else{
			source.add(res.hashCode("#1"),res);
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
		Res curr=(Res)source.getSortNext((int)index);
		if(null==curr){
			return (Res)source.getSortNext(0);
		}
		return curr;
	}
	
	
}
