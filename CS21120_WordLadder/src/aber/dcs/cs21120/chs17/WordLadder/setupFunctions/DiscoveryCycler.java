package aber.dcs.cs21120.chs17.WordLadder.setupFunctions;

import aber.dcs.cs21120.chs17.WordLadder.searchFunctions.DiscoverLadder;

/**
 * @author Chris Savill - chs17 CS21120 WordLadder class to cycle through the
 * process of discovering a word ladder between two words
 */
public class DiscoveryCycler extends LadderCycler {
    //////////////////////// Variables ///////////////////////////

    /**
     * boolean primitive used to store whether or not the start word is present in the data file being scanned
     */
    private boolean startWordPresent = false;
    /**
     * boolean primitive used to store whether or not the end word is present in the data file being scanned
     */
    private boolean endWordPresent = false;
    /**
     * DiscoverLadder class used to provide access to the search algorithm to discover the word ladder
     */
    private DiscoverLadder discoverLadder;
    
    //////////////////////// Methods ///////////////////////////

    /**
     * Method that sets off the word ladder discovery cycle, first by calling a
     * method that gets the words to ladder between from the user, then calls a
     * method that creates a new graph, then calls the search algorithm,
     * evaluates its result and acts accordingly
     */
    public void discoveryGraphSetupCycle() {
        getUserInputForDiscovery();
        graphBuilder.createGraph(wordList);
        discoverLadder = new DiscoverLadder(graph, this);

        if (discoverLadder.breadthFirstSearchForDiscovery(graph.getGraphHash().get(startWord), graph.getGraphHash().get(endWord), 0) == true) { //Evaluates the result of the iterativeDeepeningSearchForDiscovery method
            storeWordLadder(endWord);
            printLadder(resultStack);
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
        WordReader reader = new WordReader();
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
}
