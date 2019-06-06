

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BSTreeTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BSTreeTest
{
    private BSTree<Integer> tree;
    /**
     * Default constructor for test class BSTreeTest
     */
    public BSTreeTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        tree = new BSTree<>();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        tree = null;
    }
    
    @Test
    public void TestInsertFirst() {
        tree.insert(1);
        assertEquals("[1]", tree.asList(BSTree.Order.INORDER).toString());
    }
    
    @Test
    public void TestInsertBefore() {
        tree.insert(1);
        tree.insert(0);
        assertEquals("[0, 1]", tree.asList(BSTree.Order.INORDER).toString());
    }
}
