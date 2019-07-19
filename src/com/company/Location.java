/*
* Mishal Alajmi
* CS202
* Homework #5
* 03/17/2017
*
* The Location class is the only abstract class in the entire program, its job is to hold all relevant information about
* a listing. The House and Apartment class will both be derived from it, so it will only have three methods to implement
* inside of its class deceleration. All other methods are implemented as abstract for the other classes to implement.
* Its data fields are all protected so that the other two classes can have access to them, it has a string country to
* hold the country name, a string region to hold the region or state name, a string city to hold the city name, a string
* zip code to hold the zip code number, a string address name to hold the street address, a string residence type to hold
* the type of residence(whether it is a house or an apartment), an int room count to hold the number of rooms in the
* residence and finally an int allowed guests to hold the number of guests allowed to rent the residence.
*/

package com.company;

// Location Class
abstract class Location {

    protected String country; // This will hold the country name.
    protected String region; // This will hold the region/state name.
    protected String city; // This will hold the city name.
    protected String zipCode; // This will hold the zip code number.
    protected String addressName; // This will hold the street address.
    protected String residenceType; // This will hold the type of residence(House/Apartment).
    protected int roomCount; // This will hold the number of rooms in the residence.
    protected int allowedGuests; // This will hold the number of allowed guests in a residence.

    abstract public void inputInfo(); // This method will prompt the user to input all information about a Location.
    abstract public void displayInfo(); // This method will output all stored information about a Location.

    // default constructor
    public Location(){
        this.country = null;
        this.region = null;
        this.city = null;
        this.zipCode = null;
        this.addressName = null;
        this.residenceType = null;
        this.roomCount = 0;
        this.allowedGuests = 0;
    }

    // copy constructor
    public Location(Location toCopy){

        copyLocation(toCopy); // calls the copyLocation method to do the actual copying
    }



    // The copyLocation() method takes a Location reference,
    // and copies its information into the Location class.
    // It returns a boolean, and will return false if the
    // reference passed in is empty.It will otherwise return
    // true if everything has been copied in.
    public boolean copyLocation(Location toCopy){

        if(toCopy == null)
            return false;

        else {
            this.country = new String(toCopy.country);
            this.region = new String(toCopy.region);
            this.city= new String(toCopy.city);
            this.zipCode = new String(toCopy.zipCode);
            this.addressName = new String(toCopy.addressName);
            this.residenceType = new String(toCopy.residenceType);
            this.roomCount = toCopy.roomCount;
            this.allowedGuests = toCopy.allowedGuests;
            return true;
        }

    }

    // The compareData() method takes a Location reference,
    // and compares its country field with the country field
    // of the reference its being called from. it returns a
    // boolean, and will return false if the reference is empty
    // or if there wasn't a match.
    public boolean compareData(Location compareTo){
        if(compareTo == null){
            return false;
        }
        else if(country.equalsIgnoreCase(compareTo.country)){
            return true;
        }
        else {
            return false;
        }
    }
}
