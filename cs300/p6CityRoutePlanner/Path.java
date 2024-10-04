//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    City Route Planner - Path class
// Course:   CS 300 Fall 2022
//
// Author:   Sidney Heberlein
// Email:    sheberlein@gmail.com
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// N/A
// 
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class represents a valid path through a grid of city intersections surrounded by streets. 
 * That is, one which only moves either one step directly east, or one step directly north at each 
 * step, meaning that only northeast paths from one intersection point to another are allowed. A 
 * list of intersection elements creates the path.
 * @author Sidney Heberlein
 *
 */
public class Path 
{
  private ArrayList<Intersection> intersections; // List of Intersections followed in this Path
  
  /**
   * This is the constructor for the Path class. It initializes this Path to start as empty
   */
  public Path()
  {
    intersections = new ArrayList<Intersection>();
  }
  
  /**
   * This method returns the number of Intersections in this Path
   * @return   the number of Intersections in this Path
   */
  public int length()
  {
    return intersections.size();
  }
  
  /**
   * This method returns the first Intersection in this Path, if it is not empty. Otherwise, throws 
   * a NoSuchElementException.
   * @return   the first Intersection in this Path, if it is not empty
   * @throws   NoSuchElementException - if this Path is empty
   */
  public Intersection getHead() throws NoSuchElementException
  {
    if (this.length() == 0) // if the length of the intersections ArrayList is 0, throw an exception
    {
      throw new NoSuchElementException("The path is empty!");
    }
    else
    {
      return intersections.get(0);
    }
  }
  
  /**
   * This method returns the last Intersection in this Path, if it is not empty. Otherwise, throws 
   * a NoSuchElementException.
   * @return   the last Intersection in this Path, if it is not empty
   * @throws   NoSuchElementException - if this Path is empty
   */
  public Intersection getTail() throws NoSuchElementException
  {
    if (this.length() == 0) // if the length of the intersections ArrayList is 0, throw an exception
    {
      throw new NoSuchElementException("The path is empty!");
    }
    else
    {
      return intersections.get(this.length() - 1);
    }
  }
  
  /**
   * This method adds the given Intersection to the end of this Path if it is a valid addition. An 
   * Intersection is a valid addition if the current Path is empty, or the Intersection to add is 
   * one step directly east, or one step directly north of the current tail Intersection in this 
   * Path. Should throw an IllegalArgumentException if the given Intersection is not a valid 
   * addition.
   * @param toAdd   Intersection to add to the end of this Path
   * @throws   IllegalArgumentException - if the Intersection to add is not valid
   */
  public void addTail(Intersection toAdd) throws IllegalArgumentException
  {
    if (this.length() == 0 || (toAdd.getX() == getTail().getX() + 1) 
        || (toAdd.getY() == getTail().getY() + 1))
    {
      intersections.add(toAdd);
    }
    else
    {
      throw new IllegalArgumentException("The given Intersection to add is not valid.");
    }
  }
  
  /**
   * This method adds the given Intersection to the front of this Path if it is a valid addition. A 
   * Intersection is a valid addition if the current Path is empty, or the Intersection to add is 
   * one step directly west, or one step directly south of the current head Intersection in this 
   * Path. Should throw an IllegalArgumentException if the given Intersection is not a valid 
   * addition.
   * @param toAdd   Intersection to add to the beginning of this Path
   * @throws    IllegalArgumentException - if the Intersection to add is not valid
   */
  public void addHead(Intersection toAdd) throws IllegalArgumentException
  {
    if (this.length() == 0 || ((toAdd.getX() == getHead().getX() - 1) 
        && toAdd.getY() == getHead().getY()) || ((toAdd.getY() == getHead().getY() - 1 
        && toAdd.getX() == getHead().getX())))
    {
      intersections.add(0, toAdd);
    }
    else
    {
      throw new IllegalArgumentException("The given Intersection to add is not valid.");
    }
  }
  
  /**
   * This method returns a String representing the coordinates taken in this Path. An empty Path 
   * should return the String "Empty", while a non-empty Path should return the coordinates of the 
   * Intersections it visits separated by a "->". For example: (0,0)->(1,0)->(1,1)->(1,2)
   * @return   a String representing the coordinates followed by this Path
   */
  @Override
  public String toString()
  {
    if (this.length() == 0)
    {
      return "Empty";
    }
    else
    {
      String returnThis = ""; // returnThis stores a String of the coordinates of the Intersections
      // in this Path that will be returned
      for (int i = 0; i < intersections.size(); i++)
      {
        if (i == intersections.size() - 1)
        {
          returnThis += intersections.get(i).toString(); // if this is the last Intersection in this
          // Path, do not add an arrow at the end of the String to be returned
        }
        else
        {
          returnThis += intersections.get(i).toString() + "->";
        }
      }
      return returnThis;
    }
  }
}