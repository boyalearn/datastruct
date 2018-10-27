package com.xdata.tree.avltree;

/**
 * 
 * @describe 平衡树实现
 * 
 * @author zouhuixing
 *
 * @param <K>
 * @param <V>
 */

public class AVLTree<K, V> {

	private AVLTreeNode<K, V> root;
	
	/**
	 * @function 通过key获取节点数据
	 * 顺序遍历节点就可以找到数据
	 * @param key
	 * @return
	 */
	public AVLTreeNode<K, V> get(K key) {
		return getEntry(key);
	}
	
	/**
	 * @function 顺序获取下一个节点
	 * 通过参数key找到一个大于小于该节点的数据。
	 * @param index
	 * @return
	 */
	public V getNext(K index){
		AVLTreeNode<K,V> curr=this.root;
		int cmp=0;
		AVLTreeNode<K,V> parent=curr;
		while(null!=curr){
			parent=curr;
			cmp=compare(curr.key,index);
			if(cmp>0){
				curr=curr.left;
			}else if(cmp<0){
				curr=curr.right;
			}else{
				return curr.value;
			}
		}
		if(cmp>0){
			return parent.value;
		}else{
			while(null!=parent.parent){
				if(parent.parent.left==parent){
					return parent.parent.value;
				}else{
					parent=parent.parent;
				}
			}
		}
		return null;
	}
	
	/**
	 * 删除一个节点
	 * @param key
	 * @return
	 */
	public Boolean remove(K key) {
		AVLTreeNode<K, V> e = getEntry(key);
		if (e != null) {
			deleteEntry(e);
			return true;
		}
		return false;

	}
	
