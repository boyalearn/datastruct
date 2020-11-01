package com.xdata.graph.algorithmic;

import com.xdata.Bag;
import com.xdata.graph.Graph;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 广度优先的搜索方式
 */
public class SearchPathBreadthFirst {
    /**
     * 是否被标记
     */
    private boolean[] marked;

    /**
     * 前驱数组
     */
    private int[] edgeTo;

    /**
     * 起点
     */
    public int s;

    public SearchPathBreadthFirst(Graph G, int s) {
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new LinkedBlockingQueue<>();
        marked[s] = true;
        queue.add(s);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            Bag<Integer> adj = G.adj(v);
            for (Integer data : adj) {
                if (!marked[data]) {
                    edgeTo[data] = v;
                    marked[data] = true;
                    queue.add(data);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

}
