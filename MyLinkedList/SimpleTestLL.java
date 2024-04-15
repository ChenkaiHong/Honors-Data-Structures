import java.util.LinkedList;
import java.util.Iterator;

/**
 * SimpleTest tests basic operations for MyLinkedList by comparing to Java's LinkedList.
 *
 * @author Marina Peregrino
 * @version Oct 2017
 */
public class SimpleTestLL
{
    //determines whether it prints each operation
    private static final boolean DEBUG = false;
    private static void debug(String s)
    {
        if (DEBUG)
            System.out.println(s);
    }

    public static void main(String[] args)
    {
        String name = "Chenkai Hong"; 
        System.out.println("Tests MyAL for "+ name); 
        
        MyLinkedList simple = new MyLinkedList();  //Should implement the interface
        MyLinkedList<Integer> your = new MyLinkedList<Integer>();
        LinkedList<Integer> real = new LinkedList<Integer>();

        boolean test = true; 
        if (!test){
            System.out.println ("Skipping Test #1"); 
        }
        else{
            System.out.println ("Adding items to the list. Test insert then append.  ");
            for (int i = 0; i < 10; i++)
            {
                debug("your:  " + your);
                debug("real:  " + real);
                Integer value;
                if(!DEBUG) value = new Integer((int) (Math.random() * 100));
                else value = i+10 ; 
                debug("add(" + value + ", "+ i + ")");
                real.add(i, value);
                your.add(i, value);
                System.out.println (your);
            }
            int size = real.size(); 
            real.add(size, size);
            your.add(size, size); 
            System.out.println (your);
        }

        System.out.print (" iterator:  ");
        Iterator<Integer> your_it = your.iterator();
        Integer yourVal;
        Integer value;
        int index=0;
        String s ="";
        while (your_it.hasNext())
        {
            yourVal = your_it.next();
            s += yourVal + ", ";
        }
        System.out.println("next gives:");
        System.out.println(s);

        // above has tested append After insert
        // here tests append Bsefore insert
        System.out.println("\nOther tests: append, remove, insert, set, get");
        MyLinkedList<String> tester = new MyLinkedList<String>();
        System.out.println("\ntest add(obj), add a b, buzz, c");
        System.out.println("Starts: "+tester);
        tester.add("a"); System.out.println(tester);
        tester.add("b"); System.out.println(tester);
        tester.add("buzz"); System.out.println(tester);
        tester.add("c"); System.out.println(tester);

        System.out.println("\ntest remove(i), remove 3(c), 1(b), 0(a)");
        System.out.println("Starts: "+tester);
        tester.remove(3); System.out.println(tester);//c
        tester.remove(1); System.out.println(tester);//b
        tester.remove(0); System.out.println(tester);//a

        System.out.println("\ntest add(i, obj), add 0,A  1,B, ____ 3,C");
        System.out.println("Starts: "+tester);
        tester.add(0,"A"); System.out.println(tester);
        tester.add(1, "B"); System.out.println(tester);
        //"buzz"; 
        tester.add(3,"C"); System.out.println(tester);

        System.out.println("\ntest set(i, obj), set 0,x  1,y  3,zzz");
        System.out.println("Starts: "+tester);
        tester.set(0, "x"); System.out.println(tester);
        tester.set(1, "y"); System.out.println(tester);
        tester.set(3,"zzz"); System.out.println(tester);

        System.out.println("\ntest get(i), get 0,  1,  3");
        System.out.println("Starts: "+tester);
        System.out.println(tester.get(0));
        System.out.println(tester.get(1));
        System.out.println(tester.get(3));
    }
}

