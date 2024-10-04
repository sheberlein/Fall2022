//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P04 Exceptional Vending Machine
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
// Online Sources:  https://www.digitalocean.com/community/tutorials/java-read-file-line-by-line
//                  This site helped to explain how to read a file line by line using a PrintWriter
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * This class models a vending machine. When requested, the item with the soonest expiration date
 * will be dispensed first.
 */
public class ExceptionalVendingMachine 
{
  private Item[] items; // array storing the items available within this vending machine
  private int size; // number of items available in this vending machine

  /**
   * Creates a new vending machine with a given capacity
   * 
   * @param capacity maximum number of items that can be held by this vending machine
   * @throws IllegalArgumentException with a descriptive error message if capacity is zero or
   *                                  negative
   */
  public ExceptionalVendingMachine(int capacity) 
  {
    if (capacity <= 0)
    {
      throw new IllegalArgumentException("Error: the given capacity cannot be zero or negative.");
    }
    items = new Item[capacity];
    size = 0; // optional since 0 is the default value for primitive type int
  }

  /**
   * Checks whether this vending machine is empty
   * 
   * @return true if this vending machine is empty, false otherwise
   */
  public boolean isEmpty() 
  {
    if (size == 0)
    {
      return true; // return true if the vending machine is empty (size = 0)
    }
    return false; // return false if the vending machine is not empty (size > 0)
  }

  /**
   * Checks whether this vending machine is full
   * 
   * @return true if this vending machine is full, false otherwise
   */
  public boolean isFull() 
  {
    if (size == items.length)
    {
      return true; // return true if the vending machine is full (size = items.length)
    }
    return false; // return false if the vending machine is not full (size < items.length)
  }

  /**
   * Returns the total number of items available in this vending machine
   * 
   * @return the size of this vending machine
   */
  public int size() 
  {
    return size;
  }

  /**
   * Appends an item defined by its description and expirationDate to this vending machine. The item
   * will be added to the end of the vending machine.
   * 
   * @param description    description of the item to be added to the vending machine
   * @param expirationDate a positive integer which represents the expiration date of the item.
   * @throws IllegalStateException    with a descriptive error message if the vending machine is
   *                                  full
   * @throws IllegalArgumentException with a descriptive error message if description is null or
   *                                  blank or if expirationDate is negative ( &lt; 0)
   */
  public void addItem(String description, int expirationDate) 
  {
    if (description == null || description.isBlank())
    {
      throw new IllegalArgumentException("The description provided was blank.");
    }
    if (expirationDate < 0)
    {
      throw new IllegalArgumentException("The expiration date provided was negative.");
    }
    if (isFull())
    {
      throw new IllegalStateException("The vending machine is full! Cannot add item.");
    }
    // create a new item and add it to the end of this vending machine
    items[size] = new Item(description, expirationDate);
    size++;
  }


  /**
   * Returns without removing the string representation of the item at the given index within the
   * vending machine
   * 
   * @param index index of an item within the vending machine
   * @return the string representation of the item stored at the given index within the vending
   *         machine defined by items and itemsCount. The returned string must have the following
   *         format: "description: expirationDate".
   * @throws IndexOutOfBoundsException with a descriptive error message if index < 0 or index
   *                                   >= size of the vending machine
   */
  public String getItemAtIndex(int index) 
  {
    if (index < 0 || index >= size)
    {
      throw new IndexOutOfBoundsException("The provided index cannot be negative or greater than "
          + "or equal to the size of the vending machine");
    }
    return items[index].toString(); // PROBLEM LINE
  }

  /**
   * Returns the number of occurrences of items with a given description within this vending machine
   * 
   * @param description description (name) of an item
   * @return the number of occurrences of items with a given description within the vending machine
   * @throws IllegalArgumentException with a descriptive error message if description is null or
   *                                  blank
   */
  public int getItemOccurrences(String description) 
  {
    if (description == null || description.isBlank())
    {
      throw new IllegalArgumentException("description cannot be null or blank.");
    }
    int nbOccurrences = 0;
    for (int i = 0; i < size; i++) {
      if (description.equals(items[i].getDescription())) {
        nbOccurrences++;
      }
    }
    return nbOccurrences;
  }

  /**
   * Checks whether the vending machine contains at least one item with the provided description
   * 
   * @param description description (name) of an item to search
   * @return true if there is a match with description in the vending machine, false otherwise
   * @throws IllegalArgumentException with a descriptive error message if description is null or
   *                                  blank
   */
  public boolean containsItem(String description) 
  {
    if (description == null || description.isBlank())
    {
      throw new IllegalArgumentException("The description cannot be null or blank."); // throw an
      // exception if the description passed was null or blank
    }
    return getItemOccurrences(description) != 0;
  }

