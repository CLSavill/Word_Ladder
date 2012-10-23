package aber.dcs.cs21120.chs17.WordLadder.searchFunctions;

import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Graph;
import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Vertex;
import aber.dcs.cs21120.chs17.WordLadder.setupFunctions.GenerateCycler;

/**
 * @author Chris Savill - chs17 CS21120 WordLadder class to run the generation
 * part of the project.
 */
public class GenerateLadder {
    //////////////////////// Variables ///////////////////////////

    private boolean resultFound = false;
    private Graph graph;
    private GenerateCycler generateCycler;

    //////////////////////// Constructors ///////////////////////////
    /**
     * Constructor that takes an instance of the Graph class and sets its graph
     * instantiation to the graph passed in
     *
     * @param graph
     */
    public GenerateLadder(Graph graph, GenerateCycler generateCycler) {
        this.graph = graph;
        this.generateCycler = generateCycler;
    }

    //////////////////////// Methods ///////////////////////////
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
    public boolean depthLimitedSearch(Vertex currentVertex, int currentDepth, int depthLimit) {
        if (currentVertex.getDistanceFromStartVertex() < 0) {
            currentVertex.setDistanceFromStartVertex(currentDepth);
        }

        if (currentVertex.getDistanceFromStartVertex() > currentDepth) {
            return false;
        } else {
            if (currentVertex.getDistanceFromStartVertex() == depthLimit) {
                generateCycler.setEndWord(currentVertex.getWord());
                return true;
            } else {
                for (String neighbour : graph.getGraphHash().get(currentVertex.getWord()).getNeighbours()) {
                    if (graph.getGraphHash().get(neighbour).getDistanceFromStartVertex() < 0) {
                        graph.getGraphHash().get(neighbour).setPredecessor(currentVertex.getWord()); //Sets the predecesor/parent vertex of the neighbour/chile vertex to the current vertex
                        resultFound = depthLimitedSearch(graph.getGraphHash().get(neighbour), currentDepth + 1, depthLimit);
                        if (resultFound == true) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
