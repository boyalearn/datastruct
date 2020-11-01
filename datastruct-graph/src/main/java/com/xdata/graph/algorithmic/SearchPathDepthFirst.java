package com.xdata.graph.algorithmic;

import com.xdata.Bag;
import com.xdata.graph.Graph;

import java.util.Stack;

public class SearchPathDepthFirst {
    private boolean[] marked;
    private int[] edgeTo;
    private final int resource;

    public SearchPathDepthFirst(Graph G, int s) {
        this.resource = s;
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        dfs(G, s);
    }

    public void dfs(Graph G, int v) {
        marked[v] = true;
        Bag<Integer> adj = G.adj(v);
        for (Integer data : adj) {
            if (!marked[data]) {
                edgeTo[data] = v;
                dfs(G, data);
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
        for (int x = v; x != resource; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(resource);
        return path;
    }
}
