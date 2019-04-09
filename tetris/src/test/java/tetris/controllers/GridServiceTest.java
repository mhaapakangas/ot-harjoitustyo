package tetris.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tetris.models.Position;
import tetris.models.shapes.OShape;
import tetris.models.shapes.Shape;
import tetris.models.shapes.TShape;

import static tetris.models.Constants.GRID_HEIGHT;
import static tetris.models.Constants.GRID_WIDTH;

public class GridServiceTest {
    private GridService gridService;

    @Before
    public void setUp() {
        gridService = new GridService();
    }

    @Test
    public void newGridIsEmpty() {
        int[][] grid = gridService.getGrid();

        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                Assert.assertEquals(0, grid[j][i]);
            }
        }
    }

    @Test
    public void canAddShapeToGrid() {
        Shape shape = new OShape(new Position(2, 7));

        gridService.addShapeToGrid(shape);
        int[][] grid = gridService.getGrid();

        int expectedColor = shape.getColor().ordinal();
        Assert.assertEquals(expectedColor, grid[2][7]);
        Assert.assertEquals(expectedColor, grid[2][8]);
        Assert.assertEquals(expectedColor, grid[3][7]);
        Assert.assertEquals(expectedColor, grid[3][8]);
    }

    @Test
    public void renderGridContainsShape() {
        Shape shape = new TShape(new Position(2, 7));

        int[][] grid = gridService.getRenderGrid(shape);

        int expectedColor = shape.getColor().ordinal();
        Assert.assertEquals(expectedColor, grid[3][7]);
        Assert.assertEquals(expectedColor, grid[2][8]);
        Assert.assertEquals(expectedColor, grid[3][8]);
        Assert.assertEquals(expectedColor, grid[4][8]);
    }

    @Test
    public void getRenderGridDoesNotAddShapeToInternalGrid() {
        Shape shape = new TShape(new Position(2, 7));

        gridService.getRenderGrid(shape);
        int[][] grid = gridService.getGrid();

        int expectedColor = 0;
        Assert.assertEquals(expectedColor, grid[3][7]);
        Assert.assertEquals(expectedColor, grid[2][8]);
        Assert.assertEquals(expectedColor, grid[3][8]);
        Assert.assertEquals(expectedColor, grid[4][8]);
    }

    @Test
    public void canClearRowsProperly() {
        gridService.addShapeToGrid(new OShape(new Position(0, 7)));
        gridService.addShapeToGrid(new OShape(new Position(2, 7)));
        gridService.addShapeToGrid(new OShape(new Position(4, 7)));
        gridService.addShapeToGrid(new OShape(new Position(6, 7)));
        gridService.addShapeToGrid(new OShape(new Position(8, 7)));

        gridService.clearFullRows();
        int[][] grid = gridService.getGrid();
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                Assert.assertEquals(0, grid[j][i]);
            }
        }
    }

    @Test
    public void canDropBlocksWhenClearingRows() {
        gridService.addShapeToGrid(new OShape(new Position(0, 7)));
        gridService.addShapeToGrid(new OShape(new Position(2, 7)));
        gridService.addShapeToGrid(new OShape(new Position(4, 7)));
        gridService.addShapeToGrid(new OShape(new Position(6, 7)));
        gridService.addShapeToGrid(new OShape(new Position(8, 7)));

        Shape droppedShape = new TShape(new Position(2, 5));
        gridService.addShapeToGrid(droppedShape);

        gridService.clearFullRows();

        int[][] grid = gridService.getGrid();

        int expectedColor = droppedShape.getColor().ordinal();
        Assert.assertEquals(expectedColor, grid[3][7]);
        Assert.assertEquals(expectedColor, grid[2][8]);
        Assert.assertEquals(expectedColor, grid[3][8]);
        Assert.assertEquals(expectedColor, grid[4][8]);
    }
}
