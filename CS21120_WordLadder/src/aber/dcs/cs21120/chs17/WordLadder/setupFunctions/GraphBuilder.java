/**
 * @author Chris Savill - chs17
 * @title CS21120 WordLadder graph builder class
 */

package aber.dcs.cs21120.chs17.WordLadder.setupFunctions;

import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Graph;
import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Vertex;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class GraphBuilder {
    private Graph graph;
    
    //////////////////////// Constructors ///////////////////////////
    
    public GraphBuilder(Graph graph) {
        this.graph = graph;
    }
    
    public void createGraph(LinkedList<String> wordList) {
        String key;
        Vertex vertex;
        Vertex newVertex;
        Iterator<Map.Entry<String, Vertex>> iterator;          

        for (int counter = 0; counter < wordList.size(); counter++) {
            graph.getGraphHash().put(wordList.get(counter), newVertex = new Vertex(wordList.get(counter)));
            iterator = graph.getHashIterator();
            
            while (iterator.hasNext()) {
                key = iterator.next().getKey();
                vertex = graph.getGraphHash().get(key);
                if (checkOneLetterDifference(newVertex.getWord(), vertex.getWord()) == true) {
                    vertex.addNeighbour(newVertex.getWord());
                    newVertex.addNeighbour(vertex.getWord());
                }
            }
        }
    }

    

    private boolean checkOneLetterDifference(String wordToCheck, String wordToCompareWith) {
        boolean oneLetterDifference = false;
        int numberOfLettersMatched = 0;

        for (int counter = 0; counter < wordToCheck.length(); counter++) {
            if (wordToCheck.charAt(counter) == wordToCompareWith.charAt(counter)) {
                numberOfLettersMatched++;
            }
        }

        if (numberOfLettersMatched == wordToCheck.length() - 1) {
            oneLetterDifference = true;
        }
        return oneLetterDifference;
    }
}
