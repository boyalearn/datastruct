package com.xdata.digraph.algorithmic;

import com.xdata.digraph.DiGraph;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class TopologicalSort {
    private Queue<Integer> queue = new LinkedBlockingQueue<>();

    TopologicalSort(DiGraph diGraph) {
        DiGraph graph=diGraph.reverse();
        topological(graph);
    }

    private void topological(DiGraph graph){
        for (int i = 0; i < graph.V(); i++) {
            if(graph.adj(i).size()==0){
                queue.add(i);
                for (int k = 0; k < graph.V(); k++){
                    graph.adj(k).remove(i);
                }
                topological(graph);
            }
        }
    }

}
