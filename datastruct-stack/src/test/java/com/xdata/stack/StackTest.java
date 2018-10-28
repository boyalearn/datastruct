package com.xdata.stack;

import org.junit.Test;

public class StackTest {
	@Test
	public void testOne(){
		Stack<Integer> stack=new Stack<Integer>();
		stack.push(1);
		stack.push(1);
		stack.push(1);
		stack.push(1);
		stack.push(2);
		stack.push(2);
		stack.push(2);
		stack.push(2);
		stack.pop();
		stack.push(3);
		stack.push(3);
		stack.push(3);
		stack.push(3);
		stack.push(4);
		stack.push(4);
		stack.push(4);
		stack.push(4);
		stack.push(5);
		Integer e;
		for(;(e=stack.pop())!=null;){
			System.out.println(e);
		}



	}
}
