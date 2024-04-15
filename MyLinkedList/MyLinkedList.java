import java.util.NoSuchElementException;

/**
 * The MyLinkedList class is a doubly-linked list
 * and an implementation of the LinkedList class of Java.
 *
 * @author Chenkai Hong
 * @version 10/17/2023
 */
public class MyLinkedList<E> implements MyList<E>
{
    // instance variables
    private DoubleNode first;
    private DoubleNode last;
    private int size;

    /**
     * Constructor for the MyLinkedList class.
     */
    public MyLinkedList()
    {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Returns a string of values in the linked list, separated by commas.
     *
     * @return The linked list in string form.
     */
    public String toString()
    {
        DoubleNode node = first;
        if (node == null)
            return "[]";
        String s = "[";
        while (node.getNext() != null)
        {
            s += node.getValue() + ", ";
            node = node.getNext();
        }
        return s + node.getValue() + "]";
    }

    /**
     * Gets the node at the specified index from the start of the linked list.
     *
     * @param index The specified index.
     * @return The node at the index.
     * @precondition 0 <= index <= size / 2
     * @postcondition starting from first, returns the node
     * with given index (where index 0 returns first)
     */
    private DoubleNode getNodeFromFirst(int index)
    {
        DoubleNode curr = first;
        for (int i = 0; i < index; i++)
        {
            curr = curr.getNext();
        }
        return curr;
    }

    /**
     * Gets the node at the specified index from the end of the linked list.
     *
     * @param index The specified index.
     * @return The node at the index.
     * @precondition size / 2 <= index < size
     * @postcondition starting from last, returns the node
     * with given index (where index size-1 returns last)
     */
    private DoubleNode getNodeFromLast(int index)
    {
        DoubleNode curr = last;
        for (int i = size() - 1; i > index; i--)
        {
            curr = curr.getPrevious();
        }
        return curr;
    }

    /**
     * Gets the node at the specified index.
     *
     * @param index The specified index.
     * @return The node at the index.
     * @throws IndexOutOfBoundsException if there is no more node in the linked list.
     * @precondition 0 <= index < size
     * @postcondition starting from first or last (whichever
     * is closer), returns the node with given index)
     */
    private DoubleNode getNode(int index)
    {
        if (index > this.size())
            throw new IndexOutOfBoundsException("Index is not valid.");
        if (index <= size / 2)
            return getNodeFromFirst(index);
        else
            return getNodeFromLast(index);
    }

    /**
     * Returns the size of the linked list.
     *
     * @return Size of the linked list.
     */
    public int size()
    {
        return size;
    }

    /**
     * Gets the value of the node at the specified index.
     *
     * @param index The specified index.
     * @return The value of the node.
     */
    public E get(int index)
    {
        return (E) getNode(index).getValue();
    }

    /**
     * Sets the value of the node at the specified index,
     *
     * @param index The specified index.
     * @param obj   The new object.
     * @return The original object.
     * @throws IndexOutOfBoundsException if there is no more node in the linked list.
     * @postcondition replaces the element at position index with obj
     * returns the element formerly at the specified position
     */
    public E set(int index, E obj)
    {
        if (index >= size)
        {
            throw new IndexOutOfBoundsException("Index is not valid.");
        }
        E temp = get(index);
        getNode(index).setValue(obj);
        return temp;
    }

    /**
     * Adds obj to the end of the list.
     *
     * @param obj The object being added.
     * @return True.
     * @postcondition appends obj to end of list; returns true
     */
    public boolean add(E obj)
    {
        add(size, obj);
        return true;
    }

    /**
     * Removes the element at the given index and returns the removed element,
     *
     * @param index The index of the node being removed.
     * @return The removed node.
     * @throws IndexOutOfBoundsException if there is no more node in the linked list.
     * @postcondition removes element from position index, moving elements
     * at position index + 1 and higher to the left
     * (subtracts 1 from their indices) and adjusts size
     * returns the element formerly at the specified position
     */
    public E remove(int index)
    {
        if (index >= this.size())
            throw new IndexOutOfBoundsException("Index is not valid.");
        DoubleNode removed = getNode(index);
        E val = (E) removed.getValue();
        if (removed == first)
        {
            first = removed.getNext();
        }
        else if (removed == last)
        {
            last = removed.getPrevious();
        }
        if (removed.getPrevious() != null)
        {
            removed.getPrevious().setNext(removed.getNext());
        }
        if (removed.getNext() != null)
        {
            removed.getNext().setPrevious(removed.getPrevious());
        }
        removed = null;
        size--;
        return val;
    }

    /**
     * Adds obj at the specified index.
     *
     * @param index The index at which the node is inserted at.
     * @param obj   The object being inserted.
     * @throws IndexOutOfBoundsException if there is no more node in the linked list.
     * @precondition 0 <= index <= size
     * @postcondition inserts obj at position index,
     * moving elements at position index and higher
     * to the right (adds 1 to their indices) and adjusts size
     */
    public void add(int index, E obj)
    {
        if (index > this.size())
            throw new IndexOutOfBoundsException("Index is not valid.");
        if (first == null)
        {
            first = new DoubleNode(obj);
            last = first;
            size++;
            return;
        }
        else if (index == size)
        {
            DoubleNode old = getNode(index);
            DoubleNode addedNode = new DoubleNode(obj);
            old.setNext(addedNode);
            addedNode.setPrevious(old);
            last = addedNode;
            size++;
            return;
        }
        DoubleNode old = getNode(index);
        DoubleNode addedNode = new DoubleNode(obj);
        addedNode.setNext(old);
        addedNode.setPrevious(old.getPrevious());
        old.setPrevious(addedNode);
        if (addedNode.getPrevious() != null)
        {
            addedNode.getPrevious().setNext(addedNode);
        }
        else
        {
            first = addedNode;
        }
        if(addedNode.getNext() == null)
        {
            last = addedNode;
        }
        size++;
    }

    /**
     * Adds obj to the beginning of the linked list.
     *
     * @param obj The object being added.
     */
    public void addFirst(E obj)
    {
        add(0, obj);
    }

    /**
     * Adds obj to the end of the linked list.
     *
     * @param obj The object being added.
     */
    public void addLast(E obj)
    {
        add(size, obj);
    }

    /**
     * Gets the value of the first node of the linked list.
     *
     * @return The value of the first node.
     */
    public E getFirst()
    {
        return (E) first.getValue();
    }

    /**
     * Gets the value of the last node of the linked list.
     *
     * @return The value of the last node.
     */
    public E getLast()
    {
        return (E) last.getValue();
    }

    /**
     * Removes the node at the beginning of the linked list.
     * @return The returned node.
     */
    public E removeFirst()
    {
        return remove(0);
    }

    /**
     * Removes the node at the end of the linked list.
     * @return The removed node.
     */
    public E removeLast()
    {
        return remove(size - 1);
    }

    /**
     * Gets an iterator for the Linked List class.
     *
     * @return Iterator for the linked list class.
     */
    @Override
    public MyIterator<E> iterator()
    {
        return new MyLinkedListIterator();
    }

    /**
     * Gets a listIterator for the Linked List class.
     *
     * @return listIterator for the linked list class.
     */
    @Override
    public MyListIterator<E> listIterator()
    {
        return new MyLinkedList_ListIterator();
    }

    /**
     * The MyLinkedListIterator class iterates over linked lists.
     * It has methods hasNext(), next(), and remove().
     *
     * @author Chenkai Hong
     * @version 10/17/2023
     */
    private class MyLinkedListIterator implements MyIterator<E>
    {
        // instance variable
        private DoubleNode nextNode;

        /**
         * Constructor for the MyLinkedListIterator class.
         */
        public MyLinkedListIterator()
        {
            nextNode = first;
        }

        /**
         * Checks if the linked list has a next node.
         *
         * @return True, if the linked has a next node; false, otherwise.
         */
        public boolean hasNext()
        {
            return nextNode != null;
        }

        /**
         * Returns the value of the next node and moves forward once in the linked list.
         *
         * @return The value of the next node in the linked list.
         * @throws NoSuchElementException if there is no more node in the linked list.
         */
        public E next()
        {
            if (!hasNext())
                throw new NoSuchElementException("No more node in the linked list.");
            E val = (E) nextNode.getValue();
            nextNode = nextNode.getNext();
            return val;
        }

        /**
         * Removes the last element returned by next.
         *
         * @throws IllegalStateException  if next() has not been called.
         * @throws NoSuchElementException if there is no more node in the linked list.
         * @postcondition Element last returned by next is removed.
         */
        public void remove()
        {
            if (nextNode == first)
                throw new IllegalStateException("next() has not been called.");
            else if (!hasNext())
            {
                MyLinkedList.this.removeLast();
                throw new NoSuchElementException("No node in linked list.");
            }

            DoubleNode remove = nextNode.getPrevious();

            if (remove.getPrevious() == null)
                MyLinkedList.this.removeFirst();
            else
            {
                DoubleNode prev = remove.getPrevious();
                prev.setNext(nextNode);
                nextNode.setPrevious(prev);
                size--;
            }
        }
    }

    /**
     * The MyLinkedList_ListIterator class extends myLinkedList_Iterator and
     * implements the MyListIterator interface. It can add and set elements.
     *
     * @author Chenkai Hong
     * @version 01/09/2024
     */
    private class MyLinkedList_ListIterator extends MyLinkedListIterator
                                            implements MyListIterator<E>
    {
        // Instance Variable
        private int nextIndex;

        //Constructor for the MyLinkedList_ListIterator class.
        public MyLinkedList_ListIterator()
        {
            nextIndex = 0;
        }

        /**
         * Adds element before element that would be returned by next.
         *
         * @param obj The element to add.
         */
        public void add(E obj)
        {
            MyLinkedList.this.add(nextIndex++, obj);
        }

        /**
         * Sets the value that was returned by next().
         *
         * @param obj New value for the element.
         * @throws IllegalStateException If next() has not been called.
         */
        public void set(E obj)
        {
            MyLinkedList.this.set(nextIndex - 1, obj);
        }
    }
}
