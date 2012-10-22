/**
 * @author Chris Savill - chs17
 * @title CS21120 WordLadder class to run the generation part of the project.
 */
package aber.dcs.cs21120.chs17.WordLadder.searchFunctions;

import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Graph;
import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Vertex;
import aber.dcs.cs21120.chs17.WordLadder.setupFunctions.GraphBuilder;

public class GenerateLadder extends Ladder {
    //////////////////////// Variables ///////////////////////////

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
     * boolean used to store true/false depending on whether the word given by
     * the user is present in the word data file provided
     */
    private boolean wordPresent = false;

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
            printLadder();
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
