package com.xdata.test;

import org.junit.Test;

import com.xdata.tree.brtree.RBTree;

public class BRTreeNodeTest {
	@Test
	public void testOne(){
		RBTree<Integer,Object> tree=new RBTree<Integer,Object>();
		tree.put(1, 1);
		tree.put(2, 2);
		tree.put(3, 3);
		tree.put(4, 4);
		tree.put(5, 5);
		tree.put(6, 6);
		System.out.println(tree);
	}
}
