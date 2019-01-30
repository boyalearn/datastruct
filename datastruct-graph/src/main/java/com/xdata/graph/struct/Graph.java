package com.xdata.graph.basestruct;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.xdata.basestruct.Bag;

public class Graph {
	
	//边数
	private int E;
	
	//顶点数
	private final int V;
	
	//临接链表
	private Bag<Integer>[] adj;
	
	
	//构造函数
	@SuppressWarnings("unchecked")
	public Graph(int V){
		this.V=V;this.E=0;
		adj=(Bag<Integer>[])new Bag[V];
		for(int v=0;v<V;v++){
			adj[v]=new Bag<Integer>();
		}
	}
	
	public Graph(){
		this(In.readInt());
		int E=In.readInt();
		for(int i=0;i<E;i++){
			String[] line=In.readStr().split("[ ]");
			int v=Integer.valueOf(line[0]);
			int w=Integer.valueOf(line[1]);
			addEdge( w , v );
		}
	}
	
	public void addEdge(int v,int w){
		adj[v].add(w);
		adj[w].add(v);
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
	
	
	public static void main(String[] args){
		Bag<String> bag=new Bag<String>();
		bag.add("3");
		bag.add("4");
		bag.add("5");
		bag.add("6");
		for(Bag<String> b:bag){
			System.out.println(b);
		}
	}
	
	
	private static class In{

		private static BufferedReader bufr = null;  
		
		static{
			try {
				String path=In.class.getResource("").getPath()+"..//..//..//t01.txt";
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
