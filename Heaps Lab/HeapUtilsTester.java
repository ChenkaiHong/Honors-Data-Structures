import java.util.Arrays;
import java.util.List;
import java.util.Collections;

/**
 * Tests the methods in HeapUtils.
 * 
 * uses HeapDisplay 
 *     public void displayHeap(Comparable[] heapArray, int heapSize)

 * 
 * @author  Marina Peregrino 
 *   With assistance from 
 *
 * @version Oct 2020 based of the previous tester
 * @version Jan 2022    modified to go with 2015 lab
 */
public class HeapUtilsTester 
{
    /* HeapUtilsTester methods: */

    /**
     * fills an array with random Integers from 1 to 100
     * @param   rando an array to hold random Integers
     */
    public static void fillRandom(Comparable [] rando){
        for (int i=0; i < rando.length; i++)
        {
            double ran = Math.random()*101;
            rando[i] = (int) ran  + 1; 
        }

    }

    /**
     * runs a specific test on heapsort
     */
    public static void checkSort()
    {
        Comparable [] data = {null, 19, 68, 26, 83, 15, 62};
        //{null, 39, 40, 8, 87};
        HeapUtils.heapSort(data, data.length - 1 );
        if (! isDescending(data)){
            System.out.println("Error "+ Arrays.toString(data));
            //break;
        }
    }

    /**
     * repeatedly creates random arrays and checks if heapsort works. 
     * Creates larger and larger arrays, checks that they are descending
     * Prints message and shows the array if there is an error.  
     */
    public static void checkSorts2()
    {
        int errorCount =0; 
        System.out.print("\nTests for HeapSort: ");
        for (int len =5; len < 200; len++){
            Comparable [] data = new Comparable[len];
            fillRandom(data);
            data[0] = null;

            HeapUtils.heapSort(data, len-1);
            if (! isDescending(data)){
                System.out.println("Error "+ Arrays.toString(data));
                errorCount++;
                break;
            }
        }
        System.out.println(errorCount + " errors");
    }

    /**
     * Checks if given info is not ascending
     * 
     * @param   info the data to check if ascending, duplicates OK
     *          ignores index 0 
     * 
     * @return false if data is ascending 
     */
    public static boolean isDescending(Comparable [] info)
    {
        // compares index 1 onward to see if is not ascending
        for (int i=2; i< info.length; i++)
        {
            if (info[i-1].compareTo(info[i]) <0) {
                System.out.println (info[i-1] + ", " + info[i]);
                return false;
            }
        }
        return true;
    }

    /**
     * Tests all the HeapUtils for a minHeap.
     * tests: 
     *  buildHeap,
     *  remove,
     *  insert, 
     *  heapsort 
     *
     * @param args arguments from the command line
     */
    public static void main(String [ ] args)
    {
        boolean test1 = true;
        if (test1){
            // create and display an array with random data
            Comparable [] info = new Integer[8];
            fillRandom(info);
            info[0] = null; //first element is unused
            info = new Integer [] {null, 75, 66, 33, 83, 27, 32, 94};

            HeapDisplay disp1 = new HeapDisplay ("random"); 
            disp1.displayHeap(info, 7);

            // create a 2nd display
            // show the array as a heap 
            HeapUtils.buildHeap(info, 7);
            HeapDisplay disp2 = new HeapDisplay ("heap"); 
            disp2.displayHeap(info, 7);

            Debug.getUserInput("Press enter to continue. remove:");
            // remove one value, display
            int size = info.length-1; 
            Integer item = (Integer) HeapUtils.remove(info, size); 
            size --; 
            disp2.displayHeap(info, size);

            // add the value back, display 
            Debug.getUserInput("Press enter to continue. insert:");
            HeapUtils.insert( info, (Comparable)(item--), size); 
            size++; 
            disp2.displayHeap(info, size);

            Debug.getUserInput("Press enter to continue. ready to sort:");
            // on one display, show an array with new random data. 
            fillRandom(info);
            info[0] = null; //first element is unused
            size = info.length-1; 
            disp1.displayHeap(info, size);
            System.out.println("Random" + Arrays.toString(info));

            // on a second display, show the array as a heap. 
            HeapUtils.buildHeap(info, size);
            disp2.displayHeap(info, size);
            System.out.println("Heap" + Arrays.toString(info));

            // on a third display, show the array sorted.   
            HeapUtils.heapSort(info, size); 
            HeapDisplay disp3 = new HeapDisplay ("sorted"); 
            disp3.displayHeap(info, size);
            System.out.println("Sorted" + Arrays.toString(info));
        }

        checkSort();
        checkSorts2();
    }
}
