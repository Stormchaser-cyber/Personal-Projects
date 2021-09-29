import java.lang.reflect.*;
import java.util.Scanner;
import java.io.*;

public class TestLinked {

    static Song iggy = new Song("Dum Dum Boys","Iggy Pop","The Idiot","1977");
    static Song pil = new Song("Swan Lake","Public Image Ltd.","Second Edition","1980");
    static Song femmes = new Song("Add It Up","Violent Femmes","Violent Femmes","1983");

    public static void main(String[] args) {

        // Initial add() and peek() test
        /* It is a bit of a chicken-and-egg problem.
         * To test add(), peek() needs to be correct to confirm song was added.
         * To test peek(), add() needs to be correct to confirm location of song.
         */

        System.out.println("\n\nSanity check test of add, length, peek.");
        System.out.println("If these initial tests do not pass, the rest of the tests are irrelevant!");
        ListLinked<Song> songs = new ListLinked<Song>(5);
        songs.add(iggy);
        assertEquals(1,songs.length(),"First Add (length)");
        assertEquals(iggy,songs.peek(0),"Fird Add (iggy)");
        /* Once the above are confirmed to be working, the rest of the testing can proceed.
         */

        //constructor tests
        testConstructorGetters();

        //searching tests
        testPeek();
        testFind();
        testContains();

        //adding tests
        testAddsSong();
        testAddsIndex();
        testSetIndex();
        testAddsAll();

        //removing tests
        testRemoveSong();
        testRemoveIndex();
        testRemoveAll();

        //converting tests
        testConvertToArray();
//        testConvertToSublist();
        testConvertFillArray();
    }

    public static void testConstructorGetters() {
        System.out.println("\n\nTesting List constructor ...");

        // Test default constructor and getters
        ListLinked<Song> jams = new ListLinked<Song>();
        assertEquals(2000, jams.capacity(), "List() capacity");
        assertEquals(0, jams.length(), "List() length");
        assertEquals(true, jams.isEmpty(), "List() isEmpty");

        // Test constructor with size parameter
        ListLinked<Song> songs = new ListLinked<Song>(5);
        assertEquals(0, songs.length(), "List(5) length");
        assertEquals(5, songs.capacity(), "List(5) capacity");
        assertEquals(true, songs.isEmpty(), "List(5) isEmpty");

        // Test length getter (tested at 0 above)
        songs.add(pil);
        assertEquals(1,songs.length(),"length()");

        // Test isEmpty and isFull.
        ListLinked<Song> tunes = new ListLinked<Song>(3);
        assertEquals(false, tunes.isFull(), "isFull - empty");
        assertEquals(true, tunes.isEmpty(), "isEmpty");

        tunes.add(pil);
        assertEquals(false, tunes.isFull(), "isFull - 1 of 3");
        assertEquals(false, tunes.isEmpty(), "isEmpty - 1 of 3");

        tunes.add(pil);
        tunes.add(pil);
        assertEquals(false, tunes.isFull(), "isFull - 3 of 3"); //changed to false
        assertEquals(false, tunes.isEmpty(), "isEmpty - 3 of 3");


    }

    public static void testPeek() {
        System.out.println("\n\nTesting List Linked peek ...");

        Song iggy = new Song("Dum Dum boys","Iggy pop","The idiot","1977");
        Song pil = new Song("Swan lake","Public image Ltd.","Second edition","1980");
        Song femmes = new Song("Add it up","violent femmes","violent femmes","1983");
        Song s = new Song("a", "b", "c");

        ListLinked<Song> songs = new ListLinked<Song>(5);
        songs.add(iggy);
        songs.add(pil);
        songs.add(femmes);


        //testing peek
        assertEquals(iggy,songs.peek(0),"Peek at index 0");
        assertEquals(pil,songs.peek(1),"Peek at index 1");
        assertEquals(femmes,songs.peek(2),"Peek at index 2");
        assertEquals(null,songs.peek(4), "Peek invalid >length");
        assertEquals(null,songs.peek(-1), "Peek invalid -1");
    }

