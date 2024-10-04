//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Dragon Treasure Game 2.0 - Player class
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
 * This class models a Player which is an extension of the Character class. It also implements the
 * Moveable interface. Player has a current room, a description, and an instance field to determine
 * if the player has the key or not.
 * @author Sidney Heberlein
 *
 */
public class Player extends Character implements Moveable
{
  private boolean hasKey;
  
  /**
   * This is the constructor for player object. The label should be "PLAYER" and not have a key by 
   * default.
   * @param currentRoom   the room that the player should start in
   * @throws IllegalArgumentException   if the currentRoom is not a StartRoom
   */
  public Player(Room currentRoom)
  {
    super(currentRoom, "PLAYER"); // call Character's constructor to set the current room and
    // description
    hasKey = false;
  }
  
  /**
   * This method determines if the player has the key.
   * @return   true if the player has the key, false otherwise
   */
  public boolean hasKey()
  {
    return hasKey;
  }
  
  /**
   * This method gives player the key.
   */
  public void obtainKey()
  {
    hasKey = true;
  }
  
  /**
   * This method moves the Player to the destination room.
   * @param destination   the Room to change it to
   * @return   true if the change was successful, false otherwise
   */
  public boolean changeRoom(Room destination)
  {
    if (!canMoveTo(destination))
    {
      return false; // return false if the player cannot move to the destination room
    }
    else
    {
      super.setCurrentRoom(destination);
      return true; // if the player can move to the destination room, set the player's current room
      // to the destination room and return true
    }
  }
  
  /**
   * This method checks if the player can move to the given destination. A valid move is if the 
   * destination is a room adjacent to the player.
   * @param destination   the room to check if the player can move towards
   * @return   true if they can, false otherwise
   */
  public boolean canMoveTo(Room destination)
  {
    if (super.getCurrentRoom().isAdjacent(destination))
    {
      return true; // return true if destination is adjacent to the player's current room
    }
    return false;
  }
  
  /**
   * This method checks if the player needs to teleport and move them if needed.
   * @return   true if a teleport occurred, false otherwise
   */
  public boolean teleport()
  {
    if (super.getCurrentRoom() instanceof PortalRoom)
    {
      Room temp = ((PortalRoom)super.getCurrentRoom()).getTeleportLocation(); // temp is the
      // Room that the player will teleport to
      super.setCurrentRoom(temp);
      return true; // if the player needs to teleport, teleport the player to an adjacent room and
      // return true
    }
    return false; // return false if the player does not need to teleport (is not in a portal room)
  }
  
  /**
   * This method determines whether or not a portal room is nearby. A portal room is considered 
   * nearby if it is one of the adjacent rooms.
   * @return   true if a portal room is nearby, false otherwise
   */
  public boolean isPortalNearby()
  {
    for (int i = 0; i < super.getAdjacentRooms().size(); i++)
    {
      if (super.getAdjacentRooms().get(i) instanceof PortalRoom)
      {
        return true; // return true if a portal room is found in the adjacent rooms arrayList
      }
    }
    return false;
  }
  
  /**
   * This method determines whether or not the treasure room is nearby. The treasure room is 
   * considered nearby if it is one of the adjacent rooms.
   * @return   true if the treasure room is nearby, false otherwise
   */
  public boolean isTreasureNearby()
  {
    for (int i = 0; i < super.getAdjacentRooms().size(); i++)
    {
      if (super.getAdjacentRooms().get(i) instanceof TreasureRoom)
      {
        return true; // return true if a treasure room is found in the adjacent rooms arrayList
      }
    }
    return false;
  }
  
  /**
   * This method determines whether or not the given dragon is nearby. A dragon is considered 
   * nearby if it is in one of the adjacent rooms.
   * @param d   the dragon to check if nearby
   * @return   true if the dragon is nearby, false otherwise
   */
  public boolean isDragonNearby(Dragon d)
  {
    if (d.getCurrentRoom().isAdjacent(getCurrentRoom()))
    {
      return true; // return true if the dragon's current room is adjacent to this player's current
      // room
    }
    return false;
  }
}
