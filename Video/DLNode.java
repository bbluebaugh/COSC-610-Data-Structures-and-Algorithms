/*
 * This is a class just like the Doubly Linked List class from the homework earlier in the semester but with some modifications for the project
 */
public class DLNode {
	private Object element;
    private DLNode next;
    private DLNode pre;
    
    //initialize constructor with base values for the List
    public DLNode(Object obj, DLNode n, DLNode p) {
        element = obj;
        next = n;
        pre = p;
    }
    
    //get the element of current node
    public Object getElement() {
        return element;
    }
    
    //set the element of current node
    public void setElement(Object obj) {
        element = obj;
    }
    
    //get the next node of the current
    public DLNode getNextNode() {
        return next;
    }
    
    //set the next node of current
    public void setNextNode(DLNode n) {
        next = n;
    }
    
    //get the previous node of the node
    public DLNode getPreviousNode() {
        return pre;
    }
    
    //set the previous node of the current node
    public void setPreviousNode(DLNode n) {
        pre = n;
    }
    
}
