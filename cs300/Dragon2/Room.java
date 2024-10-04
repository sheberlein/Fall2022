//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Dragon Treasure Game 2.0 - Room class
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class models a Room that has a description, an ID, and an image. It also defines an
 * ArrayList of adjacent rooms.
 * @author Sidney Heberlein
 *
 */
public class Room extends DragonTreasureGame
{
  private String description; // verbal description of the room
  private ArrayList<Room> adjRooms; // list of all rooms directly connect
  private final int ID; // a "unique" identifier for each room
  protected static PApplet processing; // PApplet object which the rooms will use to
  // draw stuff to the GUI
  private PImage image; // stores the image that corresponds to the background of a room
  
  /**
   * This is the constructor for a Room object. It initializes all instance data fields.
   * @param ID   the ID that this Room should have
   * @param description   the verbal description this Room should have
   * @param image   the image that should be used as a background when drawing this Room.
   */
  public Room(int ID, String description, processing.core.PImage image)
  {
    this.ID = ID;
    this.description = description;
    this.image = image;
    adjRooms = new ArrayList<Room>();
  }
  
  /**
   * This is the getter method for the ID.
   * @return   the ID of this Room
   */
  public int getID()
  {
    return ID;
  }
  
  /**
   * This is the getter method for the description.
   * @return   the verbal description of this Room
   */
  public String getDescription()
  {
    return description;
  }
  
  /**
   * This is the getter for the list of adjacentRooms.
   * @return   a list of adjacent rooms
   */
  public ArrayList<Room> getAdjacentRooms()
  {
    return adjRooms;
  }
  
  /**
   * This method sets the processing for the class.
   * @param processing   the PApplet that this room will use to draw to the window
   */
  public static void setProcessing(processing.core.PApplet processing)
  {
    Room.processing = processing;
  }
  
  /**
   * This method adds the given room to the list of rooms adjacent to this room.
   * @param toAdd   the room to be added
   */
  public void addToAdjacentRooms(Room toAdd)
  {
    adjRooms.add(toAdd);
  }
  
  /**
   * This method checks whether or not the given room is adjacent to this room.
   * @param r   the room to check for adjacency
   * @return   true if it is adjacent, false otherwise
   */
  public boolean isAdjacent(Room r)
  {
    if (adjRooms.contains(r))
    {
      return true;
    }
    return false;
  }
  
  /**
   * This method overrides Object.equals(). It determines if two objects are equal.
   * @param other   the object to check against this Room
   * @return   true if other is of type Room and has the same ID, false otherwise
   */
  @Override
  public boolean equals(Object other)
  {
    if (other instanceof Room) // the object given must be of type Room in order to use Room's
      // methods on it
    {
      if (((Room)other).getID() == ID)
      {
        return true; // returns true if other Room's ID is the same as this Room's ID
      }
    }
    return false;
  }
  
  /**
   * This method overrides Object.toString(). It returns a string representation of a Room object.
   * @return   a string in the form of "<ID>: <description>\n Adjacent Rooms: <r1's ID> <r2's ID>" 
   *           list of adjacent room IDs continues for all rooms adjacent to this Room.
   */
  @Override
  public String toString()
  {
    String temp = ""; // stores a temporary string containing the Adjacent Rooms' IDs
    for (int i = 0; i < adjRooms.size(); i++)
    {
      temp += " " + adjRooms.get(i).getID(); // for each element in adjRooms, add the Room's
      // ID to the temporary string that holds all the adjacent Rooms' IDs
    }
    return "" + ID + ": " + description + "\n Adjacent Rooms:" + temp;
  }
  
  /**
   * This method draws this Room to the window by drawing the background image, a rectangle, and 
   * some text.
   */
  @Override
  public void draw()
  {
    processing.image(image, 0, 0); // draws the image at (0, 0)
    processing.fill(-7028); // changes the draw color to Flavescent, a light yellow-brown color.
    processing.rect(0, 500, 800, 600); // draws a rectangle with the top-left corner at (0, 500) and
    // the lower-right corner at (800, 600)
    processing.fill(0); // changes the draw color to black
    processing.text(this.toString(), 300, 525); // draws the Room’s toString() at (300, 525)
  }
}