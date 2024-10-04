//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P09 Chugimon - ChugiTree class
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
 * This class implements a ChugidexStorage as a Binary Search Tree.
 * 
 * Notes: 1) You may NOT use any arrays or Collections objects (ArrayLists, etc) in this class. 2)
 * You may NOT use any loops (for, while, etc) in this class. Recursive strategies only.
 *
 */
public class ChugiTree implements ChugidexStorage 
{

  // The root of this ChugiTree. Set to null when tree is empty. 
  private BSTNode<Chugimon> root;

  //The size of this ChugiTree (total number of Chugimon stored in this BST)
  private int size;


  /**
   * Getter method for the Chugimon at the root of this BST.
   * 
   * @return the root of the BST.
   */
  public Chugimon getRoot() 
  {
    return root.getData();
  }

  /**
   * A helper method for determining whether this ChugiTree is a valid BST. In order to be a valid
   * BST, the following must be true: For every internal (non-leaf) node X of a binary tree, all the
   * values in the node's left subtree are less than the value in X, and all the values in the
   * node's right subtree are greater than the value in X.
   * 
   * @return true if this ChugiTree is a valid BST, false otherwise
   */
  public boolean isValidBST() 
  {
    return isValidBSTHelper(root);
  }
  
  /**
   * This method is a helper for isValidBSTHelper, and it finds the maximum value of a BST.
   * @param node   the root of the tree to search
   * @return   the minimum value of a BST
   */
  private static Chugimon findMax(BSTNode<Chugimon> node)
  {
    if (node.getRight() == null)
      return node.getData();
    else
    {
      return findMax(node.getRight());
    }
  }
  
  /**
   * This method is a helper for isValidBSTHelper, and it finds the minimum value of a BST.
   * @param node   the root of the tree to search
   * @return   the minimum value of a BST
   */
  private static Chugimon findMin(BSTNode<Chugimon> node)
  {
    if (node.getLeft() == null)
      return node.getData();
    else
    {
      return findMin(node.getLeft());
    }
  }
  
  /**
   * A helper method for determining whether a BST rooted at node is a valid BST. In order to be a
   * valid BST, the following must be true: For every internal (non-leaf) node of a binary tree, all
   * the values in a node's left subtree are less than the values in a node's right subtree.
   * 
   * @param node The root of the BST.
   * @return true if the binary tree rooted at node is a BST, false otherwise
   */
  public static boolean isValidBSTHelper(BSTNode<Chugimon> node) 
  {
    // base cases
    if (node == null)
      return true;
    if (node.getLeft() == null && node.getRight() == null)
      return true;
    
    // recursive cases
    if (node.getLeft() != null && node.getData().compareTo(findMax(node.getLeft())) < 0)
    {
      return false; // return false if the max of the left subtree is more than the current node's
      // data
    }
    if (node.getRight() != null && node.getData().compareTo(findMin(node.getRight())) > 0)
    {
      return false; // return false if the min of the right subtree is less than the current node's
      // data
    }
    return isValidBSTHelper(node.getLeft()) && isValidBSTHelper(node.getRight());
  }

  /**
   * Checks whether this ChugiTree is empty or not
   * 
   * @return true if this tree is empty, false otherwise
   */
  @Override
  public boolean isEmpty() 
  {
    if (size == 0)
      return true;
    return false;
  }

  /**
   * Gets the size of this ChugiTree
   * 
   * @return the total number of Chugimons stored in this tree
   */
  @Override
  public int size() 
  {
    return size;
  }

  /**
   * Returns a String representation of all the Chugimons stored within this ChugiTree in the
   * increasing order with respect to the result of Chugimon.compareTo() method. The string should
   * be a comma-separated list of all the Chugimon toString() values. No spaces are expected to be
   * in the resulting string. No comma should be at the end of the resulting string. For instance,
   * 
   * "nameOne#12.25,nameTwo#12.56,nameTwo#89.27"
   * 
   * @return a string containing all of the Chugimon, in the increasing order. Returns an empty
   *         string "" if this BST is empty.
   * 
   */
  @Override
  public String toString() 
  {
    return toStringHelper(root);
  }

