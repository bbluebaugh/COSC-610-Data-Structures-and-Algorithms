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
public class SLL {
    private Node head; //stores head reference
    private Node tail;
    
    public SLL(){//constructor
        head = tail = null;
    }
    
    public Node getHead(){//accessor method return head node
        return head;
    }
    
    public void setHead(Node head){//mutator method to set the initialize the head node
        this.head = head;
    }
    
    public void print(){//print elements in the list
        Node current = head;
        while(current != null){
            System.out.println(current.getElement());//print data in current node
            current = current.getNext();//change to next node
        }
        System.out.println();
    }
    
    public void add(Node n){//add node n to the tail of the list
        if(head == null){//if no node exists n is head
            head = n;
        }else{
            Node current = head;//search for tail node
            while(current.getNext() != null){//while current node is not the tail node
                current = current.getNext();
            }
            //current is the tail node
            current.setNext(n);//set current's next to be the n node
        }
        n.setNext(null);//set n's next to be null
    }
    
    public void remove(){
        if(head == null){
            return;
        }
        Node current = head;
        Node prev = null;
        while(current.getNext() != null){
            prev = current;
            current = current.getNext();
        }
        prev.setNext(null);//remove the tail node
        if(prev == null){
            head = null;
        }
        else{
            prev = prev.getNext();
            prev = null;
        }
    }
    
    public void reverse(){
        if(head == null){
            return;
        }
        Node before = null;
        Node tmp = head;
        while(tmp != null){
            Node next = tmp.getNext();
            tmp.setNext(before);
            before = tmp;
            //prev = current;
            tmp = next; 
        }
        head = before;
    }

    /*
    public void reverse(Node node){
        Node prev = null;
        Node current = node;
        Node next = null;
        while(current != null){
            next = current.next;
            current.next = prev;
            current = next;
        }
        node = prev;
    }
*/
}
