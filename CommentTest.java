import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 测试类 CommentTest.
 *
 * @author  (你的名字)
 * @version (一个版本号或一个日期)
 */
public class CommentTest
{
    private Comment comment;
    
    public CommentTest()
    {
    }

    @BeforeEach
    public void setUp()
    {
        comment = new Comment("Daniel", "Great product!", 4);
    }

    @AfterEach
    public void tearDown()
    {
        comment = null;
    }
    
    @Test
    public void testAuthorAndRatingStoredCorrectly()
    {
        assertEquals("Daniel", comment.getAuthor());
        assertEquals(4, comment.getRating());
    }

    /**
     * Test that upvote increases the vote count by 1.
     */
    @Test
    public void testUpvoteIncreasesVotes()
    {
        int initialVotes = comment.getVoteCount();
        comment.upvote();
        assertEquals(initialVotes + 1, comment.getVoteCount());
    }

    /**
     * Test that downvote decreases the vote count by 1.
     */
    @Test
    public void testDownvoteDecreasesVotes()
    {
        int initialVotes = comment.getVoteCount();
        comment.downvote();
        assertEquals(initialVotes - 1, comment.getVoteCount());
    }
}