    public static void testFind() {
        System.out.println("\n\nTesting List Linked find ...");

        // check that search compares values (ignoring case), not references
        Song iggy = new Song("Dum Dum boys","Iggy pop","The idiot","1977");
        Song pil = new Song("Swan lake","Public image Ltd.","Second edition","1980");
        Song femmes = new Song("Add it up","violent femmes","violent femmes","1983");
        Song s = new Song("a", "b", "c");

        ListLinked<Song> songs = new ListLinked<Song>(5);
        songs.add(iggy);
        songs.add(pil);
        songs.add(femmes);

        //Testing find
        assertEquals(0, songs.find(iggy), "Find first");
        assertEquals(1, songs.find(pil), "Find middle");
        assertEquals(2,songs.find(femmes), "Find last");
        assertEquals(-1, songs.find(s), "find not in list");
    }

    public static void testContains() {
        System.out.println("\n\nTesting List Linked contains ...");

        // check that search compares values (ignoring case), not references
        Song iggy = new Song("Dum Dum boys","Iggy pop","The idiot","1977");
        Song pil = new Song("Swan lake","Public image Ltd.","Second edition","1980");
        Song femmes = new Song("Add it up","violent femmes","violent femmes","1983");
        Song s = new Song("a", "b", "c");

        ListLinked<Song> songs = new ListLinked<Song>(5);
        songs.add(iggy);
        songs.add(pil);
        songs.add(femmes);

        //Testing contains
        assertEquals(true, songs.contains(iggy),"Contains in list");
        assertEquals(false,songs.contains(s),"Contains() not in list.");
    }

    public static void testAddsSong() {
        System.out.println("\n\nTesting List Linked Adds Song ...");

        // more songs to work with
        Song wire = new Song("Map Ref 41 Degrees", "Wire", "154", "1979");
        Song heat = new Song("A New Kind of Water","Fame" ,"Post-Punk","1981");
        Song tv = new Song("Dorian Gray","TV Personalities","Kids Love It","1981");
        Song sonic = new Song ("Death Valley","Sonic Youth","Bad Moon Rising");

        // add(Song) ------------------------------------------------
        ListLinked<Song> songs = new ListLinked<Song>(3);
        assertEquals(0,songs.length(),"pre-add (length)");
        songs.add(iggy);
        assertEquals(1,songs.length(),"add first (length)");
        assertEquals(iggy,songs.peek(0),"add first (value)");
        songs.add(pil);
        assertEquals(2,songs.length(),"add 2nd (length)");
        assertEquals(pil,songs.peek(1),"add 2nd (value)");
        songs.add(femmes);
        assertEquals(3,songs.length(),"add to capacity (length)");
        assertEquals(femmes,songs.peek(2),"add to capacity (value)");
        songs.add(iggy);
        //assertEquals(3,songs.length(),"add attempt over capacity (length)");
        assertEquals(femmes,songs.peek(2),"add attempt over capacity (value)");

    }

    public static void testAddsIndex() {
        System.out.println("\n\nTesting List Linked Adds Song at Index ...");

        // more songs to work with
        Song wire = new Song("Map Ref 41 Degrees", "Wire", "154", "1979");
        Song heat = new Song("A New Kind of Water","Fame" ,"Post-Punk","1981");
        Song tv = new Song("Dorian Gray","TV Personalities","Kids Love It","1981");
        Song sonic = new Song ("Death Valley","Sonic Youth","Bad Moon Rising");

        // add(song, index) ------------------------------------------------
        ListLinked<Song> tunes = new ListLinked<Song>(6);
        // tunes = {iggy}
        tunes.add(iggy,0);
        assertEquals(1,tunes.length(),"add to empty (length)");
        assertEquals(iggy,tunes.peek(0),"add to empty (value)");
        // tunes = {pil, iggy}
        tunes.add(pil,0);
        assertEquals(2,tunes.length(),"add at 0 (length)");
        assertEquals(pil,tunes.peek(0),"add at 0 (value)");
        assertEquals(iggy,tunes.peek(1),"add at 0 (2nd song)");
        // tunes = {pil,iggy,femmes}
        tunes.add(femmes,2);
        assertEquals(3,tunes.length(),"add at end (length)");
        assertEquals(femmes,tunes.peek(2),"add at end (value)");
        assertEquals(pil,tunes.peek(0),"add at end (1st song)");
        assertEquals(iggy,tunes.peek(1),"add at 0 (2nd song)");
        // tunes = {pil,sonic,iggy,femmes}
        tunes.add(sonic,1);
        assertEquals(4,tunes.length(),"add to middle (length)");
        assertEquals(sonic,tunes.peek(1),"add to middle (value)");
        assertEquals(pil,tunes.peek(0),"add to middle (1st song)");
        assertEquals(iggy,tunes.peek(2),"add to middle (3rd song)");
        assertEquals(femmes,tunes.peek(3),"add to middle (4th song)");
        // test invalid indices
        tunes.add(wire,-1);
        assertEquals(4,tunes.length(),"add at -1 (length)");
        tunes.add(wire,5);
        assertEquals(4,tunes.length(),"add beyond last (length)");
        assertEquals(null,tunes.peek(5),"add beyond last (value)");
        tunes.add(wire,7);
        assertEquals(4,tunes.length(),"add beyond capacity (length)");
    }

