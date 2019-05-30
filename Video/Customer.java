/*
 * This is a class for the Customers of the store it initializes everything which will be done related to the customer
 * I.E. Customer ID, Customer Name, What videos the customer has rented, etc.
 */
public class Customer {
	private int id;
    private String name;
    private final String datatype;
    private SinglyLinkedList videosSinglyLinkedList;
    private DoublyLinkedList videosDoublyLinkedList;
    private BinarySearchTree videosBinarySearchTree;
    private AVLTree videosAVLTree;
    
    /*
     * Builds the Customer object.
     * @param datatype The chosen data structure for this execution.
     * @param id The id for this customer.
     * @param name The name of this customer.
     */
    public Customer(String datatype, int id, String name) {
        this.datatype = datatype;
        this.id = id;
        this.name = name;
        
        switch(datatype) {
            case "SinglyLinkedList":
                videosSinglyLinkedList = new SinglyLinkedList();
                break;
            case "DoublyLinkedList":
                videosDoublyLinkedList = new DoublyLinkedList();
                break;
            case "BinarySearchTree":
                videosBinarySearchTree = new BinarySearchTree();
                break;
            case "AVLTree":
                videosAVLTree = new AVLTree();
                break;
            default:
                break;
        }
    }
    
    /*
     * Returns the customer ID.
     * @return Customer ID.
     */
    public int getId() {
        return id;
    }
    
    /*
     * Returns the name of the customer.
     * @return Customer name.
     */
    public String getName() {
        return name;
    }
    
    /*
     * Returns the customers rented videos in a SinglyLinkedList.
     * @return The rented videos.
     */
    public SinglyLinkedList getRentVideosSLL() {
        return videosSinglyLinkedList;
    }
    
    /*
     * Returns the customers rented videos in a DoublyLinkedList.
     * @return The rented videos.
     */
    public DoublyLinkedList getRentVideosDLL() {
        return videosDoublyLinkedList;
    }
    
    /*
     * Returns the customers rented videos in a BinarySearchTree.
     * @return The rented videos.
     */
    public BinarySearchTree getRentVideosBST() {
        return videosBinarySearchTree;
    }
    
    /*
     * Returns the customers rented videos in a AVLTree.
     * @return The rented videos.
     */
    public AVLTree getRentVideosAVL() {
        return videosAVLTree;
    }
    
    /*
     * Adds a video to the customers rented video list.
     * @param vid The video to be added.
     */
    public void inputVideo(Videos vid) {
        switch(datatype) {
            case "SinglyLinkedList":
                videosSinglyLinkedList.add(new SLNode(new Videos(vid.getId(), vid.getTitle()), null));
                break;
            case "DoublyLinkedList":
                videosDoublyLinkedList.addLast(new DLNode(new Videos(vid.getId(), vid.getTitle()), null, null));
                break;
            case "BinarySearchTree":
                videosBinarySearchTree.insert(vid.getId(), new Videos(vid.getId(), vid.getTitle()));
                break;
            case "AVLTree":
                videosAVLTree.setRoot(videosAVLTree.insert(videosAVLTree.getRoot(), vid.getId(), new Videos(vid.getId(), vid.getTitle())));
                break;
            default:
                break;
        }
    }
    
