package com.xdata.queue;

public class Queue<T> {
	private T[] table;
	
	private int curr;
	
	private int top;
	
	private boolean isOver;
	
	private int capacity;
	
	@SuppressWarnings("unchecked")
	public Queue(){
		this.capacity=16;
		this.table=(T[])new Object[this.capacity];
		this.top=0;
		this.isOver=false;
		this.curr=0;
	}
	
	public void enqueue(T item) throws Exception{
		if(this.top>this.capacity){
			if(this.curr>0){
				this.isOver=true;
				this.top=0;
				this.table[this.top]=item;
				this.top++;
			}else{
				throw new Exception("not capacity exception");
			}
		}else{
			if(this.top>this.curr){
				throw new Exception("not capacity exception");
			}else{
				this.table[this.top]=item;
				this.top++;
			}	
		}
		
	}
	
	public T dequeue() throws Exception{
		if(this.isOver){
			if(this.curr>this.capacity-1){
				if(this.top>0){
					this.curr=0;
					this.isOver=false;
					T obj=this.table[this.curr];
					this.curr++;
					return obj;
				}else{
					throw new Exception("empty exception");
				}
				
			}else{
				return this.table[this.curr];
			}
		}else{
			if(this.curr>=top){
				throw new Exception("empty exception");
			}else{
				T obj=this.table[this.curr];
				this.curr++;
				return obj;
			}
		}
	}
}
