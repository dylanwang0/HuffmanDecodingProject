/**
 * [Stack.java]
 * A class that creates a stack where the first item pushed in is the last item to go out
 * @author Dylan Wang
 * @version 1.0 October 22
 */
class SimpleStack<E> { 
  /**stores the head/top of the stack*/
  private Node<E> head;
  
  /**
   * Creates an object from the class SimpleStack.
   */
  SimpleStack(){
    this.head = null;
  }
  
  /**
   * This method pushes a node to the top of the stack.
   * @param item The corresponding node's item
   * @param left The reference to the left node
   * @param right The reference to the right node
   */
  public void push(E item, Node<E> left, Node<E> right){
    Node<E> tempNode = head;
    if (head == null){
      head = new Node<E>(item,null,left,right);
      return;
    }
    head = new Node<E>(item,null,left,right);
    head.setNext(tempNode);
  }
  
  /**
   * This method pushes a node to the top of the stack.
   * @return Returns the removed node (null if the stack was already empty)
   */
  public Node<E> pop(){
    if (head == null){
      return null;
    }
    Node<E> tempNode = head;
    head = head.getNext();
    return tempNode;
    
  }
}