  /**
   * Returns the number of items in the vending machine with a specific description and whose
   * expiration dates are greater or equal to a specific expiration date
   * 
   * @param description    description of the item to find its number of occurrences
   * @param expirationDate specific (earliest) expiration date
   * @return the number of items with a specific description and whose expiration date is greater or
   *         equal to the given one
   * @throws IllegalArgumentException with a descriptive error message if expirationDate is negative
   *                                  (less than zero) or description is null or blank
   */
  public int getItemOccurrencesByExpirationDate(String description, int expirationDate) 
  {
    if (expirationDate < 0 || description == null || description.isBlank())
    {
      throw new IllegalArgumentException("The expiration date cannot be negative, and the description"
          + " cannot be null or blank.");
    }
    int nbOccurrences = 0; // number of occurrences of the matching items
    // traverse the vending machine looking for matching items
    for (int i = 0; i < size; i++) {
      if (description.equals(items[i].getDescription())
          && items[i].getExpirationDate() >= expirationDate) 
      {// match found
        nbOccurrences++;
      }
    }
    // return the number of occurrences of the matching items
    return nbOccurrences;
  }

  /**
   * Returns without removing the index of the item having the provided description and the smallest
   * expiration date within the vending machine.
   * 
   * @param description description of an item
   * @return the index of the next item, meaning the item with the given description and the
   *         smallest expiration date.
   * @throws IllegalArgumentException with a descriptive error message if description is null or
   *                                  blank
   * @throws NoSuchElementException   with a descriptive error message if no match found
   */
  public int getIndexNextItem(String description) 
  {
    if (description == null || description.isBlank())
    {
      throw new IllegalArgumentException("The description cannot be null or blank.");
    }
    
    int index = -1; // index of the search item
    int minExpirationDate = -1; // smallest expiration date of matching items

    // traverse the vending machine looking for the matching item with the smallest expiration date
    for (int i = 0; i < size; i++) 
    {
      if (description.equals(items[i].getDescription())) 
      {
        int itemExpirationDate = items[i].getExpirationDate();
        if (index == -1) { // first match found
          minExpirationDate = items[i].getExpirationDate();
          index = i;
        } 
        else 
        { // another match found
          if (itemExpirationDate < minExpirationDate) 
          {
            // match with smaller (sooner) expiration date found
            minExpirationDate = itemExpirationDate; // update minimum expiration date
            index = i; // update the index of the next item
          }
        }
      }
    }
    if (index == -1)
    {
      throw new NoSuchElementException("No match was found for the given description.");
    }
    return index; // return the index of the item with the given description and the smallest
                  // expiration date if found
  }


  /**
   * Removes and returns the item having the provided description with the smallest expiration date
   * within the vending machine. This method should maintain the order of precedence of items in the
   * vending machine. This means that the remove operation involves a shift operation.
   * 
   * @param description description of the item to remove or dispense
   * @return The removed or dispensed item if it is available
   * @throws IllegalArgumentException with a descriptive error message if description is null or
   *                                  blank
   * @throws NoSuchElementException   with a descriptive error message if no match found
   * 
   */
  public Item removeNextItem(String description)
  {
    // get the index of the next item to dispense by this vending machine
    if (description == null || description.isBlank())
    {
      throw new IllegalArgumentException("description cannot be null or blank.");
    }
    if (getIndexNextItem(description) < 0)
    {
      throw new NoSuchElementException("No match found.");
    }
    int index = getIndexNextItem(description); // exceptions thrown by this method call should
                                               // propagate
    // save a copy of the item to dispense
    Item itemToDispense = items[index];
    // remove the item at index using a shift operation
    for (int i = index + 1; i < size; i++) 
    {
      items[i - 1] = items[i];
    }
    items[size - 1] = null;
    size--;
    return itemToDispense; // return the removed item
  }

  /**
   * Returns a summary of the contents of this vending machine in the following format: Each line
   * contains the description or item name followed by one the number of occurrences of the item
   * name in the vending machine between parentheses. For instance, if the vending machine contains
   * five bottles of water, ten chocolates, and seven snacks, the summary description will be as
   * follows. water (5)\n chocolate (10)\n snack (7) If the vending machine is empty, this method
   * returns an empty string ""
   * 
   * @return a descriptive summary of the contents of the vending machine
   */
  public String getItemsSummary() 
  {
    String summary = ""; // empty string
    // traverse the vending machine and build its items summary description
    for (int i = 0; i < size; i++) {
      // add the item's description and count if not yet processed
      if (!summary.contains(items[i].getDescription())) {
        summary = summary + items[i].getDescription() + " ("
            + getItemOccurrences(items[i].getDescription()) + ")\n";
      }
    }
    return summary.trim(); // return the items' summary
  }

