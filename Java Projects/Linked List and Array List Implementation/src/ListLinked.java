/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tedst
 */
public class ListLinked<T> implements  List<T>{

    /**
     * Node class of nodes for our linked list
     */
    class Node {
        T data = null;
        Node next = null;
        
        /**
         * Default Constructor
         */
        Node() {}
        
        /**
         * Constructor 
         * @param d the data of a particular node
         */
        Node(T d) {
            data = d;
        }
    }
    
    Node head = null;
    Node tail = null;
    
    // ============== 2 Overloaded Constructors ============== 

    /**
     Constructor
     @param size The capacity of the list (i.e. max number of elements)
     */
    public ListLinked(int size) {
        capacity = size;
//        @SuppressWarnings("unchecked")
//        T[] tempArray = (T[]) new Object[capacity];
//        items = tempArray;
    }

    /**
     Default Constructor
     */
    public ListLinked() {
        this(DEFAULT_CAPACITY);
    }

    /** The maximum size of the array for all queues */
    protected static final int DEFAULT_CAPACITY = 2000;

    /** The capacity of the instance array (set to default). "capacity" is the equivalent to items.length() and can be used interchangably. */
    private int capacity = DEFAULT_CAPACITY;

    /** The number of items in the array. */
    private int numberOfItems=0;


    

    // ************   SETTERS, GETTERS, toPrint, and ValidIndex ******************* //

