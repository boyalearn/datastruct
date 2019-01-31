package com.xdata.digraph.struct;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.xdata.basestruct.Bag;

/**
 * 有向图。在无向图的基础上取消反向边。
 * @author zouhuixing
 *
 */
public class Digraph {
	
    private int E;
	
	private final int V;
	
	private Bag<Integer>[] adj;
	
	
	
	@SuppressWarnings("unchecked")
	public Digraph(int V){
		this.V=V;this.E=0;
		adj=(Bag<Integer>[])new Bag[V];
		for(int v=0;v<V;v++){
			adj[v]=new Bag<Integer>();
		}
	}
	
	public Digraph(){
		this(In.readInt());
		int E=In.readInt();
		for(int i=0;i<E;i++){
			String[] line=In.readStr().split("[ ]");
			int v=Integer.valueOf(line[0]);
			int w=Integer.valueOf(line[1]);
			addEdge( v , w );
		}
	}
	
	public Digraph reverse(){
		Digraph R=new Digraph(V);
		for(int v=0;v<V;v++){
			Bag<Integer> data=adj(v);
			for(Bag<Integer> d:data){
				R.addEdge(d.getData(),v);
			}
		}
		return R;
	}
	
	public void addEdge(int v,int w){
		adj[v].add(w);
		E++;
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public Bag<Integer> adj(int v){
		return adj[v];
	}
	
	
	
	private static class In{

		private static BufferedReader bufr = null;  
		
		static{
			try {
				String path=In.class.getResource("").getPath()+"..//..//..//..//t03.txt";
				FileReader fr = new FileReader(path);
				bufr = new BufferedReader(fr);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static String readStr(){
			try {
				return bufr.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
		}
		public static Integer readInt(){
			try {
				return Integer.valueOf(bufr.readLine());
			} catch (IOException e) {
				e.printStackTrace();
				return 0;
			}
		}
	}

}
