package aber.dcs.cs21120.chs17.WordLadder.setupFunctions;

import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Graph;
import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Vertex;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author Chris Savill - chs17 CS21120 WordLadder graph builder class
 */
public class GraphBuilder {
    //////////////////////// Variables ///////////////////////////

    /**
     * Graph class for the builder to build
     */
    private Graph graph;

    //////////////////////// Constructors ///////////////////////////
    /**
     * Constructor that instantiates the graph to the graph passed in
     *
     * @param graph The Graph to initialise the GraphBuilder graph
     */
    public GraphBuilder(Graph graph) {
        this.graph = graph;
    }

    //////////////////////// Methods ///////////////////////////
    /**
     * Method that builds the graph using the word list passed in
     *
     * @param wordList The LinkedList of type String from which a graph is built
     * from
     */
    public void createGraph(LinkedList<String> wordList) {
        String key;
        Vertex vertex;
        Vertex newVertex;
        Iterator<Map.Entry<String, Vertex>> iterator;

        for (int counter = 0; counter < wordList.size(); counter++) {
            graph.getGraphHash().put(wordList.get(counter), newVertex = new Vertex(wordList.get(counter))); //Creates a new vertex, initialising it with the word in the word list at the index of the counter
            iterator = graph.getHashIterator(); //Retrieves a new iterator for the hash table within the graph class

            while (iterator.hasNext()) {
                key = iterator.next().getKey(); //Assigns the next key from to the iterator to the key variable
                vertex = graph.getGraphHash().get(key); //Assigns the vertex in the hash table referenced from the key to the vertex variable
                if (checkOneLetterDifference(newVertex.getWord(), vertex.getWord()) == true) {
                    vertex.addNeighbour(newVertex.getWord()); //Adds newVertex word to the neighbour adjacency list of the current vertex it is being compared to
                    newVertex.addNeighbour(vertex.getWord()); //Adds the current vertex word to the neighbour adjacency list of the newVertex it is being compared to
                }
            }
        }
    }

    /**
     * Method to check if the two words being passed in only have a one letter
     * difference
     *
     * @param wordToCheck A String to compare with the wordToCompareWith String
     * @param wordToCompareWith A String to compare with the wordToCheck String
     * @return Returns true or false depending on whether or not the two works
     * only have a one letter difference
     */
    private boolean checkOneLetterDifference(String wordToCheck, String wordToCompareWith) {
        boolean oneLetterDifference = false;
        int numberOfLettersMatched = 0;

        for (int counter = 0; counter < wordToCheck.length(); counter++) {
            if (wordToCheck.charAt(counter) == wordToCompareWith.charAt(counter)) {
                numberOfLettersMatched++;
            }
        }

        if (numberOfLettersMatched == wordToCheck.length() - 1) {
            oneLetterDifference = true;
        }
        return oneLetterDifference;
    }
}
