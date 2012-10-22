package aber.dcs.cs21120.chs17.WordLadder.dataStructures;

/**
 * @author Chris Savill - chs17
 * @title Dynamic Queue class
 */
public class UnboundedQueue {
    //////////////////////// Variables ///////////////////////////

    /**
     * LinkedList used to allow the queue to change its size dynamically
     */
    private LinkedList queue;
    /**
     * int primitive used to store the size of the stack
     */
    private int queueLength = 0;

    //////////////////////// Constructors ///////////////////////////
    /**
     * Default constructor that just initialises the LinkedList
     */
    public UnboundedQueue() {
        queue = new LinkedList();
    }

    //////////////////////// Methods ///////////////////////////
    /**
     * Method to add a new node to the front of the queue
     *
     * @param newObject The object of data to be stored in the node
     */
    public void add(Object newObject) {
        queue.addNode(queueLength, newObject);
        queueLength++;
    }

    /**
     * Method to return the node at the front of the queue
     *
     * @return Returns the data from the node at the front of the queue
     */
    public Object remove() {
        Object objectToBeReturned = queue.getNode(0);
        queue.removeNode(0);
        queueLength--;
        return objectToBeReturned;
    }

    /**
     * Method to return the size of the queue
     *
     * @return Returns the size of the queue
     */
    public int sizeOf() {
        return this.queueLength;
    }

    /**
     * Method to return what is at the front of the queue without removing the
     * node
     *
     * @return Returns the data from the node at the front of the queue
     */
    public Object head() {
        return queue.getNode(0);
    }

    /**
     * Method to return true/false depending on if the queue is empty or not
     *
     * @return Returns a boolean
     */
    public boolean isEmpty() {
        return queueLength == 0;
    }
}