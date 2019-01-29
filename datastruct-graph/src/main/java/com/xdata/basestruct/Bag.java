package com.xdata.basestruct;

import java.util.Iterator;


public class Bag<T> implements Iterable<Bag<T>>{
	
	private T data;
	
	private Bag<T> next;
	
	private Bag<T> curr;
	
	public Bag(T data){
		this.data=data;
	}
	
	public Bag() {
	}

	public void add(T node){
		if(null==curr){
			this.data=node;
			curr=this;
		}else{
			curr.next=new Bag<T>(node);
			curr=curr.next;
		}
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

	@Override
	public String toString() {
		return String.valueOf(data);
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return new BagIterable(this);
	}
	public class BagIterable implements Iterator<Bag>{
		
		private Bag<T> curr;
		
		public BagIterable(Bag<T> bag){
			curr=bag;
		}

		@Override
		public boolean hasNext() {
			return null!=curr;
		}

		@Override
		public Bag<T> next() {
			Bag<T> data=curr;
			curr=curr.next;
			return data;
		}
		
	}

	
}
