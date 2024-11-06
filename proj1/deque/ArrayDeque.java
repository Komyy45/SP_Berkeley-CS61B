package deque;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayDeque<T> implements IDeque<T>, Iterable<T> {
    private T[] items;
    private int head;
    private int tail = -1;
    private int capacity = 0;

    public ArrayDeque()
    {
        items = (T[])new Object[8];
        head = 8;
    }
    public ArrayDeque(int size)
    {
        items = (T[]) new Object[size];
        head = size;
    }

    @Override
    public int size()
    {
        return this.capacity;
    }

    @Override
    public T get(int index)
    {
        if(index >= capacity || index < 0) throw new IndexOutOfBoundsException();
        return items[(head + index) % items.length];
    }

    private void reisze(int newSize)
    {
        if(newSize > Integer.MAX_VALUE-2) return;

        T[] newArray = (T[])new Object[newSize];
        System.arraycopy(items, head, newArray, newArray.length - (items.length - head), items.length - head);
        System.arraycopy(items, 0, newArray, 0,  tail + 1);
        this.head += items.length;
        items = newArray;
    }

    @Override
    public void addFirst(T item)
    {
        if(capacity >= items.length)
            reisze(capacity * 2);

        head--;
        items[head % items.length] = item;
        capacity++;
    }

    @Override
    public void addLast(T item) {
        if(capacity >= items.length)
            reisze(items.length * 2);
        tail++;
        items[tail] = item;
        capacity++;
    }
    @Override
    public T removeFirst()
    {
        if(capacity <= 0) return null;
        T deletedItem = items[head % items.length];
        items[head % items.length] = null;
        head++;
        capacity--;
        return deletedItem;
    }

    @Override
    public T removeLast() {
        if(capacity <= 0) return null;
        int index = tail >= 0 ? tail : items.length + tail;
        T deletedItem = items[index];
        items[index] = null;
        tail--;
        capacity--;
        return deletedItem;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T>
    {
        int curDex = 0;
        int curElement = head;
        @Override
        public boolean hasNext() {
            return curDex < capacity;
        }

        @Override
        public T next() {
            curDex++;
            curElement++;
            return items[curElement % items.length];
        }
    }
}
