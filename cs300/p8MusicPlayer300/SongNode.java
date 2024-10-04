//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    PO8 MusicPlayer300 - SongNode class
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
 * This class is a singly-linked node for our linked queue, which contains a Song object
 * @author Sidney Heberlein
 *
 */
public class SongNode 
{
  // instance fields
  private Song song; // the Song object in this node
  private SongNode next; // the next SongNode in this queue
  
  // constructors
  
  /**
   * This constructs a single SongNode containing the given data, not linked to any other SongNodes
   * @param data   the Song for this node
   * @throws IllegalArgumentException   if data is null
   */
  public SongNode(Song data) throws IllegalArgumentException
  {
    if (data == null)
    {
      throw new IllegalArgumentException("data cannot be null.");
    }
    song = data;
  }
  
  /**
   * This constructs a single SongNode containing the given data, linked to the specified SongNode
   * @param data   the Song for this node
   * @param next   the next node in the queue
   * @throws IllegalArgumentException   if data is null
   */
  public SongNode(Song data, SongNode next) throws IllegalArgumentException
  {
    if (data == null)
    {
      throw new IllegalArgumentException("data cannot be null.");
    }
    song = data;
    this.next = next;
  }
  
  // methods
  
  /**
   * This method is the accessor method for this node's data
   * @return   the Song in this node
   */
  public Song getSong()
  {
    return song;
  }
  
  /**
   * This method is the accessor method for the next node in the queue
   * @return   the SongNode following this one, if any
   */
  public SongNode getNext()
  {
    return next;
  }
  
  /**
   * This method changes the value of this SongNode's next data field to the given value
   * @param next   the SongNode to follow this one; may be null
   */
  public void setNext(SongNode next)
  {
    this.next = next;
  }
}