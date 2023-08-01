public class  List {

  /**
  * This is a list of Song objects implemented with an array. The list may have duplicates. It is not in sorted order. Add and remove operate at the end of the list by default, but positional information can be provided to operate in the middle of the list.
  */

  /** Array to store items in queue */
  protected Song[] items;

  /** The maximum size of the array for all queues */
  protected static final int DEFAULT_CAPACITY = 2000;

  /** The capacity of the instance array (set to default). "capacity" is the equivalent to items.length() and can be used interchangably. */
  private int capacity = DEFAULT_CAPACITY;

  /** The number of items in the array. */
  private int numberOfItems=0;


  // ============== 2 Overloaded Constructors ============== //

  /**
  Constructor
  @param size The capacity of the list (i.e. max number of elements)
  */
  public List(int size) {
    capacity = size;
    items = new Song[capacity];
  }

  /**
  Default Constructor
  */
  public List() {
    this(DEFAULT_CAPACITY);
  }

  // ************   SETTERS, GETTERS, toPrint ******************* //

  public int length() { return numberOfItems; }

  public int capacity() { return capacity; }

  /**
  @return True if structure is at capacity.
  */
  public boolean isFull(){
    if (numberOfItems <= DEFAULT_CAPACITY) {
      return false;
    } else {
      return true;
    }
  }

  /**
  @return True if no elements in data structure.
  */
  public boolean isEmpty() {
    if (numberOfItems == 0) {
      return true;
    } else {
      return false;
    }
  }

  /** Prints numbered list of songs.  */
  @Override
  public String toString() {
    String printedList = "";
    for (int i=0; i<numberOfItems; i++) {
      printedList += (i+1) + ". " + items[i].toString() + "\n";
    }
    return printedList;
  } // end toString()

  // *******************   FIND, CONTAINS, PEEK *************** //

  /**
   * peek returns the song at the index given if it exists
   * @param index: the index to look at
  */
  public Song peek(int index) {
    if(index < length() && index > 0)  {
      return items[index];
    } else {
      return null;
    }
  } // end peek(int)

  /**
   * find returns the index of the song if it exists
   * @param song: the song to look for
  */
  public int find(Song song) {
    boolean flag = false;
    int index = -1;
    for (int i = 0; i < numberOfItems; i++) {
      if(items[i].equals(song)){
        flag = true;
        index = i;
      }
    }
    if(flag) {
      return index;
    } else {
      return -1;
    }
  } // end find(Song)

  /**
   * contains returns true or false depending on if the song is in the song list or not
   * @param song: the song that we are checking
  */
  public boolean contains(Song song) {

    int contains = find(song);
    if (contains == -1) {
      return false;
    } else {
      return true;
    }
  } // end contains(Song)

  // *******************   ADD  ******************* //
  /**
   * add(song) adds the song at the end of the array
   * @param song: the song to add
  */
  public void add(Song song) {
    if(isFull()) {
      System.err.println("Song list is full: unable to add song");
    } else {
      items[numberOfItems] = song;
      numberOfItems += 1;
    }

  } // end add(song)

  /**
   * add(song, index) adds a song at a certain index
   * @param song: the song to add
   * @param index: the index at which to add the song at
  */
  public void add(Song song, int index) {
    if(index < length() || index > 0) {
      if (!(isFull())) {
        for (int i = numberOfItems; i >= index; i--) {
          items[i] = items[i-1];
        }
        items[index] = song;
        numberOfItems += 1;
      } else {
        System.err.println("Song list is full: unable to add song");
      }
    } else {
      System.err.println("Invalid index: unable to add song to index");
    }
  } // end add(Song, int)

    /**
     * set(song, index) replaces a song with another song at a certain index
     * @param song: the new song that is being placed in list
     * @param index: the index of that item
  */
  public void set(Song song, int index) {
    if(peek(index) != null) {
      if (!(isFull())) {
        items[index] = song;
      }
    }

  } // end set(Song, int)

    // *******************   REMOVE  ******************* //
  /**
   * Removes the song at the index
   * @param index: the index of the song to be removed
  */
  public Song remove(int index) {
    if (peek(index) != null) {
      items[index] = null;
      numberOfItems -= 1;
    }
    return null;
  } // end remove(int)

  /**
   * Removes the song by searching through the list
   * @param song: the song to remove from the list
  */
  public void remove(Song song) {
    if (contains(song)) {
      int index = find(song);
      items[index] = null;
      numberOfItems -= 1;
    }
  } // end remove(Song)

  /**
   * Removes all of the songs by overwritting items
  */
  public void removeAll() {
    items = new Song[length()];
    numberOfItems = 0;
  } // end removeAll()

  // *******************   CONVERT  ******************* //
  /**
  */
  public Song[] toArray() {
    return null;
  } // end toArray(song)

  /**
   */
  public Song[] sublist(int start, int end) {

    return null;
  } // end sublist()

} // end class List
