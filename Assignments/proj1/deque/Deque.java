package deque;

import java.util.Iterator;

public interface Deque<Item> extends Iterable<Item>{
    public int size();

    public boolean isEmpty();

    public void addFirst(Item item);

    public void addLast(Item item);

    public Item removeFirst();

    public Item removeLast();

    public Item get(int index);

    public void printDeque();

    @Override
    public Iterator<Item> iterator();

    public boolean equals(Object o);

}