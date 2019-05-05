package tetris.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreTest {

    @Test
    public void testHashCodeAndEquals() {
        Score score = new Score(101, "Player");
        Score score2 = new Score(101, "Player");

        assertTrue(score.equals(score2));
        assertEquals(score.hashCode(), score2.hashCode());
    }

    @Test
    public void notEqualWhenDifferentScore() {
        Score score = new Score(101, "Player");
        Score score2 = new Score(25, "Player");
        assertFalse(score.equals(score2));
    }

    @Test
    public void nonEqualWhenDifferentUsername() {
        Score score = new Score(101, "Player");
        Score score2 = new Score(101, "Player2");
        assertFalse(score.equals(score2));
    }

    @Test
    public void nonEqualWhenDifferentType() {
        Score score = new Score(101, "Player");
        Object o = new Object();
        assertFalse(score.equals(o));
    }
}
