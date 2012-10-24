package aber.ac.uk.dcs.cs21120.chs17.WordLadder.searchFunctionsTests;

import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Graph;
import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Vertex;
import aber.dcs.cs21120.chs17.WordLadder.searchFunctions.DiscoverLadder;
import aber.dcs.cs21120.chs17.WordLadder.wordLadderFunctions.DiscoveryCycler;
import aber.dcs.cs21120.chs17.WordLadder.setupFunctions.GraphBuilder;
import aber.dcs.cs21120.chs17.WordLadder.dataStructures.WordReader;
import java.util.LinkedList;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Chris Savill - chs17
 * @title JUnit test class for the DiscoverLader class
 */
public class DiscoverLadderTest {
    
    @Test
    public void testBreadthFirstSearchForDiscovery() {
        LinkedList<String> testWordList;
        WordReader testReader = new WordReader();
        testWordList = testReader.readWords(3);
        Graph testGraph = new Graph();
        GraphBuilder testGraphBuilder = new GraphBuilder(testGraph);
        DiscoveryCycler testDiscoveryCycler = new DiscoveryCycler();
        testDiscoveryCycler.setStartWord("dog");
        testDiscoveryCycler.setEndWord("bot");
        testGraphBuilder.createGraph(testWordList);
        DiscoverLadder testLadder = new DiscoverLadder(testGraph, testDiscoveryCycler);
        Vertex startVertex = testGraph.getGraphHash().get("dog");
        Vertex endVertex = testGraph.getGraphHash().get("bot");    
        
        assertTrue("Ladder should be found between 'head' and 'foot'", testLadder.breadthFirstSearch(startVertex, endVertex, 0));
    }
}
