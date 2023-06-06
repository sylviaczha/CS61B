# Balanced Search Tree

## Red Black Tree

```Java
public class RedBlackBST<Key extends Comparable<Key>, Value>{
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node{
        private Key key;
        private Value value;
        private Node left, right;
        private boolean color;
        private int size;


        public Node(Key key, Value value, boolean color, int size){
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
    }

    public RedBlackBST(){
    }

    public int size(){
        return size(root);
    }

    public int size(Node x){
        if (x == null) return 0;
        return x.size;
    }

    private boolean isRed(Node x){
        if (x == null) return false;
        return x.color == RED;
    }

    public boolean isEmpty(){
        return root == null;
    }

    /** Standard BST search. */

    public Value get(Key key){
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    private Value get(Node x, Key key){
        while (x ! = null){
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.val;
        }
        return null;
    }

    public boolean contains (Key key){
        return get(key) != null;
    }

    /** Red-black tree insertion. */
    public void put(Key key, Value val){
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null){
            delete(key);
            return;
        }
           root = put(root, key, val);
           root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val){
        if (h == null) return new Node(key, val, RED, 1);

        int cmp = key.compareTo(h.key);
        if      (cmp < 0) h.left  = put(h.left,  key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else              h.val   = val;

        if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
        if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left)  &&  isRed(h.right))     flipColors(h);
        h.size = size(h.left) + size(h.right) + 1;

        return h;
    }

    /** Red-black tree deletion.*/
}
```