package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        T item;
        Node next;
        Node prev;
        Node(T item, Node next) {
            this.item = item;
            doubleLink(next);
        }
        Node() {
            this.item = (T) new Object();
            this.next = null;
            this.prev = null;
        }
        Node(T item) {
            this.item = item;
            this.next = null;
            this.prev = null;
        }

        /**
         * Set current node's next node to node other,
         * and node other's prev node to current node.
         * @param other
         */
        void doubleLink(Node other) {
            next = other;
            if (other != null) {
                other.prev = this;
            }
        }
        void reverseDoubleLink(Node other) {
            prev = other;
            if (other != null) {
                other.next = this;
            }
        }

        /**
         * Bridge 2 nodes, front node's next point to current node,
         * current node's next point to back node.
         * So is the the prev pointer.
         * If front and back are the same, forms a circular list with the node.
         * @param front
         * @param back
         */
        void bridgeNodes(Node front, Node back) {
            doubleLink(back);
            reverseDoubleLink(front);
        }
    }
    private Node first;
    private Node last;
    private Node sentinel = new Node();
    private int size;
    public LinkedListDeque() {
        size = 0;
    }
    /**
     * Helper function for addFirst and addLast.
     * If add an item to an empty list, both first and last should be updated.
     * @param item
     */
    private void addToEmpty(T item) {
        Node p = new Node(item);
        p.bridgeNodes(sentinel, sentinel);
        updateFirst();
        updateLast();
        size = 1;
    }

    private void updateFirst() {
        first = sentinel.next;
    }
    private void updateLast() {
        last = sentinel.prev;
    }


    /**
     * Adds an item of type T to the front of the deque.
     * @param item
     */
    @Override
    public void addFirst(T item) {
        if (isEmpty()) {
            addToEmpty(item);
        } else {
            Node p = new Node(item);
            p.bridgeNodes(sentinel, first);
            updateEdge();
            size++;
        }
    }
    private void updateEdge() {
        updateLast();
        updateFirst();
    }

    /**
     *  Adds an item of type T to the back of the deque.
     * @param item
     */
    @Override
    public void addLast(T item) {
        if (isEmpty()) {
            addToEmpty(item);
        } else {
            Node p = new Node(item);
            p.bridgeNodes(last, sentinel);
            updateEdge();
            size++;
        }
    }


    /**
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    @Override
    public void printDeque() {
        Node p = first;
        for (int i = 0; i < size; i++) {
            System.out.println(p.item);
            p = p.next;
        }
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     * @return the item at the front of the deque
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (size == 1) {
            return removeOnlyItem();
        }
        T itemRemoved = first.item;
        sentinel.doubleLink(first.next);
        updateEdge();
        size--;
        return itemRemoved;
    }

    /**
     * Handle case that there is only item in the list, and you want to remove it.
     * In this case, both fist and last should be updated.
     *  Return the removed item. If there is more than 1 item, return null.
     * @return
     */
    private T removeOnlyItem() {
        if (size != 1) {
            return null;
        }
        T itemRemoved = first.item;
        sentinel.bridgeNodes(null, null);
        updateFirst();
        updateLast();
        size = 0;
        return itemRemoved;
    }
    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     * @return the item at the front of the deque
     */
    @Override
    public T removeLast() {
        if (size <= 1) {
            return removeFirst();
        }
        T itemRemoved = last.item;
        last.prev.doubleLink(sentinel);
        updateEdge();
        size--;
        return itemRemoved;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node p = first;
        while (index != 0) {
            p = p.next;
            index--;
        }
        return p.item;
    }
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(index, first);
    }
    private T getRecursiveHelper(int index, Node p) {
        if (index == 0) {
            return p.item;
        }
        return getRecursiveHelper(index - 1, p.next);
    }
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (other.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!other.get(i).equals(get(i))) {
                return false;
            }
        }
        return true;
    }
    private class LinkedListDequeIterator implements Iterator<T> {
        int pos = 0;
        @Override
        public boolean hasNext() {
            return pos < size();
        }

        @Override
        public T next() {
            T returnItem = get(pos);
            pos++;
            return returnItem;
        }
    }
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

}
