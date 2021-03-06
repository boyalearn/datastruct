package com.xdata.algorithmic;

import java.util.UUID;

import org.junit.Test;

import com.xdata.consisithash.ConsistHash;
import com.xdata.consisithash.RBConsistHash;

public class ConsistHashTest {
	@Test
	public void test(){
		Server server1=new Server("1.1.1.1","server1");
		Server server2=new Server("2.2.2.2","server2");
		Server server3=new Server("3.3.3.3","server3");
		Server server4=new Server("4.4.4.4","server4");
		
		ConsistHash client=new RBConsistHash(100);
		client.addRes(server1);
		client.addRes(server2);
		client.addRes(server3);
		client.addRes(server4);
		int countS1=0;
		int countS2=0;
		int countS3=0;
		int countS4=0;
		for(int i=0;i<10000000;i++){
			UUID uuid=UUID.randomUUID();
			int uuidHashCode=uuid.hashCode();
			Server curr=(Server)client.getRes(uuidHashCode);
			//System.out.println(curr);
			if(server1==curr){
				countS1++;
			}
			if(server2==curr){
				countS2++;
			}
			if(server3==curr){
				countS3++;
			}
			if(server4==curr){
				countS4++;
			}
			
		}
		System.out.println(countS1);
		System.out.println(countS2);
		System.out.println(countS3);
		System.out.println(countS4);
		System.out.println(countS1+countS2+countS3+countS4);
	}
	//@Test
	public void testInt(){
		int i=2147483639;
		System.out.println(i+Integer.MAX_VALUE);
	}
	
	
}
