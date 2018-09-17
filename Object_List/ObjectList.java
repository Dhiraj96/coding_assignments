import java.util.NoSuchElementException;
/**
 * This class is a program that implements an ObjectList. An ObjectList is an un-sorted list of objects that can grow
 * as required. It supports methods such as getCurrent, getFirst, append, insert, replace and clear. 
 * 
 * @author  Raj Amarnani
 * @version 03/18/2017
 * @since 02/01/2017
 */
public class ObjectList implements ObjectListInterface{

    private Node myHeadNode;
    private Node myTailNode;
    private int myNumEntries;
    private int myCurrentPosition;
    private ObjectListIterator myIterator;

    /**
     * Constructor creates new instance of ObjectList. It takes no parameters and
     * does not return anything.
     *
     */
    public ObjectList(){
        myHeadNode = null;
        myTailNode = null;
        myNumEntries = 0;
        myCurrentPosition = 0;
        myIterator = new ObjectListIterator();
    }

    /**
     * This method returns the object at the current position in the list. It will return null if there is no current
     * object. 
     * 
     * @return The object at the current position of the list. Null if there is no object in the current position
     * 
     */
    public Object getCurrent(){

        //only return an object if there are elements in the list
        if(myNumEntries > 0){
            Object current = myIterator.myCurrentNode.getData();
            return current;
        }

        return null;
    }
    
    
    /**
     * This method will move to the first element in the list and return the object at the beggining of the list. 
     * 
     * @return The first object in the list
     * 
     */
    public Object getFirst(){
        
        //if there is more than one entry in my list
        if(myNumEntries > 0){
            myIterator.myCurrentNode = myHeadNode;
            myCurrentPosition = 1;
            return myHeadNode.getData();
        }
        
        return null;
    }
    
    
    /**
     * This method will move to the last element in the list and return the object at the end of the list. 
     * 
     * @return The object at the end of the list. 
     * 
     */
    public Object getLast(){
        
        //if there is more than one entry in my list
        if(myNumEntries > 0){
            myIterator.myCurrentNode = myTailNode;
            myCurrentPosition = myNumEntries;
            return myTailNode.getData();
        }
        
        return null;
    }

    
    /**
     * This method moves to the next object in the list and returns that object. If we are at the end of the list,
     * it returns the last element in the list and if the list is empty, it returns null. 
     * 
     * @return The object that is after the current object in the list. If we are at the end
     * of the list, it returns the last object and if the list is empty, it returns null. 
     * 
     */
    public Object getNext(){
        
        //if my list is not empty
        if(myNumEntries != 0){
            
            //if the iterator has a next node, get the next nodes' object
            if(myIterator.hasNext()){
                Object next = myIterator.next();
                myCurrentPosition++;
                return next;
            }
            else{
                myCurrentPosition = myNumEntries;
                return myTailNode.getData();
            }
        }

        return null;
    }

    
    /**
     * This method moves to the previous object in the list and returns that object. If we are at the beggining of 
     * the list, it returns the first element and it returns null if the list is empty. 
     * 
     * @return The object that is before the current object in the list. If we are the beggining 
     * of the list, it returns the first object and if the list is empty, it returns null. 
     * 
     */
    public Object getPrevious(){

        //if my list is not empty
        if(myNumEntries != 0){
            
            //if the iterator has a previous node, get the previous nodes' object
            if(myIterator.hasPrevious()){
                Object previous = myIterator.previous();
                myCurrentPosition--;
                return previous;
            }
            else{
                return myHeadNode.getData();
            }
        }

        return null;
    }
    
    
    /**
     * This method adds a new Object to the end of the list. It then points the current object to that new object. It takes 
     * a parameter newObject and returns a boolean, true if the addition was successful. 
     * 
     * @param newObject The object we are trying to add to the list. 
     * 
     * @return True if the addition was successful. False otherwise
     * 
     */
    public boolean append(Object newObject){

        //if my list is empty, add the new object
        if(myNumEntries == 0){
            myHeadNode = new Node(newObject);
            myTailNode = myHeadNode;
            myNumEntries++;
            myCurrentPosition = myNumEntries;
            myIterator.myCurrentNode = myHeadNode;
        }
        else{

            Node tempNode = new Node(newObject,myTailNode,null);
            myTailNode.setNextNode(tempNode);
            myTailNode = tempNode;
            myNumEntries++;
            
            myIterator.myCurrentNode = myTailNode;
            myCurrentPosition = myNumEntries;
        }

        return true;
    }

