package aber.ac.uk.dcs.cs21120.chs17.WordLadder.searchFunctionsTests;

import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Graph;
import aber.dcs.cs21120.chs17.WordLadder.searchFunctions.DiscoverLadder;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Chris Savill - chs17
 * @title JUnit test class for the DiscoverLader class
 */
public class DiscoverLadderTest {
    
    @Test
    public void testBreadthFirstSearchForDiscovery() {
        Graph testGraph = new Graph();
        DiscoverLadder testLadder = new DiscoverLadder(testGraph);
        
        assertTrue("Ladder should be found between 'head' and 'foot'", testLadder.discoverLadder());
    }
}
