/**
 * @author Chris Savill - chs17
 * @title CS21120 WordLadder project main class
 */

package aber.dcs.cs21120.chs17.WordLadder.dataStructures;

import java.util.LinkedList;

public class Vertex {
    private String word;
    private int distanceFromStartVertex = -1;
    private LinkedList<String> neighbours;
    private String predecessor = null;
    
    public Vertex() {
        
    }
    
    public Vertex(String word) {
        this.word = word;
        neighbours = new LinkedList();
    }
    
    public String getWord() {
        return this.word;
    }
    
    public int getDistanceFromStartVertex() {
        return this.distanceFromStartVertex;
    }
    
    public void setDistanceFromStartVertex(int distanceFromStartVertex) {
        this.distanceFromStartVertex = distanceFromStartVertex;
    }
    
    public void addNeighbour(String word) {
        neighbours.add(word);
    }
    
    public LinkedList<String> getNeighbours() {
        return this.neighbours;
    }
    
    public String getPredecessor() {
        return this.predecessor;
    }
    
    public void setPredecessor(String predecessor) {
        this.predecessor = predecessor;
    }
}
