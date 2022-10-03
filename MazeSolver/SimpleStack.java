package cs2114.mazesolver;
import java.util.EmptyStackException;

// -------------------------------------------------------------------------
/**
 * This interface represents a simple stack abstract data type.
 *
 * @param <T>
 *            the element type stored in the stack
 * @author Gustavo Grinsteins
 * @version 2016.03.11
 */
public interface SimpleStack<T>
{
    // ~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Pushes the specified item onto the top of the stack.
     *
     * @param item
     *            the item to push onto the stack
     */
    void push(T item);


    // ----------------------------------------------------------
    /**
     * Pops an item off the top of the stack but it does not return it.
     *
     * @throws EmptyStackException
     *             if the stack is empty
     */
    void pop();


    // ----------------------------------------------------------
    /**
     * Gets the item at the top of the stack.
     *
     * @return the item at the top of the stack
     * @throws EmptyStackException
     *             if the stack is empty
     */
    T top();


    // ----------------------------------------------------------
    /**
     * Gets the number of items in the stack.
     *
     * @return the number of items in the stack
     */
    int size();


    // ----------------------------------------------------------
    /**
     * Gets a value indicating whether the stack is empty.
     *
     * @return true if the stack is empty, otherwise false
     */
    boolean isEmpty();
}
