package aber.ac.uk.dcs.cs21120.chs17.WordLadder.dataStructureTests;

import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Graph;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Chris Savill - chs17
 * @title JUnit test class for the Graph class
 */
public class GraphTest {
    
    @Test
    public void testConstructor() {
        Graph testGraph = new Graph();
        assertEquals("Expected '0' from the hash table.", 0, testGraph.getGraphHash().size());
    }
}
