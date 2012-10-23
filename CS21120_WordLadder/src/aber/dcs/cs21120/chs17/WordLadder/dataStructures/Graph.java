package aber.dcs.cs21120.chs17.WordLadder.dataStructures;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.LinkedList;

/**
 * @author Chris Savill - chs17 CS21120 WordLadder class that represents a graph
 * data structure
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
            getGraphHash().put(wordList.get(counter), newVertex = new Vertex(wordList.get(counter))); //Creates a new vertex, initialising it with the word in the word list at the index of the counter
            iterator = getHashIterator(); //Retrieves a new iterator for the hash table within the graph class

            while (iterator.hasNext()) {
                key = iterator.next().getKey(); //Assigns the next key from to the iterator to the key variable
                vertex = getGraphHash().get(key); //Assigns the vertex in the hash table referenced from the key to the vertex variable
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