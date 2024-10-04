//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    City Route Planner - PathUtils class
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
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class contains utility methods for planning a trip through a grid of city intersections
 * @author Sidney Heberlein
 *
 */
public class PathUtils 
{
  /**
   * This is the constructor for the PathUtils class
   */
  public PathUtils()
  { 
  }
  
  /**
   * This method finds the number of valid Paths between the given start and end Intersections. If 
   * it is not possible to get from the start to the end intersection by moving up or right, then 0 
   * should be returned. For example, if start is Intersection(0, 0) and end is Intersection(2, 1), 
   * this method should return 3. If start is Intersection(1, 0) and end is Intersection(0, 0), this 
   * method should return 0. MUST be implemented recursively. If you wish, you can use a call to a 
   * private static helper method which is recursive.
   * @param start   Intersection to start at
   * @param end   Intersection to end at
   * @return   the number of valid Paths which start and end at the given Intersections
   */
  public static int countPaths(Intersection start, Intersection end)
  {
    // base cases
    if (start.equals(end))
    {
      return 1; // if the starting Intersection equals the ending Intersection, return 1
    }
    if (start.getX() > end.getX())
    {
      return 0; // if the starting Intersection has an x value greater than the ending
      // Intersection's x value, that means the starting Intersection is to the east of the ending
      // Intersection, which is not a valid move.
    }
    if (start.getY() > end.getY())
    {
      return 0; // if the starting Intersection has an y value greater than the ending
      // Intersection's y value, that means the starting Intersection is to the north of the ending
      // Intersection, which is not a valid move.
    }
    
    // recursive cases
    return countPaths(start.goEast(), end) + countPaths(start.goNorth(), end);    
  }
  
  /**
   * This method finds all valid Paths between the given start and end Intersections. If it is not 
   * possible to get from the start to the end intersection by moving up or right, then an empty 
   * ArrayList should be returned. For example, if start is Intersection(0, 0) and end is 
   * Intersection(2, 1), this method should return an ArrayList consisting of the following Paths:
   * (0,0)->(1,0)->(2,0)->(2,1)
   * (0,0)->(1,0)->(1,1)->(2,1)
   * (0,0)->(0,1)->(1,1)->(2,1)
   * If start is Intersection(1, 0) and end is Intersection(0, 0), this method should return an 
   * empty ArrayList. MUST be implemented recursively. If you wish, you can use a call to a private 
   * static helper method which is recursive.
   * @param start   Intersection to start at
   * @param end   Intersection to end at
   * @return   an ArrayList containing all valid Paths which start and end at the given Intersections
   */
  public static ArrayList<Path> findAllPaths(Intersection start, Intersection end)
  {
    ArrayList<Path> list = new ArrayList<Path>();
    Path path = new Path();
    // base cases
    if (start.equals(end))
    {
      path.addHead(start);
      list.add(path);
      return list; // if the starting Intersection equals the ending Intersection, return an
      // ArrayList with one Intersection (the starting/ending point)
    }
    if (start.getX() > end.getX())
    {
      return new ArrayList<Path>(); // if the starting Intersection has an x value greater than the 
      // ending Intersection's x value, that means the starting Intersection is to the east of the
      // ending Intersection, which is not a valid move. Return empty ArrayList.
    }
    if (start.getY() > end.getY())
    {
      return new ArrayList<Path>(); // if the starting Intersection has an y value greater than the
      // ending Intersection's y value, that means the starting Intersection is to the north of the
      // ending Intersection, which is not a valid move. Return empty ArrayList.
    }
    
    // recursive cases
    ArrayList<Path> temp1 = new ArrayList<Path>(); // temp1 stores an ArrayList of the recursive 
    // call to findAllPaths going one step north
    temp1 = findAllPaths(start.goNorth(), end);
    ArrayList<Path> temp2 = new ArrayList<Path>(); // temp2 stores an ArrayList of the recursive
    // call to findAllPaths going one step east
    temp2 = findAllPaths(start.goEast(), end);
    for (int i = 0; i < temp1.size(); i++)
    {
      temp1.get(i).addHead(start); // add an intersection to the beginning of all Paths in temp1
      list.add(temp1.get(i)); // add the element to the overall list to return
    }
    for (int i = 0; i < temp2.size(); i++)
    {
      temp2.get(i).addHead(start); // add an intersection to the beginning of all Paths in temp2
      list.add(temp2.get(i)); // add the element to the overall list to return
    }
    return list;
  }
}