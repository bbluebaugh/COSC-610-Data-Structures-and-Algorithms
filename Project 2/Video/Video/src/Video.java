import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

/*
 * This is a program to simulate a video store using an AVLTree and a Binary Search Tree to store the information about the video store
 * @author Brian Bluebaugh
 * @version 1.0 4/20/19
 */
public class Video {

	private static String datatype = "";//This is the datatype the user will be choosing for how to store the videos/customers/etc. in the video store
    private static int videoCount = 1;//This is the number of videos in the video store initialized to 1
    private static int customersCount = 1;//This is the number of customers the video store has initialized to 1
    private static AVLTree videosAVLTree;//This is the AVL Tree for the videos in the store
    private static AVLTree customersAVLTree;//This is the AVL Tree for the videos in the store
    private static BinarySearchTree videosBinarySearchTree;//This is the Binary Search Tree for the videos in the store
    private static BinarySearchTree customersBInarySearchTree;//This is the Binary Search Tree for the videos in the store
    private static DoublyLinkedList videosDoublyLinkedList;//This is the Doubly Linked List for the videos in the store
    private static DoublyLinkedList customersDoublyLinkedList;//This is the Doubly Linked List for the videos in the store
    private static SinglyLinkedList videosSinglyLinkedList;//This is the Singly Linked List for the videos in the store
    private static SinglyLinkedList customersSinglyLinkedList;//This is the Singly Linked List for the Customers in the store
    private static Stack<Integer> transactionStack;
    