  /**
   * Parse an item's string representation and add the corresponding item to this vending machine
   * 
   * @param itemRepresentation a String representation of an item formatted as "description:
   *                           expirationDate". Extra spaces at the beginning and end of the item
   *                           description and expirationDate can be disregarded.
   * @throws IllegalArgumentException with a descriptive error message if itemRepresentation is null
   *                                  or blank
   * @throws DataFormatException      with a descriptive error message if the provided string is not
   *                                  correctly formatted. A correct format of the
   *                                  itemRepresentation is "description: expirationDate". The
   *                                  description must be a NOT blank string. The expirationDate
   *                                  must be a non-empty string parsable to a positive integer. The
   *                                  item's description and its expiration date must be separated
   *                                  by one colon ":". Extra whitespace at the beginning and end of
   *                                  description or expirationDate should be disregarded.
   * @throws IllegalStateException    with a descriptive error message if the vending machine is
   *                                  full
   */
  public void loadOneItem(String itemRepresentation) throws DataFormatException
  {
    if (itemRepresentation == null || itemRepresentation.isBlank())
    {
      throw new IllegalArgumentException("itemRepresentation cannot be null or blank.");
    }
    if (this.isFull())
    {
      throw new IllegalStateException("The vending machine is full! Cannot add the item.");
    }
    String trimmedString = itemRepresentation.trim(); // stores the item representation without
    // white space
    String[] temp = trimmedString.split(":"); // stores an array of Strings containing the
    // description and expiration date of the item to be added
    if (temp[0].isBlank() || temp[0] == null)
    {
      throw new DataFormatException("The description cannot be blank."); // if the description given
      // was blank, throw data format exception since itemRepresentation was not correctly formatted
    }
    if((temp.length == 2) && !isParsable(temp[1]))
    {
      throw new DataFormatException("The expiration date was not formatted correctly.");
    }
    if (temp.length != 2)
    {
      throw new DataFormatException("There were too many colons in the representation string.");
    }
    try
    {
      String newDescription = temp[0].trim(); // stores the description with white space removed
      if (isParsable(temp[1].trim()))
      {
        int newExpirationDate = Integer.parseInt(temp[1].trim());
        this.addItem(newDescription, newExpirationDate);
      }
    }
    catch (IllegalArgumentException e)
    {
      throw new DataFormatException("itemRepresentation was not correclty formatted.");
    }
    catch (ArrayIndexOutOfBoundsException e)
    {
      throw new DataFormatException("itemRepresentation was not correctly formatted.");
    }
  }
  
  /**
   * This method returns true if the String s is parasble to an integer, and false otherwise
   * 
   * @param s a String to determine if it is parsable
   * @return true if s is parsable to an integer and false otherwise
   */
  private boolean isParsable(String s)
  {
    try
    {
      Integer.parseInt(s.trim());
      return true; // return true if an exception was not thrown, meaning the string given was
      // parsable to an integer
    }
    catch (NumberFormatException e)
    {
      return false; // return false if an exception was thrown, meaning the string given was not
      // parsable to an integer
    }
  }
  
  /**
   * Reads and parses the file passed as input line by line and loads the corresponding items to the
   * vending machine. Each line in the file represents an item description formatted as
   * "description: expirationDate". Blank and badly formatted lines must be skipped.
   * 
   * Displays "Vending machine FULL. No more items can be loaded." when trying to load a new item to
   * the vending machine if it is or becomes full.
   * 
   * @param file file to load items from
   * @return the total number of new items loaded to this vending machine
   * @throws FileNotFoundException if the file object does not correspond to an actual file within
   *                               the file system.
   */
  public int loadItems(File file) throws FileNotFoundException
  {
    int counter = 0; // keeps track of the number of items added to the vending machine
    boolean tryAgain = true; // keeps track of true if more items should be added to the vending
    // machine and false otherwise
    Scanner s = null;
    try
    {
      s = new Scanner(file);
      while (s.hasNextLine() && tryAgain)
      {
        if (this.isFull())
        {
          System.out.println("Vending machine FULL. No more items can be loaded.");
          tryAgain = false;
          return counter;
        }
        String nextLine = s.nextLine(); // a string storing the next line in the file
        if (!(nextLine.isBlank()) && !(nextLine == null))
          {
            String representation = nextLine.trim(); // a string that contains the next line of
            // the file with white space on the ends removed.
            String[] temp = representation.split(":"); // stores an array of Strings containing the
            // description and expiration date of the item to be added, if correctly formatted
            if (temp.length == 2 && isParsable(temp[1]) && !(temp[0].isBlank()) && !(temp[0] == null))
            {
              loadOneItem(representation); // if the line in the file was correctly formatted (the
              // representation was split into 2 parts and the second part is parsable to an integer), 
              // load the item into the vending machine.
              counter++;
            }
          }
      }
      }
    catch (DataFormatException e)
    {
      tryAgain = true; // attempt to add the next item even if the previous item was not formatted 
                       //correctly
    }
    catch (IllegalArgumentException e)
    {
      tryAgain = true; // attempt to add the next item even if the previous item was not formatted 
      //correctly
    }
    catch (IllegalStateException e)
    {
      tryAgain = true; // attempt to add the next item even if the previous item was not formatted 
                       //correctly
    }
    finally
    {
      if (s != null)
      {
        s.close();
      }
    }
    return counter; // return the number of items added to the vending machine
    }

  /**
   * Saves the summary of this vending machine to the file object passed as input
   * 
   * @param file file object where the vending machine summary will be saved
   */
  public void saveVendingMachineSummary(File file) 
  {
    PrintWriter writer = null;
    try
    {
      writer = new PrintWriter(file);
      writer.write(this.getItemsSummary()); // writes the current item summary into the given file
    }
    catch (IOException e)
    {
    }
    catch (Exception e) // catches all exceptions since this method is not meant to throw any
    {
    }
    finally
    {
      if (writer != null)
        writer.close();
    }
  }
}