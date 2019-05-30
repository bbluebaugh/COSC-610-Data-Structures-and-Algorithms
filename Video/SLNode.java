/*
 * This is a Singly Linked List Node Class similar to the one created earlier in the semester pretty much exactly the same
 * This will be the node object stored in the Singly Linked List
 */
public class SLNode {
	private Object element;//The element which will be stored in the List
    private SLNode next;//Next node pointer
    
    public SLNode(Object obj, SLNode n) {
        element = obj;//object taken in when the node is created
        next = n;//the next element after initial
    }
    
    public Object getElement() {
        return element;//return the element at current spot
    }
    
    public SLNode getNext() {
        return next;//return the next element from current spot
    }
    
    public void setElement(Object obj) {
        element = obj;//set the element based on input
    }
    
    public void setNext(SLNode n) {
        next = n;//setting the next node to input
    }
    
}