    /**
     * determines if index given is valid or not
     * @param index the index to determine
     * @return whether or not the index is valid
     */
    public boolean validIndex(int index) {
        //if the structure is empty then return false
        if(isEmpty()) return false;

        //if the index is negative return false
        if(index < 0) return false;

        //if the index is bigger than number of items return false
        if(index >= numberOfItems) return false;

        //if it passes above conditions then index is valid
        return true;
    }

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
        //as we are using linked lists, it will always return false
        //linked lists are never full ;)
        return false;
    }

    /**
     @return True if structure has no elements in it
     */
    @Override
    public boolean isEmpty() {
        //if head and tail are both null then there is nothing in the list
        //or if there aren't any items in the list, it also implies the list has nothing
        if(((head == null) && (tail == null)) || (numberOfItems == 0)) {
            return true;
        }
        else {
            return false;
        }
        
    }

    /** Prints numbered list of objects T in linked list.  */
    @Override
    public String toString() {
        String printedList = "";

        //setting pointer and iterators
        Node p = head;
        int i = 1;
        
        //iterating through linked list
        while(p.next != null) {
            //printing out the index plus 1 (for aesthetic purposes) - changed i to start at 1 instead of zero
            printedList += (i) + ". " + p.data.toString() + "\n";
            //updating variables so that we don't get an infinite loop :)
            i++;
            p = p.next;
        }
        
        return printedList;
    } // end toString()

    // *******************   FIND, CONTAINS, PEEK *************** //

    /**
     * returns the T object at the index given, only if the object exists in linked list
     * @param index: the index to search at
     */
    @Override
    public T peek(int index) {
        //testing to see if index is valid
        if (!validIndex(index)) return null;
        
        //if the index is asking for the last item we can do that easily
        if(index == numberOfItems - 1) return tail.data;
        
        //creating searching variables
        Node p = head;
        int i = 0;
        
        //iterating through linked list
        while(p.next != null && i < index) {
            //updating variables so that we don't get an infinite loop :)
            i++;
            p = p.next;
        }
        
        //return data after we finish the while loop for finding the item's data
        return p.data;
    } // end peek(int)

    /**
     * find searches through data structure for object T and if found, returns the index of object T
     * @param T: the object to look for in data structure
     */
    @Override
    public int find(T T) {
        // testing to see if the Object is in the linked list
        if (!(contains(T))) return -1;

        // pointer and iterator variables
        Node p = head;
        int i = 0;

        // iterating through the linked list
        while (p.data != null) {
            // if the data is equal to the object
            if(p.data.equals(T)) {
                return i;
            }

            p = p.next;
            i++;
        }

        // if the object isn't found, return -1
        return -1;
    } // end find(T)

    /**
     * returns true if T is in the list, false otherwise
     * @param T: the T object that we are searching for
     */
    @Override
    public boolean contains(T T) {
        // pointer and iterator variables
        Node p = head;
        int i = 0;

        // iterating through the linked list
        while (p != null) {
            // checking to see if data equals object
            if(p.data.equals(T)) {
                return true;
            }

            p = p.next;
            i++;
        }

        // if item isn't found return false
        return false;
    } // end contains(T)

    /**
     * Return index of last occurring element in the list.
     * If element is not in list return -1
     * @param el the element to find in list
     * @return the index of the element or -1 if element is not found
     */
    public int lastIndexOf(T el) {
        // testing to see if the object is in the linked list
        if (!contains(el)) return -1;

        // pointer, iterator and index holder variables
        Node p = head;
        int i = 0;
        int lastIndex = -1;

        // iterating through the linked list
        while (p != null) {
            // if the data is equal to the object
            if(p.data.equals(el)) {
                lastIndex = i;
            }

            p = p.next;
            i++;
        }

        return lastIndex;
    } // end lastIndexOf(T el)

    /**
     * Return true if every element in "array" is also in the list
     * else return false
     * @param array the array of items to compare elements to
     * @return true if every element is the same, false if otherwise
     */
    public boolean containsAll(T[] array) {
        //checking to see if array is empty
        if (isEmpty()) return false;

        //pointer and iterator
        Node p = head;
        int i = 0;

        //iterating through the linked list
        while (p.next != null) {
            // if at any point any part of data doesn't match, return
            if (p.data != array[i]) return false;
            p = p.next;
            i++;
        }

        return true;
    } // end containsAll(T[] array)

    // *******************   ADD  ******************* //
    /**
     * adds the T object at the end of the linked list
     * @param T: the T object to add to the linked list
     */
    @Override
    public void add(T T) {
        Node p = new Node(T);
        //checking to see if adding to empty list
        // holder for capacity
        if(isEmpty()) {
            head = p;
            tail = p;
            numberOfItems++;
            return;
        }
        
        //So no need to traverse through if we have variable that stores the end
        //lowkey still not used to tail being a thing :)
        tail.next = p;
        tail = tail.next;
        numberOfItems++;
        
    } // end add(T)

    /**
     * adds object T at a certain index in the linked list
     * @param T: the T object to add to the linked list
     * @param index: the index at which to add the T object at in a linked list
     */
    @Override
    public void add(T T, int index) {
        //if the index is negative return false
        if(index < 0) return;

        //if the index is bigger than number of items return false
        if(index > numberOfItems) return;

        // pointer, iterator, and new node
        Node p = head;
        int i = 0;
        Node nodeToAdd = new Node(T);

        // test to see if it is empty
        if (isEmpty()) {
            head = nodeToAdd;
            tail = nodeToAdd;
            numberOfItems++;
            return;
        }

        // adding to the end of the list
        if (index == numberOfItems) {
            add(T);
            return;
        }

        // if index is zero, update head
        if (index == 0) {
            nodeToAdd.next = head;
            head = nodeToAdd;
            numberOfItems++;
            return;
        }

        // iterate to the index before the index given
        while(i < index - 1 && p.next != null) {
            p = p.next;
            i++;
        }

        // adding the new node to the index
        nodeToAdd.next = p.next;
        p.next = nodeToAdd;
        numberOfItems ++;
    } // end add(T, int)

    /**
     * adds all elements in the passed array to the list
     * @param toAdd: the array of elements to add to the list
     */
    @Override
    public void addAll(T[] toAdd) {
        // checking if list has space to add an item -- No need as list linked is never full

        // adding the element at the correct index
        for (int i = 0; i < toAdd.length; i++) {
            add(toAdd[i], i);
        }


    } // end addAll(T[] toAdd)

    /**
     * replaces an object T with another T object at a certain index
     * @param T: the new T that is replacing the other T object
     * @param index: the index of the object T that is being replaced
     */
    @Override
    public void set(T T, int index) {
        //if the index is negative return
        if(index < 0) return;

        //if the index is bigger than number of items return
        if(index >= numberOfItems) return;

        //pointer and iterator
        Node p = head;
        int i = 0;

        // test to see if it is empty
        if (isEmpty() && index != 0) return;

        // if index is zero, update head
        if (index == 0) {
            p.data = T;
            return;
        }

        // iterate to the index given
        while(i < index && p.next != null) {
            p = p.next;
            i++;
        }

        // setting the node's data
        p.data = T;
    } // end set(T, int)

    // *******************   REMOVE  ******************* //
    /**
     * Removes the T object at the specified index in the linked list
     * @param index: the index of the T object to be removed from the linked list
     */
    @Override
    public T remove(int index) {
        //testing to see if index is valid
        if (!validIndex(index)) return null;
        
        Node p = head;
        int i = 0;
        T data = null;
        
        //if item to remove is the first item in list
        if (index == 0) {
            data = p.data;
            head = p.next;
            numberOfItems--;
            return data;
        }
        
        //if item to remove is the last item in list
        if (index == numberOfItems - 1) {
            while(p.next.next != null && i <= index - 1) {
                i++;
                p = p.next;

            }
            tail = p;
            data = p.data;
            p.next = null;
            numberOfItems--;
            return data;
        }
        
        //iterating through linked list
        while(p.next != null && i < (index - 1)) {
            //updating variables so that we don't get an infinite loop :)
            i++;
            p = p.next;
        }
        
        //finding the value before the index we want
        // so that we can change pointers and cut out
        // node at specific index
        data = p.next.data;
        p.next = p.next.next;
        numberOfItems--;
        
        return data;

    } // end remove(int)

    /**
     * Removes the T object by searching through the list
     * @param T: the T object to remove from the list
     */
    @Override
    public void remove(T T) {
        // testing to see if item is in the linked list
        if(!contains(T)) return;

        // setting index variable to store info from find
        int index = -1;
        index = find(T);

        // removing the variable with information we got from find
        remove(index);
    } // end remove(T)

    /**
     * Removes all of the T objects by overwriting items as null
     */
    @Override
    public void removeAll() {
        // cutting off all connections to the start and end of list
        // and resetting the number of items
        head = null;
        tail = null;
        numberOfItems = 0;
    } // end removeAll()

    // *******************   CONVERT  ******************* // just needs java docs
    /**
     * Returns an array with the same number of items from the items list
     * @return the new array
     */
    @Override
    public T[] toArray() {
        // if it is empty
        if (isEmpty()) return null;

        // create a new array
        @SuppressWarnings("unchecked")
        T[] returnArray = (T[]) new Object[numberOfItems];

        // pointer and iterators
        Node p = head;
        int i = 0;

        // copy each element in the list into the new array
        while(p != null) {
            returnArray[i] = p.data;
            p = p.next;
            i++;
        }

        return returnArray;
    } // end toArray(T)

    /**
     * Returns an array with the same number of items from the items list
     * @param array: the array to add all of the items from items into
     */
    @Override
    public void toArray(T[] array) {
        // if array is not large enough to hold everything
        if (numberOfItems > capacity) return;

        // if linked list is empty return
        if (isEmpty()) return;

        // pointer and iterator variables
        Node p = head;
        int i = 0;

        // iterating through linked list and adding those values to the array
        while (p != null) {
            array[i] = p.data;
            p = p.next;
            i++;
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
        // if empty return null
        if (isEmpty()) return null;

        // if start or end are out of range
        if (start < 0) return null;
        if (end >= numberOfItems) return null;

        // if start > end
        if (start > end) return null;

        // pointer and iterator
        Node p = head;
        int i = 0;

        // create new sublist
        ListArray returnArray = new ListArray(end - (start - 1));


        // iterating to the starting point in the linked list
        for (i = 0; i < start; i++) {
            p = p.next;
        }

        // iterating until the end point in the linked list
        // and copying the data into the return array
        while (i <= end && p != null) {
            returnArray.add(p.data);
            p = p.next;
            i++;
        }

        // return new sublist
        return returnArray;
    } // end sublist()
}

