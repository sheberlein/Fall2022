//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Dragon Treasure Game 2.0 - TreasureRoom class
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
 * This class is a sub class of the class Room. It represents the room that contains treasure.
 * @author Sidney Heberlein
 *
 */
public class TreasureRoom extends Room
{
  private static final String TREASURE_WARNING = "You sense that there is treasure nearby.\n";
  private static PImage treasureBackground; //the image ALWAYS used for treasure rooms
  
  /**
   * This is the constructor for a TresureRoom object and have a description of "In the back of this 
   * room, you spot a treasure chest. It is locked...". It initializes all instance data fields.
   * @param ID   the ID to give to this object
   */
  public TreasureRoom(int ID)
  {
    super(ID, "In the back of this room, you spot a treasure chest. "
        + "It is locked...", treasureBackground);
  }
  
  /**
   * This method is the getter for TREASURE_WARNING.
   * @return   the string for warning about treasure being nearby.
   */
  public static String getTreasureWarning()
  {
    return TREASURE_WARNING;
  }
  
  /**
   * This method sets the background image for the TreasureRoom class.
   * @param treasureBackground   the image to be the background
   */
  public static void setTreasureBackground(processing.core.PImage treasureBackground)
  {
    TreasureRoom.treasureBackground = treasureBackground;
  }
  
  /**
   * This method determines whether or not the player can open the treasure chest in the room.
   * @param p   the Player to check if they can open the chest
   * @return   true if the player has the key and is in this TreasureRoom, false otherwise
   */
  public boolean playerCanGrabTreasure(Player p)
  {
    if (p.hasKey() && p.getCurrentRoom().equals(this))
    {
      return true; // if the player has the key and is in this treasure room, return true
    }
    return false;
  }
}
