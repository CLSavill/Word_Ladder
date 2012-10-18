/**
 * @author Chris Savill - chs17
 * @title CS21120 WordLadder class to run the discovery part of the project.
 */
package aber.dcs.cs21120.chs17.WordLadder.searchFunctions;

import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Graph;
import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Vertex;
import aber.dcs.cs21120.chs17.WordLadder.setupFunctions.GraphBuilder;
import aber.dcs.cs21120.chs17.WordLadder.setupFunctions.WordReader;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;


public class DiscoverLadder {
    /** Scanner class used for retrieving user input */
    private Scanner input = new Scanner(System.in);
    /** Graph class used for structuring a graph for use with the search algorithm */
    private Graph graph;
    /** Stack class used for storing the resulting words in the word ladder */
    private Stack resultStack = new Stack();
    /** boolean used to store true/false depending on whether a solution has been found */
    private boolean result = false;
    /** String used to store the start word for the word ladder */
    private String startWord;
    /** String used to store the end word for the word ladder */
    private String endWord;
    /** int used to set a max word length on words being used in the word ladder, can be changed later if additional word data files generated for other lengths */
    private int maxWordLength = 7;
    /** boolean used to store true/false depending on whether the start word is present in the word data file provided */
    private boolean startWordPresent = false;
    /** boolean used to store true/false depending on whether the end word is present in the word data file provided */
    private boolean endWordPresent = false;
    /** WordReader class used to read in the appropriate words from the appropriate data files */
    private WordReader reader = new WordReader();
    /** LinkedList of type String used to store all the words read in by the WordReader class */
    private LinkedList<String> wordList = new LinkedList();

    /**
     * Constructor that takes an instance of the Graph class and sets its graph instantiation to the graph passed in
     * @param graph 
     */
    public DiscoverLadder(Graph graph) {
        this.graph = graph;
    }
    
    /**
     * Method that sets off the word ladder discovery cycle, first by calling a method that gets the words to ladder between from the user, then calls a method that creates a new graph, then calls the search algorithm, evaluates its result and acts accordingly 
     */
    public void discoverLadder() {
        getUserInputForDiscovery();
        GraphBuilder graphBuilder = new GraphBuilder(graph);
        graphBuilder.createGraph(wordList);

        if (iterativeDeepeningSearchForDiscovery(graph.getGraphHash().get(startWord), graph.getGraphHash().get(endWord), 0) == true) { //Evaluates the result of the iterativeDeepeningSearchForDiscovery method
            System.out.println("Word Ladder successfully generated.");
            for (int counter = 0; counter < getResultStack().size(); counter++) {
                System.out.println(getResultStack().get(counter)); //Prints out the word ladder stack if successful
            }
        } else {
            System.out.println("Sorry no complete word ladder between '" + startWord + "' and '" + endWord + "'.");
        }
    }

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

    private boolean checkWordPresent(LinkedList<String> wordList, String word) {
        boolean wordPresent = false;
        for (int counter = 0; counter < wordList.size() && wordPresent == false; counter++) {
            if (wordList.get(counter).equals(startWord)) {
                wordPresent = true;
            }
        }
        return wordPresent;
    }
    
    private boolean iterativeDeepeningSearchForDiscovery(Vertex startVertex, Vertex endVertex, int currentDepth) {
        return false;
    }
    
    /**
     *
     * @param currentVertex the current vertex being evaluated to see if goal
     * state has been met.
     * @param currentDepth the current depth at which the current vertex lies
     * at.
     * @param depthLimit the maximum depth that the search will go to to find
     * the goal state.
     * @return returns true or false based on whether the goal state has been
     * found.
     */
    private boolean recursiveDepthLimitedSearchForDiscovery(Vertex currentVertex, int currentDepth, int depthLimit) {
        if (currentVertex.getDistanceFromStartVertex() < 0) {
            currentVertex.setDistanceFromStartVertex(currentDepth);
        }

        if (currentVertex.getDistanceFromStartVertex() > currentDepth) {
            return false;
        } else {
            if (currentVertex.getDistanceFromStartVertex() == depthLimit) {
                resultStack.push(currentVertex.getWord());
                return true;
            } else {
                for (String neighbours : graph.getGraphHash().get(currentVertex.getWord()).getNeighbours()) {
                    if (graph.getGraphHash().get(neighbours).getDistanceFromStartVertex() < 0) {
                        result = recursiveDepthLimitedSearchForDiscovery(graph.getGraphHash().get(neighbours), currentDepth + 1, depthLimit);
                        if (result == true) {
                            resultStack.push(currentVertex.getWord());
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /* public boolean iterativeDeepeningSearch(Vertex currentVertex, int maxDepthLimit) {
     int currentDepthLimit = 0;
     boolean resultFound = false;

     while (currentDepthLimit < maxDepthLimit) {
     resultFound = recursiveDepthLimitedSearch(currentVertex, 0, currentDepthLimit, maxDepthLimit);
     if (resultFound == true) {
     return true;
     } else {
     currentDepthLimit++;
     resultFound = recursiveDepthLimitedSearch(currentVertex, 0, currentDepthLimit, maxDepthLimit);
     }
     }
     if (resultFound == false) {
     return false;
     } else {
     return true;
     }
     } */
    public Stack getResultStack() {
        return this.resultStack;
    }
}

