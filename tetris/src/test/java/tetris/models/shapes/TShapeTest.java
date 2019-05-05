package tetris.models.shapes;

import org.junit.Before;
import org.junit.Test;
import tetris.models.Position;

import static org.junit.Assert.*;
import static tetris.models.Constants.GRID_HEIGHT;
import static tetris.models.Constants.GRID_WIDTH;

public class TShapeTest {
    private TShape shape;
    private int[][] grid;

    @Before
    public void setUp() {
        grid = new int[GRID_WIDTH][GRID_HEIGHT];
    }

    @Test
    public void shapeCanMoveLeft() {
        shape = new TShape(new Position(1, 4));
        shape.moveLeft(grid);

        Position expected = new Position(0, 4);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCannotMoveLeftWhenBlocked() {
        shape = new TShape(new Position(5, 4));
        grid[5][4] = 1;
        shape.moveLeft(grid);

        Position expected = new Position(5, 4);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCannotExitScreenLeft() {
        shape = new TShape(new Position(0, 4));
        shape.moveLeft(grid);

        Position expected = new Position(0, 4);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCanMoveRight() {
        shape = new TShape(new Position(6, 4));
        shape.moveRight(grid);

        Position expected = new Position(7, 4);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCannotMoveRightWhenBlocked() {
        shape = new TShape(new Position(5, 4));
        grid[7][4] = 1;
        shape.moveRight(grid);

        Position expected = new Position(5, 4);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCannotExitScreenRight() {
        shape = new TShape(new Position(7, 4));
        shape.moveRight(grid);

        Position expected = new Position(7, 4);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCanMoveDown() {
        shape = new TShape(new Position(5, 17));
        shape.moveDown(grid);

        Position expected = new Position(5, 18);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCannotMoveDownWhenBlocked() {
        shape = new TShape(new Position(5, 4));
        grid[7][6] = 1;
        shape.moveDown(grid);

        Position expected = new Position(5, 4);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCannotExitScreenDown() {
        shape = new TShape(new Position(8, 18));
        shape.moveDown(grid);

        Position expected = new Position(8, 18);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCanRotate() {
        shape = new TShape(new Position(5, 5));
        grid[5][7] = 1;

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
        shape = new TShape(new Position(5, 5));
        grid[6][7] = 1;

        assertArrayEquals(shape.rotations[0], shape.getOrientation());
        
        shape.rotate(grid);
        assertArrayEquals(shape.rotations[0], shape.getOrientation());
    }

    @Test
    public void testHashCodeAndEquals() {
        Shape shape = new TShape(new Position(5, 0));
        Shape shape2 = new TShape(new Position(5, 0));

        assertTrue(shape.equals(shape2));
        assertEquals(shape.hashCode(), shape2.hashCode());
    }

    @Test
    public void notEqualWhenDifferentPosition() {
        Shape shape = new TShape(new Position(5, 0));
        Shape shape2 = new TShape(new Position(5, 1));
        assertFalse(shape.equals(shape2));
    }

    @Test
    public void nonEqualWhenDifferentPosition() {
        Shape shape = new TShape(new Position(5, 0));
        Shape shape2 = new TShape(new Position(5, 0));
        shape2.rotate(grid);
        assertFalse(shape.equals(shape2));
    }

    @Test
    public void notEqualWhenDifferentShape() {
        Shape shape = new TShape(new Position(5, 0));
        Shape shape2 = new ZShape(new Position(5, 1));
        assertFalse(shape.equals(shape2));
    }

    @Test
    public void nonEqualWhenDifferentType() {
        Shape shape = new TShape(new Position(5, 0));
        Object o = new Object();
        assertFalse(shape.equals(o));
    }
}
