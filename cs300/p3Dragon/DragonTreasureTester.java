//////////////// FILE HEADER //////////////////////////
//
// Title: Dragon Treasure Hunter Tester
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

/** This class defines a tester class for the Room, Player, and Dragon classes to make sure that 
 * their methods work effectively and do not have any bugs.
 * @author Sidney Heberlein
 */
public class DragonTreasureTester 
{
  
  /** This method tests the effectiveness of all of the instance methods defined in the Room class.
   * @return true if all tests pass and false otherwise.
   */
  public static boolean testRoomInstanceMethods()
  {
    try
    {
   // create a Room
      Room den = new Room(3, "A test room"); // den is a test room with ID 3, description "A test
      // room" and room type NORMAL
      // if the adjacent rooms arraylist is null after the constructor is called, return false
      if (den.getAdjacentRooms() == null)
      {
        return false;
      }
      // test the effectiveness of the constructor, the getID method, the getRoomDescription method,
      // and the getType method
      // method
      if (!(den.getID() == 3 && den.getRoomDescription().equals("A test room") 
          && den.getType().equals(RoomType.NORMAL)))
      {
        System.out.println("testRoomInstanceMethods-testing constructor, getID, getRoomDescription,"
            + " and getType Problem detected: your constructor didn't assign the correct values to /n"
            + "ID and roomDescription, or the getID or getRoomDescription methods did not correctly "
            + "return the values of ID or roomDescription.");
        return false; // return false if the constructor did not correctly assign values to ID and
        // roomDescription or if the getID or getRoomDescription methods do not return the correct
        // values
      }
      
      // testing setRoomType and getType methods
      den.setRoomType(RoomType.PORTAL);
      if (!den.getType().equals(RoomType.PORTAL))
      {
        System.out.println("testRoomInstanceMethods-testing setRoomType and getType Problem detected:"
            + " your room did not correctly get set to a portal room or your getType method did not "
            + "work as expected");
        return false; // return false if the room type of den did not get set to portal as expected
      }
      
      // testing addToAdjacentRooms and getAdjacentRooms methods
      ArrayList<Room> testAdjRooms = new ArrayList<Room>(); // create a tester ArrayList for
      // adjacent rooms
      Room room1 = new Room(4, "second test room");
      Room room2 = new Room(5, "third test room");
      Room room3 = new Room(6, "fourth test room");
      testAdjRooms.add(room1);
      testAdjRooms.add(room2);
      testAdjRooms.add(room3);
      for (int i = 0; i < testAdjRooms.size(); i++)
      {
        den.addToAdjacentRooms(testAdjRooms.get(i)); // fill the den's adjacent rooms arraylist
        // with the rooms from the testAdjRooms arraylist
      }
      
      for (int i = 0; i < testAdjRooms.size(); i++)
      {
        if (!testAdjRooms.get(i).equals(den.getAdjacentRooms().get(i)))
        {
          System.out.println("testRoomInstanceMethods-testing addToAdjacentRooms and "
              + "getAdjacentRooms Problem detected: rooms were not properly added to a test "
              + "arraylist or the adjRooms arraylist did not contain the expected elements.");
          return false; // return false if any element of the adjacent rooms arraylist does not
          // match the expected result defined in the testAdjRooms arraylist
        }
      }
      
      // testing isAdjacent method
      // 1. test the output when isAdjacent is passed an adjacent room
      {
        if (!den.isAdjacent(room1))
        {
          System.out.println("testRoomInstanceMethods-testing isAdjacent Scenario 1: Problem "
              + "detected: isAdjacent did not return the expected value when passed an adjacent "
              + "room.");
          return false;
        }
      }
      
      //2. test the effectiveness of isAdjacent when passed a non adjacent room
      {
        Room room4 = new Room(7, "fifth test room");
        if (den.isAdjacent(room4) == true)
        {
          System.out.println("testRoomInstanceMethods-testing isAdjacent Scenario 2: Problem "
              + "detected: isAdjacent did not return the expected value when passed a non-adjacent "
              + "room.");
          return false;
        }
      }
      return true; // return true if all the tests pass and no errors are detected
    }
    catch(Exception e){ // catch any unexpected exception
      System.out.println("Error in the testRoomInstanceMethods class: an exception was thrown.");
      return false;
    }
    
  }
  
