package aber.dcs.cs21120.chs17.WordLadder.dataStructures;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Chris Savill - chs17 CS21120 WordLadder class that represents a graph
 * data structure
 */
public class Graph {
    //////////////////////// Variables ///////////////////////////

    /**
     * Scanner class used for retrieving user input
     */
    private Scanner input = new Scanner(System.in);
    /**
     * Hash table class used to store the unique words in the key and vertexes
     * as the data
     */
    private Hashtable<String, Vertex> graphHash;
    /**
     * Iterator class used to iterate through the hash table
     */
    private Iterator<Map.Entry<String, Vertex>> iterator;
    /**
     * WordReader class used for reading in words from a data file
     */
    private WordReader reader;
    /**
     * LinkedList of type String used to store all the words read in by the
     * WordReader class
     */
    private LinkedList<String> wordList;
    /**
     * int primitive used to set a max word length on words being used in the
     * word ladder, can be changed later if additional word data files generated
     * for other lengths
     */
    private int maxWordLength = 7;
    /**
     * String class used to store the startWord
     */
    private String startWord;
    /**
     * String class used to store the endWord
     */
    private String endWord;
    /**
     * boolean primitive used to determine whether or not the start word is
     * present in the data file being scanned
     */
    private boolean startWordPresent = false;
    /**
     * boolean primitive used to store whether or not the end word is present in
     * the data file being scanned
     */
    private boolean endWordPresent = false;
    /**
     * int primitive used to store the number of steps in the ladder to generate
     */
    private int stepsInLadder = 0;
    /**
     * boolean primitive used to determine whether or not the result has been
     * found
     */
    private boolean resultFound = false;
    /**
     * Stack class used for storing the resulting words in the word ladder
     */
    private Stack resultStack;
    private LinkedList<String> frontierQueue;

    //////////////////////// Constructors ///////////////////////////
    /**
     * Default constructor that just initialises the hash table
     */
    public Graph() {
        graphHash = new Hashtable();
        reader = new WordReader();
        resultStack = new Stack();
    }

