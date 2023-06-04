package bstmap;

import edu.princeton.cs.algs4.BST;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private BSTNode root;              // root of BST

    private class BSTNode{
        private K key;                 // sorted by key
        private V val;               // associated data
        private BSTNode left, right;   // left and right subtrees
        private int size;

        public BSTNode(K key, V val, int size){
            this.key = key;
            this.val= val;
            this.size = size;
        }
    }

    /** Removes all the mappings from the map. */
    @Override
    public void clear(){
        root.size = 0;
        root = null;
    }

    @Override
    public boolean containsKey(K key){
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    @Override
    /*Returns the value associated with the given key.*/
    public V get(K key){
        return get(root, key);
    }

    private V get(BSTNode node, K key){
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if      (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else              return node.val;
    }

    @Override
    public int size() {
        return size(root);
    }

    /*Returns number of key-value pairs in BST rooted at x.*/
    private int size(BSTNode node){
        if (node == null) return 0;
        else return node.size;
    }

    /*Inserts the specified key-value pair into the tree.
    * Overwrites the old value with the new value if the tree already contains key.
    * Deletes the specified key and its associated value from the tree if the value is null.*/
    @Override
    public void put(K key, V val){
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        if (val == null){
            delete(key);
            return;
        }
        root = put(root, key, val);
    }

    private BSTNode put(BSTNode node, K key, V val){
        if (node == null) return new BSTNode(key, val, 1);
        int cmp = key.compareTo(node.key);
        if      (cmp < 0) node.left = put(node.left, key, val);
        else if (cmp > 0) node.right = put(node.right, key, val);
        else              node.val = val;
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    public void delete(K key){
        if (key == null) throw new IllegalArgumentException("calls delete() with a null key");
        root = delete(root, key);
    }

    private BSTNode delete(BSTNode node, K key){
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if      (cmp < 0) node.left = delete(node.left, key);
        else if (cmp > 0) node.right = delete(node.right, key);
        else {
            if (node.right == null) return node.left;
            if (node.left == null)  return node.right;
            BSTNode x = node;  //record the original node
            node = min(x.right);
            node.right = deleteMin(x.right);
            node.left = x.left;
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    public void deleteMin(){
        if (isEmpty()) throw new NoSuchElementException("tree underflow");
        root = deleteMin(root);
    }

    private BSTNode deleteMin(BSTNode node){
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    public void deleteMax(){
        if (isEmpty()) throw new NoSuchElementException("tree underflow");
        root = deleteMax(root);
    }

    private BSTNode deleteMax(BSTNode node){
        if (node.right == null) return node.left;
        node.right = deleteMax(node.right);
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    public K min(){
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty tree");
        return min(root).key;
    }

    private BSTNode min(BSTNode node){
        if (node.left == null) return node;
        else                   return min(node.left);
    }

    public K max(){
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty tree");
        return max(root).key;
    }

    private BSTNode max(BSTNode node){
        if (node.right == null) return node;
        else                    return max(node.right);
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    /*Returns all keys in the tree in ascending order.*/
    public void printInOrder(){
        printInOrder(root);
    }
    
    private void printInOrder(BSTNode node){
        if (node == null) return;
        printInOrder(node.left);
        System.out.print(node.key);
        printInOrder(node.right);
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIter(root);
    }

    /*An iterator that iterates over the keys of the tree.*/
    private class BSTMapIter implements Iterator<K>{
        BSTNode node;
        Set<K> keys;
        Iterator<K> helper;

        public BSTMapIter(BSTNode node){
            BSTNode cur = node;
            keys = new HashSet<>();
            keySet(keys, node);
            helper = keys.iterator();
        }

        public boolean hasNext(){
            return helper.hasNext();
        }

        public K next(){
              return helper.next();
        }
    }

    
    /* Returns a set view of the keys contained in the map. */
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        keySet(keys, root);
        return keys;
    }

    private void keySet(Set<K> k, BSTNode node){
        if (node == null) return;
        keySet(k, node.left);
        k.add(node.key);
        keySet(k, node.right);
    }

    public V remove(K key){
        throw new UnsupportedOperationException();
    }

    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }
}

