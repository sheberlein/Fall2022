//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P09 Chugimon - ChugidexTester class
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

import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the methods defined in the Chugimon
 * and ChugiTree classes.
 * 
 * @author Sidney Heberlein
 *
 */
public class ChugidexTester 
{
  /**
   * Checks the correctness of the implementation of both compareTo() and equals() methods defined
   * in the Chugimon class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testChugimonCompareToEquals() 
  {
    try
    {
      // testing compareTo method
      // scenario 1: testing two Chugimon with different names and first and second IDs
      Chugimon chugi = new Chugimon(2, 100); // Ivyor
      Chugimon mon = new Chugimon (3, 99); // Venuler
      if (chugi.compareTo(mon) >= 0)
      {
        System.out.println("testChugimonCompareToEquals: compareTo scenario 1: Your compareTo "
            + "method did not return a negative integer.");
        return false;
      }
      
      // scenario 2: testing two Chugimon with the same name but different fist and second IDs
      Chugimon cha = new Chugimon(4, 25);
      Chugimon two = new Chugimon(6, 26); // these two Chugimon should have the same name
      if (cha.compareTo(two) >= 0)
      {
        System.out.println("testChugimonCompareToEquals: compareTo scenario 2: Your compareTo "
            + "method did not return a negative integer.");
        return false;
      }
      
      // scenario 3: testing two Chugimon with the same name and same first ID
      Chugimon cbat = new Chugimon(4, 42);
      Chugimon cbat2 = new Chugimon(4, 43); // these are both Charbat, and have the same first ID
      if (cbat.compareTo(cbat2) >= 0)
      {
        System.out.println("testChugimonCompareToEquals: compareTo scenario 3: Your compareTo "
            + "method did not return a negative number.");
        return false;
      }
      
      // scenario 4: testing two identical Chugimon
      Chugimon id = new Chugimon(2, 100);
      Chugimon id2 = new Chugimon(2, 100); // these two Chugimon are the same
      if (id.compareTo(id2) != 0)
      {
        System.out.println("testChugimonCompareToEquals: compareTo scenario 4: Your compareTo "
            + "method did not return zero.");
        return false;
      }
      
      // testing equals method
      // scenario 1: two identical Chugimon
      Chugimon id3 = new Chugimon(2, 100);
      Chugimon id4 = new Chugimon(2, 100); // these two Chugimon are the same
      if (!id3.equals(id4))
      {
        System.out.println("testChugimonCompareToEquals: equals scenario 1: Your equals method did"
            + " not return true when the pokemon were equal.");
        return false;
      }
      
      // scenario 2: two different Chugimon
      Chugimon id5 = new Chugimon(1, 50);
      if (id3.equals(id5))
      {
        System.out.println("testChugimonCompareToEquals: equals scenario 2: Your equals method did"
            + " not return true when the pokemon were not equal.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected error ocurred in testChugimonCompareToEquals.");
      return false;
    }
    return true; // return true if all tests pass
  }

  /**
   * Checks the correctness of the implementation of Chugimon.toString() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testChugimonToString() 
  {
    try
    {
      Chugimon cha = new Chugimon(3, 6);
      String name = cha.getName();
      String expected = name + "#3.6";
      if (!cha.toString().equals(expected))
      {
        System.out.println("testChugimonToString: Your toString method did not return the correct "
            + "String.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("testChugimonToString: an unexpected error was thrown.");
      return false;
    }
    return true; // return true if all tests pass
  }

  /**
   * Checks the correctness of the implementation of ChugiTree.isValidBSTHelper() method. This
   * tester should consider at least three scenarios. (1) An empty tree whose root is null should be
   * a valid BST. (2) Consider a valid BST whose height is at least 3. Create the tree using the
   * constructors of the BSTNode and its setters methods, (3) Consider a NON-valid BST where the
   * search order property is violated at at least one internal node.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testIsValidBSTHelper() 
  {
    try
    {
      // scenario 1: an empty tree whose root is null
      if (!ChugiTree.isValidBSTHelper(null))
      {
        System.out.println("testIsValidBSTHelper: scenario 1: Your isValidBSTHelper method did not "
            + "return true when passed a null root.");
        return false;
      }
      
      // scenario 2: a valid BST whose height is at least 3
      // construct a BST with height 3
      BSTNode<Chugimon> six = new BSTNode<Chugimon>(new Chugimon(22, 3)); // the root of the tree
      BSTNode<Chugimon> three = new BSTNode<Chugimon>(new Chugimon(10, 2)); // the root's left node
      six.setLeft(three);
      BSTNode<Chugimon> nine = new BSTNode<Chugimon>(new Chugimon(2, 7)); // the root's right node
      six.setRight(nine);
      BSTNode<Chugimon> two = new BSTNode<Chugimon>(new Chugimon(1, 2)); // three's left node
      three.setLeft(two);
      BSTNode<Chugimon> five = new BSTNode<Chugimon>(new Chugimon(23, 9)); // three's right node
      three.setRight(five);
      BSTNode<Chugimon> seven = new BSTNode<Chugimon>(new Chugimon(42, 47)); // nine's left node
      nine.setLeft(seven);
      BSTNode<Chugimon> eleven = new BSTNode<Chugimon>(new Chugimon(64, 15)); // nine's right node
      nine.setRight(eleven);
      if (!ChugiTree.isValidBSTHelper(six))
      {
        System.out.println("testIsValidBSTHelper: scenario 2: Your isValidBSTHelper method did not "
            + "return true when the tree was valid.");
        return false;
      }
      
      // scenario 3: an invalid BST
      BSTNode<Chugimon> f = new BSTNode<Chugimon>(new Chugimon(23, 9)); // the root of the tree
      BSTNode<Chugimon> t = new BSTNode<Chugimon>(new Chugimon(1, 2)); // f's left node
      f.setLeft(t);
      BSTNode<Chugimon> e = new BSTNode<Chugimon>(new Chugimon(42, 47)); // f's right node
      f.setRight(e);
      BSTNode<Chugimon> fo = new BSTNode<Chugimon>(new Chugimon(50, 52)); // e's left node
      e.setLeft(fo);
      BSTNode<Chugimon> n = new BSTNode<Chugimon>(new Chugimon(2, 7)); // e's right node
      e.setRight(n);
      if (ChugiTree.isValidBSTHelper(f))
      {
        System.out.println("testIsValidBSTHelper: scenario 3: Your isValidBSTHelper method did not "
            + "return false when the tree was not valid.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected error was thrown in testIsValidBSTHelper.");
      return false;
    }
    return true; // return true if all tests pass
  }

  /**
   * Checks the correctness of the implementation of both add() and toString() methods implemented
   * in the ChugiTree class. This unit test considers at least the following scenarios. (1) Create a
   * new empty ChugiTree, and check that its size is 0, it is empty, and that its string
   * representation is an empty string "". (2) try adding one Chugimon and then check that the add()
   * method call returns true, the tree is not empty, its size is 1, and the toString() called on
   * the tree returns the expected output. (3) Try adding another Chugimon which is less than the
   * Chugimon at the root, (4) Try adding a third Chugimon which is greater than the one at the
   * root, (5) Try adding at least two further Chugimons such that one must be added at the left
   * subtree, and the other at the right subtree. For all the above scenarios, and more, double
   * check each time that size() method returns the expected value, the add method call returns
   * true, that the ChugiTree is a valid BST, and that the toString() method returns the expected
   * string representation of the contents of the binary search tree in an increasing order from the
   * smallest to the greatest Chugimon. (6) Try adding a Chugimon already stored in the tree. Make
   * sure that the add() method call returned false, and that the size of the tree did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddToStringSize() 
  {
    try
    {
      // scenario 1: an empty ChugiTree
      ChugiTree tree = new ChugiTree();
      if (tree.size() != 0)
      {
        System.out.println("testAddToStringSize: scenario 1: Your size method did not return 0 when"
            + " the tree was empty.");
        return false;
      }
      if (!tree.isEmpty())
      {
        System.out.println("testAddToStringSize: scenario 1: Your isEmpty method did not return"
            + " true when the tree was empty.");
        return false;
      }
      if (!tree.toString().equals(""))
      {
        System.out.println("testAddToStringSize: scenario 1: Your toString method did not return a"
            + " blank string when the tree was empty.");
        return false;
      } 
      if (!tree.isValidBST())
      {
        System.out.println("testAddToStringSize: scenario 1: Your BST is not valid.");
        return false;
      }
      
      // scenario 2: one Chugimon - test that the add method call returns true, the tree is not 
      // empty, its size is 1, and toString returns the expected output.
      boolean tryAdd = tree.add(new Chugimon(22, 3)); // this Chugimon is Fearusaur, it will be the
      // root of the tree
      if (tree.isEmpty())
      {
        System.out.println("testAddToStringSize: scenario 2: Your isEmpty method did not return "
            + "false when the tree was not empty.");
        return false;
      }
      if (tree.size() != 1)
      {
        System.out.println("testAddToStringSize: scenario 2: Your size method did not return 1 when"
            + " the tree had one node.");
        return false;
      }
      if (tryAdd == false)
      {
        System.out.println("testAddToStringSize: scenario 2: Your add method did not return true "
            + "when a valid Chugimon was supposed to be added.");
        return false;
      }
      if (!tree.toString().equals("Fearusaur#22.3"))
      {
        System.out.println("testAddToStringSize: scenario 2: Your toString method did not return "
            + "the correct string when a Fearusaur was added to the tree.");
        return false;
      }
      if (!tree.isValidBST())
      {
        System.out.println("testAddToStringSize: scenario 2: Your BST is not valid.");
        return false;
      }
      
      // scenario 3: adding another Chugimon which is less than the root Chugimon
      tryAdd = tree.add(new Chugimon(10, 2)); // this Chugimon is Caterysaur. It will be the root's
      // left node.
      if (tree.isEmpty())
      {
        System.out.println("testAddToStringSize: scenario 3: Your isEmpty method did not return "
            + "false when the tree was not empty.");
        return false;
      }
      if (tree.size() != 2)
      {
        System.out.println("testAddToStringSize: scenario 3: Your size method did not return 2 when"
            + " the tree had two nodes.");
        return false;
      }
      if (tryAdd == false)
      {
        System.out.println("testAddToStringSize: scenario 3: Your add method did not return true "
            + "when a valid Chugimon was supposed to be added.");
        return false;
      }
      if (!tree.toString().equals("Caterysaur#10.2,Fearusaur#22.3"))
      {
        System.out.println("testAddToStringSize: scenario 3: Your toString method did not return "
            + "the correct string when a Caterysaur was added to the tree.");
        return false;
      }
      if (!tree.isValidBST())
      {
        System.out.println("testAddToStringSize: scenario 3: Your BST is not valid.");
        return false;
      }
      
      // scenario 4: adding a third Chugimon which is greater than the root Chugimon
      tryAdd = tree.add(new Chugimon(2, 7)); // this Chugimon is Ivytle. it will be the root's
      // right node.
      if (tree.isEmpty())
      {
        System.out.println("testAddToStringSize: scenario 4: Your isEmpty method did not return "
            + "false when the tree was not empty.");
        return false;
      }
      if (tree.size() != 3)
      {
        System.out.println("testAddToStringSize: scenario 4: Your size method did not return 3 when"
            + " the tree had three nodes.");
        return false;
      }
      if (tryAdd == false)
      {
        System.out.println("testAddToStringSize: scenario 4: Your add method did not return true "
            + "when a valid Chugimon was supposed to be added.");
        return false;
      }
      if (!tree.toString().equals("Caterysaur#10.2,Fearusaur#22.3,Ivytle#2.7"))
      {
        System.out.println("testAddToStringSize: scenario 4: Your toString method did not return "
            + "the correct string when an Ivytle was added to the tree.");
        return false;
      }
      if (!tree.isValidBST())
      {
        System.out.println("testAddToStringSize: scenario 4: Your BST is not valid.");
        return false;
      }
      
      // scenario 5: adding 2 more Chugimon, one at the left subtree and one at the right subtree
      tryAdd = tree.add(new Chugimon(23, 9)); // this Chugimon is Ektoise. It will be the right 
      // node of the left node of the root.
      if (tree.isEmpty())
      {
        System.out.println("testAddToStringSize: scenario 5: Your isEmpty method did not return "
            + "false when the tree was not empty.");
        return false;
      }
      if (tree.size() != 4)
      {
        System.out.println("testAddToStringSize: scenario 5: Your size method did not return 4 when"
            + " the tree had four nodes.");
        return false;
      }
      if (tryAdd == false)
      {
        System.out.println("testAddToStringSize: scenario 5: Your add method did not return true "
            + "when a valid Chugimon was supposed to be added.");
        return false;
      }
      if (!tree.toString().equals("Caterysaur#10.2,Ektoise#23.9,Fearusaur#22.3,Ivytle#2.7"))
      {
        System.out.println("testAddToStringSize: scenario 5: Your toString method did not return "
            + "the correct string when an Ektoise was added to the tree.");
        return false;
      }
      if (!tree.isValidBST())
      {
        System.out.println("testAddToStringSize: scenario 5: Your BST is not valid.");
        return false;
      }
      tryAdd = tree.add(new Chugimon(42, 47)); // this Chugimon is Golsect. it will be the left
      // node of the right node of the root.
      if (tree.isEmpty())
      {
        System.out.println("testAddToStringSize: scenario 5: Your isEmpty method did not return "
            + "false when the tree was not empty.");
        return false;
      }
      if (tree.size() != 5)
      {
        System.out.println("testAddToStringSize: scenario 5: Your size method did not return 5 when"
            + " the tree had five nodes.");
        return false;
      }
      if (tryAdd == false)
      {
        System.out.println("testAddToStringSize: scenario 5: Your add method did not return true "
            + "when a valid Chugimon was supposed to be added.");
        return false;
      }
      if (!tree.toString().equals("Caterysaur#10.2,Ektoise#23.9,"
          + "Fearusaur#22.3,Golsect#42.47,Ivytle#2.7"))
      {
        System.out.println("testAddToStringSize: scenario 5: Your toString method did not return "
            + "the correct string when a Golsect was added to the tree.");
        return false;
      }
      if (!tree.isValidBST())
      {
        System.out.println("testAddToStringSize: scenario 5: Your BST is not valid.");
        return false;
      }
      
      // scenario 6: adding a Chugimon already stored in the tree - add should return false, size 
      // should not change
      tryAdd = tree.add(new Chugimon(42, 47)); // this Chugimon is Golsect, and it already exists
      // in the tree.
      if (tree.isEmpty())
      {
        System.out.println("testAddToStringSize: scenario 6: Your isEmpty method did not return "
            + "false when the tree was not empty.");
        return false;
      }
      if (tree.size() != 5)
      {
        System.out.println("testAddToStringSize: scenario 6: Your size method did not return 5 when"
            + " the tree had five nodes.");
        return false;
      }
      if (tryAdd == true)
      {
        System.out.println("testAddToStringSize: scenario 6: Your add method did not return false "
            + "when an ivalid Chugimon was not supposed to be added.");
        return false;
      }
      if (!tree.toString().equals("Caterysaur#10.2,Ektoise#23.9,"
          + "Fearusaur#22.3,Golsect#42.47,Ivytle#2.7"))
      {
        System.out.println("testAddToStringSize: scenario 6: Your toString method did not return "
            + "the correct string when no Chugimon was added to the tree.");
        return false;
      }
      if (!tree.isValidBST())
      {
        System.out.println("testAddToStringSize: scenario 6: Your BST is not valid.");
        return false;
      } 
    }
    catch (Exception e)
    {
      System.out.println("An unexpected error was thrown in testAddToStringSize.");
      return false;
    }
    return true;
  }

  /**
   * This method checks mainly for the correctness of the ChugiTree.lookup() method. It must
   * consider at least the following test scenarios. (1) Create a new ChugiTree. Then, check that
   * calling the lookup() method on an empty ChugiTree returns false. (2) Consider a ChugiTree of
   * height 3 which contains at least 5 Chugimons. Then, try to call lookup() method to search for a
   * Chugimon having a match at the root of the tree. (3) Then, search for a Chugimon at the right
   * and left subtrees at different levels considering successful and unsuccessful search
   * operations. Make sure that the lookup() method returns the expected output for every method
   * call.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookup() 
  {
    try
    {
      // scenario 1: create a new ChugiTree, and check that calling lookup on an empty tree = null.
      ChugiTree tree = new ChugiTree();
      if (tree.lookup(11, 2) != null)
      {
        System.out.println("testLookup: scenario 1: lookup on an empty tree did not return null.");
        return false;
      }
      
      // scenario 2: a ChugiTree of height 3 which contains at least 5 Chugimon. Search for a 
      // Chugimon matching the root of the tree.
      // construct a BST with height 3, containing 7 nodes
      BSTNode<Chugimon> six = new BSTNode<Chugimon>(new Chugimon(22, 3)); // the root of the tree
      BSTNode<Chugimon> three = new BSTNode<Chugimon>(new Chugimon(10, 2)); // the root's left node
      six.setLeft(three);
      BSTNode<Chugimon> nine = new BSTNode<Chugimon>(new Chugimon(2, 7)); // the root's right node
      six.setRight(nine);
      BSTNode<Chugimon> two = new BSTNode<Chugimon>(new Chugimon(1, 2)); // three's left node
      three.setLeft(two);
      BSTNode<Chugimon> five = new BSTNode<Chugimon>(new Chugimon(23, 9)); // three's right node
      three.setRight(five);
      BSTNode<Chugimon> seven = new BSTNode<Chugimon>(new Chugimon(42, 47)); // nine's left node
      nine.setLeft(seven);
      BSTNode<Chugimon> eleven = new BSTNode<Chugimon>(new Chugimon(64, 15)); // nine's right node
      nine.setRight(eleven);
      // add the 7 nodes to the tree
      tree.add(six.getData());
      tree.add(three.getData());
      tree.add(nine.getData());
      tree.add(two.getData());
      tree.add(five.getData());
      tree.add(seven.getData());
      tree.add(eleven.getData());
      
      Chugimon found = tree.lookup(22, 3); // looking up the root of the tree
      if (!found.equals(six.getData()))
      {
        System.out.println("testLookup: scenario 2: lookup to find the root did not return the "
            + "correct Chugimon.");
        return false;
      }
      
      // scenario 3: search for a Chugimon at the right subtree that exists
      found = tree.lookup(42, 47);
      if (!found.equals(seven.getData()))
      {
        System.out.println("testLookup: scenario 3: lookup to find a Chugimon on the right subtree"
            + " that existed did not return the correct Chugimon.");
        return false;
      }
      
      // scenario 4: search for a Chugimon at the right subtree that doesn't exist
      found = tree.lookup(42, 48); // this Chugimon would be on the right subtree if it existed
      if (found != null)
      {
        System.out.println("testLookup: scenario 4: lookup to find a Chugimon on the right subtree"
            + " that did not exist did not return null.");
        return false;
      }
      
      // scenario 5: search for a Chugimon at the left subtree that exists
      found = tree.lookup(10, 2);
      if (!found.equals(three.getData()))
      {
        System.out.println("testLookup: scenario 5: lookup to find a Chugimon on the left subtree"
            + " that existed did not return the correct Chugimon.");
        return false;
      }
      
      // scenario 6: search for a Chugimon at the left subtree that doesn't exist
      found = tree.lookup(10, 3); // this Chugimon would be on the left subtree if it existed
      if (found != null)
      {
        System.out.println("testLookup: scenario 6: lookup to find a Chugimon on the left subtree"
            + " that did not exist did not return null.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected exception was thrown in testLookup.");
      return false;
    }
    return true; // return true if all tests pass
  }

  /**
   * Checks for the correctness of ChugiTree.countType() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testCountType() 
  {
    try
    {
      // scenario 1: testing when there are no pokemon of a given type
      ChugiTree tree = new ChugiTree();
      BSTNode<Chugimon> six = new BSTNode<Chugimon>(new Chugimon(22, 3)); // the root of the tree
      BSTNode<Chugimon> three = new BSTNode<Chugimon>(new Chugimon(10, 2)); // the root's left node
      six.setLeft(three);
      BSTNode<Chugimon> nine = new BSTNode<Chugimon>(new Chugimon(2, 7)); // the root's right node
      six.setRight(nine);
      tree.add(six.getData());
      tree.add(three.getData());
      tree.add(nine.getData());
      int num = tree.countType(ChugiType.DRAGON); // the number of Dragon types in the tree
      if (num != 0)
      {
        System.out.println("testCountType: scenario 1: Your countType method did not return 0 when "
            + "there were no Dragon types in the tree.");
        return false;
      }
      
      // scenario 2: testing when there are pokemon of a given type
      num = tree.countType(ChugiType.GRASS); // the number of Grass types in the tree
      if (num != 3)
      {
        System.out.println("testCountType: scenario 3: Your countType method did not return 3 when "
            + "there were 3 Grass types in the tree.");
        return false;
      }
    }
    catch(Exception e)
    {
      System.out.println("An unexpected error was thrown in testCountTypes.");
    }
    return true; // return true if all tests pass
  }
  
  /**
   * Checks for the correctness of ChugiTree.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty Chugimon tree is zero. (2) ensures
   * that the height of a tree which consists of only one node is 1. (3) ensures that the height of
   * a ChugiTree with four levels for instance, is 4.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() 
  {
    try
    {
   // scenario 1: height of an empty tree
      ChugiTree tree = new ChugiTree();
      if (tree.height() != 0)
      {
        System.out.println("testHeight: scenario 1: Your height method did not return 0 when there "
            + "were no nodes in the tree.");
        return false;
      }
      
      // scenario 2: height on a tree with only one node
      BSTNode<Chugimon> six = new BSTNode<Chugimon>(new Chugimon(22, 3)); // the root of the tree
      tree.add(six.getData());
      if (tree.height() != 1)
      {
        System.out.println("testHeight: scenario 2: Your height method did not return 1 when there "
            + "was one node in the tree.");
        return false;
      }
      
      // scenario 3: height on a tree with 3 levels
      // add 6 more nodes to the tree
      BSTNode<Chugimon> three = new BSTNode<Chugimon>(new Chugimon(10, 2)); // the root's left node
      six.setLeft(three);
      BSTNode<Chugimon> nine = new BSTNode<Chugimon>(new Chugimon(2, 7)); // the root's right node
      six.setRight(nine);
      BSTNode<Chugimon> two = new BSTNode<Chugimon>(new Chugimon(1, 2)); // three's left node
      three.setLeft(two);
      BSTNode<Chugimon> five = new BSTNode<Chugimon>(new Chugimon(23, 9)); // three's right node
      three.setRight(five);
      BSTNode<Chugimon> seven = new BSTNode<Chugimon>(new Chugimon(42, 47)); // nine's left node
      nine.setLeft(seven);
      BSTNode<Chugimon> eleven = new BSTNode<Chugimon>(new Chugimon(64, 15)); // nine's right node
      nine.setRight(eleven);
      // add the 6 nodes to the tree
      tree.add(three.getData());
      tree.add(nine.getData());
      tree.add(two.getData());
      tree.add(five.getData());
      tree.add(seven.getData());
      tree.add(eleven.getData());
      if (tree.height() != 3)
      {
        System.out.println("testHeight: scenario 3: Your height method did not return 3 when there "
            + "were 3 levels in the tree.");
        return false;
      }
      
      // scenario 4: a tree with 4 levels
      tree.add(new Chugimon(3, 78)); // adds a new Venudash - should be eleven's right node
      if (tree.height() != 4)
      {
        System.out.println("testHeight: scenario 4: Your height method did not return 4 when there "
            + "were 4 levels in the tree.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected error was thrown in testHeight.");
    }
    return true; // return true if all tests pass
  }

  /**
   * Checks for the correctness of ChugiTree.getFirst() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetFirst() 
  {
    // scenario 1: test an empty tree
    ChugiTree tree = new ChugiTree();
    if (tree.getFirst() != null)
    {
      System.out.println("testGetFirst: scenario 1: your getFirst method did not return null when "
          + "called on an empty tree.");
      return false;
    }
    
    // scenario 2: test on a tree with one node
    Chugimon root = new Chugimon(22, 3);
    tree.add(root); // add a new Chugimon to the root of the tree
    if (!tree.getFirst().equals(root))
    {
      System.out.println("testGetFirst: scenario 2: your getFirst method did not return the correct"
          + " Chugimon when the tree had one node.");
      return false;
    }
    
    // scenario 3: test on a tree with multiple nodes
    Chugimon three = new Chugimon(10, 2); // the root's left node
    Chugimon nine = new Chugimon(2, 7); // the root's right node
    Chugimon two = new Chugimon(1, 2); // three's left node
    Chugimon five = new Chugimon(23, 9); // three's right node
    Chugimon seven = new Chugimon(42, 47); // nine's left node
    Chugimon eleven = new Chugimon(64, 15); // nine's right node
    // add the 6 Chugimon to the tree
    tree.add(three);
    tree.add(nine);
    tree.add(two);
    tree.add(five);
    tree.add(seven);
    tree.add(eleven);
    if (!tree.getFirst().equals(two))
    {
      System.out.println("testGetFirst: scenario 3: your getFirst method did not return the correct"
          + " Chugimon when the tree had multiple nodes.");
      return false;
    }
    return true; // return true if all tests pass
  }

  /**
   * Checks for the correctness of ChugiTree.getLast() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetLast() 
  {
    try
    {
      // scenario 1: test an empty tree
      ChugiTree tree = new ChugiTree();
      if (tree.getFirst() != null)
      {
        System.out.println("testGetLast: scenario 1: your getLast method did not return null when"
            + " called on an empty tree.");
        return false;
      }
      
      // scenario 2: test on a tree with one node
      Chugimon root = new Chugimon(22, 3);
      tree.add(root); // add a new Chugimon to the root of the tree
      if (!tree.getLast().equals(root))
      {
        System.out.println("testGetLast: scenario 2: your getLast method did not return the "
            + "correct Chugimon when the tree had one node.");
        return false;
      }
      
      // scenario 3: test on a tree with multiple nodes
      Chugimon three = new Chugimon(10, 2); // the root's left node
      Chugimon nine = new Chugimon(2, 7); // the root's right node
      Chugimon two = new Chugimon(1, 2); // three's left node
      Chugimon five = new Chugimon(23, 9); // three's right node
      Chugimon seven = new Chugimon(42, 47); // nine's left node
      Chugimon eleven = new Chugimon(64, 15); // nine's right node
      // add the 6 Chugimon to the tree
      tree.add(three);
      tree.add(nine);
      tree.add(two);
      tree.add(five);
      tree.add(seven);
      tree.add(eleven);
      if (!tree.getLast().equals(eleven))
      {
        System.out.println("testGetLast: scenario 3: your getLast method did not return the "
            + "correct Chugimon when the tree had multiple nodes.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected error was thrown in testGetLast.");
      return false;
    }
    return true; // return true if all tests pass
  }

  /**
   * Checks for the correctness of ChugiTree.delete() method. This test must consider at least 3
   * test scenarios. (1) Remove a Chugimon that is at leaf node (2) Remove a Chugimon at non-leaf
   * node. For each of these scenarios, check that the size of the tree was decremented by one and
   * that the resulting ChugiTree is a valid BST, (3) ensures that the ChugiTree.delete() method
   * returns false when called on an Chugimon that is not present in the BST.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testDelete() 
  {
    try
    {
      // scenario 1: remove a Chugimon that is at a leaf node
      ChugiTree tree = new ChugiTree();
      Chugimon root = new Chugimon(22, 3);
      Chugimon three = new Chugimon(10, 2); // the root's left node
      Chugimon nine = new Chugimon(2, 7); // the root's right node
      Chugimon two = new Chugimon(1, 2); // three's left node
      Chugimon five = new Chugimon(23, 9); // three's right node
      Chugimon seven = new Chugimon(42, 47); // nine's left node
      Chugimon eleven = new Chugimon(64, 15); // nine's right node
      // add the 6 Chugimon to the tree
      tree.add(root);
      tree.add(three);
      tree.add(nine);
      tree.add(two);
      tree.add(five);
      tree.add(seven);
      tree.add(eleven);
      boolean deleted = tree.delete(eleven); // eleven is a leaf node on the right side of the tree
      if (tree.size() != 6)
      {
        System.out.println("testDelete: scenario 1: Your delete method did not correctly update the"
            + " size of the tree.");
        return false;
      }
      if (!tree.isValidBST())
      {
        System.out.println("testDelete: scenario 1: Your delete method did not correctly keep the"
            + " tree a valid BST.");
        return false;
      }
      if (deleted == false)
      {
        System.out.println("testDelete: scenario 1: Your delete method did not correctly return "
            + "true when a valid Chugimon was deleted.");
        return false;
      }
      
      // scenario 2: remove a Chugimon at a non-leaf node
      deleted = tree.delete(three); // three is the root's left node, which is not a leaf node
      if (tree.size() != 5)
      {
        System.out.println("testDelete: scenario 2: Your delete method did not correctly update the"
            + " size of the tree.");
        return false;
      }
      if (!tree.isValidBST())
      {
        System.out.println("testDelete: scenario 2: Your delete method did not correctly keep the"
            + " tree a valid BST.");
        return false;
      }
      if (deleted == false)
      {
        System.out.println("testDelete: scenario 2: Your delete method did not correctly return "
            + "true when a valid Chugimon was deleted.");
        return false;
      }
      
      // scenario 3: ensure that the Chugitree.delete() method returns false when called on a 
      // Chugimon that is not present in the BST.
      deleted = tree.delete(new Chugimon(3, 18)); // this Chugimon is not in the tree
      if (tree.size() != 5)
      {
        System.out.println("testDelete: scenario 3: Your delete method did not correctly update the"
            + " size of the tree when an invalid Chugimon tried to be deleted.");
        return false;
      }
      if (!tree.isValidBST())
      {
        System.out.println("testDelete: scenario 3: Your delete method did not correctly keep the"
            + " tree a valid BST.");
        return false;
      }
      if (deleted == true)
      {
        System.out.println("testDelete: scenario 3: Your delete method did not correctly return "
            + "false when an invalid Chugimon tried to be deleted.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected exception was thrown in testDelete.");
      return false;
    }
    return true; // return true if all tests pass
  }

  /**
   * Checks for the correctness of ChugiTree.next() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testNext() 
  {
    try
    {
      ChugiTree tree = new ChugiTree();
      Chugimon root = new Chugimon(22, 3); // the root node
      Chugimon three = new Chugimon(10, 2); // the root's left node
      Chugimon nine = new Chugimon(2, 7); // the root's right node
      Chugimon two = new Chugimon(1, 2); // three's left node
      Chugimon five = new Chugimon(23, 9); // three's right node
      Chugimon seven = new Chugimon(42, 47); // nine's left node
      Chugimon eleven = new Chugimon(64, 15); // nine's right node
      // add the 6 Chugimon to the tree
      tree.add(root);
      tree.add(three);
      tree.add(nine);
      tree.add(two);
      tree.add(five);
      tree.add(seven);
      tree.add(eleven);
      
      // scenario 1: there is a next node in the tree
      Chugimon next = tree.next(root); // the next one should be seven
      if (!next.equals(seven))
      {
        System.out.println("testNext: scenario 1: your testNext method did not return the expected"
            + " next Chugimon when there was a next node.");
        return false;
      }
      
      // scenario 2: there is not a next node in the tree
      boolean tester = false; // this should be set to true inside of the catch block
      try
      {
        tree.next(eleven);
      }
      catch (NoSuchElementException e)
      {
        tester = true;
      }
      if (tester == false)
      {
        System.out.println("testNext: scenario 2: your testNext method did not throw a "
            + "NoSuchElementException when there was not a next node in the tree.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected exception was thrown in testNext.");
      return false;
    }
    return true; // return true if all tests pass
  }

  /**
   * Checks for the correctness of ChugiTree.previous() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testPrevious() 
  {
    try
    {
      // create a new tree with 7 nodes
      ChugiTree tree = new ChugiTree();
      Chugimon root = new Chugimon(22, 3); // the root node
      Chugimon three = new Chugimon(10, 2); // the root's left node
      Chugimon nine = new Chugimon(2, 7); // the root's right node
      Chugimon two = new Chugimon(1, 2); // three's left node
      Chugimon five = new Chugimon(23, 9); // three's right node
      Chugimon seven = new Chugimon(42, 47); // nine's left node
      Chugimon eleven = new Chugimon(64, 15); // nine's right node
      // add the 7 Chugimon to the tree
      tree.add(root);
      tree.add(three);
      tree.add(nine);
      tree.add(two);
      tree.add(five);
      tree.add(seven);
      tree.add(eleven);
      
      // scenario 1: there is a previous node in the tree
      Chugimon prev = tree.previous(root); // the previous one should be five
      if (!prev.equals(five))
      {
        System.out.println("testPrevious: scenario 1: your testPrevious method did not return the"
            + " expected previous Chugimon when there was a previous node.");
        return false;
      }
      
      // scenario 2: there is not a previous node in the tree
      boolean tester = false; // this should be set to true inside of the catch block
      try
      {
        tree.previous(two);
      }
      catch (NoSuchElementException e)
      {
        tester = true;
      }
      if (tester == false)
      {
        System.out.println("testPrevious: scenario 2: your testPrevious method did not throw a "
            + "NoSuchElementException when there was not a next node in the tree.");
        return false;
      }
    }
    catch (Exception e)
    {
      System.out.println("An unexpected exception was thrown in testPrevious.");
    }
    return true; // return true if all tests pass
  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) 
  {
    System.out.println("testChugimonCompareToEquals: " + testChugimonCompareToEquals());
    System.out.println("testChugimonToString(): " + testChugimonToString());
    System.out.println("testIsValidBSTHelper(): " + testIsValidBSTHelper());
    System.out.println("testAddToStringSize(): " + testAddToStringSize());
    System.out.println("testLookup(): " + testLookup());
    System.out.println("testHeight(): " + testHeight());
    System.out.println("testCountType(): " + testCountType());
    System.out.println("testGetFirst(): " + testGetFirst());
    System.out.println("testGetLast(): " + testGetLast());
    System.out.println("testDelete(): " + testDelete());
    System.out.println("testNext(): " + testNext());
    System.out.println("testPrevious(): " + testPrevious());
  }
}