  /** This method tests the effectiveness of all of the static methods defined in the Room class.
   * @return true if all tests pass and false otherwise.
   */
  public static boolean testRoomStaticMethods()
  {
    try
    {
      Room den = new Room(3, "A test room"); // den is a test room with ID 3, description "A test
      // room" and room type NORMAL
      
      // test the getPortalWarning method
      if (!den.getPortalWarning().equals("You feel a distortion in space nearby.\n"))
      {
        System.out.println("testRoomStaticMethods-testing getPortalWarning Problem detected: the "
            + "getPortalWarning method did not return the expected warning text.");
        return false; // return false if the getPortalWarning method did not return the expected
        // warning about portals
      }
      
      // test the getTreasureWarning method
      if (!den.getTreasureWarning().equals("You sense that there is treasure nearby.\n"))
      {
        System.out.println("testRoomStaticMethods-testing getTreasureWarning Problem detected: the "
            + "getTreasureWarning method did not return the expected warning text.");
        return false; // return false if the getTreasureWarning method did not return the expected
        // warning about nearby treasure
      }
      
      //test the assignTeleportLocation and getTeleportationRoom methods
      Room.assignTeleportLocation(2);
      if (Room.getTeleportationRoom() != 2)
      {
        System.out.println("testRoomStaticMethods-testing assignTeleportLocation and "
            + "getTeleportationRoom Problem detected: the assignTeleportLocation did not assign "
            + " the correct value to teleportLocationID or the getTeleportationRoom method did not "
            + "return the expected value");
        return false; // return false if the assignTeleportLocation method did not set the teleport
        // location correctly or if the getTeleportationRoom method did not return the correct value
      }
    }catch(Exception e)
    { // catch any unexpected error
     System.out.println("error in testRoomStaticMethods class: an exception was thrown.");
     return false;
    }
    
    return true; // return true if all the tests pass and no errors are detected
  }
  
