package aber.dcs.cs21120.chs17.WordLadder.searchFunctions;

import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Graph;
import aber.dcs.cs21120.chs17.WordLadder.dataStructures.UnboundedQueue;
import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Vertex;
import aber.dcs.cs21120.chs17.WordLadder.setupFunctions.DiscoveryCycler;

/**
 * @author Chris Savill - chs17 CS21120 WordLadder class to run the discovery
 * part of the project.
 */
public class DiscoverLadder {
    //////////////////////// Variables ///////////////////////////    

    private Graph graph;
    private DiscoveryCycler discoverCycler;
    //////////////////////// Constructors ///////////////////////////

    /**
     * Constructor that takes an instance of the Graph class and sets its graph
     * instantiation to the graph passed in
     *
     * @param graph
     */
    public DiscoverLadder(Graph graph, DiscoveryCycler discoverCycler) {
        this.graph = graph;
        this.discoverCycler = discoverCycler;
    }

    //////////////////////// Methods ///////////////////////////
    /**
     * Breadth-First Search (BFS) algorithm to find the shortest word ladder
     * between two words
     *
     * @param currentVertex The current vertex/word being analysed
     * @param endVertex The target vertex/word/goal state
     * @param currentDepth The current depth in the graph
     * @return Returns true if word ladder has been found, false if not
     */
    public boolean breadthFirstSearchForDiscovery(Vertex currentVertex, Vertex endVertex, int currentDepth) {
        UnboundedQueue frontier = new UnboundedQueue();
        currentVertex.setDistanceFromStartVertex(currentDepth); //Sets distance from start vertex to the current depth, if it is the start vertex, distance would be 0
        frontier.add(currentVertex.getWord()); //Adds the current vertex to the queue

        while (!frontier.isEmpty()) { //Evaluates if the frontier queue is not empty
            if (currentVertex.getWord().equals(endVertex.getWord())) { //Checks if goal state has been met
                discoverCycler.setEndWord(currentVertex.getWord());           
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
}
