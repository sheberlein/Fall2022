//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    PO7 Quizzer - Quiz Questions Iterator class
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
 * This is an iterator that moves through MultipleChoiceQuestion(s) in a singly linked list defined
 * by its head
 * @author Sidney Heberlein
 *
 */
public class QuizQuestionsIterator implements Iterator<MultipleChoiceQuestion>
{
  private LinkedNode<MultipleChoiceQuestion> next; // refers to a node in the singly linked list of 
  // MultipleChoiceQuestion
  
  /**
   * This method creates a new QuizQuestionsIterator to start iterating through a singly linked list
   * storing MultipleChoiceQuestion elements
   * @param startNode   pointer to the head of the singly linked list
   * @throws NullPointerException   with a descriptive error message if the startNode is null
   */
  public QuizQuestionsIterator(LinkedNode<MultipleChoiceQuestion> startNode) 
      throws NullPointerException
  {
    if (startNode == null)
    {
      throw new NullPointerException("The starting node cannot be null!");
    }
    next = startNode;
  }
  
  /**
   * This method returns true if this iteration has more MultipleChoiceQuestion(s).
   * @return   true if there are more MultipleChoiceQuestion(s) in this iteration
   */
  @Override
  public boolean hasNext()
  {
    if (next != null)
    {
      return true; // return true if the next pointer of the current node is not null
    }
    else
    {
      return false;
    }
  }
  
  /**
   * This method returns the next MultipleChoiceQuestion in this iteration
   * @return   the next MultipleChoiceQuestion in this iteration
   * @throws NoSuchElementException   with a descriptive error message if there are no more 
   * questions in this iteration
   */
  @Override
  public MultipleChoiceQuestion next() throws NoSuchElementException
  {
    if (this.hasNext() == false)
    {
      throw new NoSuchElementException("There are no more questions in this iteration!");
    }
    MultipleChoiceQuestion nextData = next.getData();
    next = next.getNext();
    return nextData;
  }
}