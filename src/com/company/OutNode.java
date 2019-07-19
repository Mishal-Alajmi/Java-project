/*
* Mishal Alajmi
* CS202
* Homework #5
* 03/17/2017
*
*   The OutNode class is derived from the InTree class, so that each outer tree node has an inner tree node. It will have
*   three private fields, two OutNode references called right and left and a string reference called countryName which
*   will hold string of characters read from an external file. It will also have two setter methods and two getter methods
*   and a few copy and compare methods. It's data will be read in from an external file.
*/
package com.company;


// OutNode class
class OutNode extends InTree{
    private String countryName;
    private OutNode right;
    private OutNode left;

    // default constructor
    public OutNode(){
        this.right = null;
        this.left = null;
        this.countryName = null;
    }
    // copy constructor that invokes the InTree copy constructor
    public OutNode(OutNode toCopy){super(toCopy);}

    // copy constructor that takes a string reference representing a country's name as a parameter,
    // and creates a new InNode passing in that string reference.
    public OutNode(String copyCountry){
        root = new InNode(copyCountry);
        copyString(copyCountry);
    }
    // right reference getter
    public OutNode goRight(){
        return this.right;
    }
    // left reference getter
    public OutNode goLeft(){
        return this.left;
    }
    // right reference setter
    public void setRight(OutNode current){
        this.right = current;
    }
    // left setter reference
    public void setLeft(OutNode current){
        this.left = current;
    }

    // The compareData() method takes a Host reference as a parameter and returns a boolean, it will invoke the InNode
    // compareData() method and pass in the Host reference it will return true if the InNode's method returns true and
    // false otherwise.
    public boolean compareData(Host compareTo){
        if(compareTo == null) {
            return false;
        }
        else if(root.compareData(compareTo)){
            return true;
        }
        else {
            return false;
        }
    }

    // The compareLocation() method takes a string reference representing a country's name and returns a boolean, it will
    // compare the passed in string with this node's country field using the compareToIgnoringCase string method it will
    // return -1 if the passed in string is less than the node's country field. It will return a 1 if they are equal, and
    // it will return a 2 if passed in string is greater than the node's country field.Otherwise, it will return a 0.
    public int compareLocation(String compareTo){
        if(compareTo == null) {
            return 0;
        }
        else if(countryName.compareToIgnoreCase(compareTo) < 0){
            return 2;
        }
        else if(countryName.compareToIgnoreCase(compareTo) == 0){
            return 1;
        }
        else if(countryName.compareToIgnoreCase(compareTo) > 0){
            return -1;
        }
        return 0;
    }
    // The copyString() method takes a string reference representing a country's name and returns a boolean, it will
    // copy the passed in reference into the countryName field.
    public boolean copyString(String copyCountry){

        if(copyCountry.isEmpty()){
            return false;
        }

        else{
            this.countryName = copyCountry.replaceAll("\\r\\n","");
            return true;
        }

    }
    // The displayNode() method, will first check if the countryName field is empty or not. If empty it will output a
    // message, otherwise it will output the countryName.
    public void displayNode(){
        if(countryName == null){
            System.err.println("No location stored");
        }
        System.out.println("Country: "+countryName+"\n");
    }



}
