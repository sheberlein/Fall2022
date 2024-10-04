//////////////// FILE HEADER //////////////////////////
//
// Title: Dragon Treasure Hunter - Player class
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
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/** This class defines a character that the player of the game controls. This class is responsible
 * for keeping track of the current player location, moving the player between rooms, and
 * determining if the player is near a room that has a special property.
 * @author Sidney Heberlein
 */
public class Player 
{
  private Room currentRoom; //current location of the player
  
  /** Constructor for a player object. Assigns values to all non-static fields.
   * @param currentRoom the current location of the Player
   */
  public Player(Room currentRoom)
  {
    this.currentRoom = currentRoom;
  }
  
  /** Getter for this player's current room.
   * @return the current location of the player
   */
  public Room getCurrentRoom()
  {
    return currentRoom;
  }
  
  /** Setter for this player's current room.
   * @param newRoom the location that the player is moving to
   */
  public void changeRoom(Room newRoom)
  {
    currentRoom = newRoom;
  }
  
  /** This method gets the list of rooms adjacent to where the player is currently at.
   * @return an arraylist of rooms adjacent to the current room
   */
  public ArrayList<Room> getAdjacentRoomsToPlayer()
  {
    return currentRoom.getAdjacentRooms();
  }
  
  /** Determines whether or not the player can move to the given destination room. A valid player 
   * move is ONLY to adjacent rooms.
   * @param destination the room the player wants to move to
   * @return true if it is a valid movement, false otherwise
   */
  public boolean canMoveTo(Room destination)
  {
    if (currentRoom.isAdjacent(destination))
    {
      return true; // return true if the player can move to destination
    }
    return false; // return false if the player cannot move to destination
  }
  
  /** This method determines whether or not the player needs to teleport. Players teleport when 
   * their current room is of type PORTAL
   * @return true if the player should teleport, false otherwise
   */
  public boolean shouldTeleport()
  {
    if (currentRoom.getType().equals(RoomType.PORTAL))
    {
      return true; // return true if the current player's room type is portal
    }
    return false; // return false if the current player's room type is not portal
  }
  
  /** This method determines whether or not the given dragon is in a nearby room.
   * @param d the dragon to check if nearby
   * @return true if the dragon is nearby, false otherwise
   */
  public boolean isDragonNearby(Dragon d)
  {
    for (int i = 0; i < currentRoom.getAdjacentRooms().size(); i++)
    {
      if (d.getCurrentRoom().isAdjacent(currentRoom) || currentRoom.isAdjacent(d.getCurrentRoom()))
      {
        return true; // return true if the dragon is adjacent to the player's current room
      }
    }
    return false; // return false if the dragon is not adjacent to the player's current room
  }
  
  /** This method determines whether or not a portal room is in a nearby room.
   * @return true if a portal room is nearby, false otherwise
   */
  public boolean isPortalNearby()
  {
    for (int i = 0; i < currentRoom.getAdjacentRooms().size(); i++)
    {
      if (currentRoom.getAdjacentRooms().get(i).getType().equals(RoomType.PORTAL))
      {
        return true; // return true if a portal room is nearby
      }
    }
    return false; // return false if a portal room is not nearby
  }
  
  /** This method determines whether or not the treasure room is in a nearby room.
   * @return true if the treasure room is nearby, false otherwise
   */
  public boolean isTreasureNearby()
  {
    for (int i = 0; i < currentRoom.getAdjacentRooms().size(); i++)
    {
      if (currentRoom.getAdjacentRooms().get(i).getType().equals(RoomType.TREASURE))
      {
        return true; // return true if a treasure room is nearby
      }
    }
    return false; // return false if a treasure room is not nearby
  }
  
}
