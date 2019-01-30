package com.xdata.digraph.struct;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


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
			addEdge( w , v );
		}
	}
	
	public Digraph reverse(){
		Digraph R=new Digraph(V);
		for(int v=0;v<V;v++){
			Digraph.Bag<Integer> data=adj(v);
			while(null!=data.getData()){
				R.addEdge(data.getData(),v);
				data=data.getNext();
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
	
	public static class Bag<T>{
		
		private T data;
		private Bag<T> next;
		
		public Bag(T data){
			this.data=data;
			this.next=new Bag<T>();
		}
		
		public Bag() {
		}

		public void add(T node){
			Bag<T> curr=this;
			if(null==curr.data){
				curr.data=node;
				curr.next=new Bag<T>();
				return;
			}
			while(null!=curr.data){
				curr=curr.next;
			}
			curr.data=node;
			curr.next=new Bag<T>();
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Bag<T> getNext() {
			return next;
		}

		public void setNext(Bag<T> next) {
			this.next = next;
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
