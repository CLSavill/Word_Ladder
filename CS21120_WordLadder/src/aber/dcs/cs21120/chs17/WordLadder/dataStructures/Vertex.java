/**
 * @author Chris Savill - chs17
 * @title CS21120 WordLadder project main class
 */

package aber.dcs.cs21120.chs17.WordLadder.dataStructures;

import java.util.LinkedList;

public class Vertex {
    private String word;
    private int numberOfNeighbours = 0;
    private LinkedList<String> neighbours;
}
