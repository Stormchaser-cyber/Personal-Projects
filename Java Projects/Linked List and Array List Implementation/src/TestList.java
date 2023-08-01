public class TestList {

    // If you want to test the array implementation, this should be true.
    // If you want to test the list implementation, this should be false.
    // Alternatively, if you are doing command line execution,
    // you can select lists with "java TestList link"
    static boolean testArray = true;

    // This allows for the same tests to be used with either implementation.
    // The first parameter is used to capture the type T of the list object.
    // The second is for setting the capacity HOWEVER notice that I am
    // only using the default constructor since linked lists do not have
    // a limit on size.
    public static <T> List<T> constructList(List<T> list, int size) {
        if (testArray) {
            list = new ListArray<T>(size);
        } else {
            list = new ListLinked<T>();
        }
        return list;
    }

    // The main function reads in the command-line arguments to determin
    // which test to conduct (if arguments are provided)
    // Then all test collections are executed.
    public static void main(String[] args) {
        if (args.length > 0) {
            if (args[0].equals("linked") || args[0].equals("link")) {
                testArray = false;
            }
        }

		/*
		Comment out any line below
		to skip the collection of tests related to that behavior.
		*/
        sanityCheck(); // done (linked lists and array lists)
        if (testArray) { testConstructorArray(); } // done (linked lists and array lists)
        else { testConstructorLinked(); } // done (linked lists and array lists)
        testSearches(); // done (linked lists and array lists)
        testAdds(testArray); // done (linked lists and array lists)
        testRemoves(); // done (linked lists and array lists )
        testConvert(); // done (linked lists )
    }

    // Each method has its own test method.
    public static void testSearches() {
        System.out.println("\n\nTesting List search methods ...");
        TestSearches.peek();
        TestSearches.find();
        TestSearches.contains();
        TestSearches.containsAll();
        TestSearches.lastIndexOf();
    }

    public static void testAdds(boolean testCapacity) {
        System.out.println("\n\nTesting List add methods ...");
        TestAdds.addSong(testCapacity);
        TestAdds.addAtIndex(testCapacity);
        TestAdds.addAll(testCapacity);
        TestAdds.set();
    }

    public static void testRemoves() {
        System.out.println("\n\nTesting List remove methods ...");
        TestRemoves.removeAtIndex();
        TestRemoves.removeSong();
        TestRemoves.removeAll();
    }

    public static void testConvert() {
        System.out.println("\n\nTesting List convert methods ...");
        TestConverts.toArrayReturnArray();
        TestConverts.toArrayVoid();
        TestConverts.sublist();
    }


    // This is a super simple test to ensure peek and add are working
    public static void sanityCheck() {
        // Initial add() and peek() test
        /* It is a bit of a chicken-and-egg problem.
         * To test add(), peek() needs to be correct to confirm song was added.
         * To test peek(), add() needs to be correct to confirm location of song.
         */
        System.out.println("\n\nSanity check test of add, length, peek.");
        System.out.println("If these initial tests do not pass, the rest of the tests are irrelevant!");

        Song iggy = new Song("Dum Dum Boys","Iggy Pop","The Idiot","1977");
        List<Song> songs = null;
        songs = constructList(songs,5);
        songs.add(iggy);
        assertEquals(1,songs.length(),"First Add (length)");
        assertEquals(iggy,songs.peek(0),"First Add (iggy)");
    }

    // There are unique tests for the constructor of each implementation type.
    // All the other tests apply to both arrays and linked lists
    public static void testConstructorArray() {
        System.out.println("\n\nTesting ListArray constructor with capacity ...");

        // Test default constructor and getters
        ListArray<Integer> testDefault = new ListArray<>();
        assertEquals(2000, testDefault.capacity(), "ListArray() capacity");
        assertEquals(0, testDefault.length(), "ListArray() length");
        assertEquals(true, testDefault.isEmpty(), "ListArray() isEmpty");

        // Test constructor with size parameter
        ListArray<Integer> testSized = new ListArray<>(3);
        assertEquals(0, testSized.length(), "ListArray(5) length");
        assertEquals(3, testSized.capacity(), "ListArray(5) capacity");
        assertEquals(true, testSized.isEmpty(), "ListArray(5) isEmpty");

        // Test length getter (tested at 0 above)
        testSized.add(0);
        assertEquals(1,testSized.length(),"ListArray length()");

        // Test isEmpty and isFull.
        testSized = new ListArray<>(3);
        assertEquals(false, testSized.isFull(), "isFull - empty");
        assertEquals(true, testSized.isEmpty(), "isEmpty");

        testSized.add(0);
        assertEquals(false, testSized.isFull(), "isFull - 1 of 3");
        assertEquals(false, testSized.isEmpty(), "isEmpty - 1 of 3");

        testSized.add(1);
        testSized.add(2);
        assertEquals(true, testSized.isFull(), "isFull - 3 of 3");
        assertEquals(false, testSized.isEmpty(), "isEmpty - 3 of 3");
    }

    public static void testConstructorLinked() {
        System.out.println("\n\nTesting ListLinked constructor  ...");

        // Test default constructor and getters
        ListLinked<Integer> testDefault = new ListLinked<>();
        assertEquals(0, testDefault.length(), "ListLinked() length");
        assertEquals(true, testDefault.isEmpty(), "ListLinked() isEmpty");

        // Test length getter (tested at 0 above)
        testDefault.add(0);
        assertEquals(1,testDefault.length(),"ListLinked length()");

        // Test isEmpty and isFull.
        ListLinked<Integer> testSized = new ListLinked<>(3);
        assertEquals(false, testSized.isFull(), "isFull - empty");
        assertEquals(true, testSized.isEmpty(), "isEmpty");

        testSized.add(0);
        assertEquals(false, testSized.isFull(), "isFull - 1 of 3");
        assertEquals(false, testSized.isEmpty(), "isEmpty - 1 of 3");

        testSized.add(1);
        testSized.add(2);
        assertEquals(false, testSized.isFull(), "isFull - 3 of 3");
        assertEquals(false, testSized.isEmpty(), "isEmpty - 3 of 3");
    }

    // -------------------------------   assertEquals --------------------
    // -------------------------------------------------------------------
    static void assertEquals(Song expect, Song result, String msg) {
        if (null==expect && null==result) {
            return;
        }
        if (null==expect || !expect.equals(result)) {
            System.out.println("ERROR: "+msg+". Expected "+expect+". Result "+result);
        }
    }

    static void assertEquals(int expect, int result, String msg) {
        if (expect != result) {
            System.out.println("ERROR: "+msg+". Expected "+expect+". Result "+result);
        }
    }
    static void assertEquals(boolean expect, boolean result, String msg) {
        if (expect != result) {
            System.out.println("ERROR: "+msg+". Expected "+expect+". Result "+result);
        }
    }
} // end class Main