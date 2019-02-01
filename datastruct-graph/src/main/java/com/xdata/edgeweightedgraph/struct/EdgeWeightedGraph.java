package com.xdata.edgeweightedgraph.struct;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.xdata.basestruct.Bag;

public class EdgeWeightedGraph {
	
	private int V;
	
	private int E;
	
	private Bag<Edge>[] adj;
	
	@SuppressWarnings("unchecked")
	public EdgeWeightedGraph(int V){
		this.V=V;
		this.E=0;
		adj=(Bag<Edge>[])new Bag[V];
		for(int v=0;v<V;v++) {
			adj[v]=new Bag<Edge>();
		}
	}
	
	public EdgeWeightedGraph() {
		this(In.readInt());
		int E=In.readInt();
		for(int i=0;i<E;i++){
			String[] line=In.readStr().split("[ ]");
			int v=Integer.valueOf(line[0]);
			int w=Integer.valueOf(line[1]);
			double weight=Double.valueOf(line[2]);
			Edge e=new Edge(v,w,weight);
			addEdge(e);
		}
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public void addEdge(Edge e){
		int v=e.either(),w=e.other(v);
		adj[v].add(e);
		adj[w].add(e);
	}
	
	public Iterable<Bag<Edge>> adj(int v){
		return adj[v];
	}
	
	public Bag<Edge> edges(){
		Bag<Edge> b=new Bag<Edge>();
		for(int v=0;v<V;v++) {
			for(Bag<Edge> e:adj[v]) {
				if(e.getData().other(v)>v) {
					b.add(e.getData());
				}
			}
		}
		return b;
	}
	
	
	private static class In{

		private static BufferedReader bufr = null;  
		
		static{
			try {
				String path=In.class.getResource("").getPath()+"..//..//..//..//tinyEWG.txt";
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
