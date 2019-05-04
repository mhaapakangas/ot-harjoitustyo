package tetris.controllers;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tetris.models.DifficultyLevel;
import tetris.models.shapes.Shape;

import static org.mockito.Mockito.*;
import static tetris.models.Constants.GRID_HEIGHT;
import static tetris.models.Constants.GRID_WIDTH;

public class GameServiceTest {

    private GridService mockedGridService;
    private LevelService mockedLevelService;
    private ShapeGenerator mockedShapeGenerator;
    private Shape mockedShape;

    private GameService gameService;
    private int[][] grid;

    @Before
    public void setUp() {
        grid = new int[GRID_WIDTH][GRID_HEIGHT];
        mockedGridService = mock(GridService.class);
        mockedShapeGenerator = mock(ShapeGenerator.class);
        mockedShape = mock(Shape.class);
        mockedLevelService = mock(LevelService.class);

        when(mockedShapeGenerator.getNewShape()).thenReturn(mockedShape);
        when(mockedLevelService.getDifficultyLevel()).thenReturn(DifficultyLevel.EASY);
        gameService = new GameService(mockedGridService, mockedShapeGenerator, mockedLevelService);
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

    @Test
    public void testGameOver() throws InterruptedException {
        when(mockedGridService.getGrid()).thenReturn(grid);

        when(mockedShape.canMoveDown(grid)).thenReturn(false);
        when(mockedShape.hasValidPosition(grid)).thenReturn(false);

        Assert.assertFalse(gameService.isGameOver());

        Thread.sleep(1000);
        gameService.update();

        verify(mockedGridService).addShapeToGrid(mockedShape);
        verify(mockedGridService).clearFullRows();
        verify(mockedShapeGenerator, times(2)).getNewShape();

        Assert.assertTrue(gameService.isGameOver());
    }

    @Test
    public void testGameUpdates() throws InterruptedException {
        when(mockedGridService.getGrid()).thenReturn(grid);

        when(mockedShape.canMoveDown(grid)).thenReturn(true);

        Thread.sleep(1000);
        gameService.update();

        verify(mockedShape).moveDown(grid);
    }

    @Test
    public void testLevelIncreases() throws InterruptedException {
        when(mockedGridService.getGrid()).thenReturn(grid);

        when(mockedShape.canMoveDown(grid)).thenReturn(false);
        when(mockedShape.hasValidPosition(grid)).thenReturn(true);
        when(mockedGridService.clearFullRows()).thenReturn(3, 2);

        Thread.sleep(1000);
        gameService.update();

        Assert.assertEquals(0, gameService.getLevel());

        Thread.sleep(1000);
        gameService.update();

        verify(mockedGridService, times(2)).clearFullRows();

        Assert.assertEquals(1, gameService.getLevel());
    }

    @Test
    public void testScoreIncreases() throws InterruptedException {
        when(mockedGridService.getGrid()).thenReturn(grid);

        when(mockedShape.canMoveDown(grid)).thenReturn(false);
        when(mockedShape.hasValidPosition(grid)).thenReturn(true);
        when(mockedGridService.clearFullRows()).thenReturn(4, 1);

        Assert.assertEquals(0, gameService.getScore());

        Thread.sleep(1000);
        gameService.update();

        Assert.assertEquals(1200, gameService.getScore());

        Thread.sleep(1000);
        gameService.update();

        verify(mockedGridService, times(2)).clearFullRows();

        Assert.assertEquals(1240, gameService.getScore());
    }

    @Test
    public void testOnlyUpdatesWhenTimeHasElapsed() throws InterruptedException {
        when(mockedGridService.getGrid()).thenReturn(grid);

        when(mockedShape.canMoveDown(grid)).thenReturn(true);

        // These will have no effect
        gameService.update();
        gameService.update();
        gameService.update();

        Thread.sleep(1000);
        gameService.update();

        verify(mockedShape, times(1)).moveDown(grid);
    }
}
