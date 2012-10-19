/**
 * @author Chris Savill - chs17
 * @title CS21120 WordLadder project main class
 */

package aber.dcs.cs21120.chs17.WordLadder.dataStructures;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class Graph {
    private Hashtable<String, Vertex> graphHash;
    private Iterator<Map.Entry<String, Vertex>> iterator;
    
    //////////////////////// Constructors ///////////////////////////
    
    public Graph() {
        graphHash = new Hashtable();
    }
    
    public Hashtable<String, Vertex> getGraphHash() {
        return graphHash;
    }
    
    public Iterator getHashIterator() {
        iterator = graphHash.entrySet().iterator();
        return iterator;
    }
    
}

