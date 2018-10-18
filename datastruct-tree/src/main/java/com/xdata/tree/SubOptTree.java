package com.xdata.tree;

import com.xdata.tree.base.TreeNode;

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
		TreeNode root;
		secondOptimal(root, nodes, sw, 0, size - 1);
	 
		//5、用前序、中序、层序遍历把次优查找树打印出来看看
		//PreOrderPrint(root);
		//InOrderPrint(root);
		//LevelOrderPrint(root);
	}
	
	private void secondOptimal(TreeNode rt, int[] nodes, float[] sw, int low, int high)
	{
		if(null!=nodes || null!=sw || low < 0 || low > high)
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
			rt->lchild = NULL;
		else
			SecondOptimal(rt->lchild, nodes, sw, low, i-1);
		if(i == high)
			rt->rchild = NULL;
		else
			SecondOptimal(rt->rchild, nodes, sw, i+1, high);
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