  /**
   * Recursive helper method which returns a String representation of the ChugiTree rooted at node.
   * An example of the String representation of the contents of a ChugiTree storing three Chugimons
   * is provided in the description of the above toString() method.
   * 
   * @param node references the root of a subtree
   * @return a String representation of all the Chugimons stored in the sub-tree rooted at node in
   *         increasing order. Returns an empty String "" if current is null.
   */
  protected static String toStringHelper(BSTNode<Chugimon> node) 
  {
    String s = "";
    if (node == null)
      return "";
    else if (node.getLeft() == null && node.getRight() == null)
    {
      return node.getData().toString();
    }
    else
    {
      s += toStringHelper(node.getLeft()) + ",";
      s += node.getData().toString();
      s += "," + toStringHelper(node.getRight());
    }
    if (s.substring(s.length()-1).equals(","))
      return s.substring(0, s.length() - 1);
    if (s.substring(0, 1).equals(","))
      return s.substring(1);
    return s;

  }

  /**
   * Adds a new Chugimon to this ChugiTree. Duplicate Chugimons are NOT allowed.
   * 
   * @param newChugimon Chugimon to add to this ChugiTree
   * @return true if if the newChugimon was successfully added to the ChugiTree, false if a match
   *         with newChugimon is already present in the tree.
   * @throws IllegalArgumentException with a descriptive error message if newChugimon is null.
   */
  @Override
  public boolean add(Chugimon newChugimon) 
  {
    if (newChugimon == null)
    {
      throw new IllegalArgumentException("the new Chugimon cannot be null.");
    }
    // base cases:
    // if the tree is currently empty, set the root to the new Chugimon
    if (root == null)
    {
      root = new BSTNode<Chugimon>(newChugimon);
      size++;
      return true;
    }
    else
    {
      boolean help = addHelper(newChugimon, root); // this boolean is true if the Chugimon was
      // added, and false if it wasn't
      if (help == true)
      {
        size++; // if a Chugimon was added, increment size
      }
      return help;
    }
  }

  /**
   * Recursive helper method to insert a new Chugimon to a Pokedex rooted at node.
   * 
   * @param node        The "root" of the subtree we are inserting the new Chugimon into.
   * @param newChugimon The Chugimon to be added to a BST rooted at node. We assume that newChugimon
   *                    is NOT null.
   * @return true if the newChugimon was successfully added to the ChugiTree, false if a match with
   *         newChugimon is already present in the subtree rooted at node.
   */
  protected static boolean addHelper(Chugimon newChugimon, BSTNode<Chugimon> node) 
  {
    // base cases:
    // if left child is null, no recursive call - just set left
    if (node.getLeft() == null && newChugimon.compareTo(node.getData()) < 0)
    {
      node.setLeft(new BSTNode<Chugimon>(newChugimon));
      return true;
    }
    if (node.getRight() == null && newChugimon.compareTo(node.getData()) > 0)
    {
      node.setRight(new BSTNode<Chugimon>(newChugimon));
      return true;
    }
    
    // recursive cases:
    if (newChugimon.compareTo(node.getData()) > 0)
    {
      return addHelper(newChugimon, node.getRight());
    }
    else if (newChugimon.compareTo(node.getData()) < 0)
    {
      return addHelper(newChugimon, node.getLeft());
    }
      return false;
  }

  /**
   * Searches a Chugimon given its first and second identifiers.
   * 
   * @param firstId  First identifier of the Chugimon to find
   * @param secondId Second identifier of the Chugimon to find
   * @return the matching Chugimon if match found, null otherwise.
   */
  @Override
  public Chugimon lookup(int firstId, int secondId) 
  {
    if (root == null)
      return null;
    Chugimon chugi = new Chugimon(firstId, secondId); // a new Chugimon using firstId and secondID
    return lookupHelper(chugi, root); // this is the Chugimon
    // given by the lookupHelper method
  }

  /**
   * Recursive helper method to search and return a match with a given Chugimon in the subtree
   * rooted at node, if present.
   * 
   * @param toFind a Chugimon to be searched in the BST rooted at node. We assume that toFind is NOT
   *               null.
   * @param node   "root" of the subtree we are checking whether it contains a match to target.
   * @return a reference to the matching Chugimon if found, null otherwise.
   */
  protected static Chugimon lookupHelper(Chugimon toFind, BSTNode<Chugimon> node) 
  {
    // base case:
    // empty tree
    if (node == null)
    {
      return null;
    }
    
    // recursive cases:
    if (node.getData().compareTo(toFind) == 0)
    {
      return node.getData(); // if the Chugimon is found, return the reference to it
    }
    else if (node.getData().compareTo(toFind) > 0)
    {
      return lookupHelper(toFind, node.getLeft()); // if the root is greater than the Chugimon to
      // find, search the left subtree.
    }
    else if (node.getData().compareTo(toFind) < 0)
    {
      return lookupHelper(toFind, node.getRight()); // if the root is less than the Chugimon to
      // find, search the right subtree.
    }
    return null;
  }

