/**
 * @author Chris Savill - chs17
 * @title CS21120 WordLadder class to run the generation part of the project.
 */
package aber.dcs.cs21120.chs17.WordLadder.searchFunctions;

import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Graph;
import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Vertex;
import aber.dcs.cs21120.chs17.WordLadder.setupFunctions.GraphBuilder;
import aber.dcs.cs21120.chs17.WordLadder.setupFunctions.WordReader;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class GenerateLadder {

    //////////////////////// Variables ///////////////////////////
    /**
     * Scanner class used for retrieving user input
     */
    private Scanner input = new Scanner(System.in);
    /**
     * Graph class used for structuring a graph for use with the search
     * algorithm
     */
    private Graph graph;
    /**
     * Stack class used for storing the resulting words in the word ladder
     */
    private Stack resultStack = new Stack();
    /**
     * Boolean used to store true/false depending on whether a solution has been
     * found
     */
    private boolean result = false;
    /**
     * String used to store the word chosen for the word ladder
     */
    private String wordChosen = "WordTooLong";
    /**
     * int primitive used to store he number of levels for the search algorithm
     * to go down, the goal state
     */
    private int stepsInLadder = 0;
    /**
     * int primitive used to set a max word length on words being used in the
     * word ladder, can be changed later if additional word data files generated
     * for other lengths
     */
    private int maxWordLength = 7;
    /**
     * boolean used to store true/false depending on whether the word given by
     * the user is present in the word data file provided
     */
    private boolean wordPresent = false;
    /**
     * WordReader class used to read in the appropriate words from the
     * appropriate data files
     */
    private WordReader reader = new WordReader();
    /**
     * LinkedList of type String used to store all the words read in by the
     * WordReader class
     */
    private LinkedList<String> wordList = new LinkedList();

    //////////////////////// Constructors ///////////////////////////
    /**
     * Constructor that takes an instance of the Graph class and sets its graph
     * instantiation to the graph passed in
     *
     * @param graph
     */
    public GenerateLadder(Graph graph) {
        this.graph = graph;
    }

    //////////////////////// Methods ///////////////////////////
    /**
     * Method that sets off the word ladder generation cycle, first by calling a
     * method that gets the word to ladder from the user, then calls a method
     * that creates a new graph, then calls the search algorithm, evaluates its
     * result and acts accordingly
     */
    public void generateLadder() {
        getUserInputForGeneration();
        GraphBuilder graphBuilder = new GraphBuilder(graph);
        graphBuilder.createGraph(wordList);


        if (recursiveDepthLimitedSearchForGeneration(graph.getGraphHash().get(wordChosen), 0, stepsInLadder - 1) == true) { //Evaluates the result of the recursiveDepthLimitedSearchForGeneration method
            System.out.println("Word Ladder successfully generated.");
            for (int counter = resultStack.size() - 1; counter >= 0; counter--) {
                System.out.println(resultStack.get(counter)); //Prints out the word ladder stack if successful
            }
        } else {
            System.out.println("Sorry no complete word ladder for '" + wordChosen + "' with '" + stepsInLadder + "' steps in the ladder.");
        }
    }

    /**
     * Method that cycles through getting the user to input a valid word for the
     * word ladder generation and checks if it exists in the data files supplied
     * and then gets the user to input the number of steps the want to ladder
     */
    private void getUserInputForGeneration() {
        while (wordPresent == false) {
            wordChosen = "WordTooLong"; //"WordTooLong" used as it has more than 7 letters and to initialise word ready for while loop condition checking
            while (wordChosen.length() > maxWordLength) {
                System.out.println("Please enter in a word to generate a word ladder for (no more than 7 letters): ");
                wordChosen = input.next();
            }

            wordList = reader.readWords(wordChosen.length());

            if (checkWordPresent(wordList, wordChosen) == true) { //Evaluates if the word chosen exists in the appropriate word data file
                wordPresent = true;
            } else {
                System.out.println("Word is not present in file, please try another word.");
            }
        }
        System.out.println("Please enter in the number of steps in the ladder you want generated: ");
        stepsInLadder = input.nextInt();
    }

    /**
     * Method that checks if the word passed in is present in the word list
     * supplied
     *
     * @param wordList
     * @param word
     * @return Returns true or false depending on whether or not the word passed
     * in is present in he word list supplied
     */
    private boolean checkWordPresent(LinkedList<String> wordList, String wordChosen) {
        wordPresent = false;
        for (int counter = 0; counter < wordList.size() && wordPresent == false; counter++) {
            if (wordList.get(counter).equals(wordChosen)) {
                wordPresent = true;
            }
        }
        return wordPresent;
    }

    /**
     * Depth-Limited Search (DLS) algorithm to find the word ladder for a word
     * up to a certain depth
     *
     * @param currentVertex the current vertex being evaluated to see if goal
     * state has been met.
     * @param currentDepth the current depth at which the current vertex lies
     * at.
     * @param depthLimit the maximum depth that the search will go to to find
     * the goal state.
     * @return Returns true or false based on whether the goal state has been
     * found.
     */
    private boolean recursiveDepthLimitedSearchForGeneration(Vertex currentVertex, int currentDepth, int depthLimit) {
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
                for (String neighbour : graph.getGraphHash().get(currentVertex.getWord()).getNeighbours()) {
                    if (graph.getGraphHash().get(neighbour).getDistanceFromStartVertex() < 0) {
                        result = recursiveDepthLimitedSearchForGeneration(graph.getGraphHash().get(neighbour), currentDepth + 1, depthLimit);
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
}
