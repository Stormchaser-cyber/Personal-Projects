public class ListArray<T> implements  List<T>{

    /**
     * This is a list of T objects implemented with an array. The list may have duplicates. It is not in sorted order. Add and remove operate at the end of the list by default, but positional information can be provided to operate in the middle of the list.
     */

    /** Array to store items in queue */
    protected T[] items;

    /** The maximum size of the array for all queues */
    protected static final int DEFAULT_CAPACITY = 2000;

    /** The capacity of the instance array (set to default). "capacity" is the equivalent to items.length() and can be used interchangably. */
    private int capacity = DEFAULT_CAPACITY;

    /** The number of items in the array. */
    private int numberOfItems=0;


    // ============== 2 Overloaded Constructors ============== // just needs javadocs

    /**
     Constructor
     @param size The capacity of the list (i.e. max number of elements)
     */
    public ListArray(int size) {
        capacity = size;
        @SuppressWarnings("unchecked")
        T[] tempArray = (T[]) new Object[capacity];
        items = tempArray;
    }

    /**
     Default Constructor
     */
    public ListArray() {
        this(DEFAULT_CAPACITY);
    }

    // ************   SETTERS, GETTERS, toPrint ******************* //

    /**
     * gets the length of list
     * @return number of items in list
     */
    @Override
    public int length() { return numberOfItems; }

    /**
     * gets the capacity of the list
     * @return the number of items that can be stored in a list
     */
    @Override
    public int capacity() { return capacity; }

    /**
     @return True if structure is full
     */
    @Override
    public boolean isFull(){
        if (numberOfItems < capacity) {
            return false;
        } else {
            return true;
        }
    }

    /**
     @return True if structure has no elements in it
     */
    @Override
    public boolean isEmpty() {
        if (numberOfItems == 0) {
            return true;
        } else {
            return false;
        }
    }

    /** Prints numbered list of objects T.  */
    @Override
    public String toString() {
        String printedList = "";
        for (int i=0; i<numberOfItems; i++) {
            printedList += (i) + ". " + items[i].toString() + "\n";
        }
        return printedList;
    } // end toString()

    // *******************   FIND, CONTAINS, PEEK *************** // just needs javadocs

    /**
     * returns the T object at the index given, only if the object exists
     * @param index: the index to search
     */
    @Override
    public T peek(int index) {
        // testing if index is valid
        if (index < 0 || index > capacity) {
            return null;
        }
        if (items[index] == null) {
            return null;
        }

        // if there is an item in the index given and if the index given
        // is in appropriate range then return item at that index
        return items[index];
    } // end peek(int)

    /**
     * find searches through data structure for object T and if found, returns the index of object T
     * @param T: the object to look for in data structure
     */
    @Override
    public int find(T T) {
        // iterating through array to find element
        for(int i = 0; i < numberOfItems; i++) {
            if(items[i].equals(T)) {
                return i;
            }
        }

        return -1;
    } // end find(T)

    /**
     * returns true if T is in the list, false otherwise
     * @param T: the T object that we are searching for
     */
    @Override
    public boolean contains(T T) {
        // iterating through the array to see if item is in the array
        for (int i = 0; i < numberOfItems; i++) {
            if (items[i] != null && items[i].equals(T)) {
                return true;
            }
        }

        return false;
    } // end contains(T)

    // *******************   ADD  ******************* //
    /**
     * adds the T object at the end of the array
     * @param T: the T object to add to the array
     */
    @Override
    public void add(T T) {
        if(isFull()) {
            return;
        }

        items[numberOfItems] = T;
        numberOfItems += 1;

    } // end add(T)

    /**
     * adds object T at a certain index
     * @param T: the T object to add to array
     * @param index: the index at which to add the T object at in array
     */
    @Override
    public void add(T T, int index) {
        // checking if array has space to add an item
        if(isFull()) {
            return;
        }

        //checking to see if index is valid
        if (index < 0 || index > length()) {
            return;
        }

        //iterating through the structure and shifting all elements to the right by 1
        for (int i = numberOfItems; i > index; i--) {
            items[i] = items[i-1];
        }

        //inserting item and updating number of items
        items[index] = T;
        numberOfItems += 1;
    } // end add(T, int)

    /**
     * adds all elements in the passed array to the list
     * @param toAdd: the array of elements to add to the list
     */
    @Override
    public void addAll(T[] toAdd) {
        // checking if array has space to add an item
        if(isFull()) {
            return;
        }

        // checking to see if array has space to add all elements
        if(!(numberOfItems + toAdd.length <= capacity)) return;

        //adding items from array to list
        for (int i = 0; i < toAdd.length; i++) {
            add(toAdd[i]);
        }

    } // end addAll(T[] toAdd)

    /**
     * replaces an object T with another T object at a certain index
     * @param T: the new T that is replacing the other T object
     * @param index: the index of the object T that is being replaced
     */
    @Override
    public void set(T T, int index) {
        //checking if index is valid
        if(peek(index) == null) {
            return;
        }

        if(index < 0 ) {
            return;
        }

        if(index > capacity) {
            return;
        }

        items[index] = T;

    } // end set(T, int)

    // *******************   REMOVE  ******************* //
    /**
     * Removes the T object at the specified index
     * @param index: the index of the T object to be removed
     */
    @Override
    public T remove(int index) {
        // checking if index is valid
        if (peek(index) == null) {
            return null;
        }

        //saving the item and overwriting it
        T removedItem = items[index];
        for(int i = index; i < numberOfItems - 1; i++) {
            items[i] = items[i+1];
        }
        items[numberOfItems - 1] = null;
        numberOfItems -= 1;

        // returning the removed item
        return removedItem;
    } // end remove(int)

    /**
     * Removes the T object by searching through the list
     * @param T: the T object to remove from the list
     */
    @Override
    public void remove(T T) {
        // testing to see if item is in items
        if(!(contains(T))) {
            return;
        }

        //finding the index of the object,
        int index = find(T);
        for(int i = index; i < numberOfItems; i++) {
            items[i] = items[i + 1];
        }
        items[length() - 1] = null;
        numberOfItems -= 1;
    } // end remove(T)

    /**
     * Removes all of the T objects by overwriting items as null
     */
    @Override
    public void removeAll() {
        @SuppressWarnings("unchecked")
        T[] tempArray = (T[]) new Object[capacity];
        items = tempArray;
        numberOfItems = 0;
    } // end removeAll()

    // *******************   CONVERT  ******************* // just needs java docs
    /**
     * Returns an array with the same number of items from the items list
     * @return the new array
     */
    @Override
    public T[] toArray() {
        // checking to see if list is empty
        if (isEmpty()) {
            return null;
        }

        // creating a return array
        @SuppressWarnings("unchecked")
        T[] returnArray = (T[]) new Object[numberOfItems];

        // moving all items into the return array
        for (int i = 0; i < numberOfItems; i++) {
            returnArray[i] = items[i];
        }
        return returnArray;
    } // end toArray(T)

    /**
     * Returns an array with the same number of items from the items list
     * @param array: the array to add all of the items from items into
     */
    @Override
    public void toArray(T[] array) {

        // for loop to fill out the array passed into method
        for (int i = 0; i < numberOfItems; i++) {
            array[i] = items[i];
        }
    } // end toArray(T[] array)

    /**
     * Returns a sublist of the original list between the start and end parameters (as indices)
     * @param start: the starting index of the list
     * @param end: the ending index of the list
     * @return List array which is a sublist of the original list
     */
    @Override
    public List<T> sublist(int start, int end) {
        // checking to see if list is empty
        if (isEmpty()) {
            return null;
        }

        // checking logic of start being greater than end
        if (start > end) {
            return null;
        }

        // checking logic of start being less than 0
        if (start < 0) {
            return null;
        }

        // checking logic of end being more than number of items1
        if (end > numberOfItems) {
            return null;
        }

        // creating new list array to return if logic checks pass
        ListArray returnArray = new ListArray(end - (start - 1));
        for (int i = start; i <= end; i++) {
            returnArray.add(items[i]);
        }

        return returnArray;
    } // end sublist()

    /**
     * Return index of last occurring element in the list.
     * If element is not in list return -1
     * @param el the element to find in list
     * @return the index of the element or -1 if element is not found
     */
    public int lastIndexOf(T el) {
        // testing to see if linked list is empty
        if (isEmpty()) return -1;

        // testing to see if item is even in the linked list
        if (!contains(el)) return -1;

        // finding the last index of item
        int index = -1;
        for (int i = 0; i < numberOfItems; i++) {
            if (items[i].equals(el)) {
                index = i;
            }
        }


        return index;
    } // end lastIndexOf(T el)

    /**
     * Return true if every element in "array" is also in the list
     * else return false
     * @param array the array of items to compare elements to
     * @return true if every element is the same, false if otherwise
     */
    public boolean containsAll(T[] array) {
        // if both containers are empty
        if (isEmpty() && array.length == 0) return true;

        // if we don't have any items but the array has certain elements
        if (isEmpty()) return false;

        // testing to see if all items in items match the array
        for(int i = 0; i < array.length; i++) {
            if (!items[i].equals(array[i])) {
                return false;
            }
        }

        return true;
    } // containsAll(T[] array)
}
