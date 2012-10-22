package aber.ac.uk.dcs.cs21120.chs17.WordLadder.dataStructureTests;

import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Vertex;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Chris Savill - chs17
 * @title JUnit test class for the Vertex class
 */
public class VertexTest {

    @Test
    public void testConstructorAndGetWord() {
        Vertex testVertex = new Vertex("Hello");
        assertEquals("Expected 'Hello' as the word in the vertex.", "Hello", testVertex.getWord());
    }
    
    @Test
    public void testSetAndGetDistanceFromStartNode() {
        Vertex testVertex = new Vertex("Hello");
        testVertex.setDistanceFromStartVertex(1);
        assertEquals("Expected '1' as the distance from the start node.", 1, testVertex.getDistanceFromStartVertex());
    }
    
    @Test
    public void testAddandGetNeighbour() {
        Vertex testVertex = new Vertex("Hello");
        testVertex.addNeighbour("Bye");
        assertEquals("Expected 'Bye' as a neighbour of the vertex.", "Bye", testVertex.getNeighbours().get(0));
    }
    
    @Test
    public void testAddAndGetNeighbours() {
        Vertex testVertex = new Vertex("Hello");
        testVertex.addNeighbour("Bye");
        assertEquals("Expected 'Bye' as the first neighbour of the vertex.", "Bye", testVertex.getNeighbours().get(0));
        testVertex.addNeighbour("Hola");
        assertEquals("Expected 'Hola' as the second neighbour of the vertex.", "Hola", testVertex.getNeighbours().get(1));
    }
    
    @Test
    public void testSetAndGetPredecessor() {
        Vertex testVertex = new Vertex("Child");
        testVertex.setPredecessor("Parent");
        assertEquals("Expected 'Parent' as the parent of the vertex.", "Parent", testVertex.getPredecessor());
    }
}
