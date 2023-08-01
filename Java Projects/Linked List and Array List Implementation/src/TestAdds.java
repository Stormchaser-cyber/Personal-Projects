public class TestAdds {

    static Song iggy = new Song("Dum Dum Boys","Iggy Pop","The Idiot","1977");
    static Song pil = new Song("Swan Lake","Public Image Ltd.","Second Edition","1980");
    static Song femmes = new Song("Add It Up","Violent Femmes","Violent Femmes","1983");
    static Song wire = new Song("Map Ref 41 Degrees", "Wire", "154", "1979");
    static Song heat = new Song("A New Kind of Water","Fame" ,"Post-Punk","1981");
    static Song tv = new Song("Dorian Gray","TV Personalities","Kids Love It","1981");
    static Song sonic = new Song ("Death Valley","Sonic Youth","Bad Moon Rising");

    // -------------------------------   addSong      --------------------
    // -------------------------------------------------------------------
    public static void addSong(boolean testCapacity) {
        System.out.println("--- testing add(Song) ...");
        List<Song> songs = null;
        songs = TestList.constructList(songs,4);
        TestList.assertEquals(0,songs.length(),"pre-add (length)");
        songs.add(iggy);
        TestList.assertEquals(1,songs.length(),"add first (length)");
        TestList.assertEquals(iggy,songs.peek(0),"add first (value)");
        songs.add(pil);
        TestList.assertEquals(2,songs.length(),"add 2nd (length)");
        TestList.assertEquals(pil,songs.peek(1),"add 2nd (value)");
        songs.add(femmes);
        TestList.assertEquals(3,songs.length(),"add 3rd (length)");
        TestList.assertEquals(femmes,songs.peek(2),"add 3rd (value)");

        // test up to and over capacity (if warranted)
        if (testCapacity) {
            songs.add(wire);
            TestList.assertEquals(4,songs.length(),"add to capacity (length)");
            TestList.assertEquals(wire,songs.peek(3),"add to capacity (value)");
            songs.add(heat);
            TestList.assertEquals(4,songs.length(),"add attempt over capacity (length)");
            TestList.assertEquals(wire,songs.peek(3),"add attempt over capacity (value)");
        }
    } // end addSong


    // -------------------------------   addAtIndex   --------------------
    // -------------------------------------------------------------------
    public static void addAtIndex(boolean testCapacity) {
        System.out.println("--- testing add(Song,index) ...");
        List<Song> tunes = null;
        tunes = TestList.constructList(tunes,6);
        // tunes = {iggy}
        tunes.add(iggy,0);
        TestList.assertEquals(1,tunes.length(),"add to empty (length)");
        TestList.assertEquals(iggy,tunes.peek(0),"add to empty (value)");
        // tunes = {pil, iggy}
        tunes.add(pil,0);
        TestList.assertEquals(2,tunes.length(),"add at 0 (length)");
        TestList.assertEquals(pil,tunes.peek(0),"add at 0 (value)");
        TestList.assertEquals(iggy,tunes.peek(1),"add at 0 (2nd song)");
        // tunes = {pil,iggy,femmes}
        tunes.add(femmes,2);
        TestList.assertEquals(3,tunes.length(),"add at end (length)");
        TestList.assertEquals(femmes,tunes.peek(2),"add at end (value)");
        TestList.assertEquals(pil,tunes.peek(0),"add at end (1st song)");
        TestList.assertEquals(iggy,tunes.peek(1),"add at 0 (2nd song)");
        // tunes = {pil,sonic,iggy,femmes}
        tunes.add(sonic,1);
        TestList.assertEquals(4,tunes.length(),"add to middle (length)");
        TestList.assertEquals(sonic,tunes.peek(1),"add to middle (value)");
        TestList.assertEquals(pil,tunes.peek(0),"add to middle (1st song)");
        TestList.assertEquals(iggy,tunes.peek(2),"add to middle (3rd song)");
        TestList.assertEquals(femmes,tunes.peek(3),"add to middle (4th song)");
        // test invalid indices
        tunes.add(wire,-1);
        TestList.assertEquals(4,tunes.length(),"add at -1 (length)");
        tunes.add(wire,5);
        TestList.assertEquals(4,tunes.length(),"add beyond last (length)");
        TestList.assertEquals(null,tunes.peek(5),"add beyond last (value)");

        if (testCapacity) {
            tunes.add(wire,7);
            TestList.assertEquals(4,tunes.length(),"add beyond capacity (length)");
        }
    } // end addAtIndex

    // -------------------------------   addAll    --------------------
    // -------------------------------------------------------------------
    public static void addAll(boolean testCapacity) {
        System.out.println("--- testing addAll() ...");
        Integer[] one = {1};
        Integer[] three = {1,2,3};

        List<Integer> numbers = null;
        numbers = TestList.constructList(numbers,5);
        numbers.addAll(one);
        TestList.assertEquals(1,numbers.length(),"add one to empty");
        numbers = TestList.constructList(numbers,5);
        numbers.addAll(three);
        TestList.assertEquals(3,numbers.length(),"add three to empty");
        numbers = TestList.constructList(numbers,5);
        numbers.add(0);
        numbers.addAll(one);
        TestList.assertEquals(2,numbers.length(),"add one to list of 1");
        numbers = TestList.constructList(numbers,5);
        numbers.add(0);
        numbers.addAll(three);
        TestList.assertEquals(4,numbers.length(),"add three to list of 1");

        if (testCapacity) {
            numbers = TestList.constructList(numbers,2);
            numbers.addAll(three);
            TestList.assertEquals(0,numbers.length(),"add over capacity");
        }
    } // end addAll

    // -------------------------------   set     --------------------
    // -------------------------------------------------------------------
    public static void set() {
        System.out.println("--- testing set(Song,index) ...");
        List<Song> jams = null;
        jams = TestList.constructList(jams,6);
        // †his assumes that add is working correctly!
        jams.add(iggy);
        jams.add(pil);
        jams.add(femmes);
        // jams = {wire,pil,femmes}. Set at first element
        jams.set(wire,0);
        TestList.assertEquals(3,jams.length(),"set(0) length");
        TestList.assertEquals(wire,jams.peek(0),"set(0) value");
        TestList.assertEquals(pil,jams.peek(1),"set(0) (2nd song)");
        TestList.assertEquals(femmes,jams.peek(2),"set(0) (3rd song)");
        // jams = {wire,heat,femmes}. Set at middle element
        jams.set(heat,1);
        TestList.assertEquals(3,jams.length(),"set(1) length");
        TestList.assertEquals(heat,jams.peek(1),"set(1) value");
        TestList.assertEquals(wire,jams.peek(0),"set(1) (1st song)");
        TestList.assertEquals(femmes,jams.peek(2),"set(1) (3rd song)");
        // jams = {wire,heat,tv}. Set at last element
        jams.set(tv,2);
        TestList.assertEquals(3,jams.length(),"set(2) length");
        TestList.assertEquals(tv,jams.peek(2),"set(2) value");
        TestList.assertEquals(wire,jams.peek(0),"set(2) (1st song)");
        TestList.assertEquals(heat,jams.peek(1),"set(2) (2nd song)");
        //  invalid indices. should not change {wire,heat,tv}
        jams.set(iggy,3);	// set 1 past the last element
        TestList.assertEquals(3,jams.length(),"set at length, bad index (length)");
        TestList.assertEquals(null,jams.peek(3),"set at length, bad index (value)");
        jams.set(pil,5);
        TestList.assertEquals(3,jams.length(),"set at capacity, bad index (length)");
        TestList.assertEquals(null,jams.peek(5),"set at capacity, bad index (value)");
        jams.set(pil,-1);
        TestList.assertEquals(3,jams.length(),"set at -1 (length)");
        TestList.assertEquals(wire,jams.peek(0),"set at -1 (value)");
    } // end set()
}