    public Videos removeVideo(int id) {
        Videos element = null;
        int i;
        
        switch(datatype) {
            case "SinglyLinkedList":
                if(videosSinglyLinkedList.getHead() == null) {
                    return null;
                }
                
                SLNode previousSLL = videosSinglyLinkedList.getHead();
                SLNode currentSLL = videosSinglyLinkedList.getHead();
                
                element = (Videos) currentSLL.getElement();
                i = 0;
                
                while(currentSLL.getNext() != null) {
                    if(i != 0) {
                        previousSLL = currentSLL;
                        currentSLL = currentSLL.getNext();
                        element = (Videos) currentSLL.getElement();
                    }
                    
                    if(element.getId() == id) {
                        videosSinglyLinkedList.removeNode(previousSLL, currentSLL);
                        break;
                    }
                    
                    i++;
                }
                
                if(currentSLL.getNext() == null && element.getId() == id) {
                    videosSinglyLinkedList.removeNode(previousSLL, currentSLL);
                }
                
                break;
            case "DoublyLinkedList":
                if(videosDoublyLinkedList.getHeader() == null) {
                    return null;
                }
                
                DLNode currentDLL = videosDoublyLinkedList.getHeader();
                
                element = (Videos) currentDLL.getElement();
                i = 0;
                
                while(currentDLL.getNextNode() != null) {
                    if(i != 0) {
                        currentDLL = currentDLL.getNextNode();
                        element = (Videos) currentDLL.getElement();
                    }
                    
                    if(element.getId() == id) {
                        videosDoublyLinkedList.remove(currentDLL);
                        return element;
                    }
                    
                    i++;
                }
                
                if(currentDLL.getNextNode() == null && element.getId() == id) {
                    videosDoublyLinkedList.remove(currentDLL);
                }
                break;
            case "BinarySearchTree":
                return (Videos) videosBinarySearchTree.delete(id);
                //break;
            case "AVLTree":
                return (Videos) videosAVLTree.delete(id);
                //break
            default:
                break;
        }
        
        return element;
    }
    
    /*
     * Looks for a video with a given ID and returns whether it can be found.
     * @param id The ID of the video being looked up.
     * @return True or false, depending on whether the video is found or not.
     */
    public boolean inPossession(int id) {
        Videos element;
        int i;
        
        switch(datatype) {
            case "SinglyLinkedList":
                if(videosSinglyLinkedList.getHead() == null) {
                    return false;
                }
                
                SLNode currentSLL = videosSinglyLinkedList.getHead();
                element = (Videos) currentSLL.getElement();
                i = 0;
                
                while(currentSLL.getNext() != null) {
                    if(i != 0) {
                        currentSLL = currentSLL.getNext();
                        element = (Videos) currentSLL.getElement();
                    }
                    
                    if(element.getId() == id) {
                        return true;
                    }
                    
                    i++;
                }
                
                if(currentSLL.getNext() == null && element.getId() == id) {
                    return true;
                }
                
                break;
            case "DoublyLinkedList":
                if(videosDoublyLinkedList.getHeader() == null || videosDoublyLinkedList.getTrailer() == null) {
                    return false;
                }
                
                DLNode currentDLL = videosDoublyLinkedList.getHeader();
                element = (Videos) currentDLL.getElement();
                i = 0;
                
                while(currentDLL.getNextNode() != null) {
                    if(i != 0) {
                        currentDLL = currentDLL.getNextNode();
                        element = (Videos) currentDLL.getElement();
                    }
                    
                    if(element.getId() == id) {
                        return true;
                    }
                    
                    i++;
                }
                
                if(currentDLL.getNextNode() == null && element.getId() == id) {
                    return true;
                }
                
                break;
            case "BinarySearchTree":
                return videosBinarySearchTree.find(id);
                //break
            case "AVLTree":
                return videosAVLTree.find(id);
                //break;
            default:
                break;
        }
        
        return false;
    }
    
    /*
     * Prints out all videos in the customers possession.
     */
    public void printVideos() {
        switch(datatype) {
            case "SinglyLinkedList":
                videosSinglyLinkedList.noNull();
                break;
            case "DoublyLinkedList":
                videosDoublyLinkedList.noNull();
                break;
            case "BinarySearchTree":
                videosBinarySearchTree.print(videosBinarySearchTree.getRoot());
                break;
            case "AVLTree":
                videosAVLTree.print(videosAVLTree.getRoot());
                break;
            default:
                break;
        }
    }
    
    /*
     * Returns a string of all information for the customer.
     * @return All customer information.
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name;
    }
    
}