//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    PO8 MusicPlayer300 - MusicPlayerTester class
// Course:   CS 300 Fall 2022
//
// Author:   Sidney Heberlein
// Email:    sheberlein@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// N/A
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.util.Scanner;

/**
 * This class is the tester for the Song, SongNode, Playlist, and MusicPlayer300 classes
 * @author Sidney Heberlein
 *
 */
public class MusicPlayerTester 
{
  /**
   * This method runs all the test methods defined in this class
   * @return   true if all tests in this class pass and false otherwise
   */
  public static boolean runAllTests()
  {
    if (!testSongConstructor())
    {
      return false;
    }
    if (!testSongPlayback())
    {
      return false;
    }
    if (!testSongNode())
    {
      return false;
    }
    if (!testEnqueue())
    {
      return false;
    }
    if (!testDequeue())
    {
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /**
   * This method tests the constructor in the Song class, as well as the getTitle, getArtist, and
   * toString methods
   * @return   true if this test verifies a correct functionality and false otherwise
   */
  public static boolean testSongConstructor()
  {
    try
    {
      // testing a file that is not one of the provided .txt files
      boolean tester = false; // this should be set to true in the catch block
      try
      {
        Song song = new Song("Africa", "Toto", "audio" + File.separator + "random-song");
        // random-song is not one of the provided .txt files, so this constructor call should throw
        // an exception
      }
      catch (IllegalArgumentException e)
      {
        tester = true;
      }
      if (tester == false)
      {
        System.out.println("Your song constructor did not throw an illegalArgumentException when"
            + " passed a file that was not one of the provided .txt files.");
        return false;
      }
      
      // testing a valid file with toString() and getTitle() and getArtist() accessor methods.
      Song song = new Song("Africa", "Toto", "audio" + File.separator + "toto-africa.mid"); // a new
      // song with a valid file
      if (!song.getTitle().equals("Africa"))
      {
        System.out.println("Your getTitle method in the Song class did not return the correct "
            + "title.");
        return false;
      }
      if (!song.getArtist().equals("Toto"))
      {
        System.out.println("Your getArtist method in the Song class did not return the correct "
            + "artist.");
        return false;
      }
      String s = song.toString();
      if (!(s.contains("\"Africa\"") && s.contains("(") && s.contains(")") && s.contains(":") 
          && s.contains("by Toto")))
      {
        System.out.println("Your toString method in the Song class did not return the correct "
            + "String.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected exception was thrown in testSongConstructor.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /**
   * This method tests the isPlaying, play, and stop methods defined in the Song class
   * @return   true if this test verifies a correct functionality and false otherwise
   */
  public static boolean testSongPlayback()
  {
    try
    {
      Song song = new Song("All I Want For Christmas Is You", "Mariah Carey",
          "audio" + File.separator + "all-i-want-for-xmas.mid"); // a new song with a valid file
      song.play();
      Thread.sleep(1000);
      if (!song.isPlaying())
      {
        System.out.println("Your isPlaying method did not correctly return true when a song was "
            + "playing.");
        return false;
      }
      song.stop();
      Thread.sleep(1000);
      if (song.isPlaying())
      {
        System.out.println("Your isPlaying method did not correctly return false when a song wasn't"
            + " playing.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected exception was thrown in testSongPlayback.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /**
   * This method tests all of the methods and constructors in the SongNode class.
   * @return   true if this test verifies a correct functionality and false otherwise
   */
  public static boolean testSongNode()
  {
    try
    {
      // test the constructors with invalid input
      boolean tester = false; // this should be set to true in the catch block
      try
      {
        SongNode node = new SongNode(null);
      }
      catch (IllegalArgumentException e)
      {
        tester = true;
      }
      if (tester != true)
      {
        System.out.println("Your SongNode constructor did not throw the correct exception when"
            + " passed invalid input.");
        return false;
      }
      tester = false; // this should be set to true in the catch block
      try
      {
        Song song = new Song("Africa", "Toto", "audio" + File.separator + "toto-africa.mid"); // a
        // new song with a valid file
        SongNode node1 = new SongNode(song);
        SongNode node2 = new SongNode(null, node1);
      }
      catch (IllegalArgumentException e)
      {
        tester = true;
      }
      if (tester != true)
      {
        System.out.println("Your SongNode constructor did not throw the correct exception when"
            + " passed invalid input.");
        return false;
      }
      
      // test the constructors with valid input
      Song song = new Song("Africa", "Toto", "audio" + File.separator + "toto-africa.mid"); // a new
      // song with a valid file
      SongNode node1 = new SongNode(song);
      if (!node1.getSong().equals(song))
      {
        System.out.println("Your SongNode constructor did not correctly set the song given to the "
            + "song");
        return false;
      }
      if (node1.getNext() != null)
      {
        System.out.println("Your SongNode constructor did not correctly set the next song to null "
            + "given no next song");
        return false;
      }
      SongNode node3 = new SongNode(song, node1);
      if (!node3.getSong().equals(song))
      {
        System.out.println("Your SongNode constructor did not correctly set the song given to the "
            + "song");
        return false;
      }
      if (!node3.getNext().equals(node1))
      {
        System.out.println("Your SongNode constructor did not correctly set the next song");
        return false;
      }
      node3.setNext(node1);
      if (!node3.getNext().equals(node1))
      {
        System.out.println("Your setNext method did not correctly set the next node.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected exception was thrown in testSongNode.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /**
   * This method tests the constructor, accessor methods, and enqueue methods defined in the 
   * Playlist class
   * @return   true if this test verifies a correct functionality and false otherwise
   */
  public static boolean testEnqueue()
  {
    try
    {
      // test the constructor
      Playlist playlist = new Playlist();
      if (!playlist.isEmpty())
      {
        System.out.println("Your isEmpty method did not correctly return true when the playlist"
            + " was empty, or your playlist constructor did not correctly set the playlist size"
            + " to zero.");
        return false;
      }
      if (playlist.size() != 0)
      {
        System.out.println("Your playlist size method did not correctly return 0 when the playlist"
            + " was empty.");
        return false;
      }
      if (playlist.peek() != null)
      {
        System.out.println("Your playlist peek method did not correctly return null when the first"
            + " element was null, or your playlist constructor did not correctly set the first "
            + "element to null");
        return false;
      }
      
      // add a song to an empty playlist
      Playlist p = new Playlist();
      Song song = new Song("Africa", "Toto", "audio" + File.separator + "toto-africa.mid"); // a new
      // song with a valid file
      p.enqueue(song); // add the song to the front of the playlist
      if (!p.peek().equals(song))
      {
        System.out.println("Your enqueue method did not correctly set the last element in the "
            + "queue to the given song when the playlist was empty.");
        return false;
      }
      if (p.size() != 1)
      {
        System.out.println("Your enqueue method did not correctly update the size of the queue when"
            + " a new element was added.");
        return false;
      }
      
      // add a song to a non-empty playlist
      Song song1 = new Song("All I Want For Christmas Is You", "Mariah Carey",
          "audio" + File.separator + "all-i-want-for-xmas.mid"); // a new song with a valid file
      p.enqueue(song1); // add the song to the front of the playlist
      if (!p.peek().equals(song))
      {
        System.out.println("Your enqueue method set the first element in the queue to the new song,"
            + "not the last element.");
        return false;
      }
      if (p.size() != 2)
      {
        System.out.println("Your enqueue method did not correctly update the size of the queue when"
            + " a new element was added.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected exception was thrown in testEnqueue.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /**
   * This method tests the dequeue method defined in the Playlist class
   * @return   true if this test verifies a correct functionality and false otherwise
   */
  public static boolean testDequeue()
  {
    try
    {
      // test trying to remove from an empty playlist
      Playlist p1 = new Playlist();
      if (p1.dequeue() != null)
      {
        System.out.println("Your dequeue method did not correctly return null when the playlist was"
            + " empty.");
        return false;
      }
      
      // test removing the only element in a playlist
      Playlist p = new Playlist();
      Song song = new Song("Africa", "Toto", "audio" + File.separator + "toto-africa.mid"); // a new
      // song with a valid file
      p.enqueue(song); // add the song to the front of the playlist
      p.dequeue(); // remove the only song in the playlist
      if (!p.isEmpty())
      {
        System.out.println("Your dequeue method did not correctly update the size when the only "
            + "element in the playlist was removed.");
        return false;
      }
      if (p.peek() != null)
      {
        System.out.println("Your dequeue method did not correctly remove the element from the "
            + "playlist when there was only one element in the playlist.");
        return false;
      }
      
      // test removing an element from a playlist with more than one element
      p.enqueue(song); // add the song back to the front of the playlist
      Song song1 = new Song("All I Want For Christmas Is You", "Mariah Carey",
          "audio" + File.separator + "all-i-want-for-xmas.mid"); // a new song with a valid file
      p.enqueue(song1); // add the song to the end of the playlist
      System.out.println(p.toString()); // visually test the toString method
      p.dequeue();
      if (p.size() != 1)
      {
        System.out.println("Your dequeue method did not properly update the size when removing an "
            + "element from a playlist with more than one song.");
        return false;
      }
      if (!p.peek().equals(song1))
      {
        System.out.println("Your dequeue method did not properly remove the first element in the "
            + "playlist with more than one song.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected exception was thrown in testDequeue.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /**
   * Main method for the tester class
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args)
  {
    System.out.println("testSongConstructor: " + testSongConstructor());
    System.out.println("testSongPlayback: " + testSongPlayback());
    System.out.println("testSongNode: " + testSongNode());
    System.out.println("testEnqueue: " + testEnqueue());
    System.out.println("testDequeue: " + testDequeue());
    System.out.println("runAllTests: " + runAllTests());
  }
}
