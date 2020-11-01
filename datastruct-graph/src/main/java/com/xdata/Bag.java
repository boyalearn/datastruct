package com.xdata;

import java.util.Iterator;

/**
 * 本质是一个链表
 *
 * @param <T>
 */
public class Bag<T> implements Iterable<T> {

    private T data;

    private Bag<T> next;

    private Bag<T> curr;

    public Bag(T data) {
        this.data = data;
    }

    public Bag() {
    }

    public void add(T node) {
        if (null == curr) {
            this.data = node;
            curr = this;
        } else {
            curr.next = new Bag<T>(node);
            curr = curr.next;
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
    public Iterator<T> iterator() {
        return new BagIterable(this);
    }

    public class BagIterable implements Iterator<T> {

        private Bag<T> curr;

        public BagIterable(Bag<T> bag) {
            curr = bag;
        }

        @Override
        public boolean hasNext() {
            return null != curr && null != curr.data;
        }

        @Override
        public T next() {
            T data = curr.data;
            curr = curr.next;
            return data;
        }

    }


}
