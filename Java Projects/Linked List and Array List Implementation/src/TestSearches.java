public class TestSearches {

    static Song iggy = new Song("Dum Dum Boys","Iggy Pop","The Idiot","1977");
    static Song pil = new Song("Swan Lake","Public Image Ltd.","Second Edition","1980");
    static Song femmes = new Song("Add It Up","Violent Femmes","Violent Femmes","1983");

    // check that search compares values (ignoring case), not references
    static Song iggy2 = new Song("Dum Dum boys","Iggy pop","The idiot","1977");
    static Song pil2 = new Song("Swan lake","Public image Ltd.","Second edition","1980");
    static Song femmes2 = new Song("Add it up","violent femmes","violent femmes","1983");
    static Song s = new Song("a", "b", "c");


    // -------------------------------   peek     --------------------
    // -------------------------------------------------------------------
    public static void peek() {
        System.out.println("---Testing peek ...");

        List<Song> songs = null;
        songs = TestList.constructList(songs,5);
        songs.add(iggy);
        songs.add(pil);
        songs.add(femmes);

        //testing peek
        TestList.assertEquals(iggy,songs.peek(0),"Peek at index 0");
        TestList.assertEquals(pil,songs.peek(1),"Peek at index 1");
        TestList.assertEquals(femmes,songs.peek(2),"Peek at index 2");
        TestList.assertEquals(null,songs.peek(4), "Peek invalid >length");
        TestList.assertEquals(null,songs.peek(-1), "Peek invalid -1");
    }

    // -------------------------------   find     --------------------
    // -------------------------------------------------------------------
    public static void find() {
        System.out.println("---Testing find ...");
        //Testing find
        List<Song> songs = null;
        songs = TestList.constructList(songs,5);
        songs.add(iggy);
        songs.add(pil);
        songs.add(femmes);
        TestList.assertEquals(0, songs.find(iggy2), "Find first");
        TestList.assertEquals(1, songs.find(pil2), "Find middle");
        TestList.assertEquals(2,songs.find(femmes2), "Find last");
        TestList.assertEquals(-1, songs.find(s), "find not in list");
    } // end find

    // -------------------------------   contains     --------------------
    // -------------------------------------------------------------------
    public static void contains() {
        System.out.println("---Testing contains ...");
        List<Song> songs = null;
        songs = TestList.constructList(songs,5);
        songs.add(iggy);
        songs.add(pil);
        songs.add(femmes);
        TestList.assertEquals(true, songs.contains(iggy2),"Contains in list");
        TestList.assertEquals(false,songs.contains(s),"Contains() not in list.");
    } // end contains

    public static void containsAll() {
        System.out.println("---Testing containsAll ...");
        List<Integer> numbers = null;
        numbers = TestList.constructList(numbers,5);
        Integer[] testArray = { 0,1,2 };
        TestList.assertEquals(false,numbers.containsAll(testArray),"Empty List contains all.");

        for (int i=0; i<3; i++) {
            numbers.add(i);
        }
        TestList.assertEquals(true,numbers.containsAll(testArray),"List matches array.");
    } // end containsAll

    // -------------------------------  lastIndex     --------------------
    // -------------------------------------------------------------------
    public static void lastIndexOf() {
        System.out.println("---Testing lastIndexOf ...");

        List<Integer> numbers = null;
        numbers = TestList.constructList(numbers,10);
        Integer[] testLast = {1,2,3,2,3,2,3};
        for (Integer i : testLast) {
            numbers.add(i);
        }
        TestList.assertEquals(6,numbers.lastIndexOf(3),"Last in list.");
        TestList.assertEquals(0,numbers.lastIndexOf(1),"First in list.");
        TestList.assertEquals(5,numbers.lastIndexOf(2),"Middle of list");
        TestList.assertEquals(-1,numbers.lastIndexOf(4),"Not in list.");
    } // end lastIndexOf

} // end TestSearches
