package com.xdata.algorithmic;

import com.xdata.consisithash.Res;

public class Server implements Res{
	
	private String url;
	
	private String ip;
	
	private String name;
	
	public Server(String ip,String name){
		this.ip=ip;
		this.name=name;
	}
	
	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getIp() {
		return ip;
	}



	public void setIp(String ip) {
		this.ip = ip;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Server [url=" + url + ", ip=" + ip + ", name=" + name + "]";
	}

	@Override
	public int hashCode(Object info) {
		String str=this.toString()+info;
		final int p = 16777619;
		int hash = (int)2166136261L;
		for (int i = 0; i < str.length(); i++)
		hash = (hash ^ str.charAt(i)) * p;
		hash += hash << 13;
		hash ^= hash >> 7;
		hash += hash << 3;
		hash ^= hash >> 17;
		hash += hash << 5;
		// 如果算出来的值为负数则取其绝对值
		if (hash < 0)
		    hash = Math.abs(hash);
		return hash;
		
	}
	
	
	
}
