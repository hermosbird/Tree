/**
 * ExpressionTree objects are built by performing a pre-order traversal over prefix 
 * expressions passed as a String object.   You job is to add several methods that you test well.  
 * 
 *  1) This class has the private TreeNode build() method for building a tree
 *  2) int valueOf() with all operators + - * / %
 *  
 *  Add these new methods during lab
 *    
 *  3) prefix(), postfix(), and sideways() to show traversal possibilities
 *  4) method isFull that returns true if all nodes have 0 or 2 children.  An emptyTree is full.
 *  5) method count that returns the number of nodes O(n), not constant time. Recursive backtracking required.   
 *  6) isValid() to return true for properly constructed ExpressionTree objects
 *       - Make sure all internal nodes have + - * / %
 *       - Make sure all leafs have integers (you may throw an NumberFormatException if not, but catch it
 *       - Make sure the tree isFull
 *  7) If time permits, try nodesAtLevel(int level) that returns the number of nodes at any level. 
 *       - Level 0 has a max of 1 node, level 1 has a max of 2 nodes, level 2 has, levels too big have 0 nodes      
 */

import java.util.Scanner;

public class ExpressionTree {

  private class TreeNode {
    private String data;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(String theData) {
      data = theData;
      left = null;
      right = null;
    }

    public TreeNode(String dataRef, TreeNode leftTree, TreeNode rightTree) {
      data = dataRef;
      left = leftTree;
      right = rightTree;
    }
  } // end class TreeNode

  // The external reference starting point   
  TreeNode root;
  private Scanner scanner;

  public ExpressionTree(String prefix) {
    // Convert the prefix expression to an expression tree
    scanner = new Scanner(prefix);
    root = build();
  }

  private TreeNode build() {
    if (!scanner.hasNext())
      return null;

    // There must be at least one more token in the scanner
    String token = scanner.next();
    if (isOperator(token)) {
      TreeNode leftSubtree = build();
      TreeNode rightSubtree = build();
      return new TreeNode(token, leftSubtree, rightSubtree);
    }
    else
      return new TreeNode(token);
  }

  private boolean isOperator(String token) {
    return "+-*/%^".indexOf(token) >= 0;
  }

  public int valueOf() {
    return valueOf(root);
  }

  // helper method
  private int valueOf(TreeNode t) {
    if (t == null)
      return 0;
    else if (t.data.equals("+"))
      return valueOf(t.left) + valueOf(t.right);
    else if (t.data.equals("-"))
      return valueOf(t.left) - valueOf(t.right);
    else if (t.data.equals("*"))
      return valueOf(t.left) * valueOf(t.right);
    else if (t.data.equals("/"))
      return valueOf(t.left) / valueOf(t.right);
    else if (t.data.equals("%"))
      return valueOf(t.left) % valueOf(t.right);
    else
      // The token must be an operand
      return Integer.parseInt(t.data);
  }

  String result;

  public String inFix() {
    result = "";
    inFix(root);
    return result.trim();
  }

  private void inFix(TreeNode t) {
    if (t != null) {
      inFix(t.left);
      result += t.data + " ";
      inFix(t.right);
    }
  }

  /**
   * Height returns the maximum depth of the tree, where an empty tree is
   * considered to have a height of -1. A tree with only the root has a height
   * of 0.
   * 
   * @return the height of the tree
   */
  public int height() {
    // this method will be called by the user, who has no knowledge of the
    // underlying implementation. Performing this recursively is a simple
    // and elegant solution, however the method header does not contain
    // enough parameters. We will call on a private helper method to assist us.
    // return height of the root
    return height(this.root);
  }

  /**
   * Private helper method for height. returns the height of the sub-root
   * recursively. This is a slightly modified implementation than the one
   * written in class. You should be able to implement a method like this
   * yourself (it does not matter exactly how, as long as it is correct).
   * 
   * @param subRoot
   *          the current node, which is a root for a smaller tree
   * @return the height of the current sub-tree
   */
  private int height(TreeNode subRoot) {
    // base case, if we have looked at the child of a leaf node (null)
    if (subRoot == null) {
      // the height of an empty tree is -1
      return -1;
    }
    else {
      // recursive calls, store the height of the left and right subtrees
      int leftHeight = height(subRoot.left);
      int rightHeight = height(subRoot.right);
      // the height of the subroot is 1+the larger height of the right and
      // left subtrees
      return 1 + Math.max(leftHeight, rightHeight);
      /**
       * SPECIAL NOTE: It is easy to create off-by-one errors, especially using
       * recursion. You should prove to yourself that this implementation
       * handles all cases, not just by running the test class.
       */
    }
  }
}