	/**
	 * 添加一个节点
	 * 
	 * 算法思路
	 * 1.从跟节点开始找到插入位置。
	 * 2.根据从插入位置开始向上分析不平衡点。
	 *   然后通过左旋右旋将树调节平衡
	 *   
	 * 当数不满足平衡树条件的时候树有如下几种情况
	 * 
	 * 情况1
	 *                  4
	 *                 / \
	 *                3   5
	 *               / 
	 *              2
	 *             /
	 *            1  
	 *            
	 * 情况2           
	 *            
	 *                  4
	 *                 / \
	 *                3   5
	 *                     \
	 *                      6
	 *                       \
	 *                        7
	 * 情况3
	 *                  4
	 *                 / \
	 *                3   5
	 *               /
	 *              1
	 *               \
	 *                2
	 *                
	 * 情况4
	 *                  4
	 *                 / \
	 *                3   5
	 *                     \
	 *                      7
	 *                     /
	 *                    6
	 *                     
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean add(K key, V value) {
		if (null == this.root) {
			this.root = new AVLTreeNode<K, V>(key, value);
			return true;
		}

		AVLTreeNode<K, V> parent; // 保存curr的父节点
		AVLTreeNode<K, V> curr;
		curr = this.root;
		// 从根节点向下搜索，找到插入位置
		int cmp;
		do {
			parent = curr;
			cmp = compare(curr.key, key);
			if (cmp < 0) {
				curr = curr.right;
			} else if (cmp > 0) {
				curr = curr.left;
			} else {
				return false;
			}
		} while (curr != null);

		if (cmp < 0) {
			parent.right = new AVLTreeNode<K, V>(key, value);
			parent.right.parent = parent;

		} else {
			parent.left = new AVLTreeNode<K, V>(key, value);
			parent.left.parent = parent;
		}
		// 自下向上回溯，查找最近不平衡节点
		while (parent != null) {
			cmp = compare(parent.key, key);
			if (cmp < 0) { // 插入节点在parent的左子树中
				parent.bf--;
			} else { // 插入节点在parent的右子树中
				parent.bf++;
			}
			if (parent.bf == 0) { // 此节点的balance为0，不再向上调整BF值，且不需要旋转
				break;
			}
			if (parent.bf == 2) { // 找到最小不平衡子树根节点
				leftBalance(parent);
				break; // 不用继续向上回溯
			}
			if (parent.bf == -2) {
				rightBalance(parent);
				break;
			}
			parent = parent.parent;
		}
		return true;
	}

	private boolean rightBalance(AVLTreeNode<K, V> node) {
		boolean heightLower = true;
		AVLTreeNode<K, V> curr = node.right;
		switch (curr.bf) {
		case 1: // 左高，分情况调整
			AVLTreeNode<K, V> ld = curr.left;
			switch (ld.bf) { // 调整各个节点的BF
			case 1: // 情况1
				node.bf = 0;
				curr.bf = -1;
				break;
			case 0: // 情况2
				node.bf = curr.bf = 0;
				break;
			case -1: // 情况3
				node.bf = 1;
				curr.bf = 0;
				break;
			}
			ld.bf = 0;
			rotateRight(node.right);
			rotateLeft(node);
			break;
		case -1: // 右高，左旋调整
			node.bf = curr.bf = 0;
			rotateLeft(node);
			break;
		case 0: // 特殊情况4
			curr.bf = 1;
			node.bf = -1;
			rotateLeft(node);
			heightLower = false;
			break;
		}
		return heightLower;
	}

	private boolean leftBalance(AVLTreeNode<K, V> node) {
		boolean heightLower = true;
		AVLTreeNode<K, V> curr = node.left;
		switch (curr.bf) {
		case 1: // 左高，右旋调整,旋转后树的高度减小
			node.bf = curr.bf = 0;
			rotateRight(node);
			break;
		case -1: // 右高，分情况调整
			AVLTreeNode<K, V> rd = curr.right;
			switch (rd.bf) { // 调整各个节点的BF
			case 1: // 情况1
				node.bf = -1;
				curr.bf = 0;
				break;
			case 0: // 情况2
				node.bf = curr.bf = 0;
				break;
			case -1: // 情况3
				node.bf = 0;
				node.bf = 1;
				break;
			}
			rd.bf = 0;
			rotateLeft(node.left);
			rotateRight(node);
			break;
		case 0: // 特殊情况4,这种情况在添加时不可能出现，只在移除时可能出现，旋转之后整体树高不变
			curr.bf = -1;
			node.bf = 1;
			rotateRight(node);
			heightLower = false;
			break;
		}
		return heightLower;
	}

	private void rotateRight(AVLTreeNode<K, V> node) {
		if (null != node) {
			AVLTreeNode<K, V> curr = node.left;
			node.left = curr.right; // 把B的右节点BR作为A的左节点
			if (curr.right != null) // 如果BR不为null，设置BR的父节点为A
				curr.right.parent = node;
			curr.parent = node.parent; // A的父节点赋给B的父节点
			if (node.parent == null) // 如果p是根节点
				this.root = curr; // B为根节点
			else if (node.parent.right == node) // 如果A是其父节点的左子节点
				node.parent.right = curr; // B为A的父节点的左子树
			else // 如果A是其父节点的右子节点
				node.parent.left = curr; // B为A的父节点的右子树
			curr.right = node; // A为B的右子树
			node.parent = curr; // 设置A的父节点为B
		}
	}

	private void rotateLeft(AVLTreeNode<K, V> node) {
		if (null != node) {
			AVLTreeNode<K, V> curr = node.right;
			node.right = curr.left; // 把p右子树的左节点嫁接到p的右节点，如上图，把BL作为A的右子节点
			if (curr.left != null) // 如果B的左节点BL不为空，把BL的父节点设为A
				curr.left.parent = node;
			curr.parent = node.parent; // A的父节点设为B的父节点
			if (node.parent == null) // 如果p是根节点
				this.root = curr; // r变为父节点，即B为父节点
			else if (node.parent.left == node) // 如果p是左子节点
				node.parent.left = curr; // p的父节点的左子树为r
			else // 如果p是右子节点
				node.parent.right = curr; // p的父节点的右子树为r
			curr.left = node; // p变为r的左子树，即A为B的左子树
			node.parent = curr; // 同时更改p的父节点为r，即A的父节点为B
		}
	}

	

	private AVLTreeNode<K, V> getEntry(K key) {
		AVLTreeNode<K, V> tmp = root;
		int c;
		while (tmp != null) {
			c = compare(key,tmp.key);
			if (c == 0) {
				return tmp;
			} else if (c < 0) {
				tmp = tmp.left;
			} else {
				tmp = tmp.right;
			}
		}
		return null;
	}


	private void deleteEntry(AVLTreeNode<K, V> node){
		//如果node左右子树都不为空，找到其直接后继，替换node，之后node指向s，删除node其实是删除s
		//所有的删除左右子树不为空的节点都可以调整为删除左右子树有其一不为空，或都为空的情况。
		if (node.left != null && node.right != null) {
			AVLTreeNode<K, V> s = successor(node);
			node.key=s.key;
			node.value = s.value;
			node = s;
		}
		AVLTreeNode<K, V> replacement = (node.left != null ? node.left : node.right);
 
        if (replacement != null) {		//如果其左右子树有其一不为空
            replacement.parent = node.parent;
            if (node.parent == null)	//如果p为root节点
                root = replacement;
            else if (node == node.parent.left)	//如果p是左孩子
            	node.parent.left  = replacement;	
            else							//如果p是右孩子
            	node.parent.right = replacement;
 
            node.left = node.right = node.parent = null;		//p的指针清空
            
            //这里更改了replacement的父节点，所以可以直接从它开始向上回溯
            fixAfterDeletion(replacement);	
 
        } else if (node.parent == null) { // 如果全树只有一个节点
            root = null;
        } else {  //左右子树都为空
        	fixAfterDeletion(node);	//这里从p开始回溯
            if (node.parent != null) {
                if (node == node.parent.left)
                	node.parent.left = null;
                else if (node == node.parent.right)
                	node.parent.right = null;
                node.parent = null;
            }
        }	
	}

	private void fixAfterDeletion(AVLTreeNode<K, V> p) {
		boolean heightLower = true; // 看最小子树调整后，它的高度是否发生变化，如果减小，继续回溯
		AVLTreeNode<K, V> t = p.parent;
		int cmp;
		// 自下向上回溯，查找不平衡的节点进行调整
		while (t != null && heightLower) {
			cmp =compare(p.key,t.key);
			/**
			 * 删除的节点是右子树，等于的话，必然是删除的某个节点的左右子树不为空的情况 例如： 10 / \ 5 15 / \ 3 6
			 * 这里删除5，是把6的值赋给5，然后删除6，这里6是p，p的父节点的值也是6。 而这也是右子树的一种
			 */
			if (cmp >= 0) {
				t.bf++;
			} else {
				t.bf--;
			}
			if (Math.abs(t.bf) == 1) { // 父节点经过调整平衡因子后，如果为1或-1，说明调整之前是0，停止回溯。
				break;
			}
			AVLTreeNode<K, V> r = t;
			// 这里的调整跟插入一样
			if (t.bf == 2) {
				heightLower = leftBalance(r);
			} else if (t.bf == -2) {
				heightLower = rightBalance(r);
			}
			t = t.parent;
		}
	}

	/**
	 * 返回以中序遍历方式遍历树时，t的直接后继
	 */
	private AVLTreeNode<K, V> successor(AVLTreeNode<K, V> t) {
		if (t == null)
			return null;
		else if (t.right != null) { // 往右，然后向左直到尽头
			AVLTreeNode<K, V> p = t.right;
			while (p.left != null)
				p = p.left;
			return p;
		} else { // right为空，如果t是p的左子树，则p为t的直接后继
			AVLTreeNode<K, V> p = t.parent;
			AVLTreeNode<K, V> ch = t;
			while (p != null && ch == p.right) { // 如果t是p的右子树，则继续向上搜索其直接后继
				ch = p;
				p = p.parent;
			}
			return p;
		}
	}

	@SuppressWarnings("unchecked")
	private int compare(Object key1, Object key2) {
		return ((Comparable<? super K>) key1).compareTo((K) key2);
	}
}
