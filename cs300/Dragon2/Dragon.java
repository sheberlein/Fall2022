//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Dragon Treasure Game 2.0 - Dragon class
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
 * This class models a Dragon that is an extension of Character. It also implements the Moveable
 * interface. Dragon has _____
 * @author Sidney Heberlein
 *
 */
public class Dragon extends Character implements Moveable
{
  private Random randGen; //random number generator used for moving
  private static final String DRAGON_WARNING = "You hear a fire breathing nearby!\n";
  private static final String DRAGON_ENCOUNTER = "Oh no! You ran into the fire breathing dragon!\n";
  
  /**
   * This is the constructor for a Dragon object. It initializes all instance fields. The label 
   * should be "DRAGON" by default.
   * @param currentRoom   the room that the Dragon starts in
   */
  public Dragon(Room currentRoom)
  {
    super(currentRoom, "DRAGON"); // call Character's constructor to set the current room and
    // description
    randGen = new Random();
  }
  
  /**
   * This method moves the Dragon to the destination room.
   * @param destination   the Room to change it to
   * @return   true if the change was successful, false otherwise
   */
  public boolean changeRoom(Room destination)
  {
    if (!canMoveTo(destination))
    {
      return false; // return false if the dragon cannot move to the destination room
    }
    else
    {
      super.setCurrentRoom(destination);
      return true; // if the dragon can move to the destination room, set the dragon's current room
      // to the destination room and return true
    }
  }
  
  /**
   * This method checks if the dragon can move to the given destination. A valid move is the 
   * destination not a PortalRoom.
   * @param destination   the room to check if the dragon can move towards
   * @return   true if they can, false otherwise
   */
  public boolean canMoveTo(Room destination)
  {
    if (super.getCurrentRoom().isAdjacent(destination) 
        && !(super.getCurrentRoom() instanceof PortalRoom))
    {
      return true; // return true if destination is adjacent to the dragon's current room and it
      // is not a portal room
    }
    return false;
  }
  
  /**
   * This method picks randomly ONCE an adjacent room to move into.
   * @return   the room that this Dragon should try to move into
   */
  public Room pickRoom()
  {
    int tempRand = randGen.nextInt(super.getAdjacentRooms().size()); // tempRand stores a random
    // index of an adjacent room that the dragon will move into
    return super.getAdjacentRooms().get(tempRand); // return the room at the randomly generated
    // index
  }
  
  /**
   * This method is the getter for DRAGON_WARNING.
   * @return   the string for warning about a dragon being nearby.
   */
  public static String getDragonWarning()
  {
    return DRAGON_WARNING;
  }
  
  /**
   * This method is the getter for DRAGON_ENCOUNTER.
   * @return   the string for letting the player know they ran into the dragon.
   */
  public static String getDragonEncounter()
  {
    return DRAGON_ENCOUNTER;
  }
}
