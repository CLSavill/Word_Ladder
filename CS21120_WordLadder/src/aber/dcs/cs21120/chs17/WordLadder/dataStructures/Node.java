package aber.dcs.cs21120.chs17.WordLadder.dataStructures;

/**
 * @author Chris Savill - chs17
 * @title Node class for use within LinkedList class
 */
public class Node {
    //////////////////////// Variables ///////////////////////////

    /**
     * Object class used to store any data that the node can contain
     */
    private Object data;
    /**
     * Node class used to store the next node
     */
    private Node nextNode;

    //////////////////////// Constructors ///////////////////////////
    /**
     * Constructor that takes the data object passed in and assigns it to the
     * data variable
     *
     * @param data The data to be stored in the node
     */
    public Node(Object data) {
        this.data = data;
    }

    /**
     * Method to set the next node of the node
     *
     * @param nextNode The next node
     */
    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    /**
     * Method to return the next node
     *
     * @return Returns the next node
     */
    public Node getNextNode() {
        return this.nextNode;
    }

    /**
     * Method to set the data of the node
     *
     * @param data The data to be stored in the node
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * Method to return the data in the node
     *
     * @return Returns the data of the node
     */
    public Object getData() {
        return this.data;
    }
}