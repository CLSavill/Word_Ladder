package aber.dcs.cs21120.chs17.WordLadder.dataStructures;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Chris Savill - chs17
 * @title CS21120 WordLadder class that represents a graph data structure
 */
public class Graph {
    //////////////////////// Variables ///////////////////////////

    /**
     * Hash table class used to store the unique words in the key and vertexes
     * as the data
     */
    private Hashtable<String, Vertex> graphHash;
    /**
     * Iterator class used to iterate through the hash table
     */
    private Iterator<Map.Entry<String, Vertex>> iterator;

    //////////////////////// Constructors ///////////////////////////
    /**
     * Default constructor that just initialises the hash table
     */
    public Graph() {
        graphHash = new Hashtable();
    }

    //////////////////////// Methods ///////////////////////////
    /**
     * Method to return the hash table
     *
     * @return Returns the graphHash hash table
     */
    public Hashtable<String, Vertex> getGraphHash() {
        return graphHash;
    }

    /**
     * Method that returns the iterator for the hash table
     *
     * @return Returns the iterator for the graphHash hash table
     */
    public Iterator getHashIterator() {
        iterator = graphHash.entrySet().iterator();
        return iterator;
    }
}