//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    PO7 Quizzer - Correct Questions Iterator class
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
 * This class implements an iterator to iterate over correctly answered MultipleChoiceQuestion(s)
 * stored in a singly linked list defined by its head.
 * @author Sidney Heberlein
 *
 */
public class CorrectQuestionsIterator implements Iterator<MultipleChoiceQuestion>
{
  private LinkedNode<MultipleChoiceQuestion> next; // refers to a node in the singly linked list of
  // MultipleChoiceQuestion to traverse
  
  /**
   * This method creates a new CorrectQuestionsIterator to start iterating through a singly linked
   * list storing MultipleChoiceQuestion elements
   * @param startNode   pointer to the head of the singly linked list
   * @throws NullPointerException   with a descriptive error message if the startNode is null
   */
  public CorrectQuestionsIterator(LinkedNode<MultipleChoiceQuestion> startNode)
      throws NullPointerException
  {
    if (startNode == null)
    {
      throw new NullPointerException("The starting node cannot be null!");
    }
    else if (!startNode.getData().isCorrect()) // if the starting node was not correct, we need to
      // find the next node that is correct
    {
      LinkedNode<MultipleChoiceQuestion> temp = startNode; // temporarily stores the next node
      while (temp.getNext() != null) // while there is a next node that is not null
      {
        if (temp.getNext().getData().isCorrect()) // if the data of the next node is correct, that
          // node will be the first node
        {
          break;
        }
        temp = temp.getNext();
      }
      next = temp.getNext();
    }
    else
    {
      next = startNode;
    }
  }
  
  /**
   * This method returns true if this iteration has more MultipleChoiceQuestion(s) answered
   * correctly.
   * @return   true if there are more correct MultipleChoiceQuestion(s) in this iteration
   */
  @Override
  public boolean hasNext()
  {
    if (next == null)
    {
      return false; // return false if there is not another correct question in the list
    }
    return true; // return false if there is another correct question in the list
  }
  
  /**
   * This method returns the next correct MultipleChoiceQuestion in this iteration
   * @return   the next correct MultipleChoiceQuestion in this iteration
   * @throws NoSuchElementException   with a descriptive error message if there are no more correct
   * questions in this iteration
   */
  @Override
  public MultipleChoiceQuestion next() throws NoSuchElementException
  {
    if (hasNext() == false)
    {
      throw new NoSuchElementException("There are no more correct questions in this iteration!");
    }
    MultipleChoiceQuestion nextData = next.getData();
    LinkedNode<MultipleChoiceQuestion> temp = next; // temporarily stores the next node
    while (temp.getNext() != null) // while there is a next node that is not null
    {
      if (temp.getNext().getData().isCorrect()) // if the data of the next node is correct, that
        // node will be the next node
      {
        break;
      }
      temp = temp.getNext();
    }
    next = temp.getNext();
    return nextData;
  } 
}