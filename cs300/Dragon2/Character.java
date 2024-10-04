//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Dragon Treasure Game 2.0 - Character class
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
 * This class is the parent class for Characters, such as Player and Dragon. It contains the room
 * that the character is in currently, and a description of the character.
 * @author Sidney Heberlein
 *
 */
public class Character 
{
  private Room currentRoom; //current room the character is in
  private String label; //a label giving a basic description of the character
  
  /**
   * This is the constructor for a Character object. It initializes all instance fields.
   * @param currentRoom   the room that the Character is located in
   * @param label   a descriptive label of this Character
   */
  public Character(Room currentRoom, String label)
  {
    this.currentRoom = currentRoom;
    this.label = label;
  }
  
  /**
   * This method is the getter for the current room of this Character.
   * @return   the room where the character is
   */
  public Room getCurrentRoom()
  {
    return currentRoom;
  }
  
  /**
   * This method is the getter for the label of this Character.
   * @return   this Character's descriptive label
   */
  public String getLabel()
  {
    return label;
  }
  
  /**
   * This method gets the list of rooms adjacent to this Character.
   * @return   an ArrayList of rooms adjacent to this character
   */
  public ArrayList<Room> getAdjacentRooms()
  {
    return currentRoom.getAdjacentRooms(); // call Room's getAdjacentRooms method
  }
  
  /**
   * This method sets the current room to the one given.
   * @param newRoom   the room that should become the current room
   */
  public void setCurrentRoom(Room newRoom)
  {
    this.currentRoom = newRoom;
  }
}
