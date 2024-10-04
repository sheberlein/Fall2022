//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P10 Course Registration - CourseReg class
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

import java.util.Iterator;

/**
 * A application handler for course registration using a priority queue.
 */
public class CourseReg 
{

  // data fields
  private CourseQueue courses;  // the priority queue of all courses
  private int creditLoad;       // the maximum number of credits you want to take
  
  /**
   * Creates a new course registration object
   * @param capacity the maximum number of courses to store in the priority queue
   * @param creditLoad the maximum number of credits to take next semester
   * @throws IllegalArgumentException if either capacity or creditLoad are not a positive integer
   */
  public CourseReg(int capacity, int creditLoad) throws IllegalArgumentException 
  {
    if (creditLoad <= 0)
      throw new IllegalArgumentException("Credit load cannot be zero or negative!");
    if (capacity <= 0)
      throw new IllegalArgumentException("Capacity cannot be zero or negative!");
    courses = new CourseQueue(capacity);
    this.creditLoad = creditLoad;
  }
  
  /**
   * Returns a string representation of the highest-priority courses with a total number of credits
   * less than or equal to the creditLoad of this CourseReg object. Use the Iterable property of the
   * CourseQueue to help you out!
   * 
   * Note that this is NOT a "knapsack" problem - you're trying to maximize priority, not number of
   * credits. Just add courses to your result String until adding the next would take you over this
   * CourseReg object's creditLoad limit.
   * 
   * @return a string representation with one course on each line, where the total number of credits
   *   represented is less than or equal to the current creditLoad value
   */
  public String getRecommendedCourses() 
  {
    String result = "";
    int totalCreds = 0;
    CourseIterator iterate = new CourseIterator(courses); // a new iterator for courses
    while(iterate.hasNext())
    {
      Course next = iterate.next(); // the next course
      int credits = next.getNumCredits(); // the number of credits for the next course
      if (totalCreds + credits > creditLoad)
      {
        return result;
      }
      else
      {
        totalCreds += credits;
        result += next.toString() + "\n";
      }
    }
    return result;
  }
  
  /**
   * Tries to add the given course to the priority queue; return false if the queue is full
   * 
   * @param toAdd the course to add
   * @return true if the course was successfully added to the queue
   */
  public boolean add(Course toAdd) // TODO fix
  {
    try
    {
      courses.enqueue(toAdd);
    }
    catch (NullPointerException e)
    {
      return false;
    }
    catch (IllegalStateException e)
    {
      return false;
    }
    return true;
  }
  
  /**
   * Updates the creditLoad data field to the provided value
   * @param creditLoad the maximum number of credits to take next semester
   * @throws IllegalArgumentException if creditLoad is not a positive integer
   */
  public void setCreditLoad(int creditLoad) throws IllegalArgumentException 
  {
    if (creditLoad <= 0)
      throw new IllegalArgumentException("credit load cannot be zero or negative!");
    this.creditLoad = creditLoad;
  }
}