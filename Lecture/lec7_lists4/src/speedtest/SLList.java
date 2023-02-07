package speedtest;

public class SLList<Item> {
    private class StuffNode{
        public Item item;
        public StuffNode next;

        public StuffNode(Item i, StuffNode n){
            item = i;
            next = n;
        }
    }

    /* The first item (if it exists) is at sentinel.next.*/
    private StuffNode sentinel;
    private int size;

    /** Creates an empty SLList. */
    public SLList(){
        sentinel = new StuffNode(null, null);
        size = 0;
    }

    public SLList(Item x){
        sentinel = new StuffNode(null, null);
        sentinel.next = new StuffNode(x, null);
        size = 1;
    }

    /* Adds x to the front of the list. */
    public void addFirst(Item x){
        sentinel.next = new StuffNode(x, sentinel.next);
        size += 1;
    }

    /* Returns the first item in the list.*/
    public Item getFirst(){
        return sentinel.next.item;
    }

    /* Adds to the end of the list. */
    public void addLast(Item x){
        size += 1;
        StuffNode p = sentinel;

        /* Advance p to the end of the list. */
        while (p.next != null){
            p = p.next;
        }

        p.next = new StuffNode(x, null);
    }

    /** Returns the size of the list. */
    public int size(){
        return size;
    }

    public static void main(String[] args){
        SLList<Integer> L = new SLList<>();
    }
}
