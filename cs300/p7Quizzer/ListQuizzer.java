//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    PO7 Quizzer - List Quizzer class
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
 * This class models an iterable singly-linked list data structure which stores elements of type 
 * MultipleChoiceQuestion.
 * @author Sidney Heberlein
 *
 */
public class ListQuizzer implements Iterable<MultipleChoiceQuestion>
{
  private LinkedNode<MultipleChoiceQuestion> head; // head of this singly linked list
  private LinkedNode<MultipleChoiceQuestion> tail; // tail of this singly linked list
  private int size; // total number of MultipleChoiceQuestions stored in this ListQuizzer
  private ListingMode listingMode; // the listing mode of this list quizzer which defines which 
  // questions are going to be listed while iterating through this list
  
  /**
   * This constructor creates a new empty instance of ListQuizzer which contains zero elements and 
   * sets its listing mode to be ListingMode.ALL by default.
   */
  public ListQuizzer()
  {
    head = null;
    tail = null;
    size = 0;
    listingMode = listingMode.ALL;
  }
  
  /**
   * This method checks whether this list is empty
   * @return   true if this list is empty and false otherwise
   */
  public boolean isEmpty()
  {
    if (size == 0)
      return true; // return true if the size of this list is 0
    return false; // return false otherwise
  }
  
  /**
   * This method gets the size of this list
   * @return   the size of this list
   */
  public int size()
  {
    return size;
  }
  
  /**
   * This method sets the listing mode of this list to the one provided as input
   * @param listingMode   listing mode to set
   */
  public void switchMode(ListingMode listingMode)
  {
    this.listingMode = listingMode;
  }
  
  /**
   * This method adds a specific MultipleChoiceQuestion to a given position of this list
   * @param index   position index where to add the newQuestion to this list
   * @param question   some MultipleChoiceQuestion to add to this list
   * @throws NullPointerException   with a descriptive error message if newQuestion is null
   * @throws IndexOutOfBoundsException   with a descriptive error message if index is OUT of the 
   *                                     range 0 .. size() inclusive
   */
  public void add(int index, MultipleChoiceQuestion question) 
      throws NullPointerException, IndexOutOfBoundsException
  {
    if (question == null)
    {
      throw new NullPointerException("Question cannot be null!");
    }
    else if (index < 0 || index > size()) // scenario 1: index is out of range
    {
      throw new IndexOutOfBoundsException("index was out of range.");
    }
    else if (isEmpty())
    {
      head = new LinkedNode<MultipleChoiceQuestion>(question);
      tail = head;
      size++;
    }
    else if (index == 0) // scenario 2: adding when index is 0
    {
      this.addFirst(question);
    }
    else if (index == size) // scenario 3: adding when index is size
    {
      this.addLast(question);
    }
    else //scenario 4: adding to the middle of the list
    {
      LinkedNode<MultipleChoiceQuestion> temp = head; // start at the head
      for (int i = 0; i < size; i++)
      {
        if (temp != null)
        {
          if (i + 1 == index) // if the next node is the node with index index, update the current 
            // node's next pointer to the new node with question as its data, and update the new
            // node's next pointer to the node that was previously at index index
          {
            LinkedNode<MultipleChoiceQuestion> q = new LinkedNode<MultipleChoiceQuestion>(question);
            // a new node that holds the data question
            LinkedNode<MultipleChoiceQuestion> next = temp.getNext(); // the node that is currently 
            // at index + 1
            temp.setNext(q); // set the node after the node at (index - 1) to a new node with
            // question as its data
            q.setNext(next);
            break; // end the for loop after the new element was added
          }
          temp = temp.getNext(); // set the temporary linked node to the next node
        }
       }
      size++;
    }
  }
  
  /**
   * This method adds a specific MutlipleChoiceQuestion to the head of this list
   * @param question   some MultipleChoiceQuestion to add to the head of this list
   * @throws NullPointerException   with a descriptive error message if newQuestion is null
   */
  public void addFirst(MultipleChoiceQuestion question) throws NullPointerException
  {
    if (question == null)
    {
      throw new NullPointerException("Question cannot be null!");
    }
    else if (isEmpty())
    {
      head = new LinkedNode<MultipleChoiceQuestion>(question);
      tail = head;
      size++;
    }
    else
    {
      LinkedNode<MultipleChoiceQuestion> temp = head; // a new LinkedNode that stores the head node
      head = new LinkedNode<MultipleChoiceQuestion>(question, temp); // a new LinkedNode with
      // question as its data and temp (the previous head) as its next node
      size++;
    }
  }
  
