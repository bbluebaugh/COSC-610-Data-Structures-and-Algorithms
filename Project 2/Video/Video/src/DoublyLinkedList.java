/*
 * This is a doubly linked list just like the one created earlier in the semester for the homework
 * some things have been modified but in general it is the same
 */
public class DoublyLinkedList {
	private DLNode header;
    private DLNode trailer;
    
    /*
     * Create the Doubly linked list
     * initialize it like the singly linked list by making two nodes header and trailer and set to null
     */
    public DoublyLinkedList() {
        header = null;
        trailer = null;
    }
    
    /*
     * Get the header and return it.
     * @return The header.
     */
    public DLNode getHeader() {
        return header;
    }
    
    /*
     * Get the trailer and return it.
     * @return The trailer.
     */
    public DLNode getTrailer() {
        return trailer;
    }
    
	/*
	 * Set the header to the supplied node.
     * @param n Node to be set to header.
	 */
    public void setHeader(DLNode n) {
        header = n;
    }
    
    /*
     * Set the trailer to the supplied node.
     * @param n Node to be set to the trailer.
     */
    public void setTrailer(DLNode n) {
        trailer = n;
    }
    
    /*
     * Print the Doubly linked list
     */
    public void print() {
        String out = "";
        
        DLNode n = header;
        
        if(n == null) {
            out = "0 nodes.";
        }
        
        while(n != null) {
            out +=  n.getElement();
            
            if(n.getNextNode() != null) {
                out += "\n";
            }
            
            n = n.getNextNode();
        }
        
        System.out.println(out);
    }
    
    /*
     * Print the DLL without the possibility of a null printout.
     */
    public void noNull() {
        String out = "";
        
        DLNode n = header;
        
        if(n == null) {
            return;
        }
        
        while(n != null) {
            out +=  n.getElement();
            
            if(n.getNextNode() != null) {
                out += "\n";
            }
            
            n = n.getNextNode();
        }
        
        System.out.println(out);
    }
    
    /*
     * Add item to the beginning of the list.
     * @param n Supplied node to add.
     */
    public void addFirst(DLNode n) {
        if(header == null) {
            header = n;
            trailer = n;
        } else {
            n.setNextNode(header);
            header.setPreviousNode(n);
            header = n;
        }
    }
    
    /*
     * Add item to the end of the list.
     * 
     * @param n Supplied node to add.
     */
    public void addLast(DLNode n) {
        if(header == null) {
            header = n;
            trailer = n;
        } else {
            n.setPreviousNode(trailer);
            trailer.setNextNode(n);
            trailer = n;
        }
    }
    
    /*
     *Removes the given item from the Doubly linked list
     *@param n The given item to be removed.
     */
    public void remove(DLNode n) {
        if(header.getNextNode() == null && trailer.getPreviousNode() == null) {
            header = null;
            trailer = null;
        } else {
            DLNode temp = header;
            
            while(n.getElement() != temp.getElement()) {
                temp = temp.getNextNode();
            }
            
            DLNode pre = temp.getPreviousNode();
            DLNode pos = temp.getNextNode();
            
            if(header == temp) {
                pos.setPreviousNode(null);
                header = pos;
            } else if(trailer == temp) {
                pre.setNextNode(null);
                trailer = pre;
            } else {
                pre.setNextNode(pos);
                pos.setPreviousNode(pre);
            }
        }
    }
    
}