    /**
     * This method inserts the specified parameter newObject before the current position in the list. The new object also becomes 
     * the current object in the list. If the insertion was successful, the method returns true and false otherwise. 
     * 
     * @param newObject The object we are trying to insert into the list. 
     * 
     * @return True if the insertion was completed successfully. 
     * 
     */
    public boolean insert(Object newObject){
        
        if(myNumEntries == 0){
            myHeadNode = new Node(newObject);
            myTailNode = myHeadNode;
            myNumEntries++;
            myCurrentPosition = myNumEntries;
            myIterator.myCurrentNode = myHeadNode;
        }
        else{
            
            if(myCurrentPosition == 1){
                Node newNode = new Node(newObject);
                newNode.setNextNode(myHeadNode);
                myHeadNode.setPreviousNode(newNode);
                myHeadNode = newNode;
                myNumEntries++;
                myIterator.myCurrentNode = myHeadNode;
            }
            else{
                Node temp = myHeadNode;
                
                for(int i = 1; i < myCurrentPosition-1; ++i){
                    temp = temp.getNextNode();
                }
                
                //weird things happening(must fix) 
                Node newNode = new Node(newObject);
                newNode.setNextNode(temp.getNextNode());
                newNode.setPreviousNode(temp);
                
                temp.getNextNode().setPreviousNode(newNode);
                temp.setNextNode(newNode);
                myNumEntries++;
                myIterator.myCurrentNode = newNode;
            }
        }
        
        return true;
    }
    

    /**
     * This method replaces the object in the current position in the list with the newObject parameter. It returns true if the 
     * replacement was successful, false otherwise. 
     * 
     * @param newObject The object we are replacing the current position's object with 
     * 
     * @return True if the replacement was completed successfully. 
     * 
     */
    public boolean replace(Object newObject){
        
        if(myNumEntries > 0){
            Node newNode = new Node(newObject);
            
            if(myNumEntries == 1){
                myIterator.myCurrentNode = newNode;
                myHeadNode = newNode;
                myTailNode = myHeadNode;
                return true;
            }
            else{
                
                if(myCurrentPosition == 1){
                    newNode.setNextNode(myHeadNode.getNextNode());
                    myHeadNode.getNextNode().setPreviousNode(newNode);
                    myHeadNode = newNode;
                    myIterator.myCurrentNode = myHeadNode;
                }
                else if(myCurrentPosition == myNumEntries){
                    newNode.setPreviousNode(myTailNode.getPreviousNode());
                    myTailNode.getPreviousNode().setNextNode(newNode);
                    myTailNode=newNode;
                    myIterator.myCurrentNode = myTailNode;
                }
                else{
                    Node temp = myHeadNode;
                    
                    for(int i = 1; i < myCurrentPosition - 1; ++i){
                        temp = temp.getNextNode();
                    }
                    
                    Node nextNode = temp.getNextNode().getNextNode();
                    temp.setNextNode(newNode);
                    newNode.setPreviousNode(temp);
                    newNode.setNextNode(nextNode);
                    nextNode.setPreviousNode(newNode);
                    myIterator.myCurrentNode = newNode;
                }
            }
            
            return true;
        }
        
        return false;
    }
    
    
    
    /**
     * This method removes the current object from the list and makes the previous entry the current entry. If we are at the 
     * beggining of the list, it makes the current object the next object in the list. It returns the object that was removed from
     * the list.
     * 
     * @return The object at the current position we remove from the list. 
     * 
     */
    public Object remove(){
        
        //if there are elements to remove in my list,
        if(myNumEntries > 0){
            
            //if I only have one entry in the list, clear list. 
            if(myNumEntries == 1){
                clear();
            }
            else{
                
                //if I am at beggining of list
                if(myCurrentPosition == 1){
                    Object result = myHeadNode.getData();
                    myHeadNode = myHeadNode.getNextNode();
                    myHeadNode.setPreviousNode(null);
                    myIterator.myCurrentNode = myHeadNode;
                    myNumEntries--;
                    return result;
                }
                //if I am at end of list
                else if(myCurrentPosition == myNumEntries){
                    Object result = myTailNode.getData();
                    myTailNode = myTailNode.getPreviousNode();
                    myTailNode.setNextNode(null);
                    myIterator.myCurrentNode = myTailNode;
                    myNumEntries--;
                    myCurrentPosition--;
                    return result;
                }
                else{
                    Node temp = myHeadNode;
                    
                    //set temp to be node before currentNode
                    for(int i = 1; i < myCurrentPosition-1; ++i){
                        temp = temp.getNextNode();
                    }
                    myIterator.previous();
                    Node nextNode = temp.getNextNode().getNextNode();
                    Object result = temp.getNextNode().getData();
                    temp.setNextNode(nextNode);
                    nextNode.setPreviousNode(temp);
                    
                    myNumEntries--;
                    myCurrentPosition--;
                    return result;
                }
                
            }
        }
        
        return null;
    }

    /**
     * This method clears the list, removing all its elements. 
     * 
     * @return true if clearing the list was completed successfully. 
     * 
     */
    public boolean clear(){
        myHeadNode = null;
        myTailNode = null;
        myNumEntries = 0;
        myCurrentPosition = myNumEntries;
        return true;
    }

    /**
     * This method returns the int number of entries in the list. 
     * 
     * @return The int number of entries in the list 
     * 
     */
    public int getLength(){
        return myNumEntries;
    }

