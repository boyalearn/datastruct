package com.xdata.algorithmic;

import com.xdata.tree.AVLTree;

public class ConsistHash {
	
	/**
	 * 虚拟节点数量默认为0
	 */
	private int virNode=0;
	
	private AVLTree<Integer,Object> source=new AVLTree<Integer,Object>();
	
	public void addResource(Resource resource){
		boolean isAdd=false;
		if(virNode>0){
			int steplen=Integer.MAX_VALUE/virNode;
			int hashCode=resource.getHashCode();
			for(int i=0;i<virNode;i++){
				while(!isAdd){
					isAdd=source.add(Math.abs(hashCode+i*steplen+(int)Math.random()),resource);
				}
			}
		}else{
			while(!isAdd){
				isAdd=source.add(resource.getHashCode(),resource);
			}
		}
		
		
	}
	public void removeResource(Resource resource){
		source.remove(resource.getHashCode());
	}
	
	public Resource getResurce(Integer index){
		Resource curr=(Resource)source.getSortNext(index);
		if(null==curr){
			return (Resource)source.getSortNext(0);
		}
		return curr;
	}
	/**
	 * 
	 * @param virNode 虚拟节点数量
	 */
	public ConsistHash(int virNode) {
		this.virNode=virNode;
	}
	
	
}
