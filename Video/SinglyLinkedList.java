/*
 * Singly linked list class exactly the same as the one done during the semester changed to work with the project specification
 */
public class SinglyLinkedList {
private SLNode head;
    
    /*
     *Create the Singly linked list 
     */
    public SinglyLinkedList() {
        head = null;//initialize head to no value
    }
    
    /*
     * Get the node at the head.
     * @return The head node.
     */
    public SLNode getHead() {
        return head;
    }
    
    /*
     * Set the node to the supplied node.
     * @param n The node to set to the head.
     */
    public void setHead(SLNode n) {
        head = n;
    }
    
    /*
     * Add an item to the end of the list.
     * @param n The node to be added.
     */
    public void add(SLNode n) {
        n.setNext(null);
        
        if(head == null) {
            head = n;
            return;
        }
        
        SLNode x = head;
        
        while(x.getNext() != null) {
            x = x.getNext();
        }
        
        x.setNext(n);
    }
    
    /*
     * Remove the last element in the SLL.
     */
    public void remove() {
        if(head == null) {
            return;
        }
        
        SLNode n = head;

        if(n.getNext() == null) {
            head = null;
        }

        while(n.getNext() != null) {
            SLNode test = n.getNext();

            if(test.getNext() == null) {
                n.setNext(null);
            } else {
                n = n.getNext();
            }
        }
    }
    
    /*
     * Takes in a current node to delete and a previous node to redirect.
     * @param previous The node that needs a new 'next' node.
     * @param current The node to be deleted.
     */
    public void removeNode(SLNode previous, SLNode current) {
        //if there is nothing in the list... do nothing.
        if(head == null) {
            return;
        }
        
        //if previous and current are the same and they both have a next of null then there is only one item in the list.
        if(previous == current && current.getNext() == null) {
            head = null;
            return;
        }
        
        //if previous and current are the same then we assume that the node to be deleted is the first node in the list.
        if(previous == current) {
            //System.out.println("First");
            SLNode next = current.getNext();
            head = next;
            return;
        }
        
        //otherwise...
        previous.setNext(current.getNext());
        
    }
    
    /*
     * Print the Singly linked list
     */
    public void print() {
        String out = "";
        
        SLNode n = head;
        
        if(n == null) {
            out = "0 nodes.";
        }
        
        while(n != null) {
            out +=  n.getElement();
            
            if(n.getNext() != null) {
                out += "\n";
            }
            
            n = n.getNext();
        }
        
        System.out.println(out);
    }
    
    /*
     * Print the Singly Linked List without the possibility of a "0 nodes" printouts.
     */
    public void noNull() {
        String out = "";
        
        SLNode n = head;
        
        if(n == null) {
            return;
        }
        
        while(n != null) {
            out +=  n.getElement();
            
            if(n.getNext() != null) {
                out += "\n";
            }
            
            n = n.getNext();
        }
        
        System.out.println(out);
    }
    
}