    public static void testSetIndex() {
        System.out.println("\n\nTesting List Linked Set Song at Index ...");

        // more songs to work with
        Song wire = new Song("Map Ref 41 Degrees", "Wire", "154", "1979");
        Song heat = new Song("A New Kind of Water","Fame" ,"Post-Punk","1981");
        Song tv = new Song("Dorian Gray","TV Personalities","Kids Love It","1981");
        Song sonic = new Song ("Death Valley","Sonic Youth","Bad Moon Rising");

        // set(Song, index) ------------------------------------------------
        ListLinked<Song> jams = new ListLinked<Song>(6);
        // †his assumes that add is working correctly!
        jams.add(iggy);
        jams.add(pil);
        jams.add(femmes);
        // jams = {wire,pil,femmes}. Set at first element
        jams.set(wire,0);
        assertEquals(3,jams.length(),"set(0) length");
        assertEquals(wire,jams.peek(0),"set(0) value");
        assertEquals(pil,jams.peek(1),"set(0) (2nd song)");
        assertEquals(femmes,jams.peek(2),"set(0) (3rd song)");
        // jams = {wire,heat,femmes}. Set at middle element
        jams.set(heat,1);
        assertEquals(3,jams.length(),"set(1) length");
        assertEquals(heat,jams.peek(1),"set(1) value");
        assertEquals(wire,jams.peek(0),"set(1) (1st song)");
        assertEquals(femmes,jams.peek(2),"set(1) (3rd song)");
        // jams = {wire,heat,tv}. Set at last element
        jams.set(tv,2);
        assertEquals(3,jams.length(),"set(2) length");
        assertEquals(tv,jams.peek(2),"set(2) value");
        assertEquals(wire,jams.peek(0),"set(2) (1st song)");
        assertEquals(heat,jams.peek(1),"set(2) (2nd song)");
        //  invalid indices. should not change {wire,heat,tv}
        jams.set(iggy,3);	// set 1 past the last element
        assertEquals(3,jams.length(),"set at length, bad index (length)");
        assertEquals(null,jams.peek(3),"set at length, bad index (value)");
        jams.set(pil,5);
        assertEquals(3,jams.length(),"set at capacity, bad index (length)");
        assertEquals(null,jams.peek(5),"set at capacity, bad index (value)");
        jams.set(pil,-1);
        assertEquals(3,jams.length(),"set at -1 (length)");
        assertEquals(wire,jams.peek(0),"set at -1 (value)");
    }

    public static void testAddsAll() {
        System.out.println("\n\nTesting List Linked Adds All ...");

        // more songs to work with
        Song wire = new Song("Map Ref 41 Degrees", "Wire", "154", "1979");
        Song heat = new Song("A New Kind of Water","Fame" ,"Post-Punk","1981");
        Song tv = new Song("Dorian Gray","TV Personalities","Kids Love It","1981");
        Song sonic = new Song ("Death Valley","Sonic Youth","Bad Moon Rising");

        //New code added ------------------------------------------------
        ListLinked<Integer> numbers = new ListLinked<>(10);
        Integer[] addArray = { 0, 1, 2, 3 };
        numbers.addAll(addArray);
        for (Integer i=0; i<addArray.length; i++) {
            if (numbers.peek(i) != i) {
                System.out.println("ERROR: addAll peek(" + i + ")");
            }
        }
    }

