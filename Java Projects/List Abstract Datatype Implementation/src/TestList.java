import java.lang.reflect.*;
import java.util.Scanner;
import java.io.*;

public class TestList {

  static Song iggy = new Song("Dum Dum Boys","Iggy Pop","The Idiot","1977");
  static Song pil = new Song("Swan Lake","Public Image Ltd.","Second Edition","1980");
  static Song femmes = new Song("Add It Up","Violent Femmes","Violent Femmes","1983");

  public static void main(String[] args) {

    // Initial add() and peek() test
    /* It is a bit of a chicken-and-egg problem.
    * To test add(), peek() needs to be correct to confirm song was added.
    * To test peek(), add() needs to be correct to confirm location of song.
    */
    List songs = new List(5);
    songs.add(iggy);
    assertEquals(1,songs.length(),"First Add (length)");
    assertEquals(iggy,songs.peek(0),"Fird Add (iggy)");
    /* Once the above are confirmed to be working, the rest of the testing can proceed.
    */

//    testConstructorGetters();
//    testSearches();
//    testAdds();
//    testRemoves();
//    testConvert();
  }


  public static void testConstructorGetters() {
    //testing with 0 songs in list and default constructor


    //testing with 2 songs in list and default constructor


    //testing with 0 songs in list but specifying size


    // testing with 2 songs in list but specifying size
    // length
    // capacity
    // isFull
    // isEmpty
  }

  public static void testSearches() {
    System.out.println("Testing List search methods ...");

    Song defaultSong = new Song();
    Song fourthDimension = new Song("Hello", "Fourth Dimension", "Breaking doors", "2017");
    Song bonJovi = new Song("Living on a Prayer", "Bon Jovi", "I lowkey forgot the album", "1960?");

    List listSongsTestSearches = new List();
    listSongsTestSearches.add(defaultSong);
    listSongsTestSearches.add(fourthDimension);
    listSongsTestSearches.add(bonJovi);


    assertEquals(bonJovi, listSongsTestSearches.peek(2), "Testing peek  ");
    // check that the assertEquals compares values, ignoring case, not references
    // don't forget to try erroneous input (e.g. bad index)
    // peek(index)
    // find(song)
    // contains(song)
  }

  public static void testAdds() {
    System.out.println("Testing List add methods ...");
    // remember to test numberOfItems as well as the song in the list
    // don't forget to try erroneous input (e.g. bad index, over capacity)
    // add(Song)
    // add(Song, index)
    // set(Song, index)
  }

  public static void testRemoves() {
    System.out.println("Testing List remove methods ...");
    // remember to test numberOfItems as well as the removal of song in the list
    // don't forget to try erroneous input (e.g. bad index)
    // remove(index)
    // remove(Song)
    // removeAll()
  }

  public static void testConvert() {
    System.out.println("Testing List convert methods ...");

    List test = new List();
    test.add(pil);
    test.add(femmes);

    Song[] test2 = test.toArray();
    assertEquals(test2[0], pil, "Error in adding song to array");
    assertEquals(test2[1], femmes, "Error in adding song to array");

    test.add(pil);
    test.add(iggy);
    test.add(iggy);
    test.add(femmes);
    test.add(pil);
    List sublistTest1 = test.sublist(1, 3);
    List sublistTest2 = test.sublist(4, 6);
    assertEquals(sublistTest1.peek(0), femmes, "Error at first song in sublist");
    assertEquals(sublistTest1.peek(1), pil, "Error at second song in sublist");
    assertEquals(sublistTest1.peek(2), iggy, "Error at third song in sublist");
    assertEquals(sublistTest2.peek(0), iggy, "Error at first song in sublist");
    assertEquals(sublistTest2.peek(1), femmes, "Error at second song in sublist");
    assertEquals(sublistTest2.peek(2), pil, "Error at third song in sublist");



    // remember to test numberOfItems as well as the removal of song in the list
    // don't forget to try erroneous input (e.g. bad index)
    // Song[] toArray()
    // List sublist(start,end)
  }

  //for testing findng songs
  static void assertEquals(Song expect, Song result, String msg) {
    if (!expect.equals(result)) {
      System.out.println(msg+". Expected "+expect+". Result "+result);
    }
  }

  //for testing finding indexes
  static void assertEquals(int expect, int result, String msg) {
    if (expect != result) {
      System.out.println(msg+". Expected "+expect+". Result "+result);
    }
  }

  // for testing number of items or length
  static void assertEquals(int expect, List result, String msg) {
    if(expect != result.length()) {
      System.err.println(msg + ". Expected " + expect + ". Result " + result);
    }
  }

  //for testing


} // end class Main
