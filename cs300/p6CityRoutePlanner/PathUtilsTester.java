//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    City Route Planner - PathUtilsTester class
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
import java.util.Scanner;

public class PathUtilsTester 
{
  /**
   * This method tests the case of countPaths() when there are no valid Paths. For example, when the
   * start position is Intersection(1, 1) and the ending position is Intersection(0, 1), there 
   * should be no valid Paths, so countPaths() should return 0.
   * @return   true if all test cases are passed
   */
  public static boolean testCountPathsNoPath()
  {
    // test case 1
    try
    {
      Intersection start = new Intersection(1, 1);
      Intersection end = new Intersection(0, 1);
      if (PathUtils.countPaths(start, end) != 0)
      {
        System.out.println("Problem scenario in testCountPathsNoPath: when there was no valid path, "
            + "your countPaths method did not return the expected value, 0.");
        return false; // return false if 0 paths were not returned
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected error occurred in testCountPathsNoPath.");
      return false; // return false if an unexpected error occurred
    }
    
    //test case 2
    try 
    {
      Intersection start = new Intersection(3, 8);
      Intersection end = new Intersection(3, 6);
      if (PathUtils.countPaths(start, end) != 0)
      {
        System.out.println("Problem scenario in testCountPathsNoPath: when there was no valid path, "
            + "your countPaths method did not return the expected value, 0.");
        return false; // return false if 0 paths were not returned
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected error occurred in testCountPathsNoPath.");
      return false; // return false if an unexpected error occurred
    }
    return true;
  }
  
  /**
   * This method tests the case of countPaths() when there is a single valid Path. For example, when
   * the start position is Intersection(1, 1) and the ending position is Intersection(1, 2), there 
   * should be a single Path, so countPaths() should return 1.
   * @return   true if all test cases are passed
   */
  public static boolean testCountPathsOnePath()
  {
    // test case 1
    try
    {
      Intersection start = new Intersection(1, 1);
      Intersection end = new Intersection(1, 2);
      if (PathUtils.countPaths(start, end) != 1)
      {
        System.out.println("Problem scenario in testCountPathsOnePath: when there was one valid"
            + " path, your countPaths method did not return the expected value, 1.");
        return false; // return false if one path was not returned
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected error occurred in testCountPathsOnePath.");
      return false; // return false if an unexpected error occurred
    }
    // test case 2
    try
    {
      Intersection start = new Intersection(0, 3);
      Intersection end = new Intersection(0, 4);
      if (PathUtils.countPaths(start, end) != 1)
      {
        System.out.println("Problem scenario in testCountPathsOnePath: when there was one valid"
            + " path, your countPaths method did not return the expected value, 1.");
        return false; // return false if one path was not returned
      }
    }
    catch (Exception e)
    {
      System.out.println("Problem scenario in testCountPathsOnePath: when there was one valid"
          + " path, your countPaths method did not return the expected value, 1.");
      return false; // return false if one path was not returned
    }
    return true;
  }
  
  /**
   * This method tests the case of countPaths() when there are multiple possible paths. For example,
   * when the start position is Intersection(0, 0) and the ending position is Intersection(1, 2), 
   * there should be three possible Paths, so countPaths() should return 3.
   * @return   true if all test cases are passed
   */
  public static boolean testCountPathsRecursive()
  {
    try
    {
      Intersection start = new Intersection(0, 0);
      Intersection end = new Intersection(1, 2);
      if (PathUtils.countPaths(start, end) != 3)
      {
        System.out.println("Problem scenario in testCountPathsRecursive: when there were three valid"
            + " paths, your countPaths method did not return the expected value, 3.");
        return false; // return false if three paths were not returned
      }
      Intersection start2 = new Intersection(0, 0);
      Intersection end2 = new Intersection(5, 2);
      if (PathUtils.countPaths(start2, end2) != 21)
      {
        System.out.println("Problem scenario in testCountPathsRecursive: when there were 21 valid"
            + " paths, your countPaths method did not return the expected value, 21.");
        return false; // return false if 21 paths were not returned
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected error occurred in testCountPathsRecursive.");
      return false; // return false if an unexpected error occurred
    }
    return true;
  }
  
  /**
   * This method tests the case of findAllPaths() when there are no valid Paths. For example, when 
   * the start position is Intersection(1, 1) and the ending position is Intersection(0, 1), there 
   * should be no valid Paths, so findAllPaths() should return an empty ArrayList.
   * @return   true if all test cases are passed
   */
  public static boolean testFindAllPathsNoPath()
  {
    try
    {
      Intersection start = new Intersection(1, 1);
      Intersection end = new Intersection(0, 1);
      if (PathUtils.findAllPaths(start, end).size() != 0)
      {
        System.out.println("Problem scenario in testFindAllPaths: when there were no valid paths,"
            + " your findAllPaths method did not return an empty ArrayList.");
        return false; // return false if an empty ArrayList was not returned
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected error occurred in testFindAllPathsNoPath.");
      return false; // return false if an unexpected error occurred
    }
    return true;
  }
  
  /**
   * This method tests the case of findAllPaths() when there is a single valid Path. For example, 
   * when the start position is Intersection(1, 1) and the ending position is Intersection(1, 2), 
   * there should be a single Path. For each of your cases, ensure that there is only a single path,
   * and that the Path exactly matches what you expect to see.
   * @return   true if all test cases are passed
   */
  public static boolean testFindAllPathsOnePath()
  {
    try
    {
      Intersection start = new Intersection(1, 1);
      Intersection end = new Intersection(1, 2);
      ArrayList<Path> returned = PathUtils.findAllPaths(start, end);
      ArrayList<Path> expected = new ArrayList<Path>();
      Path p = new Path(); // a new path to add to the expected ArrayList
      p.addHead(new Intersection(0, 1)); // making the path have one intersection
      expected.add(p);
      if (returned.size() != 1 && !returned.get(0).equals(expected.get(0)))
      {
        System.out.println("Problem scenario in testFindAllPathsOnePath: when there was one valid "
            + "path, your findAllPaths method did not return the expected ArrayList.");
        return false; // return false if one path was not returned
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected error occurred in testFindAllPathsOnePath.");
      return false; // return false if an unexpected error occurred
    }
    return true;
  }
  
  /**
   * This method tests the case of findAllPaths() when there are multiple possible paths. For 
   * example, when the start position is Intersection(0, 0) and the ending position is 
   * Intersection(1, 2), there should be three possible Paths. For each of your cases, ensure that 
   * there is both the correct number of Paths, and that the returned Paths exactly match what you 
   * expect to see. Remember: The order the Paths appear in the output of findAllPaths() will not 
   * necessarily match your own implementation.
   * @return   true if all test cases are passed
   */
  public static boolean testFindAllPathsRecursive()
  {
    try
    {
      Intersection start = new Intersection(0, 0);
      Intersection end = new Intersection(1, 2);
      ArrayList<Path> returned = PathUtils.findAllPaths(start, end);
      Path p1 = new Path(); // a new path to add to the expected ArrayList
      Path p2 = new Path(); // a new path to add to the expected ArrayList
      Path p3 = new Path(); // a new path to add to the expected ArrayList
      p1.addTail(new Intersection(0, 0)); // adding 4 Intersections to path 1
      p1.addTail(new Intersection(0, 1));
      p1.addTail(new Intersection(0, 2));
      p1.addTail(new Intersection(1, 2));
      p2.addTail(new Intersection(0, 0)); // adding 4 Intersections to path 2
      p2.addTail(new Intersection(1, 0));
      p2.addTail(new Intersection(1, 1));
      p2.addTail(new Intersection(1, 2));
      p3.addTail(new Intersection(0, 0)); // adding 4 Intersections to path 3
      p3.addTail(new Intersection(0, 1));
      p3.addTail(new Intersection(1, 1));
      p3.addTail(new Intersection(1, 2));
      ArrayList<Path> expected = new ArrayList<Path>();
      expected.add(p1);
      expected.add(p2);
      expected.add(p3);
      if (returned.size() != 3)
      {
        System.out.println("Problem scenario testFindAllPathsRecursive: Your findAllPaths method did"
            + " not return the correct ArrayList");
        return false;
      }
      
      ArrayList<String> temp1 = new ArrayList<String>(); // stores the toString representations of
      // the paths in the arrayList expected
      for (int i = 0; i < expected.size(); i++)
      {
        temp1.add(expected.get(i).toString());
      }
      ArrayList<String> temp2 = new ArrayList<String>(); // stores the toString representations of
      // the paths in the arrayList returned
      for (int i = 0; i < returned.size(); i++)
      {
        temp2.add(returned.get(i).toString());
      }
      if (!temp1.containsAll(temp2))
      {
        System.out.println("Your findAllPaths method did not return the correct ArrayList.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected error occurred in testFindAllPathsRecursive.");
      return false; // return false if an unexpected error occurred
    }
    return true;
  }
  
  /**
   * Main method for the tester class
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) 
  {
    try (Scanner keyboard = new Scanner(System.in)) 
    {
      int startX, startY, endX, endY;
      String input = "Y";
      while (input.equalsIgnoreCase("Y"))
      {
        System.out.print("Enter starting X coordinate: ");
        startX = keyboard.nextInt();
        System.out.print("Enter starting Y coordinate: ");
        startY = keyboard.nextInt();
        System.out.print("Enter ending X coordinate: ");
        endX = keyboard.nextInt();
        System.out.print("Enter ending Y coordinate: ");
        endY = keyboard.nextInt();
        Intersection start = new Intersection(startX, startY);
        Intersection end = new Intersection(endX, endY);
        System.out.println("Number of paths from start to end: "
        + PathUtils.countPaths(start, end));
        System.out.println("List of possible paths:");
        for (Path p : PathUtils.findAllPaths(start, end))
        {
          System.out.println(p);
        }
        do 
        {
          System.out.print("Try another route? (Y/N): ");
          input = keyboard.next();
        } while (!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N"));
      }
    }
    catch (Exception e)
    {
    }
    Path tester = new Path();
    tester.addTail(new Intersection(0, 0)); // adding 4 Intersections to path 1
    tester.addTail(new Intersection(0, 1));
    tester.addTail(new Intersection(0, 2));
    tester.addTail(new Intersection(1, 2));
    System.out.println(tester.toString());
    
    System.out.println("testCountPathsNoPaths: " + testCountPathsNoPath());
    System.out.println("testCountPathsOnePath: " + testCountPathsOnePath());
    System.out.println("testCountPathsRecursive: " + testCountPathsRecursive());
    System.out.println("testFindAllPathsNoPath: " + testFindAllPathsNoPath());
    System.out.println("testFindAllPathsOnePath: " + testFindAllPathsOnePath());
    System.out.println("testFindAllPathsRecursive: " + testFindAllPathsRecursive());
  }
}