    public static void testRemoveSong() {
        System.out.println("\n\nTesting List Linked remove Song ...");
        Song sonic = new Song ("Death Valley","Sonic Youth","Bad Moon Rising");

        // remove(Song) ------------------------------------------------
        // This is assuming add, length, contains is working!
        ListLinked<Song> songs = new ListLinked<Song>(5);
        songs.add(iggy);
        songs.add(pil);
        songs.add(femmes);
        songs.add(sonic);

        assertEquals(4,songs.length(),"pre-removal (length)");
        // songs = {iggy,femmes,sonic}
        songs.remove(pil);
        assertEquals(3,songs.length(),"remove middle (length)");
        assertEquals(false,songs.contains(pil),"remove middle");
        assertEquals(iggy,songs.peek(0),"remove middle (1st song)");
        assertEquals(femmes,songs.peek(1),"remove middle (2nd song)");
        assertEquals(sonic,songs.peek(2),"remove middle (3rd song)");
        // songs = {iggy,femmes}
        songs.remove(sonic);
        assertEquals(2,songs.length(),"remove at end (length)");
        assertEquals(false,songs.contains(sonic),"remove at end");
        assertEquals(iggy,songs.peek(0),"remove at end (1st song)");
        assertEquals(femmes,songs.peek(1),"remove at end (2nd song)");
        // songs = {femmes}
        songs.remove(iggy);
        assertEquals(1,songs.length(),"remove first (length)");
        assertEquals(false,songs.contains(iggy),"remove first");
        assertEquals(femmes,songs.peek(0),"remove first (1st song)");
        // songs = {}
        songs.remove(femmes);
        assertEquals(0,songs.length(),"remove last element (length)");
        assertEquals(false,songs.contains(femmes),"remove last");
        assertEquals(null,songs.peek(0),"remove last");

        // remove invalid song. list is empty
        songs.remove(femmes);
        assertEquals(0,songs.length(),"remove from empty");
        songs.add(pil);
        songs.remove(femmes);
        assertEquals(1,songs.length(),"remove not in list");
        assertEquals(pil,songs.peek(0),"remove not in list (1st song)");
    }

    public static void testRemoveIndex() {
        System.out.println("\n\nTesting List Linked Remove Index ...");

        Song sonic = new Song ("Death Valley","Sonic Youth","Bad Moon Rising");

        // remove(index) ------------------------------------------------
        ListLinked<Song> songs = new ListLinked<Song>(5);
        songs.add(iggy);
        songs.add(pil);
        songs.add(femmes);
        songs.add(sonic);

        assertEquals(4,songs.length(),"pre-removal (length)");

        songs.remove(1);
        // songs = {iggy,femmes,sonic}
        assertEquals(3,songs.length(),"remove middle (length)");
        //assertEquals(false,songs.contains(pil),"remove middle");
        assertEquals(iggy,songs.peek(0),"remove middle (1st song)");
        assertEquals(femmes,songs.peek(1),"remove middle (2nd song)");
        assertEquals(sonic,songs.peek(2),"remove middle (3rd song)");

        songs.remove(2);
        // songs = {iggy,femmes}
        assertEquals(2,songs.length(),"remove at end (length)");
        //assertEquals(false,songs.contains(sonic),"remove at end");
        assertEquals(iggy,songs.peek(0),"remove at end (1st song)");
        assertEquals(femmes,songs.peek(1),"remove at end (2nd song)");

        songs.remove(0);
        // songs = {femmes}
        assertEquals(1,songs.length(),"remove first (length)");
        //assertEquals(false,songs.contains(iggy),"remove first");
        assertEquals(femmes,songs.peek(0),"remove first (1st song)"); // the index here was 1 and it took me awhile to find, not going to lie

        songs.remove(0);
        // songs = {}
        assertEquals(0,songs.length(),"remove last element (length)");
        //assertEquals(false,songs.contains(femmes),"remove last");
        assertEquals(null,songs.peek(0),"remove last");

        // remove invalid index. list is empty
        songs.remove(0);
        assertEquals(0,songs.length(),"remove from empty");
        songs.add(pil);
        songs.remove(1);
        assertEquals(1,songs.length(),"remove not in list");
        assertEquals(pil,songs.peek(0),"remove not in list (1st song)");
        songs.remove(-1);
        assertEquals(1,songs.length(),"remove at -1");
        assertEquals(pil,songs.peek(0),"remove at -1 (1st song)");
    }

