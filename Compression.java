/*----------------IMPORTS----------------*/
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
/*---------------------------------------*/

/**
 * [Compression.java]
 * A program that decodes an encoded message using a binary tree data structure
 * @author Dylan Wang
 * @version 1.0 October 22
 */
public class Compression {
  /**static variable used to iterate through the input byte array*/
  static int idx;
  /**static variable that stores all the bytes of data in the input file*/
  static byte fileData[];
  public static void main(String[] args) throws IOException {
    //initialize byte reader
    FileInputStream in = null;
    //initialize printer
    FileOutputStream out = null;
    
    //initialize console scanner
    Scanner input = new Scanner(System.in);
    
    //ask user for name of file to read from    
    System.out.println("Enter the name of the file: ");
    //user inputs the name of file
    String fileName = input.next();
    //initialize file using the given fileName
    File file = new File(fileName);
    
    //stores the name of the original file that would be printed into
    String originalFileName = "";
    //stores the string representation of the structure of the binary tree
    String treeStructure = "";
    //stores the extra bits of the data in a string
    String extraBitsStr = "";
    //stores the extra bits of the data in an integer
    int extraBits;
    //variable used to make sure the extra bits in the data are ignored
    int end = 0;
    //initialize the stack used to create the binary tree
    SimpleStack<Byte> st = new SimpleStack<Byte>();
    //initialize the binary tree used to decode the message
    BinaryTree<Byte> bt = new BinaryTree<Byte>();
    
    //try and finally is used for io exceptions
    try {
      //set the byte reader to read from a certain file
      in = new FileInputStream(file);
      
      //initialize a byte array to store the byte input
      fileData = new byte[(int)file.length()];
      //reads all the bytes of data and stores them into the byte array 
      in.read(fileData);
      
      //index used to loop through the byte array (start at 0)
      idx = 0;
      //calls a method to take a line of input and format the byte representation of the file name into a string
      originalFileName = getInput();
      //calls a method to take a line of input and format the byte representation of the tree structure into a string
      treeStructure = getInput();
      //calls a method to take a line of input and format the byte representation of the extra bits into a string
      extraBitsStr = getInput();
      
      //initialize printer using the given file name
      out = new FileOutputStream(originalFileName);
      //the string representation is parsed into an integer
      extraBits = Integer.parseInt(extraBitsStr);
      
      //stores the val of the leaf nodes
      String val = "";
      
      //loop through each character in the string representation of the tree structure
      for (int i = 0; i < treeStructure.length(); i++){
        //stores the current character
        char ch = treeStructure.charAt(i);
        //checks if the character is a closing brackter
        if (ch == ')'){
          //if the string has a value, push it into the stack
          if(!val.equals("")){
            //parses the string into an integer
            int intRep = Integer.parseInt(val);
            //converts the integer to the corresponding byte then pushes it into the stack with no left or right nodes
            st.push((byte)intRep,null,null);
            //reset the string to be empty
            val = "";
          }
          //pop the top two nodes from the stack
          Node<Byte> a = st.pop();
          Node<Byte> b = st.pop();
          //pushes a node with no value, and left node as 'b' and right node as 'a' into the stack
          //the node is the parent of the two subtrees
          st.push(null,b,a);
        } else if (ch == ' '){ //checks if the character is a space
          //if the string has a value, push it into the stack
          if(!val.equals("")){
            //parses the string into an integer
            int intRep = Integer.parseInt(val);
            //converts the integer to the corresponding btye then pushes it into the stack with no left or right nodes
            st.push((byte)intRep,null,null);
            //reset the string to be empty
            val = "";
          }
        } else if (ch != '('){ //checks if string is not a open bracket (is a number)
          //appends the digit to the string
          val += ch;
        }
      }
      //the only node in the stack is the root of the binary tree (sets the root as that node)
      bt.setRoot(st.pop());
      
      //start from the root node
      Node<Byte> node = bt.getRoot();
      
      //loops the remaining bytes in the file
      while (idx < file.length()){
        //stores the current byte
        byte curByte = fileData[idx];
        //checks if this byte is the final byte in the file
        if (idx == file.length() - 1){
          //sets the end position as the number of extra bits
          end = extraBits;
        }
        //loops through the bits in a byte
        for (int i = 7; i >= end; i--){
          //use bit operations to determine the current bit
          if ((curByte & (1 << i)) == 0){ //checks if current bit is 0
            //node is set to its left node
            node = node.getLeft();
          } else{ //current bit is 1
            //node is set to its right node
            node = node.getRight();
          }
          //checks if node is a leaf node
          if (node.isLeaf()){ //checks if the current node is a leaf node
            //prints the item stored in the leaf node
            out.write(node.getItem());
            //returns to the root node
            node = bt.getRoot();
          }
        }
        //index is increased to continue the loop
        idx++;
      }
    } finally { //always performs the following lines of code
      if (in != null) { //checks if byte reader is set to a file
        //close the byte reader
        in.close();
      }
      if (out != null) { //checks if printer is set to a file
        //close the printer
        out.close();
      }
    }
  }
  /**
   * This method gets a line of input from the byte array and returns the value in a string
   * @return The input formatted in a string
   */
  public static String getInput(){
    //stores the input
    String str = "";
    //loops until a carriage return is hit
    while (fileData[idx] != 13){
        //the byte is casted to a character and added to the string that stores the file name
        str = str + (char)fileData[idx];
        //index increases by 1 to continue the loop
        idx++;
    }
    //index is increased by 2 to avoid carriage return and new line feed
    idx+=2;
    //return the input in a string
    return str;
  }
}
