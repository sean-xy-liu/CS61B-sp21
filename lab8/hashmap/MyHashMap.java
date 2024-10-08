package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().

 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private HashSet<K> hashSet;
    private double maxLoad;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        buckets = createTable(16);
        hashSet = new HashSet<>();
        maxLoad = 0.75;
    }

    public MyHashMap(int initialSize) {
        buckets = createTable(initialSize);
        hashSet = new HashSet<>();
        maxLoad = 0.75;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        buckets = createTable(initialSize);
        hashSet = new HashSet<>();
        this.maxLoad = maxLoad;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new ArrayList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] buckets = new Collection[tableSize];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
        return buckets;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    @Override
    public void clear() {
        buckets = createTable(buckets.length);
        hashSet = new HashSet<>();
    }

    @Override
    public boolean containsKey(K key) {
        return hashSet.contains(key);
    }

    @Override
    public V get(K key) {
        if (containsKey(key)) {
            return getNode(key).value;
        }
        return null;
    }

    @Override
    public int size() {
        return hashSet.size();
    }

    private double loadFactor() {
        return (double) hashSet.size() / (double) buckets.length;
    }
    private void resize() {
        int newSize = buckets.length * 2;
        Collection[] newBuckets = createTable(newSize);
        for (K k : hashSet) {
            newBuckets[getBucketIndex(k, newSize)].add(getNode(k));
        }
        buckets = newBuckets;
    }
    @Override
    public void put(K key, V value) {
        if (containsKey(key)) {
            getNode(key).value = value;
        } else {
            Collection<Node> bucket = buckets[getBucketIndex(key)];
            bucket.add(createNode(key, value));
            hashSet.add(key);
        }
        if (loadFactor() > maxLoad) {
            resize();
        }
    }
    private Node getNode(K key) {
        Collection<Node> bucket = buckets[getBucketIndex(key)];
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return node;
            }
        }
        return null;
    }

    private int getBucketIndex(K key) {
        return getBucketIndex(key, buckets.length);
    }
    private int getBucketIndex(K key, int length) {
        int hash = Math.abs(key.hashCode());
        return hash % length;
    }

    @Override
    public Set<K> keySet() {
        return hashSet;
    }

    @Override
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        Node node = getNode(key);
        V value = node.value;
        buckets[getBucketIndex(key)].remove(node);
        hashSet.remove(key);
        return value;
    }

    @Override
    public V remove(K key, V value) {
        if (!containsKey(key)) {
            return null;
        }
        if (get(key).equals(value)) {
            return remove(key);
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return hashSet.iterator();
    }
}
