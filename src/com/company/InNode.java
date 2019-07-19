/*
* Mishal Alajmi
* CS202
* Homework #5
* 03/17/2017
*
*   The InNode class inherits Host, this class will act as an interface for the inner
*    binary search tree. It will inherit all of the host's fields and add two InNode references,
*   it will have getters and setters methods. plus, a method to copy the node and a method to compare host data.
*/
package com.company;

// InNode class
class InNode extends Host {

    protected InNode right; // Node reference to the right
    protected InNode left; // Node reference to the left

    // default constructor
    public InNode(){
        this.right = null;
        this.left = null;
    }
    // Copy constructor
    public InNode(InNode copyNode){
        super(copyNode); // invoking superclass constructor
        copyNode(copyNode); // calling copy method
        this.right = null;
        this.left = null;
    }

    // Copy constructor that takes a Host reference
    public InNode(Host copyNode){super(copyNode);}

    // Constructor that takes a string reference for country name, it creates a House object arbitrarily to
    // hold a country name in its toRent field
    public InNode(String countryname){
        this.toRent = new House(countryname);
    }


    // Getter that gets right node reference
    public InNode goRight(){

        return this.right;
    }
    // Getter that gets left node reference
    public InNode goLeft(){
        return this.left;
    }
    // Setter that sets the right node reference to the passed in InNode reference
    public void setRight(InNode cur){
        this.right = cur;
    }
    // Setter that sets the left node reference to the passed in InNode reference
    public void setLeft(InNode cur){
        this.left = cur;
    }
    // The copyNode() method takes an InNode reference and deep copies its fields
    // into the current InNode object.
    public boolean copyNode(InNode copyNode){

        if(copyNode.name == null || copyNode.residenceType == null || copyNode.occupiedPeriod == null)
            return false;


        else{

            this.name = new String(copyNode.name);
            this.residenceType = new String(copyNode.residenceType);
            this.occupiedPeriod = new String(copyNode.occupiedPeriod);
            this.pricePerNight = copyNode.pricePerNight;

            if(residenceType.equals("House") || residenceType.equals("house")) {
                this.toRent = new House(copyNode.toRent);
                return true;
            }


            if(residenceType.equals("Apartment") || residenceType.equals("apartment")) {
                this.toRent = new Apartment(copyNode.toRent);
                return true;
            }
        }
        return false;
    }

    // The checkName() method will check if the name field is empty or not
    // it will return true if it is empty, and true if it isn't empty. This
    // will be used in the inner tree insert function, to determine if the
    // node only has a country or if it also has a name.
    public boolean checkName(){
        if(name != null){
            return true;
        }
        else{
            return false;
        }
    }

}