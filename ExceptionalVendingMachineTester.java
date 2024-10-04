//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P04 Exceptional Vending Machine Tester
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
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class implements testers to check the correctness of the methods implemented in p04
 * Exceptional Vending Machine
 *
 */
public class ExceptionalVendingMachineTester 
{
  /**
   * Checks the correctness of the constructor of the class Item when passed invalid inputs
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testItemConstructorNotValidInput() 
  {
    try 
    {
      Item testDescription = new Item(null, 5); // testDescription is an Item with an invalid input 
      // for description but a valid expiration date
      Item testExpDate = new Item("Chips", -1); // testExpDate is an Item with a valid input for
      // description but an invalid expiration date
    }
    catch (IllegalArgumentException e)
    {
      return true; // if the IllegalArgumentExecption was thrown and caught correctly, return true
    }
    System.out.println("Your Item constructor did not throw an IllegalArgumentException when passed "
        + "invalid input");
    return false; // return false if the constructor did not throw the expected exception when
    // passed invalid input
  }

  /**
   * Checks the correctness of the constructor of the class Item, Item.getDescription(),
   * Item.getExpirationDate(), Item.setDescription(), and Item.toString() when passed valid inputs
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testItemConstructorGettersSetters() 
  {
    // Testing the constructor
    try 
    {
      Item tester = new Item("Chips", 5); // a tester Item that was passed valid input
    }
    catch (IllegalArgumentException e)
    {
      System.out.println("Your Item constructor threw an IllegalArgumentException when passed valid "
          + "input.");
      return false; // return false if an error was thrown
    }
    
    Item test1 = new Item("Chips", 5); // an item with description "Chips" and expiration date 5
    
    // Testing the getDescription method in the Item class
    if (!test1.getDescription().equals("Chips"))
    {
      System.out.println("Your getDescription method did not correctly return the item's "
          + "description.");
      return false; // return false if getDescription did not function properly
    }
    
    // Testing the getExpirationDate method in the Item class
    if (test1.getExpirationDate() != 5)
    {
      System.out.println("Your getExpirationDate method did not correctly return the item's "
          + "expiration date.");
      return false; // return false if getExpirationDate did not function properly
    }
    
    // Testing the setDescription method in the Item class
    test1.setDescription("Chocolate");
    if (!test1.getDescription().equals("Chocolate"))
    {
      System.out.println("Your setDescription method did not correctly set the item's description.");
      return false; // return false if setDescription did not function properly
    }
    
    // Testing the toString method in the Item class
    String temp = test1.toString(); // temp stores the current item's toString value
    if (!temp.equals("Chocolate: 5"))
    {
      System.out.println("Your toString method did not return the expected output.");
      return false; // return false if toString did not produce the expected output
    }
    return true; // return true if all tests pass.
  }

  /**
   * Checks the correctness of the Item.equals() method. You should consider at least the following
   * four scenarios. (1) Create an item with valid description and expiration date, comparing it to
   * itself should return true. (2) Two items having the same description but different expiration
   * dates should be equal. (3) Passing a null reference to the Item.equals() method should return
   * false. (4) An item MUST NOT be equal to an object NOT instance of the class Item, for instance
   * a string object.
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testItemEquals() 
  {
    // 1. Comparing an item to itself
    {
      Item test1 = new Item("Chips", 5);
      if (!test1.equals(test1))
      {
        System.out.println("Bug detected in testItemEquals scenario 1: Your equals method did not "
            + "return true when the item was compared to itself.");
        return false;
      }
    }
    
    // 2. Comparing two items with identical descriptions but different expiration dates
    {
      Item test2 = new Item("Chocolate", 5); // an item with description "Chocolate" and expiration
      // date 5
      Item test3 = new Item("Chocolate", 10); // a second item with description equal to test1's
      // description but a different expiration date
      if (!test2.equals(test3))
      {
        System.out.println("Bug detected in testItemEquals scenario 2: Your equals method did not "
            + "return true when two items with identical descriptions were compared.");
        return false; // return false if equals did not return the expected value.
      }
    }
    
    // 3. Passing a null reference to the equals method
    {
      Item test4 = new Item("Candy", 5); // an item with description "Candy" and expiration date 5
      Item test5 = null; // a null Item
      if (test4.equals(test5))
      {
        System.out.println("Bug detected in testItemEquals scenario 3: Your equals method did not "
            + "return false when it was passed a null reference.");
        return false;
      }
    }
    
    // 4. Testing an object not of type Item
    {
      String test6 = "testing 123";
      Item test7 = new Item("Candy", 5); // an item with description "Candy" and expiration date 5
      if (test7.equals(test6))
      {
        System.out.println("Bug detected in testItemEquals scenario 4: Your equals method did not "
            + "return false when it was passed an object of type String.");
        return false;
      }
    }
    return true; // return true if all tests pass
  }

  /**
   * Checks the correctness of the constructor of the ExceptionalVendingMachine when passed invalid
   * input
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testExceptionalVendingMachineConstructor()
  {
    try 
    {
      ExceptionalVendingMachine invalid1 = new ExceptionalVendingMachine(-1);
    }
    catch (IllegalArgumentException e)
    {
      return true; // return true if the correct exception was thrown
    }
    System.out.println("Your ExceptionalVendingMachine constructor did not throw an "
        + "IllegalArgumentException when passed invalid input.");
    return false; // return false if the exception was not thrown
  }

  /**
   * Checks the correctness of the following methods defined in the ExceptionalVendingMachine class
   * when an exception is expected to be thrown:
   * 
   * addItem(), containsItem(), getIndexNextItem(), getItemAtIndex(), getItemOccurrences(),
   * getItemOccurrencesByExpirationDate(), removeNextItem().
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testExceptionalVendingMachineAddContainsRemoveGetters() 
  {
    // Testing the addItem method
    {
      try 
      {
        ExceptionalVendingMachine test1 = new ExceptionalVendingMachine(5); // a test vending
        // machine with capacity 5
        test1.addItem(null, 4); // trying to add an item with a null description
        System.out.println("Bug detected: Your addItem method did not throw the expected exception"
            + " when passed an invalid description.");
        return false; // return false if an exception was not thrown
      }
      catch (IllegalArgumentException e)
      {}
      try
      {
        ExceptionalVendingMachine test2 = new ExceptionalVendingMachine(5); // a test vending
        // machine with capacity 5
        test2.addItem("Candy", -1); // trying to add an item with a negative expiration date
        System.out.println("Bug detected: Your addItem method did not throw the expected exception"
            + " when passed an invalid expiration date.");
        return false; // return false if an exception was not thrown
      }
      catch (IllegalArgumentException e)
      {}
      try
      {
        ExceptionalVendingMachine test22 = new ExceptionalVendingMachine(2); // a test vending
        // machine with capacity 2
        test22.addItem("Chips", 3);
        test22.addItem("Soda", 8);
        test22.addItem("Chocolate", 1);
        System.out.println("Bug detected: Your addItem method did not throw the expected exception"
            + " when the vending machine was full.");
        return false;
      }
      catch (IllegalStateException e)
      {}
    }
    
    // Testing the containsItem method
    {
      try
      {
        ExceptionalVendingMachine test3 = new ExceptionalVendingMachine(5); // a test vending
        // machine with capacity 5
        boolean temp = test3.containsItem(null);
        System.out.println("Bug detected: Your containsItem method did not throw the expected "
            + "exception when passed an invalid description.");
        return false; // return false if an exception was not thrown
      }
      catch (IllegalArgumentException e)
      {}
    }
    
    // Testing the getIndexNextItem method
    {
      try
      {
        ExceptionalVendingMachine test4 = new ExceptionalVendingMachine(5); // a test vending
        // machine with capacity 5
        int temp = test4.getIndexNextItem(null); // attempting to find the index of a null item
        System.out.println("Bug detected: Your getIndexNextItem method did not throw the expected "
            + "exception when passed an invalid description.");
        return false; // return false if an exception was not thrown
      }
      catch (IllegalArgumentException e)
      {}
      try
      {
        ExceptionalVendingMachine test5 = new ExceptionalVendingMachine(5); // a test vending
        // machine with capacity 5
        test5.addItem("Chocolate", 5);
        test5.addItem("Candy", 10);
        int temp = test5.getIndexNextItem("Chips"); // attempting to find the index of the next item
        // with the description "Chips"
        System.out.println("Bug detected: Your getIndexNextItem method did not throw the expected "
            + "exception when passed a description that does not exist in the vending machine.");
        return false; // return false if the expected exception was not thrown
      }
      catch (NoSuchElementException e)
      {}
    }
    
    // Testing the getItemAtIndex method
    {
      try
      {
        ExceptionalVendingMachine test6 = new ExceptionalVendingMachine(5); // a test vending
        // machine with capacity 5
        test6.addItem("Chocolate", 5);
        test6.addItem("Candy", 10);
        test6.addItem("Chips", 4);
        String temp = test6.getItemAtIndex(-1); // attempting to find the item at invalid index -1
        System.out.println("Bug detected: Your getItemAtIndex method did not throw the expected "
            + "exception when passed an invalid index.");
        return false; // return false if the expected exception was not thrown
      }
      catch (IndexOutOfBoundsException e)
      {}
    }
    
    // Testing the getItemOccurrences method
    {
      try
      {
        ExceptionalVendingMachine test7 = new ExceptionalVendingMachine(5); // a test vending
        // machine with capacity 5
        test7.addItem("Chocolate", 5);
        test7.addItem("Candy", 7);
        test7.addItem("Chocolate", 3);
        test7.addItem("Chips", 9);
        int temp = test7.getItemOccurrences(null); // attempting to get the item occurrences of a
        // null description
        System.out.println("Bug detected: Your getItemOccurrences method did not throw the expected"
            + " exception when passed an invalid description.");
        return false; // return false if the expected exception was not thrown
      }
      catch (IllegalArgumentException e)
      {}
    }
    
    // Testing the getItemOccurrencesByExpirationDate method
    {
      try
      {
        ExceptionalVendingMachine test8 = new ExceptionalVendingMachine(5); // a test vending
        // machine with capacity 5
        test8.addItem("Chocolate", 5);
        test8.addItem("Candy", 7);
        test8.addItem("Chocolate", 3);
        test8.addItem("Chips", 9);
        int temp = test8.getItemOccurrencesByExpirationDate(null, 5);
        System.out.println("Bug detected: Your getItemOccurrencesByExpirationDate method did not "
            + "throw the expected exception when passed an invalid description.");
        return false; // return false if the expected exception was not thrown
      }
      catch (IllegalArgumentException e)
      {}
      try
      {
        ExceptionalVendingMachine test8 = new ExceptionalVendingMachine(5); // a test vending
        // machine with capacity 5
        test8.addItem("Chocolate", 5);
        test8.addItem("Candy", 7);
        test8.addItem("Chocolate", 3);
        test8.addItem("Chips", 9);
        int temp = test8.getItemOccurrencesByExpirationDate("Candy", -1);
        System.out.println("Bug detected: Your getItemOccurrencesByExpirationDate method did not "
            + "throw the expected exception when passed an invalid expiration date.");
        return false; // return false if the expected exception was not thrown
      }
      catch (IllegalArgumentException e)
      {}
    }
    
    // Testing the removeNextItem method
    {
      try
      {
        ExceptionalVendingMachine test9 = new ExceptionalVendingMachine(5); // a test vending
        // machine with capacity 5
        test9.addItem("Chocolate", 5);
        test9.addItem("Candy", 7);
        test9.addItem("Chocolate", 3);
        test9.addItem("Chips", 9);
        test9.removeNextItem(null); // try to remove an item with a null description
        System.out.println("Bug detected: Your removeNextItem method did not throw the expected "
            + "exception when passed a null description.");
        return false; // return false if the expected exception was not thrown
      }
      catch (IllegalArgumentException e)
      {}
      try
      {
        ExceptionalVendingMachine test9 = new ExceptionalVendingMachine(5); // a test vending
        // machine with capacity 5
        test9.addItem("Chocolate", 5);
        test9.addItem("Candy", 7);
        test9.addItem("Chocolate", 3);
        test9.addItem("Chips", 9);
        test9.removeNextItem("Soda"); // try to remove an item that does not exist in the vending
        // machine
        System.out.println("Bug detected: Your removeNextItem method did not throw the expected "
            + "exception when passed a description of an item that does not exist in the machine.");
        return false; // return false if the expected exception was not thrown
      }
      catch (NoSuchElementException e)
      {}
    }
    return true; // return true if all tests pass
  }

  /**
   * Checks the correctness of isEmpty(), size(), and isFull() methods defined in the
   * ExceptionalVendingMachine class
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testEmptySizeFullExceptionalVendingMachine() 
  {
    // Testing the isEmpty method
    {
      ExceptionalVendingMachine one = new ExceptionalVendingMachine(5); // a test vending
      // machine with capacity 5
      if (one.isEmpty() != true)
      {
        System.out.println("Bug detected: Your isEmpty method did not return the expected value "
            + "when the vending machine was empty.");
        return false;
      }
      one.addItem("Chips", 6); // add one item to the vending machine so it is no longer empty
      if (one.isEmpty() != false)
      {
        System.out.println("Bug detected: Your isEmpty method did not return the expected value "
            + "when the vendimg machine was not empty.");
        return false;
      }
    }
    
    // Testing the size method
    {
      ExceptionalVendingMachine two = new ExceptionalVendingMachine(4); // a test vending
      // machine with capacity 4
      two.addItem("Candy", 7);
      two.addItem("Soda", 3);
      if (two.size() != 2)
      {
        System.out.println("Bug detected: Your size method did not return the expected value when "
            + "the vending machine contained 2 items.");
        return false;
      }
    }
    
    // Testing the isFull method
    {
      ExceptionalVendingMachine three = new ExceptionalVendingMachine(3); // a test vending
      // machine with capacity 3
      if (three.isFull() != false)
      {
        System.out.println("Bug detected: Your isFull method did not return the expected value when"
            + " the vending machine was not full.");
        return false;
      }
      three.addItem("Chocolate", 5);
      three.addItem("Candy", 7);
      three.addItem("Soda", 3);
      if (three.isFull() != true)
      {
        System.out.println("Bug detected: Your isFull method did not return the expected value when"
            + " the vending machine was full.");
        return false;
      }
    }
    return true; // return true if all tests pass
  }

  /**
   * Checks the correctness of loadOneItem method with respect to its specification. Consider at
   * least the four following scenarios. (1) Successful scenario for loading one item with a valid
   * string representation to a non-full vending machine. (2) Unsuccessful scenario for passing null
   * or a blank string (for instance one space or empty string) to the loadOneItem() method call, an
   * IllegalArgumentEXception is expected to be thrown. (3) Unsuccessful scenario for passing a
   * badly formatted string to the loadOneItem method. A DataFormatException is expected to be
   * thrown. (4) Unsuccessful scenario for trying to load an item with a valid representation to a
   * full vending machine. An IllegalStateException is expected to be thrown.
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testLoadOneItem() 
  {
    // 1. Test a successful scenario for loading one item with a valid string representation to a 
    // non-full vending machine
    {
      try
      {
        ExceptionalVendingMachine test1 = new ExceptionalVendingMachine(3); // a test vending
        // machine with capacity 3
        test1.addItem("Chocolate", 5);
        test1.loadOneItem("Candy: 8");
        ExceptionalVendingMachine test2 = new ExceptionalVendingMachine(3);
        test2.addItem("Chocolate", 5);
        test2.addItem("Candy", 8);
        for (int i = 0; i < 2; i++)
        {
          if (!test1.getItemAtIndex(i).equals(test2.getItemAtIndex(i)))
          {
            System.out.println("Bug detected: testLoadOneItem scenario 1: your loadOneItem method "
                + "did not correctly add the new valid item to the vending machine.");
            return false; // return false if the item was not correctly added to the vending machine
          }
        }
      }
      catch (DataFormatException e)
      {
        System.out.println("Bug detected: testLoadOneItem scenario 1: an unexpected exception was "
            + "thrown.");
        return false; // return false if an exception was thrown
      }
    }
    
    // 2. Test an unsuccessful scenario for passing null or a blank string to the method call
    {
      try
      {
        ExceptionalVendingMachine test3 = new ExceptionalVendingMachine(3); // a test vending
        // machine with capacity 3
        test3.loadOneItem("");
        System.out.println("Bug detected: testLoadOneItem scenario 2: the expected exception was "
            + "not thrown.");
        return false; // return false if the expected exception was not thrown
      }
      catch (IllegalArgumentException e)
      {}
      catch (DataFormatException e)
      {
        System.out.println("Bug detected: testLoadOneItem scenario 2: an unexpected exception was "
            + "thrown.");
        return false; // return false if an exception was thrown
      }
    }
    
    // 3. Test an unsuccessful scenario for passing a badly formatted string to the method
    {
      try
      {
        ExceptionalVendingMachine test4 = new ExceptionalVendingMachine(3); // a test vending
        // machine with capacity 3
        test4.loadOneItem("Candy Jan. 5");
        System.out.println("Bug detected: testLoadOneItem scenario 3: the expected exception was "
            + "not thrown.");
        return false; // return false if the expected exception was not thrown
      }
      catch (DataFormatException e)
      {}
      catch (IllegalArgumentException e)
      {
        System.out.println("Bug detected: testLoadOneItem scenario 3: an unexpected exception was "
            + "thrown.");
        return false; // return false if an exception was thrown
      }
    }
    
    // 4. Test an unsuccessful scenario for trying to load an item with a valid representation to a
    // full vending machine
    {
      try
      {
        ExceptionalVendingMachine test5 = new ExceptionalVendingMachine(2); // a test vending
        // machine with capacity 2
        test5.addItem("Soda", 7);
        test5.addItem("Chocolate", 3);
        test5.loadOneItem("Candy: Jan. 5");
        System.out.println("Bug detected: testLoadOneItem scenario 4: the expected exception was "
            + "not thrown.");
        return false; // return false if the expected exception was not thrown
      }
      catch (IllegalStateException e)
      {}
      catch (DataFormatException e)
      {
        System.out.println("Bug detected: testLoadOneItem scenario 4: an unexpected exception was "
            + "thrown.");
        return false; // return false if an exception was thrown
      }
      catch (IllegalArgumentException e)
      {
        System.out.println("Bug detected: testLoadOneItem scenario 4: an unexpected exception was "
            + "thrown.");
        return false; // return false if an exception was thrown
      }
    }
    return true; // return true if all tests pass
  }
  
  /**
   * This method tests the effectiveness of the loadItems method in the ExceptionalVendingMachine
   * class
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testLoadItems()
  {
    try
    {
      File test = new File("items.txt");
      ExceptionalVendingMachine test5 = new ExceptionalVendingMachine(6); // a test vending
      // machine with capacity 6
      test5.loadItems(test); // counter shows how many items have been added to the
      // vending machine after the call to loadItems
      ExceptionalVendingMachine test6 = new ExceptionalVendingMachine(6); // a test vending
      // machine with capacity 6
      test6.addItem("Snack", 50);
      test6.addItem("Soda", 35);
      test6.addItem("Chocolate", 20);
      test6.addItem("Water", 15);
      test6.addItem("Soda", 5);
      test6.addItem("Orange Juice", 25);
      for (int i = 0; i < test5.size(); i++)
      {
        if (!(test5.getItemAtIndex(i).equals(test6.getItemAtIndex(i))))
        {
          System.out.println("Bug detected: testLoadItems your loadOneItem method did not correctly"
              + " add the item from the file into the vending machine.");
          return false; // return false if the items were not correctly added to the vending machine
        }
      }
      }
    catch (FileNotFoundException e)
    {
      System.out.println("Your loadItems method did not find the file imported.");
      return false;
    }
    return true; // return true if all tests pass
  }

  /**
   * Invokes all the public tester methods implemented in this class
   * 
   * @return true if all testers pass with no errors, and false if any of the tester fails.
   */
  public static boolean runAllTests() 
  {
    if (!ExceptionalVendingMachineTester.testItemConstructorNotValidInput())
    { return false; }
    if (!ExceptionalVendingMachineTester.testItemConstructorGettersSetters())
    { return false; }
    if (!ExceptionalVendingMachineTester.testItemEquals())
    { return false; }
    if (!ExceptionalVendingMachineTester.testExceptionalVendingMachineConstructor())
    { return false; }
    if (!ExceptionalVendingMachineTester.testExceptionalVendingMachineAddContainsRemoveGetters())
    { return false; }
    if (!ExceptionalVendingMachineTester.testEmptySizeFullExceptionalVendingMachine())
    { return false; }
    if (!ExceptionalVendingMachineTester.testLoadOneItem())
    { return false; }
    return true; // return true if all tester methods passed
  }

  /**
   * Main method for the tester class
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) 
  {
    System.out.println("testItemConstructorNotValidInput: " 
    + ExceptionalVendingMachineTester.testItemConstructorNotValidInput());
    System.out.println("testItemConstructorGettersSetters: " 
        + ExceptionalVendingMachineTester.testItemConstructorGettersSetters());
    System.out.println("testItemEquals: " + ExceptionalVendingMachineTester.testItemEquals());
    System.out.println("testExceptionalVendingMachineConstructor: " 
    + ExceptionalVendingMachineTester.testExceptionalVendingMachineConstructor());
    System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters: " 
        + ExceptionalVendingMachineTester.testExceptionalVendingMachineAddContainsRemoveGetters());
    System.out.println("testEmptySizeFullExceptionalVendingMachine: " 
        + ExceptionalVendingMachineTester.testEmptySizeFullExceptionalVendingMachine());
    System.out.println("testLoadOneItem: " + ExceptionalVendingMachineTester.testLoadOneItem());
    System.out.println("testLoadItems: " + ExceptionalVendingMachineTester.testLoadItems());
    System.out.println("runAllTests: " + runAllTests());
  }
}