package com.xdata.graph.algorithmic;

import com.xdata.Bag;
import com.xdata.graph.Graph;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class SearchPathBreadthFirst {
    private boolean[] marked;
    private int[] edgeTo;
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