	public static void main(String[] args) {
		Boolean continueProgram = false;//The flag to continue the program's execution
		
		if(args.length > 0) {//This ensures that we are accepting a valid argument so that the program may function correctly
            datatype = args[0];
        }
		/*
		 * This switches between the selected datatype the user has selected
		 * "SLL" gives you the choice of the Singly Linked List
		 * "DLL" gives you the choice of the Doubly Linked List
		 * "BST" gives you the choice of the Binary Search Tree
		 * "AVL" gives you the choice of the AVL Tree
		 * We also want to Continue the program from here as we are receiving legitimate data so we set the continueProgram boolean to true.
		 */
		
		switch(datatype) {
        case "SLL":            
            videosSinglyLinkedList = new SinglyLinkedList();
            customersSinglyLinkedList = new SinglyLinkedList();
            continueProgram = true;
            break;
        case "DLL":            
            videosDoublyLinkedList = new DoublyLinkedList();
            customersDoublyLinkedList = new DoublyLinkedList();
            continueProgram = true;
            break;
        case "BST":            
            videosBinarySearchTree = new BinarySearchTree();
            customersBInarySearchTree = new BinarySearchTree();
            continueProgram = true;
            break;
        case "AVL":            
            videosAVLTree = new AVLTree();
            customersAVLTree = new AVLTree();
            
            continueProgram = true;
            break;
        default:
            System.out.println("You can choose from any of the following data structures: SLL, DLL, BST, and AVL.");
            //continueProgram = true;
            break;
		}
		
		//if datatype was not chosen then we stop
        if(continueProgram == false) {
            quit();
        }
        
        int videosInStore = 0;//Initialize the amount of videos the store has with 0 as none have been added as of yet
        int customersOfStore = 0;//Initialize the amount of customers the store has to 0 as none have been added yet
        int transactionsCompleted = 0;//Initialize the amount of transactions which have been completed to 0 as none have been started yet
        
        //check if there are 3 more parameters being passed to the program
        //these parameters supply auto creations in the program
        if(args.length == 4) {
            videosInStore = Integer.parseInt(args[1]);
            customersOfStore = Integer.parseInt(args[2]);
            transactionsCompleted = Integer.parseInt(args[3]);
        }
        
        /*
         * print out the length of what was submitted
         * also print out what was submitted
         */
        System.out.println("Length: " + args.length);
        System.out.println("Videos: " + videosInStore + ", Customers: " + customersOfStore + ", Transactions: " + transactionsCompleted);
        
        /*
         * In the event there were not enough arguments put in then we will show the menu which will allow the user to try again
         * with the correct information, we will handle the information with the switch cases and "ifs" below
		 * The switch case idea came from the pdf document as it was easy to read made it easy to step through logically
         * The first if is in case the video store is completely empty then we want to initialize it with the new stuff being added
         * Otherwise we move to the else where we loop through the lists and trees to add things
         */
        if(videosInStore == 0 && customersOfStore == 0 && transactionsCompleted == 0) {
        	
        	//Start a scanner for the user to be able to type into the console so that he/she can use the menu properly
            Scanner input = new Scanner(new InputStreamReader(System.in));
            String userSelection;//this will be a variable to hold what the user selects from the input of the menu
            
            while(true) {
            	//call the menu method so that we can display the menu to the user so that they know what to pick and how to pick it
                menu();
                //get a response from the user by reading the next line from the console and set it to the suerSelection variable 
                userSelection = input.nextLine();
                
                switch(userSelection) {
                	//The user selects the first item in the menu and wants to add a video
                    case "1":
                        System.out.println("Please enter name of the video:");
                        
                        System.out.print("Name: ");
                        String videoTitle = input.nextLine().trim();//get rid of any white space at the beginning or end of the video title
                        
                        //increment the number of videos we have and pass it to the inputVideo method so it can be added to the list
                        inputVideo(videoCount++, videoTitle);
                        
                        break;
                    case "2":
                    	//User would like to delete a video
                        System.out.println("Enter the ID of the video you would like to delete:");
                        System.out.print("ID: ");
                        String videoID = input.nextLine().trim();
                        
                        while(!videoID.matches("^\\d+$")) {
                            System.out.println("ID's can only be numbers.");//if the video ID entered is not a number try again
                            System.out.print("ID: ");
                            videoID = input.nextLine().trim();//remove any whitespace around the video ID and store it in the ID variable
                        }
                        
                        removeVideo(Integer.parseInt(videoID));//call the remove method and pass it the video ID
                        
                        break;
                    case "3":
                    	//Add a customer
                        System.out.println("Enter the customer's name:");
                        System.out.print("Customer Name: ");
                        String customerName = input.nextLine().trim();//Remove white space of customers name save the name in a variable
                        
                        insertCustomer(customersCount++, customerName);//increment the customers in the store and pass the name to insertCustomer
                        
                        break;
                    case "4":
                    	//remove a customer
                        System.out.println("Enter the Customers' ID to be deleted:");
                        
                        System.out.print("ID: ");
                        String customerID = input.nextLine().trim();
                        
                        while(!customerID.matches("^\\d+$")) {
                            System.out.println("ID's can only be numbers.");//Customer ID only numbers otherwise try again
                            System.out.print("ID: ");
                            customerID = input.nextLine().trim();//trim white space from name and pass to remove method
                        }
                        
                        deleteCustomer(Integer.parseInt(customerID));
                        
                        break;
                    case "5":
                    	//search for a video in the store
                        System.out.println("Enter the ID of the video you would like to search for:");
                        
                        System.out.print("ID: ");
                        String case5VideoID = input.nextLine().trim();
                        
                        while(!case5VideoID.matches("^\\d+$")) {
                            System.out.println("ID's can only be number.");
                            System.out.print("ID: ");
                            case5VideoID = input.nextLine().trim();
                        }
                        
                        System.out.println(checkInStore(Integer.parseInt(case5VideoID)));
                        
                        break;
                    case "6":
                    	//Checkout a video
                        System.out.println("Enter the customer and video ID:");
                        
                        System.out.print("Customer ID: ");
                        String case6CustomerID = input.nextLine().trim();
                        
                        while(!case6CustomerID.matches("^\\d+$")) {
                            System.out.println("ID's can only be numbers.");
                            System.out.print("Customer ID: ");
                            case6CustomerID = input.nextLine().trim();
                        }
                        
                        System.out.print("Video ID: ");
                        String case6VideoID = input.nextLine().trim();
                        
                        while(!case6VideoID.matches("^\\d+$")) {
                            System.out.println("ID's can only be numbers.");
                            System.out.print("Video ID: ");
                            case6VideoID = input.nextLine().trim();
                        }
                        
                        videoCheckOut(Integer.parseInt(case6CustomerID), Integer.parseInt(case6VideoID));
                        
                        break;
                    case "7":
                    	//Check in a video
                        System.out.println("Enter the video ID:");
                        
                        System.out.print("ID: ");
                        String case7VideoID = input.nextLine().trim();
                        
                        while(!case7VideoID.matches("^\\d+$")) {
                            System.out.println("ID's can only be numbers.");
                            System.out.print("ID: ");
                            case7VideoID = input.nextLine().trim();
                        }
                        
                        //If the video is checked out, then check in
                        videoCheckIn(Integer.parseInt(case7VideoID));
                        
                        break;
                    case "8":
                    	//Print all the customers in the store
                    	//For the given datatype print everything inside the store
                        switch(datatype) {
                            case "SLL":
                            	customersSinglyLinkedList.print();
                                break;
                            case "DLL":
                            	customersDoublyLinkedList.print();
                                break;
                            case "BST":
                            	customersBInarySearchTree.print(customersBInarySearchTree.getRoot());
                                break;
                            case "AVL":
                            	customersAVLTree.print(customersAVLTree.getRoot());
                                break;
                            default:
                                break;
                        }
                        
                        break;
                    case "9":
                        //Print all the videos of the store
                        printCheckedIn();
                        printCheckedOut();
                        
                        break;
                    case "10":
                        //Print all in store videos, i.e available to rent videos
                        printCheckedIn();
                        
                        break;
                    case "11":
                        //Print all checked out videos
                        printCheckedOut();
                        
                        break;
                    case "12":
                    	//Print all the videos rented by a specific customer
                        System.out.println("Please enter the customer ID:");
                        
                        System.out.print("ID: ");
                        String case12CustomerID = input.nextLine().trim();
                        
                        while(!case12CustomerID.matches("^\\d+$")) {
                            System.out.println("ID's can only be numbers, please try again.");
                            System.out.print("ID: ");
                            case12CustomerID = input.nextLine().trim();
                        }
                        
                        //print checked out videos
                        printAtElement(Integer.parseInt(case12CustomerID));
                        
                        break;
                    case "13":
                    	//Exit the Store
                        System.out.println("End of Line.");
                        quit();
                        break;
                }
            }
        } else {
            //The previous If is for when there is nothing in the list, this is the case where there is stuff in the list
            for(int i = 0; i < videosInStore; i++) {
                inputVideo(videoCount++, "Video " + (i + 1));//increment through to add the new video
            }
            
            for(int i = 0; i < customersOfStore; i++) {
                insertCustomer(customersCount++, "Customer " + (i + 1));//increment through to add the customer
            }
            
            transactionStack = new Stack<>();
            for(int i = 0; i < transactionsCompleted; i++) {
                int random = 5 + (int)(Math.random() * ((7 - 5) + 1));//add a new transaction to the transaction stack
                transactionStack.push(random);
            }
            
            
            final long startTime = System.currentTimeMillis();//start a timer for the timing info later in the specification
            
            for(int i = 0; i < transactionsCompleted; i++) {
                int temp = transactionStack.pop();//temporary variable for holding the popped item for later use
                
                //Video is in store
                if(temp == 5) {
                    int videoRandom = 1 + (int)(Math.random() * ((videoCount - 1) + 1));
                    checkInStore(videoRandom);
                }
                
                //Video is being checked out
                if(temp == 6) {
                    int customerRandom = 1 + (int)(Math.random() * ((customersCount - 1) + 1));
                    int videoRandom = 1 + (int)(Math.random() * ((videoCount - 1) + 1));
                    videoCheckOut(customerRandom, videoRandom);
                }
                
                //Video is being checked in
                if(temp == 7) {
                    int videoRandom = 1 + (int)(Math.random() * ((videoCount - 1) + 1));
                    videoCheckIn(videoRandom);
                }
                
            }
            
            final long stopTime = System.currentTimeMillis();//variable for the stopping time to be compared to starting time to check
            													//the total time of execution
            System.out.println("Operation Took: " + ((stopTime - startTime) / 1000.0) + "seconds");//convert the time in milliseconds to
            																		//seconds by multiplying by 1000 which will return seconds

        }

	}
	
