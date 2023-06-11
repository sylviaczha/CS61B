# Balanced Search Tree

## Red Black Tree

https://www.happycoders.eu/algorithms/red-black-tree-java/ 

https://www.bilibili.com/video/BV1BB4y1X7u3/?spm_id_from=333.788&vd_source=76eb554f71a53300228033f936490670 

### Red-Black Tree Properties

The following rules enforce the red-black tree balance:

1. Each node is either red or black.
2. The root is black.
3. All NIL leaves are black.
4. A red node much not have red children.
5. All paths from a node to the leaves below contain the same number of black nodes.

### Red-Black Tree Insertion

After inserting the element, we need to adjust the tree in order to maintain RBT properties. <br /> 
Case 1: new node is the root. <br /> 
Case 2: parent node is black. <br /> 
Case 3: parent node is red, parent node is the root. <br /> 
Case 4: parent node is red, uncle node is red. <br /> 
Case 5: parent node is red, uncle node is black, insert node is inner grandchild. <br /> 
Case 6: parent node is red, uncle node is black, insert node is outer grandchild.

```Java
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    private Node root;     // root of the BST

    // BST helper node data type
    private class Node {
        private Key key;           // key
        private Node left, right;  // links to left and right subtrees
        private Node parent;
        private boolean color;     // color of parent link

        public Node(Key key) {
            this.key = key;
        }
    }

    /* Red Black Tree search */
    public Value search(int key) {
        Node node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if      (cmp < 0) node = node.left;
            else if (cmp > 0) node = node.right;
            else              return node.key;
        }
        return null;
    }

    /* Red Black Tree insertion */
    public void insert(int key) {
        Node node = root;
        Node parent = null;

        while(node != null) {
            parent = node;
            int cmp = key.compareTo(node.key);
            if      (cmp < 0) node = node.left;
            else if (cmp > 0) node = node.right;
            else    throw new IllegalArgumentException("BST already contains the key");
        }

        Node new = new Node(key);
        new.color = RED;
        if      (parent == null) root = new;
        int cmp = key.compareTo(parent.key);
        if      (cmp < 0)        parent.left = new;
        else if (cmp > 0)        parent.right = new;
        new.parent = parent;

        fixTreeAfterInsert(new);
    }

    private void fixTreeAfterInsert(Node node) {
        Node parent = node.parent;
        Node grandparent = parent.parent;
        Node uncle = getUncle(parent);

        // Case 1: new node is the root.
        if (parent == null) return;

        // Case 2: parent node is black.
        if (parent.color == BLACK) return;

        // Case 3: parent node is red, parent node is the root.
        if (grandparent == null) parent.color = BLACK;
        return;

        // Case 4: parent node is red, uncle node is red.
        if (uncle != null && uncle.color == RED) {
            parent.color       = BLACK;
            uncle.color        = BLACK;
            grandparent.corlor = RED;
            // Call the fix function recursively for the grandparent. 
            fixTreeAfterInsert(grandparent);
        }

        // Case 5: parent node is red, uncle node is black, insert node is innerchild.
        // LR
        if (parent == grandparent.left && node == parent.right) {
            rotateLeft(parent);
            parent = node;
            rotateRight(grandparent);
            parent.color = BLACK;
            grandparent.color = RED;
        }


        // RL
        else if (parent == grandparent.right && node == parent.left) {
            rotateRight(parent);
            parent = node;
            rotateLeft(grandparent);
            parent.color = BLACK;
            grandparent.color = RED;
        }

        // Case 6: parent node is red, uncle node is black, insert node is outerchild.
        // LL
        else if (parent == grandparent.left && node == parent.left) {
            rotateRight(grandparent);
            parent.color = BLACK;
            grandparent.color = RED;
        }

        // RR
        else if (parent == grandparent.right && node == parent.right) {
            rotateLeft(grandparent);
            parent.color = BLACK;
            grandparent.color = RED;
        }
    }

    private Node getUncle(Node parent){
        Node grandparent = parent.parent;
        if      (grandparent.left == parent)  return grandparent.right;
        else if (grandparent.right == parent) return grandparent.left;
        else    throw new IllegalStateException("Parent is not a child of the grandparent."
    }

    
    /* Helper function */
    private void rotateRight(Node node){
        Node parent    = node.parent;
        Node leftchild = node.left;

        node.left = leftchild.right;
        if (leftchild.right != null) leftchild.right.parent = node;
        
        leftchild.right = node;
        node.parent = leftchild;

        replace(parent, node, leftchild)
    }

    private void rotateLeft(Node node){
        Node parent = node.parent;
        Node rightchild = node.right;

        node.right = rightchild.left;
        if (rightchild.left != null) rightchild.left.parent = node;

        rightchild.left = node;
        node.parent = rightchild;

        replace(parent, node, rightchild)
    }

    private void replace(Node parent, Node oldchild, Node newchild){
        if      (parent       == null)     root = newchild;
        else if (parent.left  == oldChild) parent.left == newchild;
        else if (parent.right == oldChild) parent.right == newchild;
        else    throw new IllegalStateException("Node is not a child of its parent")

        if (newchild != null) newchild.parent = parent;
    }
}
```