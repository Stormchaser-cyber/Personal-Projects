import java.lang.reflect.*;
import java.util.Scanner;
import java.io.*;

public class SongList extends ListArray<Song> {

  /** Extending List to add functionality that is specific to a Song. Everything in List can be applied to any type.
  */

  /** Short description of data stored */
  private String description;

  /**
  Constructor
  @param size The capacity of the list (i.e. max number of elements)
  @param desc A short description of list contents
  */
  public SongList(String desc, int size) {
    super(size);
    description = desc;
  }

  /**
  Constructor
  @param desc A short description of list contents
  */
  public SongList(String desc) {
    super();
    description = desc;
  }

  public void description(String desc) { description = desc; }
  public String description() { return description; }

  /** Print List description then all songs.
  */
  @Override
  public String toString() {
    String printedList = "";
    printedList += "\n"+String.format("%55s",description)+"\n";
    for (int i=0; i<100; i++) {
      printedList += "-";
    }
    printedList += "\n";
    printedList += super.toString();
    return printedList;
  } // end toString()

  // >>>>>>>>>>>     LOAD FROM and SAVE TO file operations   <<<<<<<<<<

  public void loadFromFile(String filename) throws Exception {
    // FROM seas.upenn.edu/~cis1xx/resources/java/fileIO/introToFileIO.html

    // Open the file and put contents in bufferReader
    FileReader inputStream = null;
    BufferedReader bufferReader;
    try {
      inputStream = new FileReader(filename);
      bufferReader = new BufferedReader(inputStream);
    } catch (Exception e) {
      System.out.println("Cannot open "+filename);
      return;
    }

    // Check that the column headings correspond to fields/attributes of a Song
    String[] columns = null;
    try {
      // Read first row to get column headings
      columns = bufferReader.readLine().split(",");
    } catch (Exception e) {
      System.out.println(e+" Fail to read first row.");
      throw e;
    }
    // Test that each of the column headings is in {track,album,artist,year}
    // If we are able to use song.set with each column heading (no errors), columns are legit
    try {
      Song fieldTester = new Song();
      for (String col : columns) {
        fieldTester.set(col,"test");
      }
    } catch (IllegalArgumentException e) {
      System.out.println(e+" Bad Column Heading in File");
      return;
    }

    // Read in the rest of the file. Each row is a new song to add to List
    String[] entry;
    String line;
    Song nextSong;
    try {
      // While there is more to read ...
      while ((line = bufferReader.readLine()) != null) {
        // Instantiate an "empty" Song
        nextSong = new Song();
        // read one line from the file and save each word separatly in the "entry" array
        entry = line.split(",");
        // Populate the song with information from row in file
        for (int i=0; i<entry.length; i++) {
          // for each column value, set the corresponding field of the new song
          nextSong.set(columns[i],entry[i]);
        }
        // Append to end of song list
        add(nextSong);
      }
    } catch (Exception e) {
      throw e;
    }
  } // end loadFromFile(String)


  /**
  Save contents to a file.
  @param filename of file in which to store data.
  */
  public void saveToFile(String filename){

  } // end saveToFile(String)



} // end class SongList
