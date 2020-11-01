package com.xdata.algorithmic;

import com.xdata.digraph.DiGraph;

/**
 * @author zouhuixing
 * <p>
 * ǿ��ͨ�����㷨��
 * <p>
 * �㷨������  TODO
 */
public class KosarajuSCC implements SCC {

    private boolean[] marked;         //�Ѿ����ʹ��Ķ���

    private int[] id;                 //ǿ��ͨ������ʶ��

    private int count;                //ǿ��ͨ����������

    public KosarajuSCC(DiGraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for (int s : order.reversePost()) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(DiGraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (Integer w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }


    @Override
    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public int id(int v) {
        return id[v];
    }

    public static void main(String[] args) {
        DiGraph G = new DiGraph();
        KosarajuSCC kosarajuSCC = new KosarajuSCC(G);
        int c = kosarajuSCC.count();
        System.out.println(c);
    }

}