  /**
   * This method adds a specific MutlipleChoiceQuestion to the tail of this list
   * @param question   some MultipleChoiceQuestion to add to the tail of this list
   * @throws NullPointerException   with a descriptive error message if newQuestion is null
   */
  public void addLast(MultipleChoiceQuestion question) throws NullPointerException
  {
    if (question == null)
    {
      throw new NullPointerException("Question cannot be null.");
    }
    else if (isEmpty())
    {
      head = new LinkedNode<MultipleChoiceQuestion>(question);
      tail = head; // set the head and tail to the new question if the list is empty
      size++;
    }
    else
    {
      LinkedNode<MultipleChoiceQuestion> q = new LinkedNode<MultipleChoiceQuestion>(question);
      LinkedNode<MultipleChoiceQuestion> temp = tail;
      tail = q; // set the tail to the new question if the list is not empty
      temp.setNext(q);
      size++;
    }
  }
  
  /**
   * This method removes all the question from this list. The list should be empty after this method
   * is called.
   */
  public void clear()
  {
    removeFirst();
    removeLast();
  }
  
  /**
   * This method checks whether this list contains a match with someQuestion
   * @param someQuestion   some question to find
   * @return   true if this list contains a match with someQuestion and false otherwise
   */
  public boolean contains(MultipleChoiceQuestion someQuestion)
  {
    LinkedNode<MultipleChoiceQuestion> curr = head; // a new node representing the current node
    // being used, starting at the head node
    for (int i = 0; i < size; i++)
    {
      if (curr.getData().equals(someQuestion))
      {
        return true; // if the current node's data equals someQuestion, then the list contains a
        // match with someQuestion
      }
      curr = curr.getNext();
    }
    return false; // if the current node's data never equaled someQuestion, the list does not
    // contain a match with someQuestion
  }
  
  /**
   * This method returns the MultipleChoiceQuestion stored at the given index within this list
   * @param index   index of the MultipleChoiceQuestion to return
   * @return   the MultipleChoiceQuestion stored at the given index within this list
   * @throws IndexOutOfBoundsException   if index is out of the range 0 .. size()-1 inclusive
   */
  public MultipleChoiceQuestion get(int index) throws IndexOutOfBoundsException
  {
    if (index < 0 || index > size - 1)
    {
      throw new NoSuchElementException("This list is empty!");
    }
    if (index == 0)
    {
      return head.getData(); // if the index given is 0, return the head's data
    }
    if (index == size - 1)
    {
      return tail.getData(); // if the index given is size - 1, return the tail's data
    }
    
    // if the index is in the middle of the list...
    LinkedNode<MultipleChoiceQuestion> temp = head; // start at the head
    for (int i = 0; i < size; i++)
    {
      if (i + 1 == index) // if the next node is the node with index index, break so that we can
        // return the next node
      {
        break;
      }
      temp = temp.getNext(); // set the temporary linked node to the next node
    }
    return temp.getNext().getData();
  }
  
  /**
   * This method gets the MultipleChoiceQuestion at the head of this list
   * @return   the MultipleChoiceQuestion at the head of this list
   * @throws NoSuchElementException   with a descriptive error message if this list is empty
   */
  public MultipleChoiceQuestion getFirst() throws NoSuchElementException
  {
    if (isEmpty())
    {
      throw new NoSuchElementException("This list is empty!");
    }
    return head.getData();
  }
  
  /**
   * This method gets the MultipleChoiceQuestion at the tail of this list
   * @return   the MultipleChoiceQuestion at the tail of this list
   * @throws NoSuchElementException   with a descriptive error message if this list is empty
   */
  public MultipleChoiceQuestion getLast() throws NoSuchElementException
  {
    if (isEmpty())
    {
      throw new NoSuchElementException("This list is empty!");
    }
    else
      return tail.getData();
  }
  
