/** Array based list.
 * @author Sylvia Zhang
 */

/** Invariants:
 * addLast: The next item we want to add, will go into position size.
 * getLast: The item we want to return is in position size - 1.
 * size: The number of items in the list should be size.
 */

public class AList<Item> implements List<Item> {
    private Item[] items;
    private int size;

    /** Creates an empty list. */
    public AList() {
        items = (Item []) new Object[100];
        size = 0;
    }

    /** Inserts item into given position. */
    @Override
    public void insert(Item x, int position) {
        Item[] newItems = (Item[]) new Object[items.length + 1];

        System.arraycopy(items, 0, newItems, 0, position);
        newItems[position] = x;

        System.arraycopy(items, position, newItems, position + 1, items.length - position);
        items = newItems;
    }

    /** Resizes the underlying array to the target capacity.*/
    private void resize(int capacity) {
        Item[] newItems = (Item[]) new Object [capacity];
        System.arraycopy(items, 0 , newItems, 0, size);
        items = newItems;
    }

    /** Inserts an item at the front of the list. */
    @Override
    public void addFirst(Item x) {
        insert(x, 0);
    }

    /** Inserts an item to the back of the list. */
    @Override
    public void addLast(Item x) {
        if (size == items.length) {
            resize(size + 1);
        }

        items[size] = x;
        size += 1;
    }

    /** Gets an item from the front of the list. */
    @Override
    public Item getFirst() {
        return get(0);
    }

    /** Returns the item from the back of the list. */
    public Item getLast() {
        return items[size - 1];
    }

    /** Gets the ith item in the list (0 is the front) */
    public Item get(int i) {
        return items[i];
    }

    /** Returns the number of items in the list. */
    @Override
    public int size() {
        return size;
    }

    /** Deletes item from the back of the list. */
    public Item removeLast() {
        Item x = getLast();
        items[size - 1] = null;
        size -= 1;
        return x;
    }
}
