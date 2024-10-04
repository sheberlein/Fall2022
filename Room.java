//////////////// FILE HEADER //////////////////////////
//
// Title: Dragon Treasure Hunter - Room class
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
import java.util.Random;

/** This class defines a Room that has an ID, a description, and a type. It also defines a teleport
 * location and a portal warning message and a treasure warning message. 
 * @author Sidney Heberlein
 */
public class Room 
{
  private RoomType type; //one of the four types a room could be
  private String roomDescription; //a brief description of the room
  private ArrayList<Room> adjRooms; //arraylist that holds the rooms adjacent
  private final int ID; //unique ID for each room to identify it
  private static int teleportLocationID; //place where all portal rooms will go to
  private static final String PORTAL_WARNING = "You feel a distortion in space nearby.\n";
  private static final String TREASURE_WARNING = "You sense that there is treasure nearby.\n";
  
  /** This is the constructor for the Room object that assigns values to all non-static instance
   * fields
   * @param id the unique ID number for this room
   * @param roomDescription a brief description of this room
   */
  public Room(int id, String roomDescription)
  {
    type = RoomType.NORMAL;
    this.roomDescription = roomDescription;
    adjRooms = new ArrayList<Room>();
    ID = id;
  }
  
  /** This method returns the room type of the current object
   * @return the current object's room type
   */
  public RoomType getType()
  {
    return type;
  }
  
  /** This method returns the ID of the current object
   * @return the current object's ID
   */
  public int getID()
  {
    return ID;
  }
  
  /** This method returns the current object's list of rooms adjacent to it
   * @return the current object's list of rooms adjacent to it
   */
  public ArrayList<Room> getAdjacentRooms()
  {
    return adjRooms;
  }
  
  /** This method returns the description of the current object
   * @return the current object's description
   */
  public String getRoomDescription()
  {
    return roomDescription;
  }
  
  /** This method returns the string that is the room class's portal warning, indicating that there 
   * is one nearby
   * @return the portal warning message string
   */
  public static String getPortalWarning()
  {
    return PORTAL_WARNING;
  }
  
  /** This method returns the string that is the room class's treasure warning, indicating that the
   * treasure room is nearby
   * @return the treasure warning message string
   */
  public static String getTreasureWarning()
  {
    return TREASURE_WARNING;
  }
  
  /** This method returns the id of the room where all portals will teleport to
   * @return the id of the teleportLocation room
   */
  public static int getTeleportationRoom()
  {
    return teleportLocationID;
  }
  
  /** This method takes the given room and adds it to the current object's list of adjacent rooms
   * @param toAdd the room to be added to the adjacent rooms list
   */
  public void addToAdjacentRooms(Room toAdd)
  {
    adjRooms.add(toAdd);
  }
  
  /** This method changes the current object's type to the roomtype given
   * @param newType the new roomtype of this Room object
   */
  public void setRoomType(RoomType newType)
  {
    type = newType;
  }
  
  /** This method sets the class field teleportLocationOD to the integer given
   * @param teleportID the id of the room where all portals should teleport to
   */
  public static void assignTeleportLocation(int teleportID)
  {
    teleportLocationID = teleportID;
  }
  
  /** This method checks whether the given room is adjacent to the current room or not
   * @param r the room that you are seeing if it is adjacent to the current room
   * @return true if they are adjacent, false otherwise
   */
  public boolean isAdjacent(Room r)
  {
    for (int i = 0; i < adjRooms.size(); i++)
    {
      if (adjRooms.get(i).equals(r))
      {
        return true;
      }
    }
    return false;
  }
  
  /** Determines if the given object is equal to this room.
  * They are equal if other is a Room and their IDs are the same.
  * @param other, another object to check if it is equal to this
  * @return true if the two rooms are equal, false otherwise
  * @author Michelle 
  */
  @Override
  public boolean equals(Object other)
  {
    if(other instanceof Room) 
    {
      Room otherRoom = (Room)other;
      return this.ID == otherRoom.ID;
  }
  return false;
  }
  
  /** Returns a String representation of this room.
  * @return the string representation of this room and
  * it’s object data field values
  * @author Michelle
  */
  @Override
  public String toString()
  {
    String s = this.ID +": " + this.roomDescription+ " (" + type +")\n Adjacent Rooms: ";
    for(int i = 0; i<adjRooms.size(); i++)
    {
      s+= adjRooms.get(i).ID +" ";
      }
    return s;
  }
}
