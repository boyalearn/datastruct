package com.xdata.edgeweightedgraph.algorithmic;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class MinPQ <Key> implements Iterable<Key>{ 
	private Key[] pq; 
	private int n; 
	private Comparator<Key> comparator; 
    public MinPQ(int initCapacity){ 
    	pq = (Key[]) new Object[initCapacity + 1];
    	n = 0; 
    } 
    /*
     * Initializes an empty priority queue
     */
    public MinPQ(){ 
    	this(1); 
    } 
    /*
     * Initializes an empty priority queue with the given initial capacity 
     * using the given comparator
     **/ 
    public MinPQ(int initCapacity, Comparator<Key> comparator){
    	this.comparator = comparator; 
    	pq = (Key[]) new Object[initCapacity + 1]; 
    	n= 0; 
    } 
    /*
     * Initializes an empty priority queue using the given comparator
     **/ 
    public MinPQ(Comparator<Key> comparator){ 
    	this(1,comparator); 
    }
    /*
     * Initializes a priority queue from the array of keys
     **/ 
    public MinPQ(Key[] keys){ 
    	n = keys.length; 
    	pq = (Key[]) new Object[keys.length+1]; 
    	for (int i=0;i<n;i++) 
    		pq[i+1] = keys[i];
        for (int k=n/2;k>=1;k--) 
        	sink(k);
        assert isMinHeap(); 
    } 
    /*
     * Returns true if this priority queue is empty
     **/ 
    public boolean isEmpty(){ 
    	return n==0; 
    } 
    /*
     * Returns the number of keys on this priority queue
     **/
    public int size(){ return n; } 
    /*
     * Returns a smallest key on this priority queue
     **/ 
    public Key min(){ 
		if(isEmpty())
			throw new NoSuchElementException("Priority queue underflow");
		return pq[1]; 
	} 
    /*
     * helper function to double the size of the heap array
     **/
    public void resize(int capacity){
    	assert capacity > n; 
    	Key[] temp = (Key[]) new Object[capacity]; 
    	for (int i = 1;i<=n;i++){ 
    		temp[i] = pq[i]; 
    	} 
    	pq = temp; 
    } 
    /*
     * Adds a new key to this priority queue
     **/ 
    public void insert (Key x){ // double size of array if necessary 
    	if (n == pq.length - 1)
    		resize(2 * pq.length); // add x, and percolate it up to maintain heap invariant 
	    pq[++n] = x; swim(n); 
	    assert isMinHeap(); 
	} 
    /*
     * Removes and returns a smallest key on this priority queue 
     **/
    public Key delMin(){ 
    	if (isEmpty()) 
    		throw new NoSuchElementException("Priority queue underflow"); 
    	exch(1, n); 
    	Key min = pq[n--]; 
    	sink(1); // n = n-1 n+1 = n 
    	pq[n+1] = null; // avoid loitering and help with garbage collection 
    	if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2); 
    	assert isMinHeap(); 
    	return min; 
    } 
    /***************************************************************************
     * Helper functions to restore the heap invariant.
     *************************************************************************
     **/ 
    private void swim(int k){ 
    	while(k>1 && greater(k/2,k)){ 
    		exch(k,k/2); k = k/2; 
    	} 
    }
    private void sink(int k){ 
    	while(2*k <= n){ 
    		int j = 2*k; 
    		if (j < n && greater(j, j+1)) 
    			j++; 
    		if (!greater(k, j)) 
    			break; exch(k, j); k = j; 
    	} 
    } 
    /***************************************************************************
     * Helper functions for compares and swaps.
     ***************************************************************************/ 
	private boolean greater(int i, int j) { 
    	if (comparator == null) { 
    		return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0; 
    	} else { 
    		return comparator.compare(pq[i], pq[j]) > 0; 
    	} 
    } 
    private void exch(int i, int j) { 
    	Key swap = pq[i]; 
    	pq[i] = pq[j]; 
    	pq[j] = swap; 
    } 
    // is pq[1..N] a min heap? 
    private boolean isMinHeap() { 
    	return isMinHeap(1); 
    } 
    // is subtree of pq[1..n] rooted at k a min heap? 
    private boolean isMinHeap(int k) { 
    	if (k > n) return true; 
    	int left = 2*k; 
    	int right = 2*k + 1; 
    	if (left <= n && greater(k, left))
    		return false; 
    	if (right <= n && greater(k, right)) 
    		return false; 
    	return isMinHeap(left) && isMinHeap(right); 
    } 
    /**
     * Returns an iterator that iterates over the keys on this priority queue
     * in ascending order. 
     * @return an iterator that iterates over the keys in ascending order
     **/ 
    public Iterator<Key> iterator() { 
    	return new HeapIterator(); 
    } 
    private class HeapIterator implements Iterator<Key> { // create a new pq 
	    private MinPQ<Key> copy; // add all items to copy of heap // takes linear time since already in heap order so no keys move 
	    public HeapIterator() { 
	    	if (comparator == null) 
	    		copy = new MinPQ<Key>(size()); 
	    	else copy = new MinPQ<Key>(size(), comparator); 
	    	for (int i = 1; i <= n; i++) 
	    		copy.insert(pq[i]); 
	    } 
	    public boolean hasNext() { 
	    	return !copy.isEmpty(); 
	    } 
	    public void remove() { 
	    	throw new UnsupportedOperationException(); 
	    } 
	    public Key next() { 
	    	if (!hasNext()) 
	    		throw new NoSuchElementException(); 
	    	return copy.delMin(); 
	    } 
    } 
}