package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int lastIndex;
    private int firstIndex;
    private static int initialItemLength = 8;
    private static int initialFistIndex = initialItemLength / 2;
    /** Creates an empty list. */
    public ArrayDeque() {
        items =  (T[]) new Object[initialItemLength];
        size = 0;
        updateInnerIndex(initialFistIndex);
    }
    private int getInnerIndex(int index) {
        return (index + firstIndex) % items.length;
    }
    private int increaseIndex(int index) {
        return (++index) % items.length;
    }
    private int decreaseIndex(int index){
        return (--index + items.length) % items.length;
    }
    private void addToEmpty(T str) {
        items[firstIndex] = str;
        size = 1;
    }
    private int length(){
        return items.length;
    }
    public boolean resize(int length) {
        if (length == length()
            && length <= size()) {
            return false;
        }
        int newFirstIndex = 0;
        T[] newItems = (T[]) new Object[length];
        if (hasCirculated()) {
            int size1 = length() - firstIndex;
            int size2 = size() - size1;
            System.arraycopy(items, firstIndex, newItems, newFirstIndex, size1);
            System.arraycopy(items, 0, newItems, newFirstIndex + size1, size2);
        }
        else {
            System.arraycopy(items, firstIndex, newItems, newFirstIndex, size());
        }
        items = newItems;
        updateInnerIndex(newFirstIndex);
        return true;
    }

    private boolean hasCirculated() {
        return firstIndex > lastIndex;
    }
    private void resize() {
        double rateUpperBound = 3.0 / 4;
        double rateLowerBound = 1.0 / 4;
        double currentRate = (double) size / items.length;
        if (currentRate > rateUpperBound) {
            resize(items.length * 2);
        }
        else if (currentRate < rateLowerBound
                 && items.length > initialItemLength) {
            resize(items.length / 2);
        }
    }
    private void updateInnerIndex(int firstIndex) {
        updateFirstIndex(firstIndex);
        updateLastIndex();
    }
    private void updateFirstIndex(int index) {
        firstIndex = index;
    }
    private void updateLastIndex() {
        if (isEmpty()) {
            lastIndex = firstIndex;
        } else {
            lastIndex = getInnerIndex(size() - 1);
        }
    }
    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.println(get(i));
        }

    }

    /** Inserts X into the back of the list. */
    @Override
    public void addLast(T str) {
        if (isEmpty()) {
            addToEmpty(str);
        } else {
            lastIndex = increaseIndex(lastIndex);
            items[lastIndex] = str;
            size++;
            resize();
        }
    }
    @Override
    public void addFirst(T str) {
        if (isEmpty()) {
            addToEmpty(str);
        } else {
            firstIndex = decreaseIndex(firstIndex);
            items[firstIndex] = str;
            size++;
            resize();
        }
    }

    /** Gets the ith item in the list (0 is the front). */
    @Override
    public T get(int i) {
        if (i >= size) {
            return null;
        }
        return items[getInnerIndex(i)];
    }

    /** Returns the number of items in the list. */
    @Override
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (size == 1) {
            return removeOnlyItem();
        }
        T lastItem = items[lastIndex];
        items[lastIndex] = null;
        lastIndex = decreaseIndex(lastIndex);
        size--;
        resize();
        return lastItem;
    }
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (size == 1) {
            return removeOnlyItem();
        }
        T firstItem = items[firstIndex];
        items[firstIndex] = null;
        firstIndex = increaseIndex(firstIndex);
        size--;
        resize();
        return firstItem;
    }

    private T removeOnlyItem() {
        T onlyItem = items[firstIndex];
        items[firstIndex] = null;
        size = 0;
        return onlyItem;
    }
    private class ArrayDequeIterator implements Iterator<T> {
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
        return new ArrayDequeIterator();
    }
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        ArrayDeque<T> other = (ArrayDeque<T>) o;
        if (other.size() != size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }
}