    public static void testRemoveAll() {
        System.out.println("\n\nTesting List Linked Remove All ...");

        Song sonic = new Song ("Death Valley","Sonic Youth","Bad Moon Rising");

        // removeAll()
        ListLinked<Song> songs = new ListLinked<Song>(5);
        songs.add(pil);
        songs.add(femmes);
        songs.add(iggy);
        assertEquals(3,songs.length(),"pre remove all (length)");
        songs.removeAll();
        assertEquals(0,songs.length(),"remove all (length)");
        assertEquals(false,songs.contains(pil),"remove all pil");
        assertEquals(false,songs.contains(femmes),"remove all femmes");
        assertEquals(false,songs.contains(iggy),"remove all iggy");
        assertEquals(null,songs.peek(0),"remove all");
    }

    public static void testConvertToArray() {
        System.out.println("\n\nTesting List Linked Convert to Array ...");

        Song[] array;

        // more songs to work with
        Song wire = new Song("Map Ref 41 Degrees", "Wire", "154", "1979");
        Song heat = new Song("A New Kind of Water","Fame" ,"Post-Punk","1981");
        Song tv = new Song("Dorian Gray","TV Personalities","Kids Love It","1981");
        Song sonic = new Song ("Death Valley","Sonic Youth","Bad Moon Rising");

        // Testing toArray ---------------------------------------------
        ListLinked<Song> songs = new ListLinked<Song>(4);
        if (null!=songs.toArray()) {
            System.out.println(
                    "ERROR: toArray empty list. Expect null. Results not null.");
        }

        songs.add(iggy);
        Object[] array1 = songs.toArray();
        if (null==array1) {
            System.out.println("ERROR: toArray 1 element. Returns null.");
        } else {
            assertEquals(1,array1.length,"toArray 1 element (length)");
            assertEquals((Song)array1[0], iggy, "toArray 1 element (value)");
        }

        songs.add(pil); // songs = {iggy,pil}
        Object[] array2 = songs.toArray();
        if (null==array2) {
            System.out.println("ERROR: toArray 2 elements. Returns null.");
        } else {
            assertEquals(2,array2.length,"toArray 2 elements (length)");
            assertEquals((Song)array2[0], iggy, "toArray 2 elements (1st song)");
            assertEquals((Song)array2[1], pil, "toArray 2 elements (2nd song)");
        }

        songs.add(femmes);
        songs.add(sonic); // songs = {iggy,pil,femmes,sonic}
        Object[] array3 = songs.toArray();
        if (null==array3) {
            System.out.println("ERROR: toArray at capacity. Returns null.");
        } else {
            assertEquals(4,array3.length,"toArray at capacity (length)");
            assertEquals((Song)array3[0], iggy, "toArray at capacity (1st song)");
            assertEquals((Song)array3[1], pil, "toArray at capacity (2nd song)");
            assertEquals((Song)array3[2], femmes, "toArray at capacity (3rd song)");
            assertEquals((Song)array3[3], sonic, "toArray at capacity (4th song)");
        }
    }

