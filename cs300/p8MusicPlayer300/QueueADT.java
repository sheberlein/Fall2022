//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    PO8 MusicPlayer300 - QueueADT interface
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


public interface QueueADT<T> 
{
  
  // Adds one element to the back of the queue
  public void enqueue(T element);

  // Removes and returns the element at the front of the queue
  public T dequeue();

  // Returns without removing the element at the front of the queue
  public T peek();

  // Returns true if and only if the queue contains no elements
  public boolean isEmpty();

  // Returns the number of elements in the queue
  public int size();

  // Returns a string representation of the queue
  public String toString();
}
