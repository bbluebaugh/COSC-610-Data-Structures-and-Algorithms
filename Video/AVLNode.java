/*
 * This is a class for the AVL Tree Node which will be used in the AVL Tree Class to store objects
 * creates some objects like in the linked lists
 */
public class AVLNode {
	private int id;
    private Object element;
    private AVLNode left;
    private AVLNode right;
    private int height;
    
    //initialize AVL Tree Node like the the linked lists
    public AVLNode(int num, Object obj) {
        id = num;
        element = obj;
        left = null;
        right = null;
        height = 1;
    }

    //get the id of the current node
    public int getId() {
        return id;
    }
    
    //set the id of the node for traversal
    public void setId(int num) {
        id = num;
    }

    //get the element at current node
    public Object getElement() {
        return element;
    }
    
    //set the element of current node
    public void setElement(Object obj) {
        element = obj;
    }

    //get the left child of the current node
    public AVLNode getLeftChild() {
        return (AVLNode) left;
    }

    //get the right child of the current node
    public AVLNode getRightChild() {
        return (AVLNode) right;
    }
  
    //set left child to the new node
    public void setLeftChild(AVLNode n) {
        left = n;
    }
    
    //set the right child to the new node
    public void setRightChild(AVLNode n) {
        right = n;
    }
    
    //get the height for balancing
    public int getHeight() {
        return height;
    }
    
    //set the height of the tree
    public void setHeight(int num) {
        height = num;
    }
    
}
