public class OrderedList<T extends Comparable<T>> {

    /** An array of elements of type T (Comparable) */
    private T[] items;
    private int numberOfItems = 0;
    private int capacity;
    private static final int DEFAULT = 10;

    /** Constructor to establish array (capacity of 10) */
    public OrderedList(int size) {
        capacity = size;
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Comparable[capacity];
        items = temp;
    }

    public OrderedList() {
        this(DEFAULT);
    }

    /** pretty print string for this list */
    public String toString() {
        String output = "";
        for (int i=0; i<numberOfItems; i++) {
            output += items[i].toString()+"\n";
        }
        return output;
    }

    public T peek(int index) {
        if (0<=index && index<numberOfItems) {
            return items[index];
        }
        return null;
    }

    // this is a helper function for the ordered add.
    private void shiftRight(int index) {
        for (int i = numberOfItems; i>index; i--) {
            items[i] = items[i-1];
        }
    }

    // Add an element to maintain order.
    // This is not the most efficient way to do this, but straight-forward
    public void add(T element) {
        // If full up, no can do
        if (numberOfItems >= items.length) {
            return;
        }

        // if empty, put it in the first slot
        if (0==numberOfItems) {
            items[0] = element;
            ++numberOfItems;
            return;
        }
        // else figure out where it goes
        int i=0;

        // making sure that the item at index i exists and is able to be compared to
        while (items[i] != null && element.compareTo(items[i]) > 0) { // items[i] < element
            ++i;
        }
        // if not at the end, need to shift things to make room
        if (i<numberOfItems) {
            shiftRight(i);
        }
        // place it and update count
        items[i] = element;
        ++numberOfItems;
    } // end add() -- Complete

    /** find the element provided and return that object (not the index) */
    public T find(T element) {
        // starting and ended indices
        int start = 0;
        int end = numberOfItems - 1;

        while(start <= end) {
            // finding the middle value
            int mid = start + (end - 1) / 2;

            // returning the value if it is the value
            if(items[mid].equals(element)) {
                return items[mid];
            }

            // if the element is greater than the item at the middle, we use the right side of the array
            if(element.compareTo(items[mid]) > 0) { // items[mid] < element
                start = mid + 1;
            }
            // if the element is less than the item at the middle we use the left side of the array
            else {
                end = mid - 1;
            }


        }

        // if it isn't found in our while loop, return null
        return null;
    } // end find -- Complete

    public T findRecursive(T element) {
        return findRec(element,0,numberOfItems-1);
    }

    public T findRec(T element, int start, int end ) {
        // if the end is less than zero something went wrong
        if (end >= 0) {
            // finding the middle value
            int mid = (start + end) / 2;

            // if item at mid equals what we are looking for return that item
            if(items[mid].equals(element)) {
                return items[mid];
            }

            // if the element is less than the item at the middle use the left side of the array
            if(element.compareTo(items[mid]) < 0) {
                return (findRec(element, 0, mid - 1));
            }

            // otherwise use the right side of the array
            return findRec(element, mid + 1, end);
        }

        // returning null if the end is less than zero
        return null;
    } // end findRec - Complete

} // end orderedArray