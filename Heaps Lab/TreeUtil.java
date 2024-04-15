import java.util.*;
/**
 * TreeUtil contains the following methods for manipulating binary trees:
 * leftmost, rightmost, maxDepth, createRandom, countNodes, countLeaves, preOrder, inOrder, postOrder
 * @author Chenkai Hong
 * @version 11/03/2023
 *
 */
public class TreeUtil
{
    //used to prompt for command line input
    private static Scanner in = new Scanner(System.in);
    private static final boolean debug = false;

    public static void main(String[] args)
    {
        twentyQuestions();
    }

    /**
     * leftmost returns the value in the leftmost node of the tree.  It is
     * implemented iteratively
     * precondition: t is non-empty
     * @param t is the tree whose leftmost node is to be found
     * @return the VALUE in the leftmost node of t.
     */
    public static Object leftmost(TreeNode t)
    {
        if(t.getLeft() != null)
        {
            return leftmost(t.getLeft());
        }
        return t.getValue();
    }

    /**
     * rightmost returns the value in the rightmost node of the tree.  It is
     * implemented recursively
     * precondition:  t is non-empty
     * @param t is the tree whose rightmost node is to be found
     * @return the VALUE in the rightmost node of t.
     */
    public static Object rightmost(TreeNode t)
    {
        if(t.getRight() != null)
        {
            return rightmost(t.getRight());
        }
        return t.getValue();
    }
    /**
     * maxDepth calculates the maximum depth of a binary tree.  An empty tree has
     * depth of 0 and a tree with one node has a depth of 1
     * @param t a pointer to the root of a tree whose depth is to be calculated
     * @return the depth of the tree rooted at t
     */
    public static int maxDepth(TreeNode t)
    {
        if(t == null)
        {
            return 0;
        }
        else
        {
            return 1 + Math.max(maxDepth(t.getLeft()), maxDepth(t.getRight()));
        }
    }

    /**
     * create a random tree of the specified depth.  No attempt to balance the tree
     * is provided.
     * @param depth of the tree
     * @return TreeNode object that points to the generated tree
     */
    public static TreeNode createRandom(int depth)
    {
        if (Math.random() * Math.pow(2, depth) < 1)
            return null;
        return new TreeNode(((int)(Math.random() * 10)),
                createRandom(depth - 1),
                createRandom(depth - 1));
    }
    /**
     * counts the number of nodes (including leaves) that are in the tree rooted at t
     * @param t the root of the tree
     * @return the number of nodes in the tree rooted at t
     */
    public static int countNodes(TreeNode t)
    {
        if (t == null)
            return 0;
        return 1 + countNodes(t.getLeft()) + countNodes(t.getRight());
    }
    /**
     * counts the number of leaves in the tree rooted at t
     * @param t the root of the binary tree
     * @return the number of leaves in the tree
     */
    public static int countLeaves(TreeNode t)
    {
        if(t == null)
            return 0;
        if(t.getLeft() == null && t.getRight() == null)
            return 1;
        else
            return countLeaves(t.getLeft()) + countLeaves(t.getRight());
    }
    /**
     * perform a pre-order traversal of the binary tree rooted at t, lighting
     * up the nodes on the display as the traversal takes place
     * @param t the root of the tree to traverse
     * @param display the display that will show the traversal
     * postcondition: each node in t has been lit up on display
     */
    public static void preOrder(TreeNode t, TreeDisplay display)
    {
        if (t == null)
        {
            return;
        }
        display.visit(t);
        preOrder(t.getLeft(), display);
        preOrder(t.getRight(), display);
    }
    /**
     * perform an in-order traversal of the binary tree rooted at t, lighting
     * up the nodes on the display as the traversal takes place
     * @param t the root of the tree to traverse
     * @param display the display that will show the traversal
     * post condition: each node in t has been lit up on display
     */
    public static void inOrder(TreeNode t, TreeDisplay display)
    {
        if (t == null)
        {
            return;
        }
        inOrder(t.getLeft(), display);
        display.visit(t);
        inOrder(t.getRight(), display);
    }
    /**
     * perform a post-order traversal of the binary tree rooted at t, lighting
     * up the nodes on the display as the traversal takes place
     * @param t the root of the tree to traverse
     * @param display the display that will show the traversal
     * postcondition: each node in t has been lit up on display
     */
    public static void postOrder(TreeNode t, TreeDisplay display)
    {
        if (t == null)
        {
            return;
        }
        postOrder(t.getLeft(), display);
        postOrder(t.getRight(), display);
        display.visit(t);
    }
    /**
     * fill a list with the values of a binary tree rooted at t using a
     * pre-order traversal with '$' values added to the list whenever
     * a null pointer is encountered
     * @param t the root of the tree
     * @param list the returned list of values in the tree
     */
    public static void fillList(TreeNode t, List<String> list)
    {
        if (t == null)
        {
            list.add("$");
        }
        else
        {
            list.add(t.getValue().toString());
            if (t.getLeft() != null)
            {
                fillList(t.getLeft(), list);
            }
            else
            {
                list.add("$");
            }
            if (t.getRight() != null)
            {
                fillList(t.getRight(), list);
            }
            else
            {
                list.add("$");
            }
        }
    }
    /**
     * saveTree uses the FileUtil utility class to save the tree rooted at t
     * as a file with the given file name
     * @param fileName is the name of the file to create which will hold the data
     *        values in the tree
     * @param t is the root of the tree to save
     */
    public static void saveTree(String fileName, TreeNode t)
    {
        List<String> list = new ArrayList<String>();
        fillList(t, list);
        FileUtil.saveFile(fileName, list.iterator());
    }
    /**
     * buildTree takes in an iterator which will iterate through a valid description of
     * a binary tree with String values.  Null nodes are indicated by "$" markers
     * @param it the iterator which will iterate over the tree description
     * @return a pointer to the root of the tree built by the iteration
     */
    public static TreeNode buildTree(Iterator<String> it)
    {
        if (!it.hasNext())
        {
            return null;
        }
        String val = it.next();
        if (val.equals("$"))
        {
            return null;
        }
        TreeNode t = new TreeNode(Integer.parseInt(val));
        t.setLeft(buildTree(it));
        t.setRight(buildTree(it));
        return t;
    }

