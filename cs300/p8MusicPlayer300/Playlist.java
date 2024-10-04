//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    PO8 MusicPlayer300 - Playlist class
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
 * This class is FIFO linked queue of SongNodes, conforming to our QueueADT interface.
 * @author Sidney Heberlein
 *
 */
public class Playlist implements QueueADT<Song>
{
  // instance fields
  private SongNode first; // the current first song in the queue; the next one up to play (front of
  // the queue)
  private SongNode last; // the current last song in the queue; the most-recently added one (back of
  // the queue)
  private int numSongs; // the number of songs currently in the queue
  
  // constructor
  
  /**
   * This constructs a new, empty playlist queue
   */
  public Playlist()
  {
    first = null;
    last = null;
    numSongs = 0;
  }
  
  // methods
  
  /**
   * This method adds a new song to the end of the queue
   * @param element   the song to add to the Playlist
   */
  public void enqueue(Song element)
  {
    if (isEmpty())
    {
      first = new SongNode(element);
      last = first; // set the first and last elements to the same song if the list was empty
      numSongs++;
    }
    else
    {
      SongNode q = new SongNode(element); // a new SongNode with the given song
      SongNode temp = last; // a temporary SongNode storing the previous last node
      last = q; // set the tail to the new song if the list is not empty
      temp.setNext(q);
      numSongs++;
    }
  }
  
  /**
   * This method removes the song from the beginning of the queue
   * @return   the song that was removed from the queue, or null if the queue is empty
   */
  public Song dequeue()
  {
    if (numSongs == 0)
    {
      return null;
    }
    
    // scenario 1: removing from a list with only one element
    else if (numSongs == 1)
    {
      Song temp = first.getSong();
      first = null;
      last = first;
      numSongs--;
      return temp;
    }
    
    // scenario 2: removing from a list with more than one element
    else
    {
      Song temp = first.getSong();
      SongNode newFirst = first.getNext(); // stores the second element in the list (the new first 
      // node)
      first = newFirst;
      numSongs--;
      return temp;
    }
  }
  
  /**
   * This method returns the song at the front of the queue without removing it
   * @return   the song that is at the front of the queue, or null if the queue is empty
   */
  public Song peek()
  {
    if (isEmpty())
    {
      return null;
    }
    return first.getSong();
  }
  
  /**
   * This method returns true if and only if there are no songs in this queue
   * @return   true if this queue is empty, false otherwise
   */
  public boolean isEmpty()
  {
    if (numSongs == 0)
    {
      return true;
    }
    return false;
  }
  
  /**
   * This method returns the number of songs in this queue
   * @return   the number of songs in this queue
   */
  public int size()
  {
    return numSongs;
  }
  
  /**
   * This method creates and returns a formatted string representation of this playlist, with the 
   * string version of each song in the list on a separate line. For example:
   * "He's A Pirate" (1:21) by Klaus Badelt
   * "Africa" (4:16) by Toto
   * "Waterloo" (2:45) by ABBA
   * "All I Want For Christmas Is You" (4:10) by Mariah Carey
   * "Sandstorm" (5:41) by Darude
   * "Never Gonna Give You Up" (3:40) by Rick Astley
   * @return   the string representation of this playlist
   */
  @Override
  public String toString()
  {
    String returnThis = "";
    SongNode temp = first; // a temporary node starting at the first element that will iterate 
    // through all of the songs in this playlist
    while (temp != null)
    {
      returnThis += temp.getSong().toString() + "\n";
      temp = temp.getNext();
    }
    return returnThis;
  }
}