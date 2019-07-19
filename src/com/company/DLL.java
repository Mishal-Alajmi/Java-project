/*
*  Mishal Alajmi
*  CS202
*  Homework #5
*  03/17/2017
*
*  The DLL class will build a doubly linked list of DllNodes, it will sort the list by name lexicographically. It will
*  have two DllNode references a head and a tail, and two ints a current index and current max. Current index will hold
*  the returned value of the storeReview() method from the DllNode class, which will then be used to determine whether
*  a new node is needed or not, the current max will be the maximum value which the current index cannot exceed if it does
*  it will create a new node and start the insert function from the start. It will have methods to retrieve,remove,display
*  , display single reviewer and remove the entire list.
*/

package com.company;

// DLL class
class DLL {

    private DllNode head;
    private DllNode tail;
    private int currentIndex; // current index will act as the index of the array inside of the DllNode class
    private final int currentMax = 5; // same with the current max, it will also act as the max inside of the DllNode class


    // default constructor
    public DLL(){
        this.head = null;
        this.tail = null;
        this.currentIndex = 0;
    }
    // insertReview() takes two DllNode references and a User reference and returns a DllNode reference, the second DllNode
    // reference is essentially going to be the previous pointer to connect to once head.goNext() is invoked, At first,
    // it will be null, but after the first recursive call the insertReview() will be passed heads location to connect
    // previous to. It will first check if currentIndex is less than 0, which means something is wrong and we have to return
    // null from the function, then it will check if head is null and create a new node, store the information, connect
    // to the previous(null for the first invocation) and update tail and then return head. Then, it will check if the
    // passed in User's name is the same as the current one and if current index is greater than 0 but less than current max
    // if both conditions are true, it will continue pushing in that array and updating current index. It will then check
    // if the passed in User reference is less than head, it will then insert a new DllNode before head and connect up.
    // Last check is if the passed in User's name is greater than head's name, it will then traverse to the next reference
    // connecting up along the way. It will return head at the end.
    public DllNode insertReview(DllNode head,DllNode current,User toInsert){
        DllNode temp; // This will be used to insert a node before head, if the data is less than head's data.
        if(currentIndex < 0){ // check if currentIndex is negative, which means something went wrong
            return null;
        }
        else if(head == null){
            head = new DllNode(); // create a new empty node
            head.setPrevious(current); // connect to the previous node(first one is null)
            currentIndex = head.storeReview(toInsert); // store the information passed in by calling the storeReview()
            tail = head;                                // method, and updating currentIndex. Then, updating tail
            return head;// return head after everything is complete.
        }
        else if(head.compareReview(toInsert) == 1 && currentIndex > 0 && currentIndex < currentMax){
           currentIndex = head.storeReview(toInsert); // If all conditions are true continue pushing to the array
                                                      // and updating current index.
        }
        else if(head.compareReview(toInsert) < 0 ){ // if the passed in User's name is less than head
            temp = new DllNode(); // create a new temp DllNode
            currentIndex = temp.storeReview(toInsert); // call the storeReview() method, and update currentIndex.
            temp.setNext(head); // connect temp's next reference to head
            temp.setPrevious(head.goPrevious()); // connect temp's previous to head's previous reference to temp.
            if(head.goPrevious() != null) { // check if head is not the first in the list
                head.goPrevious().setNext(temp); // connect its previous's next's reference if it isn't
            }
            head.setPrevious(temp); // otherwise set head's previous reference to temp
            head = temp; // update head's location
            return head; // return head
        }
        else if(head.compareReview(toInsert) > 1 || currentIndex == currentMax){ // If either of these conditions are true
            head.setNext(insertReview(head.goNext(),head,toInsert)); // traverse to the next pointer and connect head's
        }                                                            // next to it.
        return head; // return head when done.
    }
    // wrapper method for the recursive insertReview() method, it will kickstart it from main.
    public boolean insertReview(User toInsert){
        this.head = insertReview(head,null,toInsert); // call the function and set head to its value
        if(head == null){
            return false;
        }
        else{
            return true;
        }
    }
    // displayReviews() takes a DllNode reference and returns a DllNode reference as well, it will traverse the list
    // and display each node along the way.
    public DllNode displayReviews(DllNode head){
        if(head == null){
            return null;
        }
        head.displayNode();
        return displayReviews(head.goNext());
    }
    // wrapper method for the recursive displayReviews() method, it will kickstart it from main.
    public boolean displayReviews(){
        if(displayReviews(head)==null){
            return false;
        }
        else{
            return true;
        }
    }
   // retrieveReview() takes a DllNode and String references and User array as parameters and returns a DllNode,
   // it will first check if head is null and return null if it is. It will then traverse the list until a match is found
   // and call the DllNode's copyReview() method passing in the User array to copy into it, it will return head after it's
   // done.
   public DllNode retrieveReview(DllNode head,String reviewerName,User [] retrieveTo){
       if(head == null){
           return null;
       }
       else if(head.compareReview(reviewerName) ==1){
           head.copyReviews(retrieveTo);
           return head;
       }
       retrieveReview(head.goNext(),reviewerName,retrieveTo);
       return head;
    }
    // wrapper method for the recursive retrieveReview() method, it will kickstart it from main.
    public boolean retrieveReview(String reviewerName,User [] retrieveTo){
       retrieveReview(head,reviewerName,retrieveTo);
       if(retrieveTo == null){
           return false;
       }
       else{
           return true;
       }
    }
    // removeReview() method takes a DllNode and String references and returns DllNode, it will traverse the list until
    // a match is found. It wil check if match is the last node in the list, it will update tail to the previous node
    // and set head to null if it is. Otherwise, it will set a temp reference to the next node update the connection and
    // set head to null and then update head to the location of the temp pointer.
    public DllNode removeReview(DllNode head,String reviewerName){
        DllNode temp; // temp reference to hold the match's next node
        if(head == null){
            return null;
        }

        else if(head.compareReview(reviewerName) == 1){ // if match is found
            if(head.goNext() == null){ // first check if it is the last node
                tail = head.goPrevious(); // move tail back
                head = null; // set head to null
            }
            else{ // otherwise
                temp = head.goNext(); // set the temp reference to the next node
                head.goPrevious().setNext(temp); // move head's previous's next reference to temp
                temp.setPrevious(head.goPrevious()); // move temp's previous reference to head's previous
                head = null; // remove the node
                head = temp; // update head
            }
            return head; // return head after removing the node
        }
        head.setNext(removeReview(head.goNext(),reviewerName));
        return head;
    }
    // Wrapper method for the recursive removeReview() method, it will kickstart it from main
    public boolean removeReview(String reviewerName){
        this.head = removeReview(head,reviewerName);
        if(head == null){
            return false;
        }
        else{
            return true;
        }
    }
    // removeAllReviews() will return a void and doesn't take any arguments, it will set head to null deleting the list.
    public void removeAllReviews(){
        this.head = null;
    }
    // displayReviewer() takes a DllNode and a String reference and returns a DllNode, it will traverse the list until
    // a match is found. If a match is found it will call the DllNode's displayNode() method and return head after that.
    public DllNode displayReviewer(DllNode head,String reviewerName){
        if(head == null){
            return null;
        }

        else if(head.compareReview(reviewerName) == 1){
            head.displayNode();
            return head;
        }
        displayReviewer(head.goNext(),reviewerName);
        return head;
    }
    // displayReviewer() is a wrapper method for the recursive displayReviewer() method, it will kickstart it from main.
    public boolean displayReviewer(String reviewerName){
        if(displayReviewer(head,reviewerName) == null){
            return false;
        }
        else{
            return true;
        }
    }
}
