package aber.dcs.cs21120.chs17.WordLadder.setupFunctions;

import aber.dcs.cs21120.chs17.WordLadder.searchFunctions.GenerateLadder;

/**
 * @author Chris Savill - chs17 CS21120 WordLadder class to cycle through the
 * process of generating a word ladder of a certain depth from a word
 */
public class GenerateCycler extends LadderCycler {
    //////////////////////// Variables ///////////////////////////

    /**
     * int primitive used to store the number of steps in the ladder to generate
     */
    private int stepsInLadder = 0;
    /**
     * boolean primitive used to determine whether or not the start word is
     * present in the data file being scanned
     */
    private boolean startWordPresent = false;
    /**
     * GenerateLadder class used to provide access to the search algorithm to
     * generate the word ladder
     */
    private GenerateLadder generateLadder;

    //////////////////////// Methods ///////////////////////////
    /**
     * Method that sets off the word ladder generation cycle, first by calling a
     * method that gets the word to ladder from the user, then calls a method
     * that creates a new graph, then calls the search algorithm, evaluates its
     * result and acts accordingly
     */
    public void generateGraphSetupCycle() {
        getUserInputForGeneration();
        graphBuilder.createGraph(wordList);
        generateLadder = new GenerateLadder(graph, this);

        if (generateLadder.recursiveDepthLimitedSearchForGeneration(graph.getGraphHash().get(startWord), 0, stepsInLadder - 1) == true) { //Evaluates the result of the recursiveDepthLimitedSearchForGeneration method
            storeWordLadder(endWord);
            printLadder(resultStack);
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
        WordReader reader = new WordReader();
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
}
