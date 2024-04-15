import javax.swing.*;
import java.util.Scanner;

/**
 * The main class tests methods of the TreeUtil class including
 * sinkDown(), remove(), insert(), buildHeap(), and heapSort().
 *
 * @author Kai Hong
 * @version 01/12/2024
 */
public class Main {
    private static HeapDisplay h;
    private static HeapDisplay h1;

    public static void main(String[] args)
    {
        test();
    }

    /**
     * Compiles all aspects of the tester in a menu-based system.
     */
    public static void test()
    {
        h = new HeapDisplay();
        h1 = new HeapDisplay();
        String initial = "";
        int response = 0;
        Scanner in = new Scanner(System.in);
        boolean notValidInput = true;
        boolean alreadyHappened = false;

        while (notValidInput) {
            try
            {
                if (!alreadyHappened)
                {
                    System.out.println("\fWelcome to the Heaps Tester.\n" +
                            "This tests the implementation of the HeapUtils class.\n\n\n" +
                            "Enter 1 to test sinkDown\n" +
                            "2 to test remove\n" +
                            "3 to test insert\n" +
                            "4 to test build heap\n" +
                            "5 to test heap sort\n" +
                            "6 to quit\n");
                }
                else
                {
                    System.out.println("\fInvalid input, please try again.\n\n" +
                            "Enter 1 to test sinkDown\n" +
                            "2 to test remove\n" +
                            "3 to test insert\n" +
                            "4 to test build heap\n" +
                            "5 to test heap sort\n" +
                            "6 to quit\n");
                }

                initial = in.nextLine();
                response = Integer.parseInt(initial);
                notValidInput = false;
            }
            catch (IllegalArgumentException i)
            {
                notValidInput = true;
                alreadyHappened = true;
            }
        }


        while (true)
        {
            if (response == 1)
            {
                testSinkDown();
                System.out.println("Sinking down root node.");
            }
            else if (response == 2)
            {
                testRemove();
                System.out.println("Removing");
            }
            else if (response == 3)
            {
                testInsert();
                System.out.println("Inserting 50 to the Heaps");
            }
            else if (response == 4)
            {
                testBuildHeap();
                System.out.println("Testing buildHeap() with random values");
            }
            else if (response == 5)
            {
                testHeapSort();
                System.out.println("Testing heapSort()");
            }
            else if (response == 6)
            {
                System.out.print("\fExiting Tester.");
                System.exit(1);
            }
            else
                System.out.print("\fInvalid input, please try again.\n");
            //System.out.print("\n\nEnter 1 to test basic tree methods, " +
            //                     "2 for morse code methods, 3 to quit.\n");
            //response = Integer.parseInt (in.nextLine());
            notValidInput = true;
            alreadyHappened = false;
            while (notValidInput)
            {
                try
                {
                    if (!alreadyHappened)
                        System.out.println(
                                "Enter 1 to test sinkDown\n" +
                                        "2 to test remove\n" +
                                        "3 to test insert\n" +
                                        "4 to test build heap\n" +
                                        "5 to test heap sort\n" +
                                        "6 to quit\n");
                    else
                        System.out.println("\fInvalid input, please try again.\n\n" +
                                "Enter 1 to test sinkDown\n" +
                                "2 to test remove\n" +
                                "3 to test insert\n" +
                                "4 to test build heap\n" +
                                "5 to test heap sort\n" +
                                "6 to quit\n");
                    initial = in.nextLine();
                    response = Integer.parseInt(initial);
                    notValidInput = false;
                }
                catch (IllegalArgumentException i)
                {
                    notValidInput = true;
                    alreadyHappened = true;
                }
            }
        }
    }

    /**
     * Tests sinkDown().
     */
    public static void testSinkDown()
    {
        Comparable[] intArray = new Comparable[4];
        intArray[1] = 86;
        intArray[2] = 10;
        intArray[3] = 20;
        h.setTitle("Before");
        h.displayHeap(intArray, 3);
        HeapUtils.sinkDown(intArray, 3);
        h1.setTitle("After");
        h1.displayHeap(intArray, 3);
    }

    /**
     * Tests remove().
     */
    public static void testRemove()
    {
        Comparable[] intArray = new Comparable[5];
        intArray[1] = 10;
        intArray[2] = 30;
        intArray[3] = 20;
        intArray[4] = 40;
        h.setTitle("Before");
        h.displayHeap(intArray, 4);
        HeapUtils.remove(intArray, 4);
        h1.setTitle("After");
        h1.displayHeap(intArray, 4);
    }

    /**
     * Tests insert().
     */
    public static void testInsert()
    {
        Comparable[] intArray = new Comparable[6];
        intArray[1] = 10;
        intArray[2] = 20;
        intArray[3] = 30;
        intArray[4] = 40;
        h.setTitle("Before");
        h.displayHeap(intArray, 4);
        HeapUtils.insert(intArray, 50, 4);
        h1.setTitle("After");
        h1.displayHeap(intArray, 5);
    }

    /**
     * Tests buildHeap().
     */
    public static void testBuildHeap()
    {
        Comparable[] intArray = new Comparable[12];
        for (int i = 0; i < intArray.length; i++)
        {
            int rand = (int) (Math.random() * 100);
            intArray[i] = rand;
        }
        HeapUtils.buildHeap(intArray, 11);
        h.displayHeap(intArray, 11);
    }

    /**
     * Tests heapSort()
     */
    public static void testHeapSort()
    {
        Comparable[] intArray = new Comparable[12];
        for (int i = 0; i < intArray.length; i++)
        {
            int rand = (int) (Math.random() * 100);
            intArray[i] = rand;
        }
        h.displayHeap(intArray, 11);
        HeapUtils.heapSort(intArray, 11);
        h1.displayHeap(intArray, 11);
    }
}
