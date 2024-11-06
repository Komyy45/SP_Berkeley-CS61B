package deque;

public class LinkedListDeque<T> implements IDeque<T> {

    private static class ListNode<T>
    {
        public T item;
        public ListNode<T> prev;
        public ListNode<T> next;

        public ListNode(T _item)
        {
            item = _item;
            prev = null;
            next = null;
        }
        public ListNode(T _item, ListNode<T> _next)
        {
            item = _item;
            next = _next;
        }
        public ListNode(T _item, ListNode<T> _prev, ListNode<T> _next)
        {
            item = _item;
            prev = _prev;
            next = _next;
        }
    }

    private int capacity;
    private final ListNode<T> sentinel = new ListNode<>(null);

    public LinkedListDeque(T initialItem)
    {
        sentinel.next = sentinel.prev = new ListNode<>(initialItem, sentinel, sentinel);
        capacity = 0;
    }
    public LinkedListDeque()
    {
        sentinel.next = sentinel.prev = sentinel;
        capacity = 0;
    }
    @Override
    public int size()
    {
        return capacity;
    }

    @Override
    public void addFirst(T item)
    {
        ListNode<T> nodeAfterTheNewOnes = sentinel.next;
        ListNode<T> newNode = new ListNode<>(item, sentinel, nodeAfterTheNewOnes);
        nodeAfterTheNewOnes.prev = newNode;
        sentinel.next = newNode;
        capacity++;
    }

    @Override
    public void addLast(T item)
    {
        ListNode<T> nodeBeforeTheNewOnes = sentinel.prev;
        ListNode<T> newNode = new ListNode<>(item, nodeBeforeTheNewOnes, sentinel);
        nodeBeforeTheNewOnes.next = newNode;
        sentinel.prev = newNode;
        capacity++;
    }

    @Override
    public T removeFirst()
    {
        if(capacity == 0) return null;
        ListNode<T> firstNode = sentinel.next;
        sentinel.next = firstNode.next;
        firstNode.next.prev = sentinel;
        capacity--;
        return firstNode.item;
    }

    @Override
    public T removeLast()
    {
        if(capacity == 0) return null;
        ListNode<T> lastNode = sentinel.prev;
        sentinel.prev = lastNode.prev;
        lastNode.prev.next = sentinel;
        capacity--;
        return lastNode.item;
    }

    @Override
    public T get(int index)
    {
        if(!checkForIndex(index)) throw new IndexOutOfBoundsException();
        ListNode<T> head = sentinel;
        while(index > 0)
        {
            head = head.next;
            index--;
        }
        return head.item;
    }

    public T getRecursive(int index)
    {
        if(!checkForIndex(index)) throw new IndexOutOfBoundsException();
        return iterateRecursively(sentinel.next, index);
    }

    private T iterateRecursively(ListNode<T> node, int index)
    {
        if(index <= 0) return node.item;
        return iterateRecursively(node.next, index-1);
    }

    private boolean checkForIndex(int index) {
        return index >= 0 && index < capacity;
    }

    public boolean isEmpty(){
        return capacity == 0;
    }

    public void printDeque()
    {
        for(ListNode<T> i = sentinel.next; i != sentinel; i = i.next)
        {
            System.out.print(i.item + " ");
        }
        System.out.println();
    }
}
