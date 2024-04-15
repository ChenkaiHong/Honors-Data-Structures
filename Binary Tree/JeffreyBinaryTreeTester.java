import javax.swing.*;
import java.util.Scanner;

/**
 * Tests the logic for leftmost, rightmost, max depth count, node and leaf counts, 
 * traversal (preorder, in order, postorder) and save tree.
 * It also tests the bonus features sameShape with a copy and 
 * sameShape with a different tree.
 *
 * @author Jeffrey Hanke
 * @version November 27, 2012
 */
public class JeffreyBinaryTreeTester
{
    public static void main(String[] args)
    {
        TreeNode tree = TreeUtil.createRandom(4);
        TreeDisplay display = new TreeDisplay();
        display.displayTree(tree);

        System.out.println("# Nodes: " + TreeUtil.countNodes(tree));
        System.out.println("# Leaves: " + TreeUtil.countLeaves(tree));
        TreeUtil.preOrder(tree, display);
        TreeUtil.saveTree("tree", tree);
        
        System.out.println("Leftmost: " + TreeUtil.leftmost(tree));
        System.out.println("Rightmost: " + TreeUtil.rightmost(tree));
        System.out.println("Max Depth: " + TreeUtil.maxDepth(tree));
        
        TreeUtil.preOrder(tree, display);
       
        Scanner in = new Scanner(System.in);
        System.out.println("Preorder was just displayed.  Type in something to start the inorder display");
        in.nextLine();
        TreeUtil.inOrder(tree, display);

        System.out.println("Inorder was just displayed. Type in something to start the postorder display");
        in.nextLine();
        TreeUtil.postOrder(tree, display);


        TreeUtil.saveTree("tree", tree);
        TreeNode tree2 = TreeUtil.createRandom(4);
        TreeDisplay display2 = new TreeDisplay();
        display2.displayTree(tree2);
        if(!TreeUtil.sameShape(tree, tree2))
        {
            System.out.println("sameShape() returns false with two trees that are probably different");
        }
        else
        {
            System.out.println("WARNING:  sameShape() returns false with two trees that are probably different");
        }
        TreeNode copy = TreeUtil.copy(tree);
        if(TreeUtil.sameShape(tree, copy))
        {
            System.out.println("sameShape() returns true with a copy tree");
        }
        else
        {
            System.out.println("WARNING:  sameShape() returns false with a copy tree; something is wrong");
        }
        TreeDisplay display3 = new TreeDisplay();
        display3.displayTree(copy);
    }
    
    /**
    * called by the display object to send back the node value
    * when a node is visited
    * 
    * @param value the value passed that is printed out
    */
    public void sendValue(Object value)
    {
        System.out.print(value + " ");
    }
}