package com.xdata.digraph.algorithmic;

/**
 * ǿ��ͨ����
 * @author zouhuixing
 * 
 * ����ͼ����������v��w�໥�ɴ�������Ϊǿ��ͨ�ġ�
 * һ��ͼ�У������໥ǿ��ͨ�Ķ��������Ӽ������ǳ���Щ�Ӽ�Ϊǿ��ͨ������
 *
 */
public interface SCC {
	
	boolean stronglyConnected(int v,int w);
	
	int count();
	
	int id(int v);
}
