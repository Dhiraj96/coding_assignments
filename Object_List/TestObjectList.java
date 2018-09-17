

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TestObjectList. This class tests all the methods in the program that implements an 
 * ObjectList. It provides various unit tests for each of the public methods defined in 
 * the ObjectList class.
 *
 * @author  Raj Amarnani
 * @version 03/18/2017
 * @since 02/01/2017
 */
public class TestObjectList{
    
    private ObjectList list1;
    private ObjectList list2;
    private ObjectList list3;
    private ObjectList list4;

    /**
     * Method to initialize all list instances to be used in our test methods of ObjectList
     *
     * Called before every test case method.
     */
    @Before
    public void setUpLists(){
        //empty list
        list1 = new ObjectList();
        
        //list with only 1 element
        list2 = new ObjectList();
        list2.append("hello");
        
        //list with 2 elements
        list3 = new ObjectList();
        list3.append("hello");
        list3.append("bye");
        
        //list with 3 elements
        list4 = new ObjectList();
        list4.append("bye");
        list4.append("hello");
        list4.append("again");
        
    }
    
    /**
     * This method checks whether the the elements of the first list argument are the 
     * same as the elements in the second int array argument. It returns a boolean value 
     * true if the elements are the same and false otherwise. 
     * 
     * @param list A ObjectList object whose elements will be compared to array
     * @param array An array of integers whose elements will be compared to elements in the list
     * 
     * @return true if the elements in list are the same as the array. False otherwise
     */
    private boolean checkContents(ObjectList list, Object[] array){
        //make list point at first element before comparison begins
        list.getFirst();
        
        if(list.getLength() == array.length){
            
            for(int i = 0; i < array.length; ++i){
                
                if(!list.getCurrent().equals(array[i])){
                    return false;
                }
                list.getNext();
            }
            
            return true;
        }
        
        return false;
    }
    
    /**
     * Tests the default constructor in the CS221Set object
     * 
     */
    @Test 
    public void testConstructorObjectList(){
        ObjectList temp = new ObjectList();
        Object[] trueArray = new Object[0];
        Object[] falseArray = new Object[1];
        falseArray[0] = "hello";
        
        assertTrue(checkContents(temp, trueArray));
        assertEquals(false, checkContents(temp, falseArray));
        assertEquals(0, temp.getLength());
    }
    
    /**
     * Tests the getCurrent method in the ObjectList class
     * 
     */
    @Test 
    public void testGetCurrent(){
        assertTrue(list1.getCurrent() == null);
        
        assertTrue(list2.getCurrent() == "hello");
        list2.getNext();
        assertTrue(list2.getCurrent() == "hello");
        
        assertTrue(list3.getCurrent() == "bye");
        list3.getPrevious();
        assertTrue(list3.getCurrent() == "hello");
    }
    
    /**
     * Tests the getFirst method in the ObjectList class
     * 
     */
    @Test 
    public void testGetFirst(){
        assertTrue(list1.getFirst() == null);
        
        assertTrue(list2.getFirst() == "hello");
        
        assertTrue(list4.getFirst() == "bye");
        list4.getPrevious();
        assertTrue(list4.getFirst() == "bye");
    }
    
    /**
     * Tests the getLast method in the ObjectList class 
     * 
     */
    @Test 
    public void testGetLast(){
        assertTrue(list1.getLast() == null);
        
        assertTrue(list2.getLast() == "hello");
        
        assertTrue(list4.getLast() == "again");
        list4.getPrevious();
        assertTrue(list4.getLast() == "again");
    }
    
    /**
     * Tests the getNext method in the ObjectList class
     * 
     */
    @Test 
    public void testGetNext(){
        assertTrue(list1.getNext() == null);
        assertTrue(list1.getCurrentPosition() == 0);
        
        assertTrue(list2.getNext() == "hello");
        assertTrue(list2.getCurrentPosition() == 1);
        
        assertTrue(list4.getNext() == "again");
        assertTrue(list4.getCurrentPosition() == 3);
        list4.getFirst();
        assertTrue(list4.getNext() == "hello");
        assertTrue(list4.getCurrentPosition() == 2);
    }
    
    /**
     * Tests the getPrevious method in the ObjectList class
     * 
     */
    @Test 
    public void testGetPrevious(){
        assertTrue(list1.getPrevious() == null);
        assertTrue(list1.getCurrentPosition() == 0);
        
        assertTrue(list2.getPrevious() == "hello");
        assertTrue(list2.getCurrentPosition() == 1);
        
        assertTrue(list4.getPrevious() == "hello");
        assertTrue(list4.getCurrentPosition() == 2);
        assertTrue(list4.getPrevious() == "bye");
        assertTrue(list4.getCurrentPosition() == 1);
    }
    
    /**
     * Tests the append method in the ObjectList class 
     * 
     */
    @Test 
    public void testAppend(){
        Object[] trueArray = new Object[0];
        assertTrue(checkContents(list1, trueArray));
        list1.append("test1");
        Object[] trueArray2 = {"test1"};
        assertTrue(checkContents(list1, trueArray2));
        
        Object[] trueArray3 = {"hello"};
        assertTrue(checkContents(list2, trueArray3));
        list2.append("test2");
        Object[] trueArray4 = {"hello", "test2"};
        assertTrue(checkContents(list2, trueArray4));
        assertTrue(list2.getLast() == "test2");
        assertTrue(list2.getCurrentPosition() == 2);
        
        Object[] trueArray5 = {"bye","hello","again"};
        assertTrue(checkContents(list4, trueArray5));
        list4.getFirst();
        list4.append("test5");
        Object[] trueArray6 = {"bye","hello","again", "test5"};
        assertTrue(checkContents(list4, trueArray6));
        assertTrue(list4.getLast() == "test5");
        assertTrue(list4.getCurrentPosition() == 4);
    }
    
