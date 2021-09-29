public interface List<T> {

  /**
   * Interface of List for generic types (Object T)
   * It sets the foundation for our ListArray class
   */



  // ************   SETTERS, GETTERS, toPrint ******************* //

  public int length();

  public int capacity();

  /**
  @return True if structure is full
  */
  public boolean isFull();

  /**
  @return True if structure has no elements in it
  */
  public boolean isEmpty();

  // *******************   FIND, CONTAINS, PEEK *************** //

  /**
   * returns the T object at the index given, only if the object exists
   * @param index: the index to search
  */
  public T peek(int index);

  /**
   * find searches through data structure for object T and if found, returns the index of object T
   * @param T: the object to look for in data structure
  */
  public int find(T T);

  /**
   * returns true if T is in the list, false otherwise
   * @param T: the T object that we are searching for
  */
  public boolean contains(T T);

  /**
   * Return index of last occurring element in the list.
   * If element is not in list return -1
   * @param el the element to find in list
   */
  public int lastIndexOf(T el);

  /**
   * Return true if every element in "array" is also in the list
   * else return false
   * @param array the array of items to compare elements to
   */
  public boolean containsAll(T[] array);

  // *******************   ADD  ******************* //
  /**
   * adds the T object at the end of the array
   * @param T: the T object to add to the array
  */
  public void add(T T);


  /**
   * adds object T at a certain index
   * @param T: the T object to add to array
   * @param index: the index at which to add the T object at in array
  */
  public void add(T T, int index);

  /**
   * adds all elements in the passed array to the list
   * @param toAdd: the array of elements to add to the list
   */
  public void addAll(T[] toAdd);

    /**
     * replaces an object T with another T object at a certain index
     * @param T: the new T that is replacing the other T object
     * @param index: the index of the object T that is being replaced
  */
  public void set(T T, int index);

    // *******************   REMOVE  ******************* //
  /**
   * Removes the T object at the specified index
   * @param index: the index of the T object to be removed
  */
  public T remove(int index);

  /**
   * Removes the T object by searching through the list
   * @param T: the T object to remove from the list
  */
  public void remove(T T);

  /**
   * Removes all of the T objects by overwriting items as null
  */
  public void removeAll();

  // *******************   CONVERT  ******************* //
  /**
   * Returns an array with the same number of items from the items list
   * @return the new array
  */
  public T[] toArray();

  /**
   * Returns an array with the same number of items from the items list
   * @param array: the array to add all of the items from items into
   */
  public void toArray(T[] array);

  /**
   * Returns a sublist of the original list between the start and end parameters (as indices)
   * @param start: the starting index of the list
   * @param end: the ending index of the list
   * @return List array which is a sublist of the original list
   */
  public List<T> sublist(int start, int end);

} // end class List