  /**
   * This method removes and returns the MultipleChoiceQuestion at the given index
   * @param index   of the MultipleChoiceQuestion to remove
   * @return   the removed MultipleChoiceQuestion
   * @throws IndexOutOfBoundsException   with a descriptive error message if index is out of the 
   * range 0 .. size()-1 inclusive
   */
  public MultipleChoiceQuestion remove(int index) throws IndexOutOfBoundsException
  {
    // scenario 1: the given index is out of range
    if (index < 0 || index > size - 1)
    {
      throw new IndexOutOfBoundsException("Index was out of bounds!");
    }
    else if (size == 1) // removing the only element in the list
    {
      LinkedNode<MultipleChoiceQuestion> returnThis = head;
      head = null;
      tail = head;
      size--;
      return returnThis.getData();
    }
    else if (index == 0)
    {
      return removeFirst();
    }
    else if (index == size - 1)
    {
      return removeLast();
    }
    else
    {
      LinkedNode<MultipleChoiceQuestion> prev = head; // start at the head node
      LinkedNode<MultipleChoiceQuestion> returnThis = head;
      for (int i = 0; i < index - 1; i++)
      {
        prev = prev.getNext(); // this for loop finds the node before the node to be removed
      }
      LinkedNode<MultipleChoiceQuestion> next = prev.getNext().getNext(); // this is the node after
      // the node to be removed
      returnThis = prev.getNext(); // this is the node being removed and returned
      prev.setNext(next);
      size--;
      return returnThis.getData();
    }
  }
  
  /**
   * This method removes and returns the MultipleChoiceQuestion at the head of this list
   * @return   the MultipleChoiceQuestion at the head of this list
   * @throws NoSuchElementException   with a descriptive error message if this list is empty
   */
  public MultipleChoiceQuestion removeFirst() throws NoSuchElementException
  {
    if (isEmpty())
    {
      throw new NoSuchElementException("The list is empty!");
    }
    
    // scenario 1: removing from a list with only one element
    else if (size == 1)
    {
      MultipleChoiceQuestion temp = head.getData();
      head = null;
      tail = head;
      size--;
      return temp;
    }
    
    // scenario 2: removing from a list with more than one element
    else
    {
      MultipleChoiceQuestion temp = head.getData();
      LinkedNode<MultipleChoiceQuestion> newFirst = head.getNext(); // stores the second element in
      // the list (the new head node)
      head = newFirst;
      size--;
      return temp;
    }
  }
  
  /**
   * This method removes and returns the MultipleChoiceQuestion at the tail of this list
   * @return   the MultipleChoiceQuestion at the tail of this list
   * @throws NoSuchElementException   with a descriptive error message if this list is empty
   */
  public MultipleChoiceQuestion removeLast() throws NoSuchElementException
  {
    if (isEmpty())
    {
      throw new NoSuchElementException("The list is empty!");
    }
    
    // scenario 1: removing from a list with only one element
    else if (size == 1)
    {
      MultipleChoiceQuestion temp = tail.getData();
      head = null;
      tail = head;
      size--;
      return temp;
    }
    
    // scenario 2: removing from a list with more than one element
    else
    {
      MultipleChoiceQuestion temp = tail.getData();
      LinkedNode<MultipleChoiceQuestion> newLast = head; // a new node that will store the second
      // to last node in the list (the new tail node after tail is removed)
      while (newLast.getNext().getNext() != null)
      {
        newLast = newLast.getNext(); // find the second to last node in the list (new tail node)
      }
      tail = newLast; // set the tail to the new tail
      newLast.setNext(null); // set the second to last node's next pointer to null
      size--;
      return temp; // return the data from the previous tail
    }
  }
  
