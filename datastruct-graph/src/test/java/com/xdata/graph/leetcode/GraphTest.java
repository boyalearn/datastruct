package com.xdata.graph.leetcode;

import java.util.*;

public class GraphTest {
    private boolean hasCycle = false;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer,LinkedList<Integer>> graph =new  HashMap<Integer,LinkedList<Integer>>();
        for(int i=0;i<numCourses;i++){
            LinkedList<Integer> list=new LinkedList<>();
            graph.put(i,list);
        }
        for (int[] item : prerequisites) {
            graph.get(item[0]).add(item[1]);
        }
        topo(graph);
        return graph.isEmpty();

    }

    private void topo(HashMap<Integer,LinkedList<Integer>>  graph) {
        Iterator<Map.Entry<Integer, LinkedList<Integer>>> iterator = graph.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer, LinkedList<Integer>> next = iterator.next();
            if(next.getValue().size()==0){
                graph.remove(next.getKey());
                Iterator<Map.Entry<Integer, LinkedList<Integer>>> iterator2 = graph.entrySet().iterator();
                while (iterator2.hasNext())
                    iterator2.next().getValue().remove(next.getKey());
                topo(graph);
            }
        }

    }

    public static void main(String[] args) {

    }
}
