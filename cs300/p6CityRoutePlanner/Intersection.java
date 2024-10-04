//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    City Route Planner - Intersection class
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

/**
 * This class represents a single intersection point where two streets cross at specified x and y 
 * coordinate positions.
 * @author Sidney Heberlein
 *
 */
public class Intersection 
{
  private final int x; // X-axis coordinate of this intersection
  private final int y; // Y-axis coordinate of this intersection
  
  /**
   * This is the constructor for the Intersection class. It initializes this intersection with the 
   * given coordinates
   * @param x   horizontal position of this Intersection
   * @param y   vertical position of this Intersection
   */
  public Intersection(int x, int y)
  {
    this.x = x;
    this.y = y;
  }
  
  /**
   * This method returns the horizontal position of this Intersection
   * @return   the horizontal position of this Intersection
   */
  public int getX()
  {
    return x;
  }
  
  /**
   * This method returns the vertical position of this Intersection
   * @return   the vertical position of this Intersection
   */
  public int getY()
  {
    return y;
  }
  
  /**
   * This method returns a coordinate-pair representation of this Intersection in the form "(x,y)"
   * @return    a coordinate-pair representation of this Intersection
   */
  @Override
  public String toString()
  {
    return "(" + x + "," + y + ")";
  }
  
  /**
   * This method returns true if the given Object is identical to this Intersection
   * @param o  object to compare for equality
   * @return   true if the given Object is an Intersection object which has the same x and y 
   *           coordinates as this Intersection and false otherwise
   */
  @Override
  public boolean equals(Object o)
  {
    if (o instanceof Intersection)
    {
      if (((Intersection)o).getX() == x && ((Intersection)o).getY() == y)
      {
        return true; // return true if Object o is instanceof Intersection and it has the same x
        // and y coordinates as this Intersection object
      }
    }
    return false;
  }
  
  /**
   * This method creates a new Intersection instance which is one step directly above this 
   * Intersection. Should not modify the original Intersection object.
   * @return   a new Intersection instance which is one step directly above this Intersection
   */
  public Intersection goNorth()
  {
    return new Intersection(x, y + 1);
  }
  
  /**
   * This method creates a new Intersection instance which is one step directly below this 
   * Intersection. Should not modify the original Intersection object.
   * @return   a new Intersection instance which is one step directly below this Intersection
   */
  public Intersection goSouth()
  {
    return new Intersection(x, y - 1);
  }
  
  /**
   * This method creates a new Intersection instance which is one step directly to the right of 
   * this Intersection object. Should not modify the original Intersection object.
   * @return   a new Intersection instance which is one step directly to the right of this 
   *           Intersection
   */
  public Intersection goEast()
  {
    return new Intersection(x + 1, y);
  }
  
  /**
   * This method creates a new Intersection instance which is one step directly to the left of this 
   * Intersection. Should not modify the original Intersection object.
   * @return   a new Intersection instance which is one step directly to the left of this 
   *           Intersection
   */
  public Intersection goWest()
  {
    return new Intersection(x - 1, y);
  }
}
