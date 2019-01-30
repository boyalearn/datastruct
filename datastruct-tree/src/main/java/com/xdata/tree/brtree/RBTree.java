package com.xdata.tree.brtree;

/**
 * 红黑树
 * 
 * 红黑树可以与一棵2-3树完全等价。所以写操作参考2-3树
 * 
 * 红连接将两个2-节点连接成一个3-节点。这就是为何红黑树从根节点到每个叶子节点中黑色节点的个数一样的原因
 * 
 * 核心算法
 *   增加节点
 *   删除节点
 *   查找节点
 *   
 * @author zouhuixing
 *
 * @param <K>
 * @param <V>
 */
public class RBTree<K extends Comparable<K>,V> {
	
	private Node<K,V> root;
	
	/**
	 * 采用递归算法
	 * @param key
	 * @param value
	 */
	public void put(K key,V value){
		this.root=this.put(this.root,new Node<K,V>(key,value, Node.RED));
		this.root.color=Node.BLACK;
	}
	
	
	/**
	 * 采用递归算法
	 * 
	 * @param key
	 */
	public void remove(K key){
		if(isRed(root.left)&&!isRed(root.right)){
			root.color=Node.RED;
		}
		root=delete(root,key);
		if(!isEmpty()){
			root.color=Node.BLACK;
		}
	}
	
	private Boolean isEmpty(){
		return null==this.root;
	}
	
	/**
	 * 删除的核心算法 是将要删除的节点构造成3-节点或者4-节点进行删除
	 * @param parent
	 * @param key
	 * @return
	 */
	private Node<K,V> delete(Node<K,V> parent,K key){
		if(key.compareTo(parent.key)<0){
			if(!isRed(parent.left)&&!isRed(parent.left.left)){
				parent=moveRedLeft(parent);
			}
			parent.left=delete(parent.left,key);
		}else{
			if(isRed(parent.left)){
				parent=rotateRight(parent);
			}
			if(key.compareTo(parent.key)==0 && (parent.right == null)){
				return null;
			}
			if(!isRed(parent.right)  && !isRed(parent.right.left)){
				parent=moveRedRight(parent);
			}
			if(key.compareTo(parent.key)==0){
				parent.value=get(parent.right,min(parent.right).key);
				parent.key=min(parent.right).key;
				parent.right=deleteMin(parent.right);
			}else{
				parent.right=delete(parent.right,key);
			}
		}	
		return balance(parent);
	}
	
	public V get(K key){
		return get(this.root,key);
	}
	
	private V get(Node<K,V> node,K key){
		if(null==node){
			return null;
		}
		int cmp=key.compareTo(node.key);
		if(cmp<0){
			return get(node.left,key);
		}else if(cmp>0){
			return get(node.right,key);
		}else{
			return node.value;
		}
	}
	
	private Node<K,V> min(Node<K,V> node){
		if(null==node.left){
			return node;
		}
		return min(node.left);
	}
	
	private Node<K,V> moveRedRight(Node<K,V> node){
		flipColorsForDel(node);
		if(null==node.left||!isRed(node.left.left)){
			node=rotateRight(node);
		}
		return node;
	}
	
	private Node<K,V> moveRedLeft(Node<K,V> node){
		flipColorsForDel(node);
		if(isRed(node.right.left)){
			node.right=rotateRight(node.right);
			node=rotateLeft(node);
		}
		return node;
	}
	
	private Node<K,V> deleteMin(Node<K,V> node){
		if(null==node.left){
			return null;
		}
		if(!isRed(node.left)&&!isRed(node.left.left)){
			node=moveRedLeft(node);
		}
		node.left=deleteMin(node.left);
		return balance(node);
	}
	
	private Node<K,V> balance(Node<K,V> node){
		if(isRed(node.right)){
			node=rotateLeft(node);
		}
		if(isRed(node.right)&&!isRed(node.left)){
			node=rotateLeft(node);
		}
		if(isRed(node.left)&&isRed(node.left.left)){
			node=rotateRight(node);
		}

		if(isRed(node.left)&&isRed(node.right)){
			flipColorsForDel(node);
		}
		return node;
	}
	/**
	 * 插入算法的核心是参照2-3树对节点进行调整
	 * 
	 * @param parent
	 * @param node
	 * @return
	 */
	private Node<K,V> put(Node<K,V> parent,Node<K,V> node){
		//如果找到空节点则赋值
		if(null==parent){
			node.color=Node.RED;
			return node;
		}
		//判断当前插入节点的Key与节点key大小
		int cmp=node.key.compareTo(parent.key);
		//分别在左右节点上找对应的节点插入
		if(cmp<0){
			parent.left=put(parent.left,node);
		}else if(cmp>0){
			parent.right=put(parent.right,node);
		}else{
			//如果在树上上找到相印的节点则覆盖其值
			parent.value=node.value;
		}
		/**
		 * 对应2-3树的情况  5为根节点 
		 *                 |                                     |
		 *                 5----6                           5----6
		 *                / \    \                         / \    \
		 *             NIL   NIL  NIL     左旋后                                      NIL  NIL  NIL 
		 */
		if(isRed(parent.right)&&!isRed(parent.left)){
			parent=rotateLeft(parent);
		}
		/**
		 * 对应2-3树的情况
		 *                       |                            |
		 *             3----4----5                       3----4----5                  
		 *            / \    \    \                           
		 *           NIL NIL  NIL  NIL    右旋后                                                                  
		 */
		if(isRed(parent.left)&&isRed(parent.left.left)){
			parent=rotateRight(parent);
		}
		/**
		 * 对应2-3树的情况
		 *                      |                            |
		 *                 3----4----5                       4
		 *                                处理后                                                  / \ 
		 *                                                 3   5 
		 */
		if(isRed(parent.left)&&isRed(parent.right)){
			flipColors(parent);
		}
		return parent;
	}
	
	private Node<K,V> rotateLeft(Node<K,V> node){
		Node<K,V> x=node.right;
		node.right=x.left;
		x.left=node;
		x.color=node.color;
		node.color=Node.RED;
		return x;
	}
	private Node<K,V> rotateRight(Node<K,V> node){
		Node<K,V> x=node.left;
		node.left=x.right;
		x.right=node;
		x.color=node.color;
		node.color=Node.RED;
		return x;
	}
	private void flipColors(Node<K,V> node){
		if(null!=node.left)
		node.left.color=Node.BLACK;
		if(null!=node.right)
		node.right.color=Node.BLACK;
		node.color=Node.RED;
	}
	
	
	private void flipColorsForDel(Node<K,V> node){
		if(null!=node.left)
		node.left.color=Node.RED;
		if(null!=node.right)
		node.right.color=Node.RED;
		node.color=Node.BLACK;
	}
	
	
	private boolean isRed(Node<K,V> node){
		if(null==node){
			return false;
		}
		return Node.RED==node.color;
	}
	
	
	
	/**
	 * 定义红黑树节点数据类型
	 * 
	 * @author zouhuixing
	 *
	 * @param <K>
	 * @param <V>
	 */
	@SuppressWarnings("hiding")
	private class Node<K,V> {
		private static final boolean RED=true;
		private static final boolean BLACK=false;
		
		private K key;
		
		private V value;
		
		private Node<K,V> left;
		
		private Node<K,V> right;
		
		private Boolean color;
		
		
		public Node(K key,V value,Boolean color){
			this.key=key;
			this.value=value;
			this.color=color;
		}
		


		@Override
		public String toString() {
			return "Node [key=" + key + ", value=" + value + ", left=" + left + ", right=" + right + ", color=" + color
					+ "]";
		}
		
		

	}

}
