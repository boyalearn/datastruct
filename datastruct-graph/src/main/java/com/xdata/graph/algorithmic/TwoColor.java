package com.xdata.graph.algorithmic;

import com.xdata.Bag;
import com.xdata.graph.Graph;

/**
 * @author zouhuixing
 * @desc ����������ȵı�����������һ�������������boolean��������ɫ��
 * ÿ������һ���Ǳ�ǵ����ڽڵ���Ϊ��������ɫ��true/false�����
 * ÿ������һ����ǵĽڵ��ж���ɫ�Ƿ���ȡ������Ⱦ��Ƿ�˫ɫͼ��
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s);
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        Bag<Integer> adj = G.adj(v);
        for (Integer data : adj) {
            if (!marked[data]) {
                color[data] = !color[v];
                dfs(G, data);
            } else if (color[data] == color[v]) {
                isTwoColorable = false;
            }
        }
    }

    public boolean isBipartite() {
        return isTwoColorable;
    }
}
