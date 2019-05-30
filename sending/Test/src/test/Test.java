/*
*Addititon program
*@author Brian
*version 1.0
 */
package test;

/**
 *
 * @author bwbluebaugh0
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Node a = new Node(1, null);
        Node b = new Node(2, null);
        Node c = new Node(3, null);
        
        SLL s = new SLL();
        
        s.print();
        
        s.add(a);
        s.add(b);
        s.add(c);
        
        s.print();
        s.remove();
        s.print();
        s.reverse();
        s.print();
    }
    
}
