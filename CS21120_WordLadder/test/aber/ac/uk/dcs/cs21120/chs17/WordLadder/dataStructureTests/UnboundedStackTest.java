package aber.ac.uk.dcs.cs21120.chs17.WordLadder.dataStructureTests;

/**
 * @author Chris Savill - chs17
 * @title JUnit test class for the UnboundedStack class
 */

import aber.dcs.cs21120.chs17.WordLadder.dataStructures.UnboundedStack;
import static org.junit.Assert.*;
import org.junit.Test;

public class UnboundedStackTest {
    
    @Test
    public void testDefaultConstructor() {
        UnboundedStack testStack = new UnboundedStack();
        assertEquals("Expected '0' as the length of the stack.", 0, testStack.sizeOf());
    }
    
    @Test
    public void testPushandPop() {
        UnboundedStack testStack = new UnboundedStack();
        
        testStack.push(3);
        assertEquals("Expected '3' as the value in element 0 of the stack.", 3, testStack.pop());
    }

    @Test
    public void testMultiPushandPop() {
        UnboundedStack testStack = new UnboundedStack();
        
        testStack.push(3);
        testStack.push(233);
        testStack.push(1);
        testStack.push(5);
        testStack.push(49);
        assertEquals("Expected '49' as the value in element 0 of the stack.", 49, testStack.pop());
        assertEquals("Expected '5' as the value in element 0 of the stack.", 5, testStack.pop());
        assertEquals("Expected '1' as the value in element 0 of the stack.", 1, testStack.pop());
        assertEquals("Expected '233' as the value in element 0 of the stack.", 233, testStack.pop());
        assertEquals("Expected '3' as the value in element 0 of the stack.", 3, testStack.pop());
    }
    
    @Test
    public void testSizeOf() {
        UnboundedStack testStack = new UnboundedStack();
        UnboundedStack testStack2 = new UnboundedStack();
        
        testStack.push("Hello");
        testStack.push(77);
        assertEquals("Expected '2' as the length of the stack.", 2, testStack.sizeOf());
        
        testStack2.push("Hello");
        testStack2.push(77);
        testStack2.push("w7p");
        assertEquals("Expected '3' as the length of the stack.", 3, testStack2.sizeOf());
    }
}