    //////////////////////// Methods ///////////////////////////

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
    public void createGraph() {
        String key;
        Vertex vertex;
        Vertex newVertex;
        Iterator<Map.Entry<String, Vertex>> iterator;

        for (int counter = 0; counter < wordList.size(); counter++) {
            graphHash.put(wordList.get(counter), newVertex = new Vertex(wordList.get(counter))); //Creates a new vertex, initialising it with the word in the word list at the index of the counter
            iterator = getHashIterator(); //Retrieves a new iterator for the hash table within the graph class

            while (iterator.hasNext()) {
                key = iterator.next().getKey(); //Assigns the next key from to the iterator to the key variable
                vertex = graphHash.get(key); //Assigns the vertex in the hash table referenced from the key to the vertex variable
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

    /**
     * Method that checks if the word passed in is present in the word list
     * supplied
     *
     * @param wordList The LinkedList of type String that contains the list of
     * words for comparison
     * @param word The String to compare with the wordList LinkedList
     * @return Returns true or false depending on whether or not the word passed
     * in is present in he word list supplied
     */
    private boolean checkWordPresent(LinkedList<String> wordList, String word) {
        boolean wordPresent = false;
        for (int counter = 0; counter < wordList.size() && wordPresent == false; counter++) {
            if (wordList.get(counter).equals(word)) {
                wordPresent = true;
            }
        }
        return wordPresent;
    }

    /**
     * Method that sets off the word ladder generation cycle, first by calling a
     * method that gets the word to ladder from the user, then calls a method
     * that creates a new graph, then calls the search algorithm, evaluates its
     * result and acts accordingly
     */
    public void generateCycle() {
        getUserInputForGeneration();
        createGraph();

        if (depthLimitedSearch(graphHash.get(startWord), 0, stepsInLadder - 1) == true) { //Evaluates the result of the recursiveDepthLimitedSearchForGeneration method
            storeWordLadder();
            printLadder();
        } else {
            System.out.println("Sorry no complete word ladder for '" + startWord + "' with '" + stepsInLadder + "' steps in the ladder.");
        }
    }    
    
    /**
     * Method that cycles through getting the user to input a valid word for the
     * word ladder generation and checks if it exists in the data files supplied
     * and then gets the user to input the number of steps the want to ladder
     */
    private void getUserInputForGeneration() {
        while (startWordPresent == false) {
            startWord = "WordTooLong"; //"WordTooLong" used as it has more than 7 letters and to initialise word ready for while loop condition checking
            while (startWord.length() > maxWordLength) {
                System.out.println("Please enter in a word to generate a word ladder for (no more than 7 letters): ");
                startWord = input.next();
            }

            wordList = reader.readWords(startWord.length());

            if (checkWordPresent(wordList, startWord) == true) { //Evaluates if the word chosen exists in the appropriate word data file
                startWordPresent = true;
            } else {
                System.out.println("Word is not present in file, please try another word.");
            }
        }
        System.out.println("Please enter in the number of steps in the ladder you want generated: ");

        while (!input.hasNextInt()) {
            System.out.println("Please enter in a valid option.");
            input.next();
        }

        stepsInLadder = input.nextInt();
    }

    /**
     * Depth-Limited Search (DLS) algorithm to find the word ladder for a word
     * up to a certain depth
     *
     * @param currentVertex The current vertex being evaluated to see if goal
     * state has been met.
     * @param currentDepth The current depth at which the current vertex lies
     * at.
     * @param depthLimit The maximum depth that the search will go to; the goal
     * state
     * @return Returns True or false based on whether the goal state has been
     * found.
     */
    private boolean depthLimitedSearch(Vertex currentVertex, int currentDepth, int depthLimit) {
        if (currentVertex.getDistanceFromStartVertex() < 0) {
            currentVertex.setDistanceFromStartVertex(currentDepth);
        }

        if (currentVertex.getDistanceFromStartVertex() > currentDepth) {
            return false;
        } else {
            if (currentVertex.getDistanceFromStartVertex() == depthLimit) {
                endWord = currentVertex.getWord();
                return true;
            } else {
                for (String neighbour : graphHash.get(currentVertex.getWord()).getNeighbours()) {
                    if (graphHash.get(neighbour).getDistanceFromStartVertex() < 0) {
                        graphHash.get(neighbour).setPredecessor(currentVertex.getWord()); //Sets the predecessor/parent vertex of the neighbour/child vertex to the current vertex
                        resultFound = depthLimitedSearch(graphHash.get(neighbour), currentDepth + 1, depthLimit);
                        if (resultFound == true) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Method that sets off the word ladder discovery cycle, first by calling a
     * method that gets the words to ladder between from the user, then calls a
     * method that creates a new graph, then calls the search algorithm,
     * evaluates its result and acts accordingly
     */
    public void discoveryCycle() {
        getUserInputForDiscovery();
        createGraph();

        if (breadthFirstSearch(graphHash.get(startWord), graphHash.get(endWord), 0) == true) { //Evaluates the result of the iterativeDeepeningSearchForDiscovery method
            storeWordLadder();
            printLadder();
        } else {
            System.out.println("Sorry no complete word ladder between '" + startWord + "' and '" + endWord + "'.");
        }
    }

    /**
     * Method that cycles through getting the user to input two valid words for
     * the word ladder discovery and checks if they exist in the data files
     * supplied
     */
    private void getUserInputForDiscovery() {
        while (startWordPresent == false) {
            startWord = "WordTooLong"; //"WordTooLong" used as it has more than 7 letters and to initialise word ready for while loop condition checking
            endWord = "WordTooLong";
            while (startWord.length() > maxWordLength) {
                System.out.println("Please enter in a start word from which the word ladder will start from (no more than 7 letters): ");
                startWord = input.next();
            }

            wordList = reader.readWords(startWord.length());

            if (checkWordPresent(wordList, startWord) == true) { //Evaluates if the word chosen exists in the appropriate word data file
                startWordPresent = true;
            } else {
                System.out.println("Start word is not present in file, please try another word.");
            }
        }

        while (endWordPresent == false) {
            while (endWord.length() != startWord.length()) {
                System.out.println("Please enter in the target word to ladder to (same length as the start word): ");
                endWord = input.next();
            }

            if (checkWordPresent(wordList, endWord) == true) { //Evaluates if the word chosen exists in the appropriate word data file
                endWordPresent = true;
            } else {
                System.out.println("Target word is not present in file, please try another word.");
            }
        }
    }

    /**
     * Breadth-First Search (BFS) algorithm to find the shortest word ladder
     * between two words
     *
     * @param currentVertex The current vertex/word being analysed
     * @param endVertex The target vertex/word/goal state
     * @param currentDepth The current depth in the graph
     * @return Returns true if word ladder has been found, false if not
     */
    private boolean breadthFirstSearch(Vertex currentVertex, Vertex endVertex, int currentDepth) {
        frontierQueue = new LinkedList<String>();
        currentVertex.setDistanceFromStartVertex(currentDepth); //Sets distance from start vertex to the current depth, if it is the start vertex, distance would be 0
        frontierQueue.add(currentVertex.getWord()); //Adds the current vertex to the queue

        while (!frontierQueue.isEmpty()) { //Evaluates if the frontierQueue queue is not empty
            if (currentVertex.getWord().equals(endVertex.getWord())) { //Checks if goal state has been met
                endWord = currentVertex.getWord();
                return true;
            } else {
                currentVertex = graphHash.get(frontierQueue.peek().toString()); //Sets the current vertex to the vertex at the front of the queue
                frontierQueue.remove(); //Removes the current vertex from the frontierQueue queue, (counted as explored)

                for (String neighbour : graphHash.get(currentVertex.getWord()).getNeighbours()) {
                    if (graphHash.get(neighbour).getDistanceFromStartVertex() < 0) { //Evaluates if the vertexes have been explored
                        frontierQueue.add(graphHash.get(neighbour).getWord()); //Adds neighbour/child vertex to end of queue
                        graphHash.get(neighbour).setDistanceFromStartVertex(currentDepth + 1); //Sets the distance from start vertex to the next depth level
                        graphHash.get(neighbour).setPredecessor(currentVertex.getWord()); //Sets the predecessor/parent vertex of the neighbour/child vertex to the current vertex
                    }
                }
            }
        }
        return false; //Return false if no result found. If false is returned at the top, a failure to find the result has occurred
    }

    /**
     * Method that stacks the path/word ladder between the two words, works
     * backwards from the goal state using the predecessor variable
     *
     * @param endWord The target vertex/word/goal state
     */
    private void storeWordLadder() {
        String currentWord = endWord;
        resultStack.push(endWord); //Adds the goal state word to the result stack

        while (graphHash.get(currentWord).getPredecessor() != null) { //Loops until hit start vertex as the start vertex would have no predecessor so would be null
            resultStack.push(graphHash.get(currentWord).getPredecessor()); //Adds predecessor to result stack
            currentWord = graphHash.get(currentWord).getPredecessor(); //Sets the current word to the predecessor vertex
        }
    }

    /**
     * Method to print the resulting ladder from the result stack
     */
    private void printLadder() {
        System.out.println("Word Ladder successfully generated.");
        System.out.println("Number of words in ladder: " + resultStack.size());
        while (!resultStack.isEmpty()) {
            System.out.println(resultStack.pop().toString()); //Prints out the word ladder stack if successful
        }
    }
}