/**
 * Classic Last-In-First-Out Queue (Lifo) or STACK
 * push to add, pop to remove
 * This one specific to BinaryNode stack
 */
public class Stack<T> {

    // Using an array for easy implementation of adding and removing at end.

    /** Maximum size of stack (if user does not specify) */
    private static final Integer DEFAULT_CAPACITY = 100;

    /** Primary structure to hold all the items */
    private T[] stack;

    /** Number of elements current in list */
    private Integer size;

    /** Constructor with specified maximum size */
    public Stack(int capacity) {
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[capacity+1];
        stack = temp;
        size = 0;
    }

    /** Default constructor using default size */
    public Stack() { this(DEFAULT_CAPACITY); }

    /** Determine if empty. */
    public boolean empty() { return (0==size); }

    /**
     * Remove first-in element (oldest in queue)
     * @return element removed from the queue -- ignore if empty
     */
    public T pop() {
        // if empty, no need to pop
        if(empty()) { System.out.println("ERROR: Cannot remove: Stack is empty"); return null; }

        size--;
        return stack[size];
    }

    /**
     * Add to the end of the list.
     * @param data T object to be added to the queue
     */
    public void push(T data) {
        // assume there is room
        if(size + 1 > DEFAULT_CAPACITY) {
            System.out.println("No space to add to list");
            return;
        }

        // adding at end of array
        stack[size] = data;
        size++;
    }
}