	/*
	 * This is the menu as specified in the project specification, upon running this will be displayed for the user to select
	 * what he/she wants to do with the video store
	 * Followed exactly the output in the example in the first document for the project specification
	 */
	private static void menu() {
        System.out.println("\n===========================");
        System.out.println("Select one of the following...");
        System.out.println("1: To add a video");
        System.out.println("2: To delete a video");
        System.out.println("3: To add a customer");
        System.out.println("4: To delete a customer");
        System.out.println("5: To check if a particular video is in store");
        System.out.println("6: To check out a video");
        System.out.println("7: To check in a video");
        System.out.println("8: To print all customers");
        System.out.println("9: To print all videos");
        System.out.println("10: To print in store videos");
        System.out.println("11: To print all rent videos");
        System.out.println("12: To print the videos rent by a customer");
        System.out.println("13: To exit");
        System.out.println("===========================");
    }
	/*
	 * These are the methods for the program which are not the data structures I.E the functionality of the store program
	 * This first one is for adding the videos to the respective data structure.
	 */
	private static void inputVideo(int id, String name) {
        switch(datatype) {
            case "SLL":
            	videosSinglyLinkedList.add(new SLNode(new Videos(id, name), null));
                break;
            case "DLL":
            	videosDoublyLinkedList.addLast(new DLNode(new Videos(id, name), null, null));
                break;
            case "BST":
            	videosBinarySearchTree.insert(id, new Videos(id, name));
                break;
            case "AVL":
            	videosAVLTree.setRoot(videosAVLTree.insert(videosAVLTree.getRoot(), id, new Videos(id, name)));
                break;
            default:
                break;
        }
    }
	
