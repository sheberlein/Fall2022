//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P10 Course Registration - CourseRegTester class
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
import java.util.NoSuchElementException;

/**
 * This class implements unit test methods to check the correctness of Course,  CourseIterator, 
 * CourseQueue and CourseReg classes in P10.
 * 
 * Be aware that all methods in this class will be run against not only your code, but also our own
 * working and broken implementations to verify that your tests are working appropriately!
 */
public class CourseRegTester 
{
  
  /**
   * START HERE, and continue with testCompareTo() after this.
   * 
   * This method must test the Course constructor, accessor, and mutator methods, as well as its
   * toString() implementation. The compareTo() method will get its own test.
   * 
   * @see Course
   * @return true if the Course implementation is correct; false otherwise
   */
  public static boolean testCourse() 
  {
    try
    {
      // test the constructor
      // scenario 1: check if errors are correctly thrown.
      boolean caught = false; // this should be set to true in the catch block
      try
      {
        Course c = new Course(null, 5, 5, 5); // invalid department name
      }
      catch (IllegalArgumentException e)
      {
        caught = true;
      }
      if (caught == false)
      {
        System.out.println("testCourse: scenario 1: Your course constructor did not throw the "
            + "correct exception when the given department name was blank.");
        return false;
      }
      caught = false; // this should be set to true in the catch block
      try
      {
        Course c = new Course("Calculus", 0, 5, 5); // invalid course number
      }
      catch (IllegalArgumentException e)
      {
        caught = true;
      }
      if (caught == false)
      {
        System.out.println("testCourse: scenario 1: Your course constructor did not throw the "
            + "correct exception when the given course number was invalid.");
        return false;
      }
      caught = false; // this should be set to true in the catch block
      try
      {
        Course c = new Course("Calculus", 1, 6, 5); // invalid number of credits
      }
      catch (IllegalArgumentException e)
      {
        caught = true;
      }
      if (caught == false)
      {
        System.out.println("testCourse: scenario 1: Your course constructor did not throw the "
            + "correct exception when the given number of credits was invalid.");
        return false;
      }
      caught = false; // this should be set to true in the catch block
      try
      {
        Course c = new Course("Calculus", 1, 3, -1); // invalid number of available seats
      }
      catch (IllegalArgumentException e)
      {
        caught = true;
      }
      if (caught == false)
      {
        System.out.println("testCourse: scenario 1: Your course constructor did not throw the "
            + "correct exception when the given number of seats available was invalid.");
        return false;
      }
      
      // scenario 2: test the accessor methods
      // testing getNumCredits
      Course c = new Course("Calculus", 234, 5, 10); // a new course with all valid paramaters
      if (c.getNumCredits() != 5)
      {
        System.out.println("testCourse: scenario 2: Your course getNumCredits method did not "
            + "return the correct number of credits.");
        return false;
      }
      
      // testing equals
      Course c2 = new Course("Calculus", 234, 5, 10); // a new course with all valid paramaters
      if (!c.equals(c2))
      {
        System.out.println("testCourse: scenario 2: Your course equals method did not return true "
            + "when the courses were equal.");
        return false;
      }
      Course c3 = new Course("Anthropology", 104, 4, 11); // a new course with all valid paramaters
      if (c.equals(c3))
      {
        System.out.println("testCourse: scenario 2: Your course equals method did not return false "
            + "when the courses were not equal.");
        return false;
      }
      
      // testing toString
      String expected = "Calculus 234 (10 seats)"; // the expected toString output of Course c
      if (!expected.trim().equals(c.toString().trim()))
      {
        System.out.println("testCourse: scenario 2: Your course toString method did not return the"
            + " correct string.");
        return false;
      }
      
      // test setProfessor
      boolean tester = false; // should NOT be set to true in a catch block
      try
      {
        c.setProfessor(null, -3); // if an error is thrown, it will be caught in the catch block.
      }
      catch (Exception e)
      {
        tester = true;
      }
      if (tester == true)
      {
        System.out.println("testCourse: your setProfessor method threw an exception when given "
            + "invalid inputs.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected exception was thrown in testCourse.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /**
   * This method must test the Course compareTo() implementation. Be sure to test ALL FOUR levels
   * of the comparison here!
   * 
   * Once you complete this test, finish the Course implementation if you have not done so already,
   * then move to testCourseQueue() and testEnqueueDequeue().
   * 
   * @see Course#compareTo(Course)
   * @return true if the compareTo() implementation is correct; false otherwise
   */
  public static boolean testCompareTo() 
  {
    try
    {
      // scenario 1: if the courses are equal, it should return 0
      Course c = new Course("CS", 234, 5, 10); // a new course with all valid paramaters
      Course c2 = new Course("CS", 234, 5, 10); // a new course equal to c
      if (c.compareTo(c2) != 0)
      {
        System.out.println("testCompareTo: scenario 1: your compareTo method did not return 0 when "
            + "the courses were equal.");
        return false;
      }
      
      // scenario 2: if the departments are equal, but one has no seats available and one has seats
      Course c3 = new Course("CS", 104, 4, 0); // a new course with 0 seats left
      // compareTo should return a positive number since course c will have higher priority than c3
      if (c.compareTo(c3) <= 0)
      {
        System.out.println("testCompareTo: scenario 2: your compareTo method did not return a "
            + "positive number when the second paramater was lower priority than the first.");
        return false;
      }
      
      // scenario 3: departments are equal, they both have seats left, but one has a known professor
      // and the other does not
      Course c4 = new Course("CS", 225, 4, 10);
      c4.setProfessor("Tejedo", 4.6);
      // compareTo should return a negative number since course c will have lower priority than c4
      if (c.compareTo(c4) >= 0)
      {
        System.out.println("testCompareTo: scenario 3: your compareTo method did not return a "
            + "negative number when the first paramater was lower priority than the second.");
        return false;
      }
      
      // scenario 4: departments are equal, they both have seats left, they both have known 
      // professors, but one has a higher professor rating than the other
      Course c5 = new Course("CS", 300, 3, 6);
      c.setProfessor("Poltoratski", 3.5);
      c5.setProfessor("Kacem", 4.0);
      // compareTo should return a negative number since course c will have lower priority than c5
      if (c.compareTo(c5) >= 0)
      {
        System.out.println("testCompareTo: scenario 4: your compareTo method did not return a "
            + "negative number when the first paramater was lower priority than the second.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected exception was thrown in testCompareTo.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /**
   * This method must test the other methods in CourseQueue (isEmpty, size, peek). Verify normal 
   * cases and error cases, as well as a filled and re-emptied queue.
   * 
   * Once you have completed this method, implement the required methods in CourseQueue and verify
   * that they work correctly.
   * 
   * @see CourseQueue
   * @return true if CourseQueue's other methods are implemented correctly; false otherwise
   */
  public static boolean testCourseQueue() 
  {
    try
    {
      // testing the constructor
      boolean test = false; // this should be set to true inside the catch block
      try
      {
        CourseQueue q = new CourseQueue(-1);
      }
      catch (IllegalArgumentException e)
      {
        test = true;
      }
      if (test == false)
      {
        System.out.println("testCourseQueue: your constructor did not correctly throw an exception "
            + "when passed a negative number.");
        return false;
      }
      
      // testing isEmpty
      CourseQueue q = new CourseQueue(5);
      if (!q.isEmpty())
      {
        System.out.println("testCourseQueue: your isEmpty method did not return true when the queue"
            + " was empty.");
        return false;
      }
      
      // testing size
      // test an empty queue
      if (q.size() != 0)
      {
        System.out.println("testCourseQueue: your size method did not return 0 when the queue was "
            + "empty.");
        return false;
      }
      // test peek
      // test an empty queue
      test = false; // should be set to true in the catch block
      try
      {
        q.peek();
      }
      catch (NoSuchElementException e)
      {
        test = true;
      }
      if (test == false)
      {
        System.out.println("testCourseQueue: your peek method did not correctly throw a "
            + "NoSuchElementException when the queue was empty.");
        return false;
      }
      
      // test a queue with one item
      Course c = new Course("CS", 300, 2, 0);
      c.setProfessor("testc", 1.0);
      q.enqueue(c);
      if (!q.peek().equals(c))
      {
        System.out.println("testCourseQueue: your peek method did not return the correct object "
            + "when there was only one item in the queue.");
        return false;
      }
      if (q.isEmpty())
      {
        System.out.println("testCourseQueue: your isEmpty method did not return false when the "
            + "queue was not empty.");
        return false;
      }
      if (q.size() != 1)
      {
        System.out.println("testCourseQueue: your size method did not return 1 when there was one "
            + "element in the queue.");
        return false;
      }
      
      // test a queue with multiple items
      Course c1 = new Course("CS", 400, 5, 10); // higher priority than c
      c1.setProfessor("test1", 3.0);
      q.enqueue(c1);
      Course c2 = new Course("CS", 400, 5, 11); // higher priority than c1 and c
      c2.setProfessor("test", 3.3);
      Course c3 = new Course("CS", 300, 5, 0); // lower priority than c1 and c2
      q.enqueue(c2);
      q.enqueue(c3);
      if (!q.peek().equals(c2))
      {
        System.out.println("testCourseQueue: your peek method did not return the correct object "
            + "when there were multiple items in the queue.");
        return false;
      }
      if (q.isEmpty())
      {
        System.out.println("testCourseQueue: your isEmpty method did not return false when the "
            + "queue was not empty.");
        return false;
      }
      if (q.size() != 4)
      {
        System.out.println("testCourseQueue: your size method did not return 4 when there were four"
            + " elements in the queue.");
        return false;
      }
      
      // test size and isEmpty after dequeue
      q.dequeue();
      if (q.isEmpty())
      {
        System.out.println("testCourseQueue: your isEmpty method did not return false when the "
            + "queue was not empty after a dequeue.");
        return false;
      }
      if (q.size() != 3)
      {
        System.out.println("testCourseQueue: your size method did not return 3 when there were"
            + " three elements in the queue after a dequeue.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected exception was thrown in testCourseQueue.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /**
   * This method must test the enqueue and dequeue methods in CourseQueue. Verify normal cases and
   * error cases, as well as filling and emptying the queue.
   * 
   * You may also test the percolate methods directly, though this is not required.
   * 
   * Once you have completed this method, implement the enqueue/dequeue and percolate methods in
   * CourseQueue and verify that they work correctly, then move on to testCourseIterator().
   * 
   * @see CourseQueue#enqueue(Course)
   * @see CourseQueue#dequeue()
   * @return true if the CourseQueue enqueue/dequeue implementations are correct; false otherwise
   */
  public static boolean testEnqueueDequeue() 
  {
    try
    {
      // testing enqueue
      boolean test = false; // should be set to true in the catch block
      CourseQueue q = new CourseQueue(3);
      // giving it a null course
      try
      {
        q.enqueue(null);
      }
      catch (NullPointerException e)
      {
        test = true;
      }
      if (test == false)
      {
        System.out.println("testEnqueueDequeue: your enqueue method did not throw a "
            + "NullPointerException when passed a null parameter.");
        return false;
      }
      // the CourseQueue is full
      CourseQueue q1 = new CourseQueue(1);
      q1.enqueue(new Course("CS", 300, 4, 5));
      test = false; // should be set to true in the catch block
      try
      {
        q1.enqueue(new Course("CS2", 400, 3, 4));
      }
      catch (IllegalStateException e)
      {
        test = true;
      }
      if (test == false)
      {
        System.out.println("testEnqueueDequeue: your enqueue method did not throw an "
            + "IllegalStateException when the CourseQueue was full.");
        return false;
      }
      
      // the CourseQueue is not full
      Course c = new Course("CS", 300, 3, 0);
      q.enqueue(c);
      if (q.size() != 1)
      {
        System.out.println("testEnqueueDeQueue: (1) your enqueue method did not correctly update "
            + "the size.");
        return false;
      }
      if (!q.peek().equals(c))
      {
        System.out.println("testEnqueueDequeue: your enqueue method did not correctly add the "
            + "item to the queue.");
        return false;
      }
      // adding another of higher priority
      Course c1 = new Course("CS", 300, 3, 7);
      q.enqueue(c1);
      if (q.size() != 2)
      {
        System.out.println("testEnqueueDeQueue: (2) your enqueue method did not correctly update "
            + "the size.");
        return false;
      }
      if (!q.peek().equals(c1))
      {
        System.out.println("testEnqueueDequeue: your enqueue method did not correctly add the "
            + "item to the queue.");
        return false;
      }
      
      // testing dequeue
      // the CourseQueue is empty
      test = false; // should be set to true in the catch block
      CourseQueue q3 = new CourseQueue(3);
      try
      {
        q3.dequeue();
      }
      catch (NoSuchElementException e)
      {
        test = true;
      }
      if (test == false)
      {
        System.out.println("testEnqueueDequeue: your dequeue method did not throw a "
            + "NoSuchElementException when the queue was empty.");
        return false;
      }
      
      // CourseQueue is not empty
      q.dequeue(); // now q should only have c in it
      if (!q.peek().equals(c))
      {
        System.out.println("testEnqueueDequeue: your dequeue method did not correctly remove the "
            + "item from the queue.");
        return false;
      }
      if (q.size() != 1)
      {
        System.out.println("testEnqueueDeQueue: your dequeue method did not correctly update the "
            + "size.");
        return false;
      }
      
      // testing an implementation of CourseQueue whose percolateDown() method erroneously swaps a 
      // parent with its smaller child.
      Course course1 = new Course("Number 1", 100, 4, 10);
      course1.setProfessor("one", 4.9);
      Course course2 = new Course("Number 2", 200, 4, 11);
      course2.setProfessor("two", 4.8);
      Course course3 = new Course("Number 3", 300, 4, 5);
      course3.setProfessor("three", 3.0);
      Course course4 = new Course("Number 4", 300, 4, 0);
      CourseQueue queue1 = new CourseQueue(4);
      queue1.enqueue(course1);
      queue1.enqueue(course2);
      queue1.enqueue(course3);
      queue1.enqueue(course4);
      if (!queue1.dequeue().equals(course1))
      {
        System.out.println("testEnqueueDequeue: your dequeue method did not return the correct "
            + "course.");
        return false;
      }
      if (!queue1.peek().equals(course2))
      {
        System.out.println("testEnqueueDequeue: your peek method did not return the correct "
            + "course.");
        return false;
      }
      String string = "Number 2 200 (11 seats) with two (4.8)\nNumber 3 300 (5 seats) "
          + "with three (3.0)\nNumber 4 300 (closed)";
      if (!string.trim().equals(queue1.toString().trim()))
      {
        System.out.println("testEnqueueDequeue: your queue did not return the correct toString.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected exception was thrown in testEnqueueDequeue.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /**
   * This method must test the CourseIterator class. The CourseIterator iterates through a deep copy
   * of a CourseQueue in decreasing order of priority, returning each Course object in turn.
   * 
   * Once you have completed this method, implement the CourseIterator class and make CourseQueue
   * into an Iterable class. Verify that this works correctly, and then move on to the final test
   * method: testCourseReg().
   * 
   * @see CourseIterator
   * @return true if the CourseIterator implementation is correct; false otherwise
   */
  public static boolean testCourseIterator() 
  {
    try
    {
      // testing that the correct error is thrown in next
      boolean test = false; // this should be set to true in the catch blocok
      try
      {
        CourseQueue q = new CourseQueue(2); // a new CourseQueue to iterate over
        Iterator<Course> iterate = q.iterator(); // a new iterator
        iterate.next();
      }
      catch (NoSuchElementException e)
      {
        test = true;
      }
      if (test == false)
      {
        System.out.println("testCourseIterator: your next method did not throw the correct "
            + "exception when there was no next element.");
        return false;
      }
      
      CourseQueue q = new CourseQueue(3); // a new CourseQueue to iterate over
      Course c = new Course("CS", 300, 3, 0); // the lowest priority course
      Course c1 = new Course("CS", 300, 3, 7); // the highest priority course
      c1.setProfessor("mouna", 4.8);
      Course c2 = new Course("CS", 400, 4, 10); // the middle priority course
      q.enqueue(c);
      q.enqueue(c1);
      q.enqueue(c2);
      Iterator<Course> iterate = q.iterator(); // a new iterator
      if (!iterate.hasNext())
      {
        System.out.println("testCourseIterator: your hasNext method did not return true when "
            + "there was a next element.");
        return false;
      }
      if (!iterate.next().equals(c1))
      {
        System.out.println("testCourseIterator: (1) your next method did not return the correct "
            + "course.");
        return false;
      }
      if (!iterate.next().equals(c2))
      {
        System.out.println("testCourseIterator: (2) your next method did not return the correct "
            + "course.");
        return false;
      }
      if (!iterate.next().equals(c))
      {
        System.out.println("testCourseIterator: (3) your next method did not return the correct "
            + "course.");
        return false;
      }
      if (iterate.hasNext())
      {
        System.out.println("testCourseIterator: your hasNext method did not return false when "
            + "there was no next element.");
        return false;
      }

    }
    catch (Exception e)
    {
      System.out.println("An unexpected exception was thrown in testCourseIterator.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /**
   * This method must test the constructor and three methods of the CourseReg class (setCreditLoad,
   * add, and getRecommendedCourses). Verify normal cases and error cases.
   * 
   * Once you have completed this method, implement CourseReg and verify that it works correctly.
   * Then you're DONE! Save and submit to gradescope, and enjoy being DONE with programming
   * assignments in CS 300 !!!
   * 
   * @see CourseReg
   * @return true if CourseReg has been implemented correctly; false otherwise
   */
  public static boolean testCourseReg() 
  {
    try
    {
      // testing the constructor
      // testing that the correct errors are thrown
      boolean test = false; // should be set to true inside the catch block
      try
      {
        CourseReg c = new CourseReg(-1, 4);
      }
      catch (IllegalArgumentException e)
      {
        test = true;
      }
      if (test == false)
      {
        System.out.println("testCourseReg: your constructor did not throw an "
            + "IllegalArgumentException when passed a negative capacity.");
        return false;
      }
      test = false; // should be set to true inside the catch block
      try
      {
        CourseReg c = new CourseReg(1, -4);
      }
      catch (IllegalArgumentException e)
      {
        test = true;
      }
      if (test == false)
      {
        System.out.println("testCourseReg: your constructor did not throw an "
            + "IllegalArgumentException when passed a negative credit load.");
        return false;
      }
      
      CourseReg reg = new CourseReg(3, 8); // a new CourseReg with capacity 3 and credit load 8
      
      // testing setCreditLoad
      // make sure it throws an exception if given a negative integer
      test = false;
      try
      {
        reg.setCreditLoad(-2);
      }
      catch (IllegalArgumentException e)
      {
        test = true;
      }
      if (test == false)
      {
        System.out.println("testCourseReg: your setCreditLoad method did not throw an "
            + "IllegalArgumentException when passed a negative integer.");
        return false;
      }
      
      // testing add
      // first, test adding when the CourseReg is not full
      Course c = new Course("CS", 300, 3, 0); // the lowest priority course
      c.setProfessor("lowPriority", 1.1);
      if (reg.add(c) != true)
      {
        System.out.println("testCourseReg: your add method did not return true when the "
            + "CourseReg was not full.");
        return false;
      }
      
      // then, test adding when the CourseReg is full
      Course c1 = new Course("CS", 300, 3, 7); // the highest priority course
      c1.setProfessor("mouna", 4.8);
      Course c2 = new Course("CS", 400, 4, 10); // the middle priority course
      c2.setProfessor("midPriority", 2.0);
      if (reg.add(c1) != true)
      {
        System.out.println("testCourseReg: your add method did not return true when the "
            + "CourseReg was not full.");
        return false;
      }
      if (reg.add(c2) != true)
      {
        System.out.println("testCourseReg: your add method did not return true when the "
            + "CourseReg was not full.");
        return false;
      }
      Course c3 = new Course("Calculus", 234, 4, 11); // this course should not be added
      if (reg.add(c3) != false)
      {
        System.out.println("testCourseReg: your add method did not return false when the "
            + "CourseReg was full.");
        return false;
      }
      
      // testing getRecommendedCourses
      String real = reg.getRecommendedCourses();
      String expected = c1.toString() + "\n" + c2.toString();
      if (!real.trim().equals(expected.trim()))
      {
        System.out.println("testCourseReg: your getRecommendedCourses method did not return the "
            + "correct string.");
        return false;
      }
      if (real.contains(c.toString().trim()))
      {
        return false;
      }
      // add a low credit, low priority course, then test that it didn't get added to the 
      // expected string
      reg.add(new Course("Calculus", 222, 1, 0));
      if (!real.trim().equals(expected.trim()))
      {
        System.out.println("testCourseReg: your getRecommendedCourses method did not return the "
            + "correct string.");
        return false;
      }    
      
      // testing when all the courses should be added
      CourseReg courses = new CourseReg(3, 15);
      courses.add(c1);
      courses.add(c2);
      courses.add(c);
      String r = courses.getRecommendedCourses();
      String e = c1.toString() + "\n" + c2.toString() + "\n" + c.toString();
      if (!r.trim().equals(e.trim()))
      {
        System.out.println("testCourseReg: your getRecommendedCourses method did not return the "
            + "correct string.");
        return false;
      }
      
      // testing when the highest priority courses have a larger credit load.
      CourseReg tester = new CourseReg(3, 3);
      Course one = new Course("CS", 111, 5, 11); // the highest priority course
      one.setProfessor("prof1", 4.9);
      Course two = new Course("CS", 222, 5, 10); // the middle priority course
      Course three = new Course("Not CS", 333, 3, 0); // the lowest priority course
      tester.add(one);
      tester.add(two);
      tester.add(three);
      String re = tester.getRecommendedCourses();
      String ex = "";
      if (!re.trim().equals(ex.trim()))
      {
        System.out.println("testCourseReg: your getRecommendedCourses method did not return a "
            + "blank string.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected exception was thrown in testCourseReg.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /**
   * This method calls all test methods defined by us; you may add additional methods to this if
   * you like. All test methods must be public static boolean.
   * 
   * @return true if all tests in this class return true; false otherwise
   */
  public static boolean runAllTests() 
  {
    boolean testVal = true;
    // test Course
    System.out.print("testCourse(): ");
    if (!testCourse()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    
    // test compareTo
    System.out.print("testCompareTo(): ");
    if (!testCompareTo()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    
    // test CourseIterator
    System.out.print("testCourseIterator(): ");
    if (!testCourseIterator()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    
    // test CourseQueue enqueue/dequeue
    System.out.print("testEnqueueDequeue(): ");
    if (!testEnqueueDequeue()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    
    // test CourseQueue
    System.out.print("testCourseQueue(): ");
    if (!testCourseQueue()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    
    // test CourseReg
    System.out.print("testCourseReg(): ");
    if (!testCourseReg()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    return testVal;
  }
  
  /**
   * Calls runAllTests() so you can verify your program
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) 
  {
    runAllTests();
  }
}