    /**
     * buildTree takes in an iterator which will iterate through a valid description of
     * a binary tree with String values.  Null nodes are indicated by "$" markers
     * @param it the iterator which will iterate over the tree description
     * @return a pointer to the root of the tree built by the iteration
     */
    public static TreeNode buildStringTree(Iterator<String> it)
    {
        if (! it.hasNext())
        {
            return null;
        }
        String val = it.next();
        if (val.equals("$"))
        {
            return null;
        }
        TreeNode t = new TreeNode(val);
        t.setLeft(buildStringTree(it));
        t.setRight(buildStringTree(it));
        return t;
    }

    /**
     * read a file description of a tree and then build the tree
     * @param fileName is a valid file name for a file that describes a binary tree
     * @return a pointer to the root of the tree
     */
    public static TreeNode loadTree(String fileName)
    {
        return buildTree(FileUtil.loadFile(fileName));
    }
    /**
     * utility method that waits for a user to type text into Std Input and then press enter
     * @return the string entered by the user
     */
    private static String getUserInput()
    {
        return in.nextLine();
    }
    /**
     * plays a single round of 20 questions
     * postcondition:  plays a round of twenty questions, asking the user questions as it
     *                 walks down the given knowledge tree, lighting up the display as it goes;
     *                 modifies the tree to include information learned.
     * @param t a pointer to the root of the game tree
     * @param display which will show the progress of the game
     */
    private static void twentyQuestionsRound(TreeNode t, TreeDisplay display)
    {
        display.visit(t);
        String prev = t.getValue().toString();
        System.out.println("Is it " + prev + "?");
        String ans = getUserInput();
        if (t.getLeft() == null)
        {
            if (ans.equals("no"))
            {
                System.out.println("I give up. What is it?");
                String it = getUserInput();
                System.out.println("What distinguishes " + it + " from " + prev + "?");
                System.out.println(it + " is (a/an):");
                String diff = getUserInput();
                System.out.println();
                t.setValue(diff);
                t.setLeft(new TreeNode(it));
                t.setRight(new TreeNode(prev));
                display.displayTree(t);
            }
            else
            {
                System.out.println("I win");
            }
        }
        else
        {
            if (ans.equals("yes"))
            {
                twentyQuestionsRound(t.getLeft(), display);
            }
            else
            {
                twentyQuestionsRound(t.getRight(), display);
            }
        }
        saveTree("knowledge.txt", t);
    }
    /**
     * plays a game of 20 questions
     * Begins by reading in a starting file and then plays multiple rounds
     * until the user enters "quit".  Then the final tree is saved
     */
    public static void twentyQuestions()
    {
        String knowledge = "knowledge.txt";
        String input = "";
        TreeDisplay display = new TreeDisplay();
        while (true)
        {
            Iterator<String> k = FileUtil.loadFile(knowledge);
            TreeNode game = buildStringTree(k);
            twentyQuestionsRound(game, display);
            display.displayTree(game);
            System.out.println("Would you like to continue?");
            input = getUserInput();
            if (input.equals("quit") || input.equals("no"))
            {
                break;
            }
        }
    }
    /**
     * copy a binary tree
     * @param t the root of the tree to copy
     * @return a new tree, which is a complete copy
     *         of t with all new TreeNode objects
     *         pointing to the same values as t (in the same order, shape, etc)
     */
    public static TreeNode copy(TreeNode t)
    {
        if (t == null)
        {
            return null;
        }
        return new TreeNode(t.getValue(), copy(t.getLeft()), copy(t.getRight()));
    }

