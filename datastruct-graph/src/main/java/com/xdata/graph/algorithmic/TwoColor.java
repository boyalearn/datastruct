package com.xdata.graph.algorithmic;

import com.xdata.Bag;
import com.xdata.graph.Graph;

/**
 * @author zouhuixing
 * @desc 采用深度优先的遍历方法。以一个订点出发。用boolean数组标记颜色。
 * 每当遇到一个非标记的相邻节点标记为对立的颜色（true/false）标记
 * 每当遇到一个标记的节点判断颜色是否相等。如果相等就是非双色图。
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
