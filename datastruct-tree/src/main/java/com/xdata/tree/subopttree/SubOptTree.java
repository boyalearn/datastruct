package com.xdata.tree.subopttree;

import java.util.ArrayDeque;
import java.util.Stack;

public class SubOptTree {
	public static void main(String[] args) {
		int size = 5;
		int[] nodes = new int[]{1,2,3,4,5};
		float[] probability =new float[]{(float) 0.2,(float)0.3,(float)0.2,(float)0.1,(float)0.2};
	 
		//2、根据数组，求出每个元素查找成功时的平均查找长度（次数），存储在schlen数组中。
		int[] schlen = searchLength(size);
	 
		//3、求出每个元素的权值sw，由待查数组nodes和schlen中下标相对的元素相乘得到。
		float[] sw = sumWeight(schlen, probability, size);
	 
		//4、然后根据书中的算法，递归的构造次优查找树
		TreeNode root=new TreeNode();
		secondOptimal(root, nodes, sw, 0, size - 1);
	 
		//5、用前序、中序、层序遍历把次优查找树打印出来看看
		preOrderPrint(root);
		inOrderPrint(root);
		levelOrderPrint(root);
	}
	
	private static void levelOrderPrint(TreeNode rt)
	{
	    System.out.println("LevelOrderPrint: ");
	    if(null==rt)
	    {
	        System.out.println( "@" );
	        return;
	    }
	    ArrayDeque<TreeNode> q=new ArrayDeque<TreeNode>();
	    q.add(rt);
	    TreeNode cur = null;

	    while(!q.isEmpty())
	    {
	        cur = q.peekFirst();
	        q.pop();
	        if(visit(cur))
	        {
	            if(null!=cur.lchild)
	                q.push(cur.lchild);
	            if(null!=cur.rchild)
	                q.push(cur.rchild);
	        }
	    }
	    System.out.println( "@" );
	}
	private static Boolean visit(TreeNode node)
	{
	    if(null!=node){
	        System.out.println(node.data);
	        return true;
	    }
	    else
	        return false;
	}
	private static void preOrderPrint(TreeNode rt)
	{
	    System.out.println("preOrderPrint: ");
	    if(null==rt)
	        return;
	    Stack<TreeNode> s=new Stack<TreeNode>();
	    s.push(rt);
	    while(!s.empty())
	    {
	    	TreeNode cur = s.pop();
	        System.out.println(cur.data);
	        if(null!=cur.rchild)
	            s.push(cur.rchild);
	        if(null!=cur.lchild)
	            s.push(cur.lchild);
	    }
	    System.out.println( '@');
	}
	
	private static void inOrderPrint(TreeNode rt)
	{
	    System.out.println("InOrderPrint: ");
	    if(null==rt)
	        return;
	    Stack<TreeNode> s = new Stack<TreeNode>();
	    TreeNode cur = rt;

	    while(!s.empty() || cur != null)
	    {
	        while(null!=cur)
	        {
	            s.push(cur);
	            cur = cur.lchild;
	        }
	        if(!s.empty())
	        {
	            cur = s.pop();
	            System.out.println(cur.data);
	            cur = cur.rchild;
	        }
	    }
	    System.out.println("@");
	}

	
	private static void secondOptimal(TreeNode rt, int[] nodes, float[] sw, int low, int high)
	{
		if(null==nodes || null==sw || low < 0 || low > high)
			return;
		int i = low;
		float min = Math.abs(sw[high] - sw[low]);
		float dw = sw[high]; 
		for(int j = low + 1; j <= high; ++j)
		{
			float tmp = Math.abs(dw - sw[j] - sw[j-1]);
			if(tmp < min)
			{
				i = j;
				min = tmp;
			}
		}
		rt.data = nodes[i];
		if(i == low)
			rt.lchild = null;
		else
			rt.lchild=new TreeNode();
			secondOptimal(rt.lchild, nodes, sw, low, i-1);
		if(i == high)
			rt.rchild = null;
		else
			rt.rchild=new TreeNode();
			secondOptimal(rt.rchild, nodes, sw, i+1, high);
	}
	
	 
	private static float[] sumWeight(int[] nodes, float[] prob, int size)
	{
		float[] sw =new float[size];

		float before = (float)0.0;
		for(int i = 0; i < size; i++)
		{
			sw[i] = nodes[i] * prob[i] + before;
			before = sw[i];
		}
		return sw;
	}
	
	private static int[] searchLength(int len)
	{
		int[] factory=new int[len];
		
		if(len <= 0)
			return  null;
		assignVal(factory,0, len-1, 1);
		return factory;
	}
	 
	private static void assignVal(int[] factory,int low, int high, int factor)
	{
		if(low < 0 || low > high)
			return;
		if(low == high)
		{
			factory[low] = factor;
			return;
		}
		int mid = (low + high) / 2;
		factory[mid] = factor;
		assignVal(factory, low, mid-1, factor+1);
		assignVal(factory, mid+1, high, factor+1);
	}
}
