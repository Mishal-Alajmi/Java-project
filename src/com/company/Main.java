/*
* Mishal Alajmi
* CS202
* Homework #5
* 03/17/2017
*
* This program will build a an outer binary search tree from an external file, which will represent all countries in
* that external text file. An inner binary search tree will hold all of the user's information, and both binary search
* trees will support the same functionality. In the beginning of this main function a small description of what the
* program does will be displayed, the list of supported countries will also be displayed to allow the user to choose
* from a country in the outer binary search tree. All functions will invoke either the outer binary search tree functions
* or the host class functions, or a mix between the two. The functions will be invoked withing a do while loop, which will
* continue to run as long as the repeat function is true which is a member of the host class. To store a review, you must
* first add a host to the outer tree otherwise it won't work. Once you add at least one host everything else should work.
*/
package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        int choice = 0;
        int maxUsers = 5;
        Host host = new Host();
        Host retrieve = new Host();
        InTree retrieveHosts = new InTree();
        OutTree addData = new OutTree();
        User reviewHost = new User();
        User [] retrieveUsers = new User[maxUsers];
        DLL storeReview = new DLL();
        String countryName = new String();
        String hostName = new String();
        String reviewerName = new String();
        Scanner input = new Scanner(System.in);
        System.out.println("This program will allow you to manage your property for rent in any of the supported" +
                " countries.\n");
        addData.loadTree(); // Build the outer tree from the external file.
        do {
            System.out.println("\n******Supported countries******\n");
            addData.displayAvailable();
            System.out.println("******Supported countries******\n");

            System.out.println("To start,please make a selection: " +
                    "\n\n1-Enter a new listing" +
                    "\n2-Retrieve a listing" +
                    "\n3-Retrieve all listings in a country" +
                    "\n4-Display a single listing in a country" +
                    "\n5-Display all listings in all countries" +
                    "\n6-Remove a listing" +
                    "\n7-Remove all listings" +
                    "\n8-Review a Listing" +
                    "\n9-Display a review" +
                    "\n10-Display all reviews" +
                    "\n11-Retrieve a review" +
                    "\n12-Remove a review" +
                    "\n13-Remove all reviews");
            choice = input.nextInt();
            input.nextLine();



            if(choice == 1) {
                host.getInfo();
                addData.Insert(host);
            }
            else if(choice == 2){
                System.out.println("Please enter the name of the country: ");
                countryName = input.nextLine();
                System.out.println("Please enter the name of the host you want to retrieve: ");
                hostName = input.nextLine();
                if(addData.retrieveListing(hostName,countryName,retrieve) == false){
                    System.out.println("Could not find Host");
                }
                else {
                    retrieve.displayHost();
                }
            }
            else if(choice == 3){
                System.out.println("Please enter the name of the country: ");
                countryName = input.nextLine();
                if(addData.retrieveAll(countryName,retrieveHosts)==false){
                    System.out.println("Could not find country!");
                }
                else{
                    retrieveHosts.displayAll();
                }
            }
            else if(choice == 4){
                System.out.println("Please enter the name of the country: ");
                countryName = input.nextLine();
                System.out.println("Please enter the name of the host you want to display: ");
                hostName = input.nextLine();
                addData.displayListing(countryName,hostName);

            }
            else if(choice == 5){
                addData.displayAll();
            }
            else if(choice == 6){
                System.out.println("Please enter the name of the country: ");
                countryName = input.nextLine();
                System.out.println("Please enter the name of the host you want to remove: ");
                hostName = input.nextLine();
                addData.removeHost(hostName,countryName);
            }
            else if(choice == 7){
                addData.removeAll();
                addData.displayAll();
                addData.loadTree(); // build the tree after displaying that it has been emptied
            }
            else if(choice == 8){
                System.out.println("Please enter the name of the country for the listing you want to review: ");
                countryName = input.nextLine();
                System.out.println("Please enter the host's name of the listing you want to review: ");
                hostName = input.nextLine();
                if(addData.retrieveListing(hostName,countryName,retrieve)==false){
                    System.out.println("Could not get listing!");
                }
                else {
                    reviewHost.getInfo(retrieve);
                    storeReview.insertReview(reviewHost);
                }
            }
            else if(choice == 9){
                System.out.println("Please enter the name of the reviewer you want to display: ");
                reviewerName = input.nextLine();
                if(storeReview.displayReviewer(reviewerName)==false){
                    System.out.println("Could not find reviewer data!");
                }
                else{
                    storeReview.displayReviewer(reviewerName);
                }
            }
            else if(choice == 10){
                storeReview.displayReviews();
            }
            else if(choice == 11){
                System.out.println("Please enter the name of the reviewer you want to retrieve: ");
                reviewerName = input.nextLine();
                if(storeReview.retrieveReview(reviewerName,retrieveUsers)==false){
                    System.out.println("Could not get reviewer information!");
                }
                else{
                    for(int i=0;i<5;++i){
                        if(retrieveUsers[i] != null) {
                            retrieveUsers[i].displayUser();
                        }
                    }
                }

            }
            else if(choice == 12){
                System.out.println("Please enter the name of the reviewer you want to remove: ");
                reviewerName = input.nextLine();
                if(storeReview.removeReview(reviewerName)==false){
                    System.out.println("Could not find reviewer information!");
                }
                else{
                    System.out.println("Reviewer information removed!");
                }
            }
            else if(choice == 13){
                storeReview.removeAllReviews();
            }

        }while(host.Repeat() == true);
    }
}

