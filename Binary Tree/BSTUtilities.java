/**
 * A collection of static methods for operating on binary search trees.
 * @author Chenkai Hong
 * @version 01/07/2024
 */
public abstract class BSTUtilities extends TreeUtil
{
    /**
     * Checks if the binary search tree contains the value.
     * @param t a binary search tree in ascending order
     * @param x the value that is being searched for in the tree
     * @param display display of the tree
     * @return True, if it contains value x; false, otherwise.
     */
    public static boolean contains(TreeNode t, Comparable x, TreeDisplay display)
    {
        if (t == null)
        {
            return false;
        }
        display.visit(t);
        int compareResult = x.compareTo(t.getValue());

        if (compareResult < 0)
        {
            return contains(t.getLeft(), x, display);
        }
        else if (compareResult > 0)
        {
            return contains(t.getRight(), x, display);
        }
        else
        {
            return true;
        }
    }

    /**
     * Inserts a node into the binary search tree.
     * @precondition t is a binary search tree in ascending order
     * @postcondition: if t is empty, returns a new tree containing x;
     *                 otherwise, returns t, with x having been inserted
     *                 at the appropriate position to maintain the binary
     *                 search tree property; x is ignored if it is a
     *                 duplicate of an element already in t; only one new
     *                 TreeNode is created in the course of the traversal
     * @param t a binary search tree in ascending order
     * @param x the value being inserted
     * @param display display of the tree
     * @return A pointer to the new tree containing x
     */
    public static TreeNode insert(TreeNode t, Comparable x, TreeDisplay display)
    {
        if (t == null)
        {
            t = new TreeNode(x);
            return t;
        }
        display.visit(t);
        int compareResult = x.compareTo(t.getValue());
        if (compareResult < 0)
        {
            t.setLeft(insert(t.getLeft(), x, display));
        }
        else if (compareResult > 0)
        {
            t.setRight(insert(t.getRight(), x, display));
        }
        return t;
    }

    /**
     * Deletes a node from the binary search tree.
     * @precondition: t is a binary search tree in ascending order
     * @postcondition: returns a pointer to a binary search tree,
     *                 in which the value at node t has been deleted
     *                 (and no new TreeNodes have been created)
     * @param t a binary search tree in ascending order
     * @param display display of the tree
     * @return A pointer to the new tree without the node.
     */
    private static TreeNode deleteNode(TreeNode t, TreeDisplay display)
    {
        TreeNode left = t.getLeft();
        TreeNode right = t.getRight();
        if(right == null)
            return left;
        TreeNode leftMost = right;
        TreeNode prev = t;
        while (leftMost.getLeft() != null)
        {
            prev = leftMost;
            leftMost = leftMost.getLeft();
        }
        if (leftMost.equals(right))
        {
            t.setValue(leftMost.getValue());
            prev.setRight(leftMost.getRight());
        }
        if (!leftMost.equals(right))
        {
            t.setValue(leftMost.getValue());
            prev.setLeft(leftMost.getRight());
        }
        return t;
    }

    /**
     * Deletes a value from a binary search tree
     * @precondition: t is a binary search tree in ascending order
     * @postcondition: returns a pointer to a binary search tree,
     *                 in which the value x has been deleted (if present)
     *                 (and no new TreeNodes have been created)
     * @param t a binary search tree in ascending order
     * @param x the value being deleted from the tree
     * @param display display of the tree
     * @return A pointer to the new tree without the value.
     */
    public static TreeNode delete(TreeNode t, Comparable x, TreeDisplay display)
    {
        if (t == null)
        {
            return null;
        }
        if (t.getValue().equals(x))
        {
            return deleteNode(t, display);
        }
        display.displayTree(t);
        if (x.compareTo(t.getValue()) < 0)
        {
            t.setLeft(delete(t.getLeft(), x, display));
        }
        else
        {
            t.setRight(delete(t.getRight(), x, display));
        }
        return t;
    }
}