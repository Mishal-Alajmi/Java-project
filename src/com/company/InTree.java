/*
* Mishal Alajmi
* CS202
* Homework #5
* 03/17/2017
*
*    The InTree class will be the inner tree of this assignment containing an InNode reference called root, it will have a
*    few difference from any other tree function to allow it to act as a tree inside a tree. It will insert a new host,
*    display a single host, display the entire tree, retrieve a single host, retrieve all hosts, remove a single host,
*    remove the entire tree and finally copy the entire tree. It will also have a few utility methods like finding the inoreder
*    successor, counting how many matches there are and a few others.
*/
package com.company;

// InTree class
class InTree {

    protected InNode root; // InNode reference this will be the root of my tree
    protected int countMatch; // This will hold the number of matches for a host data
    // and will be used to allocate for an array of Hosts in the retrieveAll() method
    // default constructor
    public InTree(){
        this.root = null;
        this.countMatch = 0;
    }

    // copy constructor that takes an InTree reference
    public InTree(InTree toCopy){
        copyTree(toCopy);
    }

    // The insertHost() method will take an InNode reference and a Host reference and return an InNode reference.
    // It will first check for three conditions before inserting an node in the tree, it checks for three conditions
    // instead of one so that when the insert is done from the outer tree it can correctly insert in the inner tree.
    // The first thing it checks is if root is null which is the base case, secondly since the outer tree will be built
    // from an external file instead of user input, each InNode root will contain the name of the country of its outerNode
    // and therefore the root is not always null(except when want to add a new leaf) so we check if the passed in host's
    // country field matches the one already in the node, the last check check is to essentially check if the node only
    // has a country field and that is done by checking another field I chose the name field, but any other field would
    // have been sufficient. The rest of the function will check if the passed in Host's name is greater than or less than
    // the existing one and traverse the tree accordingly.
    private InNode insertHost(InNode root,Host add) {

        if (root == null || root.compareData(add) == true && root.checkName() == false) {
            root = new InNode(add);
            return root;
        }
        else if (root.compareHosts(add) >= 1) {
            root.setRight( insertHost(root.goRight(), add)); // setting root's right reference
        }
        else if (root.compareHosts(add) < 0) {
            root.setLeft(insertHost(root.goLeft(), add)); // setting root's left reference
        }
        return root; // returning the current state of root
    }

    // This insert() method is a wrapper function for the recursive insert method which will assign root to the value
    // returned by it, and kickstart it from the place it's being called from.
    public void insert(Host toAdd){
        this.root = insertHost(this.root,toAdd);

    }

    // The retrieveHost() method takes an InNode reference, a string reference representing the name of the host to
    // retrieve and a Host reference to copy the information to. It will use the host's compareHosts() method which takes
    // a Host reference, to traverse the tree if it finds a match it will copy the match's data to the passed in reference.
    private InNode retrieveHost(InNode root,String toRetrieve,Host store) {

        if (root == null) {
            return null;
        } else if (root.compareHosts(toRetrieve) == 1) {
            store.copyHost(root);
            return root;
        } else if (root.compareHosts(toRetrieve) > 1) {
            retrieveHost(root.goRight(), toRetrieve, store);
        } else if (root.compareHosts(toRetrieve) < 0) {
            retrieveHost(root.goLeft(), toRetrieve, store);
        }
        return root;
    }

    // This retrieveHost() method takes a string and a host reference as parameters, it's a wrapper method that will
    // assign root to the returned value of the recursive retrieve method and kickstart it from where it's being called.
    public void retrieveHost(String name,Host store){
        this.root = retrieveHost(this.root,name,store);
    }

    // The retrieveAll() method takes an InNode reference, a string reference representing the name of the host to retrieve,
    // an array of Hosts to copy the information into and finally an int index which will represent the number of elements
    // in the array of Hosts. It will traverse the tree until it finds a match, store it in the array and increment the index
    // counter. It will return null if either it reaches the end of the tree, or index is larger than the number of elements
    // of the array.
    private InNode retrieveAll(InNode root,String toRetrieve,Host [] store,int index){
        if(root == null || index > store.length){
            return null;
        }

        else if(root.compareHosts(toRetrieve)==1){
            store[index] = new Host(root);
            ++index;
        }

        retrieveAll(root.goRight(),toRetrieve,store,index);
        retrieveAll(root.goLeft(),toRetrieve,store,index);
        return root;
    }
    // This retrieveAll() method takes a string reference representing the host's name and an array of hosts, it's the
    // wrapper function for the recursive retrieveAll() method and will kickstart it from where it's being called.
    public void retrieveAll(String name,Host [] store){
        int index = 0; // array index initialized to zero to start.
        countMatches(root,name); // calling the countMatches() method to count how many matches it finds of the passed in name.
        store = new Host[countMatch]; // create an array of Hosts based on the number of matches it finds.
        this.root = retrieveAll(this.root,name,store,index);
    }

