package tetris.models.shapes;

import org.junit.Before;
import org.junit.Test;
import tetris.models.Position;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static tetris.models.Constants.GRID_HEIGHT;
import static tetris.models.Constants.GRID_WIDTH;

public class JShapeTest {
    private JShape shape;
    private int[][] grid;

    @Before
    public void setUp() {
        grid = new int[GRID_WIDTH][GRID_HEIGHT];
    }

    @Test
    public void shapeCanMoveLeft() {
        shape = new JShape(new Position(1, 4));
        shape.moveLeft(grid);

        Position expected = new Position(0, 4);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCannotMoveLeftWhenBlocked() {
        shape = new JShape(new Position(5, 4));
        grid[4][4] = 1;
        shape.moveLeft(grid);

        Position expected = new Position(5, 4);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCannotExitScreenLeft() {
        shape = new JShape(new Position(0, 4));
        shape.moveLeft(grid);

        Position expected = new Position(0, 4);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCanMoveRight() {
        shape = new JShape(new Position(6, 4));
        shape.moveRight(grid);

        Position expected = new Position(7, 4);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCannotMoveRightWhenBlocked() {
        shape = new JShape(new Position(5, 4));
        grid[6][4] = 1;
        shape.moveRight(grid);

        Position expected = new Position(5, 4);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCannotExitScreenRight() {
        shape = new JShape(new Position(7, 4));
        shape.moveRight(grid);

        Position expected = new Position(7, 4);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCanMoveDown() {
        shape = new JShape(new Position(5, 17));
        shape.moveDown(grid);

        Position expected = new Position(5, 18);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCannotMoveDownWhenBlocked() {
        shape = new JShape(new Position(5, 4));
        grid[7][6] = 1;
        shape.moveDown(grid);

        Position expected = new Position(5, 4);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void canMoveDownReturnsTrueWhenNotBlocked() {
        shape = new JShape(new Position(5, 4));
        assertTrue(shape.canMoveDown(grid));
    }

    @Test
    public void canMoveDownReturnsFalseWhenBlocked() {
        shape = new JShape(new Position(5, 4));
        grid[7][6] = 1;

        assertFalse(shape.canMoveDown(grid));
    }

    @Test
    public void shapeCannotExitScreenDown() {
        shape = new JShape(new Position(8, 18));
        shape.moveDown(grid);

        Position expected = new Position(8, 18);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCanRotate() {
        shape = new JShape(new Position(5, 5));

        assertArrayEquals(shape.rotations[0], shape.getOrientation());

        shape.rotate(grid);
        assertArrayEquals(shape.rotations[1], shape.getOrientation());

        shape.rotate(grid);
        assertArrayEquals(shape.rotations[2], shape.getOrientation());

        shape.rotate(grid);
        assertArrayEquals(shape.rotations[3], shape.getOrientation());

        shape.rotate(grid);
        assertArrayEquals(shape.rotations[0], shape.getOrientation());
    }

    @Test
    public void shapeCannotRotateWhenBlocked() {
        shape = new JShape(new Position(5, 5));
        grid[6][7] = 1;

        assertArrayEquals(shape.rotations[0], shape.getOrientation());

        shape.rotate(grid);
        assertArrayEquals(shape.rotations[0], shape.getOrientation());
    }
}
