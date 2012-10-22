package aber.dcs.cs21120.chs17.WordLadder.searchFunctions;

import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Graph;
import aber.dcs.cs21120.chs17.WordLadder.dataStructures.UnboundedStack;
import aber.dcs.cs21120.chs17.WordLadder.setupFunctions.WordReader;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Chris Savill - chs17
 * @title CS21120 WordLadder abstract class to contain the common methods of the
 * two search algorithms
 */
public abstract class Ladder {
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
     * Stack class used for storing the resulting words in the word ladder
     */
    protected UnboundedStack resultStack = new UnboundedStack();
    /**
     * boolean used to store true/false depending on whether a solution has been
     * found
     */
    protected boolean result = false;
    /**
     * int primitive used to set a max word length on words being used in the
     * word ladder, can be changed later if additional word data files generated
     * for other lengths
     */
    protected int maxWordLength = 7;
    /**
     * WordReader class used to read in the appropriate words from the
     * appropriate data files
     */
    protected WordReader reader = new WordReader();
    /**
     * LinkedList of type String used to store all the words read in by the
     * WordReader class
     */
    protected LinkedList<String> wordList = new LinkedList();

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
     * Method to print the resulting ladder from the result stack
     */
    protected void printLadder() {
        System.out.println("Word Ladder successfully generated.");
        System.out.println("Number of words in ladder: " + resultStack.sizeOf());
        while (!resultStack.isEmpty()) {
            System.out.println(resultStack.pop().toString()); //Prints out the word ladder stack if successful
        }
    }
}
