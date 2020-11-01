package com.xdata.algorithmic;

import com.xdata.Bag;
import com.xdata.digraph.DiGraph;

import java.util.Stack;

public class DirectedDFS {

    private boolean[] marked;

    private int[] edgeTo;

    public DirectedDFS(DiGraph G, int s) {
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public DirectedDFS(DiGraph G, Bag<Integer> sources) {
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        for (Integer s : sources) {
            if (!marked[s]) {
                dfs(G, s);
            }
        }
    }

    private void dfs(DiGraph G, int v) {
        marked[v] = true;
        for (Integer w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[v] = w;
                dfs(G, w);
            }
        }
    }

    public Stack<Integer> pathTo(int s, int e) {
        if (!marked(e)) {
            return null;
        }
        Stack<Integer> path = new Stack<Integer>();
        for (int x = e; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public boolean marked(int v) {
        return marked[v];
    }


    public static void main(String[] args) {
        DiGraph G = new DiGraph();
        Bag<Integer> sources = new Bag<Integer>();
        sources.add(0);
        DirectedDFS reachable = new DirectedDFS(G, sources);
        for (int i = 0; i < G.V(); i++) {
            if (reachable.marked(i)) {
                Stack<Integer> stack = reachable.pathTo(0, i);
                Integer length = stack.size();
                for (int k = 0; k < length; k++) {
                    System.out.print("->" + stack.pop());
                }
                System.out.println();
            }
        }

    }
}
