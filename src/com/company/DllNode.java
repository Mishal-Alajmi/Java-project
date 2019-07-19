/*
* Mishal Alajmi
* CS202
* Homework #5
* 03/17/2017
*
* The DllNode class will act as the interface between the User and the DLL list, and it will have two Dll references a
* next and a previous, a User reviews array that will hold multiple Users, a final int to dictate the maximum size
* and an topIndex to act as the index for the User array. It will have two getter methods that will return the node's
* next or previous references, and two setters to set the node's next or previous references. It will also have
* a method to create an array of Users and store information in its indices, it will also have methods to compare the
* User's in the array with a passed in User or String references. It will have a method to display the User array, and
* finally a method to copy the User array into a passed in one.
*/

package com.company;
// DllNode class
class DllNode{

    private User [] reviews; // User array
    private DllNode next;
    private DllNode previous;
    public static final int max = 5; //  maximum array size for user array
    private int topIndex; // index for user array

    // default constructor
    public DllNode(){
        this.reviews = null;
        this.next = null;
        this.previous = null;
        this.topIndex = 0;
    }
    // next getter
    public DllNode goNext(){
        return this.next;
    }
    // previous getter
    public DllNode goPrevious(){
        return this.previous;
    }
    // next setter
    public void setNext(DllNode current){
        this.next = current;
    }
    // previous setter
    public void setPrevious(DllNode current){
        this.previous = current;
    }
    // storeReview() takes a User reference and returns an int, it will basically allocate space for the User array and
    // then store information in it until topIndex is equal to max. It will return an int so the DLL class insert function
    // can know when to make a new node,or when to keep pushing data inside of the current array. If the passed in reference
    // is null it will return -1 to signal to the DLL class to not create a new node.
    public int storeReview(User toStore){
        if(toStore == null){
            return -1;
        }

        else if(reviews == null){ // if the array is null, allocate space and push data in it,
            reviews = new User[max];
            reviews[topIndex] = new User(toStore);
            ++topIndex; // after pushing data increment counter/index
            return topIndex; // return that value for the DLL class
        }
        
        else if(topIndex > 0 && topIndex < max){ // if the array is not null and the index does not equal max
            reviews[topIndex] = new User(toStore); // push data in
            ++topIndex;// increment counter/index
            return topIndex; // return that value for the DLL class
        }

        else if(topIndex == max){ // if the index is equal to max
            topIndex = 0; // reset the index back to 0
            return topIndex; // and return that to DLL class
        }
        return -1; // return -1 if end of world
    }

    // compareReview() method takes a User reference and returns an int, it will compare the passed in reference's name
    // with the arrays first element's name. Since all arrays will only have the same user, then they would have the same
    // name. I chose 0 because it was the safest to compare to, since it is guaranteed to have a User always. It will
    // return a 0 if the passed in reference is null or if none of the comparisons are true(impossible btw), it will return
    // a -1 if the passed in reference is less than the one in the array, it will return a 1 if they are equal and it
    // will return a 2 if the passed in reference is greater than the one in the array.
    public int compareReview(User toCompare){
        if(toCompare == null){
            return 0;
        }
        else if(reviews[0].compareUser(toCompare) < 0){
            return -1;
        }
        else if(reviews[0].compareUser(toCompare) == 1){
            return 1;
        }
        else if(reviews[0].compareUser(toCompare) > 1){
            return 2;
        }

        return 0;
    }

    // compareReview() takes a String reference and returns an int, it's an overloaded method of the other compareReview()
    // and will function exactly the same.
    public int compareReview(String toCompare){
        if(toCompare == null){
            return 0;
        }
        else if(reviews[0].compareUser(toCompare) < 0){
            return -1;
        }
        else if(reviews[0].compareUser(toCompare) == 1){
            return 1;
        }
        else if(reviews[0].compareUser(toCompare) > 1){
            return 2;
        }

        return 0;
    }

    // displayNode() doesn't take any parameters and returns a void, it will first check if reviews field is empty and
    // return if it is. Then, it will check each index and display it if it's not empty. This method is need because the
    // DllNode has an array of Users and needs a loop to display them, otherwise I would have used the User class's display
    // without creating this.
    public void displayNode(){
        if(reviews == null){
            System.out.println("No reviews available!");
            return;
        }
        else{
            for(int i=0;i<max;++i){
                if(reviews[i] != null){
                    reviews[i].displayUser();
                }
            }
        }
    }
    // copyReviews() takes a User array and returns a boolean, it will first check both the passed in array and the reviews
    // field for null. If any of them are it will return a false. Otherwise, it will copy all the non null indices of the
    // reviews array into the passed in one and return true.
    public boolean copyReviews(User [] reviewsToCopy){
        if(reviewsToCopy == null || reviews == null){
            return false;
        }
        else{
           for(int i=0;i<max;++i){
                if(reviews[i] !=null){
                    reviewsToCopy[i] = new User(reviews[i]);
                }
            }
            return true;
        }
    }

}
