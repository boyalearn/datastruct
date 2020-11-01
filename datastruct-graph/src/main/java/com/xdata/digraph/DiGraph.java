package com.xdata.digraph;

import com.xdata.Bag;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 有向图。在无向图的基础上取消反向边。
 *
 * @author zouhuixing
 */
public class DiGraph {

    /**
     * 边的数量
     */
    private int E;

    /**
     * 顶点的数量
     */
    private final int V;


    /**
     * 邻接表
     */
    private Bag<Integer>[] table;


    public DiGraph(int V) {
        this.V = V;
        this.E = 0;
        table = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            table[v] = new Bag<Integer>();
        }
    }

    public DiGraph() {
        this(In.readInt());
        int E = In.readInt();
        for (int i = 0; i < E; i++) {
            String[] line = In.readStr().split("[ ]");
            int v = Integer.valueOf(line[0]);
            int w = Integer.valueOf(line[1]);
            addEdge(v, w);
        }
    }

    public DiGraph reverse() {
        DiGraph R = new DiGraph(V);
        for (int v = 0; v < V; v++) {
            Bag<Integer> data = adj(v);
            for (Integer d : data) {
                R.addEdge(d, v);
            }
        }
        return R;
    }

    public void addEdge(int v, int w) {
        table[v].add(w);
        E++;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Bag<Integer> adj(int v) {
        return table[v];
    }


    private static class In {

        private static BufferedReader bufr = null;

        static {
            try {
                String path = In.class.getResource("").getPath() + "..//..//..//t03.txt";
                FileReader fr = new FileReader(path);
                bufr = new BufferedReader(fr);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public static String readStr() {
            try {
                return bufr.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }

        public static Integer readInt() {
            try {
                return Integer.valueOf(bufr.readLine());
            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

}
