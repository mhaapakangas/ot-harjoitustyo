package tetris.controllers;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tetris.models.shapes.Shape;

import static org.mockito.Mockito.*;
import static tetris.models.Constants.GRID_HEIGHT;
import static tetris.models.Constants.GRID_WIDTH;

public class GameServiceTest {

    private GridService mockedGridService;
    private ShapeGenerator mockedShapeGenerator;
    private Shape mockedShape;

    private GameService gameService;
    private int[][] grid = new int[GRID_WIDTH][GRID_HEIGHT];

    @Before
    public void setUp() {
        mockedGridService = mock(GridService.class);
        mockedShapeGenerator = mock(ShapeGenerator.class);
        mockedShape = mock(Shape.class);

        when(mockedShapeGenerator.getNewShape()).thenReturn(mockedShape);
        gameService = new GameService(mockedGridService, mockedShapeGenerator);
    }

    @Test
    public void testMoveShapeLeft() {
        when(mockedGridService.getGrid()).thenReturn(grid);

        gameService.moveShapeLeft();

        verify(mockedShape).moveLeft(grid);
    }

    @Test
    public void testMoveShapeRight() {
        when(mockedGridService.getGrid()).thenReturn(grid);

        gameService.moveShapeRight();

        verify(mockedShape).moveRight(grid);
    }

    @Test
    public void testRotateShape() {
        when(mockedGridService.getGrid()).thenReturn(grid);

        gameService.rotateShape();

        verify(mockedShape).rotate(grid);
    }

    @Test
    public void testDropShape() {
        when(mockedGridService.getGrid()).thenReturn(grid);
        when(mockedShape.canMoveDown(grid)).thenReturn(true, true, false);

        gameService.dropShape();

        verify(mockedShape, times(3)).canMoveDown(grid);
        verify(mockedShape, times(2)).moveDown(grid);
    }

    @Test
    public void testGetRenderGrid() {
        when(mockedGridService.getRenderGrid(mockedShape)).thenReturn(grid);

        Assert.assertArrayEquals(grid, gameService.getRenderGrid());
    }
}