  /**
   * Computes and returns the height of this BST, counting the number of NODES from root to the
   * deepest leaf.
   * 
   * @return the height of this Binary Search Tree
   */
  public int height() 
  {
    return heightHelper(root);
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at node counting the
   * number of nodes and NOT the number of edges from node to the deepest leaf
   * 
   * @param node root of a subtree
   * @return height of the subtree rooted at node
   */
  protected static int heightHelper(BSTNode<Chugimon> node) 
  {
    // base cases:
    if (node == null)
      return 0; // the height of a tree with no nodes is 0.
    if (node.getLeft() == null && node.getRight() == null)
      return 1; // the height of a tree with one node is 1.
    
    // recursive cases:
    int lHeight = heightHelper(node.getLeft());
    int rHeight = heightHelper(node.getRight());
    if (lHeight > rHeight)
      return lHeight + 1;
    return rHeight + 1;
  }

  /**
   * Recursive method to find and return the first Chugimon, in the increasing order, within this
   * ChugiTree (meaning the smallest element stored in the tree).
   * 
   * @return the first element in the increasing order of this BST, and null if the tree is empty.
   */
  @Override
  public Chugimon getFirst() 
  {
    if (root == null)
      return null;
    return getFirstHelper(root);
  }

  /**
   * Recursive helper method for getFirst().
   * 
   * @param root the node from which to find the the minimum node
   * @return the minimum element in the increasing order from the node <b>root</b>
   */
  protected static Chugimon getFirstHelper(BSTNode<Chugimon> root) 
  {
    // base case:
    if (root.getLeft() == null)
      return root.getData();
    
    // recursive case:
    return getFirstHelper(root.getLeft()); // go as far left as you can in the tree
  }

  /**
   * Recursive method to find and return the last Chugimon, in the increasing order, within this
   * ChugiTree (meaning the greatest element stored in the tree).
   * 
   * @return the last element in the increasing order of this BST, and null if the tree is empty.
   */
  @Override
  public Chugimon getLast() 
  {
    if (root == null)
      return null;
    return getLastHelper(root);
  }

  /**
   * Recursive helper method for getLast().
   * 
   * @param root the node from which to find the the maximum node
   * @return the maximum element in the increasing order from the node <b>root</b>
   */
  protected static Chugimon getLastHelper(BSTNode<Chugimon> root) 
  {
    // base case: 
    if (root.getRight() == null)
      return root.getData();
    
    // recursive case:
    return getLastHelper(root.getRight());
  }
  
  /**
   * Helper method for countType. It returns the amount of Chugimon objects with a primary or 
   * secondary type of the specified type stored in this Chugitree.
   * @param root   the root of the tree to be searched
   * @param chugiType   the type of Chugimons to count. Assume it is not null.
   * @return the number of Chugimon with a given type
   */
  private static int countTypeHelper(BSTNode<Chugimon> root, ChugiType chugiType)
  {
    // base case:
    if (root == null)
      return 0;
    
    // recursive cases:
    if (root.getData().getPrimaryType().equals(chugiType) 
        || root.getData().getSecondaryType().equals(chugiType))
    {
      return 1 + countTypeHelper(root.getLeft(), chugiType) 
      + countTypeHelper(root.getRight(), chugiType);
    }
    else
    {
      return countTypeHelper(root.getLeft(), chugiType) 
          + countTypeHelper(root.getRight(), chugiType);
    }
  }
  
  /**
   * Recursive method to get the number of Chugimon with a primary or secondary type of the
   * specified type, stored in this ChugiTree.
   * 
   * @param chugiType the type of Chugimons to count. We assume that chugiType is NOT null.
   * @return the number of all the Chugimon objects with a primary or secondary type of the
   *         specified type stored in this ChugiTree.
   */
  public int countType(ChugiType chugiType) 
  {
    return countTypeHelper(root, chugiType);
  }

  /**
   * Finds and returns the in-order successor of a specified Chugimon in this ChugiTree
   * 
   * @param chugi the Chugimon to find its successor
   * @return the in-order successor of a specified Chugimon in this ChugiTree
   * 
   * @throws IllegalArgumentException with a descriptive error message if <b>chugi</b> is null
   * @throws NoSuchElementException   with a descriptive error message if the Chugimon provided as
   *                                  input has no in-order successor in this ChugiTree.
   */
  @Override
  public Chugimon next(Chugimon chugi) 
  {
    if (chugi == null)
    {
      throw new IllegalArgumentException("the given Chugimon cannot be null.");
    }
    Chugimon curr = nextHelper(chugi, root, null);
    if (curr.equals(getLast()))
    {
      throw new NoSuchElementException("The Chugimon provided has no in-order successor in this"
          + " tree.");
    }
    return curr;
  }

  /**
   * Recursive helper method to find and return the next Chugimon in the tree rooted at node.
   * 
   * @param chugi a Chugimon to search its in-order successor. We assume that <b>chugi</b> is NOT
   *              null.
   * @param node  "root" of a subtree storing Chugimon objects
   * @param next  a BSTNode which stores a potentional candidate for next node
   * @return the next Chugimon in the tree rooted at node.
   * @throws NoSuchElementException with a descriptive error message if the Chugimon provided as
   *                                input has no in-order successor in the subtree rooted at node.
   */
  protected static Chugimon nextHelper(Chugimon chugi, BSTNode<Chugimon> node, 
      BSTNode<Chugimon> next) 
  {
    // Hint: you will need to use getFirstHelper in this method. Below are additional hints.
    if (findMax(node).equals(chugi))
    {
      throw new NoSuchElementException("the Chugimon provided has no in-order successor in the "
          + "subtree rooted at node.");
    }
    // base case: node is null
    if (node == null)
      throw new NoSuchElementException("the chugimon provided has no in-order successor.");
    
    // recursive cases:
    // (1) if chugi is found and if the right child is not null, use getFirstHelper to find and
    // return the next chugimon. It should be the left most child of the right subtree
    if (lookupHelper(chugi, node) != null && node.getRight() != null)
    {
      return getFirstHelper(node.getRight());
    }

    // (2) if chugi is less than the Chugimon at node, set next as the root node and search
    // recursively into the left subtree
    if (chugi.compareTo(node.getData()) < 0)
    {
      next = node;
      return nextHelper(chugi, node.getLeft(), next);
    }
    return null;
  }

  /**
   * Finds and returns the in-order predecessor of a specified Chugimon in this ChugiTree
   * 
   * @param chugi the Chugimon to find its predecessor
   * @return the in-order predecessor of a specified Chugimon in this ChugiTree.
   * 
   * @throws IllegalArgumentException with a descriptive error message if <b>chugi</b> is null
   * @throws NoSuchElementException   if there is no Chugimon directly before the provided Chugimon
   */
  @Override
  public Chugimon previous(Chugimon chugi) 
  {
    return previousHelper(chugi, root, null);
  }

  /**
   * Recursive helper method to find and return the previous Chugimon in the tree rooted at node.
   * 
   * @param chugi a Chugimon to search its in-order predecessor. We assume that <b>chugi</b> is NOT
   *              null.
   * @param node  "root" of a subtree storing Chugimon objects
   * @param prev  a BSTNode which stores a potentional candidate for previous node
   * @return the previous Chugimon in the tree rooted at node.
   * @throws NoSuchElementException with a descriptive error message if the Chugimon provided as
   *                                input has no in-order predecessor in the subtree rooted at node.
   */
  protected static Chugimon previousHelper(Chugimon chugi, BSTNode<Chugimon> node,
      BSTNode<Chugimon> prev) 
  {
    // Hint: you will need to use getLastHelper in this method. Below are more hints.
    if (findMin(node).equals(chugi))
    {
      throw new NoSuchElementException("the Chugimon provided has no in-order predecessor in the "
          + "subtree rooted at node.");
    }
    // base case: node is null
    if (node == null)
      throw new NoSuchElementException("the chugimon provided has no in-order successor.");
    // recursive cases:
    // (1) if chugi is found and if the left child is not null, use getLastHelper to find and return
    // the previous chugimon. It should be the right most child of the left subtree
    if (lookupHelper(chugi, node) != null && node.getLeft() != null)
    {
      return getLastHelper(node.getLeft());
    }

    // (2) if chugi is greater than the Chugimon at node, set prev as the root node and search
    // recursively into the right subtree
    if (chugi.compareTo(node.getData()) > 0)
    {
      prev = node;
      return previousHelper(chugi, node.getRight(), prev);
    }
    return null;
  }

  /**
   * Deletes a specific Chugimon from this ChugiTree.
   * 
   * @param chugi the Chugimon to delete
   * @return true if the specific Chugimon is successfully deleted, false if no match found with any
   *         Chugimon in this tree.
   * @throws IllegalArgumentException with a descriptive error message if <b>chugi</b> is null
   */
  @Override
  public boolean delete(Chugimon chugi) 
  {
    if (chugi == null)
      throw new IllegalArgumentException("the Chugimon cannot be null.");
    try
    {
      deleteChugimonHelper(chugi, root);
    }
    catch (NoSuchElementException e)
    {
      return false; // if the call to deleteChugimonHelper throws a NoSuchElement exception, then
      // the Chugimon was not successfully deleted.
    }
    size--;
    return true; // if no exception was thrown, the Chugimon was successfully deleted.
  }

  /**
   * Recursive helper method to search and delete a specific Chugimon from the BST rooted at node
   * 
   * @param target a reference to a Chugimon to delete from the BST rooted at node. We assume that
   *               target is NOT null.
   * @param node   "root" of the subtree we are checking whether it contains a match with the target
   *               Chugimon.
   * 
   * 
   * @return the new "root" of the subtree we are checking after trying to remove target
   * @throws NoSuchElementException with a descriptive error message if there is no Chugimon
   *                                matching target in the BST rooted at <b>node</b>
   */
  protected static BSTNode<Chugimon> deleteChugimonHelper(Chugimon target, BSTNode<Chugimon> node) 
  {
    // hints are provided in the comments below

    // if node == null (empty subtree rooted at node), no match found, throw an
    // exception
    if (node == null)
    {
      throw new NoSuchElementException("There is no Chugimon matching target in the BST rooted at"
          + " node.");
    }

    // Compare the target to the data at node and proceed accordingly
    // Recurse on the left or right subtree with respect to the comparison result
    // Make sure to use the output of the recursive call to appropriately set the
    // left or the right child of node accordingly
    int compare = target.compareTo(node.getData()); // the value of comparing target to node; 
    // should be 0 if equal, negative if target is less than node, and positive if target is 
    // greater than node.
    if (compare < 0)
    {
      deleteChugimonHelper(target, node.getLeft()); // if target is less than node, go to the 
      // left subtree
    }
    else if (compare > 0)
    {
      deleteChugimonHelper(target, node.getRight()); // if target is greater than node, go to the 
      // right subtree
    }
    // if match with target found, three cases should be considered. Feel free to
    // organize the order of these cases at your choice.
    else // match found
    {
      // Case 1: node may be a leaf (has no children), set node to null.
      if (node.getLeft() == null && node.getRight() == null)
      {
        node = null;
      }
      // Case 2: node may have only one child, set node to that child (whether left or
      // right child)
      else if (node.getLeft() == null && node.getRight() != null)
      {
        node = node.getRight(); // if left is null but right is not null, set node to right child
      }
      else if (node.getRight() == null && node.getLeft() != null)
      {
        node = node.getLeft(); // if right is null but left is not null, set node to left child
      }
      
      // Case 3: node may have two children,
      // Replace node with a new BSTNode whose data field value is the successor of
      // target in the tree, and having the same left and right children as node.
      // Notice carefully that you cannot set the data of a BSTNode. Hint: The
      // successor is the smallest element at the right subtree of node
      // Then, remove the successor from the right subtree. The successor must have up
      // to one child.
      else
      {
        Chugimon next = findMin(node.getRight()); // the minimum element in the right subtree of
        // node
        node = new BSTNode<Chugimon>(next, node.getLeft(), node.getRight());
        node.setRight(deleteChugimonHelper(next, node.getRight()));
      }
    }
    // Make sure to return node (the new root to this subtree) at the end of each
    // case or at the end of the method.
    return node;
  }
}