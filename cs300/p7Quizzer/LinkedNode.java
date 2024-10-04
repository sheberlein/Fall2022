//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    PO7 Quizzer - LinkedNode class
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
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * An instance of this class represents a single node within a singly linked list.
 * @author Sidney Heberlein
 *
 */
public class LinkedNode<T>
{
  private T data; // data carried by this linked node
  private LinkedNode<T> next; // reference to the next linked node
  
  /**
   * This method constructs a new node with the provided information.
   * @param data   to be stored within this node
   * @param next   node, which comes after this node in a singly linked list
   * @throws NullPointerException   with a descriptive error message if data is null
   */
  public LinkedNode(T data, LinkedNode<T> next)
  {
    if (data == null)
    {
      throw new NullPointerException("data cannot be null!");
    }
    this.data = data;
    this.next = next;
  }
  
  /**
   * This method constructs a new node with a specific data and NO next node in the list.
   * @param data   to be stored within this node
   * @throws NullPointerException   with a descriptive error message if data is null
   */
  public LinkedNode(T data)
  {
    if (data == null)
    {
      throw new NullPointerException("data cannot be null!");
    }
    this.data = data;
  }
  
  /**
   * This method is the accessor method for this node's next node reference.
   * @return   the next reference to the node that comes after this one in the list, or null when 
   *           this is the last node in that list
   */
  public LinkedNode<T> getNext()
  {
    return next;
  }
  
  /**
   * This method is the mutator method for this node's next node reference.
   * @param next   node, which comes after this node in a doubly linked list
   */
  public void setNext(LinkedNode<T> next)
  {
    this.next = next;
  }
  
  /**
   * This method is the accessor method for this node's data.
   * @return   the data stored within this node.
   */
  public T getData()
  {
    return data;
  }
  
  /**
   * This method returns a string representation of this linked node formatted as follows:
   * data.toString() if this node does NOT have a next node in the list
   * data.toString() + "->" if this node has a next node in the list
   * @return   a String representation of this node in the list
   */
  @Override
  public String toString()
  {
    if (next == null)
    {
      return data.toString();
    }
    else
    {
      return data.toString() + "->";
    }
  }
}