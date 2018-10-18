package com.xdata.algorithmic;

public class Server implements Resource{
	
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
	public int getHashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return "Server [url=" + url + ", ip=" + ip + ", name=" + name + "]";
	}
	
	
	
}
