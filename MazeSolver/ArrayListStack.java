package cs2114.mazesolver;
import java.util.ArrayList;
import java.util.EmptyStackException;

// -------------------------------------------------------------------------
/**
 * An implementation of the stack data type that adapts an ArrayList to store
 * its contents. This is a PARTIAL IMPLEMENTATION that needs completion.
 *
 * @param <T>
 *            the type of elements stored in the stack
 * @author Gustavo Grinsteins (gug218)
 * @version 2016.03.11
 */
public class ArrayListStack<T>
    implements SimpleStack<T>
{
    // ~ Instance/static variables ............................................

    private ArrayList<T> stack;

    // ~ Constructors .........................................................


    // ----------------------------------------------------------
    /**
     * Construct our new stack array object
     */
    public ArrayListStack()
    {
        stack = new ArrayList<T>();
    }

    // ~ Methods ..............................................................


    // ----------------------------------------------------------
    /**
     * Pushes the specified item onto the top of the stack.
     *
     * @param item
     *            the item to push onto the stack
     */
    public void push(T item)
    {
        stack.add(item);
    }


    // ----------------------------------------------------------
    /**
     * Pops an item off the top of the stack but it does not return it.
     *
     * @throws EmptyStackException
     *             if the stack is empty
     */
    public void pop()
    {
        if (stack.isEmpty())
        {
            throw new EmptyStackException();
        }
        else
        {
            // write code for when the stack is not empty
            stack.remove(stack.size() - 1);
        }

    }


    // ----------------------------------------------------------
    /**
     * Gets the item at the top of the stack.
     *
     * @return the item at the top of the stack
     * @throws EmptyStackException
     *             if the stack is empty
     */
    public T top()
    {
        if (stack.isEmpty())
        {
            throw new EmptyStackException();
        }
        else
        {
            // write code for when the stack is not empty
            return stack.get(stack.size() - 1);
        }

    }


    // ----------------------------------------------------------
    /**
     * Gets the number of items in the stack.
     *
     * @return the number of items in the stack
     */
    public int size()
    {
        return stack.size();
    }


    // ----------------------------------------------------------
    /**
     * Gets a value indicating whether the stack is empty.
     *
     * @return true if the stack is empty, otherwise false
     */
    public boolean isEmpty()
    {
        return stack.isEmpty();
    }
}
