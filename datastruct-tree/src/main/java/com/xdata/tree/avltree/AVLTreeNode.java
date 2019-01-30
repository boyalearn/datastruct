package com.xdata.tree.avltree;

public class AVLTreeNode<K,V> {
	public int bf=0;
	public K key;
	public V value;
	public AVLTreeNode<K,V> left;
	public AVLTreeNode<K,V> right;
	public AVLTreeNode<K,V> parent;
	
	public AVLTreeNode(K key,V value){
		this.key=key;
		this.value=value;
	}
	
	
}
