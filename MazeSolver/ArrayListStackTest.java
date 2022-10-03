package cs2114.mazesolver;
import student.TestCase;
import java.util.EmptyStackException;

// -------------------------------------------------------------------------
/**
 * Tests for the {@link ArrayListStack} class.
 *
 * @author Gustavo Grinsteins
 * @version 2016.03.11
 */
public class ArrayListStackTest
    extends TestCase
{
    // ~ Instance/static variables .............................................

    private ArrayListStack<String> stack;

    // ~ Public methods ........................................................


    // ----------------------------------------------------------
    /**
     * Creates a brand new, empty stack for each test method.
     */
    public void setUp()
    {
        stack = new ArrayListStack<String>();
    }


    // ----------------------------------------------------------
    /**
     * Testing the push implementation
     */
    public void testPush()
    {
        // test on empty stack
        // and t make sure the exception is thrown
        Exception thrown = null;
        try
        {
            assertEquals(true, stack.isEmpty());
            stack.top();
        }
        catch (Exception exception)
        {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof EmptyStackException);

        // test on non empty stack
        String a = "hey";
        String b = "yay";

        stack.push(a);

        assertEquals(false, stack.isEmpty());

        assertEquals("hey", stack.top());

        stack.push(b);

        assertEquals("yay", stack.top());

    }


    // ----------------------------------------------------------
    /**
     * Testing the pop implementation
     */
    public void testPop()
    {
        // test on empty stack
        // and t make sure the exception is thrown
        Exception thrown = null;
        try
        {
            assertEquals(true, stack.isEmpty());
            stack.pop();
        }
        catch (Exception exception)
        {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof EmptyStackException);

        // test on non empty stack
        String a = "hey";
        String b = "yay";

        stack.push(a);
        assertEquals(false, stack.isEmpty());
        stack.push(b);

        assertEquals("yay", stack.top());

        // pop the item on top of the stack
        stack.pop();

        assertEquals("hey", stack.top());

        stack.pop();
        assertEquals(true, stack.isEmpty());

    }


    // ----------------------------------------------------------
    /**
     * This method will test the size method of the stack
     */
    public void testSize()
    {
        // test empty
        assertEquals(true, stack.isEmpty());
        assertEquals(0, stack.size());

        // test non-empty
        String a = "hey";
        String b = "yay";

        stack.push(a);
        assertEquals(false, stack.isEmpty());
        stack.push(b);

        assertEquals(2, stack.size());

    }
}
