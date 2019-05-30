/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author bwbluebaugh0
 */
public class Node {
    private Object element;
    private Node next;
    
    public Node(Object element, Node next){
        this.element = element;
        this.next = next;
    }
    
    public Object getElement(){
        return element;
    }
    
    public Node getNext(){
        return next;
    }
    
    public void setElement(Object element){
        this.element = element;
    }
    
    public void setNext(Node next){
        this.next = next;
    }
    
    private Node head;
    private Node tail;
}
