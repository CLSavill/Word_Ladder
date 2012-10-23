package aber.dcs.cs21120.chs17.WordLadder.dataStructures;

import java.util.LinkedList;

/**
 * @author Chris Savill - chs17
 * CS21120 WordLadder class to represent a vertex/node for use within the
 * graph
 */
public class Vertex {
    //////////////////////// Variables ///////////////////////////

    /**
     * String class used to store the word within the vertex
     */
    private String word;
    /**
     * int primitive used to store the distance of the vertex from the current
     * vertex. Defaults to -1 to represent that the vertex is unexplored
     */
    private int distanceFromStartVertex = -1;
    /**
     * LinkedList of type String used as an adjacency list for the vertex of all
     * the neighbours
     */
    private LinkedList<String> neighbours;
    /**
     * String class used to store the predecessor of the vertex. Defaults to
     * null as no predecessor is assigned at first.
     */
    private String predecessor = null;

    //////////////////////// Constructors ///////////////////////////
    /**
     * Default constructor
     */
    public Vertex() {
    }

    /**
     * Constructor used for initialising the word and neighbours
     *
     * @param word the String to initialise the vertex word variable
     */
    public Vertex(String word) {
        this.word = word;
        neighbours = new LinkedList();
    }

    //////////////////////// Methods ///////////////////////////
    /**
     * Method to return the word assigned to the vertex
     *
     * @return Returns word
     */
    public String getWord() {
        return this.word;
    }

    /**
     * Method to return the distance that this vertex is from the start vertex
     *
     * @return Returns distanceFromStartVertex
     */
    public int getDistanceFromStartVertex() {
        return this.distanceFromStartVertex;
    }

    /**
     * Method to set the value of the distance that this vertex it from the
     * start vertex
     *
     * @param distanceFromStartVertex The int value to initialise the vertex
     * distanceFromStartVertex variable
     */
    public void setDistanceFromStartVertex(int distanceFromStartVertex) {
        this.distanceFromStartVertex = distanceFromStartVertex;
    }

    /**
     * Method to add a new neighbour to the adjacency list (LinkedList) of
     * neighbours
     *
     * @param word The string to add to the adjacency list of the vertex
     */
    public void addNeighbour(String word) {
        neighbours.add(word);
    }

    /**
     * Method to return the LinkedList of neighbours
     *
     * @return Returns neighbours
     */
    public LinkedList<String> getNeighbours() {
        return this.neighbours;
    }

    /**
     * Method to return the predecessor of the vertex
     *
     * @return Returns predecessor
     */
    public String getPredecessor() {
        return this.predecessor;
    }

    /**
     * Method to set the predecessor of the vertex
     *
     * @param predecessor The String to initialise the vertex predecessor
     * variable
     */
    public void setPredecessor(String predecessor) {
        this.predecessor = predecessor;
    }
}
