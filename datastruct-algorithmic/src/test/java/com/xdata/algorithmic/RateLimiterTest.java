package com.xdata.algorithmic;

import org.junit.Test;

import com.xdata.currentlimit.LimiterManager;

public class RateLimiterTest {
	@Test
	public void test(){
		new LimiterManager().start();
	}
	
	public static void main(String[] args){
		LimiterManager manager= new LimiterManager();
		manager.start();
		long startTime=System.currentTimeMillis();
		for(int i=1;i<100000000;){
			if(manager.getRateLimiter().acquire()){
				i++;
				System.out.println("getToken i=" +i);
			}
			
		}
		System.out.println("spend time:"+(System.currentTimeMillis()-startTime));
		
	}
}
