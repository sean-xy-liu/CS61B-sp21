package deque;
public class ArrayDeque<T> {
  public static void main(String[] args) {
    ArrayDeque<String> al = new ArrayDeque<String>();
    for (int i=0 ; i < 2; i++){
      al.addLast("JoJo");
    }
    for (int i=0 ; i < 20000; i++){
      al.addLast("Get Back");
    }
    al.show();
    al.clear();
    al.addLast("Let it be.");
    al.show();
  }
  public void clear(){
    while (!isEmpty()){
      removeLast();
    }
  }
  public void show(){
    getLength();
  };
  private int getLength(){
    System.out.println("First index: " + firstIndex);
    System.out.println("Last index: " + lastIndex);
    System.out.println("Length of items: " + items.length);
    System.out.println("Size: " + size);
    return items.length;
  }
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
    firstIndex = initialFistIndex;
    updateLastIndex();
  }
  private int getInnerIndex(int index){
    return (index + firstIndex) % items.length;
  }
  private int increaseIndex(int index){
    return (++index) % items.length;
  }
  private int decreaseIndex(int index){
    return (--index + items.length) % items.length;
  }
  private void addToEmpty(T str){
    items[firstIndex] = str;
    size = 1;
  }
  private int length(){
    return items.length;
  }
  public boolean resize(int length){
    if (length <= size()){
      return false;
    }
    T[] newItems = (T[]) new Object[length];
    if (!hasCirculated())
      System.arraycopy(items, firstIndex, newItems, firstIndex, size());
    else{
      int size1 = length() - firstIndex;
      int size2 = size() - size1;
      System.arraycopy(items, firstIndex, newItems, firstIndex, size1);
      System.arraycopy(items, 0, newItems, length(), size2);
    }
    items = newItems;
    updateLastIndex();
    return true;
  }
  private boolean hasCirculated(){
    return firstIndex > lastIndex;
  }
  public void resize(){
    double rateUpperBound = 3.0 / 4;
    double rateLowerBound = 1.0 / 4;
    double currentRate = (double) size / items.length;
    if (currentRate > rateUpperBound){
      resize(items.length * 2);
    }
    else if (currentRate < rateLowerBound &&
             items.length > initialItemLength){
      resize(items.length / 2);
    }
  }
  private void updateLastIndex(){
    if (isEmpty())
      lastIndex = firstIndex;
    else
      lastIndex = getInnerIndex(size() - 1);
  }
  public void printDeque(){
    for (int i = 0; i < size; i++){
      System.out.println(get(i));
    }

  }

  /** Inserts X into the back of the list. */
  public void addLast(T str) {
    if (isEmpty())
      addToEmpty(str);
    else {
      lastIndex = increaseIndex(lastIndex);
      items[lastIndex] = str;
      size++;
      resize();
    }
  }
  public void addFirst(T str) {
    if (isEmpty())
      addToEmpty(str);
    else {
      firstIndex = decreaseIndex(firstIndex);
      items[firstIndex] = str;
      size++;
      resize();
    }
  }

  /** Returns the item from the back of the list. */
  public T getLast() {
    if (isEmpty())
      return null;
    return items[lastIndex];
  }
  public T getFirst() {
    if (isEmpty())
      return null;
    return items[firstIndex];
  }
  public boolean isEmpty(){
    return size == 0;
  }
  /** Gets the ith item in the list (0 is the front). */
  public T get(int i) {
    if (i >= size)
      return null;
    return items[getInnerIndex(i)];
  }

  /** Returns the number of items in the list. */
  public int size() {
    return size;
  }

  /** Deletes item from back of the list and
   * returns deleted item. */
  public T removeLast() {
    if (isEmpty())
      return null;
    if (size == 1)
      return removeOnlyItem();
    T lastItem = items[lastIndex];
    items[lastIndex] = null;
    lastIndex = decreaseIndex(lastIndex);
    size--;
    resize();
    return lastItem;
  }
  public T removeFirst() {
    if (isEmpty())
      return null;
    if (size == 1)
      return removeOnlyItem();
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
}