    /**
     * tests to see if two trees have the same shape, but not necessarily the
     * same values.  Two trees have the same shape if they have TreeNode objects
     * in the same locations relative to the root
     * @param t1 pointer to the root of the first tree
     * @param t2 pointer to the root of the second tree
     * @return true if t1 and t2 describe trees having the same shape, false otherwise
     */
    public static boolean sameShape(TreeNode t1, TreeNode t2)
    {
        if (t1 == null && t2 == null)
        {
            return true;
        }
        else if (t1 == null || t2 == null)
        {
            return false;
        }
        return sameShape(t1.getLeft(), t2.getLeft()) && sameShape(t1.getRight(), t2.getRight());
    }
    /**
     * Generate a tree for decoding Morse code
     * @param display the display that will show the decoding tree
     * @return the decoding tree
     */
    public static TreeNode createDecodingTree(TreeDisplay display)
    {
        TreeNode tree = new TreeNode("Morse Tree");
        display.displayTree(tree);
        insertMorse(tree, "a", ".-", display);
        insertMorse(tree, "b", "-...", display);
        insertMorse(tree, "c", "-.-.", display);
        insertMorse(tree, "d", "-..", display);
        insertMorse(tree, "e", ".", display);
        insertMorse(tree, "f", "..-.", display);
        insertMorse(tree, "g", "--.", display);
        insertMorse(tree, "h", "....", display);
        insertMorse(tree, "i", "..", display);
        insertMorse(tree, "j", ".---", display);
        insertMorse(tree, "k", "-.-", display);
        insertMorse(tree, "l", ".-..", display);
        insertMorse(tree, "m", "--", display);
        insertMorse(tree, "n", "-.", display);
        insertMorse(tree, "o", "---", display);
        insertMorse(tree, "p", ".--.", display);
        insertMorse(tree, "q", "--.-", display);
        insertMorse(tree, "r", ".-.", display);
        insertMorse(tree, "s", "...", display);
        insertMorse(tree, "t", "-", display);
        insertMorse(tree, "u", "..-", display);
        insertMorse(tree, "v", "...-", display);
        insertMorse(tree, "w", ".--", display);
        insertMorse(tree, "x", "-..-", display);
        insertMorse(tree, "y", "-.--", display);
        insertMorse(tree, "z", "--..", display);
        return tree;
    }
    /**
     * helper method for building a Morse code decoding tree.
     * postcondition:  inserts the given letter into the decodingTree,
     *                 in the appropriate position, as determined by
     *                 the given Morse code sequence; lights up the display
     *                 as it walks down the tree
     * @param decodingTree is the partial decoding tree
     * @param letter is the letter to add
     * @param code is the Morse code for letter
     * @param display is the display that will show progress as the method walks
     *        down the tree
     */
    private static void insertMorse(TreeNode decodingTree, String letter,
                                    String code, TreeDisplay display)
    {
        if (code.isEmpty())
        {
            decodingTree.setValue(letter);
            display.visit(decodingTree);
            return;
        }
        String temp = code.substring(0, 1);
        if (temp.equals("-"))
        {
            if (decodingTree.getRight() == null)
            {
                decodingTree.setRight(new TreeNode(null));
            }
            display.visit(decodingTree.getRight());
            insertMorse(decodingTree.getRight(), letter, code.substring(1), display);
        }
        else if (temp.equals("."))
        {
            if (decodingTree.getLeft() == null)
            {
                decodingTree.setLeft(new TreeNode(null));
            }
            display.visit(decodingTree.getLeft());
            insertMorse(decodingTree.getLeft(), letter, code.substring(1), display);
        }
    }
    /**
     * decodes Morse code by walking the decoding tree according to the input code
     * @param decodingTree is the Morse code decoding tree
     * @param cipherText is Morse code consisting of dots, dashes, and spaces
     * @param display is the display object that will show the decoding progress
     * @return the string represented by cipherText
     */
    public static String decodeMorse(TreeNode decodingTree, String cipherText, TreeDisplay display)
    {
        if (cipherText.isEmpty())
        {
            return "";
        }
        String current = cipherText.substring(0,1);
        String remaining = cipherText.substring(1);
        TreeNode node = decodingTree;

        while (!current.equals(" ") && !remaining.isEmpty())
        {
            if (current.equals("."))
            {
                node = node.getLeft();
            }
            else if (current.equals("-"))
            {
                node = node.getRight();
            }
            current = remaining.substring(0, 1);
            remaining = remaining.substring(1);
        }

        display.visit(node);
        return (String) node.getValue() + decodeMorse(decodingTree, remaining, display);
    }

    /**
     * optional work
     */
    public static Number eval(TreeNode expTree, TreeDisplay display)
    {
        throw new RuntimeException("Write ME!");
    }
    /**
     * optional work
     */
    public static TreeNode createExpressionTree(String exp)
    {
        throw new RuntimeException("Write ME!");
    }

//     /**
//      * debug printout
//      * postcondition: out is printed to System.out
//      * @param out the string to send to System.out
//      */
//     
//     private static void debugPrint(String out)
//     {
//         if(debug) System.out.println("debug: " + out);
//     }
}
    