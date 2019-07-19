/*
* Mishal Alajmi
* CS202
* Homework #5
* 03/17/2017
*
    * The Host class is the class that will interface with the user, it will allow the user to input their information,
    * display a host's information, copy a host from another host and a few comparisons. It will have a Location class
    * reference which will be used either with a House object or an Apartment object. Its fields include a string name to
    * hold the host's name, a string occupiedPeriod that will hold the length of the period that the host wants to rent
    * their home, a string residenceType which will hold the type of residence the host will rent and finally an int
    * pricePerNight which will hold the host's amount per night for that listing. All of the Host class's fields are
    * protected, because the InNode class will be inherited from it and needs to have the ability to modify its fields.
*/
package com.company;
import java.util.Scanner;

// Host Class
class Host{

    protected Location toRent; // Location reference will hold either a House/Apartment objects
    protected String name; // name will store the host's name
    protected String occupiedPeriod; // occupiedPeriod will store the amount of time the host wants to rent their home
    protected String residenceType; // residenceType will store the type of home the user will list
    protected int pricePerNight; // pricePerNight will store the amount the host wishes to receive per night from their home

    // default constructor
    public Host(){
        this.toRent = null;
        this.name = null;
        this.residenceType = null;
        this.occupiedPeriod = null;
        this.pricePerNight = 0;
    }


    // copy constructor that takes a Host reference
    public Host(Host toCopy){
        copyHost(toCopy);
    }
    // The getInfo() method doesn't take any parameters and returns a void,
    // it will allocate memory for its necessary fields. It will prompt the user for information.
    // If the user inputs "House" for residenceType then toRent field will create a House object and call its functions
    // to input information. If the user inputs "Apartment" residenceType then toRent will create an Apartment object,
    // and call its input function.
    public void getInfo(){

        this.name = new String();
        this.occupiedPeriod = new String();
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter your name: ");
        this.name = input.nextLine();


        System.out.println("What type of Housing is it?(House/Apartment): ");
        this.residenceType = input.nextLine();

        System.out.println("Please enter the duration of the period you want to rent your home(Ex. From September to October): ");
        this.occupiedPeriod = input.nextLine();


        System.out.println("Please set a price per night: ");
        this.pricePerNight = input.nextInt();
        input.nextLine();

        if (residenceType.equalsIgnoreCase("House")){

            this.toRent = new House(); // If residenceType is equal to "House" disregarding case sensitivity, create House object.
            this.toRent.inputInfo(); // call its input function.
        }

        else if (residenceType.equalsIgnoreCase("Apartment")){
            this.toRent = new Apartment(); // If residenceType is equal to "Apartment" disregarding case sensitivity, create Apartment object.
            this.toRent.inputInfo();// call its input function.
        }
    }

    // The displayHost() method doesn't take any parameters and returns a void,
    // it will first check if any of the fields are empty and output a message if they are.
    // else it will display all the fields.
    public void displayHost(){
        if(toRent == null || name == null || occupiedPeriod == null || residenceType == null || pricePerNight == 0) {
            System.out.println("Location doesn't have a listing!\n");
        }
        else{
            System.out.println("name of Host: "+this.name+"\nResidence Type: "+this.residenceType+"\nRenting from: "+this.occupiedPeriod+"\nPrice of rooms per nigh: "+this.pricePerNight+"$\n");
            toRent.displayInfo();
        }
    }

    // The copyHost() method takes a host reference as a parameter and returns a boolean,
    // it will start by checking if passed in reference is empty and return false if it is,
    // else it will start to copy information from the passed in reference and returns true.
    public boolean copyHost(Host toCopy){

        if(toCopy.name == null || toCopy.residenceType == null || toCopy.occupiedPeriod == null)
            return false;

        else{

            this.name = new String(toCopy.name);
            this.residenceType = new String(toCopy.residenceType);
            this.occupiedPeriod = new String(toCopy.occupiedPeriod);
            this.pricePerNight = toCopy.pricePerNight;

            if(residenceType.equalsIgnoreCase("House")) {
                this.toRent = new House(toCopy.toRent); // if residence is equal "House" then create a new House object
                return true;                            // using its copy constructor that takes a Location reference
            }


            else if(residenceType.equalsIgnoreCase("Apartment")) {
                this.toRent = new Apartment(toCopy.toRent); //if residence is equal "Apartment" then create a new Apartment object
                return true;                                // using its copy constructor that takes a Location reference
            }
        }
        return false;
    }
    // The compareHosts method takes a Host reference and returns an int,
    // it will first check if the passed in reference is empty and returns
    // a 0 if it is. It will then compare its name field with the passed in
    // reference, if the passed in reference is less than name it will return -1.
    // If the passed in reference is equal to name it will return 1, if the passed
    // reference is greater than it will return 2.
    public int compareHosts(Host ToCompare){

        if(ToCompare == null) {
            return 0;
        }
        else if(name.compareToIgnoreCase(ToCompare.name) < 0){
            return -1;
        }
        else if(name.compareToIgnoreCase(ToCompare.name) == 0){
            return 1;
        }
        else if(name.compareToIgnoreCase(ToCompare.name) > 0){
            return 2;
        }
        return 0;
    }


    // This compareHosts method is overloaded to take a String reference
    // instead of a Host reference and return an int. It will first check
    // if the passed in reference is empty and returns a 0 if it is.
    // It will then compare its name field with the passed in
    // reference, if the passed in reference is less than name it will return -1.
    // If the passed in reference is equal to name it will return 1, if the passed
    // reference is greater than it will return 2.
    public int compareHosts(String ToCompare){

        if(ToCompare == null || name == null) {
            return 0;
        }
        else if(name.compareToIgnoreCase(ToCompare) < 0){
            return -1;
        }
        else if(name.compareToIgnoreCase(ToCompare) == 0){
            return 1;
        }
        else if(name.compareToIgnoreCase(ToCompare) > 0){
            return 2;
        }
        return 0;
    }

    // The compareData method takes a Host reference,
    // and returns a boolean. It will call the Location's
    // compareData passing in the passed in Host reference.
    public boolean compareData(Host toAdd){
        if(toRent.compareData(toAdd.toRent)){
            return true;
        }
        else{
            return false;
        }
    }
    // The Repeat() method doesn't take any parameters and returns
    // a boolean, it will prompt the user to input either a Y or N.
    // It will return true if the user enters a Y, and false if the user
    // enters a N.
    public boolean Repeat(){
        char resp;
        Scanner input = new Scanner(System.in);


        System.out.println("Would you like to re-run the program?(Y/N):");
        resp = input.next().charAt(0);

        if (resp == 'Y' || resp == 'y') {
            return true;
        }
        else if (resp == 'N' || resp == 'n') {
            return false;
        }
        else if(resp != 'Y' || resp != 'y' || resp != 'N' || resp != 'n'){ // if the user enters something other than Y or N
            System.out.println("\nPlease only input either an uppercase Y or an uppercase N\n"); // recall the function.
            Repeat();
        }
        return false;
    }
}