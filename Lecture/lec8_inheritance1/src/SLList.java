/** An SLList is a list of integers, which hides the terrible truth
 * of the nakedness within.
 */

public class SLList<Item> implements List<Item> {
    private class Node {
        public Item item;
        public Node next;

        public Node(Item i, Node n) {
            item = i;
            next = n;
        }
    }

    /* The first item (if it exists) is at sentinel.next. */
    private Node sentinel;
    private int size;

    /* Creates an empty SLList. */
    public SLList() {
        sentinel = new Node(null, null);
        size = 0;
    }

    public SLList(Item x) {
        sentinel = new Node(null, null);
        sentinel.next = new Node(x, null);
        size = 1;
    }

    /** Inserts the item into the given position in
     * the list. if the position is greater than the
     * size of the list, inserts at the end instead.
     */
    public void insert(Item item, int position) {
        Node p = sentinel;
        while (position > 1 && p.next != null) {
            position--;
            p = p.next;
        }
        Node newNode = new Node(item, p.next);
        p.next = newNode;
    }

    /** Adds x to the front of the list. */
    public void addFirst(Item x) {
        sentinel.next = new Node(x, sentinel.next);
        size += 1;
    }

    /** Adds x to the end of the list.*/
    public void addLast(Item x) {
        size += 1;
        Node p = sentinel;
        while (p.next != null){
            p = p.next;
        }
        p.next = new Node(x, null);

    }

    /** Returns the first item in the list. */
    public Item getFirst() {
        return sentinel.next.item;
    }

    /** Returns the back node of our list. */
    public Node getLastNode() {
        Node p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        return p;
    }

    /** Returns the last item. */
    public Item getLast() {
        Node back = getLastNode();
        return back.item;
    }

    /** Returns the ith item in the list. */
    public Item get(int i) {
        return get(i, sentinel.next); /* return get(i, sentinel)*/
    }

    private Item get(int i, Node p) {
        if (i == 0) {
            return p.item;
        }
        return get(i - 1, p.next);
    }

    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    /** Deletes and returns the last item. */
    public Item removeLast() {
        Node back = getLastNode();
        if (back == sentinel) {
            return null;
        }

        Node p = sentinel;

        while (p.next != back) {
            p = p.next;
        }
        p.next = null;
        return back.item;
    }

    @Override
    public void print() {
        System.out.println("THIS IS THE OVERRIDDEN VERSION.");
        Node p = sentinel.next;
        while (p != null) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    public static void main(String[] args) {
        /* Creates a list of one integer, namely 10 */
        SLList L = new SLList();
        L.addLast(20);
        System.out.println(L.size());
    }

}