    /**
     * Tests the insert method in the ObjectList class
     * 
     */
    @Test 
    public void testInsert(){
       Object[] trueArray = new Object[0];
       assertTrue(checkContents(list1, trueArray));
       list1.insert("test1");
       Object[] trueArray2 = {"test1"};
       assertTrue(checkContents(list1, trueArray2));
       assertEquals("test1", list1.getCurrent());
       
       Object[] trueArray3 = {"bye", "hello", "again"};
       assertTrue(checkContents(list4, trueArray3));
       list4.insert("test2");
       Object[] trueArray4 = {"bye", "hello", "test2", "again"};
       assertTrue(checkContents(list4, trueArray4));
       assertEquals(4, list4.getLength());
       
       Object[] trueArray5 = {"hello","bye"};
       assertTrue(checkContents(list3, trueArray5)); 
       list3.getFirst();
       assertEquals("hello", list3.getCurrent());
       assertTrue(list3.insert("test3"));
       Object[] trueArray6 = {"test3", "hello","bye"};
       assertTrue(checkContents(list3, trueArray6));
       assertEquals(3, list3.getLength());
    }
    
    /**
     * Tests the replace method in the ObjectList class
     * 
     */
    @Test 
    public void testReplace(){
        assertEquals(false, list1.replace("maybe"));
        
        Object[] trueArray = {"hello"};
        assertTrue(checkContents(list2, trueArray));
        assertTrue(list2.replace("test1"));
        Object[] trueArray2 = {"test1"};
        assertTrue(checkContents(list2, trueArray2));
        assertTrue(list2.getCurrent() == "test1");
        
        Object[] trueArray3 = {"bye", "hello", "again"};
        assertTrue(checkContents(list4, trueArray3));
        assertTrue(list4.getCurrent() == "again");
        list4.getPrevious();
        assertTrue(list4.replace("test2"));
        Object[] trueArray4 = {"bye", "test2", "again"};
        assertTrue(checkContents(list4, trueArray4));
        
        Object[] trueArray5 = {"hello","bye"};
        assertTrue(checkContents(list3, trueArray5));
        list3.getFirst();
        assertTrue(list3.replace("test3"));
        Object[] trueArray6 = {"test3", "bye"};
        assertTrue(checkContents(list3, trueArray6));
        
    }
    
    /**
     * Tests the remove method in the ObjectList class
     * 
     */
    @Test 
    public void testRemove(){
        Object[] trueArray = new Object[0];
        assertTrue(checkContents(list1, trueArray));
        assertEquals(list1.remove(), null);
        
        Object[] trueArray2 = {"hello"};
        assertTrue(checkContents(list2, trueArray2));
        list2.remove();
        assertTrue(checkContents(list2, trueArray));
        assertEquals(list2.getLength(), 0);
        
        Object[] trueArray3 = {"hello", "bye"};
        assertTrue(checkContents(list3, trueArray3));
        assertEquals(list3.getCurrent(), "bye");
        list3.remove();
        assertTrue(checkContents(list3, trueArray2));
        assertEquals(list3.getLength(), 1);
        assertEquals(list3.getCurrent(), "hello");
        
        Object[] trueArray4 = {"bye", "hello", "again"};
        assertTrue(checkContents(list4, trueArray4));
        assertEquals(list4.getCurrent(), "again");
        list4.getPrevious();
        list4.remove();
        Object[] trueArray5 = {"bye", "again"};
        assertTrue(checkContents(list4, trueArray5));
        assertEquals(list4.getLength(), 2);
    }
   
    
    /**
     * Tests the clear method in the ObjectList class
     * 
     */
    @Test 
    public void testClear(){
        Object[] trueArray1 = new String[0];
        assertTrue(list1.getLength() == 0);
        list1.clear();
        assertTrue(list1.getLength() == 0);
        assertTrue(checkContents(list1, trueArray1));
        
        Object[] trueArray2 = {"hello"};
        assertTrue(list2.getLength() == 1);
        assertTrue(checkContents(list2, trueArray2));
        list2.clear();
        assertTrue(list2.getLength() == 0);
        assertTrue(checkContents(list2, trueArray1));
       
    }
    
    /**
     * Tests the getLength method in the ObjectList class
     * 
     */
    @Test 
    public void testGetLength(){
        assertTrue(list1.getLength() == 0);
        assertTrue(list2.getLength() == 1);
        assertTrue(list3.getLength() == 2);
        assertTrue(list4.getLength() == 3);
    }
    
    /**
     * Tests the getCurrentPosition method in the ObjectList class
     * 
     */
    @Test 
    public void testGetCurrentPosition(){
        assertTrue(list1.getCurrentPosition() == 0);
        assertTrue(list2.getCurrentPosition() == 1);
        
        list3.getNext();
        assertTrue(list3.getCurrentPosition() == 2);
        
        list4.getNext();
        list4.getNext();
        assertTrue(list4.getCurrentPosition() == 3);
        list4.getNext();
        assertTrue(list4.getCurrentPosition() == 3);
    }
    
    
}
