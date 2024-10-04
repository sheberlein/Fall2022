//////////////// FILE HEADER //////////////////////////
//
// Title: Dragon Treasure Hunter - Dragon class
// Course: CS 300 Fall 2022
//
// Author: Sidney Heberlein
// Email: sheberlein@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// N/A
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: https://docs.oracle.com/javase/7/docs/api/ The Java API site helped to explain
// the Random class and how it works
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Random;

/** This class represents the dragon that is lurking in the caves, protecting the treasure. This 
 * class is responsible for keeping track of the dragon’s current location and picking a room to 
 * move to and then moving to it. 
 * @author Sidney Heberlein
 */
public class Dragon 
{
  private Room currentRoom; //current location of the dragon
  private Random randGen; //random num generator used for moving
  private static final String DRAGON_WARNING = "You hear a fire breathing nearby!\n";
  
  /** This is the constructor for a dragon object. It assigns values to all non-static fields.
   * @param currentRoom the current location of the dragon
   */
  public Dragon(Room currentRoom)
  {
    this.currentRoom = currentRoom;
    randGen = new Random();
  }
  
  /** This method is the getter for the Dragon's current room
   * @return the current Dragon's current room
   */
  public Room getCurrentRoom()
  {
    return currentRoom;
  }
  
  /** This method returns the string that is the dragon class's warning, indicating that there is 
   * one nearby.
   * @return the dragon warning message string
   */
  public static String getDragonWarning()
  {
    return DRAGON_WARNING;
  }
  
  /** In this method, the current Dragon picks one of the rooms at random and moves there if 
   * possible. If it is not a valid move, then it will pick again. Dragons abide by the following 
   * rules when moving: 1) The dragon can only move into rooms that are adjacent to it. 2) The 
   * dragon CANNOT move into portal rooms.
   */
  public void changeRooms()
  {
    int stop = 0; // if stop is 0, the dragon will keep trying to find a room to move to. If stop
    // is 1, the dragon has found a room to move to.
    int randomRoom = 0; // randomRoom picks a random integer index of a room that the dragon will 
    // try to move to
    while (stop == 0)
    {
      randomRoom = randGen.nextInt(currentRoom.getAdjacentRooms().size());
      if (!currentRoom.getAdjacentRooms().get(randomRoom).getType().equals(RoomType.PORTAL))
      {
        currentRoom = currentRoom.getAdjacentRooms().get(randomRoom);
        stop = 1;
      }
    }
  }
}