	//remove the video from the store
	private static Videos removeVideo(int id) {
        Videos element = null;
        int i;
        
        switch(datatype) {
            case "SLL":
                if(videosSinglyLinkedList.getHead() == null) {
                    return null;
                }
                
                SLNode previousSLL = videosSinglyLinkedList.getHead();
                SLNode currentSinglyLinkedList = videosSinglyLinkedList.getHead();
                
                element = (Videos) currentSinglyLinkedList.getElement();
                i = 0;
                
                while(currentSinglyLinkedList.getNext() != null) {
                    if(i != 0) {
                        previousSLL = currentSinglyLinkedList;
                        currentSinglyLinkedList = currentSinglyLinkedList.getNext();
                        element = (Videos) currentSinglyLinkedList.getElement();
                    }
                    
                    if(element.getId() == id) {
                    	videosSinglyLinkedList.removeNode(previousSLL, currentSinglyLinkedList);
                        break;
                    }
                    
                    i++;
                }
                
                if(currentSinglyLinkedList.getNext() == null && element.getId() == id) {
                	videosSinglyLinkedList.removeNode(previousSLL, currentSinglyLinkedList);
                }
                
                break;
            case "DLL":
                if(videosDoublyLinkedList.getHeader() == null) {
                    return null;
                }
                
                DLNode currentDoublyLinkedList = videosDoublyLinkedList.getHeader();
                
                element = (Videos) currentDoublyLinkedList.getElement();
                i = 0;
                
                while(currentDoublyLinkedList.getNextNode() != null) {
                    if(i != 0) {
                        currentDoublyLinkedList = currentDoublyLinkedList.getNextNode();
                        element = (Videos) currentDoublyLinkedList.getElement();
                    }
                    
                    if(element.getId() == id) {
                    	videosDoublyLinkedList.remove(currentDoublyLinkedList);
                        return element;
                    }
                    
                    i++;
                }
                
                if(currentDoublyLinkedList.getNextNode() == null && element.getId() == id) {
                	videosDoublyLinkedList.remove(currentDoublyLinkedList);
                }
                break;
            case "BST":
                return (Videos) videosBinarySearchTree.delete(id);
                //break;
            case "AVL":
                return (Videos) videosAVLTree.delete(id);
                //break;
            default:
                break;
        }
        
        return element;
    }
	
