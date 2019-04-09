package tetris.models.shapes;


import org.junit.Before;
import org.junit.Test;
import tetris.models.Position;

import static org.junit.Assert.assertEquals;
import static tetris.models.Constants.GRID_HEIGHT;
import static tetris.models.Constants.GRID_WIDTH;


public class OShapeTest {
    private OShape shape;
    private int[][] grid;

    @Before
    public void setUp() {
        grid = new int[GRID_WIDTH][GRID_HEIGHT];
    }

    @Test
    public void shapeCanMoveLeft() {
        shape = new OShape(new Position(1, 4));
        shape.moveLeft(grid);

        Position expected = new Position(0, 4);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCannotMoveLeftWhenBlocked() {
        shape = new OShape(new Position(5, 4));
        grid[4][5] = 1;
        shape.moveLeft(grid);

        Position expected = new Position(5, 4);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCannotExitScreenLeft() {
        shape = new OShape(new Position(0, 4));
        shape.moveLeft(grid);

        Position expected = new Position(0, 4);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCanMoveRight() {
        shape = new OShape(new Position(7, 4));
        shape.moveRight(grid);

        Position expected = new Position(8, 4);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCannotMoveRightWhenBlocked() {
        shape = new OShape(new Position(5, 4));
        grid[7][4] = 1;
        shape.moveRight(grid);

        Position expected = new Position(5, 4);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCannotExitScreenRight() {
        shape = new OShape(new Position(8, 4));
        shape.moveRight(grid);

        Position expected = new Position(8, 4);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCanMoveDown() {
        shape = new OShape(new Position(5, 17));
        shape.moveDown(grid);

        Position expected = new Position(5, 18);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCannotMoveDownWhenBlocked() {
        shape = new OShape(new Position(5, 4));
        grid[6][6] = 1;
        shape.moveDown(grid);

        Position expected = new Position(5, 4);
        assertEquals(expected, shape.getPosition());
    }

    @Test
    public void shapeCannotExitScreenDown() {
        shape = new OShape(new Position(8, 18));
        shape.moveDown(grid);

        Position expected = new Position(8, 18);
        assertEquals(expected, shape.getPosition());
    }
}
