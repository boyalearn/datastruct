package com.xdata.test;

import org.junit.Test;

import com.xdata.tree.avltree.AVLTree;


public class AVLTreeTest {
	@Test
	public void testOne(){
		AVLTree<Integer,Object> tree=new AVLTree<Integer,Object>();
		tree.add(1, 1);
		tree.add(2, 2);
		tree.add(3, 3);
		tree.add(4, 4);
		tree.add(5, 5);
		tree.add(100, 100);
		tree.remove(2);
		
		/*tree.add(6, 6);
		tree.add(7, 7);
		tree.add(8, 8);
		tree.add(9, 9);
		tree.add(10, 10);*/
		System.out.println(tree.getNext(100));
	}
}