	//check if the video is in the store
	private static boolean checkInStore(int id) {
        Videos element;
        int i;
        
        switch(datatype) {
            case "SLL":
                if(videosSinglyLinkedList.getHead() == null) {
                    return false;
                }
                
                SLNode currentSinglyLinkedList = videosSinglyLinkedList.getHead();
                element = (Videos) currentSinglyLinkedList.getElement();
                i = 0;
                
                while(currentSinglyLinkedList.getNext() != null) {
                    if(i != 0) {
                        currentSinglyLinkedList = currentSinglyLinkedList.getNext();
                        element = (Videos) currentSinglyLinkedList.getElement();
                    }
                    
                    if(element.getId() == id) {
                        return true;
                    }
                    
                    i++;
                }
                
                if(currentSinglyLinkedList.getNext() == null && element.getId() == id) {
                    return true;
                }
                
                break;
            case "DLL":
                if(videosDoublyLinkedList.getHeader() == null || videosDoublyLinkedList.getTrailer() == null) {
                    return false;
                }
                
                DLNode currentDoublyLinkedList = videosDoublyLinkedList.getHeader();
                element = (Videos) currentDoublyLinkedList.getElement();
                i = 0;
                
                while(currentDoublyLinkedList.getNextNode() != null) {
                    if(i != 0) {
                        currentDoublyLinkedList = currentDoublyLinkedList.getNextNode();
                        element = (Videos) currentDoublyLinkedList.getElement();
                    }
                    
                    if(element.getId() == id) {
                        return true;
                    }
                    
                    i++;
                }
                
                if(currentDoublyLinkedList.getNextNode() == null && element.getId() == id) {
                    return true;
                }
                
                break;
            case "BST":
                return videosBinarySearchTree.find(id);
                //break;
            case "AVL":
                return videosAVLTree.find(id);
                //break;
            default:
                break;
        }
        
        return false;
    }
	
	//Add the given customer to the respective list
	private static void insertCustomer(int id, String name) {
        switch(datatype) {
            case "SLL":
            	customersSinglyLinkedList.add(new SLNode(new Customer(datatype, id, name), null));
                break;
            case "DLL":
            	customersDoublyLinkedList.addLast(new DLNode(new Customer(datatype, id, name), null, null));
                break;
            case "BST":
            	customersBInarySearchTree.insert(id, new Customer(datatype, id, name));
                break;
            case "AVL":
            	customersAVLTree.setRoot(customersAVLTree.insert(customersAVLTree.getRoot(), id, new Customer(datatype, id, name)));
                break;
            default:
                break;
        }
    }
	
	//remove the customer of the passed in ID number
	private static void deleteCustomer(int id) {
        Customer element;
        int i;
        
        switch(datatype) {
            case "SLL":
                if(customersSinglyLinkedList.getHead() == null) {
                    return;
                }
                
                SLNode previousSLL = customersSinglyLinkedList.getHead();
                SLNode currentSinglyLinkedList = customersSinglyLinkedList.getHead();
                
                element = (Customer) currentSinglyLinkedList.getElement();
                i = 0;
                
                while(currentSinglyLinkedList.getNext() != null) {
                    if(i != 0) {
                        previousSLL = currentSinglyLinkedList;
                        currentSinglyLinkedList = currentSinglyLinkedList.getNext();
                        element = (Customer) currentSinglyLinkedList.getElement();
                    }
                    
                    if(element.getId() == id) {
                    	customersSinglyLinkedList.removeNode(previousSLL, currentSinglyLinkedList);
                        break;
                    }
                    
                    i++;
                }
                
                if(currentSinglyLinkedList.getNext() == null && element.getId() == id) {
                	customersSinglyLinkedList.removeNode(previousSLL, currentSinglyLinkedList);
                }
                
                break;
            case "DLL":
                if(customersDoublyLinkedList.getHeader() == null) {
                    return;
                }
                
                DLNode currentDoublyLinkedList = customersDoublyLinkedList.getHeader();
                
                element = (Customer) currentDoublyLinkedList.getElement();
                i = 0;
                
                while(currentDoublyLinkedList.getNextNode() != null) {
                    if(i != 0) {
                        currentDoublyLinkedList = currentDoublyLinkedList.getNextNode();
                        element = (Customer) currentDoublyLinkedList.getElement();
                    }
                    
                    if(element.getId() == id) {
                    	customersDoublyLinkedList.remove(currentDoublyLinkedList);
                    }
                    
                    i++;
                }
                
                if(currentDoublyLinkedList.getNextNode() == null && element.getId() == id) {
                	customersDoublyLinkedList.remove(currentDoublyLinkedList);
                }
                
                break;
            case "BST":
            	customersBInarySearchTree.delete(id);
                break;
            case "AVL":
            	customersAVLTree.delete(id);
                break;
            default:
                break;
        }
    }
	
