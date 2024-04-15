/*
 * Some things to consider for this lab:
 *
 * Use the updated checkstyle.
 *
 * Java doesn't let us instantiate an array of type E so some casting will be needed
 *
 * Some new tags and annotations you may like to use:
 * @param  <E> describes the specific type of data stored in the list.
 *
 * When casting carefully, you may not need Java's warning regarding unsafe or unchecked operations
 * you may like to use the annotation @SuppressWarnings("unchecked")
 * Use this judiciously.
 *
 * In Javadocs use the tags either {@code text} or {@literal}.
 * to avoid html interpretations for example {@code 0 <= index <= size}
 * or use {@literal 0 <= index <= size}
 */
import java.util.*;
/**
 * The ArrayList class has all the capabilities of an array,
 * but it can also add and remove elements.
 * @author Chenkai Hong
 * @version 10/15/2023
 */
public class MyArrayList<E> implements MyList<E>
{
    //An integer for size.
    private int size;
    //An array for values.
    private Object[] arr;

    /**
     * Constructor for objects of the ArrayList class.
     */
    public MyArrayList()
    {
        size = 0;
        arr = new Object[1];
    }

    /**
     * Returns the current size of the ArrayList.
     * @Return Size of the ArrayList.
     */
    public int size()
    {
        return size;
    }

    /**
     * Appends an object to the end of the list.
     * @param obj The object being appended.
     * @return True.
     */
    public boolean add(E obj)
    {
        add(size, obj);
        return true;
    }

    /**
     * Inserts an object at position by moving elements at the position and to the right by one.
     * @precondition {@code 0 <= index <= size}
     * @param index The index of the position that the object is inserted at.
     * @param obj The object being inserted.
     */
    public void add(int index, E obj)
    {
        if(index > this.size())
            throw new ArrayIndexOutOfBoundsException("Index is not valid.");
        if (size == arr.length)
            doubleCapacity();
        for (int i = size; i > index; i--)
            arr[i] = arr[i-1];
        arr[index] = obj;
        size++;
    }

    /**
     * Doubles the capability of a list.
     */
    private void doubleCapacity()
    {
        Object[] newArr = new Object[arr.length*2];
        for(int i = 0; i < arr.length; i++)
        {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    /**
     * Returns the element at position index.
     * @param index The position index of the element being retrieved.
     * @return The element at the given index.
     * @throws ArrayIndexOutOfBoundsException if index is larger than size.
     */
    public E get(int index)
    {
        if (index >= size)
        {
            throw new ArrayIndexOutOfBoundsException("Index is not valid.");
        }
        return (E) arr[index];
    }

    /**
     * Returns the length of the list.
     * @return The length of the list.
     */
    public int getCapacity()
    {
        return arr.length;
    }

    /**
     * Removes the element from position index.
     * @param index The position index of the element being removed.
     * @return The element formerly at the specified position.
     */
    public E remove(int index)
    {
        if(index >= size())
            throw new ArrayIndexOutOfBoundsException("Index is not valid.");
        E old = (E) arr[index];
        size--;
        for (int i=index; i<size; i++)
            arr[i] = arr[i+1];
        return (E) old;
    }

    /**
     * Replaces the element at position index with obj.
     * @param index index The position index of the element being set.
     * @param obj The new obj at the given index.
     * @return The element formerly at the specified position.
     */
    public E set(int index, E obj)
    {
        if(index > size())
            throw new ArrayIndexOutOfBoundsException("Index is not valid.");
        E oldObject = (E) arr[index];
        arr[index] = obj;
        return (E) oldObject;
    }

    /**
     * Returns an iterator.
     * @return An iterator.
     */
    public MyIterator<E> iterator()
    {
        return new MyArrayList_Iterator();
    }

    /**
     * Returns a list iterator.
     * @return A list iterator.
     */
    public MyListIterator<E> listIterator()
    {
        return new MyArrayList_ListIterator();
    }

    /**
     * Returns all the elements of a list, separated by commas.
     * @return All the elements of a list.
     */
    public String toString()
    {
        if (size == 0)
            return "[]";

        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++)
            s.append(arr[i]).append(", ");
        return s.toString() + arr[size - 1] + "]";
    }

    /**
     * The MyArrayList_Iterator class implements the MyIterator interface. It can traverse a list
     * by moving onto to the next element.
     * @author Chenkai Hong
     * @version 10/15/2023
     */
    private class MyArrayList_Iterator implements MyIterator<E>
    {
        /**
         * The index of the next element.
         */
        private int nextIndex;

        /**
         * Constructor for the MyArrayList_Iterator class.
         */
        public MyArrayList_Iterator()
        {
            nextIndex = 0;
        }

        /**
         * Checks whether the list has a next element.
         * @return True, if list has a next element; false, otherwise.
         */
        public boolean hasNext()
        {
            return nextIndex < size;
        }

        /**
         * Moves onto the next element of the list.
         * @return Next element of the list.
         */
        public E next()
        {
            return (E) arr[nextIndex++];
        }

        /**
         * Removes the last element that was returned by next.
         */
        public void remove()
        {
            if (nextIndex == 0)
            {
                throw new IllegalStateException("next method has not yet been called");
            }

            MyArrayList.this.remove(--nextIndex);
        }
    }

    /**
     * The MyArrayList_ListIterator class extends myArrayList_Iterator and
     * implements the MyListIterator interface. In addition, it can add and set elements.
     * @author Chenkai Hong
     * @version 10/15/2023
     */
    private class MyArrayList_ListIterator extends MyArrayList_Iterator implements MyListIterator<E>
    {
        /**
         * The Index of the value that will be returned by next().
         */
        private int nextIndex;

        /**
         * Constructor for the MyArrayList_ListIterator class.
         */
        public MyArrayList_ListIterator()
        {
            nextIndex = 0;
        }

        /**
         * Adds element before element that would be returned by next.
         * @param obj The element to add.
         */
        public void add(E obj)
        {
            MyArrayList.this.add(nextIndex++, obj);
        }

        /**
         * Sets the value that was returned by next().
         * @param obj New value for the element.
         * @throws IllegalStateException If next() has not been called.
         */
        public void set(E obj)
        {
            MyArrayList.this.set(nextIndex-1, obj);
        }
    }
}