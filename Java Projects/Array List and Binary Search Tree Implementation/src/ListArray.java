import java.util.Comparator;

/** Abstract Data Type LIST, implemented with an array using generics.
 By default, items are appended to the list, but the user can insert
 at any index. This is fashioned after Java's ArrayList from Collections.
 */
public class ListArray<T> {

    /**
     * Array to store items in queue
     */
    private T[] items;

    /**
     * The maximum size of the array for all queues
     */
    protected static final int DEFAULT_CAPACITY = 100;

    /**
     * The capacity of the instance array (set to default). "capacity" is the
     * equivalent to items.length() and can be used interchangably.
     */
    private int capacity = DEFAULT_CAPACITY;

    /**
     * The number of items in the array.
     */
    private int numberOfItems = 0;

    // ============== 2 Overloaded Constructors ============== //
    /**
     * Constructor
     *
     * @param size The capacity of the list (i.e. max number of elements)
     */
    public ListArray(int size) {
        // set the capacity and declare a new array of requested size.
        // Creation of a generic array throws warning that we are ignoring.
        capacity = size;
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[capacity];
        items = temp;
    }

    /**
     * Default Constructor
     */
    public ListArray() {
        this(DEFAULT_CAPACITY);
    }

    // ************   SETTERS, GETTERS, toPrint ******************* //
    public int length() {
        return numberOfItems;
    }

    public int capacity() {
        return capacity;
    }

    /**
     * @return True if structure is at capacity.
     */
    public boolean isFull() {
        return items.length == length();
    }

    /**
     * @return True if no elements in data structure.
     */
    public boolean isEmpty() {
        return length() == 0;
    }


    public String toString() {
        String printedList = "";
        for (int i = 0; i < numberOfItems; i++) {
            // printint without linebreaks. Change space below to "\n" if too crowded.
            printedList += items[i].toString() + " ";
        }
        return printedList;
    } // end toString()

    // helper function for testing of valid index
    private boolean isValid(int index) {
        return (index >= 0 && index < length());
    }

    // *******************   FIND, CONTAINS, PEEK *************** //

    /**
     * If index valid, return value at that index without removing.
     * @param index into items. Ignored if invalid.
     * @return value at index
     */
    public T peek(int index) {
        // Return value only if index is valid
        if (isValid(index)) {
            return items[index];
        }
        return null;
    } // end peek(int)

    /**
     * Finds index of element
     *
     * @param item to search for in list. Using equals for comparison.
     * @return returns index of element or -1 if not in list
     */
    public int find(T item) {
        // look at array items up to valid entries.
        for (int i = 0; i < length(); i++) {
            // using equals as defined for the generic type T
            if (items[i].equals(item)) {
                return i;
            }
        }
        return -1;
    } // end find()

    /**
     * Checks if the list contains the element
     *
     * @param item the item that might be in the list
     * @return whether or not the item is in the list
     */
    public boolean contains(T item) {
        // Find returns index of found item or -1 if not in the list.
        return (-1 != find(item));
    } // end contains()

    // *******************   ADD  ******************* //
    /**
     * If not full, append item to list.
     *
     * @param item the item added to the list if possible
     */
    public void add(T item) {
        // no room at the inn
        if (isFull()) {
            return;
        }
        // appending when no positional information provided.
        items[length()] = item;
        numberOfItems++;
    } // end add(item)

    /**
     * Adds item to the list at a given valid index.
     *
     * @param item the item being inserted
     * @param index the index where the item needs to be at
     */
    public void add(T item, int index) {
        // no more room
        if (isFull()) {
            return;
        }
        // index invalid
        if (index > length() || index < 0) {
            return;
        }
        // length() is valid (same as add);
        if (index==length()) {
            add(item);
        }
        // shift all items from index to end to the right to make room.
        for (int i = length() - 1; i >= index; i--) {
            items[i + 1] = items[i];
        }
        items[index] = item;
        numberOfItems++;
    } // end add()

    /**
     * Replacing item at index.
     *
     * @param item to replace current list element
     * @param index valid location of item to be replaced. ignored if not valid.
     */
    public void set(T item, int index) {
        //  ignore if index invalid
        if (!isValid(index)) {
            return;
        }
        items[index] = item;
    } // end set()

    /**
     * Adds all elements in the array passed to the new array.
     *
     * @param toAdd the elements being added to the new array
     */
    public void addAll(T[] toAdd) {
        // is array legit
        if (null == toAdd) {
            return;
        }
        // can the current list hold everything? Ignore if not.
        if (toAdd.length+length() > capacity()){
            return;
        }
        // append all of array to the list
        for (T el : toAdd) {
            add(el);
        }
    }

    // *******************   REMOVE  ******************* //
    /**
     * Removes and returns element at given index. Ignores if index invalid.
     *
     * @param index index of the element
     * @return element that is removed
     */
    public T remove(int index) {
        // ignore if index not valid
        if (!isValid(index)) {
            return null;
        }
        // save the element to be returned
        T item = items[index];
        // shift everything from index to end to left so no gaps in array
        for (int i = index; i < length(); i++) {
            items[i] = items[i + 1];
        }
        // maintain nulls at non-valid index locations
        items[length()] = null;
        numberOfItems--;
        return item;
    } // end remove(int)

    /**
     * Remove given item from list.
     *
     * @param item to be removed. Ignored if not in list.
     */
    public void remove(T item) {
        // determine location of item, then remove that location.
        remove(find(item));
    } // end remove()

    /**
     * Removes all elements
     */
    public void removeAll() {
        // start over with a fresh, empty array
        numberOfItems = 0;
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[capacity()];
        items = temp;
    } // end removeAll()

    // *******************   CONVERT  ******************* //
    /**
     * Copy contents into a new array.
     *
     * @return copy of list
     */
    public T[] toArray() {
        // if list empty, return null
        if (0 == length()) {
            return null;
        }
        // create new generic array sized to match numberOfItems
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[length()];

        // copy contents
        for (int i = 0; i < length(); i++) {
            temp[i] = items[i];
        }
        return temp;
    } // end toArray()

    /**
     * Copy list contents into given array. Side effect: modifies given array.
     *
     * @param array Holds copy. If too small, nothing copied.
     */
    public void toArray(T[] array) {
        // not big enough -- ignore request
        if (numberOfItems > array.length) {
            return;
        }
        // copy into array.
        for (int i = 0; i < array.length; i++) {
            array[i] = items[i];
        }
    }

    /**
     * Sort method that sorts a ListArray based on the passed comparator
     * @param orderBy the comparator to use for comparisons
     */
    public void sort(Comparator<T> orderBy) {
        // iterate throughout the array and switch items
        for(int i = 0; i < numberOfItems - 1; ++i) {
            //set minIndex equal to current index
            int minIndex = i;

            // compare minIndex to every other element in array (not great for massive sized ListArrays, but it'll do)
            for(int j = i + 1; j < numberOfItems; ++j) {
                // if comparison is < 0 update minIndex to the second item
                if(orderBy.compare(items[j], items[minIndex]) < 0) { minIndex = j; }
            }

            // swapping items depending on minIndex item
            T temp = items[minIndex];
            items[minIndex] = items[i];
            items[i] = temp;
        }

    } // Complete

} // end class List