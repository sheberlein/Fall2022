//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    PO7 Quizzer - Quizzer Tester class
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
 * This class checks the correctness of the an implementation of the Quizzer class
 * @author Sidney Heberlein
 *
 */
public class QuizzerTester 
{
  
  /**
   * This method runs all the tester methods defined in this QuizzerTester
   * @return   true if all tests pass and false if any of the tests fails
   */
  public static boolean runAllTests()
  {
    if (!testMultipleChoiceQuestion())
      return false;
    if (!testLinkedNode())
      return false;
    if (!testAddFirst())
      return false;
    if (!testAddLast())
      return false;
    if (!testAdd())
      return false;
    if (!testRemoveLast())
      return false;
    if (!testRemoveFirst())
      return false;
    if (!testRemove())
      return false;
    if (!testQuizQuestionsIterator())
      return false;
    if (!testCorrectQuestionsIterator())
      return false;
    if (!testInCorrectQuestionsIterator())
      return false;
    return true; // return true if all of the testers return true
  }
  
  /**
   * This method tests and makes use of the MultipleChoiceQuestion constructor, an accessor (getter)
   * method, overridden method toString() and equals() method defined in the MultipleChoiceQuestion 
   * class.
   * @return   true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testMultipleChoiceQuestion()
  {
    try
    {
      // testing the constructor and getters
      String title = "Question 1"; // a new title of a question
      String q = "What is 5 divided by 1?"; // a question description
      String[] a = new String[4]; // a new array of question choices
      int correctIndex = 3; // index of the correct answer
      int points = 5; // number of points for the question
      MultipleChoiceQuestion question 
      = new MultipleChoiceQuestion(title, q, a, correctIndex, points);
      // a new multiple choice question with the above information
      if (!question.getTitle().equals("Question 1"))
      {
        System.out.println("Your title did not match the expected title.");
        return false;
      }
      if (!question.getQuestion().equals(q))
      {
        System.out.println("Your question did not match the expected question.");
        return false;
      }
      if (question.getCorrectAnswerIndex() != correctIndex)
      {
        System.out.println("Your correct answer index did not match the expected index.");
        return false;
      }
      if (question.getPointsPossible() != points)
      {
        System.out.println("Your points possible did not match the expected points possible.");
        return false;
      }
      
      // testing the setters (and getStudentAnswerIndex getter)
      question.setTitle("Q1");
      question.setQuestion("Q2");
      question.setStudentAnswerIndex(2);
      question.setPointsPossible(4);
      question.setCorrectAnswerIndex(2);
      
      if (!question.getTitle().equals("Q1"))
      {
        System.out.println("Your title did not match the expected title.");
        return false;
      }
      if (!question.getQuestion().equals("Q2"))
      {
        System.out.println("Your question did not match the expected question.");
        return false;
      }
      if (question.getCorrectAnswerIndex() != 2)
      {
        System.out.println("Your correct answer index did not match the expected index.");
        return false;
      }
      if (question.getPointsPossible() != 4)
      {
        System.out.println("Your points possible did not match the expected points possible.");
        return false;
      }
      if (question.getStudentAnswerIndex() != 2)
      {
        System.out.println("Your student answer index did not match the expected points possible.");
        return false;
      }
      
      // resetting things from the constructor
      question.setTitle("Question 1");
      question.setQuestion("What is 5 divided by 1?");
      question.setPointsPossible(5);
      question.setCorrectAnswerIndex(3);
      
      // testing the overridden toString() method
      String expected = "QUESTION TITLE: " + "\"" + title + "\"" + "\n" + "Question:\n" + q + "\n" 
      + "Available Answers:\n" + question.getAnswers();
      if (!expected.equals(question.toString()))
      {
        System.out.println("Your toString method did not return the correct output.");
        return false;
      }
      
      // testing the overridden equals method
      MultipleChoiceQuestion question2 = question;
      if (!question2.equals(question))
      {
        System.out.println("Your equals method did not return the correct output.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected exception was thrown.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /**
   * This method tests and makes use of the LinkedNode constructor, an accessor (getter) method, and
   * a mutator (setter) method defined in the LinkedNode class.
   * @return   true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLinkedNode()
  {
    try
    {
      // testing the constructor and getters
      LinkedNode<String> nextNode = new LinkedNode<String>("two");
      LinkedNode<String> node = new LinkedNode<String>("one", nextNode);
      if (!node.getData().equals("one"))
      {
        System.out.println("Your getData method did not produce the correct result.");
        return false;
      }
      if (!node.getNext().equals(nextNode))
      {
        System.out.println("Your getNext method did not produce the correct result.");
        return false;
      }
      
      // testing a setter method
      LinkedNode<String> node3 = new LinkedNode("three");
      node.setNext(node3);
      if (!node.getNext().equals(node3))
      {
        System.out.println("Your setNext method did not produce the correct result.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected exception was thrown.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /**
   * This method is the tester for ListQuizzer.addFirst() method
   * @return   true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddFirst()
  {
    try
    {
      ListQuizzer quiz = new ListQuizzer();
      // test adding a first element when the list is empty
      if (!quiz.isEmpty())
      {
        System.out.println("Your isEmpty method did not work properly.");
        return false;
      }
      String title = "Question 1"; // a new title of a question
      String q = "What is 5 divided by 1?"; // a question description
      String[] a = new String[4]; // a new array of question choices
      int correctIndex = 3; // index of the correct answer
      int points = 5; // number of points for the question
      MultipleChoiceQuestion question 
      = new MultipleChoiceQuestion(title, q, a, correctIndex, points);
      // a new multiple choice question with the above information
      quiz.addFirst(question); // add the question to the empty list
      if (!quiz.getFirst().equals(question) || !quiz.getLast().equals(question) || quiz.size() != 1) 
        // if the head and tail pointers were not updated to the new question, or the size was not
        // updated to 1, return false
      {
        System.out.println("Your addFirst method did not properly update the empty list.");
        return false;
      }
      
      // test adding a first element when the list is not empty
      String title2 = "Question 1"; // a new title of a question
      String q2 = "What is 5 divided by 1?"; // a question description
      String[] a2 = new String[4]; // a new array of question choices
      int corrIndex = 3; // index of the correct answer
      int points2 = 5; // number of points for the question
      MultipleChoiceQuestion question2 
      = new MultipleChoiceQuestion(title2, q2, a2, corrIndex, points2);
      // a new multiple choice question with the above information
      quiz.addFirst(question2); // add the question to the non-empty list
      if (!quiz.getFirst().equals(question2) || !quiz.getLast().equals(question) || quiz.size() != 2)
        // if the head pointer was not updated to the new question or the tail pointer WAS updated, 
        // or the size was not updated to 1, return false
      {
        System.out.println("Your addFirst method did not properly update the non-empty list.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected exception was thrown.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /**
   * This method is the tester for ListQuizzer.addLast() method
   * @return   true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddLast()
  {
    try
    {
      ListQuizzer quiz = new ListQuizzer();
      // test adding a last element when the list is empty
      if (!quiz.isEmpty())
      {
        System.out.println("Your isEmpty method did not work properly.");
        return false;
      }
      String title = "Question 1"; // a new title of a question
      String q = "What is 5 divided by 1?"; // a question description
      String[] a = new String[4]; // a new array of question choices
      int correctIndex = 3; // index of the correct answer
      int points = 5; // number of points for the question
      MultipleChoiceQuestion question = 
          new MultipleChoiceQuestion(title, q, a, correctIndex, points);
      // a new multiple choice question with the above information
      quiz.addLast(question); // add a question to the empty list
      if (!quiz.getFirst().equals(question) || !quiz.getLast().equals(question) || quiz.size() != 1) 
        // if the head and tail pointers were not updated to the new question, or the size was not
        // updated to 1, return false
      {
        System.out.println("Your addLast method did not properly update the empty list.");
        return false;
      }
      
      // test adding a last element when the list is not empty
      String title2 = "Question 1"; // a new title of a question
      String q2 = "What is 5 divided by 1?"; // a question description
      String[] a2 = new String[4]; // a new array of question choices
      int corrIndex = 3; // index of the correct answer
      int points2 = 5; // number of points for the question
      MultipleChoiceQuestion question2 = 
          new MultipleChoiceQuestion(title2, q2, a2, corrIndex, points2);
      // a new multiple choice question with the above information
      quiz.addLast(question2); // add a question to the non-empty list
      if (!quiz.getFirst().equals(question2) || !quiz.getFirst().equals(question) 
          || quiz.size() != 2)
      {
        System.out.println("Your addLast method did not properly update the non-empty list.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected exception was thrown.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /**
   * This method is the tester for ListQuizzer.add() method
   * @return   true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAdd()
  {
    try
    {
      // scenario 1: index given is out of range
      ListQuizzer quiz = new ListQuizzer(); 
      String title = "Question 1"; // a new title of a question
      String q = "What is 5 divided by 1?"; // a question description
      String[] a = new String[4]; // a new array of question choices
      int correctIndex = 3; // index of the correct answer
      int points = 5; // number of points for the question
      MultipleChoiceQuestion question = 
          new MultipleChoiceQuestion(title, q, a, correctIndex, points);
      // a new multiple choice question with the above information
      boolean test = false; // if test is false after trying the below statement, the expected
      // exception was not thrown properly
      try
      {
        quiz.add(-10, question);
      }
      catch (IndexOutOfBoundsException e)
      {
        test = true;
      }
      if (test == false)
      {
        System.out.println("testAdd scenario 1: Your testAdd method did not throw the expected"
            + " exception when given an invalid index.");
        return false;
      }
      
      // adding a question to the front of the list for testing purposes
      quiz.addFirst(question);
      
      // scenario 2: index is 0
      String title2 = "Question 2"; // a new title of a question
      String q2 = "What is 5 divided by 2?"; // a question description
      String[] a2 = new String[4]; // a new array of question choices
      int corrIndex = 3; // index of the correct answer
      int points2 = 5; // number of points for the question
      MultipleChoiceQuestion question2 = 
          new MultipleChoiceQuestion(title2, q2, a2, corrIndex, points2);
      // a new multiple choice question with the above information
      quiz.add(0, question2); // add a new question to index 0
      
      if ((!quiz.getFirst().equals(question2)) || (quiz.size() != 2) 
          || (!quiz.getLast().equals(question)))
      {
        System.out.println("testAdd scenario 2: Your testAdd method did not correctly add the item"
            + " to the list when the index given was 0.");
        return false;
      }
      
      // scenario 3: index is size
      String title3 = "Question 1"; // a new title of a question
      String q3 = "What is 5 divided by 1?"; // a question description
      String[] a3 = new String[4]; // a new array of question choices
      int corrIndex2 = 3; // index of the correct answer
      int points3 = 5; // number of points for the question
      MultipleChoiceQuestion question3 = 
          new MultipleChoiceQuestion(title3, q3, a3, corrIndex2, points3);
      // a new multiple choice question with the above information
      quiz.add(2, question3); // add a new question to index 2 (size)
      if (!quiz.getFirst().equals(question2) || quiz.size() != 3 || !quiz.getLast().equals(question3))
      {
        System.out.println("testAdd scenario 3: Your testAdd method did not correctly add the item"
            + " to the list when the index given was the size of the list.");
        return false;
      }
      
      // scenario 4: index is >= 1 and < size
      String title4 = "Question 1"; // a new title of a question
      String q4 = "What is 5 divided by 1?"; // a question description
      String[] a4 = new String[4]; // a new array of question choices
      int corrIndex3 = 3; // index of the correct answer
      int points4 = 5; // number of points for the question
      MultipleChoiceQuestion question4 = 
          new MultipleChoiceQuestion(title4, q4, a4, corrIndex3, points4);
      // a new multiple choice question with the above information
      quiz.add(1, question4); // add a new question to index 1
      if (!quiz.getFirst().equals(question2) || quiz.size() != 4 || !quiz.getLast().equals(question3)
          || !quiz.get(1).equals(question4))
      {
        System.out.println("testAdd scenario 4: Your testAdd method did not correctly add the item"
            + " to the list when the index given was >= 1 and < size.");
        return false;
      } 
    }
    catch (Exception e)
    {
      System.out.println("An unexpected exception was thrown.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /**
   * This method is the tester for ListQuizzer.removeLast() method
   * @return   true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemoveLast()
  {
    try
    {
      // scenario 1: trying to remove from an empty list
      boolean test = false;
      try
      {
        ListQuizzer q1 = new ListQuizzer();
        q1.removeLast();
      }
      catch (NoSuchElementException e)
      {
        test = true;
      }
      if (test == false)
      {
        System.out.println("testRemoveLast scenario 1: Your removeLast method did not throw"
            + " the correct exception when trying to remove an item from an empty.");
        return false;
      }
      
      // scenario 2: trying to remove from a list with one element
      ListQuizzer quiz = new ListQuizzer(); 
      String title = "Question 1"; // a new title of a question
      String q = "What is 5 divided by 1?"; // a question description
      String[] a = new String[4]; // a new array of question choices
      int correctIndex = 3; // index of the correct answer
      int points = 5; // number of points for the question
      MultipleChoiceQuestion question = 
          new MultipleChoiceQuestion(title, q, a, correctIndex, points);
      // a new multiple choice question with the above information
      quiz.addFirst(question); // add the question to the list
      quiz.removeLast(); // remove the last (only) element in the list
      if (!quiz.isEmpty())
      {
        System.out.println("testRemoveLast scenario 2: Your removeLast method did not correctly"
            + " remove the last question when there was only one question in the list.");
        return false;
      }
      
      // scenario 3: trying to remove from a list with more than one element
      ListQuizzer quiz2 = new ListQuizzer(); 
      String title2 = "Question 2"; // a new title of a question
      String q2 = "What is 5 divided by 2?"; // a question description
      String[] a2 = new String[4]; // a new array of question choices
      int corrIndex = 3; // index of the correct answer
      int points2 = 5; // number of points for the question
      MultipleChoiceQuestion question2 = 
          new MultipleChoiceQuestion(title2, q2, a2, corrIndex, points2);
      // a new multiple choice question with the above information
      quiz2.addLast(question2); // add this question to quiz
      String title3 = "Question 3"; // a new title of a question
      String q3 = "What is 5 divided by 3?"; // a question description
      String[] a3 = new String[4]; // a new array of question choices
      int corrIndex2 = 3; // index of the correct answer
      int points3 = 5; // number of points for the question
      MultipleChoiceQuestion question3 = 
          new MultipleChoiceQuestion(title3, q3, a3, corrIndex2, points3);
      // a new multiple choice question with the above information
      quiz2.addLast(question3); // add another question to quiz
      
      // removing the last element
      quiz2.removeLast();
      
      if (quiz2.size() != 1 || !quiz2.getFirst().equals(question2) 
          || !quiz2.getLast().equals(question2))
      {
        System.out.println("testRemoveLast scenario 3: Your removeLast method did not correctly"
            + " remove the last question when there was more than one question in the list.");
        return false;
      }
    }
    catch(Exception e)
    {
      System.out.println("An unexpected exception was thrown.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /**
   * This method is the tester for ListQuizzer.removeFirst() method
   * @return   true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemoveFirst()
  {
    try
    {
      // scenario 1: trying to remove from an empty list
      boolean test = false;
      try
      {
        ListQuizzer q1 = new ListQuizzer();
        q1.removeLast();
      }
      catch (NoSuchElementException e)
      {
        test = true;
      }
      if (test == false)
      {
        System.out.println("testRemoveFirst scenario 1: Your removeFirst method did not throw"
            + " the correct exception when trying to remove an item from an empty.");
        return false;
      }
      
      // scenario 2: trying to remove from a list with one element
      ListQuizzer quiz = new ListQuizzer(); 
      String title = "Question 1"; // a new title of a question
      String q = "What is 5 divided by 1?"; // a question description
      String[] a = new String[4]; // a new array of question choices
      int correctIndex = 3; // index of the correct answer
      int points = 5; // number of points for the question
      MultipleChoiceQuestion question = 
          new MultipleChoiceQuestion(title, q, a, correctIndex, points);
      // a new multiple choice question with the above information
      quiz.addFirst(question); // add the question to the list
      quiz.removeFirst(); // remove the first (only) element in the list
      if (!quiz.isEmpty())
      {
        System.out.println("testRemoveFirst scenario 2: Your removeFirst method did not correctly"
            + " remove the last question when there was only one question in the list.");
        return false;
      }
      
      // scenario 3: trying to remove from a list with more than one element
      String title2 = "Question 2"; // a new title of a question
      String q2 = "What is 5 divided by 2?"; // a question description
      String[] a2 = new String[4]; // a new array of question choices
      int corrIndex = 3; // index of the correct answer
      int points2 = 5; // number of points for the question
      MultipleChoiceQuestion question2 = 
          new MultipleChoiceQuestion(title2, q2, a2, corrIndex, points2);
      // a new multiple choice question with the above information
      String title3 = "Question 3"; // a new title of a question
      String q3 = "What is 5 divided by 3?"; // a question description
      String[] a3 = new String[4]; // a new array of question choices
      int corrIndex2 = 3; // index of the correct answer
      int points3 = 5; // number of points for the question
      MultipleChoiceQuestion question3 = 
          new MultipleChoiceQuestion(title3, q3, a3, corrIndex2, points3);
      // a new multiple choice question with the above information
      quiz.addFirst(question);
      quiz.addLast(question2);
      quiz.addLast(question3); // add 3 questions to the list
      
      // remove the first question in the list
      quiz.removeFirst();
      
      if (quiz.size() != 2 || !quiz.getFirst().equals(question2) 
          || !quiz.getLast().equals(question3))
      {
        System.out.println("testRemoveFirst scenario 3: Your removeFirst method did not correctly"
            + " remove the last question when there was more than one question in the list.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected error was thrown.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /**
   * This method is the tester for ListQuizzer.remove() method
   * @return   true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemove()
  {
    try
    {
      // scenario 1: index given is out of range
      ListQuizzer quiz = new ListQuizzer(); 
      String title = "Question 1"; // a new title of a question
      String q = "What is 5 divided by 1?"; // a question description
      String[] a = new String[4]; // a new array of question choices
      int correctIndex = 3; // index of the correct answer
      int points = 5; // number of points for the question
      MultipleChoiceQuestion question = 
          new MultipleChoiceQuestion(title, q, a, correctIndex, points);
      // a new multiple choice question with the above information
      boolean test = false; // if test is false after trying the below statement, the expected
      // exception was not thrown properly
      quiz.addFirst(question);
      try
      {
        quiz.remove(-10); // an invalid index given to remove should throw an 
        // IndexOutOfBoundsException
      }
      catch (IndexOutOfBoundsException e)
      {
        test = true; // if the error was thrown, it worked properly
      }
      if (test == false)
      {
        System.out.println("testRemove scenario 1: Your testRemove method did not throw the"
            + " expected exception when given an invalid index.");
        return false;
      }
      
      // scenario 2: index is 0
      String title2 = "Question 2"; // a new title of a question
      String q2 = "What is 5 divided by 2?"; // a question description
      String[] a2 = new String[4]; // a new array of question choices
      int corrIndex = 3; // index of the correct answer
      int points2 = 5; // number of points for the question
      MultipleChoiceQuestion question2 = 
          new MultipleChoiceQuestion(title2, q2, a2, corrIndex, points2);
      // a new multiple choice question with the above information
      quiz.addLast(question2); // add a new question to the end of the list
      quiz.remove(0);
      if ((!quiz.getFirst().equals(question2)) || (quiz.size() != 1) 
          || (!quiz.getLast().equals(question2)))
      {
        System.out.println("testRemove scenario 2: Your testRemove method did not correctly remove"
            + " the item from the list when the index given was 0.");
        return false;
      }
      
      // scenario 3: index is size - 1
      String title3 = "Question 1"; // a new title of a question
      String q3 = "What is 5 divided by 1?"; // a question description
      String[] a3 = new String[4]; // a new array of question choices
      int corrIndex2 = 3; // index of the correct answer
      int points3 = 5; // number of points for the question
      MultipleChoiceQuestion question3 = 
          new MultipleChoiceQuestion(title3, q3, a3, corrIndex2, points3);
      // a new multiple choice question with the above information
      
      quiz.addFirst(question); // 
      quiz.addLast(question3); // add 2 new questions to the end of the list
      
      // remove the element at index size - 1
      quiz.remove(2);
      
      if (!quiz.getFirst().equals(question) || quiz.size() != 2 
          || !quiz.getLast().equals(question2))
      {
        System.out.println("testRemove scenario 3: Your testRemove method did not correctly remove"
            + " the item from the list when the index given was the size of the list minus 1.");
        return false;
      }
      
      // scenario 4: index is in the middle of the list
      quiz.addLast(question3);
      
      //remove the element at index 1 (middle of the list)
      quiz.remove(1);
      
      if (quiz.size() != 2 || !quiz.getFirst().equals(question) || !quiz.getLast().equals(question3))
      {
        System.out.println("testRemove scenario 4: Your testRemove method did not correctly remove"
            + " the item from the list when the index given was in the middle of the list.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected error was thrown.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /**
   * This method checks for the correctness of QuizQuestionsIterator class
   * @return   true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testQuizQuestionsIterator()
  {
    try
    {
      // first check if the constructor throws the correct exception when passed null
      boolean tester = false; // this should be set to true in the catch block if the correct
      // exception is thrown
      try
      {
        QuizQuestionsIterator itr = new QuizQuestionsIterator(null);
      }
      catch (NullPointerException e)
      {
        tester = true;
      }
      if (tester == false)
      {
        System.out.println("Your quizQuestionsIterator constructor did not throw the correct"
            + " exception.");
        return false;
      }
      
      // then, check if next throws the correct exception when there is no next element, and check
      // that hasNext works properly
      String t1 = "1"; // a new title
      String ques = "q"; // a new question
      String[] ans = new String[3]; // a new array of question choices
      int cor = 0; // index of correct answer
      int pts = 5; // number of points for the question
      MultipleChoiceQuestion quest = new MultipleChoiceQuestion(t1, ques, ans, cor, pts); // a new
      // multiple choice question with the above information
      LinkedNode<MultipleChoiceQuestion> n = new LinkedNode<MultipleChoiceQuestion>(quest); // a
      // new node that will be the only node in the list
      QuizQuestionsIterator iterator1 = new QuizQuestionsIterator(n);
      boolean bool = false; // this should get set to true in the catch block
      try
      {
        iterator1.next(); // this should not throw an exception
        if (iterator1.hasNext() != false)
        {
          System.out.println("Your iterator did not correctly return false when there was no next"
              + " element.");
          return false;
        }
        iterator1.next(); // this one should throw an exception
      }
      catch (NoSuchElementException e)
      {
        bool = true;
      }
      if (bool == false)
      {
        System.out.println("Your next method did not throw the correct exception.");
        return false;
      }
      
      // third, test the iterator
      String title = "Question 1"; // a new title of a question
      String q = "What is 5 divided by 1?"; // a question description
      String[] a = new String[4]; // a new array of question choices
      int correctIndex = 3; // index of the correct answer
      int points = 5; // number of points for the question
      MultipleChoiceQuestion question = 
          new MultipleChoiceQuestion(title, q, a, correctIndex, points);
      // a new multiple choice question with the above information
      String title2 = "Question 2"; // a new title of a question
      String q2 = "What is 5 divided by 2?"; // a question description
      String[] a2 = new String[4]; // a new array of question choices
      int correctIndex2 = 3; // index of the correct answer
      int points2 = 5; // number of points for the question
      MultipleChoiceQuestion question2 = 
          new MultipleChoiceQuestion(title2, q2, a2, correctIndex2, points2);
      // a new multiple choice question with the above information
      String title3 = "Question 3"; // a new title of a question
      String q3 = "What is 5 divided by 3?"; // a question description
      String[] a3 = new String[4]; // a new array of question choices
      int corrIndex2 = 3; // index of the correct answer
      int points3 = 5; // number of points for the question
      MultipleChoiceQuestion question3 = 
          new MultipleChoiceQuestion(title3, q3, a3, corrIndex2, points3);
      // a new multiple choice question with the above information
      ListQuizzer list = new ListQuizzer();
      list.addLast(question);
      list.addLast(question2);
      list.addLast(question3);
      LinkedNode<MultipleChoiceQuestion> node3 = new LinkedNode<MultipleChoiceQuestion>(question3);
      // this will be the tail node
      LinkedNode<MultipleChoiceQuestion> node2 = 
          new LinkedNode<MultipleChoiceQuestion>(question2, node3); // this will be the middle node
      LinkedNode<MultipleChoiceQuestion> node1 = 
          new LinkedNode<MultipleChoiceQuestion>(question, node2); // this will be the head node
      
      QuizQuestionsIterator itr = new QuizQuestionsIterator(node1); // a new iterator to iterate
      // through all of the quiz questions
      // try iterating through the three nodes using the iterator
      
      // test node 1
      MultipleChoiceQuestion curr = itr.next(); // stores the next value given by the iterator
      // (in this case, should be node 1)
      if (!curr.equals(node1.getData()))
      {
        System.out.println("Your iterator didn't access the correct node (node 1).");
        return false;
      }
      
      // test node 2
      curr = itr.next(); // stores the next value given by the iterator (node 2 in this case)
      if (!curr.equals(node2.getData()))
      {
        System.out.println("Your iterator didn't access the correct node (node 2).");
        return false;
      }
      
      // test node 3
      curr = itr.next(); // stores the next value given by the iterator (should be node 3 now)
      if (!curr.equals(node3.getData()))
      {
        System.out.println("Your iterator didn't access the correct node (node 3).");
        return false;
      }
      
      // test using a for each loop, seeing if the contents are the same as using the iterator
      QuizQuestionsIterator itr2 = new QuizQuestionsIterator(node1); // a new iterator to iterate
      // through all of the quiz questions
      for (MultipleChoiceQuestion test: list)
      {
        if (itr2.hasNext() && !test.equals(itr2.next()))
        {
          System.out.println("Your iterator didn't access the correct node.");
          return false;
        }
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected error was thrown.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /**
   * This method checks for the correctness of CorrectQuestionsIterator class
   * @return   true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testCorrectQuestionsIterator()
  {
    try
    {
      // first check if the constructor throws the correct exception when passed null
      boolean tester = false; // this should be set to true in the catch block if the correct
      // exception is thrown
      try
      {
        CorrectQuestionsIterator itr = new CorrectQuestionsIterator(null);
      }
      catch (NullPointerException e)
      {
        tester = true;
      }
      if (tester == false)
      {
        System.out.println("Your CorrectQuestionsIterator constructor did not throw the correct"
            + " exception.");
        return false;
      }
      // then, check if next throws the correct exception when there is no next element, and check
      // that hasNext works properly
      String t1 = "1"; // a new title
      String ques = "q"; // a new question
      String[] ans = new String[3]; // a new array of question choices
      int cor = 0; // index of correct answer
      int pts = 5; // number of points for the question
      MultipleChoiceQuestion quest = new MultipleChoiceQuestion(t1, ques, ans, cor, pts); // a new
      // multiple choice question with the above information
      quest.setStudentAnswerIndex(0);
      LinkedNode<MultipleChoiceQuestion> n = new LinkedNode<MultipleChoiceQuestion>(quest); // a
      // new node that will be the only node in the list
      CorrectQuestionsIterator iterator1 = new CorrectQuestionsIterator(n);
      iterator1.next(); // advance to the next element (now the iterator points to null)
      boolean bool = false; // this should get set to true in the catch block
      try
      {
        if (iterator1.hasNext() != false)
        {
          System.out.println("Your Citerator did not correctly return false when there was no next"
              + " element.");
          return false;
        }
        iterator1.next(); // this one should throw an exception
      }
      catch (NoSuchElementException e)
      {
        bool = true;
      }
      if (bool == false)
      {
        System.out.println("Your next method did not throw the correct exception.");
        return false;
      }
      
      // third, test the iterator
      String title = "Question 1"; // a new title of a question
      String q = "What is 5 divided by 1?"; // a question description
      String[] a = new String[4]; // a new array of question choices
      int correctIndex = 3; // index of the correct answer
      int points = 5; // number of points for the question
      MultipleChoiceQuestion question = 
          new MultipleChoiceQuestion(title, q, a, correctIndex, points);
      // a new multiple choice question with the above information
      String title2 = "Question 2"; // a new title of a question
      String q2 = "What is 5 divided by 2?"; // a question description
      String[] a2 = new String[4]; // a new array of question choices
      int correctIndex2 = 3; // index of the correct answer
      int points2 = 5; // number of points for the question
      MultipleChoiceQuestion question2 = 
          new MultipleChoiceQuestion(title2, q2, a2, correctIndex2, points2);
      // a new multiple choice question with the above information
      question2.setStudentAnswerIndex(3); // make the question correct
      String title3 = "Question 3"; // a new title of a question
      String q3 = "What is 5 divided by 3?"; // a question description
      String[] a3 = new String[4]; // a new array of question choices
      int corrIndex2 = 3; // index of the correct answer
      int points3 = 5; // number of points for the question
      MultipleChoiceQuestion question3 = 
          new MultipleChoiceQuestion(title3, q3, a3, corrIndex2, points3);
      // a new multiple choice question with the above information
      
      ListQuizzer list = new ListQuizzer(); // this will store all of the correct questions
      list.addLast(question2); // (correct)
      
      LinkedNode<MultipleChoiceQuestion> node3 = new LinkedNode<MultipleChoiceQuestion>(question3);
      // this will be the tail node
      LinkedNode<MultipleChoiceQuestion> node2 = 
          new LinkedNode<MultipleChoiceQuestion>(question2, node3); // this will be the middle node
      LinkedNode<MultipleChoiceQuestion> node1 = 
          new LinkedNode<MultipleChoiceQuestion>(question, node2); // this will be the head node
      
      CorrectQuestionsIterator itr = new CorrectQuestionsIterator(node1); // a new iterator to
      // iterate through all of the correct quiz questions
      
      for (MultipleChoiceQuestion test: list)
      {
        if (itr.hasNext() && !test.equals(itr.next()))
        {
          System.out.println("Your iterator didn't access the correct node.");
          return false;
        }
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected error was thrown.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /**
   * This method checks for the correctness of IncorrectQuestionsIterator class
   * @return   true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testInCorrectQuestionsIterator()
  {
    try
    {
      // first check if the constructor throws the correct exception when passed null
      boolean tester = false; // this should be set to true in the catch block if the correct
      // exception is thrown
      try
      {
        CorrectQuestionsIterator itr = new CorrectQuestionsIterator(null);
      }
      catch (NullPointerException e)
      {
        tester = true;
      }
      if (tester == false)
      {
        System.out.println("Your IncorrectQuestionsIterator constructor did not throw the correct"
            + " exception.");
        return false;
      }
      
      // then, check if next throws the correct exception when there is no next element, and check
      // that hasNext works properly
      String t1 = "1"; // a new title
      String ques = "q"; // a new question
      String[] ans = new String[3]; // a new array of question choices
      int cor = 0; // index of correct answer
      int pts = 5; // number of points for the question
      MultipleChoiceQuestion quest = new MultipleChoiceQuestion(t1, ques, ans, cor, pts); // a new
      // multiple choice question with the above information
      quest.setStudentAnswerIndex(0);
      LinkedNode<MultipleChoiceQuestion> n = new LinkedNode<MultipleChoiceQuestion>(quest); // a
      // new node that will be the only node in the list
      IncorrectQuestionsIterator iterator1 = new IncorrectQuestionsIterator(n);
      boolean bool = false; // this should get set to true in the catch block
      try
      {
        if (iterator1.hasNext() != false)
        {
          System.out.println("Your Iiterator did not correctly return false when there was no next"
              + " element.");
          return false;
        }
        iterator1.next(); // this one should throw an exception
      }
      catch (NoSuchElementException e)
      {
        bool = true;
      }
      if (bool == false)
      {
        System.out.println("Your next method did not throw the correct exception.");
        return false;
      }
      
      // third, test the iterator
      String title = "Question 1"; // a new title of a question
      String q = "What is 5 divided by 1?"; // a question description
      String[] a = new String[4]; // a new array of question choices
      int correctIndex = 3; // index of the correct answer
      int points = 5; // number of points for the question
      MultipleChoiceQuestion question = 
          new MultipleChoiceQuestion(title, q, a, correctIndex, points);
      // a new multiple choice question with the above information
      question.setStudentAnswerIndex(3); // make question correct
      String title2 = "Question 2"; // a new title of a question
      String q2 = "What is 5 divided by 2?"; // a question description
      String[] a2 = new String[4]; // a new array of question choices
      int correctIndex2 = 3; // index of the correct answer
      int points2 = 5; // number of points for the question
      MultipleChoiceQuestion question2 = 
          new MultipleChoiceQuestion(title2, q2, a2, correctIndex2, points2);
      // a new multiple choice question with the above information
      question2.setStudentAnswerIndex(3); // make question correct
      String title3 = "Question 3"; // a new title of a question
      String q3 = "What is 5 divided by 3?"; // a question description
      String[] a3 = new String[4]; // a new array of question choices
      int corrIndex3 = 3; // index of the correct answer
      int points3 = 5; // number of points for the question
      MultipleChoiceQuestion question3 = 
          new MultipleChoiceQuestion(title3, q3, a3, corrIndex3, points3);
      // a new multiple choice question with the above information
      question3.setStudentAnswerIndex(1); // make question incorrect
      ListQuizzer list = new ListQuizzer();
      list.addLast(question3); // (incorrect)
      LinkedNode<MultipleChoiceQuestion> node3 = new LinkedNode<MultipleChoiceQuestion>(question3);
      // this will be the tail node
      LinkedNode<MultipleChoiceQuestion> node2 = 
          new LinkedNode<MultipleChoiceQuestion>(question2, node3); // this will be the middle node
      LinkedNode<MultipleChoiceQuestion> node1 = 
          new LinkedNode<MultipleChoiceQuestion>(question, node2); // this will be the head node
      
      IncorrectQuestionsIterator itr = new IncorrectQuestionsIterator(node1); // a new iterator to
      // iterate through all of the quiz questions
      for (MultipleChoiceQuestion test: list)
      {
        if (itr.hasNext() && !test.equals(itr.next()))
        {
          System.out.println("Your iterator didn't access the correct node.");
          return false;
        }
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected error was thrown.");
      return false;
    }
    return true; // return true if all tests pass
  }
  
  /**
   * Main method for the QuizzerTester class
   * @param args   list of input arguments if any
   */
  public static void main(String[] args) 
  {
    System.out.println("testMultipleChoiceQuestion: " + testMultipleChoiceQuestion());
    System.out.println("testLinkedNode: " + testLinkedNode());
    System.out.println("testAddFirst: " + testAddFirst());
    System.out.println("testAddLast: " + testAddLast());
    System.out.println("testAdd: " + testAdd());
    System.out.println("testRemoveLast: " + testRemoveLast());
    System.out.println("testRemoveFirst: " + testRemoveFirst());
    System.out.println("testRemove: " + testRemove());
    System.out.println("testQuizQuestionsIterator: " + testQuizQuestionsIterator());
    System.out.println("testCorrectQuestionsIterator: " + testCorrectQuestionsIterator());
    System.out.println("testInCorrectQuestionsIterator: " + testInCorrectQuestionsIterator());
    System.out.println("runAllTests: " + runAllTests());
  }
}
