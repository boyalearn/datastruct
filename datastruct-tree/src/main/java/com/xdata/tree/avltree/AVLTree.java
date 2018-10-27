package com.xdata.tree.avltree;

/**
 * 
 * @describe ƽ����ʵ��
 * 
 * @author zouhuixing
 *
 * @param <K>
 * @param <V>
 */

public class AVLTree<K, V> {

	private AVLTreeNode<K, V> root;
	
	/**
	 * @function ͨ��key��ȡ�ڵ�����
	 * ˳������ڵ�Ϳ����ҵ�����
	 * @param key
	 * @return
	 */
	public AVLTreeNode<K, V> get(K key) {
		return getEntry(key);
	}
	
	/**
	 * @function ˳���ȡ��һ���ڵ�
	 * ͨ������key�ҵ�һ������С�ڸýڵ�����ݡ�
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
	 * ɾ��һ���ڵ�
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
	 * ���һ���ڵ�
	 * 
	 * �㷨˼·
	 * 1.�Ӹ��ڵ㿪ʼ�ҵ�����λ�á�
	 * 2.���ݴӲ���λ�ÿ�ʼ���Ϸ�����ƽ��㡣
	 *   Ȼ��ͨ������������������ƽ��
	 *   
	 * ����������ƽ����������ʱ���������¼������
	 * 
	 * ���1
	 *                  4
	 *                 / \
	 *                3   5
	 *               / 
	 *              2
	 *             /
	 *            1  
	 *            
	 * ���2           
	 *            
	 *                  4
	 *                 / \
	 *                3   5
	 *                     \
	 *                      6
	 *                       \
	 *                        7
	 * ���3
	 *                  4
	 *                 / \
	 *                3   5
	 *               /
	 *              1
	 *               \
	 *                2
	 *                
	 * ���4
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

		AVLTreeNode<K, V> parent; // ����curr�ĸ��ڵ�
		AVLTreeNode<K, V> curr;
		curr = this.root;
		// �Ӹ��ڵ������������ҵ�����λ��
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
		// �������ϻ��ݣ����������ƽ��ڵ�
		while (parent != null) {
			cmp = compare(parent.key, key);
			if (cmp < 0) { // ����ڵ���parent����������
				parent.bf--;
			} else { // ����ڵ���parent����������
				parent.bf++;
			}
			if (parent.bf == 0) { // �˽ڵ��balanceΪ0���������ϵ���BFֵ���Ҳ���Ҫ��ת
				break;
			}
			if (parent.bf == 2) { // �ҵ���С��ƽ���������ڵ�
				leftBalance(parent);
				break; // ���ü������ϻ���
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
		case 1: // ��ߣ����������
			AVLTreeNode<K, V> ld = curr.left;
			switch (ld.bf) { // ���������ڵ��BF
			case 1: // ���1
				node.bf = 0;
				curr.bf = -1;
				break;
			case 0: // ���2
				node.bf = curr.bf = 0;
				break;
			case -1: // ���3
				node.bf = 1;
				curr.bf = 0;
				break;
			}
			ld.bf = 0;
			rotateRight(node.right);
			rotateLeft(node);
			break;
		case -1: // �Ҹߣ���������
			node.bf = curr.bf = 0;
			rotateLeft(node);
			break;
		case 0: // �������4
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
		case 1: // ��ߣ���������,��ת�����ĸ߶ȼ�С
			node.bf = curr.bf = 0;
			rotateRight(node);
			break;
		case -1: // �Ҹߣ����������
			AVLTreeNode<K, V> rd = curr.right;
			switch (rd.bf) { // ���������ڵ��BF
			case 1: // ���1
				node.bf = -1;
				curr.bf = 0;
				break;
			case 0: // ���2
				node.bf = curr.bf = 0;
				break;
			case -1: // ���3
				node.bf = 0;
				node.bf = 1;
				break;
			}
			rd.bf = 0;
			rotateLeft(node.left);
			rotateRight(node);
			break;
		case 0: // �������4,������������ʱ�����ܳ��֣�ֻ���Ƴ�ʱ���ܳ��֣���ת֮���������߲���
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
			node.left = curr.right; // ��B���ҽڵ�BR��ΪA����ڵ�
			if (curr.right != null) // ���BR��Ϊnull������BR�ĸ��ڵ�ΪA
				curr.right.parent = node;
			curr.parent = node.parent; // A�ĸ��ڵ㸳��B�ĸ��ڵ�
			if (node.parent == null) // ���p�Ǹ��ڵ�
				this.root = curr; // BΪ���ڵ�
			else if (node.parent.right == node) // ���A���丸�ڵ�����ӽڵ�
				node.parent.right = curr; // BΪA�ĸ��ڵ��������
			else // ���A���丸�ڵ�����ӽڵ�
				node.parent.left = curr; // BΪA�ĸ��ڵ��������
			curr.right = node; // AΪB��������
			node.parent = curr; // ����A�ĸ��ڵ�ΪB
		}
	}

	private void rotateLeft(AVLTreeNode<K, V> node) {
		if (null != node) {
			AVLTreeNode<K, V> curr = node.right;
			node.right = curr.left; // ��p����������ڵ�޽ӵ�p���ҽڵ㣬����ͼ����BL��ΪA�����ӽڵ�
			if (curr.left != null) // ���B����ڵ�BL��Ϊ�գ���BL�ĸ��ڵ���ΪA
				curr.left.parent = node;
			curr.parent = node.parent; // A�ĸ��ڵ���ΪB�ĸ��ڵ�
			if (node.parent == null) // ���p�Ǹ��ڵ�
				this.root = curr; // r��Ϊ���ڵ㣬��BΪ���ڵ�
			else if (node.parent.left == node) // ���p�����ӽڵ�
				node.parent.left = curr; // p�ĸ��ڵ��������Ϊr
			else // ���p�����ӽڵ�
				node.parent.right = curr; // p�ĸ��ڵ��������Ϊr
			curr.left = node; // p��Ϊr������������AΪB��������
			node.parent = curr; // ͬʱ����p�ĸ��ڵ�Ϊr����A�ĸ��ڵ�ΪB
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
		//���node������������Ϊ�գ��ҵ���ֱ�Ӻ�̣��滻node��֮��nodeָ��s��ɾ��node��ʵ��ɾ��s
		//���е�ɾ������������Ϊ�յĽڵ㶼���Ե���Ϊɾ��������������һ��Ϊ�գ���Ϊ�յ������
		if (node.left != null && node.right != null) {
			AVLTreeNode<K, V> s = successor(node);
			node.key=s.key;
			node.value = s.value;
			node = s;
		}
		AVLTreeNode<K, V> replacement = (node.left != null ? node.left : node.right);
 
        if (replacement != null) {		//�����������������һ��Ϊ��
            replacement.parent = node.parent;
            if (node.parent == null)	//���pΪroot�ڵ�
                root = replacement;
            else if (node == node.parent.left)	//���p������
            	node.parent.left  = replacement;	
            else							//���p���Һ���
            	node.parent.right = replacement;
 
            node.left = node.right = node.parent = null;		//p��ָ�����
            
            //���������replacement�ĸ��ڵ㣬���Կ���ֱ�Ӵ�����ʼ���ϻ���
            fixAfterDeletion(replacement);	
 
        } else if (node.parent == null) { // ���ȫ��ֻ��һ���ڵ�
            root = null;
        } else {  //����������Ϊ��
        	fixAfterDeletion(node);	//�����p��ʼ����
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
		boolean heightLower = true; // ����С�������������ĸ߶��Ƿ����仯�������С����������
		AVLTreeNode<K, V> t = p.parent;
		int cmp;
		// �������ϻ��ݣ����Ҳ�ƽ��Ľڵ���е���
		while (t != null && heightLower) {
			cmp =compare(p.key,t.key);
			/**
			 * ɾ���Ľڵ��������������ڵĻ�����Ȼ��ɾ����ĳ���ڵ������������Ϊ�յ���� ���磺 10 / \ 5 15 / \ 3 6
			 * ����ɾ��5���ǰ�6��ֵ����5��Ȼ��ɾ��6������6��p��p�ĸ��ڵ��ֵҲ��6�� ����Ҳ����������һ��
			 */
			if (cmp >= 0) {
				t.bf++;
			} else {
				t.bf--;
			}
			if (Math.abs(t.bf) == 1) { // ���ڵ㾭������ƽ�����Ӻ����Ϊ1��-1��˵������֮ǰ��0��ֹͣ���ݡ�
				break;
			}
			AVLTreeNode<K, V> r = t;
			// ����ĵ���������һ��
			if (t.bf == 2) {
				heightLower = leftBalance(r);
			} else if (t.bf == -2) {
				heightLower = rightBalance(r);
			}
			t = t.parent;
		}
	}

	/**
	 * ���������������ʽ������ʱ��t��ֱ�Ӻ��
	 */
	private AVLTreeNode<K, V> successor(AVLTreeNode<K, V> t) {
		if (t == null)
			return null;
		else if (t.right != null) { // ���ң�Ȼ������ֱ����ͷ
			AVLTreeNode<K, V> p = t.right;
			while (p.left != null)
				p = p.left;
			return p;
		} else { // rightΪ�գ����t��p������������pΪt��ֱ�Ӻ��
			AVLTreeNode<K, V> p = t.parent;
			AVLTreeNode<K, V> ch = t;
			while (p != null && ch == p.right) { // ���t��p�������������������������ֱ�Ӻ��
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
