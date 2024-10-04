//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P04 Item class
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

/** 
 * This class models an item defined by its description and expiration date.
 * 
 * @author Sidney Heberlein
 */
public class Item 
{
  private String description; // represents a human readable description of the item
  private int expirationDate; // represents the expiration date of the item, starting at day 0, 
  // which represents Jan 1, 2023
  
  /**
   * Creates a new Item Object with a specific expiration date and description
   * 
   * @param description   a human readable description of the current Item
   * @param expirationDate  a positive integer starting at day 0, which represents Jan 1, 2023
   * @throws IllegalArgumentException - with a descriptive error message if expirationDate is 
   *                                    negative (less than zero) or description is null or blank
   */
  public Item(String description, int expirationDate)
  {
    if (description == null || description.isBlank())
    {
      throw new IllegalArgumentException("The description provided was blank.");
    }
    if (expirationDate < 0)
    {
      throw new IllegalArgumentException("The expiration date provided was negative.");
    }
    this.description = description; // update the description if the description passed is not null
    this.expirationDate = expirationDate; // update the expiration date if the expiration date 
     // passed is not negative
  }
  
  /**
   * This method gets the description of the current Item.
   * 
   * @return the description of the current Item
   */
  public String getDescription()
  {
    return description;
  }
  
  /**
   * This method gets the expiration date of the current Item.
   * 
   * @return the expiration date of the current Item
   */
  public int getExpirationDate()
  {
    return expirationDate;
  }
  
  /**
   * This method changes the description of the current Item.
   * 
   * @param description  the new description of the item
   */
  public void setDescription(String description)
  {
    this.description = description;
  }
  
  /**
   * This method returns a String representation of the current item formatted as 
   * "description: expirationDate"
   * 
   * @return a String representation of the current item
   */
  @Override
  public String toString()
  {
    return description + ": " + expirationDate;
  }
  
  /**
   * This method checks whether the current item equals another object passed as input.
   * 
   * @return true if other is instance of Item and has the same description as this item, 
   *         false otherwise.
   */
  @Override
  public boolean equals(Object other)
  {
    if (other == null)
    {
      return false; // return false if other is null
    }
    if (other instanceof Item)
    {
      if(((Item)other).getDescription().equals(this.getDescription()))
      {
        return true; // return true if this Item's description has the same description as other
      }
      return false; // return false if this Item's description does not match other's description
    }
    else
    {
      return false; // return false if other was not an instance of Item
    }
  }
}
