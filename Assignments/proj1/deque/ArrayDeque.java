package deque;

import java.util.Iterator;

public class ArrayDeque<Item> implements Deque<Item>{
    private Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /*Creates an empty array deque.*/
    public ArrayDeque(){
        items = (Item[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    /*Get the last index.*/
    private int minusOne(int index){
        return Math.floorMod(index - 1, items.length);
    }

    /*Get the first index.*/
    private int plusOne(int index){
        return Math.floorMod(index + 1, items.length);
    }

    private int plusOne(int index, int length){
        return Math.floorMod(index + 1, length);
    }

    private void resize(){
        if (size == items.length){
            expand();
        }else if (size < (items.length) * 0.25 && items.length > 8){
            reduce();
        }
    }

    private void expand(){
        resizeHelper(items.length * 2);
    }

    private void reduce(){
        resizeHelper(items.length / 2);
    }

    private void resizeHelper(int capacity){
        Item[] tempArray = items;
        int begin = plusOne(nextFirst);
        int end = minusOne(nextLast);
        items = (Item[]) new Object[capacity];
        nextFirst = 0;
        nextLast = 1;
        for(int i = begin; i != end; i = plusOne(i, tempArray.length)){
            items[nextFirst] = tempArray[i];
            nextFirst = plusOne(nextFirst);
        }
        items[nextFirst] = tempArray[end];
        nextFirst = plusOne(nextFirst);
    }

    @Override
    public void addFirst(Item item){
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    public Item getFirst(){
        return items[plusOne(nextFirst)];
    }

    @Override
    public Item removeFirst(){
        Item first = getFirst();
        nextFirst = plusOne(nextFirst);
        items[nextFirst] = null;
        size -= 1;
        return first;
    }

    @Override
    public void addLast(Item item){
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    public Item getLast(){
        return items[minusOne(nextLast)];
    }

    @Override
    public Item removeLast(){
        Item last = getLast();
        nextLast = minusOne(nextLast);
        items[nextLast] = null;
        size -= 1;
        return last;
    }

    @Override
    public Item get(int index){
        if (index < 0 || index >= size || isEmpty()) {
            return null;
        }
        index = Math.floorMod(plusOne(nextFirst) + index, items.length);
        return items[index];
    }

    @Override
    public void printDeque(){
        int begin = plusOne(nextFirst);
        int end = nextLast;
        for (int index = begin; index != end; index = plusOne(index)){
            System.out.print(items[index]);
            System.out.print(" ");
        }
        System.out.println();
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    public static void main(String[] args){
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        arrayDeque.addLast("a");
        arrayDeque.addLast("b");
        arrayDeque.addFirst("c");
        arrayDeque.addLast("d");
        arrayDeque.addLast("e");
        arrayDeque.addFirst("f");
        arrayDeque.addLast("g");
        arrayDeque.addLast("h");


        arrayDeque.printDeque();



    }

}


