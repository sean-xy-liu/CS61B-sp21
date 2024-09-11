package bstmap;

import java.util.Iterator;
import java.util.Set;

// K have compareTo method.
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private BSTNode root;
    private int size;
    private class BSTNode {
       K key;
       V value;
       BSTNode left, right;
       void setLeft(BSTNode leftNode) {
           left = leftNode;
       }
       void setRight(BSTNode rightNode) {
           right = rightNode;
       }
       int compareTo(BSTNode other) {
           return key.compareTo(other.key);
       }
       BSTNode(K key, V value) {
           this.key = key;
           this.value = value;
       }
    }
    public void printInOrder() {
        printInOrder(root);
    }
    private void printInOrder(BSTNode node) {
        if (node == null) {
            return;
        }
        System.out.println("key: " + node.key + " value: " + node.value);
        printInOrder(node.left);
        printInOrder(node.right);
    }
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key, root) != null;
    }

    @Override
    public V get(K key) {
        BSTNode p = get(key, root);
        if (p == null) {
            return null;
        }
        return p.value;
    }
    private BSTNode get(K key, BSTNode node) {
        if (node == null) {
            return null;
        }
        if (node.key.equals(key)) {
            return node;
        } else if (key.compareTo(node.key) > 0) {
            return get(key, node.right);
        } else {
            return get(key, node.left);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
//        if (root == null) {
//            root = new BSTNode(key, value);
//            size++;
//        }
//        BSTNode p = get(key, root);
//        if (p != null) {
//            p.value = value;
//        } else {
//            put(key, value, root);
//        }
        root = put(key, value, root);
    }
    private BSTNode put(K key, V value, BSTNode node) {
        if (node == null) {
            size++;
            return new BSTNode(key, value);
        }
        if (key.compareTo(node.key) == 0) {
            return new BSTNode(key, value);
        } else if (key.compareTo(node.key) < 0) {
            node.left = put(key, value, node.left);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(key, value, node.right);
        }
        return node;
//        int compareResult = key.compareTo(node.key);
//        if (compareResult > 0) {
//            if (node.right == null) {
//                node.setRight(new BSTNode(key, value));
//                size++;
//            } else {
//                put(key, value, node.right);
//            }
//        } else {
//            if (node.left == null) {
//                node.setLeft(new BSTNode(key, value));
//                size++;
//            } else {
//                put(key, value, node.left);
//            }
//        }
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}