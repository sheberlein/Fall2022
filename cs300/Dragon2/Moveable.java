//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Dragon Treasure Game 2.0 - Moveable interface
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

import java.util.ArrayList;

/**
 * Interface for things that can move between rooms in the DragonTreasureGame.
 * @author Michelle
 *
 */
public interface Moveable 
{
	/**
	 * Changes the room where the object is.
	 * @param destination, the Room to change it to
	 * @return true if the change is successful (a valid move), and false otherwise
	 */
	public boolean changeRoom(Room destination);
	
	/**
	 * Gets the list of rooms adjacent to this movable object.
	 * @return an ArrayList of rooms adjacent to the object
	 */
	public ArrayList<Room> getAdjacentRooms();
	
	/**
	 * Checks whether or not this object can move to that room.
	 * @param destination, the Room to check if it can move to
	 * @return true if it can move there (a valid move), and false otherwise
	 */
	public boolean canMoveTo(Room destination);
}
