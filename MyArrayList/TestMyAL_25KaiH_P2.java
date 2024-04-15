
/**
 * Demonstrates execution of each and every method within MyArrayList.  
 * 
 * @author Marina Peregrino  
 * @version 12 19, 2019     
 *          Created Skeleton to outline some basic tests for students to create.  
 * 
 * @author Chenkai Hong
 * @version 10/24/2023
 */
public class TestMyAL_25KaiH_P2
{

    /**
     * Tests each of the methods for the MyArrayList: 
     * 
     * toString
     * size()
     * add(n)
     * add(i, n)
     * 
     * remove
     * set
     * get
     * 
     */
    public static void main (String [] args){ 
        /*
         * Things to consider:
         * What would be different for a Linked List?  
         */

        String studentName = "Chenkai Hong"; //Insert your name here.  
        System.out.println("Tests MyAL for "+ studentName); 
        
        // Test that MyArrayList implements the interface
        MyList<Integer> nums1 = new MyArrayList<Integer>();
        /**
         * test1:  Tests for each of the following: 
         * add(i, n) to an empty list,
         * add(i, n) to an existing list,
         * size,
         * toString 
         */
        boolean test1 = true;
        if (!test1){
            System.out.println ("Skipping Test #1"); 
        }
        else{
            System.out.println ("#1 Testing: add at last index");
            for (int i=0; i < 5; i++){
                /**
                 * use add(i, number)
                 * print the resulting list after each pass
                 */
                nums1.add(i, i);
                System.out.println ("" + nums1 + ", size: " + nums1.size());
            }
        }
        nums1= null;   //release unused data 
        //////////////////////////////////////////////////////////////////////

        /**
         * test2:  Tests for each of the following: 
         * add(n) to an empty list,
         * add(n) to an existing list,
         * size,
         * toString 
         */
        MyList<Integer> nums2 = new MyArrayList<Integer>();
        boolean test2 = true; 
        if (!test2)
        {
            System.out.println ("Skipping Test #2"); 
        }
        else
        {
            System.out.println ("#2 Testing: add (appending)");
            for (int i=0; i < 5; i++){
                /**
                 * use add(number)
                 * print the resulting list after each pass
                 */
                nums2.add(i);
                System.out.println ("" + nums2 + ", size: " + nums2.size());
            }
        }

        /**
         * test3:  Tests for each of the following: 
         * iterator,
         * hasNext,
         * next,
         */
        boolean test3 = true; 
        if (!test3){
            System.out.println ("Skipping Test #3"); 
        }
        else{
            /* */
            System.out.println ("List:\n" + nums2);
            System.out.println ("#3 Testing: iterator:  ");
            String s ="";
            MyArrayList<String> myAl = new MyArrayList<String>();
            for (int num : nums2){ 
            }
            /*
             * get an iterator 
             * loop through and get values from the iterator 
             * append the values to a String 
            /* */ 
            MyIterator iterator1 = nums2.listIterator();
            while(iterator1.hasNext())
            {
                int val = (int) iterator1.next(); 
                s += val + ", ";
            }
            System.out.println(" yields:");
            System.out.println(s);
        }

        nums2= null;  //release unused data 
        //////////////////////////////////////////////////////////////////////

        /**
         * Previous tests were Integer list subsequent tests are String list
         * 
         * Add tests for the methods 
         * append,              
         * insert, 
         * remove, 
         * set, 
         * get
         * 
         * from begin, middle and end of list
         * 
         * test index out of bounds
         */

        /**
         * test4:  Tests for each of the following: 
         * append,              
         * insert, 
         * remove, 
         * set, 
         * get
         */
        MyArrayList<String> list = new MyArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        boolean test4 = true; 
        if (!test4){
            System.out.println ("Skipping Test #4"); 
        }
        else{
            System.out.println("\n Other tests:");
            System.out.println("List: "+ list);
            System.out.println("Testing Insert: ");
            list.add(1,"b");
            System.out.println(list);
            System.out.println("Testing Remove: ");
            list.remove(1);
            System.out.println(list);
            System.out.println("Testing set: ");
            list.set(list.size()-1, "d");
            System.out.println(list);
            String test = list.get(2);
            System.out.println("Testing get: ");
            System.out.println(test);
        }

        /**
         * test5:  Tests for each of the following: 
         * 
         * 
         * 
        boolean test5 = true; 
        if (!test5){
            System.out.println ("Skipping Test #5"); 
        }
        else{
            System.out.println("\n More tests:");
            throw new RuntimeException("Write code to test MyArrayList");
        }
         */

        /**
         * test6:  Tests exception for index out of bounds 
         * tests each of the methods that use index, 
         * add, remove, set, get
         * Tests when underlying array does have such and index and when it does not.   
         */
        boolean test6 = true; 
        if (!test6){
            System.out.println ("Skipping Test #6"); 
        }
        else{
            System.out.println("List: " + list + "  Size of list: " + list.size());
            System.out.println("Capacity of list: " + list.getCapacity());
            System.out.println("\n Test index out of bounds exception:");
            try 
            {
                list.set(6, "g");
            } 
            catch (IndexOutOfBoundsException e) 
            {
            System.out.println(e);
            System.out.println("Set at index 6.");
            }
            try 
            {
                list.add(6, "g");
            } 
            catch (IndexOutOfBoundsException e) 
            {
            System.out.println( e );
            System.out.println("Adding at index 6.");
            }
            try 
            {
                list.remove(6);
            } 
            catch (IndexOutOfBoundsException e) 
            {
            System.out.println(e);
            System.out.println("Removing at index 6.");
            }
            try 
            {
                list.get(6);
            } 
            catch (IndexOutOfBoundsException e) 
            {
            System.out.println( e );
            System.out.println("Get at index 6.");
            }
        }
    }
}
