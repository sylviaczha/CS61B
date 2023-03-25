# Inheritance

## Interface Inheritance

- Interface is the list of all method signatures.
- Inheritance is the subclass inherits the interface from a superclass. 
- Subclasses must overrides all of these methods, otherwise Java will fail to compile.

### Method Overriding 重写

- Method overriding means that a subclass has a method with the exact same signature as in the superclass, then the subclass overrides the method.

### Method Overloading 重载

- Method overloading refers to methods with the same name but different signatures.

## Implementation Inheritance: Default Method

- Use the default keywords to specify a method that subclasses should inherit from an interface.

```Java
public interface List61B<Item> {
    public void addFirst(Item x);
    public void addLast(Item y);
    public Item getFirst();
    public Item getLast();
    public Item removeLast();
    public void insert(Item x, int position);
    public int size();
    default public void print() {
        for(int i = 0; i < size(); i += 1){
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
}
```

```Java
public interface SLList<Item> implements List61B<Item> {
    @Override
    public void print() {
        for (Node p = sentinel.next; p != null; p = p.next) {
            System.out.print(p.item + " ");
        }
        System.out.println();
    }
}
```

## Implementation Inheritance: Extends

```Java
public class VengefulSLList<Item> extends SLList<Item> {
    private SLList<Item> deletedItems;
    public VengefulSLList() {
        deletedItems = new SLList<Item>();
    }
    @Override
    public Item removeLast() {
        Item oldBack = super.removeLast();
        deletedItem.addLast(oldBack);
        return oldBack;
    }

    public void printLostItems() {
        deletedItems.print();
    }
}
```

```Java
public VengefulSLList() {
    super();
    deletedItem = new SLList<Item> ();
}
```

```Java
public VengefulSLList(Item x) {
    super(x);
    deletedItem = new SLList<Item> ();
}
```

## Encapsulation

- A module is encapsulated if its implementation is completely hidden, and it can be accessed only through a documented interface.
- A module is a set of methods that work together as a whole to perform some task or a set of related tasks.
- Implementation inheritance breaks encapsulation.

## Casting

- Casting is to treat an expression as having a different compile-time type temporarily.

## Subtype Polymorphism

- Polymorphism is to provide a single interface to entities of different types.

