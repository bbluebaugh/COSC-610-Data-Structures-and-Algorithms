/*
 * This is an AVLTree class for the AVL Tree Algorithm implemented as specified in the program specification
 */
public class AVLTree {
private AVLNode root;
    
	//initialize constructor of AVL Tree
    public AVLNode getRoot() {
        return root;
    }
    
    //initialize the root of the tree
    public void setRoot(AVLNode n) {
        root = n;
    }
    
    //search the tree for the element you are looking for
    public boolean find(int id) {
        AVLNode current = root;
        
        while(current != null) {
            if(current.getId() == id) {
                return true;
            } else if(current.getId() > id) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
        }
        
        return false;
    }
    
    //return the element at current node if there is anything
    public Object getElement(int id) {
        AVLNode current = root;
        
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
    
    //removing a node from the tree may require balancing after removal, also special cases for
    //when the node has children so must check for that as well
    public Object delete(int id) {
        AVLNode parent = root;
        AVLNode current = root;
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
            AVLNode successor = getSuccessor(current);
            
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
    
    //get the children of the current node, this is the same as for Binary Search Tree
    public AVLNode getSuccessor(AVLNode n) {
        AVLNode successor = null;
        AVLNode successorParent = null;
        AVLNode current = n.getRightChild();
        
        while(current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeftChild();
        }
        
        //check if successor has the right child, it cannot have left child for sure
        //if it does have the right child, and it to the left of successorParent.
        if(successor != n.getRightChild()) {
            successorParent.setLeftChild(successor.getRightChild());
            successor.setRightChild(n.getRightChild());
        }
        
        return successor;
    }
    
    //check the tree for balance
    public int balance(AVLNode n) {
        if(n != null) {
            return (getHeight(n.getLeftChild()) - getHeight(n.getRightChild()));
        }
        
        return 0;
    }
    
    //traverse the AVL Tree to find the height of the Tree, primarily used for balancing
    public int getHeight(AVLNode n) {
        if(n != null) {
            return n.getHeight();
        }
        
        return 0;
    }
    
    //rotate the sub-tree right for balancing
    public AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.getLeftChild();
        AVLNode T2 = x.getRightChild();
        
        //rotation
        x.setRightChild(y);
        y.setLeftChild(T2);
        
        //update heights
        x.setHeight(Math.max(getHeight(x.getLeftChild()), getHeight(x.getRightChild())) + 1);
        y.setHeight(Math.max(getHeight(y.getLeftChild()), getHeight(y.getRightChild())) + 1);
        
        return x;
    }
    
    //rotate the sub-tree left for balancing
    public AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.getRightChild();
        AVLNode T2 = y.getLeftChild();
        
        //rotation
        y.setLeftChild(x);
        x.setRightChild(T2);
        
        //update heights
        x.setHeight(Math.max(getHeight(x.getLeftChild()), getHeight(x.getRightChild())) + 1);
        y.setHeight(Math.max(getHeight(y.getLeftChild()), getHeight(y.getRightChild())) + 1);
        
        return y;
    }
    
    //insert the node into the tree, this requires movement of the tree including rotations
    public AVLNode insert(AVLNode n, int id, Object obj) {
        if(n == null) {
            return new AVLNode(id, obj);
        }
        
        if(n.getId() > id) {
            n.setLeftChild(insert(n.getLeftChild(), id, obj));
        } else {
            n.setRightChild(insert(n.getRightChild(), id, obj));
        }
        
        //update the node height
        n.setHeight(Math.max(getHeight(n.getLeftChild()), getHeight(n.getRightChild())) + 1);
        
        int bal = balance(n);
        
        //left rotate
        if(bal > 1 && id < n.getLeftChild().getId()) {
            return rotateRight(n);
        }
        
        //right rotate
        if(bal < -1 && id > n.getRightChild().getId()) {
            return rotateLeft(n);
        }
        
        //left right rotate
        if(bal > 1 && id > n.getLeftChild().getId()) {
            n.setLeftChild(rotateLeft(n.getLeftChild()));
            return rotateRight(n);
        }
        
        //right left rotate
        if(bal < -1 && id < n.getRightChild().getId()) {
            n.setRightChild(rotateRight(n.getRightChild()));
            return rotateLeft(n);
        }
        
        return n;
    }
    
    //Print the elements of the Tree
    public void print(AVLNode n) {
        if(n != null) {
            print(n.getLeftChild());
            System.out.print(n.getElement() + "\n");
            print(n.getRightChild());
        }
    }
    
}
