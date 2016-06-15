


/**
 * A BinarySearchTree stores elements that are unique in a binary search tree
 * data structure.
 * 
 * TODO 3: Collaborative activity: in teams of 2, implement insert such that the
 * first inserted element is at the root and new nodes always start as leafs.
 * 
 * TODO 4: One team volunteer to type in solution.
 * 
 * TODO 5: Rick changes the tests to rely on 7 insertions
 * 
 * @author mercer
 * 
 * @param <E>
 *          The type of element to be stored in this BST
 */
public class BinarySearchTree<E extends Comparable<E>> {

  private class TreeNode {
    private E data;
    private TreeNode left;
    private TreeNode right;

    TreeNode(E theData) {
      data = theData;
      left = null;
      right = null;
    }

  }

  private TreeNode root;

  /**
   * Construct an empty tree
   */
  public BinarySearchTree() {
    // TODO 1: Rick hard codes the BST shown in presentation. With your help of course
    //    root = new TreeNode((E) new Integer(50));
    //    root.left = new TreeNode((E) new Integer(25));
    //    root.left.left = new TreeNode((E) new Integer(12));
    //    root.left.right = new TreeNode((E) new Integer(36));
    //    root.right = new TreeNode((E) new Integer(75));
    //    root.right.left = new TreeNode((E) new Integer(65));
    //    root.right.right = new TreeNode((E) new Integer(90));
	  root = null;
  }

  /**
   * Return a textual representation of this BST where the toString version of
   * all elements concatenated into one string with blank separators.
   * 
   * @return A textual representation of this BST with elements in order.
   */
  public String toString() {
    if (root == null)
      return "";
    else
      return toString(root).trim();
  }

  private String toString(TreeNode t) {
    if (t == null)
      return "";
    else
      return toString(t.left) + t.data + " " + toString(t.right);
  }

  /**
   * Return true if the element is in this BST or false if element is not in
   * this BST.
   */
  public boolean exists(E element) {
    TreeNode currentNode = root;
    while (currentNode != null) {
      if (currentNode.data.equals(element))
        return true;
      else if (element.compareTo(currentNode.data) < 0)
        currentNode = currentNode.left;
      else
        currentNode = currentNode.right;
    }
    return false;
  }

  // A long solution that follows Rick's algorithm on the slides
  public void insert(E newElement) {
    root = insert(newElement, root);
    // root -->  50
    //          /  \
    //        25   65
    //        /
    //      12
  }

  private TreeNode insert(E newElement, TreeNode t) {
    if (t == null)
      return new TreeNode(newElement);
    else if (newElement.compareTo(t.data) < 0) {
      t.left = insert(newElement, t.left);
    } else {
      t.right = insert(newElement, t.right);
    }
    return t;
  }

  public BinarySearchTree<E> allThoseLessThan(E value) {
    BinarySearchTree<E> result = new BinarySearchTree<E>();
    gatherAll(root, value, result);
    return result;
  }

  private void gatherAll(TreeNode t, E value, BinarySearchTree<E> result) {
    if (t == null)
      return;
    else {
      gatherAll(t.left, value, result);
      // process the node
      if (t.data.compareTo(value) < 0)
        result.insert(t.data);
      gatherAll(t.right, value, result);
    }

  }
  
  public boolean remove(E value) {
      // TODO: Implement this method
	  return false;
  }
  
}