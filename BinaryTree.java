/**
 * [BinaryTree.java]
 * A class that creates a binary tree object where each node has up to 2 children
 * @author Dylan Wang
 * @version 1.0 October 22
 */
class BinaryTree<T> {
  /**stores the root of the binary tree*/
  private Node<T> root;
  
  /**
   * Creates an object from the class BinaryTree.
   */
  BinaryTree() {
    root = null;
  }
  
  /**
   * The method returns the root node.
   * @return The root node of the tree.
   */
  public Node<T> getRoot(){
    return this.root;
  }
  
  /**
   * The method sets the root node to a given node.
   * @param root The new root of the tree.
   */
  public void setRoot(Node<T> root) {
    this.root = root;
  }
}