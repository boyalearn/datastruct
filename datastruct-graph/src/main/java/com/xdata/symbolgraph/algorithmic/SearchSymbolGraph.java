package com.xdata.symbolgraph.algorithmic;

import com.xdata.Bag;
import com.xdata.graph.Graph;
import com.xdata.graph.algorithmic.SearchPathBreadthFirst;
import com.xdata.symbolgraph.struct.SymbolGraph;

import java.util.Stack;

/**
 * @author zouhuixing
 * <p>
 * ·ûºÅÍ¼Ëã·¨
 */
public class SearchSymbolGraph {

    public void getAdjacent(SymbolGraph sg, String nodeName) {
        Graph G = sg.G();
        Bag<Integer> adj = G.adj(sg.index(nodeName));
        for (Integer data : adj) {
            System.out.println(nodeName + "->" + sg.name(data));
        }
    }

    public void findPath(SymbolGraph sg, String source, String end) {
        Graph G = sg.G();
        if (!sg.contains(source)) {
            System.out.println(source + " not in database.");
            return;
        }
        int s = sg.index(source);
        SearchPathBreadthFirst bfs = new SearchPathBreadthFirst(G, s);
        int e = sg.index(end);
        if (bfs.hasPathTo(e)) {
            Stack<Integer> stack = (Stack<Integer>) bfs.pathTo(e);
            int length = stack.size();
            for (int i = 0; i < length; i++) {
                System.out.print("->" + sg.name(stack.pop()));
            }
        } else {
            System.out.println("Not connected");
        }
    }
}