    // The displayHost() takes an InNode reference and a string reference representing the hosts name, it will traverse
    // the tree until a match is found and display it. it will return null if no match is found.
    private InNode displayHost(InNode root,String name){
        if(root == null){
            return null;
        }else if(root.compareHosts(name) == 1){
            root.displayHost();
            return root;
        }else if(root.compareHosts(name)>1){
            displayHost(root.goRight(),name);
        }else if(root.compareHosts(name)<0){
            displayHost(root.goLeft(),name);
        }return root;
    }
    // This displayHost() method takes a string reference that represents a host's name, and kickstarts the recursive
    // displayHost() method from where its being called.
    public void displayHost(String name){
        this.root = displayHost(this.root,name);
    }

    // The displayAll() method only takes an InNode reference, and traverses the tree displaying all nodes.
    private InNode displayAll(InNode root){
        if(root == null){
            return null;
        }
        root.displayHost();
        displayAll(root.goLeft());
        displayAll(root.goRight());
        return root;
    }

    // The displayAll() method is a wrapper for the recursive displayAll() method, which will kickstart it from where its
    // being called.
    public void displayAll(){
        this.root = displayAll(this.root);

        if(root == null){
            System.out.println("Location doesn't have a listing!\n");
        }
    }

    // The removeHost() method takes an InNode reference, and a string reference representing a host's name. It will
    // traverse the tree until it finds a match, if no match is found it will return null. Once a match has been found,
    // it will check five cases the first being if root is a leaf it will set it to null if it is, the second is if root
    // has a single child it will assign a temp reference to its child and set root to null then reassign root to
    // temp this applies to root's right and left children. The fourth is if root has two children, it will find the
    // inorder successor and copy it into root and then delete the inorder successor, the last case is if the inorder
    // successor has a right child it will set it as the inorder successor and delete the current one.
    private InNode removeHost(InNode root,String name){
        InNode temp;
        if(root == null){
            return null;
        }else if(root.compareHosts(name)>1){
            root.setRight(removeHost(root.goRight(),name));
        }else if(root.compareHosts(name)<0){
            root.setLeft(removeHost(root.goLeft(),name));
        }else if(root.compareHosts(name) == 1){

            if(root.goRight() == null && root.goLeft() == null){
                root = null;
            }else if(root.goRight() != null && root.goLeft() == null){
                temp = root.goRight();
                root = null;
                root = temp;

            }else if(root.goRight() == null && root.goLeft() != null){
                temp = root.goLeft();
                root = null;
                root = temp;

            }else if(root.goRight() != null && root.goLeft() != null){
                temp = findInorder(root);
                root.copyNode(temp);
                if(temp.goLeft().goRight() != null){
                    temp.setLeft(temp.goLeft().goRight());
                }else{
                    temp.setLeft(null);
                }
            }
        }return root;
    }
    // This removeHost() method takes a string reference representing the host's name, it will kickstart the recursive
    // removeHost() method from where it's being called.
    public void removeHost(String name){
        this.root = removeHost(this.root,name);
    }

    // The countMatches() method takes an InNode reference and a string reference representing the name of the host to
    // be counted. It will increment countMatch field every time it finds a match.
    private InNode countMatches(InNode root,String nameComp){
        if(root == null){
            return null;
        }

        else if(root.compareHosts(nameComp)==1){
            ++countMatch;
        }

        root.setLeft(countMatches(root.goLeft(),nameComp));
        root.setRight(countMatches(root.goRight(),nameComp));
        return root;
    }

    // the removeAll() method will set root to null to delete all of its information.
    public void removeAll(){
        this.root = null;
    }

    // The findInorder() method takes an InNode reference and traverses all the way to its left, and return the node at
    // that location.
    private InNode findInorder(InNode root){
        if(root == null){
            return null;
        }

        else if(root.goLeft() != null){
            return root.goLeft();
        }

        findInorder(root.goLeft());
        return null;
    }

    // The copyTree() method takes two InNode references, one representing the empty tree and the other the representing
    // the tree to be copied. It will traverse the entire tree copying each node along the way.
    private InNode copyTree(InNode dest,InNode source){
        if(source == null){
            dest = null;
            return dest;
        }

        dest = new InNode(source);
        dest.setRight(copyTree(dest.goRight(),source.goRight()));
        dest.setLeft(copyTree(dest.goLeft(),source.goLeft()));
        return dest;
    }
    // This copyTree() method takes an InTree reference, and will kickstart the recursive copyTree() method passing it
    // the root and passed in InTree reference's root.
    public void copyTree(InTree source){
        this.root = copyTree(this.root,source.root);
        if(root == null){
            System.out.println("No listings in the country selected!");
        }
    }

}