    /**
     * Returns the index of my current position in the list. If the list is empty, it returns 0. 
     * 
     * @return The int current position in the list. If the list is empty, it returns 0. 
     * 
     */
    public int getCurrentPosition(){
        return myCurrentPosition;
    }

 
  
   /**
    * This class implements an ObjectListIterator and keeps track of the current position of the list. It allows the traversal, both
    * forward and backwards, of the list. Methods it supports include hasNext, hasPrevious, next and previous. 
    * 
    * @author  Raj Amarnani
    * @version 03/18/2017
    * @since 02/01/2017
    * 
    */
   private class ObjectListIterator{

        private Node myCurrentNode;
        
        /**
         * The constructor for the ObjectListIterator creates a new instance of the ObjectListIterator. It takes no parameters and
         * does not return anything. 
         * 
         */
        private ObjectListIterator(){
            myCurrentNode = myHeadNode;
        }

        /**
         * This method determines whether there is a next element in the List. It returns true if there is an element after
         * the current one and false otherwise. 
         * 
         * @return True if there is a next element in the list from the current position
         * 
         */
        private boolean hasNext(){
    
            return myCurrentNode.getNextNode() != null;
        }

        /**
         * This method determines whether there is a previous element in the List. It returns true if there is an element before
         * the current one and false otherwise. 
         * 
         * @return True if there is a previous element in the list from the current position
         * 
         */
        private boolean hasPrevious(){

            return myCurrentNode.getPreviousNode() != null;
        }

        /**
         * This method returns the next object in the list and moves the iterator forwards one element. 
         * 
         * @return The object in the position after the current position in the list. 
         * 
         * @throws NoSuchElementException if next is called and there is no next element
         * 
         */
        private Object next(){
            
            //if the list has an element after the current, get the data in that element
            if(hasNext()){
                myCurrentNode = myCurrentNode.getNextNode();
                Object result = myCurrentNode.getData();
                return result;
            }
            else{
                throw new NoSuchElementException();
            }

        }

        /**
         * This method returns the previous object in the list and moves the iterator backwards one element. 
         * 
         * @return The object in the position before the current position in the list. 
         * 
         * @throws NoSuchElementException if previous is called and there is no previous element
         * 
         */
        private Object previous(){
            
            //if the list has an element before the current, get the data in that element
            if(hasPrevious()){
                myCurrentNode = myCurrentNode.getPreviousNode();
                Object result = myCurrentNode.getData();
                return result;
            }
            else{
                throw new NoSuchElementException();
            }

        }
   }



   /**
    * This class implements a doubly linked node with references to both the next and previous nodes as well as a reference to
    * its own object data. 
    * 
    * @author  Raj Amarnani
    * @version 03/18/2017
    * @since 02/01/2017
    * 
    */
    private class Node{
        private Object myData;
        private Node myNext;
        private Node myPrevious;

        /**
         * The constructor for the Node creates a new instance of the Node class. It takes an object dataPortion as a parameter to 
         * add to its Node. It does not return anything. 
         * 
         * @param dataPortion The data to be added to the new node being created. 
         * 
         */
        private Node(Object dataPortion){
            this(dataPortion,null,null);
        }
        
        /**
         * The constructor for the Node class that takes 3 parameters as arguments, the data to be added to the Node as well as the
         * Nodes that will be linked before and after itself. It does not return anything. 
         * 
         * @param dataPortion The data to be added to the new node being created.
         * @param previous The previous Node this new node will be linked to
         * @param next The next Node this new node will be linked to
         *  
         */
        private Node(Object dataPortion, Node previous, Node next){
            myData = dataPortion;
            myPrevious = previous;
            myNext = next;
        }

        /**
         * This method returns the next node linked to this node. It does not take any parameters. 
         * 
         * @return The next Node that this Node is linked to. 
         * 
         */
        private Node getNextNode(){
            return myNext;
        }

        /**
         * This method returns the previous node linked to this node. It does not take any parameters. 
         * 
         * @return The previous Node that this Node is linked to
         * 
         */
        private Node getPreviousNode(){
            return myPrevious;
        }

        /**
         * This method takes a nextNode parameter to set this node instances' next field. It does not return anything. 
         * 
         * @param nextNode The node reference we want this Nodes' next to be linked to 
         * 
         */
        private void setNextNode(Node nextNode){
            myNext = nextNode;
        }

        /**
         * This method takes a previousNode parameter to set this node instances' previous field. It does not return anything. 
         * 
         * @param previousNode The node reference we want this Nodes' previous to be linked to 
         * 
         */
        private void setPreviousNode(Node previousNode){
            myPrevious = previousNode;
        }

        /**
         * This method returns this Nodes' data that is an Object. 
         * 
         * @return The Object that is the data for this Node. 
         * 
         */
        private Object getData(){
            return myData;
        }
    }
    
}
