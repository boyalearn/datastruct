package com.xdata.stack;
/**
 * ջ����
 * 
 * ������ֻ��Ƴ�ջ����ջ����
 * 
 * top ָ��ջ��Ԫ��
 * capacity ��ǰջ����
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
