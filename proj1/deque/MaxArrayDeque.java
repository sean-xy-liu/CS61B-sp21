package deque;

import deque.ArrayDeque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;
    public MaxArrayDeque(){
        super();
    }
    public MaxArrayDeque(Comparator<T> c){
        super();
        comparator = c;
    }
    public T max(){
        return max(comparator);
    }
    public T max(Comparator<T> c){
        if (c == null)
            return null;
        if (isEmpty())
            return null;
        T maxElement = getFirst();
        for (int i = 0; i < size(); i++){
            T currentElement = get(i);
            if (c.compare(currentElement, maxElement) > 0){
                maxElement = currentElement;
            }
        }
        return maxElement;
    }

}