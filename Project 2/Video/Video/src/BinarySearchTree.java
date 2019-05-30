/*
 * This is a class for the Binary Search Tree algorithm which implements specific parts to solve the specification
 */
public class BinarySearchTree {
private BSNode root;
    
	//initialize constructor of the Binary Search Tree
    public BinarySearchTree() {
        root = null;
    }
    
    //Get the root of the tree similar to getHead()/getHeader() in Singly Linked List/Doubly Linked List
    public BSNode getRoot() {
        return root;
    }
    
    //find the node with the given ID
    public boolean find(int id) {
        BSNode current = root;
        
        //while there is still tree to traverse continue
        while(current != null) {
            if(current.getId() == id) {
                return true;
            } else if(current.getId() > id) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
        }
        //if it is not in the tree it does not exist
        return false;
    }
    
    //get the element from the passed node
    public Object getElement(int id) {
        BSNode current = root;
        
        //while there is still more to traverse continue
        while(current != null) {
            if(current.getId() == id) {
                return current.getElement();
            } else if(current.getId() > id) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
        }
        
        return null;
    }
    
    //traverse the tree to find the node with the passed ID to remove it
    public Object delete(int id) {
        BSNode parent = root;
        BSNode current = root;
        boolean isLeftChild = false;
        Object element = null;
        
        while(current.getId() != id) {
            parent = current;
            
            if(current.getId() > id) {
                isLeftChild = true;
                current = current.getLeftChild();
            } else {
                isLeftChild = false;
                current = current.getRightChild();
            }
            
            if(current == null) {
                return element;
            }
        }
        element = current.getElement();
        
        //case 1: if node to be deleted has no children
        if(current.getLeftChild() == null && current.getRightChild() == null) {
            if(current == root) {
                root = null;
            }
            
            if(isLeftChild == true) {
                parent.setLeftChild(null);
            } else {
                parent.setRightChild(null);
            }
        }
        
        //case 2: if node to be deleted has only one child
        else if(current.getRightChild() == null) {
            if(current == root) {
                root = current.getLeftChild();
            } else if(isLeftChild) {
                parent.setLeftChild(current.getLeftChild());
            } else {
                parent.setRightChild(current.getLeftChild());
            }
        } else if(current.getLeftChild() == null) {
            if(current == root) {
                root = current.getRightChild();
            } else if(isLeftChild) {
                parent.setLeftChild(current.getRightChild());
            } else {
                parent.setRightChild(current.getRightChild());
            }
        } else if(current.getLeftChild() != null && current.getRightChild() != null) {
            //now we have found the minimum element in the right subtree
            BSNode successor = getSuccessor(current);
            
            if(current == root) {
                root = successor;
            } else if(isLeftChild) {
                parent.setLeftChild(successor);
            } else {
                parent.setRightChild(successor);
            }
            
            successor.setLeftChild(current.getLeftChild());
        }
        
        return element;
    }
    
    //get the successor node of the current node
    public BSNode getSuccessor(BSNode n) {
        BSNode successor = null;
        BSNode successorParent = null;
        BSNode current = n.getRightChild();
        
        while(current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeftChild();
        }
        
        //check if successor has the right child, it cannot have left child for sure
        //if it does have the right child, add it to the left of successorParent.
        if(successor != n.getRightChild()) {
            successorParent.setLeftChild(successor.getRightChild());
            successor.setRightChild(n.getRightChild());
        }
        
        return successor;
    }
    
    //insert new node with passed ID and element
    public void insert(int id, Object element) {
        BSNode n = new BSNode(id, element);
        
        if(root == null) {
            root = n;
            return;
        }
        
        //start at the root of the tree and traverse down
        BSNode current = root;
        BSNode parent;
        
        while(true) {
            parent = current;
            
            if(id < current.getId()) {
                current = current.getLeftChild();
                
                if(current == null) {
                    parent.setLeftChild(n);
                    return;
                }
            } else {
                current = current.getRightChild();
                
                if(current == null) {
                    parent.setRightChild(n);
                    return;
                }
            }
        }
    }
    
    //Print the current element
    public void print(BSNode n) {
        if(n != null) {
            print(n.getLeftChild());
            System.out.print(n.getElement() + "\n");
            print(n.getRightChild());
        }
    }
    
}
