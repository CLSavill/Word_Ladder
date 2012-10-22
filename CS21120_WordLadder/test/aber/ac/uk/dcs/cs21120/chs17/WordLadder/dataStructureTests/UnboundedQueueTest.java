package aber.ac.uk.dcs.cs21120.chs17.WordLadder.dataStructureTests;

/**
 * @author Chris Savill - chs17
 * @title JUnit test class for the UnboundedQueue class
 */

import aber.dcs.cs21120.chs17.WordLadder.dataStructures.UnboundedQueue;
import static org.junit.Assert.*;
import org.junit.Test;

public class UnboundedQueueTest {
    
    @Test
    public void testDefaultConstructor() {
        UnboundedQueue testQueue = new UnboundedQueue();
        assertEquals("Expected '0' as the length of the queue.", 0, testQueue.sizeOf());
    }
    
    @Test
    public void testPushandPop() {
        UnboundedQueue testQueue = new UnboundedQueue();
        
        testQueue.add(3);
        assertEquals("Expected '3' as the value in element 0 of the queue.", 3, testQueue.remove());
    }

    @Test
    public void testMultiPushandPop() {
        UnboundedQueue testQueue = new UnboundedQueue();
        
        testQueue.add(3);
        testQueue.add(233);
        testQueue.add(1);
        testQueue.add(5);
        testQueue.add(49);
        assertEquals("Expected '3' as the value in element 0 of the queue.", 3, testQueue.remove());
        assertEquals("Expected '233' as the value in element 0 of the queue.", 233, testQueue.remove());
        assertEquals("Expected '1' as the value in element 0 of the queue.", 1, testQueue.remove());
        assertEquals("Expected '5' as the value in element 0 of the queue.", 5, testQueue.remove());
        assertEquals("Expected '49' as the value in element 0 of the queue.", 49, testQueue.remove());
    }
    
    @Test
    public void testSizeOf() {
        UnboundedQueue testQueue = new UnboundedQueue();
        UnboundedQueue testQueue2 = new UnboundedQueue();
        
        testQueue.add("Hello");
        testQueue.add(77);
        assertEquals("Expected '2' as the length of the queue.", 2, testQueue.sizeOf());
        
        testQueue2.add("Hello");
        testQueue2.add(77);
        testQueue2.add("w7p");
        assertEquals("Expected '3' as the length of the queue.", 3, testQueue2.sizeOf());
    }
}