/*
* Mishal Alajmi
* CS202
* Homework #5
* 03/17/2017
*
 * The Apartment class is derived from the Location class, it does not require any further fields so it will use the ones
 * inherited from the Location class. It will implement both abstract methods from the Location class, and a copyLocation()
 * method that will be used by its copy constructor.
*/

package com.company;
import java.util.Scanner;

// Apartment Class
class Apartment extends Location {

    // default constructor
    public Apartment() {
    }

    // copy constructor
    public Apartment(Location ToCopy) {
        super(ToCopy);
    }

    // copy constructor that takes a single string reference as a parameter
    public Apartment(String countryName) {
        copyLocation(countryName);
    }

    // The inputInfo() method doesn't take any parameters and returns a void,
    // it will allocate memory for all necessary fields and prompt the use for
    // the information.
    public void inputInfo() {

        Scanner input = new Scanner(System.in);
        this.residenceType = new String("Apartment");
        this.addressName = new String();
        this.country = new String();
        this.region = new String();
        this.city = new String();
        this.zipCode = new String();

        System.out.println("Please enter name of the country(Case Sensitive!): ");
        this.country = input.nextLine();

        System.out.println("Please enter name of the State/region: ");
        this.region = input.nextLine();

        System.out.println("Please enter name of the city: ");
        this.city = input.nextLine();

        System.out.println("Please enter the zipcode: ");
        this.zipCode = input.nextLine();

        System.out.println("On what street is the Apartment located: ");
        this.addressName = input.nextLine();

        System.out.println("How many rooms does your apartment have: ");
        this.roomCount = input.nextInt();

        System.out.println("How many guests are allowed to rent the apartment: ");
        this.allowedGuests = input.nextInt();
        input.nextLine();
    }

    // The displayInfo() doesn't take any parameters and returns a void, it
    // will first check if its fields are empty if they are it will output a message.
    // If not it will display them.
    public void displayInfo() {
        if (country == null || region == null || city == null || zipCode == null) {
            System.out.println("No Listing available for in this location!");
        } else {
            System.out.println("country: " + this.country + "\nState/region: " + this.region + "\ncity: " + this.city +
                    "\nZip Code: " + this.zipCode + "\nStreet Name: " + this.addressName +
                    "\nNumber of rooms: " + this.roomCount + "\nNumber of guests allowed: " + this.allowedGuests + "\n");
        }
    }

    // The copyLocation() takes a string reference as a parameter and returns a void,
    // it will first check if the string is empty and return from the function if it is.
    // Otherwise, it will copy the passed in string into its country field. the reason for
    // taking a single string will be better explained in the OutNode class copy constructor.
    public void copyLocation(String countryName) {
        if (countryName == null) {
            return;
        }
        this.country = new String(countryName).replaceAll("\\r\\n", "");
    }

    public boolean compareData(Location compareTo) {
        if (compareTo == null) {
            return false;
        }
        else if (country.equalsIgnoreCase(compareTo.country)) {
            return true;
        }
        else {
            return false;
        }
    }
}