package aber.dcs.cs21120.chs17.WordLadder.dataStructures;

/**
 * @author Chris Savill - chs17
 * Dynamic Stack class
 */
public class UnboundedStack {
    //////////////////////// Variables ///////////////////////////

    /**
     * LinkedList used to allow the stack to change its size dynamically
     */
    private LinkedList stack;
    /**
     * int primitive used to store the size of the stack
     */
    private int stackSize = 0;

    //////////////////////// Constructors ///////////////////////////
    /**
     * Default constructor that just initialises the LinkedList
     */
    public UnboundedStack() {
        stack = new LinkedList();
    }

    //////////////////////// Methods ///////////////////////////
    /**
     * Method to add a new node to the top of the stack
     *
     * @param newObject The object of data to be stored in the node
     */
    public void push(Object newObject) {
        stack.addNode(0, newObject);
        stackSize++;
    }

    /**
     * Method to return the node at the top of the stack
     *
     * @return Returns the data from the node at the top of the stack
     */
    public Object pop() {
        Object objectToBeReturned = stack.getNode(0);
        stack.removeNode(0);
        stackSize--;
        return objectToBeReturned;
    }

    /**
     * Method to return the size of the stack
     *
     * @return Returns the size of the stack
     */
    public int sizeOf() {
        return this.stackSize;
    }

    /**
     * Method to return what is on the top of the stack without removing the
     * node
     *
     * @return Returns the data from the node at the top of the stack
     */
    public Object peek() {
        return stack.getNode(0);
    }

    /**
     * Method to return true/false depending on if the stack is empty or not
     *
     * @return Returns a boolean
     */
    public boolean isEmpty() {
        return stackSize == 0;
    }
}