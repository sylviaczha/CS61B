package deque;

import java.util.Iterator;

public class LinkedListDeque<Item> implements Deque<Item>{
    public class IntNode{
        public Item item;
        public IntNode prev;
        public IntNode next;

        public IntNode(Item i, IntNode n, IntNode p){
            item = i;
            next = n;
            prev = p;
        }
    }

    public IntNode sentinel;
    public int size;

    /* Create an empty list. */
    public LinkedListDeque(){
        sentinel = new IntNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(Item x){
        sentinel = new IntNode(x, null, null);
        sentinel.next = new IntNode(x, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /*Add an item to the front of the list.*/
    @Override
    public void addFirst(Item x){
        sentinel.next.prev = new IntNode(x, sentinel.next, sentinel);
        sentinel.next = sentinel.next.prev;
        size += 1;
    }

    /*Add an item to the last of the list.*/
    @Override
    public void addLast(Item x){
        sentinel.prev.next = new IntNode(x, sentinel, sentinel.prev);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    /*Check if the deque is empty.*/
    @Override
    public boolean isEmpty(){
        if (sentinel.next == sentinel){ //or sentinel.prev == sentinel
            return true;
        }
        return false;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        IntNode p = sentinel;
        while (p.next != sentinel){
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    @Override
    public Item removeFirst(){
        if (size == 0){
            return null;
        }
        Item temp = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.next.prev = sentinel;
        size -= 1;
        return temp;
    }

    @Override
    public Item removeLast(){
        if (size == 0){
            return null;
        }
        Item temp = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.prev.next = sentinel;
        size -= 1;
        return temp;
    }

    @Override
    public Item get(int index){
        if (index > (size - 1)){
            return null;
        }
        IntNode p = sentinel;
        for (int i=0; i<=index; i++){
            p = p.next;
        }
        return p.item;

    }

    private Item getRecursiveHelp(IntNode p, int index){
        if (index == 0){
            return p.next.item;
        }else{
            return getRecursiveHelp(p.next, index - 1);
        }
    }

    public Item getRecursive(int index){
        if (index > (size - 1)){
            return null;
        }
        IntNode p = sentinel;
        return getRecursiveHelp(p, index);
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    public static void main(String[] args){
        LinkedListDeque a = new LinkedListDeque();
        a.addFirst(4);
        a.addFirst(3);
        a.addFirst(2);
        a.addFirst(1);
        a.addLast(100);
        a.addLast(101);
        a.addLast(102);
        a.printDeque();
        System.out.print(a.getRecursive(5));
    }
}