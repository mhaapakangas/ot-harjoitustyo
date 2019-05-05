package tetris.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PositionTest {
    @Test
    public void testHashCodeAndEquals() {
        Position position = new Position(2, 6);
        Position position2 = new Position(2, 6);

        assertTrue(position.equals(position2));
        assertEquals(position.hashCode(), position2.hashCode());
    }

    @Test
    public void notEqualWhenDifferentPosX() {
        Position position = new Position(2, 6);
        Position position2 = new Position(0, 6);
        assertFalse(position.equals(position2));
    }

    @Test
    public void nonEqualWhenDifferentPosY() {
        Position position = new Position(2, 6);
        Position position2 = new Position(2, 2);
        assertFalse(position.equals(position2));
    }

    @Test
    public void nonEqualWhenDifferentType() {
        Position position = new Position(2, 6);
        Object o = new Object();
        assertFalse(position.equals(o));
    }
}