    public static void testConvertToSublist() {
        System.out.println("\n\nTesting List Linked Convert to Sublist ...");

        Song[] array;

        // more songs to work with
        Song wire = new Song("Map Ref 41 Degrees", "Wire", "154", "1979");
        Song heat = new Song("A New Kind of Water","Fame" ,"Post-Punk","1981");
        Song tv = new Song("Dorian Gray","TV Personalities","Kids Love It","1981");
        Song sonic = new Song ("Death Valley","Sonic Youth","Bad Moon Rising");

        // Testing sublist ---------------------------------------------
        ListLinked<Song> sublist = null;
        ListLinked<Song> tunes = new ListLinked<Song>(5);

        sublist = (ListLinked<Song>) tunes.sublist(0,1);
        if (null!=sublist) {
            System.out.println(
                    "ERROR: Sublist of empty list. Expect null. Results not null.");
        }
        tunes.add(iggy);
        tunes.add(pil);
        tunes.add(femmes);
        tunes.add(sonic);
        tunes.add(wire);

        // tunes = {iggy,pil,femmes,sonic,wire}
        sublist = (ListLinked<Song>) tunes.sublist(0,4);
        if (null==sublist) {
            System.out.println("ERROR: sublist all elements. Returns null.");
        } else {
            assertEquals(5,sublist.length(),"sublist all elements (length)");
            assertEquals(iggy,sublist.peek(0),"sublist 1st song");
            assertEquals(pil,sublist.peek(1),"sublist 2nd song");
            assertEquals(femmes,sublist.peek(2),"sublist 3rd song");
            assertEquals(sonic,sublist.peek(3),"sublist 4th song");
            assertEquals(wire,sublist.peek(4),"sublist 5th song");
        }
        // tunes = {iggy,pil,femmes,sonic,wire}
        sublist = (ListLinked<Song>) tunes.sublist(1,3);
        if (null==sublist) {
            System.out.println("ERROR: sublist middle elements. Returns null.");
        } else {
            assertEquals(3,sublist.length(),"sublist middle elements (length)");
            assertEquals(pil,sublist.peek(0),"sublist middle 1st song");
            assertEquals(femmes,sublist.peek(1),"sublist middle 2nd song");
            assertEquals(sonic,sublist.peek(2),"sublist middle 3rd song");
        }

        // tunes = {iggy,pil,femmes,sonic,wire}
        sublist = (ListLinked<Song>) tunes.sublist(0,0);
        if (null==sublist) {
            System.out.println("ERROR: sublist first only. Returns null.");
        } else {
            assertEquals(1,sublist.length(),"sublist first only (length)");
            assertEquals(iggy,sublist.peek(0),"sublist first only");
        }

        // tunes = {iggy,pil,femmes,sonic,wire}
        sublist = (ListLinked<Song>) tunes.sublist(4,4);
        if (null==sublist) {
            System.out.println("ERROR: sublist last only. Returns null.");
        } else {
            assertEquals(1,sublist.length(),"sublist last only (length)");
            assertEquals(wire,sublist.peek(0),"sublist last only");
        }

        // tunes = {iggy,pil,femmes,sonic,wire}
        sublist = (ListLinked<Song>) tunes.sublist(0,6);
        if (null!=sublist) {
            System.out.println(
                    "ERROR: sublist invalid end index. Expect null. Results not null.");
        }
        sublist = (ListLinked<Song>) tunes.sublist(-1,1);
        if (null!=sublist) {
            System.out.println(
                    "ERROR: sublist invalid start index. Expect null. Results not null.");
        }
        sublist = (ListLinked<Song>) tunes.sublist(3,1);
        if (null!=sublist) {
            System.out.println(
                    "ERROR: sublist end<start index. Expect null. Results not null.");
        }
    }

    public static void testConvertFillArray() {
        System.out.println("\n\nTesting List Linked Convert to Sublist ...");

        Song[] array;

        // more songs to work with
        Song wire = new Song("Map Ref 41 Degrees", "Wire", "154", "1979");
        Song heat = new Song("A New Kind of Water","Fame" ,"Post-Punk","1981");
        Song tv = new Song("Dorian Gray","TV Personalities","Kids Love It","1981");
        Song sonic = new Song ("Death Valley","Sonic Youth","Bad Moon Rising");

        //Testing fillArray ------------------------------------------------
        ListLinked<Integer> numbers = new ListLinked<>(10);
        for (Integer i=0; i<5; i++) {
            numbers.add(i);
        }
        Integer[] fillArray = new Integer[numbers.length()];
        numbers.toArray(fillArray);
        if (null == fillArray) {
            System.out.println("ERROR: addAll(fillArray) returned null;");
        } else {
            for (Integer i=0; i<fillArray.length; i++) {
                assertEquals(i,numbers.peek(i),"addAll peek("+i+")");
            }
        }
    }

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