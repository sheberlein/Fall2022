//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Dragon Treasure Game 2.0 - PortalRoom class
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
 * This class is a sub class of the class Room. It represents a room that contains a portal.
 * @author Sidney Heberlein
 *
 */
public class PortalRoom extends Room
{
  private Random randGen; //random number generator for location picking
  private static final String PORTAL_WARNING = "You feel a distortion in space nearby.\n";
  private static final String TELEPORT_MESSAGE = "The space distortion teleported you to another "
      + "room!\n";
  private static PImage portalImage; //image of a portal to be shown in all portal rooms
  
  /**
   * This is the constructor for a PortalRoom object. It initializes all instance data fields.
   * @param ID   the ID that this PortalRoom should have
   * @param description   the verbal description this PortalRoom should have
   * @param image   the image that should be used as a background when drawing this PortalRoom.
   */
  PortalRoom(int ID, String description, processing.core.PImage image)
  {
    super(ID, description, image);
    randGen = new Random();
  }
  
  /**
   * This method is the getter for PORTAL_WARNING.
   * @return   the string for warning about a portal being nearby.
   */
  public static String getPortalWarning()
  {
    return PORTAL_WARNING;
  }
  
  /**
   * This method is the getter for TELEPORT_MESSAGE.
   * @return   the string for letting the player know they were teleported.
   */
  public static String getTeleportMessage()
  {
    return TELEPORT_MESSAGE;
  }
  
  /**
   * This method picks an adjacent room at random for the player to teleport into.
   * @return   the room that player should immediately be moved to
   */
  public Room getTeleportLocation()
  {
    int next = randGen.nextInt(super.getAdjacentRooms().size()); // next represents a randomly
    // generated integer from 0 to the size of the adjacent rooms arrayList
    return super.getAdjacentRooms().get(next); // return the randomly generated room
  }
  
  /**
   * This method draws this PortalRoom to the window by drawing the background image, a rectangle, 
   * some text, and the portal image.
   */
  @Override
  public void draw()
  {
    super.draw();
    processing.image(portalImage, 325, 225);
  }
  
  /**
   * This method sets the portal image for the PortalRoom class.
   * @param portalImage   the image to represent the portal
   */
  public static void setPortalImage(processing.core.PImage portalImage)
  {
    PortalRoom.portalImage = portalImage;
  }
}