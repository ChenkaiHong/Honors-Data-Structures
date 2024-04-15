/**
 * MyTreeSet implements a tree set and contains methods to add and remove
 * tree nodes
 *
 * @author Kai Hong
 * @version 01/03/2024
 */
public class MyTreeSet<E>
{
    //Instance variables
    private TreeNode root;
    private int size;
    private TreeDisplay display;

    /**
     * Constructor for the MyTreeSet class
     */
    public MyTreeSet()
    {
        root = null;
        size = 0;
        display = new TreeDisplay();

        //wait 1 millisecond when visiting a node
        display.setDelay(15);
    }

    /**
     * Returns the number of tree nodes in the set
     *
     * @return size
     */
    public int size()
    {
        return size;
    }

    /**
     * Checks if the tree set contains the provided object
     *
     * @param obj the object which the method checks the tree set for
     * @return true if MyTreeSet contains obj, false otherwise
     */
    public boolean contains(Object obj)
    {
        display.displayTree(root);
        return BSTUtilities.contains(root, (Comparable) obj, display);
    }

    /**
     * Adds obj to the set if it is not present in the set.
     * @param obj the object being added
     * @return True if the obj is present in the set; false, otherwise.
     */
    public boolean add(E obj)
    {
        display.displayTree(root);
        if (!contains(obj))
        {
            root = BSTUtilities.insert(root, (Comparable) obj, display);
            size++;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Removes obj if it is in the set
     * @param obj object being removed
     * @return true if obj is present in the set; false, otherwise
     */
    public boolean remove(Object obj)
    {
        display.displayTree(root);
        if (contains(obj))
        {
            root = BSTUtilities.delete(root, (Comparable) obj, display);
            size--;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Calls the toString method that requires a parameter.
     * @return string returned by toString method with parameter
     */
    public String toString()
    {
        return toString(root);
    }

    /**
     * Returns a string that contains the contents of the tree set.
     * @param t root node of the tree set
     * @return string with contents of the tree set
     */
    private String toString(TreeNode t)
    {
        if(t == null)
            return " ";
        return toString(t.getLeft()) + t.getValue() + toString(t.getRight());
    }
}