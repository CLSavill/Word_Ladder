package aber.dcs.cs21120.chs17.WordLadder.searchFunctions;

import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Graph;
import aber.dcs.cs21120.chs17.WordLadder.dataStructures.UnboundedQueue;
import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Vertex;
import aber.dcs.cs21120.chs17.WordLadder.setupFunctions.GraphBuilder;

/**
 * @author Chris Savill - chs17
 * @title CS21120 WordLadder class to run the discovery part of the project.
 */
public class DiscoverLadder extends Ladder {
    //////////////////////// Variables ///////////////////////////

    /**
     * String used to store the start word for the word ladder
     */
    private String startWord;
    /**
     * String used to store the end word for the word ladder
     */
    private String endWord;
    /**
     * boolean used to store true/false depending on whether the start word is
     * present in the word data file provided
     */
    private boolean startWordPresent = false;
    /**
     * boolean used to store true/false depending on whether the end word is
     * present in the word data file provided
     */
    private boolean endWordPresent = false;
    /**
     * LinkedList of type String used to store the frontier queue
     */
    private UnboundedQueue frontier;

    //////////////////////// Constructors ///////////////////////////
    /**
     * Constructor that takes an instance of the Graph class and sets its graph
     * instantiation to the graph passed in
     *
     * @param graph
     */
    public DiscoverLadder(Graph graph) {
        this.graph = graph;
    }

    //////////////////////// Methods ///////////////////////////
    /**
     * Method that sets off the word ladder discovery cycle, first by calling a
     * method that gets the words to ladder between from the user, then calls a
     * method that creates a new graph, then calls the search algorithm,
     * evaluates its result and acts accordingly
     */
    public void discoverLadder() {
        frontier = new UnboundedQueue();
        getUserInputForDiscovery();
        GraphBuilder graphBuilder = new GraphBuilder(graph);
        graphBuilder.createGraph(wordList);

        if (breadthFirstSearchForDiscovery(graph.getGraphHash().get(startWord), graph.getGraphHash().get(endWord), 0) == true) { //Evaluates the result of the iterativeDeepeningSearchForDiscovery method
            storeWordLadder(endWord);
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
    private boolean breadthFirstSearchForDiscovery(Vertex currentVertex, Vertex endVertex, int currentDepth) {
        currentVertex.setDistanceFromStartVertex(currentDepth); //Sets distance from start vertex to the current depth, if it is the start vertex, distance would be 0
        frontier.add(currentVertex.getWord()); //Adds the current vertex to the queue

        while (!frontier.isEmpty()) { //Evaluates if the frontier queue is not empty

            if (currentVertex.getWord().equals(endVertex.getWord())) { //Checks if goal state has been met
                return true;
            } else {
                currentVertex = graph.getGraphHash().get(frontier.head().toString()); //Sets the current vertex to the vertex at the front of the queue
                frontier.remove(); //Removes the current vertex from the frontier queue, (counted as explored)

                for (String neighbour : graph.getGraphHash().get(currentVertex.getWord()).getNeighbours()) {
                    if (graph.getGraphHash().get(neighbour).getDistanceFromStartVertex() < 0) { //Evaluates if the vertexes have been explored
                        frontier.add(graph.getGraphHash().get(neighbour).getWord()); //Adds neighbour/child vertex to end of queue
                        graph.getGraphHash().get(neighbour).setDistanceFromStartVertex(currentDepth + 1); //Sets the distance from start vertex to the next depth level
                        graph.getGraphHash().get(neighbour).setPredecessor(currentVertex.getWord()); //Sets the predecesor/parent vertex of the neighbour/chile vertex to the current vertex
                    }
                }

                if (graph.getGraphHash().get(currentVertex.getWord()).getWord().equals(endVertex.getWord())) {
                    return true; //Returns true and breaks out of the while loop
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
    public void storeWordLadder(String endWord) {
        String currentWord = endWord;
        resultStack.push(endWord); //Adds the goal state word to the result stack

        while (graph.getGraphHash().get(currentWord).getPredecessor() != null) { //Loops until hit start vertex as the start vertex would have no predecessor so would be null
            resultStack.push(graph.getGraphHash().get(currentWord).getPredecessor()); //Adds predecessor to result stack
            currentWord = graph.getGraphHash().get(currentWord).getPredecessor(); //Sets the current word to the predecessor vertex
        }
    }
}
