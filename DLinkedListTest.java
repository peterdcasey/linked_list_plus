

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DLinkedListTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DLinkedListTest
{
    /**
     * Default constructor for test class DLinkedListTest
     */
    
    DLinkedList<Person> list;
    
    public DLinkedListTest()
    {
        list = new DLinkedList<>();
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        list.addLast(new Person("Bob"));
        list.addLast(new Person("Betty"));
        list.addLast(new Person("Barb"));
    }

    @Test
    public void testSize() {
        assertEquals(3, list.size());
    }
    
    @Test
    public void testClear() {
        list.clear();
        assertEquals(0, list.size());
    }
    
    @Test
    public void testAddFirst() {
        list.addFirst(new Person("Andrea"));
        assertEquals("Andrea", list.getFirst().getName());
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