  /**
   * This method returns an iterator to iterate through this list with respect to the listingMode. 
   * If the listingMode is ALL, the returned iterator is initialized to the head of this list. If 
   * the listingMode is CORRECT, the returned iterator is initialized to the node carrying first 
   * correctly answered question If the listingMode is INCORRECT, the returned iterator is 
   * initialized to the node carrying first incorrectly answered question
   * @return   an iterator to iterate through this list with respect to the listingMode of this list.
   */
  @Override
  public Iterator<MultipleChoiceQuestion> iterator()
  {
    if (listingMode == listingMode.ALL)
    {
      return new QuizQuestionsIterator(head);
    }
    else if (listingMode == listingMode.CORRECT)
    {
      LinkedNode<MultipleChoiceQuestion> curr = head; // the current node being accessed
      while (curr.getNext() != null)
      {
        if (curr.getData().isCorrect())
        {
          break; // if a correct node is found, break out of the while loop and return a new
          // correct questions iterator
        }
        curr = curr.getNext();
      }
      return new CorrectQuestionsIterator(curr);
    }
    else
    {
      LinkedNode<MultipleChoiceQuestion> curr = head; // the current node being accessed
      while (curr.getNext() != null)
      {
        if (!curr.getData().isCorrect())
        {
          break; // if an incorrect node is found, break out of the while loop and return a new
          // incorrect questions iterator
        }
        curr = curr.getNext();
      }
      return new IncorrectQuestionsIterator(curr);
    }
  }
  
  /**
   * This method calculates the total points of the correctly answered questions of this ListQuizzer
   * @return   the score of this ListQuizzer
   */
  public int calculateScore()
  {
    LinkedNode<MultipleChoiceQuestion> curr = head; // a new node representing the current node
    // being used, starting at the head node
    int total = 0; // stores the total points of the correctly answered questions of this quiz
    for (int i = 0; i < size; i++)
    {
      if (curr.getData().isCorrect())
      {
        total += curr.getData().getPointsPossible(); // if the student answered the question
        // correctly, add the question's number of points to the total
      }
      curr = curr.getNext();
    }
    return total;
  }
  
  /**
   * This method calculates the total possible points of this ListQuizzer
   * @return   the score of this ListQuizzer
   */
  public int calculateTotalPoints()
  {
    LinkedNode<MultipleChoiceQuestion> curr = head; // a new node representing the current node
    // being used, starting at the head node
    int total = 0; // stores the total points of the questions of this quiz
    for (int i = 0; i < size; i++)
    {
      total += curr.getData().getPointsPossible();
      curr = curr.getNext();
    }
    return total;
  }
  
  /**
   * This method returns a deep copy of this list. This method creates a copy of this list as a new
   * ListQuizzer and adds deep copies of each MultipleChoiceQuestion stored in this list to the deep
   * copy.
   * @return   a deep copy of this list.
   */
  public ListQuizzer copy()
  {
    ListQuizzer copy = new ListQuizzer(); // the new copy of the list to be returned
    LinkedNode<MultipleChoiceQuestion> curr = head; // a new node representing the current node
    // being used, starting at the head node
    for (int i = 0; i < size; i++)
    {
      copy.addLast(curr.getData());
    }
    return copy;
  }
  