	//if the customer ID that is provided is valid return true
	private static boolean validCustomer(int id) {
        return getCustomer(id) != null;
    }
	
	//get the customer of the passed in ID
	private static Customer getCustomer(int id) {
        Customer element;
        int i;
        
        switch(datatype) {
            case "SLL":
                if(customersSinglyLinkedList.getHead() == null) {
                    return null;
                }
                
                SLNode currentSinglyLinkedList = customersSinglyLinkedList.getHead();
                element = (Customer) currentSinglyLinkedList.getElement();
                i = 0;
                
                while(currentSinglyLinkedList.getNext() != null) {
                    if(i != 0) {
                        currentSinglyLinkedList = currentSinglyLinkedList.getNext();
                        element = (Customer) currentSinglyLinkedList.getElement();
                    }
                    
                    if(element.getId() == id) {
                        return element;
                    }
                    
                    i++;
                }
                
                if(currentSinglyLinkedList.getNext() == null && element.getId() == id) {
                    return element;
                }
                
                break;
            case "DLL":
                if(customersDoublyLinkedList.getHeader() == null) {
                    return null;
                }
                
                DLNode currentDoublyLinkedList = customersDoublyLinkedList.getHeader();
                element = (Customer) currentDoublyLinkedList.getElement();
                i = 0;
                
                while(currentDoublyLinkedList.getNextNode() != null) {
                    if(i != 0) {
                        currentDoublyLinkedList = currentDoublyLinkedList.getNextNode();
                        element = (Customer) currentDoublyLinkedList.getElement();
                    }
                    
                    if(element.getId() == id) {
                        return element;
                    }
                    
                    i++;
                }
                
                if(currentDoublyLinkedList.getNextNode() == null && element.getId() == id) {
                    return element;
                }
                
                break;
            case "BST":
                return (Customer) customersBInarySearchTree.getElement(id);
                //break;
            case "AVL":
                return (Customer) customersAVLTree.getElement(id);
                //break;
            default:
                break;
        }
        
        return null;
    }
	
	//check the video out of the store
	private static void videoCheckOut(int cid, int vid) {
        //System.out.println("videoCheckOut");
        if(!checkInStore(vid) || !validCustomer(cid)) {
            return;
        }
        
        Videos videoElement = removeVideo(vid);
        getCustomer(cid).inputVideo(videoElement);
    }
	
	//Check the video into the store
	private static void videoCheckIn(int vid) {
        if(checkInStore(vid)) {
            return;
        }
        
        for(int i = 1; i < customersCount; i++) {
            if(getCustomer(i).inPossession(vid)) {
                Videos element = getCustomer(i).removeVideo(vid);
                inputVideo(element.getId(), element.getTitle());
                return;
            }
        }
    }
	
	//Print the videos that are in the store
	private static void printCheckedIn() {
        switch(datatype) {
            case "SLL":
            	videosSinglyLinkedList.noNull();
                break;
            case "DLL":
            	videosDoublyLinkedList.noNull();
                break;
            case "BST":
            	videosBinarySearchTree.print(videosBinarySearchTree.getRoot());
                break;
            case "AVL":
            	videosAVLTree.print(videosAVLTree.getRoot());
                break;
            default:
                break;
        }
    }
	
	//Print the videos that have been checked out
	private static void printCheckedOut() {
        for(int i = 1; i <= customersCount; i++) {
            printAtElement(i);
        }
    }
	
	//if the customer ID is valid then we can print the videos he/she has checked out
	private static void printAtElement(int id) {
        if(!validCustomer(id)) {
            return;
        }
        
        getCustomer(id).printVideos();
    }
	
	
	/*
	 * This is the method to quit using the program
	 */
	private static void quit() {
        System.exit(0);
    }

}
