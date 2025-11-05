import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SalesItemTest.
 *
 * @author  mik
 * @version 0.1
 */
public class SalesItemTest
{
    /**
     * Default constructor for test class SalesItemTest
     */
    public SalesItemTest()
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

    /**
     * Test that a comment can be added, and that the comment count is correct afterwards.
     */
    @Test
    public void testAddComment()
    {
        SalesItem salesIte1 = new SalesItem("Brain surgery for Dummies", 21998);
        assertEquals(true, salesIte1.addComment("James Duckling", "This book is great. I perform brain surgery every week now.", 4));
        assertEquals(1, salesIte1.getNumberOfComments());
    }

    /**
     * Test that a comment using an illegal rating value is rejected.
     */
    @Test
    public void testIllegalRating()
    {
        SalesItem salesIte1 = new SalesItem("Java For Complete Idiots, Vol 2", 19900);
        assertEquals(false, salesIte1.addComment("Joshua Black", "Not worth the money. The font is too small.", -5));
    }

    /**
     * Test that a sales item is correctly initialised (name and price).
     */
    @Test
    public void testInit()
    {
        SalesItem salesIte1 = new SalesItem("test name", 1000);
        assertEquals("test name", salesIte1.getName());
        assertEquals(1000, salesIte1.getPrice());
    }
    
    @Test
    public void testDuplicateAuthorComment()
    {
        SalesItem item = new SalesItem("Space Helmet", 9999);
        boolean firstResult = item.addComment("Daniel", "Fits great!", 5);
        boolean secondResult = item.addComment("Daniel", "Actually, it's kinda of tight.", 3);
        assertTrue(firstResult);
        assertFalse(secondResult);
        
        assertEquals(1, item.getNumberOfComments());
    }
    
    @Test
    public void testInvalidRatingBoundaries()
    {
        SalesItem item = new SalesItem("X-ray Glasses", 2500);
        boolean resultLow = item.addComment("Daniel", "It doesn't even work.", 0);
        assertFalse("Rating 0 should be rejected (expected false)", resultLow);
        boolean resultHigh = item.addComment("Daksh", "Too good to be true!", 6);
        assertFalse("Rating 6 should be rejected (expected false)", resultHigh);
    }
    
    @Test
    public void testFindMostUsefulComment()
    {
        SalesItem item = new SalesItem("Magic Wand Deluxe", 50000);
        item.addComment("Daniel", "Truly magical", 5);
        item.addComment("Daksh", "Great product, but a bit expensive", 3);
        item.addComment("Richard", "No user manual? Seriously?", 1);
        
        //Let's simulate the user upvoting Daniel's comment because he's a
        //trustworthy guy :)
        item.upvoteComment(0);
        item.upvoteComment(0);
        item.upvoteComment(0);
        
        //We'll give one to Daksh too
        item.upvoteComment(1);
        
        //Richard's comment gets downvoted because he didn't find the user manual
        item.downvoteComment(2);
        item.downvoteComment(2);
        
        //So the most helpful comment is Daniel's, since it has 3 upvotes
        Comment mostHelpful = item.findMostHelpfulComment();
        assertNotNull("There should be a most helpful comment.", mostHelpful);
        assertEquals("Daniel", mostHelpful.getAuthor());
    }
    
    @Test
    public void testFindMostHelpfuLCommentButListIsEmpty()
    {
        SalesItem item = new SalesItem("Empty Item", 1000);
        Comment mostHelpful = item.findMostHelpfulComment();
        
        //But since there are no comments, it should be null
        assertNull("Expected null when there are no comments.", mostHelpful);
    }
    
    //Let's test that upvoteComment and downvoteComment affect the vote count correctly
    @Test
    public void testVotingOnComments()
    {
        SalesItem item = new SalesItem("Headphones", 2500);
        item.addComment("Daniel", "Excellent bass.", 5);
        
        //The initial votes should be 0
        Comment c = item.findMostHelpfulComment();
        assertEquals(0, c.getVoteCount());
        
        //Now we apply the votes
        item.upvoteComment(0);
        item.upvoteComment(0);
        //Let's also test the downvoting
        item.downvoteComment(0);
        
        //Now the votes should be +1
        assertEquals(1, c.getVoteCount());
    }
    
    //Finally, we can test that comments with duplicate authors are rejected
    @Test
    public void testDuplicateAuthorRejected()
    {
        SalesItem item = new SalesItem("Tablet S", 12000);
        assertTrue(item.addComment("Richard", "Pretty good tablet.", 4));
        assertFalse(item.addComment("Richard", "Actually, even better than I thought!", 5));
    }
}
