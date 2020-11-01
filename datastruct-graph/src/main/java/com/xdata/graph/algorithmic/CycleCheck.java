package com.xdata.graph.algorithmic;

import com.xdata.Bag;
import com.xdata.graph.Graph;

/**
 * 图中环的检查
 *
 * @author zouhuixing
 * @Desc 算法描述
 * 循环每一个顶点。采用深度有先算法。每当找到一个被标记的顶点判断是否与起点相等表示有环
 */

public class CycleCheck {
    private boolean[] marked;
    private boolean hasCycle;

    public CycleCheck(Graph G) {
        marked = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s, s);
            }
        }
    }

    private void dfs(Graph G, int v, int u) {
        marked[v] = true;
        Bag<Integer> adj = G.adj(v);
        for (Integer data : adj) {
            if (!marked[data]) {
                dfs(G, data, u);
            } else if (data == u) {
                hasCycle = true;
            }
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }
}
