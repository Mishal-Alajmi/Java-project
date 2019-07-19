/*
* Mishal Alajmi
* CS202
* Homework #5
* 03/17/2017
*
*   The OutTree class is the interface between the user and the data; it will build the tree, insert the host's information,
*   retrieve and display information, and remove information from the tree. It will read in from an external text file,
*   and build the tree before inserting any user information. All other functionality will be preformed by the InTree
*   methods. The OutTree's main function is building the tree itself and traversing it, while other functionality is done
*   by the InTree.
*/
package com.company;


import java.io.*;
import java.util.Scanner;

// OutTree Class
class OutTree {

    private OutNode outRoot; // OutNode reference
    private int countLocation; // this will hold the number of lines in the text file
    // default constructor
    public OutTree(){
        this.outRoot = null;
        this.countLocation = 0;
    }
    // The countFileLines() method returns an int of the number of lines in the textfile, using the countLocation field.
    // it will create a BufferReader object and will countinue to count the lines as long as there is fIn.readLine() is
    // not null.
    private int countFileLines()throws IOException{

        try {
            BufferedReader fIn = new BufferedReader(new FileReader("countrylist.txt"));
            while (fIn.readLine() != null) {
                ++countLocation;
            }
            fIn.close();
        }
        catch (IOException e){
            System.err.println("Could not reach file!");
        }
        return 0;
    }
    // The loadTree() method returns a boolean depending on if there was an exception thrown or not, if an IOException is
    // thrown it will catch it and return false. Otherwise, open the file and read in the lines using the ";" delimiter
    // and storing it in a temp string array, while calling the buildTree() method to create the nodes and connecting them
    // together based on country name.
    public boolean loadTree() throws IOException{
        countFileLines(); // call the countFileLines() to get the number of lines in the text file
        int index = 0; // index for the elements of the string temp array
        String [] tempCountry = new String[countLocation]; // creating a temp string array with the size of the lines
        Scanner readIn;                                     // of the text files using countLines
        try {
            File FileIn= new File("countrylist.txt");
            readIn = new Scanner(FileIn);
            readIn.useDelimiter(";");
            while(index < countLocation){

                tempCountry[index] = readIn.next().replaceAll("\\r\\n","");
                this.outRoot = buildTree(this.outRoot,tempCountry[index]);
                ++index;
            }
            readIn.close();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    // The buildTree() method takes an OutNode reference and a string reference representing a country's name and returns
    // an OutNode, it will start by checking if outRoot is null and creating a new node passing it the country name if it
    // is. If outRoot is not null it will compare the passed in country name with the existing one and go right or left
    // based on which is greater.
    private OutNode buildTree(OutNode outRoot,String someCountry){
        if (outRoot == null){
            outRoot = new OutNode(someCountry);
            return outRoot;
        }
        else if(outRoot.compareLocation(someCountry)>=1){
            outRoot.setRight(buildTree(outRoot.goRight(),someCountry));
        }
        else if(outRoot.compareLocation(someCountry)<0){
            outRoot.setLeft(buildTree(outRoot.goLeft(),someCountry));
        }
        return outRoot;
    }

    // The displayAvailable() method takes an OutNode reference and returns an OutNode reference, it will traverse the
    // tree and display the information of the OutNode only so the country name and whether it has any host information
    // or not, it will not display the information inside each OutNode. This function is to show the user which countries
    // are supported so they can insert into the tree.
    private OutNode displayAvailable(OutNode outRoot){
        if(outRoot == null){
            return null;
        }

        outRoot.displayNode();
        displayAvailable(outRoot.goLeft());
        displayAvailable(outRoot.goRight());
        return outRoot;
    }
    // This displayAvailable() method will kickstart the recursive method from main and display the countries in the tree.
    public boolean displayAvailable(){
        if(displayAvailable(outRoot) == null){
            return false;
        }
        else{
            return true;
        }
    }

    // The insert() method takes an OutNode reference and a Host reference and returns an OutNode reference, it will
    // first check if outRoot is null and return null if it is, if outRoot is not null meaning it has a country name it
    // will check to see if the passed in Host reference's country matches the one in outRoot and call the InTree's
    // insert() method. Otherwise, it will traverse the tree until it finds a match, it will return null if no match is
    // found.
    private OutNode Insert(OutNode outRoot,Host toInsert){
        if(outRoot == null){
            return null;
        }else if(outRoot.compareData(toInsert)){
            outRoot.insert(toInsert);
            return outRoot;
        }
        outRoot.setRight(Insert(outRoot.goRight(),toInsert));
        outRoot.setLeft(Insert(outRoot.goLeft(),toInsert));
        return outRoot;
    }
    // This Insert() method takes a Host reference, and will kickstart the recursive insert() method from main.
    public void Insert(Host toInsert){
        this.outRoot = Insert(outRoot,toInsert);

    }
    // The displayAll() method takes an OutNode reference and returns an OutNode reference, it will traverse the outer
    // tree and display the country name within the OutNode first and then display all the information of the tree inside
    // it.
    private OutNode displayAll(OutNode outRoot){
        if(outRoot == null){
            return null;
        }

        System.out.println("***************************");
        outRoot.displayNode();
        outRoot.displayAll();
        System.out.println("***************************");
        displayAll(outRoot.goLeft());
        displayAll(outRoot.goRight());
        return outRoot;
    }
    // This displayAll() method is the wrapper function for the recursive displayAll() method, it will kickstart it from
    // main.
    public boolean displayAll(){
        if(displayAll(outRoot) == null){
            System.out.println("No Listings available!");
            return false;
        }
        else{
            return true;
        }
    }
    // The displayListing() method takes an OutNode and two string references, a hostName and a countryName. It will
    // traverse the tree until a matching country name is found, and then call the inner tree's displayHost() method
    // and pass it the hostName.
    private OutNode displayListing(OutNode outRoot,String countryName,String hostName){
        if(outRoot == null){
            return null;
        }

        else if(outRoot.compareLocation(countryName) == 1){
            outRoot.displayHost(hostName);
            return outRoot;
        }
        displayListing(outRoot.goRight(),countryName,hostName);
        displayListing(outRoot.goLeft(),countryName,hostName);
        return outRoot;
    }
    // This displayListing() method takes two string references, one for countryName and one for hostName. It is the
    // wrapper function for the recursive displayListing() method which will kickstart it from main.
    public boolean displayListing(String countryName,String hostName){
        if(displayListing(outRoot,countryName,hostName) == null){
            return false;
        }
        else{
            return true;
        }
    }
    // The retrieveListing() method takes an OutNode reference, two string references one representing a country's name
    // and one representing a host's name, and a Host reference and it will return an OutNode. It will traverse the
    // outer tree until a matching country name is found, it will then invoke the inner tree's retrieveHost() method
    // passing it the host's name and the Host reference to copy into.
    private OutNode retrieveListing(OutNode outRoot,String hostName,String countryName,Host toRetrieve){
        if(outRoot == null){
            return null;
        }

        else if(outRoot.compareLocation(countryName) == 1){
            outRoot.retrieveHost(hostName,toRetrieve);
            return outRoot;
        }

        retrieveListing(outRoot.goRight(),hostName,countryName,toRetrieve);
        retrieveListing(outRoot.goLeft(),hostName,countryName,toRetrieve);
        return outRoot;
    }
    // This retrieveListing() method takes a Host reference, and two string references one for the country name and one
    // for the host's name. It will kickstart the recursive retrieveListing() method from main.
    public boolean retrieveListing(String hostName,String countryName,Host toRetrieve){
        retrieveListing(outRoot,hostName,countryName,toRetrieve);
        if(toRetrieve == null){
            return false;
        }
        else{
            return true;
        }
    }

    // The removeHost() method takes in an OutNode reference and two string references one for the country name and one
    // for the host's name. It will traverse the outer tree until it finds a matching country name, if a match is found
    // it will call the inner tree's tree removeHost() method passing it the host's name.
    private OutNode removeHost(OutNode outRoot,String userName,String countryName){
        if(outRoot == null){
            return null;
        }

        if(outRoot.compareLocation(countryName) == 1){
            outRoot.removeHost(userName);
            return outRoot;

        }

        outRoot.setRight(removeHost(outRoot.goRight(),userName,countryName));
        outRoot.setLeft(removeHost(outRoot.goLeft(),userName,countryName));
        return outRoot;
    }
    // This removeHost() method takes in two string references, one representing the country's name and one representing
    // the host's name. It will kickstart the recursive removeHost() method from main.
    public void removeHost(String userName,String countryName){
        this.outRoot = removeHost(outRoot,userName,countryName);
    }
    // The removeAll() method will delete the outer tree by setting outRoot to null.
    public void removeAll(){
        outRoot.removeAll();
        outRoot = null;
    }
    // The retrieveAll() method takes an OutNode reference, a string reference and InTree reference and returns an OutNode.
    // It will traverse the outer tree until a matching country name is found, it will then call the inner tree's recursive
    // copy method, to copy the tree in that outer node.
    private OutNode retrieveAll(OutNode outRoot,String countryName,InTree toRetrieve){
        if(outRoot == null){
            return null;
        }

        else if(outRoot.compareLocation(countryName) == 1){
            toRetrieve.copyTree(outRoot);
            return outRoot;
        }

        retrieveAll(outRoot.goRight(),countryName,toRetrieve);
        retrieveAll(outRoot.goLeft(),countryName,toRetrieve);
        return outRoot;
    }
    // This retrieveAll() method takes a string reference and an InTree reference, it will kickstart the recursive
    // retrieveAll() method from main.
    public boolean retrieveAll(String countryName,InTree toRetrieve){
        this.outRoot = retrieveAll(outRoot,countryName,toRetrieve);
        if(toRetrieve == null){
            return false;
        }
        else{
            return true;
        }

    }

}
