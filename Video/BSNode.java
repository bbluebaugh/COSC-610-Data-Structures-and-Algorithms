/*
 * This is the Binary Search Tree node, it behaves similar to the SLNode or DLNode but with a few added methods for the Binary Search Tree
 * Class which will be used to make sure it behaves like a BST should
 */
public class BSNode {
	private int id;//integer to hold the id of the incoming object
    private Object element;//similar to the SLNode class there must be an Object to be stored could be any type
    private BSNode left;//new node for traversal to the left
    private BSNode right;//new node for traversal to the right
    
    //initialize the constructor the BSNode
    
    public BSNode(int num, Object obj) {
        
    	//initialize all elements of the BSNode so that you can use them later
    	
    	id = num;
        element = obj;
        left = null;
        right = null;
    }
    
    //get the ID of the node
    public int getId() {
        return id;
    }
    
    //get the element at the current spot
    public Object getElement() {
        return element;
    }
    
    //get the left child of the current node
    public BSNode getLeftChild() {
        return left;
    }
    
    //get the right child of the current node
    public BSNode getRightChild() {
        return right;
    }
    
    //set the ID of the node
    public void setId(int num) {
        id = num;
    }
    
    //set the element of the node
    public void setElement(Object obj) {
        element = obj;
    }
    
    //set the left child of the node
    public void setLeftChild(BSNode n) {
        left = n;
    }
    
    //set the right child of the node
    public void setRightChild(BSNode n) {
        right = n;
    }
    
}