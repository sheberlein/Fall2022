//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P09 Chugimon - Chugimon class
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

/**
 * This class models the Chugimon data type.
 * @author Sidney Heberlein
 *
 */
public class Chugimon implements Comparable<Chugimon>
{
  // instance fields
  private final int FIRST_ID; // the first ID of the Chugimon
  private final int SECOND_ID; // the second ID of the Chugimon
  private final String NAME; // the name of the Chugimon
  private final ChugiType PRIMARY_TYPE; // the primary type of the Chugimon; cannot be null; cannot
  // be the same as the secondary type
  private final ChugiType SECONDARY_TYPE; // the secondary type of the Chugimon; may be null; cannot
  // be the same as the primary type
  private final double HEIGHT; // the height of the Chugimon in meters
  private final double WEIGHT; // the weight of the Chugimon in kilograms
  public static final int MAX_ID = ChugidexUtility.MAX_CHUGI_ID; // the maximum ID number
  public static final int MIN_ID = ChugidexUtility.MIN_CHUGI_ID; // the minimum ID number
  
  // constructor
  
  /**
   * This constructor creates a new Chugimon with specific first and second IDs and initializes all
   * its data fields accordingly.
   * @param firstID   the first ID of the Chugimon, between 1-151
   * @param secondID   the second ID of the Chugimon, between 1-151
   * @throws IllegalArgumentException   if the first and second ID are out of bounds or equal to
   *                                    each other.
   */
  public Chugimon(int firstID, int secondID)
  {
    if (firstID == secondID)
    {
      throw new IllegalArgumentException("The first and second IDs cannot be equal to each other.");
    }
    if (firstID > MAX_ID || firstID < MIN_ID)
    {
      throw new IllegalArgumentException("The first ID was out of range.");
    }
    if (secondID > MAX_ID || secondID < MIN_ID)
    {
      throw new IllegalArgumentException("The second ID was out of range.");
    }
    FIRST_ID = firstID;
    SECOND_ID = secondID;
    HEIGHT = ChugidexUtility.getChugimonHeight(firstID, secondID);
    WEIGHT = ChugidexUtility.getChugimonWeight(firstID, secondID);
    NAME = ChugidexUtility.getChugimonName(firstID, secondID);
    ChugiType[] types = ChugidexUtility.getChugimonTypes(firstID, secondID);
    PRIMARY_TYPE = types[0];
    SECONDARY_TYPE = types[1];
  }
  
  // methods
  
  /**
   * This method gets the name of this Chugimon
   * @return   the name of the Chugimon
   */
  public String getName()
  {
    return NAME;
  }
  
  /**
   * This method gets the first ID of the Chugimon
   * @return   the first ID of the Chugimon
   */
  public int getFirstID()
  {
    return FIRST_ID;
  }
  
  /**
   * This method gets the second ID of the Chugimon
   * @return   the second ID of the Chugimon
   */
  public int getSecondID()
  {
    return SECOND_ID;
  }
  
  /**
   * This method gets the primary type of the Chugimon
   * @return   the primary type of the Chugimon
   */
  public ChugiType getPrimaryType()
  {
    return PRIMARY_TYPE;
  }
  
  /**
   * This method gets the secondary type of the Chugimon
   * @return   the secondary type of the Chugimon
   */
  public ChugiType getSecondaryType()
  {
    return SECONDARY_TYPE;
  }
  
  /**
   * This method gets the height of the Chugimon
   * @return   the height of the Chugimon
   */
  public double getHeight()
  {
    return HEIGHT;
  }
  
  /**
   * This method gets the weight of the Chugimon.
   * @return   the weight of the Chugimon.
   */
  public double getWeight()
  {
    return WEIGHT;
  }
  
  /**
   * This method determines the ordering of Chugimon. Chugimon are ordered by: 1) name
   * (alphabetical) 2) the first ID (if name is equal). The one with the smaller first ID is less
   * than the other. 3) the second ID (if name and first ID are equal). The one with the smaller
   * second ID is less than the other. A Chugimon with identical #1-3 are considered equal.
   * @param otherChugi   the other Chugimon to compare this Chugimon to.
   * @return   a negative int if this Chugimon is less than other, a positive int if this Chugimon
   *           is greater than other, or 0 if this and the other Chugimon are equal.
   */
  @Override
  public int compareTo(Chugimon otherChugi)
  {
    if (!NAME.equals(otherChugi.getName()))
    {
      return NAME.compareTo(otherChugi.getName());
    }
    else if (FIRST_ID != otherChugi.getFirstID())
    {
      return FIRST_ID - otherChugi.getFirstID();
    }
    else if (SECOND_ID != otherChugi.getSecondID())
    {
      return SECOND_ID - otherChugi.getSecondID();
    }
    return 0; // return 0 if the Chugimon are equal
  }
  
  /**
   * This method is a Chugimon's String representation is its name followed by "#FIRST_ID.SECOND_ID"
   * -- Example: "Zapchu#145.25"
   * @return   a String representation of this Chugimon
   */
  @Override
  public String toString()
  {
    return NAME + "#" + FIRST_ID + "." + SECOND_ID;
  }
  
  /**
   * This method is the equals method for Chugimon. This Chugimon equals another object if other is
   * a Chugimon with the exact same name, and their both first and second IDs match.
   * @param other   object to determine equality against this Chugimon
   * @return   true if this Chugimon and other Object are equal, false otherwise
   */
  public boolean equals(Object other)
  {
    if (other instanceof Chugimon)
    {
      Chugimon chug = (Chugimon)other; // cast other to a Chugimon in order to use its getter methods
      if (chug.getFirstID() == FIRST_ID && chug.getSecondID() == SECOND_ID)
      {
        return true;
      }
    }
    return false;
  } 
}
