package com.xdata.graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class SymbolGraph {
	private Map<String,Integer> st;
	private String[] keys;
	private Graph G;
	
	public SymbolGraph(){
		st=new HashMap<String,Integer>();
		String lineOne="";
		In inone=new In();
		while(!StringUtil.isEmpty(lineOne=inone.readStr())){
			String[] a=lineOne.split("[ ]");
			for(int i=0;i<a.length;i++){
				if(!st.containsKey(a[i])){
					st.put(a[i], st.size());
				}
			}
		}
		try {
			inone.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		keys=new String[st.size()];
		for(String name:st.keySet()){
			keys[st.get(name)]=name;
		}
		G=new Graph(st.size());
		In intwo=new In();
		String lineTwo="";
		while(!StringUtil.isEmpty(lineTwo=intwo.readStr())){
			String[] a=lineTwo.split("[ ]");
			int v=st.get(a[0]);
			for(int i=0;i<a.length;i++){
				G.addEdge(v, st.get(a[i]));
			}
		}
	}
	
	public boolean contains(String s){
		return st.containsKey(s);
	}
	
	public int index(String s){
		return st.get(s);
	}
	
	public String name(int v){
		return keys[v];
	}
	
	public Graph G(){
		return G;
	}
	
	private class In{

		private BufferedReader bufr = null;
		
		private FileReader fr;
		
		public In(){
			try {
				String path=In.class.getResource("").getPath()+"..//..//..//t02.txt";
				fr = new FileReader(path);
				bufr = new BufferedReader(fr);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		public String readStr(){
			try {
				return bufr.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
		}

		@SuppressWarnings("unused")
		public Integer readInt(){
			try {
				return Integer.valueOf(bufr.readLine());
			} catch (IOException e) {
				e.printStackTrace();
				return 0;
			}
		}
		public void close() throws IOException{
			if(null!=fr){
				fr.close();
			}
			if(null!=bufr){
				bufr.close();
			}
		}
	}
	
}
