/**
 * [Node.java]
 * A class that creates a node used in the binary tree and stack data structure
 * @author Dylan Wang
 * @version 1.0 October 22
 */
class Node<T> { 
  /**Stores the node's corresponding item*/
  private T item;
  /**Stores the reference to the next node in the stack*/
  private Node<T> next;
  /**Stores the reference to the left node in the tree*/
  private Node<T> left;
  /**Stores the reference to the right node in the tree*/
  private Node<T> right;
  
  /**
   * Creates an object from the class Node.
   * @param item Stores the node's corresponding item
   * @param next Stores the reference to the next node in the stack
   * @param left Stores the reference to the left node in the tree
   * @param right Stores the reference to the right node in the tree
   */
  Node(T item, Node<T> next, Node<T> left, Node<T> right){
    this.item = item;
    this.next = next;
    this.left = left;
    this.right = right;
  }
  
  /**
   * This method returns the node's corresponding item
   * @return The item of the node
   */
  public T getItem(){
    return this.item;
  }
  
  /**
   * This method returns the reference to the next node
   * @return The reference to the next node
   */
  public Node<T> getNext(){
    return this.next;
  }
  
  /**
   * This method sets the next node to a given node
   * @param next The new next node
   */
  public void setNext(Node<T> next){
    this.next = next;
  }
  
  /**
   * This method returns the reference to the left node
   * @return The reference to the left node
   */
  public Node<T> getLeft(){
    return this.left;
  }
  
  /**
   * This method sets the left node to a given node
   * @param left The new left node
   */
  public void setLeft(Node<T> left){
    this.left = left;
  }
  
  /**
   * This method returns the reference to the right node
   * @return The reference to the right node
   */
  public Node<T> getRight(){
    return this.right;
  }
  
  /**
   * This method sets the right node to a given node
   * @param right The new right node
   */
  public void setRight(Node<T> right){
    this.right = right;
  }
  
  /**
   * This method returns a true or false whether or not the node is a leaf node
   * @return Returns true if the node is a leaf and false otherwise
   */
  public boolean isLeaf(){
    if (this.left == null && this.right == null){
      return true;
    }
    return false;
  }
}
