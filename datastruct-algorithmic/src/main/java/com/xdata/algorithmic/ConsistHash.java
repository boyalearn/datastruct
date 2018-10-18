package com.xdata.algorithmic;

import com.xdata.tree.AVLTree;

public class ConsistHash {
	
	private AVLTree<Integer,Object> source=new AVLTree<Integer,Object>();
	
	public void addResource(Resource resource){
		source.add(resource.getHashCode(),resource);
	}
	public void removeResource(Resource resource){
		source.remove(resource.getHashCode());
	}
	
	public Resource getResurce(){
		return null;
	}
}
