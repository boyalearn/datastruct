package com.xdata.tree;

import com.xdata.tree.base.TreeNode;

public class SubOptTree {
	public static void main(String[] args) {
		int size = 5;
		int[] nodes = new int[]{1,2,3,4,5};
		float[] probability =new float[]{(float) 0.2,(float)0.3,(float)0.2,(float)0.1,(float)0.2};
	 
		//2���������飬���ÿ��Ԫ�ز��ҳɹ�ʱ��ƽ�����ҳ��ȣ����������洢��schlen�����С�
		int[] schlen = searchLength(size);
	 
		//3�����ÿ��Ԫ�ص�Ȩֵsw���ɴ�������nodes��schlen���±���Ե�Ԫ����˵õ���
		float[] sw = sumWeight(schlen, probability, size);
	 
		//4��Ȼ��������е��㷨���ݹ�Ĺ�����Ų�����
		TreeNode root;
		secondOptimal(root, nodes, sw, 0, size - 1);
	 
		//5����ǰ�����򡢲�������Ѵ��Ų�������ӡ��������
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
