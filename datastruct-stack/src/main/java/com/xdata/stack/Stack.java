package com.xdata.stack;
/**
 * 栈类型
 * 
 * 该类型只设计出栈与入栈操作
 * 
 * top 指向栈顶元素
 * capacity 当前栈容量
 * 
 * @author zouhuixing
 *
 * @param <T>
 */
public class Stack<T> {
	
	private T[] table;
	
	private int top=0;
	
	private int capacity=16;
	
	public Stack(){
		this(16);
	}
    @SuppressWarnings("unchecked")
	public Stack(int capacity){
    	this.top=-1;
    	this.capacity=capacity;
		this.table=(T[])new Object[capacity];
	}

    public T pop(){
    	if(this.top<0){
    		return null;
    	}
    	T e=this.table[this.top];
    	this.top--;
    	return e;
    }
    
    public void push(T e){
    	ensureCapacity();
    	this.top++;
    	this.table[this.top]=e;
    }
    
    @SuppressWarnings("unchecked")
	private void ensureCapacity(){
    	if(this.top>=this.capacity-1){
    		T[] newTable=(T[])new Object[capacity*2];
    		for(int i=0;i<=this.top;i++){
    			newTable[i]=this.table[i];
    		}
    		this.table=newTable;
    	}
    }
}
