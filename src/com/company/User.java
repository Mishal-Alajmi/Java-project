/*
* Mishal Alajmi
* CS202
* Homework #5
* 03/17/2017
*
*   The User class will allow the user to choose a listing and then leave a review and a rating on it, it will have a
*   field to hold their userName, a field to hold their rating, a field to hold their feedback and a Host reference to hold
*   the listing to review. For methods, it will have a method to get the information from the user and store them into
*   the fields, a method to compare a user's userName and a method to copy the user.
*/
package com.company;
import java.util.Scanner;
// User class
class User {

    protected Host toReview;
    protected String userName;
    protected String feedback;
    protected int rating;

    //default constructor
    public User(){
        this.toReview = null;
        this.userName = null;
        this.feedback = null;
        this.rating = 0;

    }

    // copy constructor
    public User(User toCopy){
        copyUser(toCopy);
    }

    // getInfo() takes a Host reference and returns a void, a user will be asked to review a listing in main by retrieving
    // from the outer tree. Once a Host has been retrieved, the user will be prompted to enter their information.
    public void getInfo(Host revListing){
        this.toReview = new Host(revListing);
        this.userName = new String();
        this.feedback = new String();
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter your userName: ");
        userName = input.nextLine();

        System.out.println("What did you think of your experience: ");
        feedback = input.nextLine();

        System.out.println("From a scale from 1-5 how would you rate your experience(1-lowest/5-highest): ");
        rating = input.nextInt();
    }
    // displayUser() does not take any parameters and returns a void, it will fist check if userName or feedback are null
    // and return null if they are. it will then display the User's information and then call the Host's displayHost()
    // method.
    public void displayUser(){

        if(userName == null || feedback == null){
            return;
        }
        else{
            System.out.println("---Reviewer Information---:\nReviewer name:\t"+userName+"\nWritten review:\t"+feedback+"\nRating:\t");
            for(int i=0;i<rating;++i){
                System.out.print("*\t");
            }
            System.out.println("\n---Listing Info---:\n");
            toReview.displayHost();

        }
    }
    // compareUser() takes a User reference and returns an int, it will compare the passed in reference's name with this
    // User's name lexicographically and returns a -1 if the passed in parameter is less than this userName, a 1 if they
    // match and a 2 if the passed in User is greater than.
    public int compareUser(User toCompare){
        if(toCompare == null){
            return 0;
        }
        else if(userName.compareToIgnoreCase(toCompare.userName) < 0){
            return -1;
        }
        else if(userName.compareToIgnoreCase(toCompare.userName) == 0){
            return 1;
        }
        else if(userName.compareToIgnoreCase(toCompare.userName) > 0){
            return 2;
        }
        return 0;
    }
    // compareUser() takes a String reference and returns an int, it's an overloaded method of the other compareUser().
    public int compareUser(String toCompare){
        if(toCompare == null){
            return 0;
        }
        else if(userName.compareToIgnoreCase(toCompare) < 0){
            return -1;
        }
        else if(userName.compareToIgnoreCase(toCompare) == 0){
            return 1;
        }
        else if(userName.compareToIgnoreCase(toCompare) > 0){
            return 2;
        }
        return 0;
    }
    // copyUser() takes a User reference and returns a boolean, it will first check the passed in parameter. If it is null
    // it will return false, else it will copy its information.
    public boolean copyUser(User toCopy){

        if(toCopy == null){
            return false;
        }
        else{
            toReview = new Host(toCopy.toReview);
            userName = new String(toCopy.userName);
            feedback = new String(toCopy.feedback);
            rating = toCopy.rating;
            return true;
        }
    }
}
