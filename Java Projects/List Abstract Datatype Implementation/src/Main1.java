public class Main1 {

    public static void main(String[] args) {
        // testing isFull ---
        List songs = new List();
        Song firstSong = new Song("Title", "Artist", "Album", "Year");
        songs.add(firstSong);
        System.out.println(songs.toString());

        //testing isEmpty --- works
        List noSongs = new List(5);
        System.out.println(noSongs.isEmpty());
    }
}
