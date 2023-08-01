### Lab 3 : Building a LIST Abstract Data Type
#### Due Tuesday, October 6 before class
#### Submit a single zipped file to Moodle

<hr>

Learning Outcomes:

- Know how to implement a _List_ using an array.
- Understand and implement the primary operations of add, remove, and find.
- Work with text files to read in data.
- Practiced in code documentation using Javadocs.
- Practiced in code testing to verify correctness.

<hr>

Provided Files:

- **Main.java** : compile and execute this file. It has main()
- **Song.java** : class of the objects to be stored using a List
- **List.java** : implements the ADT List of songs using an Array
- **SongList.java** : extends List providing Song-specific functionality
- **test.csv** : small set of songs for testing
- **postpunk.csv** : larger set of songs for testing

<hr>

General Requirements:

1. Complete the methods that perform the primary operations of add, remove, and find.
2. Write tests to verify code.
3. Document all code with Javadocs.
4. Learn how to save data to a .csv file.

<hr>

Assessment:

- 20% : complete, test file compiles and executes without throwing exception 
- 10% : javadocs
- 10% : tests (in collaboration with peer group)
- 05% : isEmpty, isFull, Song.equals
- 10% : search methods
- 15% : add methods
- 15% : remove methods
- 10% : convert methods
- 05% : SaveToFile methods and tests.

<hr>

## LISTS

The Abstract Data Type (ADT) **_List_** refers to a collection of elements, which is maintained with primary operators that add, remove, find, and sort elements. In your reading, they distinguish between a list, bag, and set. I will make no such distinctions and refer to all of them as a list, but I will be specific in whether or not those elements are unique (i.e. a set) or in a sorted order. The operators applied to a list must be explicitly defined, unlike with queues in which elements are added and removed from specific locations.

It is important to have a shared understanding of the list qualities and how the operators modify the list. For this lab:
- The list may have duplicate elements. It is not sorted.
- If no positional information is provided, the `add()` operator will add to the END of the list.
- If positional information is provided, the `add()` operator will add at the INDEX indicated, shifting elements to make room.
- If positional information is provided, the 'remove()' operator will remove at the INDEX indicated, shifting elements so that no empty spaces are left between elements. It will return the removed element.

<hr>

### Javadocs Documentation

1. AS you are coding, add javadocs comments where appropriate. Documentation for javadocs can be found here:
    - [https://www.oracle.com/technetwork/java/javase/documentation/index-137868.html]
    - [https://www.tutorialspoint.com/java/java_documentation.htm]

    _Where Appropriate_ means that ...
    - all classes have a javadoc comment above its definition
    - all methods within a class are documented and include @param, @return, etc. as appropriate (the one exception is setters and getters, which are usually self-explanatory thus do not need documentation)
    - most class members/attributes/fields (pick your favorite vocabulary term) are documented. Most, if not all of these, have been done for you.

2. When complete, generate javadocs and inspect your work. If you are in NetBeans, there is an option to generate Javadoc documentation from the pulldown _Run_ menu. Alternatively, you can create a `docs` folder inside the Lab2 folder. Compile javadoc comments from within that folder. This will generate a collection of web pages. If at the command prompt, it looks like this:
  ```
  mkdir docs
  cd docs
  javadoc ../*.java
  ```
  You can view the results by opening the index.html file.

<hr>

> Note that you can use other methods in the class to take advantage of code you already wrote. If you find yourself copying and pasting code, ask yourself if that is what you really want to do, because perhaps you just want to call the function in which that code exists.

<hr>

### Search Methods

3. Implement the `peek(int index)` method.
    - If the index is valid, return the element at that index. It should not be removed, just returned.
    - If the index is not valid, return null.

4. Implement the `find(Song song)` method.
    - If the song is in the list, return its index.
    - If the song is not in the list, return -1.

5. Implement the `contains(Song song)` method.
    - If the song is in the list, return True, else False.

<hr>

### ADD Methods

6. Implement the method `add(Song song)`.
    - If the structure is not full, add to the end of the list. (The _end_ of the list refers to after the last element in the list.)
    - If the structure is full, print an error message. Do not add anything to the list.
    - Maintain the numberOfItems.

7. Implement the method `add(Song song, int index)`.
    - If the index is invalid (i.e. a location that leaves space between elements or is out of range), print an error message. Do not add the song to the list.
    - If the structure is not full and the index is valid, add the element at the index indicated.
    - Shift all elements as appropriate BEFORE adding the element to make space for it.
    - If the structure is full, print an error message. Do not add anything to the list.
    - Maintain the numberOfItems.

8. Implement the method `set(Song song, int index)`.
    - If the index is invalid (i.e. a location that does not currently contain an element or is out of array bounds), print an error message. Do not add the song to the list. 
    - This writes over the current element at index.

<hr>

### REMOVE Methods

9. Implement the method `remove(int index)`.
    - If the index is invalid, print an error message and return null. Do not remove anything.
    - If there is an element at _index_, remove the element (save it so you can return it).
    - Shift all elements as appropriate so that there are no empty spaces between elements in the list.
    - Place a null appropriately so that everything at or past the index of _numberOfItems_ is null.
    - Maintain the numberOfItems.

10. Implement the method `remove(Song song)`.
    - If the song is in the list (based on equals), remove it from the list.
    - Shift all elements as appropriate so that there are no empty spaces between elements in the list.
    - Place a null appropriately so that everything at or past the index of _numberOfItems_ is null.
    - This is a _void_ function. There is no need to return the song, because that is what was passed in, so the caller already has it.
    - If the song is not in the list, print an error message and return null.
    - Maintain the numberOfItems.

11. Implement the remove method `removeAll()`.
    - Remove all songs from the list. The array should have only _null_ elements. (HINT for Efficiency: You do not have to iterate over the array.)
    - Set the numberOfItems to 0.

<hr>

### CONVERT Methods

12. Implement the method `Song[] toArray()`.
    - Return an array containing all songs in the List.
    - The length of the array should be the number of items stored.
    - If the List is empty, return null.

13. Implement the method `List sublist(int start, int end)`.
    - Return a new List that contains the songs from index "start" to indes "end", inclusive.
    - if indices are invalid, print a message and return null;
    - if the sublist is empty, return a List with no songs.

<hr>

### Save To File

19. Complete the method `saveToFile(String filename)` in _SongList.java_. If you are getting an error that the file cannot be opened, look at #9 below.


<hr>
Professor Kerlin gave me some tips to give to you about setting up your NetBeans projects.

1. Create a new NetBeans **_Ant_** project that is a Java Application.
2. At the next window, name your project (e.g. Lab2) and be sure to **uncheck** the box to _Create Main Class_. Pay attention to where NetBeans is placing your project folders.
3. GO TO YOUR FINDER (do not move files around from inside NetBeans) and locate the _src_ folder inside your new project folder.
4. Copy all the files into this src folder.
5. I then had to reload NetBeans to see the files in the defaultPackage folder in the left _project_ sidebar under _Lab2_.
6. Select _Project Properties (Lab2)_ from the pulldown File menu.
7. Click on the "Run" option in left sidebar.
8. In the _Main Class_ field, type "Main" because that is the name of the class that holds `main()`.
9. Click the green arrow button to run, and you will probably see the error message "Cannot open test.csv". You have to locate your working directory and give a path relative to that. You probably need to add `src/` to the front of the filename:
`songs.loadFromFile("src/test.csv");`