  /**
   * This method loads MultipleChoiceQuestions from a file
   * 
   * @author Jeff and Mouna
   * 
   * @param file   file to read
   * @return   the number of added MultipleChoiceQuestions to this list
   * @throws FileNotFoundException   if the file is not found
   */
  public int loadQuestions(File file) throws FileNotFoundException
  {
    int loadedCount = 0; // count of loaded multiple choice questions
    int answerCount = 0; // count of possible answers per question
    int indexCorrectAnswer = 0; // index of the correct answer
    int points = 0; // possible points for a multiple choice question
    // try to read the file
    Scanner reader = null; // scanner to read the file line by line
    int lineNumber = 0; // number of the last read line
    try {
    reader = new Scanner(file);
    // parse the file lines - while loop to read parts of each multiple choice question
    while (reader.hasNextLine()) { // no more lines to read
    // read title
    String title = reader.nextLine();
    lineNumber++;
    // read question stem
    if (!reader.hasNextLine()) { // no more lines to read
    return loadedCount;
    }
    String question = reader.nextLine();
    lineNumber++;
    // read possible answers count
    if (!reader.hasNextLine()) { // no more lines to read
    return loadedCount;
    }
    String count = reader.nextLine();
    lineNumber++;
    // check the validity of count
    try {
    answerCount = Integer.parseInt(count.trim());
    if (answerCount <= 0 || answerCount > 10) {
    throw new NumberFormatException();
    }
    } catch (NumberFormatException e) { // count invalid - print an error message and return
    System.out.println("Syntax error! A positive integer less or equal to 10 is expected at line "
    + lineNumber + (". Load questions operation interrupted!"));
    return loadedCount;
    }
    // valid count -> create the answerList array
    String[] answerList = new String[answerCount];
    int index = 0;
    while (index < answerCount && reader.hasNextLine()) {
    String answer = reader.nextLine();
    lineNumber++;
    answerList[index] = answer;
    index++;
    }
    // read index of the correct answer
    if (!reader.hasNextLine()) { // no more lines to read
    return loadedCount;
    }
    String line = reader.nextLine();
    lineNumber++;
    try { // check the validity of the index of the correct answer
    indexCorrectAnswer = Integer.parseInt(line.trim());
    if (indexCorrectAnswer < 0 || indexCorrectAnswer >= answerCount) 
    {
    throw new NumberFormatException();
    }
    } catch (NumberFormatException e) { // indexCorrectAnswer invalid - print error and return
    System.out.println("Syntax error! A positive integer less than " + answerCount 
        + " is expected at line " + lineNumber + (". Load questions operation interrupted!"));
    return loadedCount;
    }
    // valid index of the correct answer -> read possible points
    // read points
    if (!reader.hasNextLine()) { // no more lines to read
    return loadedCount;
    }
    line = reader.nextLine();
    lineNumber++;
    try { // check the validity of the index of the correct answer
    points = Integer.parseInt(line.trim());
    if (points < 0) {
    throw new NumberFormatException();
    }
    } catch (NumberFormatException e) { // invalid points - print error message and return
    System.out.println("Syntax error! A positive integer for possible points " 
    + " is expected at line " + lineNumber + (". Load questions operation interrupted!"));
    return loadedCount;
    }
    // create and add quizQuestion
    MultipleChoiceQuestion quizQuestion =
    new MultipleChoiceQuestion(title, question, answerList, 
    indexCorrectAnswer, points);
    this.addLast(quizQuestion);
    loadedCount += 1;
    System.out.println("Question " + loadedCount + " loaded!");
    }
    } finally {
    if (reader != null)
    reader.close();
    }
    return loadedCount;
  }
  
  /**
   * This method allows a user to take this quiz. The quiz should be taken on a deep copy of this 
   * ListQuizzer. This method should not make any changes to the contents of this ListQuizzer.
   * 
   * @author Jeff and Mouna
   * 
   * @return   the instance of ListQuizzer taken by the user. It should include the user's responses.
   */
  public ListQuizzer takeQuiz()
  {
    ListQuizzer copy = this.copy();
    copy.switchMode(ListingMode.ALL);
    Scanner input = new Scanner(System.in);
    for (MultipleChoiceQuestion question : copy) {
    System.out.println(question);
    System.out.print("Enter your answer: ");
    int entry = input.nextInt();
    question.setStudentAnswerIndex(entry - 1);
    if (question.isCorrect()) {
    System.out.println("Correct!");
    } else {
    System.out.println("Incorrect!");
    }
    }
    int correctPoints = copy.calculateScore();
    int totalPoints = copy.calculateTotalPoints();
    System.out.println("Your Score: " + correctPoints);
    System.out.println("Percentage: " + correctPoints / totalPoints);
    input.close();
    return copy;
  }
  
  /**
   * This method returns true if o is a ListQuizzer which has the exact same contents as this 
   * ListQuizzer
   * 
   * @author Mouna
   * 
   * @param o  an object to compare with
   * @return   true if o is instanceof ListQuizzer with the exact same contents as this ListQuizzer
   */
  @Override
  public boolean equals(Object o)
  {
    if(o instanceof ListQuizzer) 
    {
      ListQuizzer other = (ListQuizzer)o;
      if(this.size()!= other.size())
        return false;
      this.switchMode(ListingMode.ALL);
      other.switchMode(ListingMode.ALL);
      Iterator<MultipleChoiceQuestion> iterator = this.iterator();
      Iterator<MultipleChoiceQuestion> otherIterator = other.iterator();
      while(iterator.hasNext())
      {
        if(!iterator.next().equals(otherIterator.next()))
          return false;
      }
      return true;
    }
    return false;
  }   
}