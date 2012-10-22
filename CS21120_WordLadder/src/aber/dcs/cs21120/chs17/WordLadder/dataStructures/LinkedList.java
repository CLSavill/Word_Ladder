package aber.dcs.cs21120.chs17.WordLadder.dataStructures;

/**
 * @author Chris Savill - chs17
 * @title LinkedList class that expands dynamically when needed
 */
public class LinkedList {
    //////////////////////// Variables ///////////////////////////

    /**
     * Node class used to store the head of the LinkedList
     */
    private Node head;
    /**
     * Node class used to store the tail of the LinkedList
     */
    private Node tail;
    /**
     * int primitive used to store the number of nodes in the LinkedList
     */
    private int numberOfNodes = 0;

    //////////////////////// Methods ///////////////////////////
    /**
     * Method to return the number of nodes in the LinkedList
     *
     * @return Returns the numberOfNodes
     */
    public int getNumberOfNodes() {
        return this.numberOfNodes;
    }

    /**
     * Method to return the data in node requested
     *
     * @param nodeIndex The index of the node to be returned
     * @return Returns an Object which is the data contained in the node
     * requested
     */
    public Object getNode(int nodeIndex) {
        Node currentNode = head;

        if (nodeIndex == numberOfNodes - 1) {
            return (Comparable) tail.getData();
        } else {
            for (int counter = 1; counter <= nodeIndex; counter++) {
                currentNode = currentNode.getNextNode();
            }
            return (Comparable) currentNode.getData();
        }
    }

    /**
     * Method to add a new node to the LinkedList at the position passed in,
     * initialising the node with data passed in
     *
     * @param nodeIndex The position at which the node will be added
     * @param data The data that the new node will contain
     */
    public void addNode(int nodeIndex, Object data) {
        Node newNode = new Node(data);

        if (numberOfNodes == 0) {
            head = newNode;
            tail = newNode;
        } else if (nodeIndex == 0) {
            newNode.setNextNode(head);
            head = newNode;
        } else if (nodeIndex == numberOfNodes) {
            tail.setNextNode(newNode);
            tail = newNode;
        } else {
            Node currentNode = head;
            for (int counter = 0; counter < nodeIndex - 1; counter++) {
                currentNode = currentNode.getNextNode();
            }
            newNode.setNextNode(currentNode.getNextNode());
            currentNode.setNextNode(newNode);
        }
        numberOfNodes++;
    }

    /**
     * Method to delete a node from the LinkedList
     *
     * @param nodeIndex The index of the node to be deleted
     */
    public void removeNode(int nodeIndex) {
        if (1 == numberOfNodes) {
            head = null;
            tail = null;
        } else if (0 == nodeIndex) {
            head = head.getNextNode();
        } else {
            Node currentNode = head;
            for (int counter = 0; counter < nodeIndex - 1; counter++) {
                currentNode = currentNode.getNextNode();
            }
            Node nextNode = currentNode.getNextNode();
            currentNode.setNextNode(nextNode.getNextNode());
            if (tail == nextNode) {
                tail = currentNode;
            }
        }
        numberOfNodes--;
    }
}