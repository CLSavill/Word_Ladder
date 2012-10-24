package aber.dcs.cs21120.chs17.WordLadder.wordLadderFunctions;

import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Graph;
import aber.dcs.cs21120.chs17.WordLadder.dataStructures.UnboundedStack;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Chris Savill - chs17 CS21120 WordLadder abstract class from which the
 * DiscoveryCycler an GenerateCycler extend from
 */
public abstract class LadderCycler {

    //////////////////////// Variables ///////////////////////////
    /**
     * Scanner class used for retrieving user input
     */
    protected Scanner input = new Scanner(System.in);
    /**
     * Graph class used for structuring a graph for use with the search
     * algorithm
     */
    protected Graph graph;
    /**
     * WordReader class used for reading in words from a data file
     */
    protected WordReader reader;
    /**
     * LinkedList of type String used to store all the words read in by the
     * WordReader class
     */
    protected LinkedList<String> wordList;
    /**
     * int primitive used to set a max word length on words being used in the
     * word ladder, can be changed later if additional word data files generated
     * for other lengths
     */
    protected int maxWordLength = 7;
    /**
     * String class used to store the startWord
     */
    protected String startWord;
    /**
     * String class used to store the endWord
     */
    protected String endWord;
    /**
     * boolean primitive used to determine whether or not the start word is
     * present in the data file being scanned
     */
    protected boolean startWordPresent = false;
    /**
     * Stack class used for storing the resulting words in the word ladder
     */ 
    protected UnboundedStack resultStack;

    //////////////////////// Constructors ///////////////////////////
    /**
     * Constructor to initialise variables
     */
    public LadderCycler() {
        graph = new Graph();
        reader = new WordReader();
        wordList = new LinkedList();
        resultStack = new UnboundedStack();
    }

    //////////////////////// Methods ///////////////////////////
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
    protected boolean checkWordPresent(LinkedList<String> wordList, String word) {
        boolean wordPresent = false;
        for (int counter = 0; counter < wordList.size() && wordPresent == false; counter++) {
            if (wordList.get(counter).equals(word)) {
                wordPresent = true;
            }
        }
        return wordPresent;
    }

    /**
     * Method that stacks the path/word ladder between the two words, works
     * backwards from the goal state using the predecessor variable
     *
     * @param endWord The target vertex/word/goal state
     */
    protected void storeWordLadder(String endWord) {
        String currentWord = endWord;
        resultStack.push(endWord); //Adds the goal state word to the result stack

        while (graph.getGraphHash().get(currentWord).getPredecessor() != null) { //Loops until hit start vertex as the start vertex would have no predecessor so would be null
            resultStack.push(graph.getGraphHash().get(currentWord).getPredecessor()); //Adds predecessor to result stack
            currentWord = graph.getGraphHash().get(currentWord).getPredecessor(); //Sets the current word to the predecessor vertex
        }
    }

    /**
     * Method to print the resulting ladder from the result stack
     */
    protected void printLadder(UnboundedStack resultStack) {
        System.out.println("Word Ladder successfully generated.");
        System.out.println("Number of words in ladder: " + resultStack.sizeOf());
        while (!resultStack.isEmpty()) {
            System.out.println(resultStack.pop().toString()); //Prints out the word ladder stack if successful
        }
    }
}