  /** This method tests the effectiveness of the canMoveTo method in the Player class.
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testPlayerCanMoveTo()
  {
      try 
      {
        ArrayList<Room> adjacentTest = new ArrayList<Room>(); // the arraylist adjacentTest stores 
        // adjacent rooms that the player can move to
        Room r1 = new Room (111, "Kitchen");
        Room r2 = new Room (222, "Living Room");
        Room r3 = new Room (333, "Bathroom"); // create 3 new Rooms and add them to adjacentTest
        adjacentTest.add(r1);
        adjacentTest.add(r2);
        adjacentTest.add(r3);
        Player one = new Player(new Room(444, "Bedroom")); // create a new player to test canMoveTo
        Room r4 = new Room(555, "Basement"); // create a new Room that is not adjacent to the player
        for (int i = 0; i < adjacentTest.size(); i++)
        {
          one.getCurrentRoom().addToAdjacentRooms(adjacentTest.get(i)); // fill the object den's
          // adjacent rooms arraylist with the rooms from the testAdjRooms arraylist
        }
        
        // 1. test a room that would be valid to move to
        {
          if (one.canMoveTo(r2) != true)
          {
            System.out.println("testPlayerCanMoveTo-scenario 1. Problem detected: Your canMoveTo did"
                + " not return the expected output when passed a valid room to move to.");
            return false;
          }
        }
        
        // 2. test a room that would NOT be valid to move to
        {
          if (one.canMoveTo(r4) != false)
          {
            System.out.println("testPlayerCanMoveTo-scenario 2. Problem detected: Your canMoveTo did"
                + " not return the expected output when passed an invalid room to move to.");
            return false;
          }
        }
      }catch(Exception e)
      { // catch any unexpected exception
       System.out.println("error in testPlayerCanMoveTo: an exception was thrown.");
       return false;
      }
    return true; // return true if all tests pass
  }
  
  /** This method tests the effectiveness of the shouldTeleport method in the Player class.
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testPlayerShouldTeleport()
  {
    try
    {
      Room testPortal = new Room(123, "portal");
      testPortal.setRoomType(RoomType.PORTAL); // create a new room of type portal
      Room notPortal = new Room(456, "not a portal"); // create a new room that is not a portal
      
      // 1. trying to teleport from a room of type portal
      {
        Player p1 = new Player(testPortal); // a new player that is in a portal room
        if (p1.shouldTeleport() != true)
        {
          System.out.println("testPlayerShouldTeleport-scenario 1. Problem detected: Your"
              + " shouldTeleport did not return the expected output when the player was in a portal"
              + " room.");
          return false;
        }
      }
      
      // 2. trying to teleport from a room that is NOT a portal room
      {
        Player p2 = new Player(notPortal); // a new player that is not in a portal room
        if (p2.shouldTeleport() != false)
        {
          System.out.println("testPlayerShouldTeleport-scenario 2. Problem detected: Your"
              + " shouldTeleport did not return the expected output when the player was not in a "
              + "portal room.");
          return false;
        }
      }
    }
    catch(Exception e){ // catch any unexpected exception
      System.out.println("error in testPlayerShouldTeleport: an exception was thrown.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /** This method tests the effectiveness of the isPortalNearby and isTreasureNearby methods from
   * the Player class
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testPlayerDetectNearbyRooms()
  {
    try
    {
   // 1. test the methods when there is no treasure or portal room nearby
      {
        ArrayList<Room> adjacentTest = new ArrayList<Room>();
        Room r1 = new Room (111, "Kitchen");
        Room r2 = new Room (222, "Living Room");
        Room r3 = new Room (333, "Bathroom"); // create 3 new normal Rooms and add them to adjacentTest
        adjacentTest.add(r1);
        adjacentTest.add(r2);
        adjacentTest.add(r3);
        Player one = new Player(new Room(444, "Bedroom"));
        for (int i = 0; i < adjacentTest.size(); i++)
        {
          one.getCurrentRoom().addToAdjacentRooms(adjacentTest.get(i)); // fill the object den's
          // adjacent rooms arraylist with the rooms from the testAdjRooms arraylist
        }
        if (one.isPortalNearby() || one.isTreasureNearby())
        {
          System.out.println("testPlayerDetectNearbyRooms-scenario 1. Problem detected: Your"
              + " isPortalNearby or isTreasureNearby methods did not return the expected output when "
              + "the player was not nearby either a treasure room or a portal room.");
          return false;
        }
      }
      
      // 2. test the methods when there is a treasure room and a portal room nearby
      {
        ArrayList<Room> adjacentTest1 = new ArrayList<Room>();
        Room rm1 = new Room (111, "Kitchen");
        Room rm2 = new Room (222, "Living Room");
        Room rm3 = new Room (333, "Bathroom");
        rm1.setRoomType(RoomType.PORTAL);
        rm3.setRoomType(RoomType.TREASURE); // create 3 Rooms and make one a portal room and one a
        // treasure room
        adjacentTest1.add(rm1);
        adjacentTest1.add(rm2);
        adjacentTest1.add(rm3);
        Player two = new Player(new Room(987, "hallway"));
        for (int i = 0; i < adjacentTest1.size(); i++)
        {
          two.getCurrentRoom().addToAdjacentRooms(adjacentTest1.get(i)); // fill the object den's
          // adjacent rooms arraylist with the rooms from the testAdjRooms arraylist
        }
        if (!(two.isPortalNearby() && two.isTreasureNearby()))
        {
          System.out.println("testPlayerDetectNearbyRooms-scenario 2. Problem detected: Your"
              + " isPortalNearby or isTreasureNearby methods did not return the expected output when "
              + "the player was nearby a treasure room and a portal room.");
          return false;
        }
      }
    }
    catch(Exception e){ // catch any unexpected exception
      System.out.println("error in testPlayerDetectNearbyRooms: an exception was thrown.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /** This method tests the effectiveness of the changeRooms method in the Dragon class
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testDragonChangeRooms()
  {
    try
    {
   // create some tester rooms and a player
      Room roomOne = new Room(11, "Normal room");
      Room roomTwo = new Room(22, "Portal room");
      Room roomThree = new Room(33, "Kitchen");
      Room roomFour = new Room(44, "Dining room");
      Room roomFive = new Room(55, "Treasure room");
      roomTwo.setRoomType(RoomType.PORTAL);
      roomFive.setRoomType(RoomType.TREASURE);
      Room roomSix = new Room(66, "Hallway");
      Dragon d1 = new Dragon(roomSix); // create a dragon to test changeRooms
      // add the created rooms to the Player's adjacent rooms arraylist
      d1.getCurrentRoom().addToAdjacentRooms(roomOne);
      d1.getCurrentRoom().addToAdjacentRooms(roomTwo);
      d1.getCurrentRoom().addToAdjacentRooms(roomThree);
      d1.getCurrentRoom().addToAdjacentRooms(roomFour);
      d1.getCurrentRoom().addToAdjacentRooms(roomFive);
      roomOne.addToAdjacentRooms(d1.getCurrentRoom());
      roomTwo.addToAdjacentRooms(d1.getCurrentRoom());
      roomThree.addToAdjacentRooms(d1.getCurrentRoom());
      roomFour.addToAdjacentRooms(d1.getCurrentRoom());
      roomFive.addToAdjacentRooms(d1.getCurrentRoom());
      // have the dragon change rooms
        d1.changeRooms();
        // test if the dragon changed into a portal room
        if (d1.getCurrentRoom().getType().equals(RoomType.PORTAL))
        {
          System.out.println("testDragonChangeRooms- Problem detected: Your dragon moved into a "
              + "portal room.");
          return false;
        }
      // test if the dragon moved to a valid adjacent room
      if (!(d1.getCurrentRoom().equals(roomOne) || d1.getCurrentRoom().equals(roomThree) 
          || d1.getCurrentRoom().equals(roomFour) || d1.getCurrentRoom().equals(roomFive)))
      {
        System.out.println("testDragonChangeRooms- Problem detected: Your dragon did not move into "
            + "an adjacent room.");
        return false;
      }
    }
    catch(Exception e){ // catch any unexpected exception
      System.out.println("error in testPlayerShouldTeleport: an exception was thrown.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /** This method runs all of the test methods in the DragonTreasureTester class and outputs their
   * result
   * @param args an array of String[] objects that stores Java command-line arguments
   */
  public static void main(String[] args) 
  {
    System.out.println("testRoomInstanceMethods: " + DragonTreasureTester.testRoomInstanceMethods());
    System.out.println("testRoomStaticMethids: " + DragonTreasureTester.testRoomStaticMethods());
    System.out.println("testPlayerCanMoveTo: " + DragonTreasureTester.testPlayerCanMoveTo());
    System.out.println("testPlayerShouldTeleport: " + DragonTreasureTester.testPlayerShouldTeleport());
    System.out.println("testPlayerDetectNearbyRooms: " 
    + DragonTreasureTester.testPlayerDetectNearbyRooms());
    System.out.println("testDragonChangeRooms: " + DragonTreasureTester.testDragonChangeRooms());
  }
}
