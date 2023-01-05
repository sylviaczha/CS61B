/** An SLList is a list of integers, which hides the
 * terrible true of the nakedness within. */
public class SLList {
    public static class IntNode{
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n){
            item = i;
            next = n;
        }
    }

    /* The first item (if it exists) is at sentinel.next. */
    private IntNode sentinel;
    private int size;

    /** Creates an empty SLList. */
    public SLList(){
        sentinel = new IntNode(1, null);
        size = 0;
    }

    public SLList(int x){
        sentinel = new IntNode(1, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    /** Adds an item to the front of the list. */
    public void addFirst(int x){
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    /** Retrieves the front item from the list. */
    public int getFirst(){
        return sentinel.next.item;
    }

    /** Adds an item to the end of the list. */
    public void addLast(int x){
        size += 1;
        IntNode p = sentinel;

        /* Advance p to the end of the list. */
        while (p.next != null){
            p = p.next;
        }

        p.next = new IntNode(x, null);
    }

    public int size(){
        return size;
    }

    public static void main(String[] args){
        /*Creates a list of one integer, namely 10 */
        SLList L = new SLList();
        L.addLast(20);
        System.out.println(L.size());
    }

}
