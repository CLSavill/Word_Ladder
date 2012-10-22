package aber.ac.uk.dcs.cs21120.chs17.WordLadder.dataStructureTests;

/**
 * @author Chris Savill - chs17
 * @title JUnit test class for the LinkedList class
 */

import aber.dcs.cs21120.chs17.WordLadder.dataStructures.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;

public class LinkedListTest {
    
    @Test
    public void testDefaultConstructor() {
        LinkedList testLinkedList = new LinkedList();
        assertEquals("Expected '0' as the number of the nodes in the new LinkedList.", 0, testLinkedList.getNumberOfNodes());
    }
    
    @Test
    public void testAddNodeandGetNode() {
        LinkedList testLinkedList = new LinkedList();
        testLinkedList.addNode(0, 5);
        testLinkedList.addNode(1, 8.88);
        testLinkedList.addNode(2, "wicked");
        
        assertEquals("Expected '5' as the value stored in the 1st node of the linked list", 5, testLinkedList.getNode(0));
        assertEquals("Expected '8.88' as the value stored in the 2nd node of the linked list", 8.88, testLinkedList.getNode(1));
        assertEquals("Expected 'wicked' as the value stored in the 3rd node of the linked list", "wicked", testLinkedList.getNode(2));
    }
    
    @Test
    public void testAddNodeandRemoveNode() {
        LinkedList testLinkedList = new LinkedList();
        testLinkedList.addNode(0, 5);
        testLinkedList.addNode(1, 8.88);
        testLinkedList.addNode(2, "wicked");
        
        testLinkedList.removeNode(1);
        assertEquals("Expected '2' as the number of nodes in the linked list", 2, testLinkedList.getNumberOfNodes());
        assertEquals("Expected 'wicked' as the value stored in the 2nd node of the linked list", "wicked", testLinkedList.getNode(1));
        testLinkedList.removeNode(0);
        assertEquals("Expected '1' as the number of nodes in the linked list", 1, testLinkedList.getNumberOfNodes());
        assertEquals("Expected 'wicked' as the value stored in the 1st node of the linked list", "wicked", testLinkedList.getNode(0));
        testLinkedList.removeNode(0);
        assertEquals("Expected '0' as the number of nodes in the linked list", 0, testLinkedList.getNumberOfNodes());   
    }
}
